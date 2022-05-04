John McCarthy was a genius of the highest order.  Much is known about his contribution to Lisp as its inventor, but he was also the mind behind much of early AI research, the progenitor of much of computer chess, and the initiator of time-sharing.  As a Lisp advocate I have many thanks due to John McCarthy and it saddens me that I will never have the chance to give them.  However, like many geniuses McCarthy was surrounded by likewise brilliant creators and thinkers.  This post is devoted to some of the brilliant minds that influenced, provoked, and in one case defied McCarthy for the betterment of the early LISP history spawning the Lisp world that we now enjoy.  So goes the history of monumental minds that their likewise monumental shadow engulfs those whose help was critical to the larger historical significance.

## Steve Russell

That Steve Russell created the first LISP evaluator is fairly well known, so to include his name on this list is questionable.  However, it's important to note that has it not been for his effort the history of LISP as a language might have never occurred and if it had then it would have manifested very differently.  In early 1959, Steve Russell took the theoretical idea that was LISP devised by his employer John McCarthy and hand-compiled the "Universal LISP Function" to operate on the IBM 704.  In a survey of one, I suppose the lesson is to never listen to your boss. 

The implementation was novel in that it utilized the machine capabilities to maximum effect.  However, unbeknownst to Russell, and early decision would "haunt" LISP even up to today:

> Because of an unfortunate temporary lapse of inspiration, we couldn't think of any other names for the 2 pointers in a list node than "address" and "decrement," so we called the functions CAR for "Contents of Address of Register" and CDR for "Contents of Decrement of Register."

As a contributor to [Clojure](http://clojure.org) it always brings a smile to my face when people deride its lack of `car` and `cdr`.  What seems a priceless nostalgic gem to some is but the leavings from a lack of inspiration some 50+ years ago.

## Dan Edwards

Dan Edwards (gc) during June/July of 1959 wrote the garbage collector that would serve up to the 1.5 release of LISP. (McCarthy 1962)  According to McCarthy, the garbage collector worked as such:

> At any given time only a part of the memory reserved for list structures will actually be in use for storing S-expressions. The remaining registers (in our system the number, initially, is approximately 15,000) are arranged in a single list called the free-storage list. A certain register, FREE, in the program contains the location of the first register in this list. When a word is required to form some additional list structure, the first word on the free-storage list is taken and the number in register FREE is changed to become the location of the second word on the free-storage list. No provision need be made for the user to program the return of registers to the free-storage list.

Edwards, Michael Levin, and Hart (below) also proved the equivalency between the alpha-beta and minimax search algorithms, (Edwards 1961) that was also overshadowed by McCarthy given that he seemingly [invented alpha-beta out of thin air in 1955](http://www-formal.stanford.edu/jmc/slides/wrong/wrong-sli/wrong-sli.html).

## Timothy Hart

Macros are a ubiquitous element for any Lisp language in use today, but the fact is that they were not part of the early LISP implementations.  Instead, LISP macros were invented years after Russell's first implementation by Hart and were described in a scrawled 3-page document called *MACRO Definitions for LISP* published as an MIT memo.  (Hart 1963)  This short but epic document described an extension to the [original seven-function LISP `eval`](http://fogus.me/static/preso/magnificent7/#slide10) that looked effectively like the following:

    ((eq (caar expr) (quote macro))
     (cond
       ((eq (cadar expr) (quote lambda))
        (eval (eval (car (cdddar expr))
                    (cons (list (car (caddar expr)) 
                                (cadr expr)) 
                          binds))
              binds))))

I've extended [my Lithp interpreter with Hart-style macros](https://github.com/fogus/lithp/blob/master/src/core.lisp#L107) if you'd like to play with such a beast.

## Nathaniel Rochester and Herbert Gelernter

It's hard to imagine how Rochester, the creator of the first assembler, could be overshadowed by McCarthy, but from a LISP perspective he certainly is.  The influence of Rochester and Gelernter are likely deeper than I know, but in one instance theirs is a negative influence.  That is, McCarthy at one point was considering an extended version of Fortran with list processing capabilities for his AI research, of which such a system was developed by Rochester and Gelernter.  However, McCarthy realized that such a library did not adequately serve his needs which led him to explore the creation of a language more suited to his AI explorations and LISP was the result.

## Alan R. Kotok

Alan Kotok, one of the designers of the PDP-10 was driven to design a machine that could effectively host a LISP system.  The PDP-10 is, at its core, is built with an eye toward LISP.  Indeed, the 36-bit word size is there because LISP implementations benefited from two 18-bit pointers per word (the elements of a cons cell).  It's unclear how the PDP-10 influenced LISP, if at all,[^ai] but I thought that this was a great story and a preview of the Lisp Machines that would come years later.

[^ai]: Much of the early AI work, done with Lisp, was hosted on the PDP-10, so I imagine there must have been some level of bidirectional influence.

## Others and Inaccuracies?

Did I misrepresent something here?  Please let me know.

Additionally, there are many others involved with the early days of LISP whose contributions were not easy to find, including:

- Paul W. Abrahams
- Robert K. Brayton
- Saul Goldberg
- David Luckham
- Michael Levin
- More...

If you have any information about these pioneers, or are the pioneers themselves, then please comment here with pointers to more information and I will add it to the main post body.

## References

  - Dan Edwards and Timothy Hart. 1961. *[The Alpha-Beta Heuristic](http://dspace.mit.edu/handle/1721.1/6098)*.
  - Dan Edwards. *[The LISP II Garbage Collector](ftp://publications.ai.mit.edu/ai-publications/pdf/AIM-019.pdf)* (PDF).
  - Timothy Hart and Michael Levin. *[MACRO Definitions for LISP](http://dspace.mit.edu/handle/1721.1/6111)*
  - John McCarthy, Paul W. Abrahams, Daniel J. Edwards, Timothy P. Hart, and Michael Levin. 1962. *[LISP 1.5 Programmer's Manual](http://www.amazon.com/o/asin/0262130114?tag=fogus-20)*.

:F
