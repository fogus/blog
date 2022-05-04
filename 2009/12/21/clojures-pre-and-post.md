One of the more exciting features of the upcoming Clojure 1.1 release is the inclusion of pre and post assertion functions.  Some preliminary details can be [found in the Clojure documentation](http://clojure.org/special_forms#toc10), but I thought I'd quickly cover a simple example with potentially far-reaching implications; the case of making `:post` assertions relative to the input argument(s).  

Let's re-write the simple function `constrained-sqr` as found in the Clojure documentation linked above and re-cast it as `constrained-fn`:

<pre lang="lisp">
(defn constrained-fn [f x]
  {:pre  [(pos? x)]
   :post [(= % (* 2 x))]}
  (f x))

(constrained-fn #(* 2 %) 2)
;=> 4
(constrained-fn #(float (* 2 %)) 2)
;=> 4.0
(constrained-fn #(* 3 %) 2)
;=> java.lang.Exception: Assert failed: (= % (* 2 x))
</pre>

So what have I done?  By specifying a `:post` assertion of `(= % (* 2 x))` I've constrained the passed function `f` to be (effectively) a doubling function.  I've done this by constraining the result of `constrained-fn` relative to its input.  There is nothing entirely ground-breaking about pre and post conditions, but they are surprisingly powerful.  By stripping out the assertions into a wrapper function I've detached some hairy logic from a potentially globally useful function and isolated it in an *aspect* (if you will) named `constrained-fn`.  Now we could wrap the function `f` using a different constraining function depending on the context in which it is intended to be used; for example `jimmys-fn`, `logged-result-fn`, `only-ints-fn`, `payroll-dept-fn`, etc... In fact, I like that word *aspect*; it has a nice ring to it.  I wonder if [anyone](http://people.cs.ubc.ca/~gregor) has [thought](http://en.wikipedia.org/wiki/Aspect-oriented_programming) of that before?

-m
