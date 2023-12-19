![mouse](http://farm4.static.flickr.com/3133/2785147996_fea180ee84_o.png "Business man they drink my wine...")

In developing [Broccoli][brocc], I have turned up some extremely interesting language design principles that I would love to fold into [Doris][doris]:

[brocc]: /projects/broccoli
[doris]: /2008/08/19/project-doris/

Clojure
======
Iterables
----------
[Clojure][clojure] has a [notion of sequences][it] that allow different data structure to provide access as a logical list.  The simplest example is to view a string as a sequence of characters where typical list operations (e.g. first, rest, cons, etc...) can be performed on its elements.  I have started working that type of behavior into the CLIPS source, but it is slow going as the implementation of multifields, fact-bases, strings, and symbols are categorized as discrete types.  Once I start digging into [Numan][numan], I will likely add a similar notion of the [ISeq][iseq] interface (with some extensions) to the base object hierarchy.

[numan]: /projects/numan/
[it]: http://clojure.org/sequences
[clojure]: http://clojure.org
[iseq]: http://clojure.svn.sourceforge.net/viewvc/clojure/trunk/src/jvm/clojure/lang/ISeq.java?view=markup

Javascript
========
Objects as containers
-------------------------
I love the way that Javascript provides a simple mechanism for using objects as general-purpose containers.  This allows one to add properties and functions to an *instance* at any time during execution.  CLIPS allows us to add message handlers to *classes* at any time, but class slots (properties) can only be defined at the time of the class definition.  I feel that providing a similar capability in [Numan][numan] would be an enormous effort, but it is worth it. 

CLOS
====
Meta-object protocol
-------------------------
CLIPS can be said to have a fairly useful introspective MOP, but i have in the past found it disjointed.  Aside from the way that `(defmessage-handler)` occurs outside of a class definition, COOL has not other real mechanism for providing intercession for objects.  Likewise, there is an overall lack of intercession capabilities in CLIPS.  Doris would be nicely served if it offered even a partially realized MOP along the lines of CLOS (as introduced in Andreas Paepcke's paper *[User-Level Language Crafting - Introducing the CLOS Metaobject Protocol][mop]*).

[mop]: http://www-db.stanford.edu/~paepcke/shared-documents/mopintro.ps

-m
