The most interesting programming languages moving forward, in my opinion, are languages targeting an existing host platform.  Regardless of your opinion of the following languages, I think that the reason for their buzz, power and (eventual?) success is due to their embrace of their host targets:

* [Elixir](http://elixir-lang.org/) is a programming language targeting the [Erlang virtual machine](http://www.unlimitednovelty.com/2009/01/cutting-edge-of-vm-design.html).
* [CoffeeScript](http://coffeescript.org/) is a language that compiles down to JavaScript
* [Clojure](http://clojure.org) is a Lisp that targets the Java Virtual Machine and JavaScript
* [Visi](http://visi.io/) is a language that targets the cloud via a Haskell and Objective C-based runtime
* [Roy](http://roy.brianmckenna.org/) is a statically typed language that targets JavaScript
* [JRuby](http://www.jruby.org) is a Ruby variant on the JVM
* [Cobra](http://cobra-language.com/) is an OO language with builtin support for contracts and unit tests targeting .NET and Mono
* [Shen](http://www.shenlanguage.org) is a beautiful statically (optional) typed Lisp variant targeting Common Lisp, JavaScript, the JVM and possibly more targets

There are more of course, but these represent a nice sample of the most exciting offerings.  Where they differ from other languages is that they embrace established, well maintained and/or popular systems.  That is, they leverage a host platform to their advantage.

Moving into the future I believe one would need to be gutsy, ill-informed or insane (or a mix of the three) to create a language runtime from scratch.  That's not to say that it shouldn't *ever* happen, in fact for "systems" languages like [Rust](http://www.rust-lang.org/) and [Go](http://golang.org/) it makes a lot of sense to start from whole cloth.  However, for higher-level languages leveraging an existing runtime is a huge win.  One has only a finite amount of time to burn, and it's a weighty choice to want to spend it recreating[^gc] garbage collectors, virtual machines and the like.  That's not to say that there's no room for improvement in those areas, but I think at this point it's easier to make a mark in programming languages or compilation techniques than in garbage collection or VMs.

So if I were insane[^1] enough to create a programming language for use by others, I would, without hesitation pick one of the following hosts:

* [Factor](http://factorcode.org/): the concatenative programming language has a stunning compiler and runtime
* GHC: the Haskell compiler and extensions with the appropriate runtime libraries
* Java Virtual Machine: world-beating megatech
* JavaScript itself or a specific JavaScript targets like Node.js or V8
* Erlang's virtual machine
* A Common Lisp implementation
* [Racket](http://www.racket-lang.org) via its [`#lang`](https://gist.github.com/3840018) feature

There are other targets of note,[^libs] especially [LLVM](http://llvm.org), [Parrot](http://www.parrot.org), [LuaJit](http://luajit.org), [PyPy](http://pypy.org/) and [Camlp5](http://pauillac.inria.fr/~ddr/camlp5/) but I'm not terribly familiar with the way that they solve the ["library problem"](https://github.com/clojure/clojurescript/wiki/Rationale).

I'm not likely to create a programming language at any point this decade, but langdev is one of those topics that I love to think about.

:F

[^1]: I use insane with all deference to those insane enough to create wonderful languages.

[^libs]: If you're not inclined to limit yourself to full-blown hosts, then there are langdev elements such as garbage collectors, shells, parsers and the like available as libraries.

[^gc]: That's not to say that you shouldn't try.  I think every programmer should try to write a garbage collector or a virtual machine.  It builds character.