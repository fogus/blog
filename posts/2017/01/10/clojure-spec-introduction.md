If you've looked into the relatively new [Clojure](http://www.clojure.org) library [clojure.spec](http://blog.cognitect.com/blog/2016/5/23/introducing-clojurespec) you might have come across something curious.  Observe the use of `core.spec/or`:

<pre class="prettyprint lang-clj">
(require '[clojure.spec :as s])
    
(s/def ::num (s/or :float float? 
                   :int   int? 
                   :ratio ratio?))
    
(s/conform (s/coll-of ::num) [0.25 1/2 1])
;;=> [[:float 0.25] [:ratio 1/2] [:int 1]]
</pre>

The result of the call to `s/conform` is quite descriptive in the way that it mirrors the vector `[0.25 1/2 1]` provided.  Indeed, the form of the return looks interesting like a naive version of a parse-tree, but why should such a thing happen?  What's the purpose of the `s/conform` function in light of the fact that *spec* already provides functions for validation and rich descriptions of their failures:[^explain-data]

<pre class="prettyprint lang-clj">
(s/valid? (s/coll-of ::num) [0.25 1/2 1])
;;=> true
    
(s/valid? (s/coll-of ::num) [0.25 1/2 :blarg])
;;=> false
    
(s/explain-data (s/coll-of ::num) [0.25 1/2 :blarg])
    
;;=> #:clojure.spec{
;  :problems ({:path [:float], 
;              :pred float?, 
;              :val :blarg, 
;              :via [:user/num], 
;              :in [2]} 
;             {:path [:int], 
;              :pred int?, 
;              :val :blarg, 
;              :via [:user/num], 
;              :in [2]} 
;             {:path [:ratio], 
;              :pred ratio?, 
;              :val :blarg, 
;              :via [:user/num], 
;              :in [2]})}
</pre>

[^explain-data]: The `#:clojure.spec{ ... }` form shows the new [namespaced map](http://dev.clojure.org/jira/browse/CLJ-1910) feature slated for the [Clojure 1.9 release](http://dev.clojure.org/jira/browse/CLJ/fixforversion/10750).

It certainly seems that if *spec* were limited to validation and explanation then it would be fairly useful in its own right.  However, *spec* provides capability beyond validation and explanation precisely because it's not designed to merely solve those problems but instead recognizes them as components for solving a much more pernicious "language problem".

So what's the problem?  

Functional languages in general and Clojure specifically fosters the use of aggregations of simple data to represent complex domain information, the ugly truth is that each and every data aggregation necessarily constitutes its own [mini-language](http://blog.fogus.me/2010/03/23/clojures-mini-languages/).  Of course, the nature of languages is such that their meaning and interpretation is encoded in custom parsing code.  That is, prior to the introduction to *spec*, Clojure programmers had to create ad hoc parsers for walking their domain structures and identifying and reporting any errors or inconsistencies.  Tools like the useful [Schema](https://github.com/plumatic/schema) library helped to alleviate the complexities around the problem of the data mini-language, but it's focused mainly on the problem of validation.  The *spec* library on the other hand recognizes that there is a fundamental synergy between specification, parsing, combination, validation, explanation, and generation and provides an extremely powerful tool for managing the complexities inherent in data design.

I'm going to take some time over the coming weeks to write about *spec* and explore some of its uses and advantages, specifically as they pertain to the problem of mini-languages brought on by the use of domain data encoding and in its use as a general [tool for thinking](http://swannodette.github.io/2016/06/03/tools-for-thought) about data and program design.

:F

*Thanks to David Nolen, Alex Miller, Rich Hickey, and Carin Meier for reading a draft of this post.*