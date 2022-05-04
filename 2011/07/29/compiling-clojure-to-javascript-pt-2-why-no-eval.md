# Why no eval?

*this is the second entry in an n-part [series explaining the techniques and design principles of ClojureScript](http://blog.fogus.me/tag/clj-compilation). translations: [[日本語](http://ykomatsu.akaumigame.org/compiling-clojure-to-javascript-pt-2-why-no-eval-ja.html)]*

I was not expecting to talk about `eval` so soon in this series, but apparently its exclusion from [ClojureScript](http://github.com/clojure/clojurescript) was more controversial than I imagined.  However, by excluding `eval` the Clojure/core team was not acting miserly, but instead had valid reasons for its exclusion.  This post will go over why `eval` was excluded and why it may never see the light of day in the core distribution.

<a href="http://blog.fogus.me/wp-content/uploads/2011/07/random-image-from-library-of-congress1.jpeg"><img src="http://blog.fogus.me/wp-content/uploads/2011/07/random-image-from-library-of-congress1-238x300.jpg" alt="" title="random image from library of congress" width="238" height="300" class="aligncenter size-medium wp-image-3389" /></a>

## What is `eval`?

The first question you may have is "who cares?" but preceding that may be "what is eval?"  Simply put, `eval` is a function that takes a data structure representing a program and executes it in the context of the dynamic environment.  For example, in Clojure you can perform the follow:

    (eval '(+ 1 2 3))
    ;=> 6

This takes the list: the symbol `+` and the numbers `1`, `2`, and `3` and executes it as if it was typed into the REPL.  Let's look at a more interesting example:

    (def blip [\b \e \e \p])
    (def buzz {:beep 42})
    
    (eval (list* (keyword (reduce str blip)) 
                 [(symbol "buzz")]))
    
    ;=> 42

This fragment builds from pieces the list `(:beep buzz)` which is evaluated as a keyword lookup into a map.  This is an interesting example not because the result is useful, but in that it demonstrates an interesting property of Clojure (and Lisp in general) -- code is made of the same data structures that the language itself manipulates.  

## eval is Indeed Very Cool

One of the first games that programmers new to Lisp play is the `eval` game.  That is, they build a bunch of data structures using the Lisp functions and pass them into `eval`.  It's great fun.  Having `eval` in ClojureScript would also be a very useful tool.  Indeed, I suspect it would be extremely useful to base an in-page REPL on `eval`.  This REPL could then be made to operate within the context of the currently loaded ClojureScript-enabled page allowing direct manipulation of the page elements and even the runtime code itself.  This is potentially a very powerful debugging and incremental development tool for ClojureScript in the same way that [Firebug](http://getfirebug.com/) serves for JavaScript.  However, the greatest part about this "fictional" REPL is that [it doesn't require that ClojureScript provide `eval` **at all**](https://github.com/ibdknox/brepl).[^knew]

But honestly, if ClojureScript programs are composed of data forms directly manipulable by the language itself then why not just provide `eval` anyway?  The answer can be found, as many answers in life, in the tradeoffs.

## FojureScript

Imagine a language named FojureScript that is exactly like ClojureScript in every way except that it contains `eval` (and has awesome error messages).  FojureScript programs are compiled into JavaScript with a compiler written in another language named Fojure.  FojureScript's compiler also allows for [differing levels of optimization](http://blog.fogus.me/2011/07/21/compiling-clojure-to-javascript-pt1/) from raw JavaScript emission, to advanced dead-code elimination.  What happens when a call to `eval` is performed in FojureScript?  In the first scenario the naive `eval` falls down:

    (eval '(+ 1 2 3))
    ;!! Unknown function +.
    ;!! Maybe you meant fojs.bore._PLUS_?

So clearly the problem is that during the compilation process the function named by `+` is munged into a JavaScript function `fojs.bore._PLUS_`.  OK, no big deal.  FojureScript can be made to resolve the properly munged names at evaluation time with (relatively) minimal fuss.  However, what about the call to `eval` in the case of a maximally minified compilation?

    (eval '(+ 1 2 3))
    ;!! WTF dude!?  I have no idea what + is!
    :!! That function doesn't even exist.

What's the problem?  

If you looked in the FojureScript bore.fojs library you would see that `+` is clearly defined.  However, in the production code a call to `+` never actually occured and therefore its implementation was removed  completely from the runtime environment!

## Blurring the Lines

Although Lisp programs are made up of data structures available in the language, there is a marked difference between the data composing the code, and the data flowing through the code.  The data composing the language is subject to static analysis and a lot of information about it can be garnered at the time of compilation.  The data running through the code is known only at the time of execution and therefore can only be known in very superficial ways.  There is a stark line between what is known at compilation time and what is known at runtime.   Therefore, compilation can and will eventually remove a function that is needed at runtime via `eval`.[^comp]  And therein lies the rub.  The presence of `eval` blurs the line between runtime and compilation time requiring that everything available in the latter be present in the former. *(or is it the other way around?)*

## Trade-off

[A goal](https://github.com/clojure/clojurescript/wiki/Rationale) of the ClojureScript team is to provide maximally minified source code that maintains program semantics.  The trade-off therefore is that it is much more important as a ClojureScript design principle that the runtime environment be available at dev time than the dev environment be available at runtime.[^brepl]

The larger goal of Clojure (the family) is to generate high-performance code on *every single platform* and to support `eval` in ClojureScript is antithetical to that goal.  It would be a fool's errand to try and support `eval` in the face of aggressive code elimination -- you can have one, but not the other.  ClojureScript is designed to solve the types of big problems that are simply too difficult to fathom, much less achieve, using raw JavaScript or any number of JavaScript frameworks.  It's conceivable that these huge applications will require a bevy of libraries.  You cannot effectively leverage these libraries without an optimizing compiler utilizing dead-code elimination.  You cannot have the optimizing compiler if you wish to support `eval`. *Q.E.D.*

While you may not agree with all decisions made in the development of ClojureScript (especially regarding `eval`), I hope you will agree that it was not designed and developed by fools.  

I hope...

Anyone?

...

Bueller?

:F

*thanks to [Chris Redinger](https://github.com/redinger) and [Brenton Ashworth](http://blog.fogus.me/2010/08/03/take-5-brenton-ashworth/) for reading a draft of this post*

[^comp]: And if you extrapolate from this point you'll see why there is no runtime compilation.  That is, if a macro builds a form containing functions that were optimized away at runtime, then bad things will happen.

[^brepl]: This principle is manifest in [Chris Granger's brepl](https://github.com/ibdknox/brepl).

[^knew]: I knew this would happen, just not this quickly.  Great timing I'd say.
