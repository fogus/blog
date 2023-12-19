In the [last episode of Clojure Golf](http://blog.fogus.me/2009/08/14/clojure-golf-episode-1/) we saw some interesting ways to write a function that takes a couple sequences, applies a function to the pairing elements, and then returns a list of the results based on a supplied filtering function.  Thanks to all who participated in that exercise.

This episode is a little tougher.

Below I have written a function that takes two numbers: any given number, and another number that acts as a starting point.  This function will then calculate the largest prime factor of the given number.  The implementation of this function came from a [Twitter](http://twitter.com) meme where people would describe [songs in code](http://search.twitter.com/search?q=songsincode) within the constraints of 140 characters.  As it turns out, *most* of the snippets were meant to be humerous and didn't actually run or represent legal source programs at all.  I wanted to break that trend while still maintaining some sense of humor about the whole thing.  In any case, before I thought of an entry I knew that my snippet would deal with the Tommy Tutone song [867-5309/Jenny](http://en.wikipedia.org/wiki/8675309).  However, what I didn't know at the time was that the number 8675309 is a prime number [^prime], but once I realized this I knew what [my #songsincode entry would be](http://twitter.com/fogus/status/3451864235).

[sourcecode lang="clj" gist="183954"]
;; largest prime factor
(defn lpf 
  "Takes a number n and a starting number d > 1 
   and calculates the largest prime factor of n 
   starting at number d.

   usage: (lpf 364362978 2) => 8675309"
  [n d] 
  (if (> d n) 
    (- d 1) 
    (recur 
     (#(if (zero? (rem % d)) 
         (recur (/ % d)) 
         %) 
      n) 
     (inc d))))
[/sourcecode]

I think the above function is likely as small as it can get [^small], but it's not very fast (e.g. `(lpf 1234567890123456789012345678901234567890 2)` [^bigun] took about 10 minutes on my machine).  Therefore, this episode of Clojure Golf is devoted to making `lpf` as fast as possible, while still preserving as much compactness as possible.  It would also be nice to see how to squeeze a few more characters out of this particular implementation.

As always, Clojure snippets are not required -- any programming language is encouraged.

-m

[^prime]: I'm [not the first person to discover this](http://divisbyzero.com/2009/08/25/367-5309-more-than-jennys-phone-number/).
[^bigun]: 5,964,848,081
[^small]: 99 characters, minus docstring

