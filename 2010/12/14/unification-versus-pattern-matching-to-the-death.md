The number one question asked of me at [The Conj](http://first.clojure-conj.org/)[^question] was: *what is unification?*.  Once I explained what unification was and how [clojure.core.unify](http://github.com/clojure/core.unify) implemented it, the follow-up question was inevitably: *how is unification different from pattern matching?*.  You see, Drew Colthorp wrote a fantastic pattern matching library called [Matchure](http://github.com/dcolthorp/matchure) that creates bindings based on the way that structural forms[^vsdestruct] match:

    (if-match [[?a ?b] [1 2]] {'?a a '?b b})
    
    ;=> {?a 1, ?b 2}

That is, the bindings `a` and `b` are created based on the way that the structural template containing the "variables" `[?a ?b]` matches with the actual form `[1 2]`.  The core.unify library works similarly to Matchure for this specific case, as shown below:

    (unify '[?a ?b] [1 2])
    
    ;=> {?b 2, ?a 1}

So what the heck do we need unification for?  The answer lies in the question: *what happens when there is a variable element on **both** sides of the match?*

    (if-match [[?a ?b] [1 ?a]] {'?a a '?b b})
    
    ; java.lang.Exception: 
    ;   Unable to resolve symbol: ?a in this context

Pattern matching, while powerful[^matching] does not handle the case where matching variables appear on both sides of the check.  However, this scenario is exactly where unification shines:

    (unify '[?a ?b] '[1 ?a])
    
    ;=> {?b 1, ?a 1}

And there we have it -- the fundamental difference between unification and pattern matching.  There are of course vast differences between the implementations of Matchure and core.unify[^binding], but I'll save those for another day.

:f

[^question]: Besides, "Will you please go away?"

[^vsdestruct]: I'll bet Drew gets asked all the time: *What is the difference between pattern matching and destructuring?* :p

[^matching]: Having used Scala over the past 2.75 years, I must say that I've grown to feel exquisite sadness whenever I use a language without pattern matching.

[^binding]: One huge difference that should be immediately apparent, is that Matchure does binding while core.unify does not.  I plan to add binding sooner rather than later.
