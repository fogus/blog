The next 1000 languages will most likely be dynamic, or declarative, or pure, but most certainly increasingly functional, with type inference, or pattern matching, or algebraic data types, but most certainly a rich set of concurrent primitive and/or models... or more likely a combination of some or all of those things.  The real question is, what languages will the next 1000 languages be written in?  There was a time that the first and final answer to this question was C, but today there are a few more viable options.  It's a good time to be a polyglot.

Java
====
There is little doubt that the JVM is king right now as the chosen platform for some truly exciting langdev projects.  From [Clojure][clj] ([my current favorite](http://blog.fogus.me/tag/clojure/)), to [Scala][scala] ([a recent addition to my workplace](http://blog.fogus.me/2008/10/20/groovin-with-scala/)), to [JRuby][jrb], to [Jython][jpy], to [Rhino][jjs], to [Groovy][groovy], to [Beanshell][bs], to [SISC][sisc], to [Nice][nice], to [Fan][fan], the JVM is hot stuff, and deservingly so; it's a beautiful piece of sofware.  That is, it is a beautiful piece of software, for running Java.  In order to get all of these other (dynamic) languages to run on top of a system that is geared toward running a static (not in the [type-system sense](http://www.pphsg.org/cdsmith/types.html)) codebase many hairy tricks are employed.  However, there is a light at the end of the tunnel in the form of the [Da Vinci Machine][davinci] project that is working toward making the JVM a viable target for dynamic languages.  I fully expect a flood of new languages will be built on the JVM after the Da Vinci changes take effect (there have recently been some talk about [what InvokeDynamic tastes like](http://blog.headius.com/2008/09/first-taste-of-invokedynamic.html)).

[sisc]: http://sisc-scheme.org/
[davinci]: http://en.wikipedia.org/wiki/Da_Vinci_Machine
[clj]: http://www.clojure.org
[scala]: http://www.scala-lang.org
[jrb]: http://jruby.codehaus.org/
[jpy]: http://www.jython.org/Project/
[jjs]: http://www.mozilla.org/rhino/
[groovy]: http://groovy.codehaus.org/
[bs]: http://www.beanshell.org/
[nice]: http://nice.sourceforge.net/
[fan]: http://www.fandev.org/

Javascript
=======
You may think this is an odd inclusion to this list, but with the release of the [V8][v8] engine by Google it's an obvious one.  That is, the V8 engine compiles JavaScript directly to the host machine code on the fly offering native speeds -- big win.  Javascript has closures, and apparently one can make anything out of them, so in theory multi-paradigm support is feasible.  (*tangent: so does Lisp, and it baffles me that there are not 20,000 languages, much less 2, that target it*).  A potential fly in the ointment is that the [EMCAScript committee decided to ignore TCO][jstco], but they may regret that decision sooner rather than later...

... or not.  The state of affairs in compilers targeting Javascript is lacking with only a handful of original languages like [Parenscript][parenscript], [Haxe][haxe], and [Milescript][milescript].  The rest of the efforts fall into two categories: 1) DSLs targeting AJAX apps or 2) existing languages with toy Javascript compilers (i.e. [Ruby][rb2js], [Sheme][scm2js], [Smalltalk][st2js], and [Haskell][haskell] to name a few).  I think that if I were to start (yet) another project, Javascript would be my host language.  I really think that you can **currently** go a long way with writing a js core for a declarative language and compiling its clauses into Javascript.  Speaking of host, I didn't even mention the extremely strange parasitic language [OMeta][ometa], but that is for another day.  Javascript, is a radical choice given that it really was meant as an embedded document language, so some work needs to be done to make it a true compilation target.  

[ometa]: http://lambda-the-ultimate.org/node/2477
[jstco]: http://lambda-the-ultimate.org/node/3047
[v8]: http://code.google.com/p/v8/
[rb2js]: http://rb2js.rubyforge.org/
[haxe]: http://haxe.org/doc/intro
[scm2js]: http://hop.inria.fr/usr/local/share/hop/weblets/home/articles/scheme2js/article.html
[parenscript]: http://common-lisp.net/project/parenscript/
[st2js]: http://www.squeaksource.com/ST2JS/
[haskell]: http://haskell.org/haskellwiki/Yhc/Javascript/Brief_overview
[milescript]: http://milescript.org/
[calc]: http://calculist.blogspot.com/2006/08/compiling-to-javascript.html
[links]: http://groups.inf.ed.ac.uk/links/


C#
==
There are currently three big players currently on the .NET platform: [Iron Python][ironpy], [Iron Ruby][ironrb], and the exciting [F#][fsharp].  Of course, this does not include C# itself, VB.NET, ASP.NET, etc...  However, aside from this core of big languages, there are very few additional language projects going on.  Of course there is [Boo][boo], [Cat][cat], [Cobra][cobra], and even [Scala][scalanet], but the pickings are slim.  Why is this?  Who knows.  Perhaps it has to do with the fact that many .NET developers are weekday warriors and simply are not interested in the platform outside of it facilitating their ability to draw a paycheck.  Likewise, perhaps the FOSS community inevitably sees .NET as evil and is avoiding it.  Maybe it has to do with the fact that dynamic languages are not fully supported, although that has not seemed to hurt the JVM (note: the [Dynamic Language Runtime][dlr] is working to fix this).  It could be that the .NET platform is already loaded with languages (although they mostly seem to be different faces on C#).  My guess is that the answer lies in some combination of the above.  Whatever the reason(s), my guess is that eventually people will start targeting the CLR/DLR.  Actually, most languages will likely target both the CLR/DLR and the JVM. 

[dlr]: http://blogs.sun.com/jrose/entry/bravo_for_the_dynamic_runtime
[boo]: http://boo.codehaus.org/
[cat]: http://www.artima.com/weblogs/viewpost.jsp?thread=166952
[psharp]: http://www.dcs.ed.ac.uk/home/jjc/psharp/psharp-1.1.3/dlpsharp.html
[ironpy]: http://www.codeplex.com/Wiki/View.aspx?ProjectName=IronPython
[ironrb]: http://www.ironruby.net/
[scalanet]: http://www.scala-lang.org/node/168
[fsharp]: http://research.microsoft.com/fsharp/fsharp.aspx
[cobra]: http://cobra-language.com/
[ionet]: http://synrc.com/io-language.htm

Others?
======
I predict that in the future people will compile all languages into quadruples which will then be converted to all necessary bytecodes.  

No?  

Yeah, I do not believe that either, but there may be an interesting PhD thesis in their somewhere... 

or not.

C
--
Of course people will still create languages using C; I'm not insane.  However, it's monopoly is forever broken.

Perl
-----
There is this strange language called [Perl][perl] that has apparently been around for a little while now and supposedly there is this really cool VM called [Parrot][parrot] that it will run on that supports dynamic languages out of the box.  However, both are somewhat vapor-ish, but all of the propaganda coming out of the Perl camps are exciting, if not [weird][pugs].

[parrot]: http://www.parrotcode.org/
[perl]: http://perl6.cz/wiki/Perl_6_and_Parrot_links
[pugs]: http://www.pugscode.org/

Erlang
-------
So apparently [Erlang][erl] runs on top of its own VM and supposedly there are one or two languages out there targeting it, but I have a hard time believing that trend will expand any time soon.  However, (and I speak from now actual knowledge) the big win is that presumably the Erlang VM provides concurrency operations, which, unless you've lived under a rock, is the wave of the future (and all futures both past and present). 

[erl]: http://www.erlang.org/

Future Language X
------------------------
As cool as Java, Javascript, C#, and Perl6 are (will be), some other language and/or VM will come along that is built around solving the problems that new language implementers are currently struggling with and a bunch of languages will be written in it... it happens every time.

-m
