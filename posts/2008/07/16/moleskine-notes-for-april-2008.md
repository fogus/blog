While Reading [Steve Yegge][yegge1]
=======================

Read the book, [Patterns of Software][pos].
Read about the Emacs/XEmacs [schism][schism] and [Lucid Inc][lucid].

Is Emacs too far along to take a commit from the likes of me?

I almost forgot about James Clark's nxml-mode!

LLVM

While reading [Bill Clementson][clem1]
=============================

I have very little (read: none) with the ratpoison window manager.
Bill briefly talks about its [inspiration][rp].

## BTW
 I should try to install Ubuntu on Parallels again.  There is a
fool-proof (although they have never anticipated a fool like me)
[guide][ubuntupara].

Some books to read
===============
1. Raymond E. Feist's Magician: Apprentice
2. L. E. Modesitt, jr.'s The Magic of Recluce
Begin with The Magic of Recluce and then read The Towers of the
Sunset. Although the immediate sequel to The Magic of Recluce is The
Death of Chaos, I feel that it's better to read at least one of the
prequels before finishing the "real time" story arc. All other Recluce
volumes occur in the past. Personally, I really enjoyed the two books
dealing with the "mythology" of Recluce, Fall of Angels and The Chaos
Balance.
3. R. Scott Bakker's The Darkness that Comes Before
4. Guy Gavriel Kay's Tigana and The Lions of Al-Rassan
5. Neal Stephenson's Quicksilver
6. Katherine Kurtz's Deryni Rising
7. C. S. Friedman's Black Sun Rising, When True Night Falls and Crown
of Shadows.
8. Tad Williams' The Dragonbone Chair
9. Stephen R. Donalson's Lord Foul's Bane
10. Stephen R. Donaldson's The Real Story: The Gap into Conflict
11. Margaret Weis and Tracy Hickman's Dragon Wing (Death Gate Cycle)
12. Margaret Weis' The Lost King

The list above was ripped directly from [Pat's Fantasy Hotlist](http://fantasyhotlist.blogspot.com)

While reading [java sucks][jsucks]
=========================
I need to get a bit smarter on
1. [downward funargs](http://www.google.com/search?q=downward-funargs&ie=utf-8&oe=utf-8&aq=t&rls=org.mozilla:en-US:official&client=firefox-a)
2. multi-dispatch
3. The distinction between slots and methods is stupid. Doing foo.x
should be defined to be equivalent to foo.x(), with lexical magic for foo.x = ...'  assignment.
4. link() on Unix

While reading [about null][null]
====================
> In fact, once we allow it, we may find that we need more than one kind of null, with differing semantics depending on what we mean by it in that context. One kind may mean “unknown”, another might mean “inapplicable”, and of course we might need a third to say that “we do not know if this is applicable or not”. When taken to its logical conclusion, this leads to an explosion of kinds of null.

While reading about [Pascal][pascal]
=======================

On [Javascript as a real language][js.jar]
=========================
John Resig is the man. 

Start with Rhino.  load('env.js').  

Broccoli Thoughts
===========
1.	Monads can be supported if I have the following
	* Closures
	* Anonymous functions
2. Broccoli should perhaps *never* have auto-type coersion.
3. Predicate logic
4. How to impl [folding](http://en.wikipedia.org/wiki/Fold_(higher-order_function))
5. How limited is the flow control without `return` and `break`

TODO
====
- Examine why "The lack of static variables and variable initialization destroy the locality of a program."
- L@@k "Why Pascal Is Not My Favorite Programming Language" by Kernighan
- Add Yahoo! Buzz, Newsvine, Facebook, and MixIt to my pages.
- Make social links conditional on a certain metadata of "post"
- There should be an awesome tag for my essential del.icio.us links.


-m

[pascal]: http://www.pascal-central.com/ppl/chapter4.html
[null]: http://apocalisp.wordpress.com/2008/05/03/null-vs-pure-reason/
[clem1]: http://bc.tech.coop/blog/080429.html
[rp]: http://www.nongnu.org/ratpoison/inspiration.html
[ubuntupara]: http://forum.parallels.com/showpost.php?p=86747&postcount=54
[schism]: http://www.jwz.org/doc/lemacs.html
[pos]: http://www.amazon.com/gp/product/0195121236?ie=UTF8&tag=fogus-20
[lucid]: http://en.wikipedia.org/wiki/Lucid_Inc.
[yegge1]: http://steve-yegge.blogspot.com/2008/04/xemacs-is-dead-long-live-xemacs.html
[jsucks]: http://www.jwz.org/doc/java.html
[js.jar]: http://ejohn.org/blog/bringing-the-browser-to-the-server/
