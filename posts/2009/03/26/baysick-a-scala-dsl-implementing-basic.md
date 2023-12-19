*This post was featured on the [Scala](http://www.scala-lang.org) website.  It is advised to [jump there first](http://www.scala-lang.org/node/1403) and then sneak back here ... eventually.*

A couple weeks back I came across a post on creating the [simplest possible BASIC DSL written in Scala][jachim] by Szymon Jachim.  The implementation itself was dead simple to understand, but I suspected it was due to the fact that it only provided `GOTO` and `PRINT`.  The title of the post was *ease of making DSLs in Scala - nobody cares*, which was unfortunate because I think that many people care, including myself, so I took it upon myself to extend the DSL to be a pseudo-implementation of [Tiny BASIC][tb].  In a nutshell, I found that it was simple to create an internal DSL providing a simple dialect of BASIC using Scala.  There were some sticking points, which I will touch on later, but overall it was a joy.  The [code is available][baysick] for public consumption, so feel free to try it, criticize it, and/or improve it.  I find it retains its simplicity even after extending the original to provide additional forms and functions.

Here is a simple [Lunar Lander][lunar] game written in Baysick.

[sourcecode lang="bas" gist="122994"]
object Lunar extends Baysick {
  def main(args:Array[String]) = {
    10 PRINT "Welcome to Baysick Lunar Lander v0.9"
    20 LET ('dist := 100)
    30 LET ('v := 1)
    40 LET ('fuel := 1000)
    50 LET ('mass := 1000)

    60 PRINT "You are drifting towards the moon."
    70 PRINT "You must decide how much fuel to burn."
    80 PRINT "To accelerate enter a positive number"
    90 PRINT "To decelerate a negative"

    100 PRINT "Distance " % 'dist % "km, " % "Velocity " % 'v % "km/s, " % "Fuel " % 'fuel
    110 INPUT 'burn
    120 IF ABS('burn) <= 'fuel THEN 150
    130 PRINT "You don't have that much fuel"
    140 GOTO 100
    150 LET ('v := 'v + 'burn * 10 / ('fuel + 'mass))
    160 LET ('fuel := 'fuel - ABS('burn))
    170 LET ('dist := 'dist - 'v)
    180 IF 'dist > 0 THEN 100
    190 PRINT "You have hit the surface"
    200 IF 'v < 3 THEN 240
    210 PRINT "Hit surface too fast (" % 'v % ")km/s"
    220 PRINT "You Crashed!"
    230 GOTO 250
    240 PRINT "Well done"

    250 END

    RUN
  }
}
[/sourcecode]

Notes
======
1.  Variables are denoted with the single quote which corresponds to Scala's symbol literals
2.  The assignment operator is the Pascal-like `:=`
3.  The append operator for the `PRINT` form is `%`
4.  Only integer math is supported
5.  Strings cannot (currently) be compared using the relational operators
6.  Usage of Scala code within the BASIC forms is not fully supported
7.  There are currently only 6 math functions: `ABS` `SQRT` `+` `-` `*` and `/`

Issues
=======
1.  As has been mentioned by innumerable programmers advocating (and deriding) DSLs, you are often constrained by the host language in the grammar, error reporting, and hosting capabilities of your DSL.  While Scala allows you to support bizarre grammars without significantly increasing the complexity, it does not necessarily facilitate a simple way to provide solid error reporting and hosting.  This is not necessarily a limitation of Scala per se, but instead for general purpose languages.  In order to effectively build a wide range of DSLs divorced from their underlying host we need a language that is especially geared toward building DSLs.  The two important language features that Scala provides that makes it especially conducive for DSLs are [operator notation][on] and [implicits][implicit].  Usually, a language is considered DSL-friendly if it allows you to omit the dot on a method call, but a really powerful DSL metalanguage would have to go much much further than that.

2.  For some strange reason I could not coax Scala into allowing my `LET` forms to be parenthesis free.  I assume that it is an error on my part, but I was never able to solve it before my artificial deadline expired.  

Todo
=====
1.  A subroutine facility via `GOSUB` and `RETURN`
2.  Allow strings to be compared using the relation operators

So there you have it -- Baysick: A Scala DSL Implementing BASIC.

-m

[jachim]: http://www.nabble.com/-scala--ease-of-making-DSLs-in-Scala---nobody-cares-to22450638.html#a22450638
[tb]: http://en.wikipedia.org/wiki/Tiny_BASIC_programming_language
[lunar]: http://en.wikipedia.org/wiki/Lunar_Lander_(computer_game)#Lunar_Lander_.281971.29
[baysick]: http://github.com/fogus/baysick/tree/master
[on]: http://www.artima.com/weblogs/viewpost.jsp?thread=251945
[implicit]: http://lalitpant.blogspot.com/2008/08/scala-implicits-dose-of-magic-part-1.html