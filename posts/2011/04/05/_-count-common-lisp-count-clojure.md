There has been [some talk about the number of forms available in Clojure compared to Common Lisp](http://arcanesentiment.blogspot.com/2011/04/clojure-is-almost-as-big-as-common-lisp.html).  I find this line of thought puzzling.  This is the same line of thinking that has been thrown at Common Lisp for ages -- and the argument grows no more valid over time.[^1]  The only reason that we can have such a discussion is because forms and functions in Common Lisp and Clojure are countable in the first place.  How do you measure the size of a language's conceptual model?  Is the Var or the function the right currency with which to tally language size?  If so, then how many Vars are Java's checked exceptions worth?  

[A well-formed Forth program consists of hundreds of special-purpose words](http://thinking-forth.sourceforge.net/), but no one would say that Forth is a bloated language.  Instead, it's important to know that a language should always have the right number of forms and functions to be properly expressive.  Should Clojure eliminate useful functions for the sake of Var count?  Who pays the bill in that case?  Well, of course, the Clojure programmer does.  <img src="http://images.fogus.me/blog/toomanynotes.png" style="float: left; padding: 2em;"/> It's simple to write many of Clojure's core functions, so why do we need most of them?  We can instead force the programmer to write and re-write common functions over and over again.  This is a Scheme perspective.  Clojure is not Scheme.

Any function, form, or Var in Clojure's distribution is there for two reasons: 1) it is useful and 2) it provides abstraction.[^4]  Rich is very thoughtful[^2] in including or excluding new features into the language.  In the war between practicality and purity in Clojure's offerings, it's the former that wins.[^3]

Clojure has *the right number of Vars*&trade; to provide an expressive programming experience.  No less.

:f

*thanks to Michael Patterson, Edmund Jackson, fossi, and Pepijn de Vos for reading a draft of this post and providing feedback.*

[^1]: I've often found that people rallying against Common Lisp's "bloat" fail (or refuse) to recognize that the language was designed to allow the programmer to do *anything* not simply the *right* thing (as defined by the language designers).  It's doubly perplexing when [advocates of Common Lisp, who should know better](http://www.loper-os.org/?p=374), point their gnarled fingers at Clojure's supposed "bloat".

[^2]: I find myself using this word a lot these days.

[^3]: It's even better when a nice balance can be found, which Clojure strives for.

[^4]: Abstraction takes the typical cognitive form that we're all familiar with, but it also helps to abstract performance characteristics.  This distinction is the subject of another post.  ;-)
