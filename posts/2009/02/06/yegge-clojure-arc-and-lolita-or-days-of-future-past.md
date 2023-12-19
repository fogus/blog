<img src="http://blog.fogus.me/wp-content/uploads/2009/02/tinrobot-300x300.jpg" alt="" title="tinrobot" width="300" height="300" class="aligncenter size-medium wp-image-4598" />

*This post was discussed thoroughly  on [Hacker News](http://news.ycombinator.com/item?id=470254).  Even Mr. Graham was kind enough to comment -- though not in the affirmative.  There was moderate discussion on [Reddit](http://www.reddit.com/r/programming/comments/7vgho/yegge_clojure_arc_and_lolita/) as well.*  

A long time ago, in a galaxy far far away, [Steve Yegge wrote an essay on Common Lisp](http://steve-yegge.blogspot.com/2006/04/lisp-is-not-acceptable-lisp.html) named *Lisp is not an Acceptable Lisp*.  As far as Yegge essays go, it was one of the more Molotov-esque.  There are a few items in this ancient tract that I feel are spot on with reality and another few that seem to miss the mark these many centuries later. 

Precognition
===============

> Arc's going to be a new religion, of course, because programmers just haaaaaave to make it that way.

Looking back on the reaction to the [announcement of Arc](http://www.paulgraham.com/arcll1.html), Yegge was reading the writing on the wall with this particular comment.  Paul Graham has always tended to be viewed as a living/breathing/walking religion because of his spectacular essays on [high school life](http://www.paulgraham.com/nerds.html), education failures, and general purpose geekery.  However, his essays are likely to be remembered for the way that they generated a frenzy-tinged lust for LISP.  There are a 1000 reasons why this could be, but the most likely lies in the fact that when his most groundbreaking essays were written, the world of programming was [mired in the world of Java, C, Visual BASIC, and C++](http://www.tiobe.com/index.php/content/paperinfo/tpci/index.html).  His essays on LISP elucidated an almost [Utopian world of programming power](http://www.paulgraham.com/avg.html), long forgotten or little imagined to those mired in [Blub](http://en.wikipedia.org/wiki/Paul_Graham#Blub).  

> ... My prediction: someone will get tired of waiting, and they'll Torvalds Arc into obsolescence before it's ever released.

The [release of Arc](http://paulgraham.com/arc0.html) was a grand day in the history of the Internets.  Birds sang that day my friends.  The air smelled of the finest of perfume.  I believe even [Christopher Hitchens](http://en.wikipedia.org/wiki/Christopher_Hitchens) thanked the gods that day (speaking of cult-like figures in the world of geekdom).  As a result of this unparalleled software release, [mzscheme](http://www.plt-scheme.org/) was downloaded over a trillion times.  However, the honeymoon was short and the grumbling was indeed furious thereafter.  "Is this it?"  Now bear in mind, over the years leading up to the release, the fledgling Arc community had nurtured such expectations of Mr. Graham's language that anything short of [The Singularity](http://singinst.org) occurring as a result of its execution was tantamount to [Global Thermonuclear War](http://www.mgm.com/title_title.php?title_star=WARGAMES) followed by the cancellation of *Heroes* and the concurrent deaths of [Adam and Jamie](http://dsc.discovery.com/fansites/mythbusters/mythbusters.html).  It was running on top of mzscheme?  It didn't support UNICODE?  No module system?  Few libraries?  Unhygienic macros?  Lists for dinner -- again?  These criticisms festered into essentially an Internet meme [^meme]; everyone was jumping in on the lashing.  Eventually, the buzz of Arc wore off and all that remained was the negativity.  Eventually, Mr. Graham himself seemed to spurn [^spurn] the language, and it has since been relegated to the dustbin of the [github fork](http://github.com/languages/Arc).  [^gh]

And then along came [Clojure](http://clojure.org).  For all intents and purposes, Clojure's creator Rich Hickey **is** Arc's Torvalds quipped on by Mr. Yegge.  Adding powerful namespacing coupled with the fact that it's built on top of the JVM dispels the questions and gripes aimed at Arc from the start.  Any momentum that Arc may have gained from its release and subsequently maintained once the love affair ended was squashed under the heel of the Clojure juggernaut.  Sure, Arc still has its proponents, desperately clinging to their language of choice like a desperate Humbert Humbert to a very pregnant Lolita [^lo], but even [they have the preliminary itch of disillusionment](http://news.ycombinator.com/item?id=446238).  

> Truth be told, Lisp should probably have a skinnable syntax. That implies a canonical abstract syntax tree, which of course hasn't been defined (and in many implementations isn't even available to you, the way it is in the Io language, say). 

Now I'm not a LISP expert, but it seems to me that the S-Expression is popularly regarded as the canonical representation of the LISP AST (and many other languages for that matter).  That is, the LISP syntax is as close to an AST as one can get.  Perhaps the presence of macros, **#**, **`**, **'**, and **,** muddy the waters a bit, but not much.

Misses
=======

> Your only other option is to design a new language, and you won't get any help from Lisp people, because they will hate you.

It seems to me that the [greater LISP community](http://www.infoq.com/oopsla) is embracing Clojure and its creator Rich Hickey most amiably.  Not coming from a LISP background, it's difficult to say what the condition of vitriol and hatred was when the original article was written.  Additionally, I do not have a herd of LISP hackers roaming my office halls in order to observe any low-frequency mummers disparaging Rich Hickey.  In any case, Clojure has [garnered a lot of excitement](http://danweinreb.org/blog/software-technologies-that-i-must-learn) from some [highly credible LISP hackers](http://bc.tech.coop/blog/081023.html).  If [Mr. Kenny Tilton](http://smuglispweeny.blogspot.com/) would come around, then the coup would be complete.  

> Or maybe you could go the Haskell route and not have OOP at all. That seems to alienate most programmers, though, despite the attractions of not having to create nouns for everything.

It's tough to be negative on Mr. Yegge on this quote considering that 2006 was still during the time when OOP principles were drilled into every C.S. majors' skull and could be heard in the laughter of children.  However, the functional programming para-dig-em is finally starting to gain a handhold in an otherwise homogeneous programming language landscape [^homogeneous].  


Still True?
==========

> Macros are notoriously hard to debug, and honestly it needn't be that way. If your editor knows all about macros, then you should be able to click to see the expansion, and click again to see its sub-expansions, all the way down to the primitive functions.

It may simply be my own ignorance, but I still do not know of an editor that does this for any current LISP.  *(pointers welcome)*

Conclusion
==========

In order to wrap up this post, I would like to quote a comment posted in the comments section of the original article:

> Lisp indeed leaves you with your own ideas and your own limitations. It doesn't pose any artificial restrictions on your programs that you have to work against, and it doesn't provide any "color by numbers" examples that make you feel like you have achieved something. Lisp requires you to be creative.

The quote above by [Pascal Costanza](http://p-cos.net/) says it all: about Clojure, about Common Lisp, about [Qi](http://www.lambdassociates.org/), Scheme, and even about Arc.

Having spent a majority of my programming career writing Blub, I have only recently [^college] discovered the power of languages in the LISP vein.  Like many programmers, my interest was stoked by the excellent and iconic (as far as Internet noise goes) LISP essays written by Mr. Graham.  I, like many others, dreamed of the [100 Year Language