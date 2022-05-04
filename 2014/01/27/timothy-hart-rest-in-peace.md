On January 20, 2014 the world lost a hacker named Timothy Hart.  Hart was not just any hacker -- he was a hacker of the highest order.  If you've read more than three books on Lisp then you might have seen his name pop up here and there.  If not, then you've definitely felt his influence.

## Macros

Macros are a ubiquitous element for any Lisp language in use today, but the fact is that they were not part of the early LISP implementations. Instead, LISP macros were invented years after Russell’s first implementation by Hart and were described in a [short MIT memo entitled *MACRO Definitions for LISP*](http://dspace.mit.edu/handle/1721.1/6111). This short but epic document described an extension to the original seven-function LISP eval that looked effectively like the following:

    ((eq (caar expr) (quote macro))
     (cond
       ((eq (cadar expr) (quote lambda))
        (eval (eval (car (cdddar expr))
                    (cons (list (car (caddar expr)) 
                                (cadr expr)) 
                          binds))
              binds))))

After reading this paper I took some time to extend my tiny Lisp interpreter Lithp to support Hart-style macros.  Please take a moment to read the original paper; you won't be disappointed.  It's striking how a feature so powerful and eventually far-reaching was described via a humble, 3-page memo.[^1]

[^1]: Hart's main contributions to the history of LISP appear to be in the form of code.  The [Lisp archives at the Software Preservation Group](http://www.softwarepreservation.org/projects/LISP/) have his name scattered throughout.

## Alpha-Beta Search

Sticking with short, yet deeply influential memos, Hart and D.J. Edwards described a modification to the known [minimax algorithm](http://en.wikipedia.org/wiki/Minimax) that would change game search forever.  The paper, *[The Alpha-Beta Heuristic](http://dspace.mit.edu/handle/1721.1/6098)* is 5-pages of astonishment and still stands as one of the best descriptions of the technique.  An interesting fact about Alpha-Beta pruning is that it was independently invented by numerous people and organizations.  So go great ideas.

## LISP 1.5 

While a comprehensive write-up of Hart's work would lead to a much longer post, I wanted to highlight three that personally influenced me in my programming life.  While both the macro and alpha-beta papers were important to me, neither more deeply influenced my thinking more than the [LISP 1.5 Programmers Manual](http://www.mcjones.org/dustydecks/archives/2012/08/29/590/).  Hart is listed as one of the co-authors of this amazing work and while I'm not certain of his precise contributions to the manual, his association with the book and his contributions to this early LISP implementation put him amongst the most influential Lisp hackers in the history of the young computing industry.  The *LISP 1.5 Programmer's Manual* is near the top of any list of book that every programmer should read.

Timothy Hart.  Thank you and rest in peace.

:F