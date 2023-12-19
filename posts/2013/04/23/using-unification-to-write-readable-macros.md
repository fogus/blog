In my [core.contracts](https://github.com/clojure/core.contracts) code I've experimented with using unification to aide read- and re-readability in my macros.  Often I've found that I'll hit a wall when returning to a macro that I wrote long ago.  A mass of documentation often helps, but I wanted something more.  I think I've found it... or at least the beginnings of 'it'.[^it]  Observe the following macro:[^joc]

    (defn- build-contract-body
      [[args cnstr descr :as V]]
      (unify/subst     
       '(?PARMS
         (let [ret ?PRE-CHECK]
           ?POST-CHECK))
    
       {'?ARGS       args
        '?F          'f
        '?PARMS      (vec (list* 'f args))
        '?MSG        descr
        '?PRE-CHECK  (build-condition-body 
                       {:pre (:pre cnstr)}   
                       '(apply ?F ?ARGS) 
                       "Pre-condition failure: ")
        '?POST-CHECK (build-condition-body 
                       {:post (:post cnstr)} 
                       'ret 
                       "Post-condition failure: ")}))

This macro builds a data-structure that corresponds to a function body useful for tracking pre- and post-condition constraint failures.  You'll see that the meat of the macro is simply:

    '(?PARMS
       (let [ret ?PRE-CHECK]
         ?POST-CHECK))

My approach uses unification (`subst` from the [core.unify](https://github.com/clojure/core.unify) library) to fill in the body variables `?PARMS`, `?PRE-CHECK` and `?POST-CHECK` with further data structures.  Specifically, the structures to fill are provided in a bindings map to `subst` and built directly or via another macro shown below:

    (defn- build-condition-body
      [constraint-map body prefix-msg]
      (unify/subst
       '(try
          ((fn []
             ?CNSTR
             ?BODY))
          (catch AssertionError ae
            (throw (AssertionError. (str ?PREFIX ?MSG \newline (.getMessage ae))))))
    
       {'?CNSTR  constraint-map
        '?PREFIX prefix-msg
        '?BODY   body}))

Using this method allows me to effectively draw a picture of the data[^defsyntax] structure representing a function body and fill in the required values via substitution.  I need to explore this deeper, so buyer beware, but I like the initial findings.

:F

*originally posted on [my coderwall profile](https://coderwall.com/fogus)*

[^defsyntax]: It's a poor man's RHS of a `define-syntax` (maybe) so maybe someone should just create a `define-syntax` library.  (cough cough)

[^joc]: I was considering expanding on this theme in the [2nd edition of the Joy of Clojure](http://www.joyofclojure.com/buy), but decided against it.

[^it]: I hesitate to say that 'it' is the ever-elusive [self-documenting code](http://blog.fogus.me/2012/07/06/this-plt-life-self-documenting-code/).