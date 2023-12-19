![what happens when you bang your head against the wall](http://farm4.static.flickr.com/3482/3274703628_7b5b2a0d8e_o.png "Original photo by http://www.flickr.com/photos/carowallis1/")

Jeff Atwood, the patron saint of [NNPPs][nnpp], recently wrote a post [decrying SOLID as Ferengi programming][ferengi].  Stepping back, [SOLID][solid] is a set of principles outlined by [Robert Martin][unclebob] that distill his 200 years of Object-oriented programming into a set of best practices fostering agility, testability, extensibility, and any other *ity* that you can think of.  Mr. Atwood warns us that the SOLID principles are created by someone who likely has not written a lot of code, and are not feasible for programmers who write software in the *real world*.  

Not unexpectedly, a common theme that I see in the comment section is as follows: 

> I created tons of software w/o SOLID and the  customer was happy, so that's all that matters in the real world. [^quote]

The statement above would be laughable, if it weren't almost true.  That is, software development is an interesting field in that there is a very large spectrum of competence. [^comp] It's almost like a baseball talent pyramidal structure where for every all-star developer at the top, there are two for each of them in skill, and four below for each of them, etc... [^rh]  Sadly, at any competency level software can be developed that *gets the job done and makes the customer happy*.  In all but the most blatent of cases, it is very difficult for the customer, who is often not versed in software development, to tell the difference in quality between software written by all-stars and those by no-stars.  So if that were the case, who cares right?

The problem really manifests for the developers downstream tasked with maintenance; you know, [the majority][maint].  I think Mr. Atwood is completely dismissive of these developers by preaching such a laissez-faire approach.  But honestly, there is often very little that is sexy about the maintenance cycle, and he certainly wouldn't have such a following if he chose it as the focus of his blog.  However, not all projects are blessed with developers at the caliber of Mr. Atwood's ninja programming team.  Instead, most teams are populated all along the competency spectrum (and many filled primarily from the base) and it's these *real world* teams that absolutely must adhere to a solid set of software development principles in order to maintain some semblance of sanity in the codebase. 

Regardless [how you feel about Object-oriented programming][noop], it is difficult for me to understand why one would choose to disparage the SOLID principles; that is, unless you've already internalized the majority of them over the years without realizing it, or you revel in your NNPP status.

-m


[unclebob]: http://blog.objectmentor.com/articles/category/uncle-bobs-blatherings
[solid]: http://butunclebob.com/ArticleS.UncleBob.PrinciplesOfOod
[ferengi]: http://www.codinghorror.com/blog/archives/001225.html
[maint]: https://www.thedacs.com/databases/url/key/5881
[nnpp]: http://blog.jayfields.com/2009/01/cost-of-net-negative-producing.html
[noop]: http://www.paulgraham.com/noop.html

[^quote]: This is not a direct quote, only a taste of a general buzzing noise eminating from the comments section.
[^comp]: I can't imagine that the more classical engineering fields have such a wide gap between those with the least talent and those with the most.  Am I wrong?
[^rh]: For example, for each [Rich Hickey](http://clojure.org), there are 37,856 of me.
