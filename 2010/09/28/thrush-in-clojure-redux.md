[Combinators are fun](http://github.com/fogus/skiing).  In fact, there seems to be a combinator renaissance lately -- almost like the Ancient Greeks who excavated Mycenaean  technology surpassing their own[^books70].  There is one specific combinator called the *Thrush* that [effectively takes a set of nested functions and turns them into a pipeline](http://blog.fogus.me/2009/09/04/understanding-the-clojure-macro/).  There are far better explanations of the Thrush than this, including [one by Reginald Braithwaite focusing on Ruby](http://github.com/raganwald/homoiconic/blob/master/2008-10-30/thrush.markdown#readme), [one by Debasish Ghosh focusing on Clojure](http://debasishg.blogspot.com/2010/04/thrush-in-clojure.html), and [and another focused on Clojure by Shawn Hoover](http://clojure.bighugh.com/thrush.html).  Go ahead and read those -- I'll wait...

Of the posts linked above, I tend to point people toward Reginald Braithwaite's treatment because it focuses in on the functional aspects whereas the latter two do not.  What do I mean by that?  Well, the Clojure treatments focus too much on the `->` and `->>` macros which are only *mostly* Thrush combinators.  What a wishy-washy thing to say!  Maybe a more direct way to phrase it is that neither `->` nor `->>` are the Thrush combinator.  Eeeek!

A Little Bit About Macros
-------------------------

Macros in Clojure (and Lisp in general) operate in different "times", the most obvious being "compilation time".  The picture is actually foggier than this, but for this discussion it should suffice.  What that means is that a macro receives its arguments as unevaluated data structures that are then manipulated in some way before being fed into the compiler.  In the case of the `->` macro, a call of the following will look and act just like the Thrush operator mentioned in the posts above:


    (-> 5 (+ 4) -)
    ;=> -9

That is, we take the number 5, feed it into the addition (to four) function, and then negate it.  The expansion of this macro call would be:

    (- (+ 5 4))
    ;=> -9

This is what I meant by *mostly Thrush*.

However, `->` performs a transformation of its arguments into a nested form and doesn't deal directly with functions at all, if it did then we would expect the following to work:

    (-> 5 (fn [x] (+ x 4)) -)
    ; java.lang.RuntimeException: 
    ;   java.lang.UnsupportedOperationException...

The reason for this exception can be understood by observing the following:

    (macroexpand-1 '(-> 5 (fn [x] (+ x 4))))
    ;=> (fn 5 [x] (+ x 4))

The `->` macro is treating the form `(fn [x] (+ x 4))` not as a function at all but instead as a list of the symbol `fn`, a vector `[x]`, and another list `(+ x 4)` and just plops `5` right between the the symbol and vector.  Clearly `->` is not the Thrush at all, but does play one on TV.

The Thrush in Clojure
---------------------

Clojure has a function `comp` that works similarly to how we want the Thrush to act, but it does so in reverse -- so why not fix that "deficiency" directly:

    (defn thrush [a & args] 
      ((apply comp (reverse args)) a))
    
    (thrush 5 (fn [x] (+ x 4)) -)
    ;=> -9

Hooray!! We've done it!  A real-live Thrush combinator in Clojure.  There is one problem however.  This implementation is forced to walk the arguments once more than it should.  A more elegant and efficient solution (by [Chris Houser](http://blog.n01se.net), author of [The Joy of Clojure](http://joyofclojure.com)) is as follows:

    (defn thrush [& args] 
      (reduce #(%2 %1) args))
    
    (thrush 5 #(+ % 4) -)
    ;=> -9

Elegant indeed!

Thrushiness
-----------

My goal in this post was not to bash the view of `->` and `->>` as having a high degree of Thrushiness.  Instead, I wanted to illustrate that they are not entirely Thrushful, and you can run into issues if you treat them as such.

:f

*Thanks to [Craig Andera](http://twitter.com/craigandera) for reading a draft of this.*

[^books70]: This is how I feel whenever I read a computer book from the 70s.