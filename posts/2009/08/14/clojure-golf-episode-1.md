This is an attempt to have some fun with [Clojure](http://clojure.org).  I hope to engage the Clojure community, but it would be all the more fun if others played along with their chosen language(s).  The idea is that I present a sub-optimal code snippet and people throw in their optimized, smallest, and/or weirdest submissions of that same function.  

Episode 1 is the function `filter-collecting` taken directly from [Greg's Little Object System](http://people.csail.mit.edu/gregs/ref-dyn-patterns.html) -- the code is thus:

<pre class="prettyprint lang-clj">
(defn filter-collecting [predicate collector & lists] 
  (lazy-seq 
    (loop [lists lists out []] 
      (if (empty? (first lists)) 
        (reverse out) 
        (let [heads (map first lists)] 
          (if (apply predicate heads) 
            (recur (map rest lists) (cons (apply collector heads) out)) 
            (recur (map rest lists) out)))))))

(filter-collecting (fn [x y] (< x y)) (fn [x y] (+ x y)) ‘(1 7 3 9) ‘(5 5 5 5)) 
;;=> (6 8)
</pre>

In a nutshell, it takes *n* number of `lists` and applies a `predicate` to subsequent elements.  If said predicate passes, then it applies the `collector` function (arity *n*) and returns that result in a collection.  As a starting point, it should be noted that this is a direct translation from Scheme and should be more Clojure-esque

Have fun.

Follow-up
---------
There were some nice follow-ups on the [Clojure group](http://groups.google.com/group/clojure/browse_frm/thread/b08ec71d4bd355c8/eb150ea310eceabb?lnk=gst&q=golf#) (code changes only done for formatting purposes):

#### Jarkko Oranen
<pre lang="lisp">
(defn filter-collecting [c f & seqs] 
  (remove nil? 
          (apply map (fn [& args] 
                       (when (apply c args) 
                         (apply f args))) 
                 seqs))) 
</pre>

#### Christophe Grand 
*my personal favorite*
<pre lang="lisp">
(defn filter-collecting [predicate collector & lists] 
  (for [v (apply map vector lists) 
          :when (apply predicate v)] 
    (apply collector v))) 
</pre>

#### Chris Houser
<pre lang="lisp">
(defn filter-collecting [p c & l] 
  (map #(apply c %) 
       (filter #(apply p %) 
               (apply map vector l)))) 
</pre>

#### Laurent Petit
<pre lang="lisp">
(let [x (Object.)] 
   (defn filter-collecting [p c & l] 
      (remove #(identical? % x) 
        (apply map #(if (apply p %&) (apply c %&) x) l)))) 

;; AND 

(defn filter-collecting [p c & l] 
  (apply mapcat 
         #(when (apply p %&) [(apply c %&)]) l))  
</pre>

#### Jonathan Smith
<pre lang="lisp">
(defn filter-collecting [p c & seqs] 
  (let [fun #(if (apply p %1) 
               (conj! %2 (apply c %1)) 
               %2)] 
    (loop [hs (map first seqs) 
           ts (map rest seqs) 
           result (transient [])] 
      (if (next (first ts)) 
        (recur (map first ts) 
               (map rest ts) 
               (fun hs result)) 
        (persistent! (fun hs result))))))
</pre>

Thanks to everyone who participated.

-m
