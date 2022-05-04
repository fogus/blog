> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/

; It seems that my first two chapters were well received, and garnered some positive comments and constructive criticisms (except for the commenter who talked about viagra and offshore betting).  The main point made by readers was that my code was not Clojure-esque enough.  Therefore, I went back to the [online documentation](http://clojure.org/) and rethought my approach.  Therefore, I present some refactored functions:

; pg. 15

;; It turns out that my original implementation of remove-if would trash the stack with very large lists.  This can be verified by (on my setup at least) with `(remove-if even? (range 1000000))`.  After digesting some tips from [Chouser](http://chouser.n01se.net/), I chose to rewrite the function using [recur](http://clojure.org/special_forms#toc10) and [lazy-cons](http://clojure.org/api#toc277).  However, I have to admit that the documentation for both was ... spartan.  

; <pre lang="lisp">
(defn remove-if [f lst]
  (if (seq lst) ; idiomatic
    (if (f (first lst))
      (recur f (rest lst))
      (lazy-cons (first lst) (remove-if f (rest lst))))
    nil))

(remove-if even? (range 1000000)) ; go get some coffee
; </pre>

; pg. 24

;; My initial version of triangle worked fine (with a 'little' caveat mentioned later).  However, it was pointed out to me that performing a `defn` inside of a defn actually binds the inner name in the global namespace.  Again, using the guidance from [Chouser](http://chouser.n01se.net/) I was able to fix this particular problem.  Additionally, the original code blew the stack at `(triangle 300000)` so I once again used recur to avoid that situation.

; <pre lang="lisp">
(defn triangle [n]
  (let [tri 
        (fn [c n]
          (if (zero? n)
            c
            (recur (+ n c) (- n 1))))]
        (tri 0 n)))

(triangle 300000)
(triangle 3000000)
(triangle 30000000)
; </pre>


; pg. 19

;; The third lesson that I learned since implementing the initial chapter 2 functions was the [ref](http://clojure.org/refs) element.  While my original java.Array hack worked, it didn't really teach me anything about writing Clojure code.  Therefore, (thanks again to Chouser) I rewrote the `make-adderb` using a ref and as a result, my new function is now thread safe ;).  As a side note, the documentation for refs is among the most comprehensive.  Recommended reading.

; <pre lang="lisp">
(let [n (ref 0)]
  (defn make-adderb [m]
    (dosync (ref-set n m))
    (fn [x & change]
      (dosync
       (if change
         (ref-set n x)
         (+ (ensure n) x))))))

(def addx (make-adderb 1))
(addx 3)

(addx 100 true)
(addx 3)
; </pre>

;; I only fixed three of the functions in order to get a feel for the newly learned methodologies and constructs.  I leave it as an exercise to the reader to fix the rest.  :p

As always, I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).