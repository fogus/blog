As I listen to ["Soup" by Can](https://www.amazon.com/Ege-Bamyasi-Can/dp/B00LX65WE4/?tag=fogus-20) I'm reminded of the [time when Alan Kay took some time to post on Hacker News](https://news.ycombinator.com/item?id=11808551) addressing his thoughts and ideas about object-oriented programming. For raw context read the original thread, but in this post I'll attempt to annotate some of what was posted. For the purpose of this post anything in italics and all footnotes are assumed to be my words, blockquotes are questions from other HN denizens, and everything else are quotes from Dr. Kay.

## The 10,000ft view

I had several stages about "objects". The first was the collision 50 years ago in my first weeks of (ARPA) grad school of my background in math, molecular biology, systems and programming, etc., with [Sketchpad](https://www.youtube.com/watch?v=6orsmFndx_o), [Simula](https://www.youtube.com/watch?v=m0pEj58xlyU), and the proposed [ARPAnet](https://www.youtube.com/watch?v=Cg_XeRSD6Rg). This led to an observation that almost certainly wasn't original -- it was almost a tautology -- that since you could divide up a computer into virtual computers intercommunicating ad infinitum you would (a) retain full power of expression, and (b) always be able to model anything that could be modeled, and (c) be able to scale cosmically beyond existing ways to divide up computers. I loved this. Time sharing "processes" were already manifestations of such virtual machines but they lacked pragmatic universality because of their overheads (so find ways to get rid of the overheads ...)

Though you could model anything -- including data structures -- that was (to me) not even close to the point (it got you back into the soup). The big deal was encapsulation and messaging to provide loose couplings that would work under extreme scaling (in manners reminiscent of Biology and Ecology).

<em><p style="font-size:8px">As we know, modern OO programming is all about using classes to define and model data structures. While the use of interface-first design has helped manage the complexities added, I wonder if the point is still missed with this practice.</p></em>

A second stage was to mix in "the Lisp world" of Lisp itself, [McCarthy's ideas](http://jmc.stanford.edu/articles/index.html) about [robots](http://jmc.stanford.edu/articles/ascribing.html) and temporal logics, the AI work going on within ARPA (especially at MIT), and especially [Carl Hewitt's PLANNER language](https://www.youtube.com/watch?v=7erJ1DV_Tlo). One idea was that objects could be like servers and could be goal-oriented with PLANNER-type goals as the interface language.

A third stage were a series of Smalltalks at Parc that attempted to find a pragmatic balance between what was inevitably needed in the future and what could be done on the Alto at Parc (with 128K bytes of memory, half of which was used for the display!). This was done in partnership with [Dan Ingalls](https://twitter.com/daningalls?lang=en) and other talented folks in our group. The idealist in me gritted my teeth, but the practical results were good.
A fourth stage (at Parc) was to deeply revisit the temporal logic and "world-line" ideas (more on this below).

<em><p style="font-size:8px">A fascinating example of an early Smalltalk is the [ST-71 version](http://worrydream.com/EarlyHistoryOfSmalltalk/) that has a somewhat familiar implementation pattern matched with a logical matching system.</p></em>

A fifth stage was to seriously think about scaling again, and to look at e.g. [Gelernter's Linda "coordination language"](https://web.archive.org/web/20091219121402/http://wcat05.unex.es/Documents/Wells.pdf) as an approach to do loose coupling via description matching in a general publish and describe manner. I still like this idea, and would like to see it advanced to the point where objects can actually "negotiate meaning" with each other.

## McCarthy's Temporal Logic: "Real Functions in Time"

There's lots of context from the past that will help understanding the points of view presented here. I will refer to this and that in passing, and then try to provide a list of some of the references (I think of this as "basic CS knowledge" but much of it will likely be encountered for the first time here).

Most of my ways of thinking about all this ultimately trace their paths back to John McCarthy in the late 50s. John was an excellent mathematician[^nash] and logician. He wanted to be able to do consistent reasoning himself -- and he wanted his programs and robots to be able to do the same. Robots were a key, because he wanted a robot to be in Philadelphia at one time and in New York at another. In an ordinary logic this is a problem. But John fixed it by adding an extra parameter to all "facts" that represented the "time frame" when a fact was true. This created a simple temporal logic, with a visualization of "collections of facts" as stacked "layers" of world-lines.

[^nash]: Around the time that Kay posted these comments I read *[A Beautiful Mind](https://www.amazon.com/Beautiful-Mind-Biography-John-Forbes/dp/0684819066/?tag=fogus-20)* by Sylvia Nasar about the mathematician John Nash and it mentions that McCarthy was not liked by Nash and was the but of various pranks. Regardless of this (seemingly) mutual dislike, Nash and McCarthy invented a pure negotiation game called [So Long Sucker](https://boardgamegeek.com/boardgame/8304/so-long-sucker) that's notorious for its brutal nature.

This can easily be generalized to world-lines of "variables", "data", "objects" etc. From the individual point of view "values" are replaced by "histories" of values, and from the system point of view the whole system is represented by its stable state at each time the system is between computations. Simula later used a weaker, but useful version of this.

I should also mention [Christopher Strachey](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.332.3161&rep=rep1&type=pdf) -- a great fan of Lisp and McCarthy -- who realized that many kinds of programming could be unified and also be made safer by always using "old" values (from the previous frame) to make new values, which are installed in a the new frame. He realized this by looking at how clean "tail recursion" was in Lisp, and then saw that it could be written much more understandably as a kind of loop involving what looked like assignment statements, but in which the right hand side took values from time t and the variables assigned into existed in time t+1 (and only one such assignment could be made). This unified functional programming and "imperative like" programming via simulating time as well as state.

And let me just mention the programming language Lucid, by Ashcroft and Wadge, which extended many of Strachey's ideas ...

It's also worth looking at ["atomic transactions"](https://dl.acm.org/citation.cfm?id=726386) on data bases as a very similar idea with "coarse grain". Nothing ever gets smashed, instead things are organized so that new versions are created in a non-destructive way without race conditions. There is a history of versions.

The key notion here is that "time is a good idea" -- we want it, and we want to deal with it in safe and reasonable ways -- and most if not all of those ways can be purely functional transitions between sequences of stable world-line states.

<em><p style="font-size:8px">This is the core idea behind the Datomic database.</p></em>

The just computed stable state is very useful. It will never be changed again -- so it represents a "version" of the system simulation -- and it can be safely used as value sources for the functional transitions to the next stable state. It can also be used as sources for creating visualizations of the world at that instant. The history can be used for debugging, undos, roll-backs, etc.

In this model -- again partly from McCarthy, Strachey, Simula, etc., -- "time doesn't exist between stable states": the "clock" only advances when each new state is completed. The CPU itself doesn't act as a clock as far as programs are concerned.

