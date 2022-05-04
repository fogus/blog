Today I would like to introduce an idea that I've been playing around with as a thought experiment for years, but that has finally become a reality.  Imagine a programming language designed specifically for teaching young computer science students a solid foundation in sound computer science topics as well as practical techniques useful in creating rock-solid industrial systems.  Below, I'll outline the features of Enfield.[^1]

<a href="http://blog.fogus.me/2013/01/21/enfield-a-programming-language-designed-for-pedagogy/enfield/" rel="attachment wp-att-5022"><img src="http://blog.fogus.me/wp-content/uploads/2013/01/enfield-150x150.jpg" alt="enfield" width="150" height="150" class="aligncenter size-thumbnail wp-image-5022" /></a>

*the Enfield logo*

Syntax
------

To start, I'm strong believer that when learning new, and at times complex topics it's outright detrimental to overload students with frivolous syntax rules.  Therefore, Enfield is designed with minimal syntax rules and what's more minimal than a Lisp like syntax.  An example function definition in Enfield is as follows:

    (define (fibo number)
      (letrec ([f (lambda (number n i)
                    (if (<= number 0)
                        i   
                        (f (- number 1) 
                           (+ n i) 
                           n)))])
        (f number 1 0)))
    
    (fibo 500)
    
    ;=> 1394232245616978801397
        2438287040728395007025
        6587697307264108962948
        3255716228632906915576
        58876222521294125

There are a few interesting points about this snippet:

  1. A Lisp syntax
  2. Bignums
  3. Tail-recursion
  4. Lexical scope

In each case a pedagogist can take the opportunity to deeply explore these important topics.  However, focusing again on syntax for a moment, while I do think that a simplified syntax is best, it's important to allow maximum flexibility.  It would be nice for Enfield to provide macros in additional to lexer and parser hooks (another chance to dive deeply here) in order to form the syntax to one's will, allowing a [multitude of syntactic forms](http://docs.racket-lang.org/algol60/index.html).  One interesting possibility, and one that I think is sorely lacking from CS education, is a special form of Enfield that allows a student to [write essays in a textual language containing embedded source code](http://docs.racket-lang.org/scribble/index.html).  Too often **CS education focuses on mathematical formalisms and code to the dismissal of the spoken and written word** -- Enfield can help!

Essential building blocks
-------------------------

Key to any college programming course is that the language of choice provide the essential building blocks of computation.  Ideally, an introductory course would require students to not only learn a language, but to also create an interpreter for a subset of the language using the language itself.  Enfield should therefore provide a set of basic building blocks useful for building Enfield in Enfield.  Features such as metaprogramming, code as data, concurrency primitives, continuations and parsing libraries are just a few that would allow a deep understanding of not only Enfield, but language design and implementation in the abstract.

A plethora of libraries
-----------------------

While it's a great idea to keep the syntax and logical footprint of Enfield small, in no way should the language be minimal in it possibilities.  A language for learning should provide a large spectrum of possibility, from offering easy [exploration of the untyped lambda calculus](http://matt.might.net/articles/compiling-up-to-lambda-calculus/) to first-class access to [performant linear algebra libraries](https://github.com/farr/PLT-Racket-Linear-Algebra-Bindings) via its packed offerings, [package repositories](http://planet.racket-lang.org/) and [popular source repositories](https://github.com/languages/Racket).

Hardcore IDE
------------

A first-class IDE is essential to the learning process.  As amazing IDEs like [LispWorks](http://www.lispworks.com/), [Mathematica Notebooks](http://reference.wolfram.com/mathematica/tutorial/UsingANotebookInterface.html) and [ObjectStudio](http://www.cincomsmalltalk.com/main/) have shown, there is amazing potential in a programming environment tightly integrated with the language on which it operates.  A Enfield IDE should have the standard fare, including: breakpoints, value inspection, import resolution, expression evaluation and all of the other features found in modern IDEs.  Additionally, a first-class graphical capability allowing students to plot graphs and [explore the shape of algorithmic execution](http://en.wikipedia.org/wiki/File:Drracket.png) are key.

<a href="http://blog.fogus.me/2013/01/21/enfield-a-programming-language-designed-for-pedagogy/dr/" rel="attachment wp-att-4976"><img src="http://blog.fogus.me/wp-content/uploads/2013/01/dr-150x150.jpg" alt="dr" width="225" height="225" class="aligncenter size-thumbnail wp-image-4976" /></a>

*An illustration of a 'hypothetical' IDE session of a student creating a Tetris game*.

There is a lot to learn from new IDEs technologies like [Light Table](http://www.chris-granger.com/2012/04/12/light-table---a-new-ide-concept/) in developing a Enfield IDE.

A plethora of paradigms
------------------------

While Enfield supports functional styles, and any curriculum should use it as the primary focus, it should also support numerous paradigms.

### Query languages and logic

Logic programming languages should be readily available or easily made using Enfield.  Ideally a curriculum built around Enfield would include sections on [miniKanren](http://planet.racket-lang.org/package-source/dfriedman/miniKanren.plt/1/1/doc.txt) (supplemented with appropriate [miniKanren literature](http://www.amazon.com/The-Reasoned-Schemer-Daniel-Friedman/dp/0262562146/?tag=fogus-20), [Prolog](http://docs.racket-lang.org/racklog/), [SQL](http://planet.racket-lang.org/package-source/sweeney/sqlid-helper.plt/1/0/htmldocs/scheme-pg.html) and [Datalog](http://planet.racket-lang.org/display.ss?package=datalog.plt&owner=jaymccarthy).  A solid foundation in logical thinking and declarative programming is key for any computer scientist.

### OOP

In addition to covering the standard academic topics such as functional programming, logic and continuations, Enfield should allow industry-proven object-oriented techniques.  A simple example might look as follows:

    (define animal-interface (interface () say))
      
    (define cat% (class* object% (animal-interface)
                   (super-new)
                   (define/public (say)
                     (display "meeeeew!"))))
     
    (define tom (new cat%))
    (send tom say)
    ;; meeeeew!

This is a work in progress.

### Metal

Lest I be accused of favoring high-level programming over bare metal techniques, I should say that every programmer should have exposure to low-level programming.  Ideally Enfield would have a first-class [FFI](http://docs.racket-lang.org/foreign/index.html) used to access C-level libraries created by the students.  A different world unfolds when one needs to manage memory manually and tweak algorithms to their utmost to milk every last bit of speed.  Enfield should facilitate that experience rather than deny.

### Other paradigms

In addition to the paradigms above, Enfield should also support others including, but not limited to: [prototype-based object programming](http://planet.racket-lang.org/display.ss?package=prometheus.plt&owner=daedalus) (sample below), [dataflow](http://docs.racket-lang.org/frtime/index.html), imperative and [parallel programming](http://planet.racket-lang.org/display.ss?package=riot.plt&owner=gcr).

    (define-object account (*the-root-object*)
      (balance set-balance! 0)
      ((payment! self resend amount)
       (self 'set-balance!
             (+ (self 'balance)
                amount))))
    
    (define a1 (account 'clone))
    (define a2 (account 'clone))
    
    (a1 'payment! 100)
    (a2 'payment! 200)
    
    (a1 'balance)
    ;; => 100
    
    (a2 'balance)
    ;; => 200
    
