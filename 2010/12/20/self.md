<img src="http://farm6.static.flickr.com/5128/5277661046_06e23942a7_d.jpg" height=110px width=410px/>

I've been an object-oriented practitioner for years and like many others I've come to find it wanting.  To be fair, while I think there are valid complaints against OOP in general, the more egregious failings tend to be matters of implementation.  For example, in any discussion involving Java's OOP, the faults of Java's implementation tends to falsely serve as a proxy for the overall failings of object-orientation proper.  It's almost as if Java's specific implementation can serve as the template for a more general discussion point.  I guess this is how humans operate -- someone should figure out how to harness that for good.

Which leads me to the [Self programming language](http://labs.oracle.com/self/language.html).

Maybe you've never heard of Self?  Shame on you for sure, but your saving grace is that you've likely heard of[^ch8] or used a least one of: Javascript, [Io][io], [Ioke][ioke], [NewtonScript][ns], [Lua][lua], or [Cecil][cecil] and I guarantee that the creators of these language **have**.  To put it as succinctly as possible: Self is the quintessential prototype-based programming language and forms the nexus of influence for every other similar language.

I was initially introduced to the idea of prototypal inheritance when reading Douglas Crockford's [Javascript: The Good Parts][jsgp].  You see, I had been using Javascript on and off (as needed) for a long time prior to reading that book, but like many fools who use Javascript I chose to shoe-horn my notion of "good object-oriented principles" into the language.  Much to my dismay, Javascript let me do it.  Further to my dismay, [Mr. Crockford taught me how to do it](http://www.crockford.com/javascript/inheritance.html).  But I don't blame Crockford for my missing the point -- the fault was entirely my own.   Javascript is a very powerful programming language for many reasons that have nothing to do with object-oriented programming.  Instead, the point to be derived from Crockford's essay is not to implement classical inheritance and stop there under the assumption that you've reached the pinnacle of domain modeling.  Instead, we should explore how far we can push Javascript's inherent power to express our solutions in different ways.  Alas, it's always fun to mold a powerful programming language to operate under different guiding principles than its own, even if those principles happen to be less powerful.[^ways]

Which leads me back to the [Self programming language](http://selflanguage.org/).

You see, while Crockford gave me hint of the power potential for prototype-based languages, it wasn't until I read *Organizing Programs without Classes* by David Ungar, Craig Chambers, Bay-Wei Chang, and Urs Hölzle that I really became excited.  I'm a sucker for under-appreciated programming paradigms and techniques, so the ideal of the prototype was immediately appealing.  But as I read, hacked, and explored further I've come to realize that there is much more potential to prototypes than I could have ever imagined.  More amazingly, if you take some time to explore the excellent [Self bibliography](http://selflanguage.org/documentation/published/index.html) you might notice that its influence runs much deeper than just prototype-based techniques.  In fact, you can explore how its implementation of [polymorphic inline caches](http://selflanguage.org/documentation/published/pics.html) has driven much in [the way that Javascript engines are implemented][v8][^callsites] these days.  The [canonical implementation of Self can be found on Github](https://github.com/russellallen/self), and in the coming months I plan to tear it apart to see how it ticks.

Will you join me?

:F

[^ch8]: Or maybe you've read [section 9.2](http://www.infoq.com/resource/articles/the-joy-of-clojure/en/resources/JoyofClojureCH09.pdf)(PDF) in [The Joy of Clojure](http://joyofclojure.com)?

[v8]: https://groups.google.com/group/strongtalk-general/browse_frm/thread/15a0d0da2d8ea773/d1688526916e3324?hl=ky&#d1688526916e3324

[jsgp]: http://www.amazon.com/o/asin/0596517742?tag=fogus-20

[^callsites]: But call-site caching predates Self back to Smalltalk.  (see *Efficient implementation of the smalltalk-80 system* by L. Peter Deutsch and Allan M. Schiffman)

[io]: http://iolanguage.com/

[ioke]: http://ioke.org/

[ns]: http://waltersmith.us/newton

[lua]: http://www.lua.org/

[cecil]: http://www.cs.washington.edu/research/projects/cecil/www/cecil.html

[^ways]: That's not to say that prototypes are the pinnacle either.
