I recently compiled a list of [Perlis Languages](http://blog.fogus.me/2011/08/14/perlis-languages/) that were meant to illustrate varying paradigms not typically found in mainstream programming languages.  Those languages were meant to stretch your mind and are not necessarily representative of the state of the art in language design and implementation.  In this post I will provide a list of fairly new languages (let's say 5 years with a little flex) that display[^me] interesting features and display higher-order thinking in the way that they tend toward an evolution of past learnings in programming language thinking.  Not all of these languages enjoy active development, but the more important point is that they represent in some way "new language thinking".  Remember that this goal does not necessarily mean "innovative".

Unlike my Perlis post, I have not fully explored all of the languages below, so caveat emptor.  Please correct me if I misrepresent something.  No meaning behind the ordering implied.

### [take the associated poll!](http://gopollgo.com/what-is-the-most-innovative-programming-language-created-since-2006)

[^me]: As with any post, there should always be an implicit "to me" and "in my opinion" attached to any statement.  Welcome to blogging!

## Shen

*released: 2011, author: Dr. Mark Tarver*

I included the fantastic language Qi in my Perlis Languages post, so it may seem gratuitous to include its successor Shen here.  In fact Shen and Qi offer most of (all of?) the same high-level features:

 - A Lisp
 - Optional static typing
 - Pattern matching with guards
 - Macros
 - Partial application
 - Function backtracking
 - Builtin Prolog
 - Builtin compiler compiler

However, Shen is indeed the next evolutionary step after Qi.  Shen builds on the ideas in Qi in various ways, but the primary motivating force is targetability.  What exactly does that mean?  The precursor Qi was built to target Common Lisp as its host language and did so to maximum effect.  However, Qi was found to target a very small subset of Common Lisp.  Therefore, Dr. Tarver devised the idea that the successor Shen should be defined in terms of a minimal kernel Lisp language called Kl which would, in theory, provide an easier port target across various runtime hosts, including, but not limited to: JavaScript, Python, Clojure, and Common Lisp.  I have been thinking of "kernel Lisps" a lot and so Shen is ripe with ideas.

An example of a `member` function using the embedded Prolog:

    (defprolog member
      X [X | _] <--;
      X [_ | Y] <-- (member X Y);)
    
    (prolog? (member 1 [1 2 3]))
    /*=> true */

Partial application is automatic (simplified below):

    (* 2)
    /*=> #<FUNCTION LAMBDA (x) (* 2 x)>
    
    ((* 2) 54)
    /*=> 108 */

Here is a function that calculates the nth triangle number:

    (define triangle
      0 -> 0
      N -> (+ N (triangle (- N 1))))
    
    (triangle 100)
    /*=> 5050 */

And a typed version of the same:

    (tc +)  /* turn on type-checking */
    
    (define triangle
      {number --> number}
      0 -> 0
      N -> (+ N (triangle (- N 1))))
    
    (triangle 5)
    /*=> 15 : number */
    
    (triangle a)    
    /* type error */
    
Shen is a natural evolutionary step along the long, winding path that is Lisp history.  Many modern programming languages are absorbing features that Lisp innovated long ago, but still the most exciting languages are Lisps.

#### More resources

* [Official standard](http://www.lambdassociates.org/specification/shen_1.8.htm)
* [Source](http://www.lambdassociates.org/download/index.htm)
* [Shen in 15 minutes](http://shenlanguage.org/documentation/Tutorials/Shen-in-15min.htm)

## Agda 2

*released: 2009, author: Ulf Norel*

I've yet to truly wrap my head around Agda (any year now), but I can say a few things about it.  First, Agda is a purely functional, pattern matching, dependently typed programming language that walks the thin border adjacent to proof assistants.  In dependently typed languages, type expressions can contain (depend on) a program expression.  Therefore, the resolution of type constraints is a function of code or predicated on values.  It's like a kick in the face.  As an added bonus, the Agda type language is the same as the value language (i.e. the programming language itself).  What this means is that type invariants in Agda can express a much wider spectrum of constraints than typically attributed to static type systems; for example, [a list type that statically guarantees sorted order](http://www.cs.nott.ac.uk/%7Etxa/publ/ydtm.pdf) (PDF).  There's a drop-kick for you.

Here is an encoding of even and odd numbers in the Agda type system:

    data Nat : Set where
      zero : Nat
      suc  : Nat -> Nat
    
    fortyTwo : Nat
    fortyTwo = 42
    
    plus : Nat -> Nat -> Nat
    plus  zero   m = m
    plus (suc n) m = suc (plus n m)

    mutual
      even : Nat -> Bool
      even zero    = true
      even (suc n) = odd n

      odd : Nat -> Bool
      odd zero    = false
      odd (suc n) = even n

The previous code defines two datatypes: 1) the natural numbers and 2) the even natural numbers.  You can also define type functions as infix operators:

    _+_ : Nat -> Nat -> Nat
    zero  + m = m
    suc n + m = suc (n + m)

This represents the extent of my understanding about Agda so far.  While it would be nice to know more, what I've seen is amazing enough to warrant inclusion.

#### More resources

* [The Agda Wiki](http://wiki.portal.chalmers.se/agda/agda.php?n=Main.HomePage)
* [Intro sources](http://www.cse.chalmers.se/~ulfn/darcs/Agda2/examples/Introduction/)
* [Papers using Agda](http://wiki.portal.chalmers.se/agda/agda.php?n=Main.PapersUsingAgda)
* [Source](http://code.haskell.org/Agda/)

## Ioke

*released: 2008, author: Ola Bini*

Ola Bini's [Ioke](http://ioke.org) is based on a simple question: what kind of language can you build if you completely disregard performance and instead focus on expressivity?[^expr-ioke]  As it turns out you gain incredible expressiveness as shown in Bini's presentation series (links below).  One of the more interesting aspects of Ioke is that it is a homoiconic language providing macros.  

    myfor = dsyntax(
    "takes a name, an enumerable, and a transforming expr
    and returns the result of transforming each entry in 
    expression, with the current value of the enumerable
    bound to the name given as the first argument",

      [argName, enumerable, argCode]

      ''(`enumerable map(`argName, `argCode))
    )

    myfor(x, 1..5, x*2) 
    ;=> [2,4,6,8,10]

[^expr-ioke]: Really the goal is probably extreme expressiveness, but I like the way the speed angle sounds -- it's more "in your face".

Another advantage in studying Ioke is my own personal first axiom:

> When brilliant people create things, study them.

As programmers the onus is on us to push our skills to the point of uncomfortableness.  Studying the works of great minds is a highly effective way to do this.

#### More resources

* [Ioke](http://ioke.org/)
* [Ioke: a Folding Language](http://blip.tv/carlfk/ioke-a-folding-language-1-of-2-2240400) (video)
* [Ioke Wiki](http://ioke.org/wiki/index.php/Main_Page)
* [Announcement](http://olabini.com/blog/2008/09/ioke/)
* [Source](http://kenai.com/projects/ioke/sources/main/show)
* [Macro types in Ioke - or: What is a dmacro?](http://olabini.com/blog/2009/01/macro-types-in-ioke-or-what-is-a-dmacro/)

## Pure

*released: 2008, author: Albert Gräf*

Pure is a functional language built around [term rewriting](http://code.google.com/p/pure-lang/wiki/PurePrimer1#Term_rewriting).  Term rewriting is very similar to what we did in high-school algebra using the FOIL method:

    (x1 + y1) * (x2 + y2) = 
      (x1 * x2) +            // First
      (x1 * y2) +            // Outer 
      (y1 * x2) +            // Inner 
      (y1 * y2);             // Last

The code above defines the steps needles to transform the multiplication of two binomials into the FOIL steps.  Testing this rewrite rule yields:

    (x + 3) * (x + 5);
    //=> x*x+x*5+3*x+15

