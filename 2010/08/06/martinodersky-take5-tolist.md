Dr. Martin Odersky, the lead designer/developer of the [Scala Programming Language](http://scala-lang.org), Generic Java, and Pizza requires no introduction, but the motivation for this micro-interview does.  That is, there is an unfortunate view in some circles that sees the existence of Scala and Clojure as somehow being at odds.  In chatting with Dr. Odersky it is my hope to dispel the notion that there is a need for ill-will.  I would like to quote Brenton Ashworth, as he sets the tone nicely:

> We should choose languages based on how accurately they allow us to think about the problems we are trying to solve.
> -- [Brenton Ashworth](http://blog.fogus.me/2010/08/03/take-5-brenton-ashworth/)

Not only is the mutual existence of Scala and Clojure important, it is indeed absolutely vital.  As it happens, Clojure more closely fits the shape of my own thoughts and ways of working, but Scala serves as muse for a great minds like [Daniel Spiewak](http://www.codecommit.com/blog/) and [Jonas Bonér](http://jonasboner.com/).  I could continue along this train of thought, but I think that Dr. Odersky makes a better point than I could.

### In order for Scala to "win", does Clojure need to "lose"?

No, this is silly. Let's be realistic. Functional programming is still [far from being mainstream][mainstream]. A lot of hard work is needed to get it there. And infighting will just damage the adoption of functional programming as a whole. By contrast, any win of a functional language in an enterprise raises the credibility of all other functional languages. **We should help each other, not fight each other**.[^emph]

[mainstream]: http://www.tiobe.com/index.php/content/paperinfo/tpci/index.html

[^emph]: Emphasis mine.

### What are your thoughts about Clojure?


I have not programmed in Clojure. From what I can see it is a very well put together language, that brings the power of Lisp to the JVM in a practical package. There are things we liked so much in Clojure that we copied[^copied] them, for instance the data structures for sequences and [hash-tries][tries].  I believe there are, and will be, many other areas of fruitful cross-pollination[^akka].

[tries]: https://lampsvn.epfl.ch/trac/scala/ticket/1509

[^akka]: I think that it would be wonderful to explore the ideas in Jonas Boner's [Akka](http://akkasource.org/) Actor framework. 

[^copied]: A comment by Daniel Spiewak clarifies this point and leds us to [an interesting ticket](https://lampsvn.epfl.ch/trac/scala/ticket/3724). 

### Both Clojure and Scala are clearly influenced by [Haskell][haskell], [ML][ML], [Chris Okasaki][okasaki], and [Phil Bagwell][bagwell].  However, are there common influences that might not be as apparent?

Erlang was the template after which [Scala's actor's library][actors] was modeled and it might have influenced Clojure's agents as well. Lisp, Scheme and Prolog had some indirect influences to Scala. But the tricky part of any language design in Scala is really getting the static types right, so in that sense Haskell, SML, OCaml and F# had a stronger influence. Then of course there's Java which as the primary host language influences what's possible in Scala and Clojure to no small degree.

[actors]: http://www.scala-lang.org/node/242

[haskell]: http://github.com/clojure/clojure/blob/a1eff35124b923ef8539a35e7a292813ba54a0e0/src/clj/clojure/core_deftype.clj#L398

[ML]: http://books.google.com/books?id=B_QZqFmG-vQC&pg=PA314&lpg=PA314&dq=ml+reference+types&source=bl&ots=0gtl5QyJb-&sig=AlQnUGruRERehJLUSH4XQVDs2wg&hl=en&ei=BBNcTJWFJMH48Abb69jlAg&sa=X&oi=book_result&ct=result&resnum=3&ved=0CBwQ6AEwAg#v=onepage&q=ml%20reference%20types&f=false

[okasaki]: http://github.com/clojure/clojure/blob/a1eff35124b923ef8539a35e7a292813ba54a0e0/src/jvm/clojure/lang/PersistentQueue.java

[bagwell]: http://github.com/clojure/clojure/blob/master/src/jvm/clojure/lang/PersistentHashMap.java

## Rich Hickey is as well-read in the academic papers as anyone, but it's Scala that has gained the perception as an "academic language".  Why do you think that has happened?

I think it's mostly people who want to put Scala down making that comment. They take my [EPFL](http://www.epfl.ch/index.fr.html) affiliation and the papers[^papers] we publish as an excuse to attach that label to the language. What's funny is that often senior industrial Scala programmers get also accused as being academic. All this is rather ironical because Scala is foremost an industrial language[^cufp] with many well known companies using it. By contrast it's much less taught at universities than Haskell or Lisp,
let alone Java!

[^papers]: A recent gem being *[Deprecating the Observer Pattern](http://lamp.epfl.ch/~imaier/pub/DeprecatingObserversTR2010.pdf)* (PDF)

[^cufp]: I am giving a talk at [CUFP](http://cufp.org/) about my own experience using Scala in industry.

## I've found many Scala and Haskell programmers who posses an extremely acute sense of humor.  Is it static typing that attracts these minds, or does it create them?

Maybe it's the creative pauses forced on them when they wait for the type-checker to finish ;-)
