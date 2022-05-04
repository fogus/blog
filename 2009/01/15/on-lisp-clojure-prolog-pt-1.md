; As always, I will post when the code is "complete", but my progress can be followed on [Github](http://github.com/fogus/polyglot/tree/master/reading/onlisp).  Also, this post is executable, just copy and paste into a Clojure REPL.

(comment "
> Posts in this series: [ch. 2][ch2], [ch. 2 redux][ch2r], [ch. 3][ch3], [ch. 4][ch4], [ch. 5][ch5], [ProloG pt. 1][prolog]

[ch2]: /2008/09/26/on-lisp-clojure-chapter-2/
[ch2r]: /2008/10/02/on-lisp-clojure-chapter-2-redux/
[ch3]: /2008/09/30/on-lisp-clojure-chapter-3/
[ch4]: /2008/10/08/on-lisp-clojure-chapter-4/
[ch5]: /2008/10/24/on-lisp-clojure-chapter-5/
[prolog]: /2009/01/14/on-lisp-clojure-prologon-lisp-clojure-prolog/

First, let me thank [Stuart Halloway][stu] for [picking up the On Lisp -> Clojure port where I left off][stuol].  I do not know if that was intentional, but in any case it makes for nice reading to start with my first 5 chapters and then transition right into Mr. Halloway's posts (which are much higher quality IMO).  

[stu]: http://pragprog.com/titles/shcloj/programming-clojure
[stuol]: http://blog.thinkrelevance.com/2008/12/12/on-lisp-clojure

")

; One of my favorite parts from *[On Lisp](http://www.paulgraham.com/onlisp.html)* is his implementation of an embedded Prolog interpreter based on a simple database and inference system.  My main goal for starting the On Lisp -> Clojure series was to eventually have this embedded Prolog system to use in my own applications.  I will start out by just talking about a few preliminary structures and functions and then expand on them in future installments.  

;<pre lang="lisp">

;;
;; The original Lisp make-hash-table function works on a 
;; cons-cell structure, however Clojure provides a persistent 
;; hash structure instead.  I am not going to port the Common 
;; Lisp function to Clojure, but instead will modify the Prolog 
;; implementation to fit a more idiomatic Clojure approach.
;; 
(defn make-db []
  nil)

(def *default-db* (ref (make-db)))

(defn clear-db 
  "Takes a db and clears it"
  ([] (clear-db *default-db*))
  ([db] (dosync (ref-set db nil))))

(defn db-query
  "Takes a db and a key and returns the mapped val"
  ([key] (db-query *default-db* key))
  ([db key] (get @db key)))

;;
;; db-push in Clojure is a bit trickier since it will be working
;; on a referenced persistent hash map.  This version is larger 
;; than the On Lisp version do to the fact that the database
;; being queried is an in-transaction value.  
;;
(defn db-push 
  "Stores a value in a the db"
  ([key val] (db-push *default-db* key val))
  ([db key val]
     (dosync
      (commute db
               (fn [the-db key val]
                 (assoc the-db key (cons val (get the-db key))))
               key
               val))))

;;
;; fact is almost a direct translation save for minor syntax diffs
;;
(defmacro fact 
  [pred & args]
  `(do
     (db-push '~pred '~args)
     '~args))

(defn test-pt1 []
  (fact painter reynolds joshua english)
  (fact painter canale antonio venetian)
  (db-query 'painter))

;</pre>

; There is definitely some room for improvement (suggestions/criticisms always welcome), but these initial functions work the same as the On Lisp originals, so that's all for now.  I'll come back later for more.

;-m
