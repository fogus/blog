`(take 8 (david-edgar-liebke))`
===============================

David Edgar Liebke is a prolific writer of [Clojure](http://clojure.org) source codes.  His project [Incanter](http://incanter.org/) is a rocket-powered statistical environment based on [R](http://www.r-project.org/) with beautiful visualization capabilities (and then some).  David is also a major data geek and is not afraid to talk about it on his [Data Sorcery](http://data-sorcery.org/) blog.  As a founding member of [Clojure/core](http://clojure.com), David flys to-and-fro in the Clojure-jet fighting against the enemies of concision, simplicity, and bad statistics.  He was gracious enough to take some time to answer a few of my questions.

*[(take...](http://blog.fogus.me/tag/take) is an on-going series of micro-interviews focused on interesting Clojurians.*

### What is the genesis of Incanter?

Incanter is the result of a project I was working on with my Ph.D advisor before leaving grad school to pursue Clojure/core. The goal was to develop a standalone application for building specific kinds of statistical models that are used in the social sciences. 

My early prototypes were built with R, which I love, but it has limitations as a platform for building standalone applications. So I searched for alternatives that would give me R's interactive development cycle, it's core statistical and linear algebra functions, its basic data visualization capabilities, but that would be better suited for general-purpose application development. That's when I found Clojure. 

I spent a fair amount of time using Lisp for AI programming as an undergrad, and then had the opportunity to spend more time with it a couple years ago learning to write automated theorem provers. I had always loved its minimalist syntax, the emphasis on using only a few data structures, the mind-melting awesomeness of recursion and macros, and of course the REPL. So, I had always kept an eye out for opportunities to return to Lisp. 

After a few preliminary experiments with Clojure, and a couple different Java numeric libraries, I had basic statistical and linear algebra functionality. Then one evening I threw [JFreeChart][jfc] into the mix, giving me basic data visualizations. 

[jfc]: http://www.jfree.org/jfreechart/

I was able to very quickly build an environment that had some of the key features of R that I depended on the most, just by combining Clojure with a couple Java libraries, I was hooked and have never looked back.

Incanter took on a life of its own. What was once built as the underlying framework for a specific data modeling application became a general purpose statistical computing environment. 


### Are you a music-while-programming kind of guy?

I don't usually listen to music when I'm working alone or remote pairing, but I definitely like the energy it provides when I'm working down at [Relevance][relevance] HQ -- although with the competing musical tastes of 20 different people (not to mention Aaron Bedra's perverse love of mashups), it's an eclectic mix to be sure.

[relevance]: http://thinkrelevance.com

### Do programmers stink at statistics?

Almost everybody stinks at statistics. However, programmers have an edge now that programming has become an important part of statistics. Unfortunately, they may have no choice but to become better at it because statistics has become an important part of computer science.


### Vi, Emacs, or other?

Both. I started life in Emacs, switched to vi/vim because I spent a lot of time doing system administration. I developed much of Incanter with vim and a command line REPL, but I finally found my way back to Emacs for Clojure development because of the power duo of Slime and [Paredit][pe], but I still use vim for for everything else.

[pe]: http://www.emacswiki.org/emacs/ParEdit

### Aside from Clojure, what programming languages do you hold in high esteem?

I love R despite, or perhaps because of, its quirks; I love the purity of [Scheme][scheme], and I've always appreciated [Ruby][rb], and after spending time at Relevance with some of the best Ruby programmers anywhere, that appreciation has grown.

[scheme]: http://library.readscheme.org/page1.html

[rb]: http://ruby-lang.org/

### Why did you give up the life of celebrity and riches to join Clojure/core?

I gave up a pretty awesome job, working with some of the best people in the bioinformatics world, a great environment, and a bit of free time. In return I get to work with some of the best Clojurists in the world (including [Stuart Halloway][stu], [Stuart Sierra][stu2], and the man himself, [Rich Hickey][rh]). I get a front row seat to the development of one of the most interesting programming languages around, and I get to participate in the early stages of a growing community, full of incredibly bright and enthusiastic people. It was an opportunity I couldn't resist. 

[stu]: http://www.amazon.com/Programming-Clojure-Pragmatic-Programmers-Halloway/dp/1934356336

[stu2]: http://www.amazon.com/Practical-Clojure-Definitive-Guide-VanderHart/dp/1430272317

[rh]: http://www.amazon.com/gp/richpub/listmania/fullview/R3LG3ZBZS4GCTH

### Can you provide a picture summarizing your feelings about Clojure/core?

I think this pretty much sums up my feelings.

![cookies](http://images.fogus.me/blog/cookie.gif "Cookiej")


### Why should anyone care about Clojure/core and Clojure in general?

Well, I think the goals of Clojure/core are pretty cool 

1. Help fund the further development of Clojure
2. Help improve the Clojure ecosystem and community
3. Help those that don't understand the benefits of Clojure's approach to see the value
4. Help those that already understand the value to build systems

And I think the combination of in-depth knowledge of the Clojure language and Relevance's well known expertise in agile development is pretty fantastic. 

I consider Lisp the first agile programming language. Later languages couldn't match its power and fast development cycle. Eventually features like dynamic typing, interactive development with a REPL, and meta-programming made their way to newer languages. In time leading to Ruby, and now Clojure brings it back full circle. 
