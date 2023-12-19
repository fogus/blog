While reading [Christopher Alexander's amazing essay "A City is Not a Tree"](https://www.patternlanguage.com/archive/cityisnotatree.html) I was reminded of a page in one of my old notebooks. On that page I had taken some notes on some amazing computer science (and related) dissertations that might form the basis for a personal program design gestalt.[^ges]

<img src="http://blog.fogus.me/wp-content/uploads/2019/01/IMG_0996-300x171.jpg" alt="" width="300" height="171" class="aligncenter size-medium wp-image-6507" />

In this post I'll mention each paper that I wrote and provide a little context for my choice and add a little motivation.

[^ges]: A topic for another day... but Alexander's work is as good a summary as any I suppose.

Constraint programming 
----------------------

"[The Definition and Implementation of a Computer Programming Language Based on Constraints](https://apps.dtic.mil/dtic/tr/fulltext/u2/a096556.pdf)" By Guy Steele

#### follow-up reading / viewing

 * [Scheme code implementing the constraint language in the paper](https://www.cs.cmu.edu/Groups/AI/lang/scheme/code/csp/0.html)
 * [Constraint logic examples](https://github.com/joyofclojure/book-source/tree/master/src/clj/joy/logic) presented in [The Joy of Clojure](http://www.joyofclojure.com) 2nd edition
 * [Building Problem Solvers](http://www.amazon.com/Building-Problem-Solvers-Artificial-Intelligence/dp/0262061570/?tag=fogus-20) by Forbus and de Kleer -- *a great book that I've been slowly working my way through for a couple of years.*
 * [Algorithm = Logic + Control](http://dl.acm.org/citation.cfm?id=359136) by Robert Kowalski
 * [Constraints](http://dspace.mit.edu/handle/1721.1/6311) by Steele and Sussman
 * [We Really Don't Know How To Compute!](http://www.infoq.com/presentations/We-Really-Dont-Know-How-To-Compute) -- a talk by Gerald J. Sussman at the 2011 Strange Loop conference that builds on all of the ideas in Steele's original and also the "Constraints" paper.

Actors
------

"[ACTORS: A Model of Concurrent Computation in Distributed Systems](http://dspace.mit.edu/handle/1721.1/6952)" by Gul Agha

This was perhaps the work that built on the previous 14 years of Actor theory and formalized it into a practical and workable model of computation. 

#### follow-up reading / viewing

 * [A Universal Modular Actor Formalism for Artificial Intelligence](http://worrydream.com/refs/Hewitt-ActorModel.pdf)
 * [Semantics of communicating parallel processes.](https://dspace.mit.edu/handle/1721.1/57710) by Irene Greif
 * [Early history of Erlang](http://www.erlang.se/publications/bjarnelic.pdf) by by Bjarne Däcker

Continuations
--------------

"[Portable and high-level access to the stack with Continuation Marks](https://www2.ccs.neu.edu/racket/pubs/dissertation-clements.pdf)" by John Clements

This is a generalization of continuations to provide a way to store tagged marks in each continuation frame. This is a bit nuanced, but I learned a lot about "classic" continuations by seeing this contrasting approach.

#### follow-up reading / viewing

 * [Racket's continuation marks](http://docs.racket-lang.org/reference/contmarks.html)
 * [Axis of Eval on CoMs](https://axisofeval.blogspot.com/2012/08/continuation-marks.html)

Internet of Things
------------------

"[Programming Memory-Constrained Networked Embedded Systems](http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.157.8954)" by Adam Dunkels

I read this a long time ago most because I was interested in the idea that someone had developed a fairly modern operating system and TCP stack on the classic Commodore 64. While this was not the progenitor of the "Internet of Things" meme, it went well with (at the time) my exposure to the beginnings of IoT as a stand-alone movement, stemming from what was the seeds of sensor network simulation techniques.

#### follow-up reading / viewing

 * [Contiki-alightweightandflexible operating system for tiny networked sensors](https://ieeexplore.ieee.org/document/1367266)
 * [Bill Joy's Six Webs](https://www.technologyreview.com/s/404694/etc-bill-joys-six-webs/)

SHRDLU
------

"[Procedures as a representation for data in a computer program for understanding natural language](http://hci.stanford.edu/winograd/shrdlu/AITR-235.pdf)"

One of my summer projects in college[^coll] was to pick a famous paper and implement the system described therein. My choice (with some prompting from my professor) was a "Blocks World" program that, given an abstract representation of stacked colored blocks, took English-language commands to manipulate the blocks. The program was written in [XLISP](http://www.xlisp.org) and while it did understand a limited subset of English, the language that it understood was more Zork-like than anything else. For a grad school project I further built on that codebase by wiring in [CLIPS](http://www.clipsrules.net) and allowing the specification of a goal state, after which the program would solve the required steps to reach that goal.

[^coll]: My college had a summer program for its computer science department where students could work full-time with a local tech company and also work on a summer-long project. I learned a tremendous amount during this time and am still influenced by those lessons even today.

#### follow-up reading / viewing

 * [Understanding Natural Language](https://www.amazon.com/Understanding-Natural-Language-Terry-Winograd/dp/0127597506/?tag=fogus-20) by Terry Winograd

Speaking of CLIPS...

Production Rules
----------------

"[On the efficient implementation of production systems](http://reports-archive.adm.cs.cmu.edu/anon/scan/CMU-CS-79-forgy.pdf)" by Charles Forgy

My undergrad and graduate focus was on expert systems design and development[^es] so needless to say this paper was kinda the whizz for me. 

[^es]: A decidedly unfashionable branch of Ai these days... though I've often found that most systems that I consult for could use an expert system or have a "ad-hoc, informally-specified, bug-ridden, slow implementation of" an expert system embedded within. Even in the face of the fact that I rarely do active expert systems development, the processes of expert system design still influence the way that I do greenfield systems development.

#### follow-up reading / viewing

 * [Rete: A Fast Algorithm for the Many Pattern/Many Object Pattern Match Problem](https://www.researchgate.net/publication/222465509_RETE_A_fast_algorithm_for_the_many_patternmany_object_pattern_match_problem) by Charles Forgy -- *I'm very familiar with the Rate algorithm and a couple of its implementations in CLIPS and OPS5. Indeed, I've poured over this paper more than any other in my life as I've implemented Rete my fair share of times in various languages.* 
 * [Expert Systems Principles Programming](https://www.amazon.com/Expert-Systems-Principles-Programming-Fourth/dp/0534384471/?tag=fogus-20)
 * [Jess in Action](https://www.amazon.com/Jess-Action-Java-Rule-Based-Systems/dp/1930110898/?tag=fogus-20) -- *IMO the best book ever published by Manning*
 * [A teeny tiny production rules system in Clojure](https://leanpub.com/readevalprintlove004)

fexprs
------

"[Fexprs as the basis of Lisp function application; or, $vau: the ultimate abstraction](http://www.wpi.edu/Pubs/ETD/Available/etd-090110-124904/)" by John Shutt

It was long into my life as a Lisp adherent that I learned about fexprs, which have a long history in LISP. Simply put (nearly to the point of libel) fexprs are functions that do not evaluate their arguments. For a long time fexprs were a dead branch of the Lisp family lineage, but Shutt's paper gives them the thorough and respectful treatment that they deserve.

#### follow-up reading / viewing

 * [The LISP 1.5 Programmer's Manual](http://www.softwarepreservation.org/projects/LISP/book/LISP%201.5%20Programmers%20Manual.pdf)
 * [The Evolution of Lisp](https://www.dreamsongs.com/Files/HOPL2-Uncut.pdf) by Steele and Gabriel
 * [The theory of fexprs is trivial](http://citeseerx.ist.psu.edu/viewdoc/summary?doi=10.1.1.44.9264) by Wand

Contracts
---------

"[Behavioral Software Contracts