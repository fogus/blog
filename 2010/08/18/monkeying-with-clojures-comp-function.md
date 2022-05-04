A slight modification to Clojure's `comp` function gives me more power:

<pre class="prettyprint lang-clj">
(defn comp+
  ([] identity)
  ([f] f)
  ([f g] 
     (fn 
       ([] (f (g)))
       ([x] (f (g x)))
       ([x y] (f (g x y)))
       ([x y z] (f (g x y z)))
       ([x y z & args] (f (apply g x y z args)))))
  ([f g h] 
     (fn 
       ([] (f (g (h))))
       ([x] (f (g (h x))))
       ([x y] (f (g (h x y))))
       ([x y z] (f (g (h x y z))))
       ([x y z & args] (f (g (apply h x y z args))))))
  ([f1 f2 f3 & fs]
    (let [fs (reverse (list* f1 f2 f3 fs))]
      (fn [& args]
        (loop [ret (apply (first fs) args) fs (next fs)]
          (if fs
            (recur ((first fs) ret) (next fs))
            ret))))))

;; allowing
(require ‘clojure.string)

(for [f (map #(apply comp+ %)
             [[keyword name]
              []
              [name clojure.string/upper-case]])
      e '[foo bar]]
  (f e))

;=> (:foo :bar foo bar "FOO" "BAR")
</pre>

I added a function body to `comp+` handling the case where it's given no function,  returning the `identity` function.  This let's me use `comp+` in more interesting ways without requiring that I handle the case of no arguments explicitly and without exploding when I don't.

Just one of those little things I guess.

:f
