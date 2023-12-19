Java left fold
===============
Using [Functional Java](http://projects.workingmouse.com/public/functionaljava/trunk)

    public static <A, B> A fold(F<A, F<B, A>> f, A z, Iterable<B> xs) {
      A p = z;
      for (B x : xs) {
          p = f.f(p).f(x);
      }
      return p;
    }

A Regex 
=======
Match username (min 3, max 16, aAzZ_09)
/^[a-zA-Z0-9_]{3,16}$/

Langs
=====
- D
- OCaml
- Ruby
- Scheme (on tap)
- Corba
- Haskell
- ML

Popes scare me worse than clowns.

Broccoli
========
- Predicate functions end in ?
- underscore is a scope var lookup
- Function overloading
- Varargs
- OO is message based

## Conventions
- `ClassName`
- `method_name`
- `_scalar_variable`
- `__collection_variable`
- `predicate?`
- `!undefine`
- `CONSTANT`
- Block-level elements (e.g. fundef, flow ctrl) enclosed in curly brackets `{}`
- Fact base views (e.g. rule LHS, bulk facts) enclosed in square brackets `[]`
- Function calls enclosed in parens `()`

Some of the conventions are enforced by the language, others are merely standards.

Tech Books
===========
1.  The Pencil: A History of Design and Circumstance
2.  The Design of Everyday Things
3.  The Codebreakers: The Story of Secret Writing
4.  Longitude: The True Story of a Lone Genius Who Solved the Greatest
Scientific Problem of His Time
5.  The Making of the Atomic Bomb

Charles H. Moore
=================
> Yes indeed, I write Forth code every day. It is a joy to write a few
simple words and solve a problem. As brain exercise it far surpasses
cards, crosswords or Sudoku; and is useful. 

Prolog/Lisp/Prolog/Lisp Insanity
=================================
Using [GNU Prolog](http://www.gprolog.org/)

    eval(X,O) :- defined(X,A), eval(A,O).
    eval([X|T],O) :- defined(X,A), eval([A|T],O).
    
    eval(X,X) :- number(X); X = t ; X = [].
    
    eval([quote,X],X).
    eval([lambda|X],[lambda|X]).
    eval([define,X,Y],t) :- \+ defined(X,_), asserta(defined(X,Y)).
    
    eval([cond],_) :- !, fail.
    eval([cond,[H,A]|_],Z) :- eval(H,O), \+ O = [],!, eval(A,Z).
    eval([cond,_|T],Z) :- eval([cond|T],Z),!.
        
    eval([F|A],X) :- eval_list(A,Ae), apply(F,Ae,X),!.
    
    eval_list([],[]).
    eval_list([H|T],[Ho|To]) :- eval(H,Ho), eval_list(T,To).
    
    apply(add,[X,Y],Z) :- Z is X + Y.
    apply(add,[X,Y|T],Z) :- I is X + Y, apply(add,[I|T],Z).
    
    apply(mul,[X,Y],Z) :- Z is X * Y.
    apply(mul,[X,Y|T],Z) :- I is X * Y, apply(mul,[I|T],Z).
    
    apply(sub,[X,Y],Z) :- Z is X - Y.
    apply(div,[X,Y],Z) :- Z is X / Y.
    
    apply(mod,[X,Y],Z) :- Z is X mod Y.
    apply(pow,[X,Y],Z) :- Z is X ** Y.
    
    apply(eq,[X,Y],t) :- X = Y.
    apply(eq,[X,Y],[]) :- \+ X = Y.

    apply(atom,[X],t) :- number(X);atom(X); X = [].
    apply(atom,[[_|_]],[]).
    
    apply(cons,[X|[Y]],[X|Y]).
    apply(car,[[X|_]],X).
    apply(cdr,[[_|T]],T).
    
    apply([lambda,[],E],[],O) :- eval(E,O).
    apply([lambda,[A|Ta],E],[L|Tl],O) :- subst(A,[quote,L],E,E2), apply([lambda,Ta,E2],Tl,O).
    
    subst(_,_,[],[]).
    subst(A,B,[A|T],[B|L]) :- subst(A,B,T,L).
    subst(A,B,[H|T],[H2|L]) :- subst(A,B,H,H2),subst(A,B,T,L).
    subst(A,B,A,B).
    subst(_,_,X,X).
    
    % gprolog
    :- predicate_property(defined,dynamic).
    % swi prolog
    :- dynamic defined/2.
    
    defined(_,[]) :- !,fail.
    
    
    lisp([
        [define,null,[lambda,[x],[eq,x,[quote,[]]]]],
        [define,and,[lambda,[x,y],[cond,[x,[cond,[y,t],[t,[quote,[]]]]],[t,[quote,[]]]]]],
        [define,not,[lambda,[x],[cond,[x,[quote,[]]],[t,t]]]],
        [define,append,[lambda,[x,y],[cond,[[null,x],y],[t,[cons,[car,x],[append,[cdr,x],y]]]]]],
        [define,list,[lambda,[x,y],[cons,x,[cons,y,[quote,[]]]]]],
        [define,pair,[lambda,[x,y],[cond,[[and,[null,x],[null,y]],[quote,[]]],[[and,[not,[atom,x]],[not,[ato
            m,y]]],[cons,[list,[car,x],[car,y]],[pair,[cdr,x],[cdr,y]]]]]]],
        [define,assoc,[lambda,[x,y],[cond,[[eq,x,[car,[car,y]]],[car,[cdr,[car,y]]]],[t,[assoc,x,[cdr,y]]]]]
        ],
        [define,evcon,[lambda,[c,a],[
            cond,[[eval,[caar,c],a],[eval,[cadar,c],a]],[t,[evcon,[cdr,c],a]]
        ]]],
        [define,eval,[lambda,[e,a],[
            cond,
            [[atom,e],[assoc,e,a]],
            [[atom,[car,e]],[cond,
            [[eq,[car,e],[quote,quote]],[cadr,e]],
            [[eq,[car,e],[quote,atom]],[atom,[eval,[cadr,e],a]]],
            [[eq,[car,e],[quote,eq]],[eq,[eval,[cadr,e],a],[eval,[caddr,e],a]]],
            [[eq,[car,e],[quote,car]],[car,[eval,[cadr,e],a]]],
            [[eq,[car,e],[quote,cdr]],[cdr,[eval,[cadr,e],a]]],
            [[eq,[car,e],[quote,cons]],[cons,[eval,[cadr,e],a],[eval,[caddr,e],a]]],
            [[eq,[car,e],[quote,cond]],[evcon,[cdr,e],a]],
            [t,[eval,[cons,[assoc,[car,e],a],[cdr,e]],a]]
        ]],
        [[eq,[caar,e],[quote,label]],
        [eval,[cons,[caddar,e],[cdr,e]],[cons,[list,[cadar,e],[car,e]],a]]
        ],
        [[eq,[caar,e],[quote,lambda]],
        [append,[pair,[cadar,e],[evlis,[cdr,e],a]],a]
        ]]]],
        [define,evlis,[lambda,[m,a],[
            cond,[[null,m],[quote,[]]],[t,[cons,[eval,[car,m],a],[evlis,[cdr,m],a]]]
        ]]],
        [define,cadr,[lambda,[x],[car,[cdr,x]]]],
        [define,caddr,[lambda,[x],[car,[cdr,[cdr,x]]]]],
        [define,cadar,[lambda,[x],[car,[cdr,[car,x]]]]],
        [define,caddar,[lambda,[x],[car,[cdr,[cdr,[car,x]]]]]]
    ]).
    
    load :- lisp(X), eval_list(X,_)
    
    paren("(").
    paren(")").
    
    digit("0") --> "0".
    digit("1") --> "1".
    digit("2") --> "2".
    digit("3") --> "3".
    digit("4") --> "4".
    digit("5") --> "5".
    digit("6") --> "6".
    digit("7") --> "7".
    digit("8") --> "8".
    digit("9") --> "9".
    
    space(" ") --&gt; " ".
    space("\t") --&gt; "\t".
    space("\n") --&gt; "\n".
    
    identifier_char(X) :-
        \+ space(X, _, _),
        \+ paren(X),
        \+ digit(X, _, _).

    ws --&gt; space(_).
    ws --&gt; space(_), ws.
    ows --&gt; space(_), ows.
    ows --&gt; [].
    
    program([H|T]) --&gt; sexp(H), ows, program(T).
    program([H]) --&gt; sexp(H).
    
    sexp(L) --&gt; "(", ows, sexp_list(L), ows, ")".
    sexp([]) --&gt; "(", ows, ")".
    sexp_list([H|T]) --&gt; (sexp(H); atom(H)), ws, sexp_list(T).
    sexp_list([H]) --&gt; sexp(H); atom(H).
    
    atom(A) --&gt; identifier(A) ; number(A).
    
    identifier(I) --&gt; [A], { identifier_char([A]) }, id_list(L), { atom_codes(I, [A|L]) }.
    id_list([H|T]) --&gt; [H], { identifier_char([H]) }, id_list(T).
    id_list([]) --&gt; [].
    
    number(N) --&gt; n_list(L), { number_codes(N, L) }.
    n_list([H|T]) --&gt; digit([H]), n_list(T).
    n_list([H]) --&gt; digit([H]).


Dyna-fgets
=========
    /*
     * rfgets.c
     * dynamically allocating fgets
     * daniel.fischer at iitb.fraunhofer.de
     */
    
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    
    enum { RFGETS_CHUNK_SIZE = 32 };
    
    static char *rfg(FILE *f, size_t n)
    {
        char b[RFGETS_CHUNK_SIZE + 1], *const e = b + sizeof b - 2, *p;
        size_t x;
        /* store '\0' in the second-to-last character of b */
        *e = '\0';
        /* read into b */
        if (!fgets(b, sizeof b, f))
            return 0;
        /* check for an incomplete line */
        if (*e != '\0' && *e != '\n' && (p = rfg(f, n + (x = sizeof b - 1))))
            return memcpy(p - x, b, x);
        /* this is the end of the line (or EOF); allocate a buffer for the line */
        else if ((p = malloc(n + (x = strlen(b) + 1))))
            return memcpy(p + n, b, x);
        else
            return 0;
    }
    
    char *rfgets(FILE *f)
    {
        return rfg(f, 0);
    }
    
    int main(void)
    {
        char *line;
        for (; line = rfgets(stdin); free(line))
            printf(">>%s<<\n", line);
        if (!feof(stdin))
            puts("Error?");
        return 0;
    }

Low-power PC
============
1. [AMD Geode LX 800 Nano ITX Motherboard/CPU Combo $154][mobo]
2. [512MB 200-pin SO-DIMM DDR-400 $20][dimm]
3. [4GB compact flash card $14][flash]
4. [12vdc AC/DC external wall wart $18][ac] 

Broccoli Adoption
==================
