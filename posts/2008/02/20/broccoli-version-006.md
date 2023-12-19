I have been working on programming language interpreters for seemingly forever, but only recently has the effort born any fruit.  That is, 95% of all of my langdev projects have been throw-aways or work-related DSLs; however, Broccoli one is more suitable for public consumption. 

<h2>What Broccoli Is Not</h2>
<strong>Broccoli is not Lisp/Scheme/Arc/etc...</strong>
That is, Broccoli is not a functional language.  It certainly has some functional elements, but it is far from treating functions as first-class objects.  In addition, Broccoli lists are in fact fundamental data types whereas they are not in <a href="http://clisp.cons.org">Lisp</a> and <a href="http://www.plt-scheme.org/software/mzscheme">Scheme</a> (not sure about <a href="http://www.paulgraham.com/arc.html">Arc</a>).  Finally, Broccoli also contains those pesky parenthesis, but that and a few similar function names are as close to Lisp/Scheme/Arc/etc... as Broccoli gets.  There are most likely millions of other ways that Broccoli differs, but these are the root differences.

<strong>Broccoli is not Fast</strong>
That is, Broccoli is a fully interpreted language that does not contain much in the way of optimizations ala <a href="http://en.wikipedia.org/wiki/Just-in-time_compilation">JIT compilation</a>.  That is to say that it cannot one day be fast, only that it is not the goal to start.

<strong>Broccoli is not (fully) Object-Oriented</strong>
In fact, at the moment Broccoli is not "Object-Oriented" in the least.  I will one day build an object system into it (it would actually be quite easy); however even when that day comes, Broccoli will provide support for functions and primitive types.  

<strong>Broccoli is not (yet) Open Source</strong>
But it <a href="http://www.opensource.org/licenses/mit-license.php">will be</a> one day.

<h2>What Broccoli Is</h2>
<strong>Broccoli is the language specification of Broccoli</strong>
What on earth does that mean?  In essence it means that the form of Broccoli is the parse form of the Broccoli language (see <a href="http://www.paulgraham.com/icad.html">ROtNs</a> for more details on this recursive definition).

<strong>Broccoli is succinct and familiar</strong>
I have tried (and will continue) to use names that are familiar to an important part of the programming community... <a href="http://www.flickr.com/photos/fogus/">me</a>.  I will not sacrifice succinctness to reach this aim however.  

To learn more about Broccoli, check out the <a href="/projects/broccoli/">manual</a>, request a binary, play around, or just slag me online (oh wait).  

-m
