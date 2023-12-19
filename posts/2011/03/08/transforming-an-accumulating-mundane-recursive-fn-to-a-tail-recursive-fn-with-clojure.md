*this example is in Clojure, but the general principle stands regardless of language*

Transforming an accumulating mundane recursive function to a tail-recursive function using a helper function.

<code><pre>
(defn mundane-pack
  "Mundane recursive way to pack a sequence"
  [[f & r :as S]]
  (if (seq S)
    (let [[packed tail] (split-with {f true} S)]
      (if (seq tail)
        (cons packed (mundane-pack tail))
        [packed]))
    [nil]))

(defn pack
  "Tail-recursive way to pack a sequence"
  [coll]
  (letfn [(pack* [[f & r :as S] acc]
            (when (seq S)
              (let [[packed tail] (split-with {f true} S)]
                (if (seq tail)
                  (recur tail (conj acc packed))
                  (conj acc packed)))))]
    (pack* coll [])))

(defn rle 
  "Run-length encode a sequence"
  [coll]
  (map #(vector (count %) (first %))
       (pack coll)))

(def S '[a a a a b c c a a d e e e e])

(pack S)
;=> [(a a a a) (b) (c c) (a a) (d) (e e e e)]

(rle S)
;=> ([4 a] [1 b] [2 c] [2 a] [1 d] [4 e])
</pre></code>

See *[Common Lisp: A Gentle Introduction to Symbolic Computation](http://www.cs.cmu.edu/~dst/LispBook/)* for more information about this technique.

:f
