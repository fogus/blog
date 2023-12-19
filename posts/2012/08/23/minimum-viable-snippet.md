Learning a new programming language, library or framework can be difficult. It often takes an incredible amount of patience, time and persistence to properly learn something new and of even moderate complexity. Given this unavoidable commitment, programmers are often reticent to choose one option over another. Granted, programmers looking to transition away from .NET as a career choice probably has all the motivation they need to learn Java and the popular products of its ecosystem.  However, for the casual, autodidact or adventurous programmer presented with a cyclopean mountain of choice, there is, on the surface, little reason to choose one option over another.  Therefore, like many choices chosen by computerists, there is a strong desire to cut corners in their learning, or at least receive as much help as possible along the way.

One extremely simple way that a software designer or community can help the neophyte is what I like to call the *Minimum Viable Snippet*.  The Minimum Viable Snippet is a tiny fragment of source code that adheres to the following properties:

 1. It's located on the landing page of the main software site.
 2. It is typically located in a way that draws the eye.
 3. It is fervently representative of the library, language or framework.
 4. It's short and sweet and as elegant as possible; either self-evident in its intent or minimally explained via commentary.

While you will not always find that the Minimum Viable Snippet is given a place of central focus (point #2), the less scrolling required to see it the better.  An important point about #3 is that under no circumstances should the Minimum Viable Snippet be a "Hello World" example.  You can do better, but it might require some thought. Take some time to think up a snippet that illustrates what your software is all about. Finally, #4 is important because it really highlights the essential nature of your software or language. That is, if the Minimum Viable Snippet is 500 lines long, then it gives the prospective user a notion of what kind of pain they can expect.  Likewise, if the snippet is without commentary, then a programming language like [J](http://www.jsoftware.com/) is fighting an uphill battle.

There are some excellent examples of Minimum Viable Snippets to take inspiration from:

* Regardless of your feelings about [Node.js](http://nodejs.org/), it's MVS fits almost all of the criteria listed above.

* [Ruby's Minimum Viable Snippet](http://www.ruby-lang.org/en/) is positioned perfectly, is nicely commented, and is lovely. Sadly it's a "Hello World", but at least it's nicely motivated by the commentary.

* One of my favorites is the MVS on [the Sinatra homepage](http://www.sinatrarb.com/). It could stand to be syntax highlighted, but otherwise it's near perfect.

* The programming language [Roy](http://roy.brianmckenna.org/) has no MVS, but it has something else entirely; an operational programming console on the page itself. However, it would be nice to see some code prepopulating the console, awaiting one to hit the enter key.

* The contracts for Java library [Cofoja](http://code.google.com/p/cofoja/) has a nice MVS on its landing page. As a bonus, it's highly representational of the library itself and the philosophy of [DbC](http://en.wikipedia.org/wiki/Design_by_contract).

* Other nice examples (not comprehensive) include: [Akka](http://akka.io/), [jADT](http://jamesiry.github.com/jADT/index.html), [Joda Time](http://joda-time.sourceforge.net/), [Knockout.js](http://knockoutjs.com/). Comment below if you know of more.

So should we choose our programming languages and frameworks based solely on the MVS alone? Absolutely not, but it benefits the user to see a carefully chosen example of use from the start in order to save them some precious cycles trying to track one down.  Likewise it benefits the creator and community supporting the software to provide a low-pass filter for those who are likely to dismiss a system or language over its Minimum Viable Snippet. Everyone wins.

It's one of the few things in software that is a no-brainer.

:F