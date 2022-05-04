In a (hopefully) ongoing series of blog posts named BookPr0n, I will highlight and briefly describe some interesting and (at times) obscure books that I come across.  The books will tend to be technological in nature, but not exclusively so.  

The first in the series is the first book published by the immortal [Gregory Chaitin](http://en.wikipedia.org/wiki/Chaitin) titled *Algorithmic Information Theory* (AIT - also [available online](http://www.umcs.maine.edu/~chaitin/cup.pdf) or [for purchase](http://www.amazon.com/gp/product/0521616042/?tag=fogus-20)).  It should be noted that my copy is the 1st edition, whereas the link above is for the current 3rd edition.  I have been looking for some time to purchase and read Chaitin's book *[The Unknowable](http://www.amazon.com/gp/product/9814021725/?tag=fogus-20)* for a few years now, so when I came across AIT on [Paperback Swap](http://www.paperbackswap.com) I had to grab it.  

<a href="http://www.amazon.com/Algorithmic-Information-Cambridge-Theoretical-Computer/dp/0521616042/?tag=fogus-20"><img src="http://farm9.staticflickr.com/8206/8252035051_02150a7faf_d.jpg" alt="cover" /></a>

The cover is a bit hard on the eyes, but it does follow the scheme for the [Cambridge Tracts in Theoretical Computer Science](http://images.google.com/images?q=cambridge%20tracts%20in%20theoretical%20computer%20science) series; for better or for worse.

<img src="http://farm9.staticflickr.com/8060/8252050819_3d6254d71e_n_d.jpg" alt="pg 11" />

Eleven pages into the book we find [Pascal's Triangle](http://en.wikipedia.org/wiki/Pascal%27s_Triangle) which for me is a huge win as far as pure geekery goes.  

<img src="http://farm9.staticflickr.com/8063/8252060065_61192b0186_n_d.jpg" alt="pg 83" />
<img src="http://farm9.staticflickr.com/8348/8252060261_c22b1f251f_m_d.jpg" alt="pg 90" />

Later pages perfectly illustrate what this book brings to the table.  On first glance they appear to be the ramblings of an insane mind, but there is logic to this seeming chaos.  First, in chapter 3 Chaitin defines a [simplified version of LISP](http://www.cs.umaine.edu/~chaitin/unknowable/lisp.c) and writes three versions of the code for the `eval` function (which also happens to be a [universal Turing machine](http://en.wikipedia.org/wiki/Universal_Turing_machine)) using this language.  In chapter 4 he converts his definition of simplified LISP into a [register machine](http://en.wikipedia.org/wiki/Register_machine) and then compiles that into an [exponential diophantine equation](http://en.wikipedia.org/wiki/Diophantine_equation#Modern_research) (which incidentally was written in [REXX](http://en.wikipedia.org/wiki/Rexx)).  

So the question arises: what's the point?  "Simply put", Chaitin's goal in performing the above steps is to create an equation that represents a universal turing machine so that he can then calculate by random evaluation and encode an approximation to the [halting probability](http://en.wikipedia.org/wiki/Halting_probability) (which remember, would also correspond to a LISP program as a exponential diophantine equation) leading the conclusion that this halting probability is an programmatic way to represent [Gödel's incompleteness theorems](http://en.wikipedia.org/wiki/Incompleteness_theorem).  In other words, there is no possible way to tell if his, or any other program, [has a finite or infinite set of programs that will run to their conclusion](http://en.wikipedia.org/wiki/Halting_Problem).

Further information [can be found on Chaitin's homepage](http://cs.umaine.edu/~chaitin/) and his book *[Meta Math: The Quest for Omega](http://www.amazon.com/gp/product/1400077974/?tag=fogus-20)*.

*note: The first 64-bits of Chaitin's constant have been shown (in decimal form) to be [0.0078749969978123844](http://akpublic.research.att.com/~njas/sequences/A100264).*

-m