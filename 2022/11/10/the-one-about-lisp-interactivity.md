Lisp REPLs are on the collective lips of the WWW lately and so I thought I'd add my little bit of chatter to the mix as well.[^fifteen]

For example, David Vujic wrote a post that fell victim to [common misconceptions about what a REPL is](https://davidvujic.blogspot.com/2022/08/joyful-python-with-repl.html). Slava Pestov also joined the fray when he tweeted about the [benefits of interactivity in dynamic programming languages](https://twitter.com/slava_pestov/status/1587809146010755075). Conversely, [Chas Emerick at ocaml.org](https://discuss.ocaml.org/t/whats-your-development-workflow/10358/8?u=cemerick) discussed a contrasting opinion on the fitness of OCaml versus a Clojure development process. However, my favorite post on the subject of Lisp REPLs is from Lisp luminary Mikel Evins who wrote a very good post titled *[On REPL-driven Development](https://mikelevins.github.io/posts/2020-12-18-repl-driven/)*, describing his subjective view on the way that REPL-driven development fits his development mindset. Of particular note was his beautifully phrased description of the fundamental mode of development in Lisp[^st] systems:

> What they (i.e. Lisp systems) have is a language and runtime system that are designed from the ground 
> up with the assumption that you’re going to develop programs by starting the language engine and 
> talking to it, teaching it how to be your program interactively, by changing it while it runs.

Evins' statement nicely matches the reason that I've always found Lisp languages a fit for my own mind -- the act of "collaboration" with my programming language works as a lens that I might view computing problems. That said, I'd like to use this post to add some nuance to the ongoing (and perpetual) REPL discussions by attempting to remove myself from the equation and instead build the discussion on a more solid foundation than feelings.

The term "interactive" is the key term in this post, even though it comes with a lot of baggage, and Evins hints at what it means in the context of Lisp. To try and hammer the point home, I'd like to compare the Lisp programming experience with that of Java's. The reason that I've chosen Java is because I'm more familiar with it than most other languages, but there are many others that could act as stand-ins for this choice.

[^fifteen]: Above what already said during the [Clojure 15th anniversary panel discussion](https://www.youtube.com/watch?v=exSRG-iL74Q).

[^st]: And also Smalltalk systems, which I won't talk about today, but much of what I write about Lisp development would also apply to it.

## What problems are the development environments trying to solve?

The features in any given language IDE exist to address problems with the nature of the language itself or to address the problem of best leveraging the programming language's capabilities. Taking this view, what are the problems addressed by Java development environments vs Lisp environments?

#### Java

Java IDEs provide a powerful lever for Java programmers because they[^jide] address two very specific problems: catching errors early and managing object-oriented sprawl.

##### Catch (a very few, non-conception) errors early

Most Java IDEs provide live type-checking and offer completion information to help cut down on typing and ... um... typing errors. Additionally, many Java IDEs have a code analysis function that can also detect a few potentially problematic patterns of usage such as missing guards around possible `null` values, un-thrown throwables, redundant initializers, etc.

##### Manage OO sprawl

Java class hierarchies can become deeply layered and broad and most Java IDEs attempt to provide ways to visualize and navigate them. Java as a typed language benefits from one of the strengths of types in that they provide code documentation. With this benefit in mind, most Java IDEs can navigate the system types in a pseudo-hyperlinked fashion. For programmers versed in their Java IDEs, the ability to navigate via types is powerful.

[^jide]: I'm speaking to the most prominent Java IDEs: Eclipse, IntelliJ, and NetBeans, but most others attempt to address the same problems.

#### Lisp

Lisp development environments on the other hand serve a very different problem. Specifically, Lisp IDEs, with the REPL at their core **help developers solve problems by keeping them connected to their program** -- that's it -- but a usable definition of "interactive" falls out of that. My friend Chas Emerick, one time Clojure programmer someone whose opinions I value very highly, stated his doubts on this model when he recently posted the following:

> However, appeals to how e.g. lisps provide some kind of unique advantage in interactive development are wildly overstated (and I say this as a former advocate of such practices).
> -- [Chas Emerick at ocaml.org](https://discuss.ocaml.org/t/whats-your-development-workflow/10358/8?u=cemerick)

He framed his critique in comparison to OCaml and since I don't have a strong grasp on that particular language nor its ecosystem I can't rightly critique his take. However, I'll paraphrase his list of OCaml benefits instead:

- OCaml's compiler toolchain is very fast
- OCaml build systems are also fast
- Ocaml dev tooling provides fast (enough) type hints, completions, go-to definitions, and the like

This list echoes the benefits listed earlier in this post regarding using a Java IDE. Chas outlined helpful set of tactics and tools for development that helps him  write high-powered systems in OCaml with a denouement stating:

> At the end of the day, compared with Clojure/ClojureScript/Racket/CL/etc, I end up with the same kind of feeling of having a conversation with my program and the compiler...

This sounds great for OCaml programmers, and if I were one then I too would have adopted Chas' process and tools[^chas] for my own needs. However, as a Clojure programmer I value the ability to interact not with my compiler, but instead have a conversation with my running program. As a Lisp programmer I want and expect my dev environment to allow me to do the following:

- maintain aggregate program state from one change to the next
- not rely on an external program state's information (e.g. a static analysis DB) or transient processes for program truth
- interact with my runtime code in a way that's indistinguishable from connecting to a runtime production system (and to do the second part as well when needed)

Most Lisp programming environments provide all of these benefits because the nature of Lisp languages foster them at the language level -- namely that Lisps provide REPLs as a matter of course. REPLs provide interactivity in the purest sense.

[^chas]: I do not want to call out Chas in any negative way. As I mentioned, I value his opinions as highly as anyone else, and indeed if he's this excited about OCaml then it behoves me to take the time to explore more about it to learn why!

## What's the development model of the program under development?

> The program is a database, not a listing.
> -- an old Xerox Parc slogan

Now that I've discussed the fundamental problem that Java and Lisp development IDEs try to address, let me take some time to discuss the differences in the model of the program under development in these development environments.

#### Java

Java IDEs almost universally view the program under development in terms of **files** and one or more **static analysis databases**.[^bluej] Java source listings are merely textual and interacted with using a textual paradigm. Java IDEs store any additional information about the program in a static analysis database that is separate from, and almost entirely inaccessible to the program itself. Indeed, the only context in which the running program has access to the static information is in the transient debugging context. As mentioned above, the capabilities that fall out of the static analysis is a powerful lever for Java developers, but the model is fundamentally different than a Lisp model.

[^bluej]: One environment that stands out in its differences from this model is the [BlueJ](https://www.bluej.org/about.html) IDE which takes an instances-centric view of a program but having never used it in anger, it's unclear to me how one would build large systems using this model.

#### Lisp

Lisp development environments on the other hand present a very different model at development time. Certainly there is a textual representation of a Lisp program that often mirrors the form and function of the runtime system. However, Lisp systems reify the components of the textual system in the running program itself. For example, Clojure stores a function in a Var that itself exists in a namespace instance.[^packages] These vars and namespaces are directly accessible to the running program so in effect there is no difference between the runtime, debug, and development time models. That is, the same mechanism for querying, creating, and modifying the reified elements are available at runtime and development time -- there simply is no separation.[^strt] If you wish to access a snapshot of the running state of a Clojure system then you need only to directly access the vars and namespaces that hold said state.

[^packages]: In Common Lisp the same may be said of packages and symbolic mappings.

[^strt]: In Smalltalk there's a similar lack of separation, but even less so.

## What's the development interaction?

The next dimension that I'll discuss is that of the development interaction, that is how a programmer interacts with the program under development.

#### Java

Developing a Java application is a linear and sequential process. That is, a programmer starts with nothing and then opens an editor and writes some code. At this point there is still no system, so the programmer needs to compile the textual source to generate a set of class files. Even still, there is no system to speak of, so those compilation artifacts need to execute within the context of a Java runtime. Further still, you often you then need to bundle the compiled class files with other resources into JAR artifacts for deployment and yet you still do not have a system. Only once you load these artifacts into a JVM you finally have a system.[^src] Another path for realizing a system is during debugging, but that is a transient phase and certainly no one runs production systems that way. You then repeat these steps indefinitely, or until the running system meets (or doesn't) some criteria that's considered "complete" thus ending the development phase.[^maintenance] The idea that the development process ends is a key limitation of Java development and one that Lisp systems are not subject to.

[^maintenance]: Glossing over the debate whether a maintenance phase is also a development phase.

[^src]: By contrast, Lisp system can and often do run as source, with compilation steps happening at runtime.

#### Lisp

Lisp development on the other hand is a non-linear and incremental process. Indeed, when starting to develop a Lisp system you immediately start with a runtime before you do anything else. A running Lisp REPL bursts with possibility and as Mikel Evins states in his post, is "taught" how to be your system interactively. A Lisp system is always simultaneously and **continuously** in the development, debugging, and runtime phases.

## What's the experimentation model?

It's worth talking briefly about the experimentation model supported by both Java and Lisp development environments and tease out the fundamental differences therein.

#### Java

Experimentation in the Java development process exists as test code, which is a separate program from the system under development. As a separate program, the tests need to run independently of the system, often using some subset of the system supported by mocked functionality, and the results of the run inspected afterwards. Additionally, the tests are code and forever require maintenance for the lifetime of their relevance to the system under development.

#### Lisp

On the other hand, there is no difference between experimentation code and the system code in a Lisp development environment -- experimentation programming is runtime programming. There is no need to create a separate test program when experimenting with a Lisp system under development.[^test]  Instead, you simply write code that runs experiments on the system itself during development.

[^test]: That said, it's often useful to extract your experimentation code into your test suite when creating systems.

## What's the system information model?

The final property that I'll discuss is the information model of the system itself. An information model is the underlying capability to inspect the state of a system to reason about its behavior and health.

#### Java

In a Java system there is an information model, but it endures only within the confines of an IDE’s debugging sessions and indexing DB. At runtime, reflection capabilities exist to allow inspection according to the vagaries of the existing class hierarchies, however it does not allow extension. Java systems require that you bolt a robust information model onto the system code in order to achieve higher degrees of inspectability. In practice, Java systems rely on intensive logging in order to piece together the state of a system – and as we know, information gleaned from the logs is often too little too late.

#### Lisp

A Lisp system information model is by contrast the very program itself. Inspection is available directly in the program by the language and user functions[^rrepl] Because the information model is directly represented in the program itself, the model is enduring and not predicated on special runtime modes and the like.[^llogs]

[^rrepl]: This is a more nuanced idea than a remote REPL connection into a running program, although that too is a powerful feature available to Lisp systems.

[^llogs]: This does not obviate the need for logging at runtime; instead logging is complementary to the information model already available to Lisp programmers.

## Conclusion

In conclusion, it's worth enumerating the differences between Lisp and other programming models, but as these things go it's difficult to understand the power of a continuous development process available to most Lisps without getting your hands dirty (so to speak). That said, let me take a moment to enumerate the high-level points as a pseudo-cheatsheet for understanding the differences. First, it's worth circling back to the meaning of the word "interactive" as used to describe Lisp development:

* interactive != person interacting with computer
* interactive != person interacting with a compiler/interpreter
* *interactive == person interacting with running program*

Also in this post I talked about the problem that languages like Lisp attempt to solve in comparison to Java. It's worth noting that I've used the word "problem" in a more nuanced way than just "writing programs" but instead have attempted to scope its meaning relative to the strengths and complexities inherent in the languages themselves. 

|                                        | Java                                                                                            | Lisp                                                                                                      |
|----------------------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| What problem are they trying to solve? | Catch (a very few, non-conceptual) errors early & manage OO sprawl                              | Help developers solve problems by keeping<br>them connected to their program                              |


Finally, I walked through the definitions of a few keys terms and their realizations in the development and runtime environments of both Lisp and Java systems, each summarized below:

|                                        | Java                                                                                            | Lisp                                                                                                      |
|----------------------------------------|-------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------|
| Development Model                      | Files + static analysis<br>Largely inaccessible to the program and IDE other than via debugging | Reified in the program: vars, namespaces<br>Accessible to the program                                     |
| Development Interaction                | Linear / Sequential<br>Edit, compile, link, test, run/debug<br>It ends                          | Non-linear / Incremental<br>Have a runtime from the start<br>Continuous                                   |
| Experimentation Model                  | Make a test program<br>Run it<br>Look at the results                                            | No difference<br>Experimentation programming is programming<br>Direct use without creating a test program |
| System Information Model               | There isn't one<br>What a debugger provides is ephemeral                                        | The running program<br>Enduring                                                                           |

This post was not an attempt to sell or dissuade you from adopting one approach/language over another. Instead, I've tried to enumerate differences for the purposes of both illumination and arming you with the information needed to come to your own conclusions regarding which model your mind more closely. Each language has a set of benefits and trade-offs and I hope that this post can help others to derive them for their own purposes.

*I would like to thank Alex Miller and Howard Lewis Ship for their feedback on a draft of this post.*

:F
