Say you have a namespace `a` that needs to be tested: [^much-ado]

    (ns a)
    (defn ^{:private true} foo [] 42)

Using Clojure's `clojure.test` libs you might think it would be as simple as the following:

    (ns b
      (:use [clojure.test :only [deftest is]]))
    
    (deftest test-foo
      (is (= 42 (a/foo))))
    ; java.lang.IllegalStateException: var: #'a/foo is not public

Whoops!  That function `a/foo` is private and not readily available to call.  Now there are a number of ways to get at that function to test it, but the shortest path is the following:

    (deftest test-foo
      (is (= 42 (#'a/foo))))
    
    (test-ns 'b)
    ; Testing b
    ;=> {:test 1, :pass 1, :fail 0, :error 0}

So using the `#'` reader macro we can resolve the `a/foo` var directly, but what if there was a way to declare a test that specifically for testing private vars:

    (defmacro defprivatetest
      [name [local-name private-name] & body]
      (when *load-tests*
        `(def ~(vary-meta name 
                          assoc 
                          :test `(let [~local-name (resolve '~private-name)] 
                                   (fn [] ~@body)))
            (fn [] (test-var (var ~name))))))

This is almost exactly like the implementation of `clojure.test/deftest` except that it allows for a single named binding that is resolved to a function (private or not) in another namespace, like so:

    (defprivatetest test-a
      [foo a/foo]
      (is (= 42 (foo))))
    
    (test-ns 'b)
    ;=> {:test 1, :pass 1, :fail 0, :error 0}

Granted this is a lot of work just to avoid `#'`, but this is much more flexible in the face of change.

I leave it to the reader to change the implementation to allow multiple bindings.

:f

[^much-ado]: This post is much ado about nothing, but I thought I'd push it into the ether while everyone else is distracted by [Clojure Conj](http://clojure-conj.org/).
