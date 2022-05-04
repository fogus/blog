A few weeks ago I tweeted:

> Every programmer should create, at least once: OS, editor, database, roguelike, 
> interpreter, compiler and robot.
> 
> https://twitter.com/fogus/status/268736490836750337

Yesterday a lovely post was released by [an adventurous programmer Gusts Kaksis who hopes to create an operating system](http://gusc.lv/2012/11/im-writing-my-own-os/) from scratch.  Much to my chagrin, the discussion over at [Hacker News was quite negative](http://news.ycombinator.com/item?id=4833218).[^bas]  However, a nice comment was added to the thread that summarizes my thoughts exactly:

> Our discipline needs more people who have dared to write an OS from scratch, not fewer.
> 
> http://news.ycombinator.com/item?id=4834302

In fact, I wouldn't stop at the OS.  Instead I would advocate most, if not all, of the following:

## Operating system 

There is [no need to create an entire](http://fogus.me/thunks/osdev.html) operating system, but it would behoove us to learn more about the needs of a machine at its lowest levels.  Perhaps a kernel, some boot code, a HAL, a memory manager or process scheduler would suffice.  The point is that we should learn what it means to true operate at the level of bare metal.

## Text editor

Many programmers spend a vast majority of their time on the confines of some IDE of some sort.  Very often the IDE is nothing but a glorified text editor.  As a result we often [take our editing environments for granted](http://blog.fogus.me/2008/07/23/confessions-of-a-textual-loser/), but to create one from scratch covers a vast swath of computing topics.

## Database

Many of you have probably already created some sort of in-memory database for a project or ten, but those can hardly be called databases.  Why not try to create a database that provides efficient storage, querying and run time optimization techniques?

## Game

In my original tweet I mentioned a roguelike, but that was simply my own bias leaking out.  In reality the field of game development is cyclopean in its coverage so to create any specific type of game is likely to only scratch the surface of the programming depth required.  However, to pick a certain type of game and run with it is fun and provides a deeper appreciation for what it takes to bring something to market that requires technicality, an exquisite attention to detail and a strong design sense.

## Interpreter

It's not as important to design a new programming language as it to gain an appreciation of what it takes to build one.  The easiest first step in language creation is to build an interpreter...

## Compiler

The next step is to build a compiler.  I would advise starting with a simple Pascal variant requiring only a single compilation pass and then moving on to more complex languages with ambiguities in syntax and semantics.  For bonus points, throw in a VM and/or an assembler.

## Robot

My final suggestion is to actually build a robot.  This is similar to the OS task except even deeper in that the design and creation of hardware and software is required.  There is nothing like debugging with an oscilloscope, and doing so makes you wish for something as sophisticated as `printf`.  Performing the robot task can be a stand in for all of the other tasks if done properly, but really I view it as a culminating event.

What's the point of all this?  [Education and betterment, nothing more](http://jasonrudolph.com/blog/2011/08/09/programming-achievements-how-to-level-up-as-a-developer/).[^school]  The goals of a list such as this is *never* the artifacts, although if you create something amazing along the way then all the better for us!  

Does our industry need more text editors or operating systems?  That's debatable.[^debate]  However, our industry definitely needs more people willing to work hard to sharpen their craft, not fewer.

What do you think?  Is your list different?  Feel free to post a comment below or email me in my inbox at ![email](http://fogus.me/images/addy.png "").

:F

[^school]: In a perfect world we would have covered all of these topics in our undergrad computer science curriculum.

[^debate]: I fall on the side of the fence that says the world needs vastly more of each of the things mentioned.  Mountains more.

[^bas]: Don't let the bastards grind you down Gusts!