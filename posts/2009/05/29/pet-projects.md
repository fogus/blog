*this is another entry in a series on [programmer enrichment](http://blog.fogus.me/tag/enrichment)*

Most programmers looking to improve their craft are enthusiastic about learning new programming languages.  I am a huge proponent of polyglot programming practices and as a result I attack literature, code samples, and discussions regarding interesting programming languages and dialects with a gusto unmatched by any other hobby in my life (save for MST3K).  I would like to take a moment to clarify my previous sentence.  That is, it's not fair to qualify any particular programming language with the term *interesting* because in fact

> all programming languages are interesting in *some* way

That being said, I have accumulated a set of pet projects that I tend to cycle through when learning new languages.  Each pet project scratches a certain itch and each requires differing approaches for solving.  When learning a new language I like to solve two or more of these pet projects in order to get a feel for the capabilities and shortcomings of said language.  The current set of projects is as follows.

Lisp Interpreter
-----------------
The idea of creating an interpreter for a subset of Lisp has only recently become [a member](http://github.com/fogus/lithp/tree) of my pet projects.  I previously shied away from creating a Lisp because it has become incredibly ubiquitous and it seemed silly to throw Y.A.L.I. (**Y**et **A**nother **L**isp **I**nterpreter) into the already over-crowded landscape.  However, upon further reflection on the matter I realized 

> the best way to learn two programming languages concurrently is to write an interpreter or compiler of one using the other.  However, it's highly probable that you will gain a deeper understanding of the implemented language rather than the implementation language.

It's not accurate to label this particular pet project a Lisp interpreter because in all likelihood I will instead write one for a wholly different language in order to more effectively reap the 2-1 benefits in the future.

Lexer Generator
----------------
With outstanding tools like [JavaCC](https://javacc.dev.java.net), [Lex](http://dinosaur.compilertools.net), [Antlr](http://www.antlr.org), and [Flex](http://flex.sourceforge.net) it seems unlikely that one could further the current understanding of scanner generator techniques.  However, it's useful to note 

> your own set of pet projects should not attempt to be groundbreaking but instead work to further your own understanding of their constituent parts and how they are implemented using your chosen target language

If you manage to do something truly innovative, then kudos to you.  However, I like to maintain a set of projects solving well-known problems; that way the bulk of the implementation activity is spent trying to solve the problem using the target language idioms and not in research and design.

Knuth's Pac-Land
----------------
Pac-Land is essentially Conway's Game of Life on steroids.  It's a great project for targeting languages facilitating highly concurrent programs.

Linear Algebra Library
----------------------
I do this mainly to refresh my mathematical muscles every couple of years.  This brings us to another principle one should adhere to with a set of pet projects

> It's not always exclusively about honing your programming skills.  In the world of pet projects, side-effects are a good thing.

Workflow Engine
---------------
This project is mainly an attempt to finally get it right... next time.

m-ary Tree
-----------
It's a good idea to incorporate a data structures project into your set.  An [m-ary tree](http://www.brpreiss.com/books/opus5/html/page257.html) is as good as any and oft times better given that you can do additional experimentation on the effects of: 
1. Differing values for m
2. Immutability
3. Generic implementation

And to boot, it's always fun to implement the searching and sorting functions for the tree.

Now Do This
-----------
The most useful tool that I've come across in the past 3 years is Jakob Lodwick's [Now Do This](http://nowdothis.com) TODO application.  It's so simple in its perfection it almost hurts me physically that I didn't think of it first.  In any case, it's a great little app that helps to exercise UI capabilities of a target programming language.

Project Scaffolding Generator
-----------------------------
This is a bit more involved and to be honest I haven't done it in a while since I last implemented it to learn Python, but it's a good (and practical) project.  And that leads us to another principle

> Don't worry about practicality when choosing your own pet projects, but with all other things being equal favor it. 

Gap Buffer
----------
Long ago I set out to create [my own text editor](http://blog.fogus.me/2008/07/23/confessions-of-a-textual-loser/).  However, after spending many hours on the task I realized that it was unlikely that my editor would be any better than hundreds of other editors.  Therefore, I decided to call it quits after finally getting to the point of having a usable (albeit lame) text editor.  That is not to say that my effort was wasted as I learned a lot, which is the foremost [^fun] point of any pet programming project.  The most intriguing part was of course the runtime text storage mechanism implemented as a [gap buffer](http://en.wikipedia.org/wiki/Gap_buffer).  I will occasionally feel nostalgic for my old gap buffer (I called it Gappy) and have previously reimplemented it a couple of times, although it's been a while.

Next generation projects
========================
Pet projects have come and gone and those listed above are no different.  That being the case, I have considered adding a couple additional projects as listed below.

Twitter client
--------------
Creating a Twitter client has crossed my mind, but in all likelihood by the time I feel motivated to do one there will be millions of others available for study.

> You are not required to always write code.  Instead, try supplementing the creation of pet projects with the study of existing implementations of your pet projects in your target language(s).

Project Euler
-------------
I like the idea of [Project Euler](http://projecteuler.net) but I simply cannot personally find any enthusiasm for attempting the problems, which of course brings one additional principle that you absolutely **must** adhere to

> You can forget every other principle -- as long as you have fun

What are your pet projects?

-m

[^fun]: Even when it turns out to be not very fun.
