---
title: "Languages Zoo"
author: "Fogus"
date: "2023.06.02"
---

As long as I can remember I have loved the process of defining, designing, and implementing my own programming languages. I've created so many languages that you could say that I've created something of a [programming languages zoo](https://plzoo.andrej.com/). Below you'll find a list of the languages that I've created over the years and those that I'm thinking about currently.


<a id="org1d1c742"></a>

## Active projects

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">Project</th>
<th scope="col" class="org-left">Status</th>
<th scope="col" class="org-left">Synopsis</th>
<th scope="col" class="org-left">Impl langs</th>
<th scope="col" class="org-left">Next Goals</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">Clojure</td>
<td class="org-left">maintainer</td>
<td class="org-left">The Clojure programming language</td>
<td class="org-left">Java + Clojure</td>
<td class="org-left">[ ] keep rockin'</td>
</tr>


<tr>
<td class="org-left">Cribbaj</td>
<td class="org-left"><a href="https://github.com/fogus/thunks/blob/main/langdev/forth.txt">delimiting</a></td>
<td class="org-left">An implementation of Forth with IDE and VM</td>
<td class="org-left">C + Ruby + Cribbaj</td>
<td class="org-left">[ ] kernel-forth definition</td>
</tr>


<tr>
<td class="org-left">Folog</td>
<td class="org-left">prototyped</td>
<td class="org-left">An Prolog implementation</td>
<td class="org-left">Common Lisp</td>
<td class="org-left">[ ] port to CLJ</td>
</tr>


<tr>
<td class="org-left">Quincunx</td>
<td class="org-left">prototyped</td>
<td class="org-left">A small functional language with FEXPRs</td>
<td class="org-left">Clojure -&gt; C</td>
<td class="org-left">[ ] read and annotate <a href="https://web.cs.wpi.edu/~jshutt/kernel.html">Shutt</a></td>
</tr>


<tr>
<td class="org-left">Incal</td>
<td class="org-left">documenting</td>
<td class="org-left">Minimal implementation of the J language</td>
<td class="org-left">C</td>
<td class="org-left">[ ] org-mode babel</td>
</tr>


<tr>
<td class="org-left">Ix</td>
<td class="org-left">dev</td>
<td class="org-left">An OO language with production rules</td>
<td class="org-left">C + Ix</td>
<td class="org-left">[ ] test harness [ ] vectors</td>
</tr>


<tr>
<td class="org-left">Juxt</td>
<td class="org-left">prototyped</td>
<td class="org-left">A functional concatenative language</td>
<td class="org-left">Java + Juxt</td>
<td class="org-left">[ ] reader impl</td>
</tr>


<tr>
<td class="org-left">Lithp</td>
<td class="org-left"><a href="https://github.com/fogus/lithp">released</a></td>
<td class="org-left">An implementation of McCarthy's original LISP w/ macros</td>
<td class="org-left">Python + Lithp</td>
<td class="org-left">[ ] perpetual hacking</td>
</tr>


<tr>
<td class="org-left">Minderbinder</td>
<td class="org-left">prototyped</td>
<td class="org-left">An RPN command line calculator with units of measure</td>
<td class="org-left">Python / Clojure</td>
<td class="org-left">[ ] define uom specs</td>
</tr>


<tr>
<td class="org-left">µPy</td>
<td class="org-left">dev</td>
<td class="org-left">A very small implementation of Python</td>
<td class="org-left">C + Python</td>
<td class="org-left">[ ] decorators</td>
</tr>
</tbody>
</table>


<a id="org43b9874"></a>

### Clojure

I've been a full-time member of the [Clojure](https://www.clojure.org) core team [for two-years](https://blog.fogus.me/2021/01/15/clojure-core/) and have worked to maintain, fix, and enhance the language and ecosystem. I certainly would not say that Clojure is "my" language, not even a little bit, but I have contributed enough to warrant mention. Perhaps one day when the Clojure history book is written I will get a paragraph.


<a id="orgf745b0f"></a>

### Cribbaj

Cribbaj is a Forth implementation that's intended to start with the [Forth-83](https://forth.sourceforge.net/standard/fst83/index.html) spec and expand into a more modern and portable implementation. The ideas that I would like to explore with Cribbaj are meta-compilation, meta-programming, kernel-Forths, IDE design, and batteries-included language implementation. The historical precedents for Cribbaj include: [KAMAS](https://en.wikipedia.org/wiki/KAMAS_(program)), Forth-83, [MMSForth](https://www.millermicro.com/mmsforth.html), [FIG Forth](https://www.retrotechnology.com/memship/figforth_1802.html), and [colorForth](https://en.wikipedia.org/wiki/ColorForth).

Some references that I'm using for design include:

-   [Thinking Forth](https://www.forth.com/wp-content/uploads/2018/11/thinking-forth-color.pdf) (pdf) by Brodie
-   [Threaded Interpretive Languages](https://www.amazon.com/Threaded-Interpretive-Languages-Design-Implementation/dp/007038360X/?tag=fogus-20) by Loeliger
-   [Forth: A Text and Reference](https://www.amazon.com/Forth-Text-Reference-Prentice-Hall-Software/dp/0133263312/?tag=fogus-20) by Kelly and Spies
-   [Designing & Programming Personal Expert Systems](https://archive.org/details/designingprogram0000town) by Townsend Feucht
-   [Object-Oriented Forth: Implementation of Data Structures](https://www.amazon.com/Object-Oriented-Forth-Dick-Pountain/dp/0125635702?tag=fogus-20) by Pountain
-   "[Programming a 144-computer chip to minimize power](https://www.youtube.com/watch?v=0PclgBd6_Zs)" by Chuck Moore
-   "[Moving Forth](https://www.bradrodriguez.com/papers/moving1.htm)" by Brad Rodriguez
-   "[Dynamic Typing and NaN Boxing](https://leonardschuetz.ch/blog/nan-boxing/)" by Leonard Schütz

Some thoughts and ideas about the direction that I'll take Cribbaj is found in [my thunks repo](https://github.com/fogus/thunks/blob/main/langdev/forth.txt).


<a id="org3d64d60"></a>

### Folog

Folog started as a fork of Hideyuki Nakashima's [PPiCL](https://www.cs.cmu.edu/afs/cs/project/ai-repository/ai/lang/prolog/impl/prolog/ppicl/0.html) &#x2013; an elegant and mind-warping little interpreter. I added extensions such that I could in turn implement a little LISP in the language but I have not taken it further. Eventually I would like to port the original logic to Clojure and occasionally hack on it toward that goal.


<a id="org15a0ea5"></a>

### Quincunx

Quincunx is currently implemented as a mini interpreter written in Clojure. The purpose was to better understand [FEXPRs](https://en.wikipedia.org/wiki/Fexpr), and while I think that goal was achieved, it would be nice to take it further. The natural path is toward the Kernel language, but some study needs to happen on my part before I can proceed. I would like to create a small interpreter in C and explore simple GCs, but that is further in the future.

Some references that I'm using for design include:

-   [LISP 1.5 Programmer's Manual](https://www.softwarepreservation.org/projects/LISP/book/LISP%201.5%20Programmers%20Manual.pdf) (pdf) by McCarthy, et al.
-   "vau-calculi and the theory of fexprs" by John Shutt
-   "The Theory of Fexprs is Trivial" by Mitchell Wand
-   [John Shutt's blog archive](http://fexpr.blogspot.com/)
-   "[Small objects and pointer tagging](https://bernsteinbear.com/blog/small-objects/) by Max Bernstein


<a id="orgfe1ca5a"></a>

### Incal

This started as the original C implementation, [J Incunabulum](https://code.jsoftware.com/wiki/Essays/Incunabulum) but I took the effort to translate the code into a style that fits my own style. Many similar efforts have since happened over the years but this one is mine. I would like to eventually make a literate version of this. 


<a id="org4e65eb8"></a>

### Ix

Ix is a language that I've been hacking on for 20 years. The seed of Ix is found in [CLIPS](https://www.clipsrules.net/), especially in the COOL subsystem. The central conceit of Ix is this: [can we mitigate the complexity of object-oriented programming by describing and scoping instance mutation in-terms of production rules](https://leanpub.com/readevalprintlove004/read#leanpub-auto-production-rules-and-oop)?

Some references that I'm using for design include:

-   [Expert Systems: Principles and Programming](https://www.amazon.com/exec/obidos/tg/detail/-/0534384471/?tag=fogus-20) by Giarratano and Riley
-   [Smalltalk-80: the language and its implementation](http://stephane.ducasse.free.fr/FreeBooks/BlueBook/Bluebook.pdf) (pdf) by Goldberg and Robson
-   [Object-oriented Programming with ANSI-C](https://www.cs.rit.edu/~ats/books/ooc.pdf) (pdf) by Axel-Tobias Schreiner
-   [Semantic Web for the Working Ontologist](https://www.amazon.com/Semantic-Web-Working-Ontologist-Effective/dp/0123859654?tag=fogus-20) by Deam Allemang
-   [Efficient Polymorphic Calls](https://www.amazon.com/Efficient-Polymorphic-Springer-International-Engineering-ebook/dp/B000WNJ39E/?tag=fogus-20) by Karel Driesen
-   "[Smalltalk-72 Instruction Manual](https://bitsavers.org/pdf/xerox/smalltalk/Smalltalk-72_Instruction_Manual_Mar76.pdf)" (pdf) by Goldberg and Kay
-   "Production Matching for Large Learning Systems" by Robert Doorenbos

Ix has been rewritten no less than three times and has gone from a Smalltalk-like system to a Lisp-like system to something in-between. I will probably hack on this until I die&#x2026; in fact, it might kill me.


<a id="org242d721"></a>

### Juxt

Juxt is very much inspired by [Joy](https://hypercubed.github.io/joy/joy.html) but I would like to incorporate some of the ideas in Clojure. A prototype was written in a weekend and allows code such as below:

    /swoncat (A B : B A)! cat/
    /Y ($cons) swoncat $cons i/

The code above implements the Y combinator and if you know Joy then you'll understand that the functions `cat` and `$cons` are list manipulation operators. Indeed, Juxt (like Joy) uses quotations as its lambda representation, which are simply lists that may or may not have some context attached to them. If I were to describe Juxt in one sentence it would be: a homoiconic, functional, stack-based, concatenative programming language with: lexical scope, closures, tail-recursion, a stack-effects mini-language, first-class environments, destructuring, modules, metaprogramming support, dynamic type checking, and Java interoperability. The simplicity of the language is such that the kernel was written in a weekend and further features like modules, recursion, lexical binding, and shuffle operators were written over time in Juxt.

Some references that I'm using for design include:

-   "[Recursion Theory and Joy"](https://hypercubed.github.io/joy/html/j05cmp.html) by Manfred von Thun
-   "[The Algebra of Joy](https://hypercubed.github.io/joy/html/j04alg.html)" by Manfred von Thun
-   [Arrows, Structures and Functors: The Categorical Imperative](https://www.amazon.com/Arrows-Structures-Functors-Categorical-Imperative/dp/0120590603?tag=fogus-20) by Michael Arbib
-   [The Architecture of Symbolic Computers](https://www.amazon.com/Architecture-Computers-McGraw-Hill-Supercomputing-Processing/dp/0070355967?tag=fogus-20) by Peter Kogge
-   [To Mock and Mockingbird](https://www.amazon.com/Mock-Mockingbird-Raymond-Smullyan/dp/0192801422/?tag=fogus-20) by Smullyan
-   "Can Programming be liberated from the von Neumann Style?" by J.W. Backus
-   "Clean &#x2014; a Language for Functional Graph Rewriting" by van Leer and Plasmejer


<a id="org07ec53b"></a>

### Lithp

[Lithp](https://github.com/fogus/lithp) is an implementation of an interpreter for the original LISP as described by John McCarthy and the macro system described later by Timothy Hart. The interpreter implements the following seven functions:

1.  `atom`
2.  `car`
3.  `cdr`
4.  `cond`
5.  `cons`
6.  `eq`
7.  `quote`

and also two special forms:

1.  `label`
2.  `lambda`

Using these functions I also implemented a Lithp interpreter in Lithp itself that implements the same 9 features and also added a `macro` special form.


<a id="org0236f81"></a>

### Minderbinder

Minderbinder started as a Clojure library of the same name providing unit of measure conversions. However, over time it took on a life of its own and has since morphed into a command line RPN calculator supporting bignums and unit of measure awareness. It's not a general solution yet and needs some work to be usable by someone other than myself.

Some references that I'm using for design include:

-   [The Frink Programming Language](https://frinklang.org/)
-   [Algorithms for RPN Calculators](https://archive.org/details/algorithmsforrpn0000ball) by John Ball


<a id="org9857696"></a>

### µPy

µPy is a spike of [tinypy](http://www.tinypy.org/) by Phil Hassey that I at one time considered for the front end to Ix (mentioned above). However, I never went that far and instead left in some of the features that Ix and later Python had, including:

-   Tuples
-   MOP
-   Advice
-   A very hacky form of predicate dispatch

I don't know how far I will go with this.

Some references that I'm using for design include:

-   [The Art of the Metaobject Protocol](https://direct.mit.edu/books/book/2607/The-Art-of-the-Metaobject-Protocol) by Kiczales, des Rivieres, and Bobrow


<a id="org37d875f"></a>

## Paused projects

<table border="2" cellspacing="0" cellpadding="6" rules="groups" frame="hsides">


<colgroup>
<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />

<col  class="org-left" />
</colgroup>
<thead>
<tr>
<th scope="col" class="org-left">Project</th>
<th scope="col" class="org-left">Status</th>
<th scope="col" class="org-left">Synopsis</th>
<th scope="col" class="org-left">Implementation</th>
<th scope="col" class="org-left">Eventual Goals</th>
</tr>
</thead>

<tbody>
<tr>
<td class="org-left">370 ASM</td>
<td class="org-left">lost</td>
<td class="org-left">An assembler for a subset of IBM 370 assembly</td>
<td class="org-left">C</td>
<td class="org-left">[ ] invent time machine</td>
</tr>


<tr>
<td class="org-left">Baysick</td>
<td class="org-left"><a href="https://github.com/fogus/baysick">released</a></td>
<td class="org-left">A embedded BASIC DSL</td>
<td class="org-left">Scala</td>
<td class="org-left">[ ] make work in Scala.latest</td>
</tr>


<tr>
<td class="org-left">Eleusis</td>
<td class="org-left">prototyped</td>
<td class="org-left">An implementation of microKanren</td>
<td class="org-left">Clojure</td>
<td class="org-left">[ ] speed up unification</td>
</tr>


<tr>
<td class="org-left">Mouse</td>
<td class="org-left">lost</td>
<td class="org-left">An implementation of the Mouse programming language</td>
<td class="org-left">C64 BASIC</td>
<td class="org-left">[ ] invent time machine</td>
</tr>


<tr>
<td class="org-left">Pascal</td>
<td class="org-left">lost</td>
<td class="org-left">A compiler for a subset of Pascal that targets a simple VM</td>
<td class="org-left">C</td>
<td class="org-left">[ ] invent time machine</td>
</tr>


<tr>
<td class="org-left">Russ-Forth</td>
<td class="org-left"><a href="https://github.com/fogus/russ-forth">released</a></td>
<td class="org-left">A teeny-tiny Forth-esque</td>
<td class="org-left">Ruby</td>
<td class="org-left">[ ] stdlib</td>
</tr>


<tr>
<td class="org-left">Tori-Lisp</td>
<td class="org-left"><a href="https://github.com/fogus/tori-lisp">released</a></td>
<td class="org-left">Small functional programming language</td>
<td class="org-left">JavaScript + Tori-Lisp</td>
<td class="org-left">[ ] in-broswer IDE</td>
</tr>


<tr>
<td class="org-left">μLithp</td>
<td class="org-left"><a href="https://github.com/fogus/ulithp">released</a></td>
<td class="org-left">A micro LISP implementation in 24 lines</td>
<td class="org-left">Ruby</td>
<td class="org-left">[ ] interop</td>
</tr>
</tbody>
</table>


<a id="orgec23df4"></a>

### 370 ASM

This was an IBM System/370 assembler that I wrote in college. It has been lost to the dustbin of history.


<a id="org3d668d4"></a>

### Baysick

[Baysick](https://github.com/fogus/baysick) is a Scala DSL that allows embedded BASIC-like syntax. It worked on a version of Scala circa 2009 but has not been maintained since.


<a id="org46d6d96"></a>

### Eleusis

Like everyone in the Clojure community, I also created an embedded microKanren library. Eleusis was slightly different in that I also implemented a REPL that read microKaren forms and executed them.

Some references that I'm using for design include:

-   "[µKanren: A Minimal Functional Core for Relational Programming](http://webyrd.net/scheme-2013/papers/HemannMuKanren2013.pdf)" by Hemann and Byrd
-   "[A Gentle Introduction to MicroKanren](https://erik-j.de/microkanren/)" by Erik Derohanian


<a id="org6ca68d2"></a>

### Mouse

Possibly my first language was a janky implementation of the [Mouse programming Language](https://en.wikipedia.org/wiki/Mouse_(programming_language)) on my old Commodore 64. I doubt that I implemented the whole language but the parts that I did were simple because the language can be implemented by simply walking an array of bytes and calling dispatch subroutines for each byte. In all likelihood I only implemented the mathematical operators and possibly variables.


<a id="org7ed0011"></a>

### Pascal

Also in college I implemented a 1-pass Pascal compiler that targeted an intermediate quadruple language and a little interpreter for that IL.


<a id="orgad85e61"></a>

### Russ-Forth

I implemented [Russ-Forth](https://github.com/fogus/russ-forth) years ago after being nerd-sniped by my old-friend Russ Olsen.


<a id="orgc31f09b"></a>

### Tori-Lisp

[Tori-Lisp](https://github.com/fogus/tori-lisp) is a code riff that I created from from [Mary Rose Cook's lovely Little Lisp](https://github.com/maryrosecook/littlelisp). The central conceit was: what would you need in a Lisp that didn't have macros but felt like it did?


<a id="orgae9deac"></a>

### μLithp

[μLithp](https://github.com/fogus/ulithp) was an experiment to see if I could create a Lisp-like interpreter in less than 30 lines of Ruby. It took 24.

There are certainly more than those listed above, but any additional exist only on dusty archives and not in my brain case. I can only hope that they bubble to the surface one day.

:F

**note: [a living version of this document is found on GitHub](https://github.com/fogus/thunks/blob/main/langdev/zoo.txt).**
