In the quest for programming nirvana developers are constantly trying to reduce complexities in their code.  One source of confusion and complexity is mutation.  This post is about the different faces of mutation and state change, and the ways that Clojure helps to alleviate the complexities surrounding them.  This is not a comprehensive treatise by any means, but hopefully it serves as a survey.

*disclosure: this was a rejected submission to [PragPub's Special Clojure issue](http://pragprog.com/magazines/2011-07/content) (which was excellent BTW), so it's much longer than I would have liked for my blog, and probably much more formal than I normally write.*

# A surfeit of mutation

The Java programming language allows one to create classes with publicly accessible fields:

    package gfx;
    
    public class ThreeDee {
        public double x;
        public double y;
        public double z;
    }

This level of promiscuity in a Java class definition allows any other piece of code to directly manipulate the instance fields:

    ThreeDee p = new ThreeDee();
    p.x = 0.0;
    p.y = 1.0;
    p.z = 2.0;

Almost every Java book written will discourage public class fields in the name of tight-coupling and instead promote the use of getters and setters.  From the perspective of mutation complexities however, there is very little difference between one and the other.  The tangled web of mutation still exists.

![web of confusion](http://images.fogus.me/articles/web-of-confusion.png)

While this example is extreme, Java's model for mutation leads to a tightly coupled web of mutation that can make programs difficult to reason about, test, and change.  There are better alternatives to this web of insanity, as I will discuss next.

# Package local mutation

A more constrained model of mutation is one bounded by package access.  Observe the following class definition:

    package gfx;
    
    public class ThreeDee {
        double x;
        double y;
        double z;
    }

The class `ThreeDee` now limits access to its fields to only the classes within the `gfx` package.  This is less of a problem for coupling because the assumption is such that you have more control over the code in a given package and can therefore adjust accordingly should the fields in `ThreeDee` change.  However, while the web of mutation has been shrunk, it is still complex within the `gfx` package itself.

![pkg web of confusion](http://images.fogus.me/articles/pkg-web.png)

While certainly not a widespread phenomenon, you will on occasion encounter package-level mutable fields in Java source in the wild.

# Class local mutation

Every Java book (and most OO books in general -- for good reason IMO) written will espouse the virtues of data hiding encapsulation.  This practice is useful not only in hiding implementation details, but also to hide mutation.  For example, [Google's Guava library](http://code.google.com/p/guava-libraries/) provides an `ImmutableList` class that implements an immutable implementation of the classic list data-structure.  An example usage is as below:

    import com.google.common.collect.ImmutableList;
    
    ImmutableList lst = ImmutableList.of("servo", "joel", "crow");
    System.out.println("lst is " + lst.toString());
    
    System.out.println("REVERSING!");
    lst.reverse();
    
    System.out.println("lst is now " + lst.toString());

When running the code above, you'll notice the following printed:

    lst is [servo, joel, crow]
    REVERSING!
    lst is [servo, joel, crow]    

The `ImmutableList` class lives by its namesake and does not provide a mutable interface, in fact if you try to call the mutable bits of the `java.util.List` interface a `java.lang.UnsupportedOperationException` is thrown.

    lst.remove(0);
    // java.lang.UnsupportedOperationException

The Guava library is designed to provide clean immutable collections[^guava] for use in Java programs.   However, that's not to say that mutation isn't there.  Instead, the mutable bits are cleverly hidden away inside of the Guava classes.  In the case of `ImmutableList` there is a plain-old Java array holding the elements of the list hidden away from grubby little mutants.

Limiting mutation at the class boundary is a fairly nice way to develop Java classes, especially those requiring concurrent execution.  That is, if a class is immutable from an external API perspective and its internals are thread-safe, then instances can be shared freely across thread boundaries.

![class web of confusion](http://images.fogus.me/articles/class-web.png)

However, there is still a problem.  That is, Google's Guava library is an amazing piece of programming, but it's advantages can only be realized within the context of a system-wide convention for immutability.  In other words, Java will not enforce immutability as a language feature -- the onus is on us to enforce our own best-practices.

I don't know about you, but I've found programming conventions and best-practices difficult to observe completely when left to my own devices.

This is where Clojure enters the fray.

# Single points of mutation

Clojure is a programming language in the Lisp family of languages that eschews promiscuous mutation.  The core libraries and data structures are geared toward immutability by default.  In fact, Clojure's data-structures provide most of the same functionality as Guava, except in Clojure these features are exposed and enforced at the language level.  Therefore, the problem of an adherence to convention is simplified vastly.  However, let's be realistic; sometimes mutation is needed.  In the case where mutation really does seem like a good fit for any given problem at hand, Clojure provides multiple solutions.

## Reference types

Recall the image denoting the aforementioned web of mutation:

![web of confusion](http://images.fogus.me/articles/web-of-confusion.png)


Clojure's model of mutation attempts to simplify the model from the chaos above by distilling the points of mutation into as few points of evil as possible; preferably one:

![single point](http://images.fogus.me/articles/single-point.png)


Clojure offers a set of reference types that provide a mutation model centered on singular points of mutation.  References types can be viewed as containers for values -- where values are things that cannot be changed like the number `9` or the immutable vector `[1 2 3]`.  Clojure therefore allows mutation only at the boundary of the reference type under very specific semantic constraints.  The precise mutation semantics of each reference type are beyond the scope of this article,[^more-ref] but common among each is that the mutation occurs as the result of a function call given the reference type's current value.

## Atoms

The simplest of Clojure's mutable reference types is the Atom.  Simply put, the Atom implements thread-safe compare-and-swap logic for mutation.

    (def TIME (atom 0))

The Atom `TIME` when created will hold the value `0`.  To get at the value inside Clojure provides a dereferencing function `deref` (*note, the symbol `;=>` denotes a function return value*):

    (deref TIME)
    ;=> 0

All of Clojure's reference types adhere to a simple interface for retrieving their value using `deref` (or the syntactic `@` operator, that does the same thing).  To update the value in the Atom `TIME` Clojure uses the `swap!` function that itself takes the Atom and a update function that will be used to calculate a new value from the current value:

    (swap! TIME inc)
    
    @TIME
    ;=> 1

You can also pass arguments to the update function (where applicable):

    (swap! TIME + 100)
    
    @TIME
    ;=> 101

Internal to the `swap!` function, the preceding will be executed as such:

 1. Get the current value of `TIME` from the Atom
 2. Calculate `(+ <current value> 100)`
 3. Check if the value in the Atom is the same as before
 4. If it is, then set the new value to the calculated value
 5. Else Retry from step 1

