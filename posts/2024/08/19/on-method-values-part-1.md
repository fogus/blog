---
title: "On method values, part 1"
author: "Fogus"
date: "2024.08.19"
---

Prior to [discovering Clojure back in 2007](https://blog.fogus.me/2007/10/24/linkage-20071024/), I was a full-time Java programmer. This was long before method handles, method references, lambda expressions, and invokedynamic, so viewing Java through a functional programming lens was not second-nature to me. Regardless, during my early Clojure explorations I fully expected code like the following to "just work":

```clojure
    (defn up-all [strings]
      (map .toUpperCase strings))
    ;; Unable to resolve symbol: .toUpperCase
```

That is, I[^cdevs] fully expected Clojure to recognize that `.toUpperCase` referred to the instance method `String.toUpperCase` as a value, and infer how to "do the right thing" with it for each element in the `strings` collection handled by `map` even though Clojure could never know what it might hold. 

Of course, it eventually occurred to me that the actual way to do what I wanted was to wrap the method call in a function instead:

```clojure
    (defn up-all [strings]
      (map (fn [s] (.toUpperCase s)) strings))
    
    (up-all ["a" "b" "c"])
    ;;=> ["A" "B" "C"]
```

Once I learned of this fact, I put it into my tool-belt and moved on. But I could have asked the question of why it didn't work as I expected and even at that early time I think that Clojure could have supported it... almost.

[^cdevs]: Most new Clojure devs expect this to work I suspect.

> *Spoiler warning: [Clojure 1.12.0](https://github.com/clojure/clojure/blob/master/changes.md#changes-to-clojure-in-version-1120) has support for method values, and is feature complete for the formal release soon.*
> Follow along with this post using the 1.12.0 release candidate run with the following Clojure CLI 1-liner:
> 
> clj -Sdeps '{:deps {org.clojure/clojure {:mvn/version "1.12.0-rc1"}}}'

## Why Method Values

Adding features to a programming language is not a casual process. New features need to first solve a problem and second need to fit within the gestalt of the language. Sometimes problems are aesthetic, sometimes they address complexities or common mistakes, and sometimes they address bugs. As I showed, the lack of method values wasn't a bug in Clojure because there was an idiomatic and straight-forward way to wrap method calls with functions. Aesthetically, writing `.toUpperCase` is certainly fewer characters than `#(.toUpperCase %)` and is arguably cleaner, but few Clojure programmers were clamoring for the reduction of 4 characters in such cases. Instead, the problems that method value solve fall into the complexities category.

Clojure provides a hinting syntax that adds metadata to applicable syntactic elements that signify their types. If you fail to use hinting in cases where type information will help resolve ambiguity then your program can run slower.

### Incidental reflection

The function example above is flawed in a way that becomes evident with the addition of a line of code:

```clojure
    (set! *warn-on-reflection* true)
    
    (defn up-all [strings]
      (map (fn [s] (.toUpperCase s)) strings))
    ;; REFLECTION WARNING
    
    (up-all ["a" "b" "c"])
    ;;=> ["A" "B" "C"]
```

The problem is that Clojure cannot resolve which class the `.toUpperCase` method belongs to until using reflection at run time to determine the type of its argument. Reflection is a wonderful tool for [interactive programming](https://blog.fogus.me/2022/11/10/the-one-about-lisp-interactivity/) but it's slower than direct invocation that you get with resolved methods using type hints:

```clojure
    (map (fn [s] (.toUpperCase ^String s)) ["a" "b" "c"])
    ;;=> ["A" "B" "C"]
```

### Loss of type generality

Manual hinting is a fine way to help Clojure resolve method contexts, but using type hinting reduces the generality of a function. This becomes more clear with the following example:

```clojure
    (defn absv [^long n] (Math/abs n))
    
    (map absv [-1 -2 3])
    ;;=> [1 2 3]
```

The `absv` function[^absv] works fine for longs, but does not generalize to doubles because of the manual hinting:

```clojure
    (map absv [-1.1 -2.45 3.14159])
    ;;=> [1 2 3]
```

[^absv]: The `absv` function above is illustrative for this post only... use the Clojure core function `abs` instead. :)

### Loss of arity generality

The function `(fn [s] (.toUpperCase ^String s))` works fine for single strings, but the `.toUpperCase` method is overloaded on arity allowing an additional `java.util.Locale` object argument. The function taking a single argument `s` would clearly not work for users wishing to call the 2-arity case.

Any method value implementation should work to solve the complexities listed, and since the manual solution involved function wrapping, we decided that method values should become Clojure functions that call the named method. The specifics of the generated functions were iterated on, but the general idea was that the function arities would correspond to the arities of the method. However, we decided very early on that Clojure should support method value syntax not only in value position, but also in invocation position.

## Qualified methods in invocation position

Prior to Clojure 1.12.0, Java interop calls in invocation position took one of three forms: `Classname/staticMethod`, `.instanceMethod`, or `Classname.` for constructors. There are problems with re-purposing the existing invocation syntaxes for value position. First, value position method values are by nature context agnostic, therefore deriving an instance method's target type is impossible without some additional information available only at runtime. Second, the three syntaxes are asymmetric in their form. Finally, as a technical point the syntaxes were sugar that Clojure translated into various configurations of the `.` and `new` special forms. Any new syntax had to address all three of these considerations.

Therefore, we moved to what we called "qualified methods", which is an expansion of the existing static method symbolic form to both instance methods and constructors:

- `Classname/staticMethod`
- `Classname/.instanceMethod`
- `Classname/new` for constructors

Importantly, Clojure already knew how to resolve class names in symbol prefixes, so moving to this cleaner syntax allowed us to leverage existing code-paths. This allowed us to double down on providing strong feature semantics using existing support. Additionally, having the prefix class in hand for all three cases allows Clojure to use it as essential method resolution contexts, even in invocations:

```clojure
    (map (fn [s] (String/.toUpperCase s)) ["a" "b" "c"])
    ;;=> ["A" "B" "C"]
```

The implications for supporting qualified methods naturally led to an elegant implementation approach that I'll discuss in the next post. But for now it's worth noting that by moving to this syntax for instance methods required some extra consideration. That is, circumstances can arise where the qualifying class in a qualified instance method call can contradict the type of the target object, for example:

```clojure
    (map (fn [s] (String/.toUpperCase ^Object s)) ["a" "b" "c"])
    ;;=> ["A" "B" "C"]
```

For qualified instance method calls, it made sense to always use the qualifying class for the purpose of resolving the instance method to invoke. 

## Qualified methods in value position

Of course, because qualified methods contain the context class for the method we can leverage that information to resolve methods in otherwise context-free value positions. However, there are deeper complexities in play that further complicate resolution in value position that I'll discuss presently.

### Method value resolution

The different ways to refer to the instance method `Double/.isNaN` and the static method `Double/isNaN` eliminate the first layer of ambiguity. However, a bigger problem is how to resolve type-overloaded methods like `Math/abs` that take any of primitive doubles, floats, ints, or longs? The obvious solution was to somehow attach signature information to the method value itself like `Math/abs-long`. However, this approach is not conducive to programmatic generation (i.e. in macros) as it requires symbolic munging and deconstruction by every programmer who wants to deal with method values. Instead, Clojure already has an elegant and programmer-friendly way to attach information to forms, namely via metadata.

#### Explicit resolution

We already saw how to use type hints to resolve the `.toUpperCase` method. So it felt natural to add a new kind of metadata to help resolve method values. That is, any qualified method with a metadata mapping of the keyword `:param-tags` to a vector of zero or more legal type hints will signal to Clojure the desired overload and arity of the method to use in the generated function call. We added a shorthand for this metadata using a new metadata syntax `^[... tag hints ...]` for it:

```clojure
    ^[long] Math/abs
    ;; resolves to only the static method taking 1 primitive long
```

This is analogous to the `:tag` metadata, and any legal `:tag` value is legal inside of the param-tags vector. Further, we also allow the special hint `_` in the param-tags vector that signals a kind of "any type" in that place of the signature. Further, the param-tag metadata provides arity declaration by default. These capabilities allow programmers to param-tags to convey their intent for a specific arity and the minimum types to resolve to a single method signature. Opting into this intentional specification means that any existing ambiguity is an error condition.

#### Inference

As you might guess, sometimes NO param-tags might be enough to disambiguate a method if there are no type nor arity overloads at all. For example, the method `java.time.OffsetDateTime/.getYear` is not overloaded at all and therefore resolves without the use of param-tags at all. Sadly our old friend `Math/abs` is overloaded and therefore requires param-tags to resolve. However, I showed that Clojure already allows the use of unresolved methods by falling back to reflection at run time and therefore it felt natural to do the same for unresolved qualified methods without param-tags.

However, Clojure has always used surrounding context to help resolve interop calls. We realized that qualified method invocations could leverage not only their qualifying class for resolution context, but also the mechanisms for leveraging type flow to resolve type overloads. Take the following example:

```clojure
    (defn bat-file? [filename & {loc :loc}]
      (let [ext "bat"
            fnm (if loc 
                  (String/.toLowerCase filename loc)
                  (String/.toLowerCase filename))]
        (String/.endsWith fnm ext)))
    
    (bat-file? "AUTOEXEC.BAT")
    ;;=> true
```

The `bat-file?` function uses both qualifying class information and also type flow information to resolve the method calls within:

- The qualifying context is inherent in the qualified `String/.toLowerCase` calls
- `String/.toLowerCase` is arity overloaded, but not type overloaded at each arity, so there is no ambiguity
- `ext` is bound to a string literal that Clojure recognizes and uses as its type

While, the calls above all resolve, there may be circumstances where type flow is insufficient to resolve even a qualified method call. In those cases Clojure falls back to reflective calls at run time. As I mentioned before, reflection is a tool supporting [interactive programming](https://blog.fogus.me/2022/11/10/the-one-about-lisp-interactivity/), which itself is a differentiating feature of Lisp languages in general. It was critical that qualified methods in both invocation and value position fall back to reflection on non-resolution to preserve interactivity. And of course, falling back to reflection does not preclude programmers from also using param-tags to guaranteed non-reflective cases in production-ready code.

#### The method value razor

By splitting method value resolution into explicit and inferential branches, we've provided users with complimentary options. With param-tags Clojure programmers can intentionally specifiy which method that they want and if Clojure can resolve it then they're bound to the resolved method now and forever.[^future-change] On the other hand, programmers can forego param-tags and Clojure will derive intention at compile time or at runtime via reflection.


[^future-change]: It's possible that later Java or API versions could introduce ambiguity in the minimally-specified cases (i.e. using `_`), but this has always a danger even in type-hinted code. Clojure 1.12 provides the tool to avoid future ambiguity by fully specifying a method via param-tags.

## Conclusion

Implementing method values for Clojure 1.12.0 was an incremental process that incorporated a thoughtful design process coupled with thoughtful feedback from the community. As a result I believe that we have added a feature to Clojure that's elegant in approach and in implementation (a topic for a future post). If you're interested in using the features outlined in this post then please do try the feature-complete release candidate using the following dependency coordinates.

**Clojure Deps**

    org.clojure/clojure {:mvn/version "1.12.0-rc1"}

**Leiningen**

    [org.clojure/clojure "1.12.0-rc1"]

As always, Clojure feedback is always welcomed at the following locations:

* [Q&A Forum](https://ask.clojure.org/)
* [Clojurians Slack](http://clojurians.net/)

Stay tuned for the next installment when I'll dig deeper into the implementation details and possible future directions related to method values.

:F
