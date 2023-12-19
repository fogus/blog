[sourcecode lang="java" gist="591070"]// There is a difference between classes and data

// All classes and data are immutable by default
public data Person {
    String  name;
    Int     age;

    // return type inference
    public toString() {
        return name + " is " + age;
    }
}

// somewhere else

// Chains replace the need for ordered arg ctors
// do we really need new all over the place? Each class
// defn defines a factory singleton
Person p = Person().age(45).name("Jimmy Jimmy Jim Jim");

// accessors appear direct
p.name;

// shouldn't println be a first-class citizen by now?
// polymorphic print facility stays because it rocks
println(p);
//// Jimmy Jimmy Jim Jim is 45

// immutability should be a compile-time check
p.name = "froby";
//// Compilation Error: attempting to set an immutable field


/* NOTSET */

// What if we do not set a property?

Person p = Person().name("Jimmy Jimmy Jim Jim");

println(p.age);
//// <NotSet>

// If a field is not set, then it's type is NotSet
// How do we set it?
// Answer: you don't.  You create a new object with the field set
Person p2 = p.age <- 45;

// <- is an operator that says, take an instance and return a new instance
// with some field set

Person p3 = p.name <- "Frobby", p.age <- 21;

// You can chain the <- operator if you want with commas

// So what do we have here?
// = Sets a reference
// <- "mutates" a property, returning a new instance
// If a property has not been set, then it's type is NotSet

/* NOTHING */

// Nothing, almost takes the place of null, except that it is
// a bottom type
// Nothing is different than NotSet in that it is an actual value
// rather than a semantic type meaning, "this thing has not been
// set"
//
// So why have both?  The reason is that in Java we are often forced to
// overload the meaning of null to stand in for both Nothing and NotSet.
// By making them types you can now handle them explicitely along different
// code paths:

pubic class Foo {
    void handle(String o) {
        println("got a string");
    }

    void handle(NotSet o) {
        println("was never set");
    }

    void handle(Nothing o) {
        println("got nothing");
    }
}

/* EXCEPT */

// Checked exceptions are out.  Missing catches can instead be compiler warnings
// try/catch blocks should be cleaner

/* INVARIANTS */

public class Foo {
    @invariant(_ < 10)
    @invariant(_ >  0)
    Int x;

    @pre(n != 0, n != 1)
    @post(_ != 0, _ > x)
    Int frob(Int n) {
        return x * n;
    }
}

// invariant checks occur on construction and mutation

/* LITERALS */

// Maps and sequences should have literal syntax

Map<String, Int> m = {"foo" : 1, "bar" 2};

List<Int> l = [1,2,3,4,5];

Set<Int> s = |1,2,3,4|;

// But what are the concrete types?  There can be a default, or there can be a scoped
// ctor, or there could be something like:

List<Int> l = ArrayList([1,2,3,4,5]);

// or maybe just

List<Int> l = ArrayList(1,2,3,4,5);

Map<String, Int> m = HashMap("foo" : 1, "bar" 2);

// which is literal-syntax-lite

// The point is... if you're going to invent a new Java that breaks compatability, then
// at least try and fix some of the major issues like verbosity, invariants, inference,
// checked exceptions, and mutability.  Go wild.  Everything presented here could be
// compiled directly into Java.  Pre-processors are go!

// :f
[/sourcecode]
