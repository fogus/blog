*this is the fourth entry in an n-part [series explaining the compilation techniques of Clojure](http://blog.fogus.me/tag/clj-compilation). translations: [[日本語](http://ykomatsu.akaumigame.org/compiling-clojure-to-javascript-pt-3-the-himera-model-ja.html)]*

When [ClojureScript](http://github.com/clojure/clojurescript) was first announced there was much gnashing of teeth over the fact that it provided neither `eval`, nor runtime macros. In response, I did [tackle the matter of `eval`](http://blog.fogus.me/2011/07/29/compiling-clojure-to-javascript-pt-2-why-no-eval/), but code speaks louder than words, so I therefore present [Himera](http://github.com/fogus/himera), a ClojureScript compilation web-service.

I have a deployment of [Himera on Heroku](http://himera.herokuapp.com/index.html)  (shown below -- caveat emptor) if you'd like to play with it. Additionally, the [Himera source code is available on Github](http://github.com/fogus/himera).

<a href="http://himera.herokuapp.com/index.html">
![himera-ws](http://farm8.staticflickr.com/7188/6874829058_0694d746c4_d.jpg "Click to visit Himera")
</a>

## What Himera is

Himera[^band] (Russian: Химера, pronounced Hee-mera with a trill) is an experiment in slicing and dicing the typical REPL model of Lisp computation, providing a modularized web service for ClojureScript compilation.

[^band]: Химера was a Russian band that I liked very much as a younger man.

## REPL

Lisps, and Clojure is no exception, provide a unique programming experience via the REPL. The canonical representation of the REPL described as source is summarized simply as:

    (loop (print (eval (read))))

You've probably seen this contrivance, but what exactly does it mean?  The diagram below is a graphical representation of the same idea:

![repl-vanilla](http://farm8.staticflickr.com/7272/6872246852_6e1f3775b8_n_d.jpg "REPL 10000ft")

That is, a REPL is a composition of three repeating functions: `read`, `eval`, and `print`.  The read step takes a string (or maybe an input buffer) and produces a Lisp data structure representing the program in hand[^ast-not].  This data structure is then fed into the `eval` function and executed as a program.  Finally, the result of the evaluation step (another Lisp data structure) is printed to the user. 

[^ast-not]: A popular view of a Lisp program is that its data structure, syntax, and abstract syntax tree are equivalent.  The fact of the matter is that these three things are very very different... the subject of a future post.

This model of the REPL is highly simplistic, but it serves as representational for most cases. However, because of a lot of historical baggage, the typical conception of this model is often limited to that of a single process, as in the image below:

![repl-1-proc](http://farm8.staticflickr.com/7271/7018375589_3394d31528_n_d.jpg "The REPL as single process")

But this is an outmoded ideal, whether it be at a console REPL:

![repl-console](http://farm8.staticflickr.com/7250/6872327792_04a2833c9d_n_d.jpg "REPL in da console")

... a browser:

![repl-browser](http://farm8.staticflickr.com/7109/6872327840_f047cfa2a9_n_d.jpg "REPL in da browser")

... or a phone:

![repl-phone](http://farm8.staticflickr.com/7272/6872328064_9d19f52a10_n_d.jpg "REPL in da iPhone")

It simply does not need to be configured in such a way.  The very nature of Lisp and its furcated architecture allows many different ways to arrange the components of a REPL.

## An exploded view of the REPL

Before I can talk about various ways to slice up the REPL into bits and pieces I should mention that the canonical image above is way too simplistic. Instead, the ClojureScript compiler is modularized along much finer dimensions than the Lispy trinity.  Observe the following:

![cljs-compiler-anatomy](http://farm8.staticflickr.com/7280/7018455969_e35f4bfffc_z_d.jpg "ClojureScript's compiler anatomy")

The constituent parts of the ClojureScript anatomy are as follows:

### Input

Some input device reads a string of characters and feeds it into the reader as a true string datatype or some input buffer.

### Reader

The reader consumes the string from the input device and transforms it into a Clojure data structure. In other words, the raw string:

    "(vector :thx (-> 1138 - str))"

Is converted into a Clojure persistent list data structure of three elements: 1) the symbol `vector`, 2) the keyword `:thx`, and 3) another persistent list of three elements a symbol `->`, `1138`, a symbol `-` and another symbol `str`.  The source view of this data structure is described in Clojure as:

    (list 'vector :thx (list '-> 1138 '- 'str))

The result of the Reader is always a Clojure data structure, Java instance, or an error.

### Macro expansion (macro-xp)

The raw Clojure data structures produced by the Reader are then processed for macro-expansion to some fixed point (i.e. they are expanded until the input equals the output). In the case of the structure listed above, the macro `->` would be expanded into the following:

    (vector :thx (str (- 1138)))

This is where Clojure's idea of (and Lisp in general) code as data diverges from the syntactic representation.

### Analyzer

The analysis phase of ClojureScript compilation builds an abstract syntax tree (AST) that represents the program itself, divorced from syntactic matters.  That is, the tree structure defines logical groupings along branches, binding contexts alongs tree depth, etc.  This is where Clojure's (and Lisp in general) code as data diverges from its parse form. The analysis phase also marks the end of the the first phase in ClojureScript's 2-phase compilation process.

### Emitter

This is where ClojureScript's AST is walked and transformed into JavaScript. This is the beginning and end of the second phase of ClojureScript's 2-phase compilation process.  This is also where you would typically deploy your ClojureScript application. However, in the context of a REPL layout, two more elements are missing.

### Eval (or runtime)

The JavaScript that is produced by the ClojureScript compiler is evaluated.  The original code `(vector :thx (str (- 1138)))` under examination above would result in what in Clojure would look like the following:

    [:thx "-1138"]

However, it would be JavaScript and therefore an instance of `cljs.core.Vector` containing two strings.[^kw-str]

[^kw-str]: Keywords in ClojureScript are compiled down to JavaScript strings prefixed with some magic unicode character.

### Print

The result of the JavaScript is "printed" via the appropriate means.

Taking this exploded view of the ClojureScript compiler to heart, imagine how the traditional REPL model might look differently under various operational constraints.  Below I will illustrate a few.

## The Browser-connected REPL

Because ClojureScript has neither runtime evaluation nor compilation elements, the [Clojure/core](http://clojure.com) team had to devise a way to provide an agile development experience that Clojure programmers were accustomed to. The initial release of ClojureScript packaged [Rhino](http://www.mozilla.org/rhino/) and used it as the evaluation engine of the emitted JavaScript, however, this was less than optimal for numerous reasons outside of the scope of this post. Eventually, it was decided that the evaluation engine should instead be that of a browser, as shown below:

![bc-repl](http://farm8.staticflickr.com/7051/6873346498_f822daa961_d.jpg "Browser-connected REPL")

