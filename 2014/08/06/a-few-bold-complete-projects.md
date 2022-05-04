One of my favorite bloggers James Hague recently wrote a blog post entitled *[Timidity Does Not Convince](http://prog21.dadgum.com/35.html)*.  In this relatively short post he managed to get me thinking about programming languages and their viability in a market for mindshare, especially with his statement:

> The only arguments that hold water, in terms of programming language 
> suitability, are bold, finished projects.

There are a lot of questions about this statement that jump to mind, the primary of which are:

 * What does "bold" and "finished" mean in the context of this statement?
 * What constitutes a "project" in the context of this statement? Is Mr. Hague talking about applications
   or can we assume that things like database and web frameworks are on the table also?

For my own purposes I'm going to assume that "bold" means "big and audacious" and "finished" means "once ran, but not necessarily saw widespread usage."

That said, I'd like to share a few of the "bold" projects that I'm aware of.  One of my favorite aspects of the original post is that Mr. Hague talks about two projects created in two non-mainstream languages, Pascal and Erlang.  Therefore, in this small follow-up[^0] I'd like to share a few of the bold, completed projects that I'm aware of in a few interesting languages.

**I'd love to see others write similar posts or comments listing their favorite bold projects in their favorite obscure languages.**

[^0]: That amazingly has run longer than the OP.

## Clojure

In the course of [my consulting work with Clojure](http://blog.fogus.me/2014/02/03/i-cognitect/) I've seen  numerous proprietary systems[^1] that I'd classify as bold, however I should probably not talk about those.  Instead for Clojure I'd like to offer [Datomic](http://www.datomic.com) as fitting into this category.  Without going into too much ceremony, Datomic is a distributed database system with an interesting query language and an even more interesting data model.

Another interesting system that I'd add to Clojure's credit is the one that runs [Prismatic](http://getprismatic.com/home).  I don't know anything about the details of this great service, but I'm aware that Clojure runs deep.

[^1]: Including one called Patagonia that I talked briefly about in a [talk at the 2013 Clojure Conj](https://www.youtube.com/watch?v=1E2CoObAaPQ).

## Erlang

Staying on this train of thought, [CouchDB](http://couchdb.apache.org/) is perhaps the first document database that I had ever heard of[^2].  I do not have much personal experience with CouchDB, but it's made a splash in a dense market and managed to avoid losing mindshare to the Nth-mover advantage.

[^2]: Though I suspect there will be a commenter in my future telling me about something similar created in Lisp or Smalltalk 30-40 years ago. ;)

## Ruby

Just [a little web framework](http://rubyonrails.org/) that fueled (and continues to?) billions of dollars in consulting revenue flowing through the computing industry.[^rr]

[^rr]: Generally I'd like to avoid web frameworks in this discussion because the first "large-project" for most modern languages includes either a build system or a web framework — or both.  I include Rails here because it's impact was so staggering for a time, relative to the marketshare of its host language.

## Common Lisp

One of my first simulation projects was to help maintain a large wargame simulation written entirely in Common Lisp.  Since I can't really talk about that one, I'll instead point to [Ron Garret's classic tale of Lisp at the Jet Propulsion Labs](http://www.flownet.com/gat/jpl-lisp.html).[^ps]

[^ps]: A huge thanks to Paul Snively for reminding me about this story.

## Visual Prolog

I worked for a company once with a very smart guy who worshiped at the Colmerauerian altar.  He was one of those guys who if you walked past his cubicle he'd grab you and start talking about Prolog.  I personally liked these kinds of conversations, but I can see why some might have been put off by it.  Rather than being a raving blow-hard, the guy actually created an amazing rule-based system (GUI and all in [Visual Prolog](http://www.visual-prolog.com/)) that was basically a highly-sophisticated system to help solve the napsack problem for container ships.  It was probably the cleanest Prolog code that I've ever seen and will ever see in my life.

What are your favorite bold projects in your favorite obscure languages?

:F

