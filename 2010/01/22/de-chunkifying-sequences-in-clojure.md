*note: code modified to work with Clojure v1.2 on 2010.Nov.27 thanks to a reminder by [Colin Jones](http://blog.8thlight.com/colin)*

At the first [CAP CLUG](http://www.meetup.com/Cap-Clug/) meetup I gave a [presentation about the features of the recent Clojure 1.1](http://www.fogus.me/static/preso/clj1.1+) and one of the topics that garnered interest was that of chunked sequences.  For those of you not familiar with chunked sequences they can be summarized fairly easily.  Clojure [sequence](http://clojure.org/sequences) functions are lazy, however with the release of Clojure 1.1 the granularity of this laziness was changed from a 1-at-a-time to a chunk-at-a-time model.  In other words, instead of "walking" through a sequence one node at a time, chunked sequences provide a "windowed" viewed on sequences 32-elements wide.  We can see this in action with the following code:

    (def gimme #(do (print \.) %))
    
    (take 1 (map gimme (range 32)))

We might expect that this little snippet would print `(.0)` because we're only grabbing the first element, and if you're running Clojure 1.0 that is exactly what you would see.  However, in Clojure 1.1 the picture is a bit different:

    ;=> (................................0)

If you count the dots you'll see exactly 32, which is what we would expect given my statement from the first paragraph.  Expanding a bit further if we increase the argument to `range` to be 33 instead what do you expect we'd then see?

    (take 1 (map gimme (range 33)))
    ;=> (................................0)

Yep, that's 32 dots again.  To move our chunky window to the right is as simple as asking for its first element by:

    (take 1 (drop 32 (map gimme (range 64))))
    ;=> (................................................................32)

You be might starting to wonder about this.  In fact, you might be worried that chunked sequences have squashed the entire point of lazy sequences and for small sequences you would be correct.  However, the benefits of lazy sequences are really striking for those of cyclopean magnitudes -- I'm talking larger than memory big.  Chunked sequences in those cases in those cases are an incredible boon because not only do they make sequence functions more efficient overall, they still fulfill the promise of lazy sequences: [avoiding full realization of interim results][rich].  

However, there are legitimate concerns about this chunked model and one such concern is that of desiring a 1-at-a-time model to avoid exploding computations.  Without going into too much detail about that, a nice straw-man to use as a counterpoint against chunked sequences is that of building an infinite sequence of [Mersenne primes][mp].  That is, implicit realization of the first 32 Mersenne primes through chunked sequences will be quite costly indeed.

Therefore, in order to combat such situations I present a simple function (useable only in the current "new" branch) to de-chunkify a lazy sequence and enforce the 1-at-a-time model:

    (defn seq1 [#^clojure.lang.ISeq s]
      (reify clojure.lang.ISeq
        (first [_] (.first s))
        (more [_] (seq1 (.more s)))
        (next [_] (let [sn (.next s)] (and sn (seq1 sn))))
        (seq [_] (let [ss (.seq s)] (and ss (seq1 ss))))
        (count [_] (.count s))
        (cons [_ o] (.cons s o))
        (empty [_] (.empty s))
        (equiv [_ o] (.equiv s o))))
    
    (take 1 (map gimme (seq1 (range 32))))
    ;=> (.0)
    
    (take 1 (drop 32 (map gimme (seq1 (range 64)))))
    ;=> (.................................32)

And there you go!  Now you can again safely generate your lazy, infinite sequence of Mersenne primes!  The world rejoices.

Bear in mind, that the code for `seq1` is in **no way official** and should not be used for production code.   Clojure will one day provide an official version of the function above, but for now I simply took a [rough sketch][rseq1] posted by Rich Hickey and made it work with the "master" branch as an exercise and to hopefully gain more insight into the whole matter of chunkiness in general.  Hopefully, it can serve the same purposes for you.

-m

p.s. I'm [writing a book about Clojure](http://joyofclojure.com) with [Chris Houser](http://blog.n01se.net).  The matter of chunked sequences and many other Clojure topics will be covered.  ^_^

[db]: http://old-www.cwi.nl/htbin/ins1/publications?request=abstract&key=BoZuNe:CIDR:05

[rich]: http://www.reddit.com/r/programming/comments/afyav/clojure_11_rc1_out_cuts_some_overhead_of/c0headd

[mp]: http://en.wikipedia.org/wiki/Mersenne_Primes

[rseq1]: http://paste.lisp.org/display/90536
