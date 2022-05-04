*this is the third entry in an n-part [series explaining the compilation techniques of Clojure](http://blog.fogus.me/tag/clj-compilation).[^cng]*

There was an interesting [discussion about invokedynamic on the Clojure mailing list](http://groups.google.com/group/clojure/browse_frm/thread/d66af49bef572e8a) focused on the need for and potential benefits of [invokedynamic](http://jcp.org/en/jsr/detail?id=292).  Granted this topic is often quite technical, so I suppose that it's understandable that confusion and disagreement would arise.  However, the general tone of the thread and the [ensuing Internet discussion](http://news.ycombinator.com/item?id=2928285) was that Clojure didn't need invokedynamic.  This is technically true, but there is a distinct difference between *need* and *benefit*.  This post will hopefully clarify the current state of affairs regarding just how invokedynamic can benefit, and also hurt, Clojure on the JVM.

# Why Clojure might not need invokedynamic

[Tal Liron](http://groups.google.com/group/clojure/tree/browse_frm/thread/d66af49bef572e8a/84595d95fef714cb?rnum=11&_done=%2Fgroup%2Fclojure%2Fbrowse_frm%2Fthread%2Fd66af49bef572e8a%3F#doc_dd82cc60855b1ca3) provided a very nice summary of the reasons that invokedynamic might be unnecessary for Clojure.  I highly suggest you read his assessment as I will only summarize some high points here and in the next section, offering corrections where his thesis differs from the realities of the invokedynamic story for Clojure.

### Java Interop

Let's take a look at the first potential use of invokedynamic for Clojure, interop calls.  As you may know, Clojure allows one to call Java class methods directly as below:

    (defn at [s] (.charAt s 1))
    (at "abc" 1)
    ;=> \b

However, the call above is problematic in that the compiler cannot resolve the class of the `charAt` method so an expensive route through reflection must take place at runtime.  Unfortunately for us, though [some common paths through reflection are indeed optimized](http://java.sun.com/products/hotspot/whitepaper.html) by the [JVM HotSpot](http://www.oracle.com/technetwork/java/hotspotfaq-138619.html), we wouldn't want to hang our hat on that fact.  Would it be possible therefore to use invokedynamic instead of the reflective call?  Probably.  However, it's worth noting that through compile-time type inferencing, most interop calls are emitted in the most efficient ways possible.  In fact, most of the interop calls in Clojure are compiled in the same way that Java itself is compiled.  For those times as above when the Clojure compiler in unable to infer the correct target type, then Clojure provides type hinting to help the compiler along:

    (defn at [^String s] (.charAt s 1))

Like many Lisps, Clojure is built around the principle that one should write the code correctly first and then only add adornments later when speed is needed.  That is not to say that there is no place for a scenario whereby invokedynamic is used instead of reflection in the non-inferred case, but the presence of type hinting makes that scenario less than urgent and definitely not compelling enough to consider it a worthwhile venture on its own.  I would imagine that time would be better spent implementing more comprehensive type inferencing.  This is a clear win that would benefit many codebases quickly, with none of the downsides listed in the last section of this post.

### Multimethods

Clojure has a multimethod function type that provides runtime dynamic dispatch of a named function to a set of unique implementations based on the result of a separate dispatch function.  A simple example is below:

    (defmulti say-count count)
    (defmethod say-count 0 [_] "Zero")
    (defmethod say-count 1 [_] "One")
    (defmethod say-count :default [_] "A Bunch")
    
    (say-count [1 2 3])
    ;=> A Bunch

As you might see, the multimethod `say-count` is defined to dispatch based on the result of the interceding function `count`.  When `count` returns `0` the specific method associated with that value is invoked.  For every call to a multimethod[^more-mm] its dispatch method is called.  This is a potential bottleneck, but what you give up in speed you gain in flexibility.  But does it need to be this way?  Can invokedynamic help alliviate this tradeoff?  The answer as it turns out is similar to interop's answer.  How invokedynamic can help multimethods escapes me, although they do have a PIC-like doo-dad (technical term) under the hood -- so the jury is still out here.

[^more-mm]: Actually there is more to it than this as there are also dynamic hierarchies that are (potentially) traversed on each multimethod calls.

# How invokedynamic can help Clojure

*a nice source of core knowledge on this section is this [treatment of dynamic invocation on the JVM by John Rose](http://blogs.oracle.com/jrose/entry/dynamic_invocation_in_the_vm)*

Before any discussion about how invokedynamic can help begins, it's critical to understand what it provides.  In a nutshell, invokedynamic provides the raw material for building efficient [polymorphic inline caches](http://c2.com/cgi/wiki?PolymorphicInlineCaches) that are subject to finer grained HotSpot optimizations. At the moment Clojure and JRuby (and others for sure) build those PICs from "something else" (technical term).  However, as I will discuss regarding Vars next, invokedynamic's benefits are not limited to the case where one might find a PIC.

Much of the following is covered by the eminent [Paul Stadig in the original thread](http://groups.google.com/group/clojure/tree/browse_frm/thread/d66af49bef572e8a/3a21f40138f22c0d?rnum=21&_done=%2Fgroup%2Fclojure%2Fbrowse_frm%2Fthread%2Fd66af49bef572e8a%3F#doc_63423c53208effdf), but I'll provide an overview below.

### def

At the core of Clojure's dynamic heart is the Var.  At it's most boiled (i.e. before taking into account dynamic or thread-local bindings), a Var is a single point of mutability that holds a value or function.  Clojure as a Lisp is predicated on the ability to change a function definition at any time.  In production this explicit function redefinition is not used very much, but at the REPL, Clojure's interactive console, this capability is used to great effect in incrementally building a solution and in-place sanity checking (i.e. REPL-based testing).

To provide this level of flexibility Clojure establishes a level of indirection.[^dyn]  Specifically, all function lookups through a Var occur, at the lowest level, through an atomic volatile.[^linking]  This happens every time that a function bound using the `def`/`defn` special forms is called.  This indirection is not amenable to HotSpot optimizations.

This is a great place for invokedynamic, especially during production scenarios where the root value of Vars remain stable.  That is, invokedynamic would eliminate the volatile lookup on every call and likely lead to HotSpot inlining. From another perspective, the JVM provides a way to change out class implementations on the fly via something called safepoints.  This implementation swapping is analogous, if not a mirror of, the swapping of Var root bindings.  Safepoints are intuitively viewed as stable execution points where interrupts can be utilized to save JVM state, thus allowing magic (technical term) to happen safely and atomically.  At the moment, invokedynamic is the *only* path to safepoints for the JVM language implementor.  It would be awesome (technical term) to have a direct path to safepoints, but I digress.

[^dyn]: The root-level indirection through a volatile is not the same as dynamic binding.  The latter is another level of indirection for Vars marked as `^:dynamic`.

[^linking]: However, some Var root values not marked as `^:dynamic` can be directly linked, but this occurs at the caller's site and is not a property of Var's themselves.


### protocols

