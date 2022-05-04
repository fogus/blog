![ix](http://farm4.static.flickr.com/3288/2778330731_6510c01109_o.png "Ix Robot Go!")

Project Doris
==============
Project Doris is actually the name of a programming language -- actually it's three programming languages.  I suppose some explanation is in order.

A History of Project Doris
---------------------------
It's probably wise to begin at the beginning.  Right after obtaining my [undergraduate degree][smcm], I obtained work as a programmer for a [medium-sized company][dcs] located in Lexington Park, MD.  My task for this company was to create an expert system that was able to best [determine the loading of navy aircraft and their sequence of release][weps], which apparently is still a living entity, although my initial version is probably long gone (which looking back is probably a good thing).  Being an expert system, it was natural that the source code be written in the [CLIPS programming language][clips].  Having no prior experience with CLIPS, it made very little sense to me at first, yet I was not to be detered.  In order to make an attempt to wrap my head around the CLIPS language I decided to go into the C source and muck it up in order to make it look more akin to [something that I was already accustomed to][lisp].  Overall my efforts were useful for learning the internals and function of CLIPS, but it was essentially throw-away code as the requirement was to write the system in CLIPS-proper (seriously, this was an *actual* requirement for some strange reason).  Sadly, the originally modified source was left on my computer when I left that particular company (the first time) and is likely lost to the dustbin of history.  However, the bug for modification has struck again and Project Doris is the attempt to reuse some of the same ideas that I originally had coupled with some new ideas that I have germinated in my mind since.  

[lisp]: http://www.stat.uiowa.edu/~luke/xls/xlsinfo/xlsinfo.html
[dcs]: http://www.dcscorp.com
[weps]: http://www.aaai.org/Library/IAAI/2005/iaai05-002.php
[smcm]: http://www.smcm.edu
[clips]: http://clipsrules.sourceforge.net

So what the heck is Project Doris?
----------------------------------
Aside from being an homage to [my lovely grandmother][mommom] (RIP), I suppose you could call Project Doris a modernization of CLIPS, but even that would be too presumptuous.  In reality, it is the creation of four programming languages (plus some other stuff) using the CLIPS source code as the base.

[mommom]: http://www.flickr.com/photos/fogus/2112947183/in/set-72157603478752122

Four Languages?!? WTF?
----------------------
I know what you're thinking, "does the world really need" another programming language, much less four more?"  My answer to you my friends is and emphatic **YES**.  The world not only needs four more, but it needs *forty-thousand* more.  That is, the programming landscape would be much richer if programmers took some time and designed their own little [DSLs][dsl] and hacked-up programming languages.  

[dsl]: http://en.wikipedia.org/wiki/Domain-specific_programming_language

You arrogant bastard!
----------------------
Again, I know what your next reaction might be: "So you think you can do better with CLIPS than Gary Riley, Brian Dantes, Brian Donnell, Frank Lopez, and NASA?"

My answer is a high-pitched <sub>no</sub>.  but you see, my goal is not to make a better version of CLIPS, it only to see how far the original source can be stretched and mangled.  I do not wish to usurp CLIPS; in fact, I would be quite shocked (and awed) if anyone but myself ever used anything that comes out of Project Doris.  I am a true believer in the CLIPS language, but I am more of a believer in the idea of taking someone else's idea and taking stuff away until it is awesome.  CLIPS is 20 some-odd years old and has an established user base who are happy with it the way it is (not that 20 some-odd years of history much allows for much change in the language itself).  Besides, using the CLIPS source as the base allows me to leverage existing code -- who has time to write a garbage collector, parser, and Rete implementation?  Not me.

Common Ground
=============
There will be some common functionality between the three sub-languages that comprise Doris, some of which have not yet been decided. However, the current list of common parts is as follows.

Has
---
### Blocks
In the olden days, my initial implementation of a block structure was simply syntactic sugar for the `(progn)` function.  This may or may not be the way to go moving forward, but in any case I like the idea of passing code blocks around.  This is not the same as a Ruby block but instead a nameable set of actions that can be applied at certain points and which can access the current lexical scope.  In addition, parametrized blocks would provide a general closure mechanism.

### Modules
The base CLIPS module system will primarily be used as a packaging mechanism for packaging libraries.  The fact-base partitioning capability that the `(defmodule)` constructs has always provided a powerful mechanism and will largely remain unchanged.  I plan to extend the base capability by providing aliasing.

### Lists
The fundamental data structure shared between languages will be none other than the truty list.  CLIPS itself has a scaled-back version of the list called a multifield which simply put can be thought of as a single-level list (i.e. no embedded lists).  Multifields are fine and good, but I need to be able to store any structure in a list, including lists.  Therefore, with a little bit of effort I was able to get true lists working.  Lists in Doris are not like classic Lisp lists in that they are built up as pairs, but are instead backed by arrays.  

Privative
-----------
### Globals
It is ashamed that after years of being bitten by global variables that languages are still being created that provide them.  

Broccoli
=====
### Functions
I have been working on a language called [Broccoli][broccoli] (previously Vodka) for quite some time now, but the [original interpreter was written in Python][lithp].  Sometime about 4 months ago I decided to use the CLIPS source as the basis for Broccoli and it turned out to work quite well.  There are some major differences in that there is no real support for anonymous functions, but I believe that I have a way to hack that into the CLIPS interpreter.  Over the past 4 months I have made quite a few changes to the CLIPS source and what remains is vaguely recognizable as being rooted in CLIPS.

[lithp]: http://www.earthvssoup.com/2007/11/23/lithp-v001/
[broccoli]: http://www.earthvssoup.com/projects/broccoli

### Anonymous functions
One aspect that I have always felt was missing (and is actually discouraged) from CLIPS was that of anonymous functions.  There is support for indirectly calling functions by using the `(funcall)` function with a symbol corresponding to the name of an existing function, but it would be nice to eliminate this extra step in many cases.  It is often appropriate, desirable, and [beautiful][jquery] to chain functions together, and this can be elegantly done by simply allowing the creation of anonymous functions within certain language constructs.  The precise mechanism behind implementing this is only a vague and cloudy mess in the back of my mind at the moment.  It seems that the most difficult aspect would be the disposal of the anonymous functions as they drop out of scope, but perhaps an `(undeffunction)` call can be bound to the internal anonymous symbolic name, but the implications of this are yet to be determined.

[jquery]: http://www.jquery.com

Ix
==
### Rule based
