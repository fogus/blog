In my post about [unification vs. pattern matching](http://blog.fogus.me/2010/12/14/unification-versus-pattern-matching-to-the-death/) I included a little footnote about how the creator of [Matchure](https://github.com/dcolthorp/matchure) is probably often asked how pattern matching differs from Clojure's destructuring.  This footnote was an attempt to draw the elusive author out and comment on the subject.  Since my attempt was a failure, I decided to throw this post together to explain[^foo] it myself.

Imagine that you have a vector of numbers:

    (def $ [1 2 3 4 5])

How might we bind a variable `x` to the number at the center of this vector?  With destructuring this is simple:

    (let [[_ _ x _ _] $] x)
    ;=> 3

However, what if you wanted to bind `x` based on the precise contents of the vector?

    (when-let [[1 2 x 4 5] $] x)
    ; java.lang.Exception: Unsupported binding form: 1

We can't do that with destructuring because it only cares about the "shape" or "outline" of the collection.  However, using pattern matching we can indeed match in specific cases:

    (when-match [[1 2 ?x 4 5] $] x)
    ;=> 3
    
    (when-match [[1 2 ?x 400 500] $] x)
    ;=> nil

This is matching at its most precise, but Matchure also allows matching based on arbitrary functions:

    (when-match [[(odd? ?) (even? ?) ?x _ _] $] x)
    ;=> 3
    
    (when-match [[(even? ?) (odd? ?) ?x _ _] $] x)
    ;=> nil
    
    (when-match [[_ _ ?x & (some #{5} ?)] $] x)
    ;=> 3
    
    (when-match [[_ _ ?x & (some #{42} ?)] $] x)
    ;=> nil

Both destructuring and pattern matching let you draw a picture of the structure under observation and create bindings as a result.  However, pattern matching subsumes destructuring because it allows you to draw at a finer levels of detail.

:F

[^foo]: This post refers to Matchure's implementation and is not meant to stand in for every language implementation of pattern matching.  However, it is likely *mostly correct* for said languages.
