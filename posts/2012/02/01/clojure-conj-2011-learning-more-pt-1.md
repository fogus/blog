By my measure, the [second Clojure Conj](http://clojure-conj.org/) was a smashing success.  I wrote summaries of the [mainline Conj activities](http://clojure.com/blog/2011/11/17/second-conj.html) and the [unconference activities](http://clojure.com/blog/2011/11/17/second-conj.html) if you need some context.  The amount of brain power on tap at the conference was intoxicating.  In an attempt to present a feel for the topics of discussion in the lecture hall and hallways I'll post with links to posts, papers, presentations, and tidbits of information for learning more.

I've limited the scope of this post to cover only the [initial released talks](http://clojure.com/blog/2012/01/31/first-conj-2011-videos-available.html), but as more are released so too will I post more.

### Learning Clojure - Next Steps

[Stuart Sierra](http://stuartsierra.com/) started the conference with a talk about pushing [Clojure](http://clojure.org) and [ClojureScript](http://github.com/clojure/clojurescript) further, including:

* Extending the Clojure reader
* Using Clojure from Java
* Using the JVM's base concurrency primitives
* Using Clojure's rich data-structures instead of JSON
* And others...

<iframe src="http://blip.tv/play/AYLpuVUC.html?p=1" width="550" height="339" frameborder="0" allowfullscreen></iframe><embed type="application/x-shockwave-flash" src="http://a.blip.tv/api.swf#AYLpuVUC" style="display:none"></embed>

From this talk there are a couple of interesting follow ups at your disposal.  First, the idea of using Clojure's rich data formats as a replacement for JSON was a topic of discussion during my DevIgnition talk on December 2nd, 2011 entitled *[Code as data as code](http://www.slideshare.net/fogus/codedata).  The slides are only partially helpful, but I hope that a video will be available soon.  In any case I will likely explore this idea in a later post.  Second, the idea of an extensible Clojure reader via tagged literals was discussed deeper in Rich Hickey's keynote at the Conj and is currently [under design](http://dev.clojure.org/display/design/Tagged+Literals) and [implementation](https://github.com/clojure/clojure/blob/master/src/clj/clojure/core.clj#L6569).

### Concurrent Stream Processing

While Clojure's official [fork-join](http://docs.oracle.com/javase/tutorial/essential/concurrency/forkjoin.html) story is incomplete, David's talk discusses Revelytix's approach to concurrent data stream processing in their query processing engine.  With a bevy of nice images, he walked through the high-level architecture of a system designed to combine large data streams as one might [connect the ends of hoses](http://doc.cat-v.org/unix/pipes/).  Related to this point, and of critical importance to Clojure, is the focus on leveraging topnotch Java libraries in achieving their goals.  The near-seamless Java interop in Clojure is an incredible boon to companies and individuals using Clojure in their projects.

<iframe src="http://blip.tv/play/AYLpqDoC.html?p=1" width="550" height="339" frameborder="0" allowfullscreen></iframe><embed type="application/x-shockwave-flash" src="http://a.blip.tv/api.swf#AYLpqDoC" style="display:none"></embed>

One of the interesting aspects of David's talk was his discussion of how he and his team discovered ways to improve the design of their system, specifically how they might refactor [protocols](http://clojure.org/protocols) to split orthogonal concerns.  This kind of thought-process and its benefits was recently summarized beautifully [by Steve Losh on Hacker News](http://news.ycombinator.com/item?id=3515862).    

### Clojail

Anthony's talk on his [Clojail](https://github.com/Raynes/clojail) library that provides a sandboxed environment for Clojure execution has a long and storied precedent.  For example, [Racket's sandboxing capability](http://docs.racket-lang.org/reference/Sandboxed_Evaluation.html) is at least superficially very similar to Clojail.  However, Racket's runtime security mechanism called [Custodians](http://docs.racket-lang.org/reference/eval-model.html#(part._custodian-model)) are very different from the JVM model absorbed by Clojail, although the goals for each are similar.  

<iframe src="http://blip.tv/play/AYLpqEwC.html?p=1" width="550" height="339" frameborder="0" allowfullscreen></iframe><embed type="application/x-shockwave-flash" src="http://a.blip.tv/api.swf#AYLpqEwC" style="display:none"></embed>

Before Racket (and PLT-Scheme on which it's built) there was some research an implementation for [Scheme48 of a security kernel by Jonathan Rees called W7](http://mumble.net/~jar/pubs/secureos/secureos.html).[^rees]  The approach of W7 is quite breathtaking and there may be some lessons to take away for Clojail itself.

[^rees]: I find myself studying many things by Jonathan Rees these days.


### ClojureScript

Chouser's presentation on ClojureScript was given on a nifty little piece of presentation software written in ClojureScript named [Traction](https://github.com/Chouser/talk-cljs).  This is just an interesting aside.

#### Data structures

In his talk he talked about how ClojureScript's data structures are currently copy-on-write, mimicking Clojure's behavior below a certain threshold.  Reading over Clojure's implementation of [PersistentVector](https://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/PersistentVector.java#L170) and [PersistentHashMap](https://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/PersistentHashMap.java#L536) provides some insight into this approach.

<iframe src="http://blip.tv/play/AYLpqF8C.html?p=1" width="550" height="339" frameborder="0" allowfullscreen></iframe><embed type="application/x-shockwave-flash" src="http://a.blip.tv/api.swf#AYLpqF8C" style="display:none"></embed>

#### Protocols and types

Clojure's approach for implementing types and protocols is built on the [pseudo-classical](http://bolinfest.com/javascript/inheritance.php) inheritance pattern that stands in contrast to the [functional inheritance pattern](http://www.crockford.com/javascript/inheritance.html) espoused by Douglas Crockford.  Crockford's pattern isn't exactly Crockford's pattern at all, but instead has a rich history.  Some interesting implementations using this pattern to create class-based OO models include:

* [Oleg's purely functional OO library for Scheme](http://okmij.org/ftp/Scheme/pure-oo-system.scm)
* [Bryan O'Sullivan's BOS for Scheme48](https://gist.github.com/1482348)
* Chouser and I talk briefly about this approach in section 7.2 of *[The Joy of Clojure](http://joyofclojure.com)*
* and many many more...

### Extending JavaScript Libraries from ClojureScript

I enjoyed Kevin's talk very much as his humor and wit shone through in his talk.  Kevin's company [Keming Labs](http://keminglabs.com/) was one of the earliest adopters of ClojureScript and his talk focused on how they used it, how it helped them succeed, and some of its painful aspects.  Specifically, he focused on the use of the [D3 JavaScript data visualization library](http://mbostock.github.com/d3/) via ClojureScript interop.  

<iframe src="http://blip.tv/play/AYLpqHIC.html?p=1" width="550" height="339" frameborder="0" allowfullscreen></iframe><embed type="application/x-shockwave-flash" src="http://a.blip.tv/api.swf#AYLpqHIC" style="display:none"></embed>

In addition to a nice talk, he and his company put together a [ClojureScript pamphlet](http://keminglabs.com/talks/conj_2011/handout.pdf) that although a bit outdated at the moment[^alpha] is still a useful reference.  Since his talk ClojureScript has matured thanks in no small part to his own efforts and comments. 

[^alpha]: The cost of referencing alpha software.

### Tune in next time

There are more Conj 2011 videos due for release soon, so I will try to keep up as they are released.  

:F
