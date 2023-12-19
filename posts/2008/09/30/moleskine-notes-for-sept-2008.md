DSL
===
1.  Language describing a scanner
2.  Language to create Word of Dog parser

Goals for 2009
===============
1.  Emacs proficiency
2.  Read at least one Jane Austen novel

Polyglot projects?
===========
1802 emulator
Maze generator
WFE
Elite Trader
dnd


Testing Haikeeba 3
===================
<pre lang="scala">
import fogus.haikeeba.core._
val mv = Move.construct().from('d',2).to('d',4).build()
mv.getFrom() == 51
mv.getTo() == 35
</pre>

Ix Features?
============
-  Traits (because sometimes inheritence is not what you want)


2009 Books
===========
-  Dostoyevsky
-  Steinbeck
-  Orlando
-  Beowulf
-  Lord of the Flies
-  "White Line Fever" by Lemmy
-  Lem
-  [Paradigms of AI]()
-  [SICP]()
-  [Code Gen in Action]()
-  [Programming in Scala]()

<pre lang="ix">
var $s <- new FooClass

or

var $s <- FooClass.new

$s.func( list( a b c))
</pre>


RESTful CMX
===========
Stores comic archives, but provides different views into them.

/cmx/
/cmx/company/{company}/{line}/{title}/{vol}/{num}/{page}
/cmx/timeline/{year}/^company line above
/cmx/talent/{author}/{year}/^ company line above
/cmx/talent/{penciler}/{year}/^ company line above

examples
---------
/cmx/dc/vertigo/Animal+Man/v1/1/15/
shows page 15 of Animal Man, vol. 1

Navigation
-----------

### /cmx/
Shows {company, timeline, talent} set

### /cmx/company/
Shows a list of the companies

### /cmx/timeline/
Shows a list of years

### /cmx/talent/
Shows a list of authors/pencilers grouped by role

Scala Odds
===========
<pre lang="scala">
val odds = (1 to 10).filter(_ % 2 != 0)
</pre>

Classical Music
===============
1.  Jasha Heifetz
2.  Glen Gould 80-cd boxed set
3.  Mozart piano sonata no. 14 - no. 17
4.  Cesar Franck
5.  Edward Elgar Sonata for violin and piano in E minor, Op82
6.  Rachmaninov symphony no.2


<pre lang="ix">
fn foo || <- {
   out( got " " _s crlf)
}
</pre>

Y-combinator in Scala
==============
<pre lang="scala">
case class B[F,T](c: B[F, T] => (F => T)) extends (B[F, T] => (F => T)) {
    def apply(b: B[F, T]) = c(b);
}

def Y[F, T] = (f: (F => T) => F => T) =>
  B[F, T](x => f(x(x)(_)))(B(x => f(x(x)(_))))
</pre>

-m
