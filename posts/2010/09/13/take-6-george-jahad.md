

George Jahad is, in my estimation, one of the most exciting Clojure hackers.  His public output, while spartan, is incredibly compelling.  I've learned[^ch13] quite a bit from his Clojure output and hope to see more.  George works for [Runa](http://runa.com/) (they are [hiring](http://www.workatruna.com/) BTW); one of the big four[^big4] Clojure employers.


### What led you to Clojure?

My road to Clojure has been lined not just with great technology, but with great people.  Smart, fun-loving, dynamic people who have really made a difference in my life.  Great technology combined with great people; it's irresistible!

In early 2009, I was programming in C, porting Android to different devices, and wanted to try something higher level. Hacking emacs was often the funnest part of my work day.  I knew I'd never get paid to do that, so I started studying other lisps.  Then going to the [Bay Area Clojure meetups](http://www.meetup.com/The-Bay-Area-Clojure-User-Group/) sponsored by the Runa guys, [@amitrathore](http://twitter.com/amitrathore), and [@rberger](http://twitter.com/rberger).  (They've been doing a great job of promoting Clojure in the SF Bay Area; I wouldn't be a professional Clojure programmer today if it weren't for them.)

I was an anti-java snob, so it took a while, but I kept going to meetups and following the [mailing list](http://groups.google.com/group/clojure); pretty soon, I realized I'd found something special.

I started getting involved: added [Clojure support to the Emacs JDB interface](http://georgejahad.com/clojure/cljdb.html), and got the [Clojure REPL working on Android](http://is.gd/eA1El).

Amit encouraged me to show my stuff at [a meetup with Rich](http://www.mefeedia.com/watch/20002628).  We all went out to dinner afterwards.  I was disappointed not to get to sit at Rich's table.  Instead I sat next to a couple of characters named [@dysinger](http://twitter.com/dysinger) and [@technomancy](http://twitter.com/technomancy)[^phil].  I'd never heard of them before!  A few months later, Tim hired me to do Clojure full time at Sonian.  Missing dinner with Rich ended up being one of the best things that ever happened to me!

I received my "phd" in Clojure at the University of Sonian.  My professors were Doctors Dysinger, Hagelberg, [Larkin](http://github.com/danlarkin) and [Gilardi](http://github.com/scgilardi).  My dissertation was the [debug-repl](http://github.com/GeorgeJahad/debug-repl).  Given the level of ability there, I wanted to show I was "worthy"; I was also growing annoyed by Clojure's lack of debugging tools.  Unfortunately, the original version required hacking the compiler.  Thankfully, [@atosborne](atosborne) showed that unnecessary, that an existing Compiler var would suffice, and the debug-repl was born. Then [@hugoduncan](http://twitter.com/hugoduncan) ported it to Slime and you and [Chris](http://blog.n01se.net/?author=3) discussed it in the [JoC](http://joyofclojure.com); Rich sanctified the whole thing by [exposing the Compiler var as the implicit `&env` arg to macros](http://github.com/GeorgeJahad/debug-repl/commit/1f4381ed5f8dd40b5111f91e94f5cedef96ac1ac).

### Favorite snippet of code?

Well, [@stuartsierra](http://twitter.com/stuartsierra) won't be happy with my answer, but it's: [clojure.walk/walk](http://github.com/clojure/clojure/blob/master/src/clj/clojure/walk.clj#L35)

The audacity of bending Clojure to your will with 12 lines of code.

[Stuart](http://stuartsierra.com/), the author, hates it because he says it's too buggy and breaks on too many edge cases, but it's been really good to me.  I've used it to solve a number of problems, including how to [diff two clojure forms](http://georgejahad.com/clojure/difform.html).

If Stuart has the time, I'd be interested in hearing more about the problems.  I haven't run into them yet.

### Can you describe your typical day at Runa?

I stayed in touch with Amit and Rob, and as I learned more about the work, I got real excited about what they were doing.  Basically, the Runa guys have found gross inefficiencies in the e-commerce systems that exist today and are using Clojure to ameliorate them.  The inefficiencies are so great, that we only have to make a small difference to have a big impact on our customers' bottom line.

In a typical day, I will explore any problems that have occurred in the runtime in the last 24 hours; if there is nothing serious there, then I think about ways to make the system more efficient or easier to maintain/debug or continue adding features.  It's a totally new business model.  We're figuring it out as we go, trying new things each day to see what sticks.  *That's another reason why Clojure is such a good fit for us.  We need to be able to make significant changes to a large system on a daily basis, without the whole thing falling apart.*

When things get tense, we have a "meeting", Amit's euphemism for playing Quake3.

Overall, it's a very smart, collegial environment, with a strict [no a**holes policy](http://www.youtube.com/watch?v=UacbJ72dluU&feature=channel), aggressively attacking interesting problems with Clojure.

What more could you ask for?  More details at <http://www.workatruna.com>


### Horror movies or westerns?
Which one is "Dr. Strangelove"?


### What's next for you?

The debug-repl is pretty good overall, but has a few weaknesses, due to the nature of Clojure.  The main ones are that it can't be invoked automatically when an exception is thrown, and it can't show the local scope from higher up in the stack.  Fixing that would require changing Clojure internals.  Rich has suggested, and I agree, that before we spend a lot of time changing Clojure to improving debugging, we should be exploiting the debug infrastructure the Java vm already provides.  

I started doing this with a [Clojure Debugging Toolkit](http://georgejahad.com/clojure/cdt.html).  It's a command line debugger, like JDB or GDB, but the command line is the standard Clojure REPL, with extensions that allow you to set breakpoints on function entry or exceptions, and eval clojure expressions on the remote target.

While it's already functional, it's missing some standard features like: stepping, modification of locals, breakpoints on line numbers.  After adding those, I'll extend cljdb as an Emacs based front end.

There is also a problem with the compiler clearing locals; it's great when you are trying to avoid holding the head of a lazy seq, but not so good when you are debugging and trying to look up the stack to see what the values are there.  Hopefully, that will be addressable somehow.  

Once those basics are done, we can start doing some interesting things.  Start's thinking about it as a DSL for the creation of debugging features.  For example, it should be fairly easy to create a seq of all the local vars in all the stack frames in all the threads on the VM.  You could then filter that looking for ones that might be holding the head.  

### Favorite editor, drawing app, todo app, notes app, etc.?

I'm a total Emacs geek.  Use it for all of the above as well as shells, debuggers, bookmarks, window management, email, IRC and text-based web browsing.  Regularly have 400 buffers[^buffers] open in a single session.  Emacs is to editors what Clojure is to languages.

Before I go, I just wanted to say I'm really looking forward to [clojure-conj in October](http://first.clojure-conj.org/).  Thanks loads to the [Clojure/core](http://clojure.com) team for pulling it together.  It's going to be awesome!  See you all there![^drink]

[^buffers]: WJW!!

[^ch13]: The final section of [The Joy of Clojure](http://joyofclojure.com) is inspired by George's [debug-repl](http://github.com/GeorgeJahad/debug-repl)... 

[^big4]: [Relevance](thinkrelevance.com) (Super Friends), [Sonian](http://www.sonian.com/) (The Avengers) , [Runa](http://runa.com) (The Armorines?), and [Akamai](http://www.akamai.com/) (??).  I am leaning toward tagging Akamai as the Legion of Doom since it seems to be populated by evil geniuses (in a good way).

[^phil]: I also [interviewed Phil Hagelberg](http://blog.fogus.me/2010/06/28/take-8-phil-hagelberg/).

[^drink