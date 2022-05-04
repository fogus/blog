When I first started using Clojure I was often very confused.  My previous context before finding Clojure was one where things like Java, an object-oriented mindset and bang-in-place update models reigned.  So, as you can imagine, that Clojure took a very different approach to modeling time and preferring functional composition seemed to me quite a hurdle to jump.  However, with some perseverance and free-time little things about Clojure gradually started to make sense.  For example, when I first encountered the ["threading macros"](http://blog.fogus.me/2009/09/04/understanding-the-clojure-macro/) `->` and `->>` I was mystified.  I don't know why.  There was simply a blockage for a day and a half that I simply couldn't overcome.  However, at the same time I learned that in Clojure the comma is considered a whitespace character.

**A ha!**

That was it!  I instantly understood how I could get over my blockage regarding `->` and `->>`.  That is, I could just put some commas into the stitch point of the threading macros to visually show where the flow was occurring from call to call -- something like this:[^if]

    (-> (/ 144 12) (/ ,,, 2 3) str keyword list)

But you know what?  I did that for about half a day before I stopped using it.  I didn't need it any more.  I had become enlightened in that tiny way.

Later in my career and long after I had seemingly exhausted my a ha moments I had the honor and pleasure of working with [Stuart Sierra](http://stuartsierra.com/) and [Brenton Ashworth](https://twitter.com/brentonashworth) on a system that would, as it turns out, serve as the spark for some of the core thinking in [Pedestal](http://pedestal.io/).  During that time I was exploring the idea of [event sourcing](http://martinfowler.com/eaaDev/EventSourcing.html) and felt like I was finally starting to get it when Stuart and Brenton showed me a part of the system that they were working on rehydrate its state based on a stream of sequenced events.

**Ha ha!**

While I had done it before, it was this moment that I really started looking at systems, rather than just programming languages and applications through a Clojure-focused lens.  You see, event sourcing fit so neatly with "The Clojure Way" that I should have seen it all along, but for whatever reason it didn't click.  I suppose looking back on it I must have always known of the fit, but it didn't click until that moment.  Programmers, chemists, architects, engineers and physicists often have these moments of realization punctuated by a laugh.  The mechanism is much the same as that of a joke or likewise a Zen story -- there is a blockage followed by a sudden realization followed by a laugh.

Recently I've been working on a project that, to me, is the product of some long thought focused through a Clojure lens, the culmination of which has transported me into another frame of reference[^pink] for systems building.  Whether or not what I've created is novel is irrelevant, I've transcended the way that I previously viewed systems -- there're no turning back, yet all I did was to run some code, view a console message and say **Aah...**

These are the moments that we hope for as programmers.  These three reactions are why we do what we do.

:F

[^pink]: Arthur Koestler in *[The Act of Creation](http://www.amazon.com/The-Act-Creation-Arthur-Koestler/dp/0330244477/?tag=fogus-20)* talks about these responses and how they relate to an unexpected unification between two different frames of reference.  It's a good read.  Alan Kay discusses the same ideas when he occasionally talks about "something almost new."  The best discussion by Kay along these lines is probably *[The computer revolution hasn't happened yet](http://www.youtube.com/watch?v=oKg1hTOQXoY)*.

[^if]: *Interfolation* (v) The act of inserting `,,,` into the stitch point of the Clojure threading macro for the sake of clarity and/or enlightenment.