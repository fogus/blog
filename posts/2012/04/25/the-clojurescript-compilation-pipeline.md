*this is the fifth entry in an n-part [series explaining the compilation techniques of Clojure](http://blog.fogus.me/tag/clj-compilation).*

In honor of the upcoming [Clojure's Google Summer of Code projects](http://www.google-melange.com/gsoc/org/google/gsoc2012/clojure) I present some discussion of the ClojureScript compiler pipeline. I talked about this in my [ClojureWest](http://www.infoq.com/presentations/ClojureScript) talk, but a little more discussion is welcomed.  Much of what I say here also applies to Clojure's pipeline except that the details around data structures and actual programmatic interfaces are different.  I will attempt to stay at a high-level of abstraction. 

## The ClojureScript pipeline survey

This is the ClojureScript compiler pipeline:

![cljs](http://farm6.staticflickr.com/5341/7110268565_de4998482b_n_d.jpg "CLJS")

You put Clojure code in one end and out comes JavaScript from the other end. 

![cljs](http://farm8.staticflickr.com/7112/7110268589_cd20258b6e_d.jpg "CLJS")

The compiler is made of numerous phases the first of which is devoted to reading strings and converting them into Clojure data structures.  

![cljs](http://farm9.staticflickr.com/8161/7110268635_6dc99f64af_m_d.jpg "CLJS")

You can see how the reading phase works by observing the following in a Clojure REPL:

    (def E (read-string "(-> 42 (- 6) Math/sqrt)"))
    
    (type E)
    ;=> clojure.lang.PersistentList
    
    (type (last E))
    ;=> clojure.lang.Symbol

You can see `E` is a data structure.

The next compilation phase is the macro expansion phase.

![cljs](http://farm8.staticflickr.com/7094/6964196494_6480b368fc_m_d.jpg "CLJS")

During this phase a form will be expanded until it reaches some fixed point, as shown below:

    (-> E macroexpand-1)
    ;=> (clojure.core/-> (clojure.core/-> 42 (- 6)) Math/sqrt)
    
    (-> E macroexpand-1 macroexpand-1)
    ;=> (Math/sqrt (clojure.core/-> 42 (- 6)))
    
    (-> E macroexpand-1 macroexpand-1 macroexpand-1)
    ;=> (. Math sqrt (clojure.core/-> 42 (- 6)))
    
    (-> E macroexpand-1 macroexpand-1 macroexpand-1 macroexpand-1)
    ;=> (. Math sqrt (clojure.core/-> 42 (- 6)))

In the illustrative case above at three levels of macro expansion the form reached a fixed point (does not change from one level to the next).  Eventually the inner `->` macro will also expand, but that happens as the form is traversed during AST generation.  You'll notice that I made the macro expansion box a bit smaller.  The meaning for this difference is that macro expansion is interleaved with AST generation.  Apart from rote expansion of macros, this phase also shuffles arguments to the `.` (dot) operator into a consistent `(. target field/method args)` format.

The next phase is deemed the analysis phase and its primary purpose to generate the ClojureScript abstract syntax tree (AST).

![cljs](http://farm8.staticflickr.com/7252/6964196568_4a44028a2f_m_d.jpg "CLJS")

If you've talked to me about ASTs you'll probably had the unfortunate luck to hear all about my prejudice against the unfortunate phrase "Lisp syntax is the AST of the program itself" (or some such variation).  This is junk.  Actual ASTs are adorned with a boatload of additional information like local binding information, accessible bindings, arity information, and many other useful tidbits.

![ast](http://farm6.staticflickr.com/5458/6965437392_e3f451c048_d.jpg "ast")

You've likely seen a diagram like this (and probably images similar to the pipeline also) in other blog posts, textbooks and papers.  However, one advantage that the ClojureScript (and the Clojure compiler one day) provides is that between each compilation phase the interface consists solely of Clojure data!  The product of the reader is a list, some other Clojure data type, or a nesting thereof.  The product of the macro expansion is the same.  The product of the analysis phase is an AST itself composed of nested Clojure maps.  There is no product of the compilation phases that cannot be processed by the very tools that Clojure or ClojureScript themselves (and hundreds of libraries) handle directly.  This is truly an amazing feature of Clojure and Lisps in general.

The final stage is the emission phase that takes an AST and generates JavaScript.

![cljs](http://farm9.staticflickr.com/8010/6964196594_34112d25b1_m_d.jpg "CLJS")

Emission is likely the most complicated part of the entire ClojureScript compiler - it's about 700 lines of code.

## Jacking into the ClojureScript pipeline

The first obvious point of extension is at the backend of the analysis phase.

![cljs](http://farm8.staticflickr.com/7252/6964196568_4a44028a2f_m_d.jpg "CLJS")

This is effectively the approach that Ambrose Bonnaire-Sergeant takes in his [typed-clojure](https://github.com/frenchy64/typed-clojure).  The big difference is that his [analyze](https://github.com/frenchy64/analyze) project provides a ClojureScript-like AST using Clojure's analysis engine. It's very cool. Graphically, you can envision typed-clojure situated like the following:

![cljs](http://farm9.staticflickr.com/8027/7110268821_e268f74213_n_d.jpg "CLJS")

With the type checker adorning and vetting a tree generated by the analysis phase.  Question: what is the greatest limitation of Haskell's type system?  Think about it.  I answer this in my talk, but it's not a central point to this post.

![cljs](http://farm8.staticflickr.com/7249/6964196632_970f29f8a4_n_d.jpg "CLJS")

There is no reason to stop there however as conceptually one can imagine additional checking occurring on the AST in sequence.

![cljs](http://farm9.staticflickr.com/8148/6964196688_9eb932bbd7_d.jpg "CLJS")

Therefore, the model derived is one where an AST is gradually adorned through a pipeline.  This is a pluggable[^plug] compilation system; one that is fully in line with Clojure's ideal of open extension.

[^plug]: Gilad Bracha talks eloquently about pluggable type systems in his paper "Pluggable Type Systems" at <http://bracha.org/pluggableTypesPosition.pdf>

![cljs](http://farm6.staticflickr.com/5040/6964196720_1f4afb2269_d.jpg "CLJS")

This is a very powerful model, but [potentially fraught with complexity](http://clojure.com/blog/2012/04/19/take5-daniel-spiewak.html).  

However, while powerful, the sequential model is not terribly interesting nor desired.  Instead, a better model would be one that allows branching or tap logic into the pipeline. For example, if a program is isolated and fully typed then a branch (beta) may be an appropriate action.  However, if a program includes the use of untyped libraries then perhaps partial static typing ornamented with runtime constraint checks[^runtime] (alpha) may be the more appropriate branch path.[^docs]

[^runtime]: Gilad Bracha again with [Types are anti-modular](http://gbracha.blogspot.com/2011/06/types-are-anti-modular.html).

[^docs]: Although I have the docs phase situated along a separate branch it may more appropriately serve as a tap into all branches.

![branch](http://farm6.staticflickr.com/5332/6967119580_37053af588_d.jpg "branching")

Designing an interface to the analysis phase and a set of tools for processing its structures is fairly straightforward. The hard part comes in their use, tapping into the pipeline, and branch logic.  Careful design is required here.

The next point of extension is at the point of input to the emission phase.

![cljs](http://farm9.staticflickr.com/8010/6964196594_34112d25b1_m_d.jpg "CLJS")

By extension of course I mean that one can plug in their own emitter.

![cljs](http://farm8.staticflickr.com/7210/6964196766_e7b154efb1_n_d.jpg "CLJS")

This is likely the most straightforward way to target new platforms for ClojureScript and is indeed the one taken by [clojure-scheme](https://github.com/takeoutweight/clojure-scheme).  That is, with clojure-scheme Nathan Sorenson generates [Gambit Scheme](http://dynamo.iro.umontreal.ca/~gambit/wiki/index.php/Main_Page) at the backend.  The generated Scheme can then be further compiled to C and then finally compiled to machine code.

![cljs](http://farm8.staticflickr.com/7267/6964196792_0b5ae0ef62_d.jpg "CLJS")

