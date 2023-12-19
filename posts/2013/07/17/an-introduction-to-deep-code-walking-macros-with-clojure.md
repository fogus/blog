With the release of the [core.async](https://github.com/clojure/core.async) library the Clojure community has been exposed to the joys[^0] of [Communicating Sequential Processes](http://swannodette.github.io/2013/07/12/communicating-sequential-processes/) and [Go-style asynchronousity](https://github.com/clojure/core.async/blob/master/examples/walkthrough.clj) [^1].  While this is all very exciting and new for the Clojure community at-large, a likewise interesting aspect of core.async's release has been exposed -- deep code-walking macros -- a subject I will give an overview of herein.[^2]

[^1]: And the [misconceptions of asynchronous APIs](http://martintrojer.github.io/clojure/2013/07/07/coreasync-and-blocking-io/).

[^2]: If you've ever watched my [Macronomicon talk](http://www.youtube.com/watch?v=0JXhJyTo5V8) and wondered what the heck my koan in the beginning meant then this post is a hint.

In chapter 17 of *[The Joy of Clojure](http://www.joyofclojure.com/)* we show an example of a macro called `defformula` that allows you to define spreadsheet-like cells in Clojure using [watchers](http://blog.fogus.me/2011/09/23/clojurescript-watchers-and-validators/).  For that example we intentionally required that the formulas defined be preceded by a binding vector, like so:

<pre class="prettyprint clojure">
(defformula avg [at-bats ab hits h]
  (float (/ @hits @at-bats)))
</pre>

This allowed us to tie formula-internal variable names with existing reference types without requiring our implementation to garner the ties programmatically.  The point of that section had to do with [the Observer Pattern](http://en.wikipedia.org/wiki/Observer_pattern), so we didn't want to clutter the discussion with other concerns.  However, we can use the same example to illustrate deep code-walking macros (DCWM) and doing so will lead to a different implementation.  For this post I'll talk about only two types[^3] of DCWMs, namely: additive transformers and in-place transformers.

[^3]: The amazing book *[Let Over Lambda](http://www.amazon.com/Let-Over-Lambda-Doug-Hoyte/dp/1435712757?tag=fogus-20)* talks about these types of DCWMs in depth, and much much more (although he doesn't use the same terminology).

## Deep walking for additive transforms

The `defformula` macro in [The Joy of Clojure](http://www.joyofclojure.com) was indeed an additive transformer.  That is, the macro took an expression and augmented it with additional stuff, specifically calls to `add-watch`.  However, having the binding vector allowed us to assume reference type dereferencing forms (things like `@foo`).  However, if I want to infer dereferencing forms within an expression then I will need to dive into it and find them.  To do that I'd first like to have a function `deref-form?` that takes something and checks if it's a dereferencing form, implemented below:

<pre class="prettyprint clojure">
(ns formulas 
  (:require [clojure.walk :as walk]))

(defn deref-form? [form]
  (boolean
    (when (seq? form)       ;; <1>                                                                                       
      (let [[op & _] form]
        (and
         op
         (symbol? op)
         (or                 ;; <2>                                                                                      
          (= op 'deref)
          (= op 'clojure.core/deref)))))))
</pre>

  1. Only work on seqs
  2. Check for `deref` calls

You'll notice that the `deref-form` assumes that the `form` given is a read-expanded dereferencing form rather than a [reader-macro](http://clojure.org/reader) for (i.e. `(deref foo)` rather than `@foo`).  Given that assumption, it'll work as follows:

<pre class="prettyprint clojure">
(deref-form? 42)
;;=> false

(deref-form? [deref :foo])
;;=> false

(deref-form? (quote (deref foo)))
;;=> true

(deref-form? `(deref foo))
;;=> true
</pre>

Seems right.  Now that I can identify a dereferencing form I'd like to dive into an expression and gather up all of the names that serve as the target in said dereferencing forms.  The function to do this very task named `find-derefs` is implemented below:

<pre class="prettyprint clojure">
(defn find-derefs [expr]
  (if (coll? expr)
    (->> expr
         walk/macroexpand-all  ;; <1>                                                                                  
         (tree-seq coll? seq)  ;; <2>                                                                                  
         (filter deref-form?)  ;; <3>                                                                                  
         (map second)          ;; <4>                                                                                  
         set)                  ;; <5>                                                                                  
    #{}))
</pre>

  1. Expand all sub-forms
  2. Build a seq of all composite forms
  3. Take only the `deref` forms
  4. Take only their operands
  5. Make a set out of them

You'll notice that with the use of `walk/macroexpand-all` I can ensure that any nested `@foo`s are turned into the equivalent `(clojure.core/deref foo)` calls.  This lets me build up a set of the embedded dereferenced targets in expressions:

<pre class="prettyprint clojure">
(find-derefs :a)
;;=> #{}

(find-derefs 42)
;;=> #{}

(find-derefs [])
;;=> #{}

(find-derefs '(inc @c1))
;;=> #{c1}

(find-derefs '[{:foo @bar, :baz [@quux]}])
;;=> #{quux bar}
</pre>

The `find-derefs` function basically builds the information that the binding form in JoC provided explicitly.  Now that I can get at the names embedded in an expression, I can now implement a macro to build spreadsheet-like formulas whose values are dependent on the embedded references within:

<pre class="prettyprint clojure">
(defmacro formula [expr]
  `(let [formula#   (agent ~expr)               ;; <1>                                                               
         update-fn# (fn [key# ref# o# n#]       ;; <2>                                                               
                      (send formula#
                            (fn [_#] ~expr)))]
     (doseq [r# ~(find-derefs expr)]            ;; <3>
       (add-watch r#                            ;; <4>
         :update-formula
         update-fn#))

     formula#))
</pre>

  1. Set the Agent's initial value based on result of the formula
  2. Build a function to update the Agent with the new formula calculation
  3. For every internal dereference in `expr`...
  4. ... build a call setting a watcher that updates the formula value on change

The body of a `formula` is just an expression that depends on zero or more embedded dereferences.  Not only does `formula` deeply walk the expression to find the dereferences, but it also augments a normal call to `agent` (holding the result of the formula) with calls to `add-watch` on the embedded (i.e. its dependents) reference types that automatically update the `formula`'s value whenever any of its dependents change.  That is, rather than just define an Agent that holds the result of some expression like `(agent (float (/ @hits @at-bats)))` the `formula` macro builds something like the following:

<pre class="prettyprint clojure">
(let* [formula (agent (float (/ (deref hits) 
                                (deref at-bats))))
       update-fn (fn [key ref o n]
                   (send formula
                     (fn [_]
                       (float (/ (deref hits) 
                                 (deref at-bats))))))]
   (doseq [r #{hits at-bats}]
     (add-watch r :update-formula update-fn))

   formula)
</pre>

That's quite a transformation!  Because of this transformation `formula` allows you to do the following:

<pre class="prettyprint clojure">
(def hits (ref 25))
(def at-bats (ref 100))

(def avg (formula (float (/ @hits @at-bats))))

@avg
;;=> 0.25
</pre>

As shown, the `avg` formula holds the result of the division of the numbers stored in the Refs `hits` and `at-bats`.  What is so cool about DCWMs is that they **allow me to define rich semantics** for the simple `formula` expression and augment it with spreadsheet-like capabilities:

