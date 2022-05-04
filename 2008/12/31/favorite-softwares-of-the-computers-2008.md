Once again it is time for my yearly rundown of the applications that I have found most useful over the past year (see entries for [2007][2007-fav-apps], [2005][2005-fav-apps], and [2004][2004-fav-apps]).

Without further delay.

### Web Framework ###
[Jersey][jersey]: There came a time at my job when the word REST [^rest] was being bandied about with impunity.  Since I was slowly discovering the virtues of a RESTful approach, it fell upon me to put together a demonstration for my project team and my framework of choice was Jersey.  There are a number of things that I have found wonderful about Jersey:

1.  Dead simple model for representing [REST resources and clients](http://blogs.sun.com/sandoz/entry/services_are_clients_too)
2.  [Annotations based](http://blogs.sun.com/sandoz/entry/annotated_scala_groovy_jruby_rhino)
3.  Nice integration with [Spring](http://www.springsource.org)
4.  Works extremely well with [Scala](http://scala-lang.org)

### Text Editor ###
[Emacs][emacs]:  I have been using Emacs for a few years and for a time I truly thought that I was quite productive with it.  However, after being exposed to some truly prolific Emacs users in the past year (just about everyone I work with), I soon learned [just how clueless I really was concerning Emacs][loser].  Therefore, I have taken it on to live in Emacs and the results are promising.  I will not make sweet love to myself just yet... there is more work to be done.

#### Runner up(s) ####
The whole point this year has been to scrap all other text editors and work with Emacs.

### IDE ###
[Eclipse][eclipse]: I was going to put [Emacs][emacs] here, but I assumed that all of my half-dozen readers would click away in disgust over "yet another one of *those* posts".  And really, the pick of Eclipse isn't that far off considering that most of my daily paycheck programming is done with Java and [Scala][groovin], and that my group has extended the framework extensively, Eclipse is (for now) the defacto choice for me (although, the Scala support is only less sucky than the other alternatives that I've tried).

#### Runner up(s) ####
[Emacs][emacs]: And since my for-fun programming is done in [Clojure][clojure], it is absolutely essential to do so using [Swank-Clojure][swank].  In addition, I tend to [do a lot of C][ix] in my spare time, so I've found the Emacs support quite pleasant.

### Operating System ###
*OSX*: I have been using OSX since the beginning and cannot imagine going back to another OS.

### Programming Language ###
[Clojure][clojure]: I have found Clojure to be a joy to use.  I have [espoused its virtues](http://twitter.com/fogus/status/1075566397) from time to time, and have devoted a significant [portion](/tag/onlisp) of my (dwindling) free time [working with it](http://github.com/fogus/elgar/tree/master).  I have always spent my spare cycles coding up my own projects, but never have I found such joy in it.  Hell, I even talk to [my wife](http://www.modus-tollens.com) about it at the dinner table.  I cannot imagine that this is what she dreamed of when she said, "I do."

#### Runner up(s) ####
[Python][python]: A late comer to my 2008 favorites party.  That's not to say that I have just discovered Python; on the contrary I have used on and off it for a couple years.  However, the release of [Python 3000](http://docs.python.org/dev/3.0/whatsnew/3.0.html) has gotten me excited to get back into it.

[Scala](http://www.scala-lang.org): I was once very happy with Java.  In fact, it was only a year ago that I found a new-found love for it after reading the [Bloch tome][bloch].  However, a year of reflection, coupled with the [discovery of Scala][groovin] has shone a new light on the matter.  I also like that while the [JVM languages][jvml] tend toward dynamic typing, Scala preaches that Java just wasn't static enough.  If I had to pick the eventual "winner"[^jvm] of the JVM languages, it would be Scala.  

-m

[jersey]: http://blogs.sun.com/sandoz/date/20081201
[bloch]: http://www.amazon.com/gp/product/0321356683/?tag=fogus-20
[swank]: http://github.com/jochu/swank-clojure/tree/master
[2007-fav-apps]: /2007/05/01/favorite-apps-of-the-computers-2007/
[2005-fav-apps]: /2005/03/04/these-are-a-few-of-my-favorite-things/
[2004-fav-apps]: /2004/04/17/203/
[loser]:/2008/07/23/confessions-of-a-textual-loser/
[emacs]: http://www.gnu.org/software/emacs/emacs.html
[groovin]: /2008/10/20/groovin-with-scala/
[tw]: http://www.barebones.com/products/textwrangler/
[eclipse]: http://www.eclipse.org
[clojure-emacs]: http://clojure.blip.tv/file/982823
[clojure]: http://www.clojure.org
[python]: http://www.python.org/
[ruby]: http://ruby-lang.org
[java]: http://java.sun.com
[osx]: http://www.apple.com/macosx
[lithp]: http://github.com/fogus/lithp
[ix]: http://github.com/fogus/ix/tree/master
[jvml]: http://www.is-research.de/info/vmlanguages/

[^jvm]: Winner is of course a very nebulous term, but in this case I will define it as the language that the most Java programmers will be using in 3 years.

[^rest]: I suppose REST is not one word.