Inspired by [Stuart Holloway](http://blog.thinkrelevance.com/2008/09/16/pcl-clojure)'s excellent series porting *[Practical Common Lisp][pcl]* to Clojure and [Ola Bini](http://olabini.com/blog/2008/09/paradigms-of-artificial-intelligence-programming-in-ruby/)'s bad-ass port of *[Paradigm's of Artificial Intelligence][paip]* to Ruby.  I have started my own quest to port [Paul Graham](http://www.paulgraham.com)'s [On Lisp](http://www.paulgraham.com/onlisp.html) to Clojure.  

> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/

I have made it through chapter 2 and am ready to start the coding for chapter 3.  I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).

[pcl]: http://www.amazon.com/exec/obidos/ASIN/1590592395/ref=nosim/fogus-20
[paip]: http://www.amazon.com/exec/obidos/ASIN/1558601910/ref=nosim/fogus-20

<pre lang="lisp">
; pg. 10
(defn dbl [x]
  (* x 2))

(dbl 39)
(dbl 17872687642786723868743216782)

; pg. 11
(= dbl (first (list dbl)))

; pg. 12
(fn [x] (* x 2))

((fn [x] (* x 2)) 20)

; pg. 13
(def dbl (fn [x] (* x 2)))

; pg. 14
(map (fn [x] (+ x 10)) '(1 2 3))

(map + '(1 2 3) '(10 100 1000))

;; Clojure uses the Java's Comparator on the sort function
;; so there is no need to supply the < function.  In fact,
;; if you did, an exception would be thrown. (l@@k why?)
(sort '(1 4 2 5 6 7 3))

; pg. 15
;; Because Clojure is a Lisp-1, funcall is not needed.  Instead,
;; we just call directly as (f)
(defn remove-if [f lst]
  (if (nil? lst)
    nil
    (if (f (first lst))
      (remove-if f (rest lst))
      (cons (first lst) (remove-if f (rest lst))))))

(remove-if even? '(1 2 3 4 5))
(remove-if nil? '(1 2 nil 3 nil))
(remove-if (fn [x] 
             (if (> x 5)
               nil
               x)) 
           '(3 4 5 6 7))


; pg. 16
(let [y 7]
  (defn scope-test [x]
    (list x y)))

(let [y 5] (scope-test 3)) ;; no shocker here

; pg. 18
(defn list+ [lst n]
  (map (fn [x] (+ x n)) lst))

(list+ '(1 2 3) 10)

;;
;; This is surprisingly difficult given that Clojure does not allow the 
;; modification of local variables defined in a let.  Only vars (globals)
;; and class fields can be modified with the (set!) function.  
;; Therefore, I had to hack it so that the closed counter is an array
;; of one integer element.  Therefore, it is the array that is modified
;; and not the count.  The point being, if you want to create closures
;; for functions modifying local state, then you have to use a mutable object
;; to do so
;;
(let [counter (to-array [0])]
  (defn new-id [] (aset counter 0 (inc (aget counter 0))))
  (defn reset-id [] (aset counter 0 0)))


;; On the other hand, this is extremely easy
(defn make-adder [n]
  (fn [x] (+ x n)))

(def add10 (make-adder 10))
(def add42 (make-adder 42))

(add10 1)
(add42 100)
(add42 (add10 1))

; pg. 19
;; Again we run into the immutable local binding feature.  Since I already 
;; solved this problem before, getting it to work for this case was 
;; a piece of cake.
(let [n (to-array [0])]
  (defn make-adderb [m]
    (aset n 0 m)
    (fn [x & change]
      (if change
        (aset n 0 x)
        (+ (aget n 0) x)))))

(def addx (make-adderb 1))
(addx 3)

(addx 100 true)
(addx 3)


; pg. 22
;; Clojure handles this very nicely.  
(defn count-instances [obj lsts]
  (defn instances-in [lst]
    (if (list? lst)
      (+ 
       (if (= (first lst) obj) 1 0)
       (instances-in (rest lst)))
      0))
  (map instances-in lsts))

(count-instances 'a '((a b c) (d a r p a) (d a r) (a a)))


;; Tail-recursive find-if 
;; returns the first instance satisfying (f)
(defn our-find-if [f lst]
  (if (f (first lst))
    (first lst)
    (our-find-if f (rest lst))))

(our-find-if even? '(1 3 5 7 9 11 14))


; pg. 23
;; Tail-recursive our-length
(defn our-length [lst]
  (defn rec [lst acc]
    (if (nil? lst)
      acc
      (rec (rest lst) (inc acc))))
  (rec lst 0))

(our-length (range 100))
(our-length (range 0 100 2))


; pg. 24
(defn triangle [n]
  (defn tri [c n]
    (if (zero? n)
      c
      (tri (+ n c)
           (- n 1))))
  (tri 0 n))

(triangle 10)
(triangle 30000)  ;; one more zero blew my stack
</pre>

-m
