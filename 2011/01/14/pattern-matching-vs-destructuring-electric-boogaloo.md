After my [previous post comparing pattern matching and destructuring in Clojure](http://blog.fogus.me/2011/01/12/pattern-matching-vs-destructuring-to-the-death/) a fine gentleman was kind enough to point out an incredibly concise summary:

> Pattern matching is a conditional construct and destructuring isn’t.
> 
> -- [a fine gentleman](http://clojure.org)

This is absolutely correct, and I missed making that distinction in a clear way.  Consider the following, from my previous post:

    (def $ [1 2 3 4 5])
    
    (when-match [[1 2 ?x 4 5] $] x)
    ;=> 3
    
    (when-match [[1 2 ?x 400 500] $] x)
    ;=> nil

It appears that conditional branching is occurring somehow based on pattern matching, but what about the following using destructuring:

    (when-let [[_ _ x _ _] $] x)
    ;=> 3

Now it would seem that destructuring also acts a conditional, but that is an illusion brought on by the nature of `when-let`.  We can see the distinction more clearly if we pull apart what is actually happening behind the scenes using the `destructure` function[^lookat] (formatted for clarity):

    (destructure '[[_ _ x _ _] [1 2 3 4 5]])
    
    ;=> [V [1 2 3 4 5]
         _ (nth V 0 nil)
         _ (nth V 1 nil)
         x (nth V 2 nil)
         _ (nth V 3 nil)
         _ (nth V 4 nil)]

That is, the result of `destructure` is a simple sequence of steps to pull apart the vector `[1 2 3 4 5]` piece by piece.  Replacing the structure above into the expansion of the previous `when-let` you can see how the actual condition occurs (formatted for clarity):

    (let* [temp $]
      (if temp
        (do
          (let* [<the destructuring bits>]
            x))))

That is, not only does the destructuring provide no conditional property, but its not even involved in the expanded conditional logic at all.  However, if we expand [Matchure's](https://github.com/dcolthorp/matchure) `when-match` expression, the picture looks very different (formatted for clarity):

[sourcecode lang="clj" gist="779635"]<html>
<head><title>301 Moved Permanently</title></head>
<body bgcolor="white">
<center><h1>301 Moved Permanently</h1></center>
<hr><center>nginx/0.7.67</center>
</body>
</html>
[/sourcecode]

While this expansion is pretty big, it's clear that the code produced is walking through the vector `[1 2 3 4 5]` and conditionally checking its contents against the expected pattern.  

And therein lies the reality of the opening quote.

:F

[^lookat]: If you really want to understand destructuring, then there is no better way than to experiment with the `destructure` function.
