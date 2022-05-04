; Chapter 5, entitled "Returning Functions", is where we really start to see the power of functional programming.  It is the types of problems outlined in the chapter where [Clojure](http://clojure.org) really shines.  In fact, many of the functions created by Paul Graham in *On Lisp* are built into Clojure, as I will show below.

; As always, I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).  Also, this post is executable, just copy and paste into a Clojure REPL.

> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/

; pg. 62

; Clojure does not have a typecase function, but one could be made by writing a macro that expands into something like (I stress 'like' as this is not exactly correct):

;<pre lang="lisp">
(defn joiner [obj]
  (let [name (. (. (. obj getClass) getGenericSuperclass) getName)]
    (cond
     (= name "clojure.lang.ASeq") cons
     (= name "java.lang.Number") +)))
;</pre>


; Clojure of courser has a multi-method facility that would provide something similar:

;<pre lang="lisp">
(defmulti joiner class)
(defmethod joiner :default [obj] cons)
(defmethod joiner java.lang.Number [obj] +)

(joiner 2)
(joiner '(2 3 4))
(joiner 3.14159)
(joiner [1 2 3])
(joiner "test")
;</pre>

; Of course it would be nice if we could use type hints for dispatch and simplify the API.  I will not hold my breath for this (especially since it breaks Clojure's current intent for type hints... and my comment on it was ignored :p).

;<pre lang="lisp">
(defn foo
  ([#^java.lang.Number x] +)
  ([x] cons)) 
;</pre>


; pg. 63

; One of PG's famous "inventions" is the make-adder function.  He [originally presented it](http://www.paulgraham.com/icad.html) as a test for programming language X which is used to determine how such a simple function cannot be easily defined in many (at the time) popular languages.  Of course, since Clojure has closures like Lisp, it's a no-brainer.

;<pre lang="lisp">
(defn make-adder [n]
  (fn [x] (+ x n))) 

(def add3 (make-adder 3))
(add3 10)
;</pre>


; Remember that `(remove-if)` function from [chapter 2](http://blog.fogus.me/2008/10/02/on-lisp-clojure-chapter-2-redux/)?  We can use Clojure's `(complement)` function to build an inverse function from an existing predicate.

; <pre lang="lisp">                                                                                                                                                
(defn remove-if [f lst]
  (if (seq lst) ; idiomatic                                                                                                                                        
    (if (f (first lst))
      (recur f (rest lst))
      (lazy-cons (first lst) (remove-if f (rest lst))))
    nil))

(remove-if (complement odd?) '(1 2 3 4 5 6))
; </pre>  


; pg. 65

; Memoization has gotten a lot of airtime recently as it was a fun game to show how it could be done in language x -- that meme seems to have died away.  In short, memoization is the act of defining a function that caches past results of calls to it and returns the cached version if it exists.  This is useful when it does a computationally intense operation and it expects to be called often with similar values.  I was planning on writing a memoization function, but found that the [Clojure Contribs](http://sourceforge.net/projects/clojure-contrib) library [already has one](http://clojure-contrib.svn.sourceforge.net/viewvc/clojure-contrib/trunk/src/clojure/contrib/memoize/memoize.clj?revision=157).

;<pre lang="lisp">
(defn memoize
  [function]
  (let [cache (ref {})]
    (fn [& args]
      (or (@cache args)
          (let [result (apply function args)]
            (dosync
             (commute cache assoc args result))
            result)))))

(def slowly (memoize (fn [x] (. Thread sleep 100) x)))
(time (slowly 1)) 
(time (slowly 1)) 

(def mri (memoize (remove-if f lst)))
(time (mri odd? (range 1000)))
(time (mri odd? (range 1000)))

;; This may show an more dramatic speed-up
(time (doall (mri odd? (range 11000))))
(time (doall (mri odd? (range 11000))))
;</pre>





; pg. 66

; PG talks next about function composition, that is, defining a function that takes functions f and g (any number but 2 for illustration) functions and returns a function that is f(g(&args)).  Of course, Clojure provides this in the form of the `(comp)` function:

;<pre lang="lisp">
((comp first list) 'a 'b 'c)
((comp first rest list) 'a 'b 'c)
;</pre>

; I skip over the tree functions (pgs. 70-75) built by PG because for the most part they are either included or trivial using Clojure's [Zipper](http://clojure.org/api#toc569) API.  Maybe I will come back one day and do this should I need them in later chapters.  But for now, good day.

; -m
