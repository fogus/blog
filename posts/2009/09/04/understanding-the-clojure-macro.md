*Translations: [[日本語](http://t100life.blog121.fc2.com/blog-entry-213.html)]*

On page 109 of [Paul Graham's][pg] *ANSI Common Lisp* he describes a function [`compose`][compose] taken from the [Dylan programming language][dylan].  The function described is very similar to the [`->`][arrow] macro that often confuses many a new Clojurian.  In order to better understand this little nasty, I've found it useful to think of it as an arrow indicating the flow of data from one function to another.  That is,  the follow:

`(-> (Math/sqrt 25) int list)`

Can literally be read:

1.  Take the result of `(Math/sqrt 25)`
2.  Feed it into the function `int`
3.  Feed that result into the function `list`

Graphically, this can be viewed as:

    (Math/sqrt 25) --5.0--> (int 5.0) --5--> (list 5) 
    => (5)

Which expands into the following [s-expression][sexpr]:

    (list (int (Math/sqrt 25)))

When viewed this way, the `->` macro can be said to "thread" [^thread] a sequence of forms into each in turn.  This threading can be done within any form and is *always* stitched in as the second argument to the outermost s-expression.  Clojurians will sometimes use the comma [^comma] as a visual marker for the stitch point:

    (-> (/ 144 12) (/ ,,, 2 3) str keyword list)
    => (:2)
    
    (-> (/ 144 12) (* ,,, 4 (/ 2 3)) str keyword (list ,,, :33))
    => (:32 :33)

Unfortunately, the documentation for `->` does not do justice to its power, but hopefully things are a little more clear now.

-m

[^thread]: Not the `java.lang.Thread` kind of thread.
[^comma]: Since commas are considered whitespace.  The number of comma's used as a marker is a matter of taste.

[arrow]: http://clojure.org/API#toc35
[pg]: http://www.paulgraham.com
[dylan]: http://www.opendylan.org
[compose]: http://www.opendylan.org/books/drm/Functional_Operations#HEADING-104-2
[sexpr]: http://en.wikipedia.org/wiki/S-expression