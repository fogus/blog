It can be said that one of the strengths of Clojure is that it is comprised of many little mini-languages, each fulfilling a particular sweet spot.  Based on a [conversation on #clojure](http://clojure-log.n01se.net/date/2010-03-23.html#09:27), I present here a list of Clojure's internal mini-languages, with some examples of each:

Destructuring
-----------
One of the most powerful features that Clojure provides is that of destructuring.  Simply put, destructuring refers to the way that collections can be pulled apart into local bindings.

    (defn destr-seq [[a b & more]] (str "Got " a ", " b ", and " more))
    (destr-seq [1 2])
    ;=> "Got 1, 2, and "
    
    (destr-seq [1 2 3 4])
    ;=> "Got 1, 2, and (3 4)"
    
    ;; associative desctructuring
    (let [{first-thing 0 last-thing 3} '[a b c d]] 
      [first-thing last-thing])  
    ;=> [a d]
    
    ;; map destructuring
    (let [{p1 :player1 p2 :player2} {:player1 "Mike", :player2 "Chris"}]
      [p1 :vs p2])
    ;=> ["Mike" :vs "Chris"]

And there are many other ways to destructure in Clojure (including a [facility for named-args](http://twitter.com/fogus/status/10925604342) coming soon), but I think there is little doubt that what I've shown counts as a mini-language.

List Comprehensions
-------------------

Clojure list-comprehensions are performed using the `for` macro:

    (for [x (range 3), y (range 3), z (range 3) 
          :when (or (< x y z) (> x y z))] 
      [x y z])
    ;=> ([0 1 2] [2 1 0])

    (for [x (range 3), y (range 3), z (range 3) 
          :while (or (< x y z) (> x y z))] 
      [x y z])
    ;=> ([2 1 0])

The for-comprehension also allows a binding form using the `:let` directive.  

Regular Expressions
--------------------

Regular expressions are far from mini, but in the greater context of Clojure I'll just name it as a mini-language:

    (re-seq #"\w*(\w)" "one-two/three")
    ;=> (["one" "e"] ["two" "o"] ["three" "e"])
    
    (re-find #"(?i)ninjas" "NiNJaS")
    ;=> "NiNJaS"

There is not much else to say about regular expressions.

#()
---

The short-hand function form is a particularly small mini-language, but likely fits the bill:

    (def four-things #(list % %2 %3 %4))
    (four-things 1 2 3 4)
    ;=> (1 2 3 4)
   
    (#(apply %1 %&) str "Hello " "Cleveland") 
    ;=> "Hello Cleveland"
    
    
Syntax-quote
------------

Syntax quote is roughly analogous to the Lisp backquote; however, the former does some nice things to avoid name capturing.  I will not go into that particular aspect, but you may be able to garner how with some examples:

    `(+ 10 (* 3 2))
    ;=> (clojure.core/+ 10 (clojure.core/* 3 2))
    
    `(+ 10 ~(* 3 2))
    ;=> (clojure.core/+ 10 6)
    
    (let [x '(+ 2 3)] `(1 ~@x))
    ;=> (1 + 2 3)
    
    `x#
    ;=> x__623__auto__
    
    +
    ;=> #<core$_PLUS___3519 clojure.core$_PLUS___3519@6179d854>
    
    `~'+
    ;=> +
    
    `(~'+ 1 2 3)
    ;=> (+ 1 2 3)
    
    (eval `(~'+ 1 2 3))
    ;=> 6

The above examples do no justice to the power of syntax-quote.  Of course, any time you talk about it a discussion of macros soon follows, which provide a way for an infinite number of mini-languages to be created.

pre- and post-conditions
------------------------

I [talked about function constraints previously](/2009/12/21/clojures-pre-and-post/), so I will not cover that ground again.  However, in summary they provide the building blocks for [contract programming](http://en.wikipedia.org/wiki/Design_by_contract).

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

Sean Devlin also created a [screencast taking pre- and post-conditions](http://vimeo.com/8399758) one step further.

-> and ->>
----------

I've also talked about [Clojure's pipeline macro](http://blog.fogus.me/2009/09/04/understanding-the-clojure-macro/) `->`, but did not show the ability to nest:

    (-> 25 Math/sqrt (->> (- 3) str))
    ;=> "-2"

Literal Numerics
-----------------

These are all the same number:

    [127 0x7F 0177 16r7F 2r01111111]
    ;=> [127 127 127 127 127]

ns
---

Namespace declarations are very declarative in nature:

    (ns joy.ns-ex                                    
      (:refer-clojure :exclude [defstruct])
      (:use (clojure set xml))
      (:use [clojure.test :only (are is)])
      (:require (clojure [zip :as z]))
      (:import (java.util Date)
               (java.io File)))

gen-class
--------

The `gen-class` feature is likewise declarative in nature, both in their stand-alone form, and their `ns`-embedded (which would make them an internal-internal-mini-language) form:

    (ns clojure.examples.instance
      (:gen-class
       :implements [java.util.Iterator]
       :init init
       :constructors {[String] []}
       :state state))

A much better description of gen-class can be found on [Meikel Brandmeyer](http://kotka.de/blog/2010/02/gen-class_how_it_works_and_how_to_use_it.html)'s blog.

Macros
------

Many of these so-called mini-languages are possible thanks to Clojure's macros.  While not really a mini-language, macros are the building blocks of your own.

What mini-languages does your programming language have?

-m
