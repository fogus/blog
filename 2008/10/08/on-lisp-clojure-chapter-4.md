; As always, I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).  Also, this post is executable, just copy and paste into a Clojure REPL.

> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/

; pg. 42

;; PG defines a general find function `(find2)` that given a function and a list, returns both the first matched value and the value returned from the matching function.  The most direct translation to Clojure is:

;<pre lang="lisp">
(defn findr [f sq]
  (when (seq sq)
    (let [value (f (first sq))]
      (if (nil? value)
        (recur f (rest sq))
        [(first sq) value]))))

(defn evenr [elem]
  (if (even? elem)
    "is even"))

(findr evenr '(1 13 3 4))
(findr evenr '(1 13 3 5)) 
(findr evenr nil) 
;</pre>


; pg. 45

;; L@@k: Why rseq not lazy.

;; Clojure really shines with a simple problem like this because it will work on lists, vectors, sorted maps, and strings.

;<pre lang="lisp">
(defn last1 [sq]
  (last s))
;</pre>

;; Test for a sequence of one element
;<pre lang="lisp">
(defn single [sq]
  (and (seq? sq) (not (rest sq))))
;</pre>

;; `(append1)` seems too complicated.  It needs some work, but for now it works.

;<pre lang="lisp">
(defn append1 [sq elem]
  (if (seq elem)
    (lazy-cons sq elem)
    (concat sq (vector elem))))
;</pre>

;; It's generally frowned on to produce side-effects in Clojure along the lines of what PG's `(conc1)` function does, but if you were so inclined it would be done as:

;<pre lang="lisp">
(defn conc1 [sq elem]
  (dosync
   (if (seq? elem)
     (ref-set sq (lazy-cons @sq elem))
     (ref-set sq (concat @sq (vector elem))))))

(def x (ref '(4 5 6)))
@x
(conc1 x 4)
@x
;</pre>

;<pre lang="lisp">
(defn makelist [elem]
  (lazy-cons elem '()))
;</pre>

; pg. 47

;<pre lang="lisp">
(defn longer [x y]
  (let [cmp (fn [x y]
              (and (seq? x)
                   (or (nil? y)
                       (recur (rest x) (rest y)))))]
    (if (and (seq? x) (seq? y))
      (cmp x y)
      (> (count x) (count y)))))
;</pre>

;; The [list-comprehension](http://clojure.org/api#toc227) macro supplied by Clojure provides a powerful way to filter a sequence based on a `:when` or `:while`.  Therefore an implementation of the filter function is trivial.

;<pre lang="lisp">
(defn filtr [f lst]
  (for [x lst :when (f x)]
    x))

(filtr even? '(1 2 3 4))
(filtr even? (range 100000)) 
(filtr seq? '(1 2 3 (4 5) 6 [7] 8 (9) "10"))
;</pre>


;; `(group)` could stand to be (a lot) more elegant

;<pre lang="lisp">
(defn group [source n]
  (if (zero? n) (pr "error: zero length"))
  
  (let [t (int (/ (count source) n))
        remainder (drop (* n t) source)]
    (lazy-cat 
     (for [i (range t)]
       (take n (nthrest source (* i n))))
     (if (nil? remainder)
       '()
       (list remainder)))))

(group '(1 2 3 4 5 6 7) 4)
(group '(1 2 3 4 5 6 7) 400)
;</pre>

; pg. 49

;<pre lang="lisp">
(defn flatten [x]
  (if (seq? x)
    (apply concat (map flatten x))
    (list x)))
;</pre>


;; Clojure provides a powerful library for walking and editing trees functionally using a [structure called a zipper](http://www.st.cs.uni-sb.de/edu/seminare/2005/advanced-fp/docs/huet-zipper.pdf).  The library is fairly comprehensive, and its power is apparent in a few minutes playtime.  There are a couple of missing features (outlined below), but overall it makes something like PG's prune function a piece of cake.  

;; One feature missing from the zip library is the ability to create a zip structure from any given type of seq-able data structure.  That is, before you build a zipper you have to know the form of the data so that you can call one of `(seq-zip)`, `(vector-zip)`, or `(xml-zip)`.  This is a minor point overall, but making the prune more generic requires some work up front.

;<pre lang="lisp">
(defn zip-util [root]
  (if (seq? root)
    (zip/seq-zip root)
    (zip/vector-zip root)))
;</pre>

;; The prune function itself is taken alomst verbatim from the [clojure zip examples](http://clojure.org/other_libraries) except for a couple minor changes, one of which is to allow a filter function to decide the pruning as in "On Lisp" plus a call to `(zip-util)` to handle different types of structures.  Another useful feature missing from the zip library is a predicate that can be used to check if a structure is already a zipper.  As it stands trying to zip a zipper throws an exception and there was no clear way (that I could find) to perform such a check.

;<pre lang="lisp">
(defn prune [f tree]
  (loop [loc (zip-util tree)]
    (if (zip/end? loc)
      (zip/root loc)
      (recur
       (zip/next
        (if (f (zip/node loc))
          (zip/remove loc)
          loc))))))
;</pre>

;; PG's `(prune)` function works on nodes only, but I thought it might be better to work on branches also.  Of course, this breaks the ability to just send in something like `(even?)`, but I think that is a minor tradeoff.

;<pre lang="lisp">
(defn node-filter [x]
  (if (zip/branch? x)
    nil
    (even? x)))

(def s '(1 2 (3 (4 5) 6) 7 8 (9)))
(def v [1 2 [3 [4 5] 6] 7 8 [9]])

(prune node-filter s)
(prune node-filter v)
;</pre>

; pg. 50

;; PG's `(before)` is alomst a direct translation, except for one major difference: Clojure does not have default argument values.  Therefore, one must always pass in a test function to get the same effect.  I decided to drop the test function *for now* to simplify the function.

;<pre lang="lisp">
(defn before? [x y sq]
  (and sq
       (let [elem (first sq)]
         (if (= y elem)
           nil
           (if (= x elem) 
             sq
             (recur x y (rest sq)))))))

(before? 'b 'c '(1 2 a b c))
(before? 1 'c '(1 2 a b c)) 
(before? 'a 1 '(1 2 a b c)) 


;; This is from CS-101
(defn member [x sq]
  (if (seq sq)
    (if (= x (first sq))
      sq
      (recur x (rest sq)))))

(defn after? [x y sq]
  (let [elem (before? y x sq)]
    (and elem
         (member x elem))))

(after? 'b 'c '(1 2 a b c))
(after? 'c 'a '(1 2 a b c))


(defn duplicate? [obj sq]
  (member obj (rest (member obj sq))))

(duplicate? 'a '(1 2 a b c a d))

;; Needs work
(defn split-if [f sq]
  [(for [x sq :when (f x)] x) (for [x sq :when (not (f x))] x)])

(split-if #(= % 5) '(1 2 3 4 5 6 7 8 9 10))
(split-if #(< % 5) '(1 2 3 4 5 6 7 8 9 10))
(split-if #(> % 500) '(1 2 3 4 5 6 7 8 9 10))
(split-if #(if (< % 7) true false) '(1 2 3 4 5 6 7 8 9 10))
;</pre>

;; Something to note: Clojure provides shorhand notation for lambda's containing a single call of #().  This notation allows for the passing of multiple arguments each accessed via the % operator (e.g. %1 %2 etc...)


; pg. 52

;; Continuing with my abuse of the list-comprehension (only until it becomes second nature... any day now), the most function is pretty trivial.

;<pre lang="lisp">
(defn most [f sq]
    (let [wins (ref (first sq))
          mx (ref (f @wins))]
      (doall
       (for [x (rest sq) :when (> (f x) @mx)]
         [(dosync (ref-set wins x) (ref-set mx (f x)))]))
      (list @wins @mx)))

(most count '((1 2 3) (1 2))) 
(most count '((1 2 3) (1 2 3 4)))
;</pre>

;; It's important to note that the `(for)` had to be wrapped in a `(doall)` because the former generates a [lazy sequence](http://clojure.org/sequences#toc43) and will only supply the first argument, therefore you're always going to see the first item in the sequence as the answer without the latter.  It took me a while to track this down.  :(

;<pre lang="lisp">
(defn best [f sq]
  (let [wins (ref (first sq))]
    (last
      (for [x (rest sq) :when (f x @wins)]
        (dosync (ref-set wins x))))))

(best > '(1 2 3 4 5)) 
(best > '(1 2 7 4 5))
(best > nil) 
;</pre>

;; But of course, when Rich Hickey looked at my `(best)` function he gave me a virtual pat on the head and hit me with:

;<pre lang="lisp">
(defn best [f xs