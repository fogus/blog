Lately I've been obsessed with [palindromes](http://en.wikipedia.org/wiki/Palindrome).  I have no idea why, but I find the topic fascinating.  Besides reading papers and books[^books] on the subject, I've been exploring writing some utilities for detecting[^pp] and generating palindromes in Clojure sequences.

[^pp]: One of my favorite sites [Programming Praxis](http://programmingpraxis.com/2015/03/31/identifying-palindromes/) recently had an entry on detecting palindromes.  Even if you're not interested in palindromes, PP is worth exploring.

Before I talk about a couple of (IMO) cool Clojure functions, let me take a moment to define palindromic sequences.  Simply put, a palindromic sequence is one that "reads" the same forwards and backwards, or in code has the following property:

<pre class="prettyprint lang-clj">
(= a-sequence (reverse a-sequence))
;;=> true
</pre>

A concrete example of a palindromic sequence is the vector `[:a :b :b :a]`.

What led me to writing the functions below is that I've been toying around with a few game designs in my head that center around palindromes.  Specifically, I'm writing an AI that can play these designs and flesh out some of the sticking points.[^lib]  However, to make the AI somewhat smart it has to be able to look at a group of things and determine all of the possible palindromes that can be built from it.

## Detecting 

The first matter to deal with for my AI was to create a function that, given a sequence, returns `true` or `false` depending on whether it's palindromic.  I started out writing a convoluted `palindrome?` function that walked both ends of a sequence and compared the elements in place, but it wasn't terribly efficient nor legible.  Instead, I decided to just write the function almost exactly as I described it above:

<pre class="prettyprint lang-clj">
(defn palindrome? [s]
  (= (seq s) (reverse s)))

(palindrome? [1 2 3 2 1])
;;=> true

(palindrome? [1 2 3 1 2])
;; false

(palindrome? "abba")
;;=> true
</pre>

This turns out to be fairly fast, at least for my purposes, and it was definitely faster than my original.  A nice property about this implementation is that it'll detect palindromic strings[^pure] also.  This was a nice discovery, but the real fun came next.

[^pure]: It detects pure palindromes only and not those that require a manipulation of punctuation and spacing.

## Finding palindromes

Given a Clojure vector of `[:a :b :b]` there are two possible palindromes that can be made from it: `[:b :b]` and `[:b :a :b]`.  The way to find this answer is to perform the following steps:

 1. Find all of the different ways to group the elements of the original
 2. Calculate all of the different ways to arrange all of the groupings
 3. Keep only the palindromes

Thankfully, for me there is a Clojure contrib library called [math.combinatorics](https://github.com/clojure/math.combinatorics/) that has two functions, `partitions` and `permutations`, that could take care of #1 and #2.  Unfortunately for me, math.combinatorics only handles sequences of numbers, so I had to use modified versions[^comb] of the functions `parts` and `perms` instead.

First, to find all of the groupings of the original I just need to calculate the possible partitions:

<pre class="prettyprint lang-clj">
(->> (comb/parts [:a :b :b])) ;; calc groupings

;;=> (([:a :b :b]) 
;;    ([:a :b] [:b]) 
;;    ([:a] [:b :b]) 
;;    ([:a] [:b] [:b]))
</pre>

You'll notice that this doesn't create separate groups for the groups of `:a` and the first `:b` and another for the second `:b`.  This is because the groups created are considered equivalent and not replicated.  However, using a different input set shows a different behavior:

<pre class="prettyprint lang-clj">
(->> (comb/parts [:a :b :c])) ;; calc groupings

;;=> (([:a :b :c]) 
;;    ([:a :b] [:c]) 
;;    ([:a :c] [:b]) 
;;    ([:a] [:b :c]) 
;;    ([:a] [:b] [:c]))
</pre>

So you see, `[:a :b]` and `[:a :c]` are both generated.  Without going too far afield, using my modified functions allows me to manipulate the way that groups are formed by overriding the `Comparable#compareTo` method, but that's a different discussion.[^wilds]

Now that I've calculated the groupings, I can gather them up and get rid of all of the duplicates and singleton groups (which are uninteresting palindromes indeed):

<pre class="prettyprint lang-clj">
(->> (comb/parts [:a :b :b])   ;; calc groupings
     (apply concat)            ;; gather them up
     (filter #(> (count %) 1)) ;; remove singletons
     set)                      ;; remove dups

;;=> #{[:a :b] [:b :b] [:a :b :b]}
</pre>

There are more efficient ways to get this result, but this is good enough for my purposes.

Now that I have the unique groupings I want to use `perms` to generate all of the possible arrangements of the groupings:

<pre class="prettyprint lang-clj">
(->> (comb/parts [:a :b :b])   ;; calc groupings
     (apply concat)            ;; gather them up
     (filter #(> (count %) 1)) ;; remove singletons
     set                       ;; remove dups
     (map comb/perms))         ;; calc arrangements

;;=> (([:a :b] [:b :a]) 
;;    ([:b :b]) 
;;    ([:a :b :b] [:b :a :b] [:b :b :a]))
</pre>

So once again, I then want to gather the arrangements up and keep only the palindromes:

<pre class="prettyprint lang-clj">
(->> (comb/parts [:a :b :b])   ;; calc groupings
     (apply concat)            ;; gather them up
     (filter #(> (count %) 1)) ;; remove singletons
     set                       ;; remove dups
     (map comb/perms)          ;; calc arrangements
     (apply concat)            ;; gather them up
     (filter palindrome?))     ;; keep palindromes

;;=> ([:b :b] [:b :a :b])
</pre>

And that's it -- all of the possible palindromes for `[:a :b :b]`.  

## Done?

Even though there is a larger context to all of this, I wanted to share a few of the bits that I thought were interesting.  There's more to palindromic functions than what's shown in this post, including a function to take a sequence and calculate the most "profitable" arrangement.  For the purposes of the function, "profit" is defined as the total number of elements forming a palindrome (including sub-palindromes) in a sequence.  For example, for the sequence `[:a :a :b :b]` the most profitable arrangement is `[:a :b :b :a]` worth 6 points (`[:a :b :b :a]` plus `[:b :b]`).  That was fun to figure out, but I leave the implementation as an exercise to the reader.

Thanks for reading, and remember — *never a foot too far, even.*[^other]

:F

[^wilds]: For the purposes of the game experiment, I was toying with the idea of "wilds" that could be used in any position of the palindrome.

[^comb]: I hope to submit a patch supporting the same in math.combinatorics soon, but if it's not accepted then I'll just use my own.

[^books]: Three wonderful books on the subject include *[Palindromes and Anagrams](http://www.amazon.com/Palindromes-Anagrams-Howard-W-Bergerson/dp/0486206645/?tag=fogus-20)*, *[Poetical Ingenuities and Eccentricities](http://www.amazon.com/Poetical-Ingenuities-Eccentricities-Various-ebook/dp/B00AQMRG6S/?tag=fogus-20)*, and *[Go Hang a Salami! I'm a Lasagna Hog!](http://www.amazon.com/Hang-Salami-Lasagna-Hog-Palindromes/dp/0374444730/?tag=fogus-20)*.

[^lib]: During the implementation of this AI an interesting library (that I'm calling spielespielen) has fallen out.  I might open it up one day, but the mix of functions is so bizarre that I find it hard to imagine that anyone but myself would find it useful.  Still, it might make a good [code painting](http://blog.fogus.me/2015/02/16/code-painting/).

[^other]: I would love to see these functions written in other programming languages, so if you're willing I'd love to know about what you create. 