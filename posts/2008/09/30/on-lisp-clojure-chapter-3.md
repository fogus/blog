; As always, I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).

> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/

; Chapter 3 was a relatively short chapter (code-wise).  It dealt mostly with side-effects and the virtues of a more functional style to avoid such evil.  Thankfully, Clojure eliminates many of the problems outlined by Graham in the way that it prefers immutability and minimizes the functions causing side-effects.

; pg. 29

;  Graham provides an intentionally bad implementation of a reverse function that modifies a list in place.  To do this in Clojure would require some gymnastics and therefore is probably not worth the effort.

; pg. 30

;<pre lang="lisp">

(defn good-reverse [lst]
  (defn rev [lst acc]
    (if (nil? lst)
      acc
      (rev (rest lst) (cons (first lst) acc))))
  (rev lst nil))

;</pre>

; pg. 32

;  Clojure doesn't provide multiple value bindings in the same way that Lisp does.  Instead, you can construct a vector of values and then deconstruct them easily on the return within a let)

;<pre lang="lisp">

(defn mytrunc [num]
  [(int num) (- num (int num))])

(let [[int_part frac_part] (mytrunc 26.21875)]
  (str int_part " and " frac_part))

;</pre>

; -m
