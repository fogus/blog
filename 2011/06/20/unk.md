<img alt="" src="http://images.fogus.me/blog/titan.jpg" title="Unk" style="float:left; display:inline;" class="alignleft" width="150" height="150" />

[unk](https://github.com/fogus/unk) is a [memoization](http://en.wikipedia.org/wiki/Memoization) library for Clojure that provides an extension to the base capabilities.

Using unk
=========

unk is easy to use.

## Leiningen

    [unk "0.9.0"]

## Maven

    <dependency>
      <groupId>unk</groupId>
      <artifactId>unk</artifactId>
      <version>0.9.0</version>
    </dependency>

## Source

[Unk is on the Githubs](https://github.com/fogus/unk).

## Straight-up replacement

unk can be used in place of `clojure.core.memoize`.

    (def slowly (fn [x] (Thread/sleep 3000) x)) 
    (time [(slowly 9) (slowly 9)])
    ; "Elapsed time: 6000.63 msecs" 
    ;=> [9 9]

This calls for `fogus.unk.memo`:

    (def sometimes-slowly (manipulable-memoize slowly)) 
    (time [(sometimes-slowly 108) (sometimes-slowly 108)]) 
    ; "Elapsed time: 3000.409 msecs" 
    ;=> [108 108]

`fogus.unk.memo` is a raw replacement for `clojure.core.memoize` -- it does the exact same thing.  All of unk's memoization strategies can operate as replacements for `memoize`, except that each displays its own operational characteristics.

## Fifo

There is a basic Fifo cache.

    (def id (memo-fifo identity 2))

This creates a new function `id` operating with a First-in-first-out cache of size two.  How would this operate under use?

    (id 42)
    ;=> 42

What does the cache look like?

    (snapshot id)
    ;=> {[42] 42}

Simple enough.  But what if we cross the size limit?

    (id 108)
    ;=> 108
    (id 9)
    ;=> 9
    (snapshot id)
    ;=> {[108] 108, [9] 9}

Of course, the first entry in the cache `42` has been removed.

## And the rest...

unk also provides a Least-recently-used (LRU), Least-used (LU), Time-to-live (TTL, in ms), and a basic cache (i.e. like `memo`) built on soft references.[^soft].  I will not go into incredible detail about those here, but I am working on documentation that will explain more and include more thorough usage scenarios.

# Why unk?

Because I needed it on a project.  I didn't simply need memoization.  I needed pluggable (LRU, LU, TTL, FIFO, etc.), manipulable (`memo-swap!`, `memo-clear!`, `memo-unwrap`, etc.), extendable (`build-memoizer`), and inspect-able (`snapshot`, etc.) memoization.  It's as simple as that.  [I suspect you may need it too](http://dev.clojure.org/jira/browse/CLJ-804).  This is why I'm announcing it now, rather than waiting until it's fully documented.

For now you can view the [annotated unk source code](http://www.fogus.me/fun/unk/) for some ideas.

## The true story of unk

unk is inspired by section 12.4 in *[The Joy of Clojure](http://joyofclojure.com)* which is in turn inspired by the [memoization philosophy](http://kotka.de/blog/2010/03/memoize_done_right.html) espoused by [Christophe Grand](http://clj-me.cgrand.net/), Eugen Dück, and [Meikel Brandmeyer](http://kotka.de/).  In addition, I would like to thank [Chas Emerick](http://cemerick.com/) for his [memoization based on SoftReferences](https://gist.github.com/747395).  This project would be nothing[^not] without their original vision.  Thanks gentlemen!

:F

[^soft]: In alpha at the moment.  Use with care.  Patches welcomed.

[^not]: Seriously... nothing... I never would have thought of this on my own.