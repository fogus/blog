A few years ago I got the idea that it would be fun to implement a variant of Scheme targeting the JVM.  During my search for different ways to implement numerics I looked deeply at the implementation of two languages: [JScheme](http://jscheme.sourceforge.net/jscheme/main.html) and a little-known language [Clojure](http://www.clojure.org).  During my explorations with these two languages and the ways that they handled and implemented numerics I quickly came to a humbling realization: there was no possible way that I could make a language as good as either, and along the way I happened to fall in love with Clojure.  So as a result I completely abandoned my piddly Scheme and adopted Clojure outright.

However, over the months that followed the time that I had spent in the interpreter nagged at me.  It seemed that perhaps I could use what I learned to good effect.  Rather than attempt to complete the Scheme for the purposes of use, I instead put together a draft of a single book to introduce two different topics:

 1. Functional programming
 2. Programming language interpretation and compilation

This book was intended to use JavaScript to implement a variant of Scheme piecewise; building a more capable interpreter as the book progressed.  I got as far as re-implementing much of the original language in JavaScript and completed a fairly detailed outline and a couple of chapters.  However, along the way I got sidetracked on writing another book called [The Joy of Clojure](http://www.joyofclojure.com/buy) and my JS-Scheme book was derailed.

During my research for the The Joy of Clojure I happened upon a book called [Lisp In Small Pieces](http://www.amazon.com/Lisp-Small-Pieces-Christian-Queinnec/dp/0521545668/?tag=fogus-20) that simply blew my mind.  Aside from the powerful content itself, the very premise was... wait for it... 

> to implement a variant of Scheme piecewise; building a more capable interpreter as the book progressed.
> 
>  -- me, in this blog post, like 7 seconds ago

As you can imagine, I completely abandoned my JS/Scheme book idea.

However, over the months that followed the time that I had spent on the interpreter, outline and chapters nagged at me.  It seemed that perhaps I could use what I learned to good effect.  Rather than attempt to complete the Scheme for the purposes of a book, I instead put together drafts of *two different books to introduce two different topics*:

  1. Functional programming
  2. Programming language interpretation and compilation

Instead of creating a language for the purposes of building an understanding of functional programming, I could instead write one book introducing functional programming for a language that could use such a book and another introducing how and why languages are created the way that they're created.  About a year and a half ago I started both of these books, but as it stands only one has managed to come to fruition so far.

## Introducing *Functional JavaScript*

Through an odd set of circumstances my name, via [Jeremy Ashkenas](http://ashkenas.com/), found its way onto the desk of Mary Treseler at [O'Reilly](http://shop.oreilly.com/product/0636920028857.do) and I inked a contract to complete my book as an O'Reilly venture:

<a href="http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20"><img src="http://blog.fogus.me/wp-content/uploads/2013/03/funjs-228x300.jpg" alt="funjs" width="228" height="300" class="aligncenter size-medium wp-image-5114" /></a>

## When

As of a month ago the book was effectively complete and is now making its way through the internal review process.[^1]  **The expected release date is June 2013, but [the book is available for pre-order from Amazon now](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20).**

## Why

There are many good books on JavaScript that cover, to varying degrees, functional programming.  However, there is a surprising lack of JavaScript books that tackle the topic full-on.  I am an advocate of functional programming and functional programming techniques, so I think that there are many lessons from functional programming languages like Clojure and Haskell that are directly applicable to JavaScript.  While I'm am advocate, I'm fairly non-dogmatic and pragmatic about functional programming, so I think there is room for a book on the topic that takes a realistic and fun, yet principled perspective.

## What

The book uses and builds on the [Underscore library](http://underscorejs.org/) to highlight and explain functional programming techniques.  It's not intended as a full reference to Underscore.js, but is instead about functional programming in JavaScript.  You have to wait for the release to see it all, but a high-level outline of the chapters is as follows:

 - Chapter 1: Getting started
 - Chapter 2: First-class functions and applicative programming
 - Chapter 3: Variable scope and closures
 - Chapter 4: Higher-order functions
 - Chapter 5: Function building functions
 - Chapter 6: Recursion
 - Chapter 7: Purity, immutability and policies for change
 - Chapter 8: Flow-based programming
 - Chapter 9: Programming without class

This book has been a long time in the works in some form or another and I think that despite all of the setbacks getting here, now is the best time for it to see the light.

I hope that you'll enjoy.

*[hacker news discussion](https://news.ycombinator.com/item?id=5407287)*

:F

[^1]: Additionally my good friend [Rob Friesel](http://blog.founddrama.net) has taken some of his precious time to provide valuable feedback along the way.