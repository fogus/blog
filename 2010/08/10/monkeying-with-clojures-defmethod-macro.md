I've at times[^cemerick] found the need to use the precise match form of Clojure's multimethod dispatch value.  For example:

    (defmulti repeating-mm (juxt first second))
    (defmethod repeating-mm [1 2] [_] (str [1 2]))
    
    (repeating-mm (range 1 10))
    ;=> "[1 2]"

`repeating-mm` works as I would expect, but I never liked the need to repeat the `[1 2]` dispatch value.  Instead, I've often wished for a way to create a binding of the dispatch value itself -- so I played around with doing just that.  My first instinct was to create an anaphora for the dispatch value, bound to `$`, ala:

    (defmacro defmethod-anaphoric
      [multifn dispatch-val & fn-tail]
      `(. ~(with-meta multifn {:tag 'clojure.lang.MultiFn}) 
          addMethod 
          ~dispatch-val
          (let [~'$ ~dispatch-val]
            (fn ~@fn-tail))))
    
    (defmulti a-mm (juxt first second))
    (defmethod-anaphoric a-mm [1 2] [_] (str $))
    (defmethod-anaphoric a-mm [3 4] [s] (reverse s))
    
    (a-mm [1 2])
    ;=> "[1 2]"
    
    (a-mm [3 4])
    ;=> (4 3)

And this works fine.  However, using an anaphoric binding is poor form and not idiomatic Clojure code (in general).  Therefore, it would be better to provide some sort of named binding for the dispatch value, ala:

    (defmacro defmethod-explicit
      [multifn dispatch-val & fn-tail]
      (let [[kw n & body] fn-tail]
        (if (= :as kw)
          `(. ~(with-meta multifn {:tag 'clojure.lang.MultiFn}) 
              addMethod 
              ~dispatch-val 
              (let [~n ~dispatch-val] (fn ~@body)))
          `(. ~(with-meta multifn {:tag 'clojure.lang.MultiFn}) 
              addMethod 
              ~dispatch-val 
              (fn ~@fn-tail)))))

    (defmulti expl-mm (juxt first second))
    (defmethod-explicit expl-mm [1 2] :as dv [_] (str dv))
    (defmethod-explicit expl-mm [3 4] [s] (reverse s))
    
    (expl-mm [1 2])
    ;=> "[1 2]"
    
    (expl-mm [3 4])
    ;=> (4 3)

I made the implementation of `defmethod-explicit`, uhhhh explicit, to highlight the differences between the code path with the explicit binding of the dispatch value and without.  In the case with a binding (i.e. `:as dv`), the multimethod is built using a closure over the name `dv`.  In the case without, there is only a function.

I have a bunch of little language hacks like this laying around and whenever I create one it never ceases to astonish how maleable Clojure (and Lisp in general) can be.

:f

[^cemerick]: This post inspired by [Chas Emerick's post on defrecord default slot values](http://cemerick.com/2010/08/02/defrecord-slot-defaults/).
 