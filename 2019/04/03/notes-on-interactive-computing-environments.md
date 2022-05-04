In the pantheon of my personal favorite books on computing, *[Interactive Programming Environments](https://www.amazon.com/Interactive-Programming-Environments-David-Barstow/dp/0070038856/?tag=fogus-20)* by Barstow[^bar], Shrobe, and Sandewall places somewhere in the proverbial chancel. 

<a href="https://www.amazon.com/Interactive-Programming-Environments-David-Barstow/dp/0070038856/?tag=fogus-20"><img src="http://blog.fogus.me/wp-content/uploads/2019/04/IPE-201x300.jpg" alt="" width="201" height="300" class="aligncenter size-medium wp-image-6549" /></a>

The book covers a wide range of topics from Lisp Machines, Smalltalk, Pascal, OOP, UNIX, AI, and intelligent agents but the common thread is this:

*Your programming environments should be an active partner in the act of creating systems.*

This post contains notes on some of the points along this vector with some jumping-off points for further exploration and research.

## Moore and Forth

*Programming a Problem-Oriented Language* by Charles Moore ([PDF](http://www.forth.org/POL.pdf)) is Chuck Moore's thesis on Forth and its design and core philosophy. The genesis of the language stems from a simple premise:

> I've written many programs over the years. I've tried to 
> write good programs, and I've observed the manner in which 
> I write them rather critically. My goal has been to decrease 
> the effort required and increase the quality produced.

Moore's motivations culminated in the Forth programming language, which in turn led to the term "problem-oriented-language." The basic idea is that a programming language should facilitate the construction of programs by allowing you to build in layers, where each layer is a specialized language for building the layer above it. Certainly the book dives deeper into matters such as simplicity, YAGNI, bootstrapping, and the like, but the core idea of *problem-orientation* is the key take away.

## Fundament

Moore's work encourages the creation of the entire programming stack, but there are varying levels at which an interactive environment can evolve up from. For example, one of my attempts at the IPE idea built on the Java Virtual Machine and its included graphical interface APIs. The idea was to build a tool that I could explore Java APIs with in an interactive manner. However, to do that I had to build a total environment for building scratchpads for exploration. 

<img src="http://blog.fogus.me/wp-content/uploads/2019/04/Screen-Shot-2019-04-02-at-1.38.02-PM-150x150.png" alt="" width="150" height="150" class="aligncenter size-thumbnail wp-image-6564" />

I got it into a good place eventually, but the increased capabilities of the [BlueJ](https://www.bluej.org) environment convinced me to end development. Later I worked on various IPEs hosted on platforms varying from [CLIPS](http://www.clipsrules.net), to [Emacs](https://github.com/alepharchives/ordoemacs), to [Emacs in CLIPS](https://web.archive.org/web/20130420043846/http://le-trombone.livejournal.com/182656.html), to JavaScript. I learned a lot along the way, but never felt that I had even scratched the surface of finding an environment that helped me to explore.

## Work for the next 10 years

Any work in IPEs is likely to require a huge amount of effort and brain-power to crack. Indeed, you could expect to spend the next 10-years just gaining a deep understanding of the problem space and contributing to the rich landscape. Even after the 10-years are spent the expected likelihood is that any artifacts created along the way are likely to find little to no usage or to be obviated by something else entirely. Enormous amounts of effort went into systems such as [Interlisp](http://www.softwarepreservation.org/projects/LISP/interlisp_family/), [Project Chandler](https://www.amazon.com/Dreaming-Code-Programmers-Transcendent-Software/dp/1400082471?tag=fogus-20), Backus' [FP](https://prog21.dadgum.com/14.html) [^dip], the [Data General Eclipse MV/8000](https://people.cs.clemson.edu/~mark/330/eagle.html), and [Xanadu](http://xanadu.com) and every one of them are relegated to the dustbin of computing history. It's difficult to start a project that most likely will merely provide footnotes to the next generation of proposed solutions.

So it goes.

[^dip]: [Alan Dipert](https://twitter.com/alandipert?lang=en) introduced me to the FP language and I've studied it on and off for the past few years. It never fails to surprise. His passion for topics such as I'm outlining in this post is/was infectious and he's probably forgotten more about the subject than I've ever known. 

## Bootstrapping 

[Smalltalk](https://www.amazon.com/Smalltalk-80-Interactive-Programming-Environment-Addison-Wesley/dp/0201113724/?tag=fogus-20) is a great example of a system that evolved via bootstrapping new iterations using previous versions of itself. Alan Kay talks about this as the ideal for Smalltalk development for much of its early life in his [famous OOPSLA 1997 talk](https://www.youtube.com/watch?v=oKg1hTOQXoY&t=3490s). The whole talk is an amazing repository of wisdom that hasn't yet been truly tapped for inspiration.

<img src="http://blog.fogus.me/wp-content/uploads/2019/04/TheBlueBook-227x300.jpg" alt="" width="227" height="300" class="aligncenter size-medium wp-image-6570" />

That said, while Smalltalk itself moved away from the meta-language bootstrapping model with the development of Smalltalk-80, the early principles are foundational. The seminal book *[Smalltalk-80: The Language and Its Implementation](https://www.amazon.com/Smalltalk-80-Language-Implementation-Adele-Goldberg/dp/0201113716/?tag=fogus-20)* captures the gestalt of the early days by including the source code for a [VM for the language described in the book using the language described in the book](http://www.mirandabanda.org/bluebook/). The ideal outlined by early Smalltalk implementations (and [Squeak](https://wiki.squeak.org/squeak)) is an ideal that IPEs should strive for. That is, the purpose of an Interactive Programming Environment should not be to provide a solution, but instead to provide a set of ideas useful for exploring higher-level ideas better than those provided in the base environment.

## Emacs

These days the closest thing that I have to a powerful IPE in my life is the Emacs text editor. In addition to my place for writing blog posts, Emacs is also my programming environment, idea scratchpad, TODO-list manager, a source control interface, and occasionally a [game system](https://www.masteringemacs.org/article/fun-games-in-emacs). Emacs is extended with the Emacs Lisp programming language and provides a set of abstractions around working with text buffers. The act of extending Emacs is a bit of black magic, but at least [one interesting manual for writing extensions](https://www.amazon.com/Writing-GNU-Emacs-Extensions-Customizations-ebook/dp/B0043M56SW?tag=fogus-20) has been written. 

## A total head-space

[Douglas Englebart's NLS](http://www.dougengelbart.org/content/view/155/87/) was a total collaborative environment meant to enable its users to engage in "knowledge work." The idea was to provide a repository for knowledge, tools for collaboration, idea capture, and discovery. However, NLS was not just a set of software tools, instead it was an environment that ensconced the knowledge worker. 

<img src="http://blog.fogus.me/wp-content/uploads/2019/04/enviboard-300x170.jpg" alt="" width="300" height="170" class="aligncenter size-medium wp-image-6574" />

From the now ubiquitous mouse to the chorded keyboard, the NLS workspace was designed as a phase-1 stage in moving towards human-machine co-evolution. Perhaps the ideal for IPEs is not just a software concern but instead a total headspace. There was a popular trend in software writing tools that promised immersive environments (see [Writeroom](http://www.hogbaysoftware.com/products/writeroom) and [FocusWriter](https://gottcode.org/focuswriter/) for examples), but they stopped at the screen. Perhaps something akin to [Dan Price's Hobbit home](https://www.youtube.com/watch?v=zdLAM-wChxY), a hideaway van, or perhaps [Bruce Hauman