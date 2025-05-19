---
title: "Arities as pseudo-protocol"
author: "Fogus"
date: "2025.04.23"
---

# Arities as pseudo-protocol

Recently the Clojure team released an alpha version of the core.async library that includes a new library named [Flow](https://clojure.github.io/core.async/flow-guide.html). While the new functionality is amazing and is already inspiring devs to use it, this post is not about Flow. Instead, I'd like to talk about a small part of the Flow API where process behavior is implemented in terms of [step-fns](https://clojure.github.io/core.async/flow-guide.html). Simply put, step-fns are functions of four arities, where each arity is called at key points in the life-cycle of a process to provide configuration information or perform behaviors.

The four arities are as follows:

- 0 Describe the step-fn by returning a data-structure describing its boundaries
- 1 Returns the initialization state for the enclosing process given compatible arguments 
- 2 Called every time a life-cycle transition occurs with a state and transition key and returns an updated state
- 3 Called in a loop for every message received by the enclosing process with a state, input, and message and returns a tuple of the transformed state and a map o receivers -> messages

For more information, see the [official Flow guide](https://clojure.github.io/core.async/flow-guide.html).

Long-time Clojure programmers will immediately wonder why the function arity approach is used in lieu of a protocol such as the following:

    (defprotocol StepFn
      (describe [_])
      (init [_ arg-map])
      (transition [_ state trans])
      (transform [_ state input msg]))

Clojure devs are accustomed to defining protocols for just such occasions, and indeed a protocol would have worked for step-fns also. As always, there are trade-offs for choosing one approach over another, so I'll talk about that. But first, I would like to invite the reader to squint their eyes a bit and see the lines between the two blur.

## Bags of names and bags of numbers

The protocol `StepFn` above defined a set of functions. Each function in the set defines two things that define how a protocol dispatches on its target: name + arity. On the other hand, a function alone defines a set of arities alone. That is, Clojure dispatches to the function body associated with the arity corresponding to the arguments count. These are different, but are similar in ways that choosing a protocol over function arities is worth some careful consideration.

Rather than outlining the motivations for using protocols, I'll defer to the [existing protocols documentation](https://clojure.org/reference/protocols) as a guide. Instead, I'd like to talk about the benefits of using an arity pseudo-protocol.

## Benefits of arity dispatch

To avoid burying the lede, let me just enumerate the primary benefits of leveraging function arity in a Clojure API:

- fn-punning
- Hot reloading
- Good names
- Composition

As a Lisp programmer, I value [interactivity](https://blog.fogus.me/2022/11/10/the-one-about-lisp-interactivity.html) in my development process very highly in my hierarchy of developer needs. Flow facilitates interactivity by leveraging [Var](https://clojure.org/reference/vars) Var fn-punning. A Var is a reference to a value within a context -- usually a namespace. If the value held in the Var is a function, then we can call the Var itself as a function and it will delegate the call down to the function that it holds. This is one form of fn-punning in Clojure. The benefit of this form is that we can use the Var as a stand-in for a step-fn in flow and if we later redefine the function that the Var holds then the process calling it will automatically get the new behavior. Another practical benefit is that when errors occur we will get better names in the error output if Vars are used instead of functions. Certainly the message `Execution error (ArithmeticException) at user/flub` looks much better than `Execution error (ArithmeticException) at user/eval141$fn`. Finally, while it's not likely to be a major win for step-fns in Flow, function composition allows users to augment behaviors in ad hoc ways.

## Downsides of arity dispatch

Aside from the fact that leveraging arity dispatch is an under utilized technique,[^transd] there is one big downside of using that technique. That is, because a function only defines its dispatch on a set of numbers (arities), if you ever need more functionality that clashes with an existing arity then you're in the soup. Additionally, by attaching expected behavior to arities, you lose the descriptive benefit that the names in a protocol provide.

> Familiarity is half of popularity
> 
> -- Alison Frane

As with anything in programming, choosing one approach over another requires an analysis of the trade-offs. Both protocol dispatch and arity dispatch are tools with upsides and downsides and while the former covers many cases that the latter does, there are some compelling reasons to choose a function instead.

:F

[^transd]: Although, the technique is also used in the [transducers API](https://clojure.org/reference/transducers#_creating_transducers) and the benefits of function composition dominates those of hot reloading and fn-punning.
