[Broccoli v0.1.2 released](https://sourceforge.net/projects/broccoli-lang/)

Thinking about Scala
====================
Scala at the core allows mutable and immutable objects based with the following observation:
>Objects can be mutable or immutable. The latter is preferred when it’s possible, since it needs no concurrency control. Also, these days it’s faster to make new objects and allow them to be efficiently GC’ed, than incur the GC overhead

Incurring GC overhead
*It is important that I understand what this means.*

1.  Traits 

    are like interfaces **except** they can provide default method impls.  mixin capability
    
2.  Singletons
 
    are used instead of statics

3.  Actors

    used to provide async messaging

Scala has two *interesting* things:
1. Implicits 
Once again we are bashed over the head with globals and side-effects.

2. Pattern matching (algebraic types)

> But the more powerful you make a type system, the more you run into hard-to-understand stuff at the edges, and if you make it even more powerful, the edges start moving in toward the center.


While reading [Steve Yegge's thoughts on dynamic languages][yegge1]
====================================================================
> C++you've got your virtual method dispatch, which is what C++ you know, sort of evangelists, that's the first thing they go after, like in an interview, "tell me how a virtual method table works!" Right? Out of all the features in C++, they care a lot about that one, because it's the one they have to pay for at run time, and it drives them nuts! It drives them nuts because the compiler doesn't know, at run time, the receiver's type.

> If you call foo.bar(), foo could be some class that C++ knows about, or it could be some class that got loaded in afterwards. And so it winds up this polymorphism winds up meaning the compiler can compile both the caller and the callee, but it can't compile them together. So you get all the overhead of a function call. Plus, you know, the method lookup. Which is more than just the instructions involved. You're also blowing your instruction cache, and you're messing with all these, potentially, code optimizations that could be happening if it were one basic-block fall-through.

> So what he (Urs) does, is he has these counters at hot spots in the code, in the VM. And they come in and they check the types of the arguments (or operands). And they say, all right, it looks like a bunch of them appear to be class B, where we thought it might be class A.

> So what we're gonna do is generate this fall-through code that says, all right, if it's a B Ð so they have to put the guard instruction in there; it has to be correct: it has to handle the case where they're wrong, OK? But they can make the guard instruction very, very fast, effectively one instruction, depending on how you do it. You can compare the address of the intended method, or you can maybe do a type-tag comparison. There are different ways to do it, but it's fast, and more importantly, if it's right, which it is 80-90% of the time, it falls through (i.e., inlines the method for that type - Ed.), which means you maintain your processor pipeline and all that stuff.

> The syntax of a language, unless it's Scheme, gives you a lot of clues about the semantics, right? That's actually the one place, maybe, where lots of syntax actually wins out (over Scheme).

> So javac, the Java compiler: what does it do? Well, it generates bytecode, does some optimizations presumably, and maybe tells you some errors. And then you ship it off to the JVM. And what happens to that bytecode? First thing that happens is they build a tree out of it, because the bytecode verifier has to go in and make sure you're not doing anything (illegal). And of course you can't do it from a stream of bytes: it has to build a usable representation. So it effectively rebuilds the source code that you went to all that effort to put into bytecode.

> But that's not the end of it, because maybe javac did some optimizations, using the old Dragon Book. Maybe it did some constant propagation, maybe it did some loop unrolling, whatever.

> The next thing that happens in the JVM is the JIT undoes all the optimizations! Why? So it can do better ones because it has runtime information. ... So it undoes all the work that javac did, except maybe tell you that you had a parse error.

> And to us, C++ was the ultimate in Roman decadence. I mean, it was equivalent to going and vomiting so you could eat more.

> The problem is, picture an ant walking across your garage floor, trying to make a straight line of it. It ain't gonna make a straight line. And you know this because you have perspective. You can see the ant walking around, going hee hee hee, look at him locally optimize for that rock, and now he's going off this way, right?

Interesting Langdev Papers
===========================
http://www.ics.uci.edu/%7Efranz/Site/pubs-pdf/C44Prepub.pdf
http://www.ics.uci.edu/%7Efranz/Site/pubs-pdf/ICS-TR-07-10.pdf
http://research.sun.com/self/papers/pldi94.ps.gz
http://homepages.inf.ed.ac.uk/wadler/papers/essence/essence.ps
http://en.wikipedia.org/wiki/Covariance_and_contravariance_%28computer_science%29

> writing programs is a medium of expression which doesn't necessarily map to anything other than a programming language, and probably isn't easier in anything other than a programming language.

Question about CFront
======================
What was so special about its error messages?

JVM Languages
=============
1. Rhino
2. JRuby
3. Jython
4. Clojure
5. Groovy
6. Scala
7. [Broccoli][broccoli] (TBD)

On Javascript
=============

There are some very nice things about js and some very bad things:

## Good
1.  JSON
2.  lambda *(js is the first mainstream language with this language feature)*
3.  Functional nature
4.  Object literals
5.  Objects as containers
6.  No casting needed, the type system is dynamic
7.  Supports light, minimal, and shallow coding
    + On the other hand, Java fosters extremely deep type hierarchies
8.  Standardized

## Bad
1.  Overloaded + to mean addition and concatenation
2.  Automatic semicolon injection
3.  Objects as containers are not quite useable as general purpose containers
4.  Global vars as method of linkage for compilation units
5.  Variables without `var` in front are global by default!
6.  Globals in a file and DOM are readable by any other js file that includes it
7.  `with` statement wants to be dynamic in a static system
8.  Very cluttered
9.  Standardized

## References
Paper by Ivan Pratt.

[jslint][jslint] 
> jslint will hurt your feelings

Static vs. Dynamic vs. Strong vs. Weak vs. Duck vs. Structural
===============================================================
It's that time of the year where I refresh on some basic computer science topics.

## Static typing
*examples: c, c++, c#, java*

Static languages require variable type definitions prior to their usage.  These type definitions are checked at *compile time*.  The type of an entity resides in the variable.

## Dynamic typing
*examples: php, lisp, [Broccoli][broccoli], javascript, perl, python, ruby, scheme, and smalltalk*

Dynamic languages apply type definitions as needed.  These type definitions are checked at *run time*.  The type of an entity resides in the value.

## Strong typing
Claims that a variables and operations can hold only one type of variable and only one type.  There are no implicit conversions.

### How to break strong typing
-   Most static languages has some form of casting, which throws the guarantees of static typing out the window -- buying all of the dangers of dynamic typing, with none of the benefits.  
-   Automatic type conversion
-   Variadic functions (???)
-   Union types

## Weak typing
Claims that variables and operations can hold compatible types in addition to the specific type.

## Duck typing
An object is equivalent to another if the relevant pieces are the same.  That is, there is no need to check if object A is of type X; if A can perform A.quack() at runtime, then it is considered equivalent.

## Structural typing
