CLIPS
====
> Here's how hash tables work for a simple rule.
> 
    (defrule example
      (point-1 ?x)
      (point-2 ?x)
      =>)
    
> Let's say you've got facts (point-1 1) through (point-1 100). When
> these facts are asserted, they're stored in a hash table for the
> pattern (point-1 ?x) based on the value of ?x. If (point-2 53) is
> asserted, the value of 53 for ?x is used to create a hash value to
> look into the hash table for the first pattern. We then apply the
> pattern test to the contents of the bucket in the specified position
> in the hash table. If the contents of the bucket were (point-1 3) and
> (point-1 53), we'd only have to apply the comparison test for the
> correct value of ?x twice. Without the hash table, we'd be forced to
> look at all the matches for (point-1 ?x) which would require 100
> comparisons rather than 2.
> 
> I'm still hoping to get a beta version of 6.3 with the performance
> improvements out in the next month or two. The major performance
> improvements have all been completed. Currently I'm tweaking the
> internal representation of the rules so that the addition of (initial-
> fact) patterns to rule will no longer be necessary. I also need to add
> hash tables to speed up pattern matching for situations where a large
> number of constants are used in patterns. 

I have no mouth...
============
> Outwardly: dumbly, I shamble about, a thing that could never have been 
> known as  human, a thing whose shape is so alien a travesty that 
> humanity becomes more  obscene for the vague resemblance. 
> ...
> I have no mouth. And I must scream.
![http://harlanellison.com/heboard/ihnm/images/hebill.jpg]

Movies
======
1.  Cool Hand Luke
2.  Tombstone

Scala
=====
> Let's say that we want to enrich Java Sets so that we can do folds and foreachs 
> and all that good functional stuff with them. Here's a trait that defines the
> methods that we want to provide:
    trait RichIterableOps[A] {

      // required method
      def iterator: Iterator[A]
  
      def foreach(f: A => Unit) = {
        val iter = iterator
        while (iter.hasNext) f(iter.next)
      } 
  
      def foldLeft[B](seed: B)(f: (B, A) => B) = {
        var result = seed
        foreach(e => result = f(result, e))
        result
      } 
    }

> Let me fire up the Scala Interpreter to play with this. I am going to mix the 
> trait into a HashSet, and then do some operation on the Set; let's see how 
> that plays out:

~~~
    scala> val richSet = new HashSet[Int] with RichIterableOps[Int]
    richSet: java.util.HashSet[Int] with traits.RichIterableOps[Int] = []
    
    scala> richSet.add(1); richSet.add(2)
    
    scala> richSet
    res0: java.util.HashSet[Int] with traits.RichIterableOps[Int] = [1, 2]
    
    scala> richSet.foldLeft(1)((x,y) => x+y)
    res1: Int = 4
~~~

A new emoticon?
===========
<pre>
,.|..
</pre>

aether
====
~~~
relationship :wrote has passive :written 

"Stephen King" wrote "Pet Cematary"  # This reflexively calls "Pet Cematary" written by "Stephen King"
"Stephen King" isa :author
an :author isa :person

# querying
what :wrote "Pet Sematary"?  => :author
what isa :author             => :person
who wrote "Pet Sematary"?    => "Stephen King"
~~~

-m
