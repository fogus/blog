An interesting thought exercise is to pull apart ideas that are typically thought inseparable.  One could call this act [decomplecting](http://www.infoq.com/presentations/Simple-Made-Easy),[^rh] but for the purposes of this post I'll coin the term "adventures in tearing."

A few interesting examples in the wild include:

## Kernel

[The Kernel programming language](http://web.cs.wpi.edu/~jshutt/kernel.html) pulls apart function creation into two parts:
  1. `$vau` - an operator[^op] like `lambda` in Lisp-like languages but without evaluation if its arguments.
  2. `wrap` - an operator that takes the result[^res] of `$vau` and creates a function that receives its arguments evaluated.

[^op]: Excuse my imprecise terminology regarding Kernel -- my attempt here is to use more common parlance. A future post will be more precise.

[^res]: The combination of these two operations combine to provide function semantics similar to common programming languages. That is, `lambda` (or JavaScript-style `function`) is *not* a primitive type, but instead composed of two other more primitive features.

## David Hume

Whether you agree with [David Hume's philosophy](http://www.constitution.org/dh/hume.htm) or not, it's difficult to deny that he was a master of tearing ideas apart into their constituent parts.  Often when examining a composite's parts you'll gain a deeper understanding of the whole.  Instead, Hume famously attempted to use tearing to unravel the very fabric of human understanding.

Specifically, one of Hume's famous passages talks about the action of [one billiard ball connecting with another](http://www.gutenberg.org/files/9662/9662-h/9662-h.htm). The human notion that the action of one ball hitting another *causes* the other to move, according to Hume, is an illusion. Instead, causality is a fabrication of the human mind assigned to antecedent and sequent events conjoined in time. Further, that humans believe that any event causes another is a bias built over a lifetime of observing events constantly conjoined in a consistent way.

This is an exercise in tearing taken to an extreme that has motivated philosophers since 1748.


## Wat

Manuel Simoni's [Wat programming language](https://github.com/manuel/wat-js) decomposes type definition, creation, tags and access from the typical type/class definition model.

An example of Wat used in this way is as follows:

    (def (type-tag type-creator type-accessor) (make-type))
	;;=> (#{anonymous} #[Applicative anonymous] #[Applicative anonymous])
	
Where the `make-type` function returns a triplet described by the names above.  The interesting part about this feature is that it separates creation from access allowing one to limit access to one aspect or	both using ad hoc encapsulation mechanisms.  This is very similar to [Racket's `make-struct-type` feature](http://docs.racket-lang.org/reference/creatingmorestructs.html) with and added element of runtime reflection. It's a very clever extension to Scheme that seems fairly obvious in hindsight.  That's the funny thing about obviousness; it's of course obvious once someone shows it to you.  Unfortunately, people (and even programmers) are apt to take the obvious for granted and fail to see things as anything less than wholly formed ideas.  Wat's `make-type` feature has helped me to better grasp some elusive aspects of types, privacy and reflection.

Mr. Simoni talks more about this particular [Wat capability in a blog post](http://axisofeval.blogspot.com/2012/08/schemes-missing-ingredient.html); I highly recommend reading it.


## Clojure

[Clojure](http://clojure.org) pulls apart transformation from data representation and order in its [reducers library](http://clojure.com/blog/2012/05/08/reducers-a-library-and-model-for-collection-processing.html).

Additionally, Clojure has a multimethods feature that splits the idea of function dispatch from the dispatch logic itself. Instead, Clojure's multimethods allow you to define a function that is used as the dispatch logic that itself can be a different multimethod.[^mm]

[^mm]: While you can abuse multimethods along a dispatch chain like this, it can be a bit confusing. Additionally, although Clojure's multimethods are open in regard to the dispatch value, the dispatch function is locked by default. There are ways around that, but they're ill advised.

## Fini

There are other great examples of *adventures in tearing* including, but not limited to: [Datomic](http://www.datomic.com), [Common Lisp's MOP](http://www.alu.org/mop/index.html), the stack in Forth-like languages, `shift`/`reset` in delimited continuation models, and Mathematica's [Reap and Sow](http://reference.wolfram.com/mathematica/tutorial/CollectingExpressionsDuringEvaluation.html).

I've been thinking a lot about tearing ideas apart and while I've not blazed new ground I think that I've gained a greater appreciation for some (for me) difficult ideas. The process of tearing, or decomplecting if you will, has given me greater insight into [actors](http://en.wikipedia.org/wiki/Actor_model), [unification](http://www.github.com/clojure/core.unify), and continuations.  Once you get the mindset it's difficult to avoid viewing the world, ideas, prejudices and software as conglomerates of finer grained notions -- or at least attempting to anyway.  I hope that further adventures in tearing can shed some light on my recent elusive problems like browser-based apps, simulation, and event sourcing.

I plan to continue my studies in tearing and will [share](http://www.complected.org/) my ideas as I go.  I hope that you'll share yours as well.

:F

[^rh]: Of all the people that I've worked with, Rich Hickey is the Decomplector General.[^st]  His ability to pull apart a system into its parts is inspiring.

[^st]: Decomplector General sounds like a character in the *[Book of the New Sun series](http://en.wikipedia.org/wiki/The_Book_of_the_New_Sun)*.