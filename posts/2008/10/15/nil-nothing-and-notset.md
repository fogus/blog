![jaka](http://farm4.static.flickr.com/3172/2945223760_01c5282155_o.png "What happens on (is nothing nothing)")


One of the more nagging elements of Java programming (and programming in general) is that there is a coarseness to the representation of the conditions nil, nothing, and notset.  In Java, one typically uses `null` to represent all three of these conditions and the semantics for the *actual* meaning lies in the secret incantation of its usage.  While playing around with my hobby language [Jaka][jaka] I felt that there could be a finer way to deal with these conditions using a set of objects known as object references.

[jaka]: http://github.com/fogus/jaka/tree/master

nil
---
In Jaka, [nil][n] is used to represent an object that has no possible value.  Since, Jaka is built around the [Cons][cons] data structure, the really only idiomatic usage of nil is for its termination.  However, there are conceivably other instances where "no possible value" could come into play.  Of all of the object reference types, nil is the only that an object can be explicitly set.

[cons]: http://github.com/fogus/jaka/tree/master/src/jaka/fogus/jaka/Cons.scala
[n]: http://github.com/fogus/jaka/tree/master/src/scala/fogus/jaka/Existential.scala

notset
--------
While nil represents the case where an object can have no possible value, [notset][ns] describes an object whose value has never been defined.  When an object is defined without a value it is implicitly marked as notset.  Jaka is designed such that any defined objects are immutable, so it is useful to distinguish those that have not been set.

<pre lang="lisp">
(def x)
x  => gives notset
(def y x)  => error condition, x was never set 
</pre>

But the real win is when you access a map with a key that was never set:
<pre lang="lisp">
(def m {a 1 b 2})
(get m a)  => 1
(get m c)  => notset
</pre>


[ns]: http://github.com/fogus/jaka/tree/master/src/scala/fogus/jaka/Existential.java

nothing
---------
Jaka distinguishes between an object having no possible value, an unknown value, and with [nothing][no], the non-existence of an object. One of the driving goals of Jaka is to eliminate needless earth-shattering exceptions.  Most languages will choke if an undefined variable is even referenced, but Jaka will happily assign it as nothing and move on.  What this means to something like addition is TBD, but for something as fundamental as equality it's a no-brainer.

<pre lang="lisp">
(def x 2)
(is x z)  => z was never defined, so it's "value" is nothing and the equality check is false
</pre>

[no]: http://github.com/fogus/jaka/tree/master/src/scala/fogus/jaka/Existential.java

Of course, if you try to check the equality of two nothing objects a hole in the space-time continuum is ripped opened.

-m
