[Justin Balthrop](http://ninjudd.com/) is the author of [Cake](http://clojure-cake.org) (the Clojure build tool, not the spongy delicious treat).  He works for [Geni](http://www.geni.com) and is an alumni of The University of New Mexico[^nm].  Interestingly, Justin (or ninjudd as he is known on the Intertubes) is the only person I've ever met who looks just like his/her online images.

[^nm]: Having never visited New Mexico I have grand visions of sweeping desert landscapes, crags, and cacti -- you know, like that picture on the side of my neighbor's van when I was a kid.  Remember that one?

### What led you to Clojure?

I was obsessed with Logo in fourth grade. The other kids wanted to play Oregon Trail, and
I wanted to tell the turtle how to draw things. The fact that the drawing commands were
relative to the turtle's current position made a lot of sense to me. It's so different
from the way a human draws. Like the difference between looking at a maze on paper and
actually being inside a maze.

After that, I didn't see Lisp again until my second year in college. I almost gave up on
Computer Science after the first C++ class, but someone in one of my math classes
convinced me to take "Non-Imperative Programming". It was all about Scheme, and I was
obsessed immediately. The professor, [Lance Williams](http://www.cs.unm.edu/~williams),
taught the class using Emacs instead of a chalk board. He wrote programs and ran them in
class, debugging and refactoring as he went. There was something magical about the REPL
and the way it let you develop software. I think I was more comfortable in Scheme after
one day than C++ after an entire semester.

Since I didn't have another functional programming class for two years, I applied to be a
teaching assistant for the Scheme class so I could keep doing it. There's nothing better
than the look on a person's face when functional programming starts making sense to them.
After that, I started reading [SICP](http://mitpress.mit.edu/sicp) and [ANSI Common Lisp](http://www.amazon.com/o/asin/0133708756?tag=fogus-20). Paul Graham's philosophy of
[building programs from the bottom up](http://www.paulgraham.com/progbot.html) quickly overtook the UML diagrams and requirements
documents of traditional software engineering in my head.

Over the next eight years, I ended up doing C++, a bit of Java, then Perl and Ruby. I
didn't think I could make money programming Lisp, so I settled on Ruby, appeasing myself
with the block syntax instead of macros and making my coworkers uncomfortable with too
many lambdas. I tried to write functional Ruby code, but it was always a struggle. I once
had to change a purely functional library to be impure because the calls to clone were too
expensive without persistent data structures. It seemed like a small trade-off for
performance at the time, but using mutable data structures allowed a bunch of nasty bugs
to creep in since then. Someone should volunteer to implement persistent data structures
for Ruby as a community service...

In the end, I happened upon Clojure by accident. We were in the process of looking for a
faster language than Ruby for our graph traversal algorithms at Geni. We'd narrowed the
choice down to Java, C++ or Erlang, when I saw a forum post comparing Clojure to Erlang
that claimed Clojure could be as fast as Java with the right optimizations. I built a
prototype of our graph in Clojure that was 10-20 times faster than our Ruby version, and
I've been doing Clojure full-time ever since.

### What is Cake and why is it important? 

Cake is the build tool that we didn't mean to write. We spent a heroic amount of time
trying to use existing tools, but in the end, it came down to two things: 1) we wanted to
write our builds in Clojure, not XML and 2) our builds were too complicated to do in
[Leiningen](https://github.com/technomancy/leiningen). This was before lein had hooks, and the first version of Cake was actually
a proof of concept I wrote to show how I thought hooks should work in Leiningen.
Then [Lance Bradley](http://twitter.com/lancepantz) added some basic tasks and all of a
sudden we had a build tool.

I was pretty smitten by Martin Fowler's [rake article](http://martinfowler.com/articles/rake.html)
when I started programming Ruby, and it convinced me that dependency-based programming was
the way to go for a build tool. Don't get me wrong, functions are great. All but the most
simple tasks in Cake are written as functions internally. But when you're dealing with a
bunch of interconnected actions that by definition have side-effects, there is a lot of
incidental complexity that dependency-based programming deals with for you. This is
especially true when users need to be able to extend and modify built-in tasks.

The other really exciting feature of Cake is the persistent JVM. I like the fast startup
it provides, but aside from that, a shared, persistent process just makes sense for a
language with concurrency primitives like Clojure. I love to open up multiple REPLs on the
same JVM and experiment with concurrent code interactively. Or to open a REPL while a
migration is running to check on the status in realtime. This is something Swank users
have been able to experience for a long time, but Cake brings this to everyone.

Cake's core philosophy is that tasks should be easy to write. In the Ruby world,
developers end up writing a lot of tasks because Rake makes it so easy, and that's a good
thing. It was quite a shock when I first came to Clojure and started using Ant. My
build.xml files quickly grew to hundreds of lines. I found myself copying XML between
projects, and I had to write Java if I wanted reusable tasks.

Programmers don't want to think about their build tool. They don't want to learn a new
language to customize their build, and most programmers don't want the complexity hidden
behind their IDE either. Programmers want a build tool that is powerful when they need it,
but most of the time, they just want to open up a single file, add a few lines of code,
and get on with the rest of their work.


### Why does Aquaman get such a bad rap?

If you look into it, you'll find that almost every person who thinks Aquaman is lame
watched *Super Friends* when they were a kid. Other than *Super Friends*, Aquaman was actually
pretty awesome. The question we should be asking is: Who hated Aquaman so much that they
created an anti-Aquaman propaganda machine and dressed it up as a children's TV show? [^aqm]

[^aqm]: I've never considered this before -- it's so insidious that it must be true.  Chupacabra!

### How does the perception of TDD between the Ruby and Clojure communities differ?

I never really believed in test driven development, even when I was in the midst of
it. The biggest problem is that TDD makes it too easy to write bad tests&ndash; tests that do
more harm than good, like the too-brittle functional test that breaks every time you
change anything, or the almost useless unit test that stubs so many interactions that it
keeps passing even when everything is totally broken. I've wasted too many days fighting
with tests like these.

The hard part about software is writing good abstractions, and tests are never going to
solve that part for you. It seems too many programmers in the test-driven world write
tests before they even understand the problem. It's really hard to write good tests this
way. I'm still a big believer in jumping in and starting to program to figure out your
problem. But this only works if you're willing to throw away the code that isn't any good,
and maybe people are less likely to do that if they already have a full suite of passing
tests.

Here's the thing: tests are incredibly important for making sure your implementation does
what you designed it to do, but they are deceptively hard to write well. The best tests
are the ones that are written out of necessity rather than habit. The best tests are the
ones written after you've thought hard about your problem and come up with a good
design. If tests are driving development, then tests are also driving design, and tests
