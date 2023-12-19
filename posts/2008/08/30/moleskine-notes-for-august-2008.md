with-input-from-file
====================
`(with-input-from-file "some-file" read-line)`
=> "first line of file"

The first argument of with-input-from-file specifies the file to
read. Its second argument must be a procedure of zero
arguments. With-input-from-file opens the given file and connects the
default input port to that file. In this context it evaluates the
given procedure. When the procedure returns, the default inport port
is re-connected to the file or device that was in effect before the
call to with-input-from-file. 

Music for 2009
==============
-  Louis Armstrong And King Oliver 
-  Bags' Groove by Miles Davis

Wines
=====

2005 Lot 205 Petite Sirah
--------------------------
$9, Tastes like the greatest generation.
Will taste better in a year.
Burgers, pizza night

Foppiano Petit Sirah Sonoma Russian River
------------------------------------------
$14, Smells like a candy necklace
Tastes like a baseball glove.  
Average.

2006 Rosenblum Petite Sirah Heritage Clones
--------------------------------------------
$14, Cranberry and Pom nose
Candy-city with Cafe Mocha and Grand Monier
Great buy!

McCainomicon
============
McCainomicon is a living document of unspeakable evil the contents of which are certain to cause its viewer insanity, outrage, boredom, or inescapable nostalgia for the greatest generation (no saving throw).

McCainomicon was once the possession of the mad Texan Gee Dub o' Bushared and it is said (in hushed tones) that the vile document is utterly tainted by his influence. All whom posses the McCainomocon are inevitably devolved to mental and intellectual adolescence.

Things to notes about CLIPS
============================
-  The fact-base is an extremely hostile programming environment

King of Traffic
===============
-  Marcel Proust *In Remembrance of Things Past*
-  Hans Monderman
-  Wolfgang Sachs *For Love of the Automobile*

[MOP vs Reflection](http://groups.google.com/group/comp.lang.lisp/browse_frm/thread/50a6dd2e463eb239)
====================
-  Meta-circular interpreter
-  Andreas Paepcke's *User-Level Language Crafting*
-  Aspect-oriented programming
-  L@@k Gregor Kiczales' *TinyCLOS* for Scheme [link](ftp://ftp.parc.xerox.com/pub/mops/tiny/)

Books for the book sale
=========================
-  DragonFlight by McCaffrey
-  Denyri Rising by Kurtz
-  The Once and Future King by White
-  Pet Sematary
-  Eon by Bear
-  Recluce
   +  The Magic Engineer
   +  The Order War
-  *Tigana* by Guy Gavriel Kay
-  *The Lions of Al-Rassan* by Guy Gavriel Kay
-  Memory, Sorrow and Thorn by Tad Williams
   +  Stone of Farewell
   +  To Green Angel Tower
-  The Gap Cycle
   +  The Gap into Conflict: The Real Story
   +  The Gap into Vision: Forbidden Knowledge
   +  The Gap into Power: A Dark and Hungry God Arises
   +  The Gap into Madness: Chaos and Order
   +  The Gap into Ruin: This Day All Gods Die
-  The Deathgate Cycle
   +  The Hand of Chaos
   +  Into the Labyrinth
   +  The Seventh Gate
-  Star of the Guardians
   +  The Lost King
   +  King's Test
-  **Jack Vance**
-  Wizard of Earthsea
   +  A Wizard of Earthsea
   +  The Tombs of Atuan
   +  The Farthest Shore
-  Chronicles of an Age of Darkness by Hugh Cook
   +  Wizards and the Warriors
   +  Wordsmiths and the Warguild
   +  The Women and the Warlords (aka The Oracle)
   +  The Walrus and the Warwolf 
   +  The Wicked and the Witless
-  *The Shadow of the Torturer* by Gene Wolfe
-  Mordant's Need by Stephen Donaldson
   +  The Mirror of Her Dreams
   +  A Man Rides Through
- **Heinlein**
   +  I Will Fear No Evil
   +  Number of the Beast

Word Frequency.rb
=================
<pre lang="ruby">
the_file='test.txt'
h = Hash.new
f = File.open(the_file, "r")
f.each_line { |line|
    words = line.split
    words.each { |w|
       if h.has_key?(w)
           h[w] = h[w] + 1
       else
           h[w] = 1
       end
    }
}

h.sort{|a,b| a[1]<=>b[1]}.each { |elem|
    puts "\"#{elem[0]}\" has #{elem[1]} occurrences"
}
</pre>

[Doris](/projects/doris) Syntax Sandbox
==============
~~~

#
# binds
#

var _foo  <- an_atom
var __bar <- (a listof 2 stuff)

#
# functions
#

fn fun <-
"Some docstring"
{
   for e in range( 1 10)
       out( blah crlf)
   end
}

fn fun2 <-
"Some docstring"
{| _arg1 _arg2 |

   for( _e in range( 1 10))
       out( _arg1 " " _arg2 crlf)
   end
}

#
# rules
#

rule rl @lhs
"Doc string"
   (_f1 <- [fac _f]
    _b1 <- [bac _b])
end

rule rl @rhs <-
"Doc string"
{|_f1 _f2 _f _b|
   out( found " " _f and _b)
   retract( _f1 _b1)
}

assert( [fac 123])

facts blee <-
([fac 234]
 [bac abc]
 [fac 345])

assert( blee)

match()

#
# objects
#
class Pip isa( A B)
"Docstring"
   hasa _prop1
   hasa _prop2
end

method meth of Pip <-
"Docstring"
{|_arg1 _arg2|
   out( "In meth" crlf)
}

method @around meth of Pip <-
"Docstring"
{|_arg1 _arg2|
   out( "Around meth" crlf)
}

var _p <- new Pip

#
# modules
#

module Moop
   imports Pip
   exports Blee
end

fn Moop::Chee
"docstring"
{|_foo|
   out( tick " " tock " " _foo crlf)
}

Moop::Chee( dim)

#
# declaratations
#
for( @ever)
    out( whoa crlf)
end

fn test <-
"docstring"
{|_x @int _y @float|
    out( _x " " _y crlf)
}

fn @predicate test? <-
"predicate functions do not require parens for args"
{|_x|
   true
}

~~~

Giles Goatboy
=============
> skilled programmers can write better programmers than they can hire

Quotes
======
> Better train people and risk they leave - than do nothing and risk they stay.
> Why do we never have time to do it right, but always have time to do it over?

Math
====
[John von Neumann and the Origins of Modern Computing](http://www.amazon.com/exec/obidos/ASIN/0262011212/ref=nosim/fogus-20)

1.  Easier to learn math after learning programming
2.  Math is taught incorrectly
3.  Learn the *right kinds* of math
4.  Like programming, TISMTOWTDI.  Pick the way you like best

You have probably forgotten everything that you learned in high school... that's good.

Where to go
-----------
1.  Probability theory
2.  Statistics
3.  Algebra
4.  Linear Algebra
5.  Logic
6.  Information Theory
7.  Kolmogorov Complexity
8.  Combinatorics

-m
