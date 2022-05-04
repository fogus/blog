[Brenton Ashworth](http://formpluslogic.blogspot.com/) is relatively new to the Clojure community, but has already created four interesting (and useful) projects: [deview](http://github.com/brentonashworth/deview), [lein-difftest](http://github.com/brentonashworth/lein-difftest), [sandbar](http://github.com/brentonashworth/sandbar) and [carte](http://github.com/brentonashworth/carte).  I met Brenton at the previous [Pragmatic Clojure Studio](http://pragmaticstudio.com/clojure/) and we talked a little bit about what would eventually become carte.  He's an interesting guy with interesting ideas.

*[(take...](http://blog.fogus.me/tag/take) is an on-going series of micro-interviews focused on Clojure.*

### What led you to Clojure?

I was looking for a language that was good for writing concurrent programs. The options were [Erlang](http://www.erlang.org/index.html), [Scala](http://www.scala-lang.org/node/242) and Clojure; which I evaluated in that order. This order came about naturally from the order in which I could obtain books on each of these languages. It was mid 2008, “[Programming Erlang](http://www.pragprog.com/titles/jaerlang/programming-erlang)” was already out, “[Programming in Scala](www.artima.com/shop/programming_in_scala )” came out in November of 2008 and then “[Programming Clojure](http://pragprog.com/titles/shcloj/programming-clojure)” in May 2009. After my initial evaluation, I liked all three languages (still do), but I was most impressed with Scala. Erlang seemed more like a specialized language for distributed, fault-tolerant programming but not necessarily a great general purpose language. Clojure was the most foreign and the one which I liked least. I wrote a couple of small programs in both Scala and Clojure for comparison. The Scala versions were easier to write and more readable. 

At about this time, I started watching and re-watching some of [Rich’s presentations on Clojure](http://clojure.blip.tv/). I loved all of the ideas behind the language: functional programming, identity separated from value, persistent data structures, etc... I also knew from experience that OO is overused. I just didn’t like actually writing Clojure code. I suddenly realized that Clojure wasn’t the problem, I was. I didn’t like Clojure because the syntax was foreign and it was hard to think non-imperatively. So, I decided to force myself to use it until I liked it. If it wasn’t for those presentations by Rich,[^videos] I would have never made that decision.

[^videos]: Clojure videos to the rescue again.  I would love to see similarly paced videos for Scala and Erlang.

After a year of Clojure programming, it is much easier to read Clojure code than it is to read Java or C++. Consequently, I read a lot more code these days. I’m now convinced that choosing a language based on how easy it is to read or write is a very bad idea. What if we chose musical instruments based on how easy they are to play? Everyone would playing kazoos. You can’t think deeply about music if all you know is a kazoo. Skill at reading and writing code is learned. We should choose languages based on how accurately they allow us to think about the problems we are trying to solve. 

### What part of the Clojure Studio did you find the most compelling?

Whenever I listen to Rich speak about Clojure, I am always impressed by how he uses [ideas from science, philosophy or real world examples](http://wiki.jvmlangsummit.com/Clojure_Keynote) to justify or explain decisions he has made about the language. This is the one aspect that I like most about Clojure; the ideas in Clojure are more in sync with the real world than any other language I am aware of. It is easy for us as developers to let crazy ideas get into our software and these usually cause us problems. At one point in the Studio, when Rich was talking about maps being functions of their keys and keywords being functions of maps someone asked, “Why not make lists functions of their indexes?”. I remember thinking, “that’s interesting”. Rich’s response was, “Lists are not functions, as an idea.”. At another point when Rich was talking about how readers don’t block, someone suggested that it would be nice if you could see the current value of something. Rich’s response: “There is not such thing as the current value. The future just keeps coming.”. Rich’s commitment to ensuring that Clojure has a strong connection to reality is mind-blowing.

### What aspect of Clojure did/do you find the most difficult to grasp?

[Emacs](http://github.com/technomancy/emacs-starter-kit). Just kidding.

It is hard to leave behind imperative thinking. I still find myself writing ugly code and then realizing that it is ugly because it is overly imperative. Once I realize this and re-write it using functional techniques, it is much smaller and all of the difficulties disappear. It is hard to get to the point where my first thought is the correct functional version.

This is one big advantage of Clojure over Scala. In Scala, imperative code doesn’t stand out[^stand-out] as being out-of-place and ugly. Coming from Java, it would take much longer to learn functional programming in Scala than it does in Clojure.

[^stand-out]: As someone who has spent a lot of time with Scala I will say that the language gives you the ability to be as imperative as you need -- the onus is therefore on you to do good things.  Except in the cases of heavy interop scenarios, I've not found much imperative Scala code in the wild.

### Can you name three uses for an empty Altoids can?

1. small pet coffin
2. miniature Caribbean steel drum
3. the perfect toy for a young child with a good imagination

### What's next for you and Clojure?

Recently, I have been working on Deview which currently provides a more dynamic view of running tests and displays better test results with diffs, when tests fail, as well as shorter stacktraces. This is just the beginning. Very soon it will run all tests automatically when files are modified, run only the tests that need to be run based on which files were modified and run tests in parallel. The theme here is to shorten the amount of time between when you break something and when you know exactly what the problem is. If this period of time is too large then you lose your train of thought. To finish out testing, I will add some kind of test coverage reporting.

Because Clojure code is just data, it will be relatively easy to turn this tool into something that can analyze that data and make suggestions for improvements. It will report things like functions without doc strings, suggestions for cleaning up namespace declarations, and occasions where another function might be better than the one you are using (e.g. `(not (= x))` -> `(not= x)`).

For every one idea I implement, I get ten more; so, we will see how far I get.

I am also obsessed with the idea of graphical representations of Clojure data structures and therefore Clojure code. I may play around with the idea of creating images of Clojure at various levels of abstraction. It would be nice to a have function that creates an image of the namespace dependencies for a project or the flow of code from a specific function. This would become a part of Deview and also could be a great addition to automatically generated documentation.
