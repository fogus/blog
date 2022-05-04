From Alan Kay on the [fonc mailing list](http://vpri.org/mailman/listinfo/fonc) (circa March 2012):

> ... since meeting [Seymour](http://www.papert.org/) and starting to think about children and programming, there were eight systems that I thought were really nifty and cried out to be unified somehow:
>
> 1. Joss [^joss]
> 2. Lisp [^lisp]
> 3. Logo [^logo]
>    which was originally a unification of Joss and Lisp, but I thought more could be done in this direction).
> 4. Planner [^planner]
>    a big set of ideas (long before Prolog) by Carl Hewitt for logic programming and "pattern directed inference" both forward and backwards with backtracking)
> 5. Meta II [^meta2]
>    a super simple meta parser and compiler done by Val Schorre at UCLA ca 1963
> 6. IMP [^imp]
>    perhaps the first real extensible language that worked well -- by Ned Irons (CACM, Jan 1970)
> 7. The Lisp-70 Pattern Matching System [^lisp70]
>    by Larry Tesler, et al, with some design ideas by me
> 8. The object and pattern directed extension stuff I'd been doing previously with the Flex Machine and afterwards at SAIL 
>    that also was influenced by Meta II [^opm]
>
> One of the schemes was to really make the [pattern matching parts of this "work for everything"](http://vimeo.com/27860102) that eventually required "invocations and binding". This was doable semantically but was a bear syntactically because of the different senses of what kinds of matching and binding were intended for different problems. This messed up the readability and desired "simple things should be simple".
>
> Examples I wanted to cover included simple translations of languages ([English to Pig Latin](http://www.snowcrest.net/donnelly/piglatin.html), English to French, etc. some of these had been done in Logo), the Winograd robot block stacking and other examples done with Planner, the making of the language the child was using, message sending and receiving, extensions to Smalltalk-71, and so forth.
>
> I think today the way to try to do this would be with a much more graphical UI than with text -- one could imagine tiles that would specify what to match, and the details of the match could be submerged a bit.
>
> More recently, both [OMeta](http://tinlizzie.org/ometa/) and several of [Ian's matchers](http://piumarta.com/software/peg/) can handle multiple kinds of matching with binding and do backtracking, etc., so one could imagine a more general language that could be based on this.
>
> On the other hand, trying to stuff 8 kinds of language ideas into one new language in a graceful way could be a siren's song of a goal.
>
> Still ....
>
> Cheers,
>
> Alan

You could spend months surveying the contents of this post by Kay and  build a dissertation (or four) on a deep dive.

Enjoy!

:F

[^joss]: The [JOSS Primer](http://www.rand.org/pubs/research_memoranda/2006/RM5220.pdf) (pdf)

[^lisp]: Stuff that [I've written about Lisp](http://blog.fogus.me/tag/lisp/)

[^logo]: The book [To Artificial Intelligence](http://history.dcs.ed.ac.uk/archive/docs/ArtificialIntelligence/art.html) uses Logo extensively. I enjoyed Papert's [Mindstorms: Children, Computers, And Powerful Ideas](http://www.amazon.com/Mindstorms-Children-Computers-Powerful-Ideas/dp/0465046746/?tag=fogus-20) in college.

[^planner]: The key resources are Hewitt's [PLANNER: A Language for Proving Theorems in Robot](http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.80.756&rep=rep1&type=pdf) and Sussman and Winograd's [Micro-planner Reference Manual](http://hdl.handle.net/1721.1/5833).

[^meta2]: A [META II compiler](http://www.bayfronttechnologies.com/metaii.html)

[^imp]: The original [PDP-10 IMP source code](http://pdp-10.trailing-edge.com/decuslib10-03/index.html).

[^lisp70]: The [LISP-70 Pattern Matching System](http://ijcai.org/Past%20Proceedings/IJCAI-73/PDF/073.pdf) (pdf)

[^opm]: Kay's original [FLEX – A flexible extendable language](http://www.mprove.de/diplom/gui/kay68.html) is the best reference that I know.  Also of interest is Kay's original idea for "Simulation LOGO".  I don't know very much about it, but if someone were to jump on fonc and ask, they might just get an answer. :-)
