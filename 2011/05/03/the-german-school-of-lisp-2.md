The German school of Lisp is described by [Kazimir Majorinc](http://kazimirmajorinc.blogspot.com/2011/04/one-picolisp-snippet.html) as a Spartan movement[^mini] in Lisp implementation.  But let me be clear, the Lisps in question are not toys like the one's littering the Lisp landscape (including [my own](http://github.com/fogus/lithp)). Likewise, these Lisps are not libraries nor are they full-blown development ecosystems.  

Let's explore these distinctions for just a moment to clarify my meaning.

![Lisps](http://images.fogus.me/blog/lisps.png "The space between")

This post deals with that crimson space between, but first...

## Exo-Lisps

Exo-Lisps are the variants that exist to serve very pointed use cases.  They can take the form of highly specialized libraries or embeddables like [GLOS](http://people.csail.mit.edu/gregs/ref-dyn-patterns.html) or [Lush](http://lush.sourceforge.net/).  Likewise, Exo-Lisps may facilitate embedding within applications or act as extension frameworks like [Guile](http://www.gnu.org/s/guile/), [Visual Lisp](http://usa.autodesk.com/adsk/servlet/index?siteID=123112&id=770237), [Elk](http://sam.zoy.org/elk/), and [Emacs Lisp](http://www.gnu.org/software/emacs/emacs-lisp-intro/).  Finally, Exo-Lisps like [LFE](https://github.com/rvirding/lfe) and [Liskell](http://lambda-the-ultimate.org/node/2363) serve as skins over the semantics of another language.

## Practical Lisps

Practicality is a relative term.  Having said that, there are clearly a set of Lisps that exist primarily to solve "real world" problems, including: [Common Lisp](http://common-lisp.net/), [Clojure](http://clojure.org), [Scheme (R5RS, R6RS, R7RS-big)](http://www.schemers.org/), [Racket](http://racket-lang.org/), [Chicken Scheme](http://www.call-cc.org/), and [Dylan](http://www.opendylan.org/).  I'm tempting libel cases by including this particular category and filling it with these particular choices, but so be it.

## Kernel Lisps

I struggled to find a way of separating Exo-Lisps and Kernel Lisps and the following distinction from this struggle arose.  Where Exo-Lisps serve as a framework for a specific problem, Kernel Lisps serve as a framework for a more general problem  -- language development and experimentation.  Perhaps I'm splitting hairs.  Some examples of Kernel Lisps include: [R7RS-small](https://groups.google.com/forum/#!topic/comp.lang.scheme/MCfPoeir90s), [Scheme48](http://s48.org), [Lisp Machine Lisp](http://en.wikipedia.org/wiki/Lisp_Machine_Lisp), [EuLisp](http://people.bath.ac.uk/masjap/EuLisp/eulisp.html), and [Kawa](http://www.gnu.org/software/kawa/).

## Pedagogical Lisps

As I alluded to earlier, the [Lisp landscape is rife with toys](http://www.google.com/search?sourceid=chrome&ie=UTF-8&q=toy+lisp), [Ur-Lisps](http://axisofeval.blogspot.com/2010/08/no-more-minimal-early-lisps-pulleezz.html), and half implementations.  However, the vast majority of these Lisps exist for pedagogical pursuits and are rarely (if ever) overtly practical[^overt].

## Fluchtpunkt Lisps

Fluchtpunkt Lisps skirt the vanishing point between theory, practicality, and art.  These implementations are all of the other categories in some way, while simultaneously being none in particular.  They may or may not be general purpose languages in all instances, but they are all driven by a fervent ideal.  

Below is a list of Fluchtpunkt Lisp implementations that I've found, and some discussion of their driving ideal (as I understand):

#### [T](http://mumble.net/~jar/tproject/)

T is my favorite Scheme variant and the inspiration for many a Lisp thereafter.  I've poured over [Stephen Slade's T book](http://www.amazon.com/o/asin/013881905X?tag=fogus-20) numerous times, finding something mind blowing each time.  The primary driving force behind T was to prove that a Scheme could be made to run extremely fast, and fast it ran.  T's compiler technology was the motivation for numerous dissertations, and remains influential in many ways, even if some of its compilation techniques have fallen out of fashion.

#### [Qi/Shen](http://www.lambdassociates.org/Lib/libraries.htm)

Qi (and its successor Shen) really push the limits of what we might call a Fluchtpunkt Lisp.  I suspect it requires a categorization of its own.  

A few years ago I was looking for a Lisp to dive into and my searching uncovered two extremely interesting options: Clojure and Qi.  I eventually went with Clojure, but in the intervening time I've managed to spend quality time with Qi and I love what I've seen so far.  Qi's confluence of features, including an optional type system (actually, its type system might be more accurately classified as "skinnable"), pattern matching,[^patt] and an embedded logic engine based on Prolog, make it a very compelling choice indeed.

#### [newLISP](http://www.newlisp.org/)

newLISP raised some ire at one point or another because of its design choices, specifically its choice of pervasive dynamic scope and [fexprs](http://www.wpi.edu/Pubs/ETD/Available/etd-090110-124904/unrestricted/jshutt.pdf) (pdf).[^fexprs]  I like dynamic scope under some circumstances, but I can't say that I've followed it to its logical end for any application of significant size or complexity.  The advocates of newLISP are interesting folk and in many ways of my own mind.  For example, how would one [calculate Pi out to `n` digits in newLISP]()?

    (define (pi n)
     (replace "\\" (join (exec 
       (format "echo 'scale=%d; 4 * a(1)' | bc -ql" n))) 
       ""))
    
    (pi 30) 
    ;=> "3.1415926535897932384626433832795028841968"

Why you would call out to the UNIX `bc` command of course.  Why would you need your programming language to provide that type of calculation[^mul] when there are more appropriate tools for doing so?  I suppose the fundamental philosophy of newLISP is to not necessarily provide everything, but to make everything possible.  newLISP facilitates possibility by treating the use of `eval` as a first-class approach and utterly idiomatic.

#### [Arc](http://www.paulgraham.com/arc.html)

I agonized over including Paul Graham's Arc in the category of Fluchtpunkt Lisp, but I think this is the correct place for it.  The driving forces behind Arc are [succinctness](http://www.paulgraham.com/power.html) and [centenarianism](http://www.paulgraham.com/hundred.html) -- both of which are certainly emblematic of the Fluchtpunkt ideal.    Arc hasn't taken off as much as non-Paul-Graham humans would have liked, but I believe that the root of the problem is that they prayed for Practical, but instead got Fluchtpunkt.

#### [Pico Lisp](http://picolisp.com/5000/-2.html)

I first came across Pico Lisp when reading the paper ["Pico Lisp: A Radical Approach to Application Development"](http://software-lab.de/radical.pdf) by  Alexander Burger and was instantly fascinated.  The primary goal is to provide an idealized Lisp interpreter that runs as fast as possible.  To accomplish this goal Pico Lisp limits its feature set and optimizes the code path along the dimensions of its features.  The core types provided by Pico Lisp are numbers, symbols, and lists.  Given the paucity of these types Pico Lisp has the advantage of always taking the most direct interpretation path and thus avoiding any unnecessary checks and abstractions that a more corpulent Lisp might require.  For example, Pico Lisp's `quote` function is defined in such a way that it returns all of its arguments unevaluated allowing the operation of `quote` to optimize into only a return of its `cdr` rather than the `car` of its `cdr`.  Simple no?

Pico Lisp is, in my opinion, the most interesting entry in a family of *really really small Lisps* that also includes [Nanolisp](http://www-fourier.ujf-grenoble.fr/~sergerar/Nanolisp/) and [femtoLisp](http://code.google.com/p/femtolisp/), although I would hesitate to include these latter two in the Fluchtpunkt category.

#### [Wasp Lisp](http://sites.google.com/site/waspvm/)

Wasp Lisp is a small Scheme inspired by Erlang with lightweight cooperative threads, communication via channels, and an implementation of [MOSREF