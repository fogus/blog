Like many programmers, I'm always on the lookout for the perfect programming book.  Over the years I have tried to [enumerate](http://blog.fogus.me/2004/07/08/242/) a [few](http://blog.fogus.me/2005/03/28/essential-unix-programming-books/) key [examples](http://blog.fogus.me/2004/04/04/195/), however after reading them all I am still left wanting.  However, I recently came across a couple interesting blog posts [^motivation] that motivated me that the only way to find the set of perfect books for programmers was to build them myself.  

The process is simple:

1.  Define the set of topics
2.  Find the PDFs comprising the aggregate essays for each topic
3.  Create a table of contents, forward, and write or steal a summary of each essay
4.  Merge everything together
5.  Go to [Lulu.com](http://lulu.com) and print a book [^copyright]
6.  Read the books
7.  ???
8.  Become a better programmer (i.e. profit)

> YOU, and NO ONE ELSE, is responsible for your career.
> -- [Uncle Bob Martin](http://blog.objectmentor.com/articles/2009/02/27/whiners-that-fail)

The Topics
==========
After thinking about the possible topics that would be beneficial to my own enrichment (and to the programmer in general), I hit on the following seven:

0.  Core 

    Things that every programmer should know and read about; taken mostly from [the list compiled by Michael Feathers](http://blog.objectmentor.com/articles/2009/02/26/10-papers-every-programmer-should-read-at-least-twice) with some of my own additions.

1.  The Lambda Papers

    Including the original [Lambda Papers](http://library.readscheme.org/page1.html) and relevant essays pertaining to them and their topics.  

2.  Functional Programming

    Topics concerning functional programming in general including, but not limited to: state, immutability, lambda calculus, purity, recursion, pattern matching, laziness, type systems, closure, anonymous functions, and currying.

3.  Language Development

    One way to learn more about developing new programming languages is to learn how other programming languages were created.  This book would be similar to the previous two books except with a slant toward the more practical aspects of language development.

4.  Object-oriented Design

    While I am learning more and more [the virtues of functional programming](http://blog.fogus.me/tag/clojure,scala,haskell,lisp,onlisp/), I still think that object-oriented programming has just as an important role as ever in software design and development.

5.  Software Development Management

    While my own brief foray into software development manager can only be described as a failure, I believe that the right person implementing the right management techniques can be as valuable as the greatest programmer that you've ever met.  

6.  Operating Systems Development

    From web-based, to monolithic, to exo-kernel, to micro-kernels, there is a lot to be learned from the field of operating system development and their relationships to the underlying hardware; as long as I do not constrain myself to the [Von Neumann architechture](http://en.wikipedia.org/wiki/Von_Neumann_architecture).

7.  Interviews and Anecdotes

    What better way to become a better software developer and designer than to read about the art from the masters.  

And that is it.  I think that after crunching through the seven books outlined above, I should, at least minimally, be smarter than I was this time last year.  I'm always looking for suggestions and comments about my outline above.  I do not want these books to be *my* vision of the best, but instead I want them to be *the best* available.  In addition, I will likely discover new essays, code snippets, and interviews after books are created, so I will likely create an 8th or even 9th book to cover those.  

To end this post, I present the table of contents and summary section [^summaries] to Book 0: Core:

Foreword
---------
THE ORIGINS OF PATTERN THEORY THE FUTURE OF THE THEORY, AND THE GENERATION OF A LIVING WORLD – Christopher Alexander

Essays
------
1. On the Criteria to Be Used in Decomposing Systems Into Modules – David Parnas
2. A Note On Distributed Computing – Jim Waldo, Geoff Wyant, Ann Wollrath, Sam Kendall
3. The Next 700 Programming Languages – P. J. Landin
4. Can Programming Be Liberated from the von Neumann Style? – John Backus
5. Reflections on Trusting Trust – Ken Thompson
6. Lisp: Good News, Bad News, How to Win Big – Richard Gabriel
7. An Experimental Evaluation of the Assumption of Independence in Multiversion Programming – John Knight and Nancy Leveson
8. Arguments and Results – James Noble
9. A Laboratory For Teaching Object-Oriented Thinking – Kent Beck, Ward Cunningham
10. Programming as an Experience: the inspiration for Self – David Ungar, Randall B. Smith
11. Equal Rights for Functional Objects or, The More Things Change, The More They Are the Same – Henry G. Baker
12. The Universal Design Pattern – Steve Yegge
13. A Universal Modular Actor Formalism for Artificial Intelligence – Carl Hewitt, Peter Bishop, Richard Steige 
14. The Humble Programmer – Edsger W. Dijkstra
15. Notes on Programming in C – Robert Pike
16. Go To Statement Considered Harmful – Edsger W. Dijkstra
17. Callbacks in C++ Using Template Functors – Rich Hickey
18. What Every Computer Scientist Should Know About Floating-Point Arithmetic – David Goldberg
19. Duff's Device – Tom Duff
20. MATREX Data Collection and Analysis: Linking Simulation Results to Military Analyst Requirements – Michael Fogus, Dave Prochnow, Kuan Penn, Howard Borum

**On the Criteria to Be Used in Decomposing Systems Into Modules – Parnas**

This is a very old paper, but it is more than a classic. In in it, Parnas introduces a forerunner to the Single Responsibility Principle. He introduces the idea that we should use modularity to hide design decisions – things which could change. People still don’t consider this as often as they should.

Another thing I really like in the paper is his comment on the KWIC system which he used as an example. He mentioned that it would take a good programmer a week or two to code. Today, it would take practically no time at all. Thumbs up for improved skills and better tools. We have made progress.

**A Note On Distributed Computing – Waldo, Wyant, Wollrath, Kendall**

Abstraction is great but it can only go so far. In this paper, the authors lay to rest what was once a pervasive myth – that we could design a distributed system and make distribution transparent. Ever wonder why you had to implement specific interfaces to do remoting in Java? This is why.

In the aftermath it might seem hard to believe that people thought this was possible. I think we can we partially thank this paper for that.

**The Next 700 Programming Languages – Landin**

Most of us have spent a lot of time working in traditional programming languages, but functional programming languages are slowly seeing an uptick and many OO languages are gaining functional features. This paper (which reads like a tutorial) makes an argument for an expression-oriented style of programming. It also lays the foundation for lazy evaluation.

One of the other neat things about this paper, from a historical point of view, is that there is a discussion section at the end in which there a number of questions and comments about whether making indentation significant in a language is a good idea. I was thrown to see people asking whether or not this would be a problem for functions which span over several pages(!).

**Can Programming Be Liberated from the von Neumann Style? – Backus**

John Backus is known for a number of achievements in computer science. He received the ACM Turing Award for his work on Fortran. This paper, which he presented at the award ceremony was rather shocking at the time because it said, in essence, “we got it wrong.” Backus took the opportunity to make a plea for pure functional programming. His arguments were convincing and they helped to set a research agenda which is just now starting to make some waves in the mainstream.

