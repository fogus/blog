*this is another entry in a series on [programmer enrichment](http://blog.fogus.me/tag/enrichment)*

<img src="http://blog.fogus.me/wp-content/uploads/2011/08/alan_perlis-243x300.jpg" alt="" title="alan_perlis" width="243" height="300" style="display: block; margin-left: auto; margin-right: auto" />

> A language that doesn’t affect the way you think about programming is not worth knowing.
> -- Alan Perlis

*inspired by a [LtU thread](http://lambda-the-ultimate.org/node/3464) and the great post [Programming Achievements: How to Level Up as a Developer](http://jasonrudolph.com/blog/2011/08/09/programming-achievements-how-to-level-up-as-a-developer/) by Jason Rudolph[^my-list]. most code samples from [Rosetta Code](http://rosettacode.org/wiki/Main_Page).*

The philosopher Friedrich Nietzsche believed that all interactions and interpretations of the external world occurred through the lens of individual perspective.  Therefore, truth to Nietzsche was subject to interpretation.  Programmers are especially prone to subjective views on their industry best-practices and valuations, often colored by their chosen[^chosen] programming languages.[^not-just]  However, Nietzsche believed that in order to achieve a high-level of thinking, one should grant all perspectives equal opportunity and let them stand, or fall, on their own merits.  You've probably experienced this approach yourself in college if a professor demanded the classic assignment whereby students write an essay taking the side of an argument that they themselves denounce.  This type of exercise is potentially very powerful in that it often works to crystalize one's own beliefs and occasionally helps to shake those beliefs to the core.

A Perlis Language is a programming language that I believe will shake one's views on software development to the core.

Below I will enumerate[^npo] some of my Perlis Languages and give an all-too-brief overview and just a sip of code for each.

## Joy

[Joy](http://www.kevinalbrecht.com/code/joy-mirror/joy.html) is an example of a [concatenative programming language](http://c2.com/cgi/wiki?ConcatenativeLanguage) or more simply put, a stack-based language.  That is, functions are never explicitly passed arguments, but instead take an implicit stack that is maintained by the programmer.  This may seem totally insane, a sentiment not wholly inaccurate, but the levels of succinctness and elegance achieved via this model is stunning.  The complexity in maintaining a stack is typically handled by concatenative programmers through the process of vigorous factoring of words (think functions) into smaller words.  There is a whole class of purely stack-manipulation primitives that signal the need for factoring when their use becomes too pervasive.  The goal is to constantly drive the code toward an expression of the domain rather than an expression of the programming language-specific expression of the domain.  There are certainly better and more historically significant concatenative languages, but Joy strikes a nice chord with me -- your mileage may vary.

    (* Quicksort in Joy *)
    
    DEFINE qsort ==
       [small]
       []
       [uncons [>] split]
       [swapd cons concat]
       binrec .

#### More information

* [Daniel Spiewak wrote a great trilogy about concatenative languages](http://www.codecommit.com/blog/category/cat); his treatment is far better than I could ever produce.

* [A Joy tutorial](http://www.kevinalbrecht.com/code/joy-mirror/j01tut.html)

* The [Concatenative Wiki](http://concatenative.org/wiki/view/Front%20Page)

*possible substitutes: [Factor](http://factorcode.org/), [Forth](http://www.ultratechnology.com/4th_1970.html), [Cat](http://www.cat-language.com/), [PostScript](http://www.tailrecursive.org/postscript/postscript.html)*

## Eiffel

Eiffel is an extremely opinionated object-oriented programming language.  Like myself, many programmers today have a majority-share of "real-world" experience in one OO language or another.  However, you haven't used anything like Eiffel.  That is, the bulk of Eiffel will be recognizable to most programmers, but the enlightening feature for most is the first-class support for [Design by Contract](http://en.wikipedia.org/wiki/Design_by_contract).  In a nutshell, DbC (or contracts programming) is an approach that allows one to specify the expectations on method results based on their required input parameters and also to define class-level invariants.[^inv]  This may seem fairly simplistic, but it's in this simplicity that forms the basis for an extremely powerful design paradigm.  In fact, contract support libraries and extensions have been created for other languages: [Ruby](http://blog.brianguthrie.com/2007/03/20/lets-make-a-deal-handshake-a-contract-system-for-ruby/), [Clojure](http://fogus.me/fun/trammel), [C++](http://en.wikipedia.org/wiki/GNU_Nana), [Java](https://code.google.com/p/cofoja/), and [Groovy](http://github.com/andresteingress/gcontracts) to name only a few.

    -- Dictionary class fragment
    
    class DICTIONARY [ELEMENT]
    feature
    	put (x: ELEMENT; key: STRING) is
    			-- Insert x so that it will be retrievable
    			-- through key.
    		require
    			count <= capacity
    			not key.empty 
    		ensure
    			has (x)
    			item (key) = x
    			count = old count + 1
    		end
    invariant
    	0 <= count
    	count <= capacity
    end

#### More information

* [An introduction to DbC](http://www.eiffel.com/developers/design_by_contract.html)
* [Using code contracts for safer code](http://buksbaum.us/2011/04/20/using-code-contracts-for-safer-code/)
* [DbC and Unit Testing](http://onestepback.org/index.cgi/Tech/Programming/DbcAndTesting.html) by Jim Weirich

*possible substitutes: [D](http://www.digitalmars.com/d/2.0/index.html), [Cobra](http://cobra-language.com/)*

## Qi

[Qi](http://www.lambdassociates.org/Book/page000.htm) is a Lisp -- big deal right?  Wrong.  Qi is a Lisp with skinnable types, a built-in logic engine, rewrite rules, back-tracking dispatch, built-in lexer and parser generators, and pattern matching that compiles down to [highly-performant Common Lisp](http://www.lambdassociates.org/studies/study10.htm).  The author [Dr. Mark Tarver](http://www.lambdassociates.org/) has strong opinions on the state of the software development in general and Lisp in particular (you may already have read *[The Bipolar Lisp Programmer](http://www.lambdassociates.org/blog/bipolar.htm)*) and these opinions shine in the implementation of Qi.  The successor to Qi, Shen, is in active development with a release scheduled some time this summer.

    \ A simple expression calculator \
    
    (datatype arith-expr
    
        X : number;
        ====================
        [num X] : arith-expr; 
    
        if (element? Op [+ - * /])
        X : arith-expr; Y : arith-expr;
        ===============================
        [X Op Y] : arith-expr;)
    
     (define do-calculation
      {arith-expr --> number}
      [X + Y] -> (+ (do-calculation X) (do-calculation Y))
      [X - Y] -> (- (do-calculation X) (do-calculation Y))
      [X * Y] -> (* (do-calculation X) (do-calculation Y))
      [X / Y] -> (/ (do-calculation X) (do-calculation Y))
      [num X] -> X)

#### More information

* [Qi language group](http://groups.google.com/group/Qilang)
* [Purely Functional Red/Black Trees in Qi](http://jng.imagine27.com/articles/2011-06-28-141124_purely_functional_types_red_black_trees_in_qi.html) by Justin Grant


*possible substitutes: [Pure](http://code.google.com/p/pure-lang/)*

## Clojure

Clojure is a fantastic language, but [let's just say that I have some skin in this game](http://www.joyofclojure.com).  Take this entry with a grain of salt.  So instead...

## Kernel

[Kernel](http://web.cs.wpi.edu/~jshutt/kernel.html) is also a Lisp, but it differs in that it completely eliminates the line separating compile-time and runtime via [fexprs