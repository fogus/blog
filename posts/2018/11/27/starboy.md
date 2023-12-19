While listening to [Starboy by The Weekend](https://www.amazon.com/Starboy-Weeknd/dp/B01MPXXBNB/?tag=fogus-20) I'm reminded about some codebases that I have to dive into more deeply. When reading codebases, be they for fun or profit, I tend to take a systematic approach to exposing them, described as:

 - <strong>Get it building and running</strong> - not always as easy as it should/could be.
 - <strong>Change code to break it</strong> - Once it's going I try to make changes to understand how and why they break things.
 - <strong>Understand all comments</strong> - Where applicable.
 - <strong>Delete all comments</strong> - To avoid biases.
 - <strong>Rename like mad</strong> - Going over the code I tend to rename functions and structures to fit my *current( understanding. This is an iterative approach as mu understanding deepens. In good codebases my final rename matches closely the original names.
 - <strong>Draw pictures</strong> - This is an ongoing and also iterative process.
 - <strong>Refactor code</strong> - Once I think I understand what it's doing I try and refactor the code to my understanding. This hearkens back to breaking things. :p
 - <strong>(Re) Add comments</strong> - Comments are added back based on the understanding achieved (or not) and modifications made.
 - <strong>Read, read, read</strong> - Supplemental reading is an ongoing act.

Some great codebases I've read and mucked around with include:

### C

  - [CLIPS](https://sourceforge.net/projects/clipsrules/) - Expert system shell and programming environment
    + Supplemental reading: *[Expert Systems: Principles and Programming](https://www.amazon.com/Expert-Systems-Principles-Programming-Fourth/dp/8131501671/?tag=fogus-20)*
  - [Emacs](https://nullprogram.com/blog/2014/01/04/) - The bytecode interpreter is especially interesting
  - [io](https://github.com/IoLanguage/io) - Prototypal programming language

### Clojure

  - [core.logic](https://github.com/clojure/core.logic) - Logic programming library for Clojure & ClojureScript
    + Supplemental reading: *[The Reasoned Schemer](https://www.amazon.com/dp/B07B9SL4LR/?tag=fogus-20)*

### Pascal

  - [The data structures book I had 1-million years ago](https://www.amazon.com/Structures-Using-Pascal-Aaron-Tenenbaum/dp/0131966685/?tag=fogus-20)

### JavaScript

  - [Functional JavaScript](https://github.com/osteele/functional-javascript) - Oliver Steele's original functional library for JS.
    + Supplemental reading: *[My own thing](https://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20)*
  - [transducers-js](https://github.com/cognitect-labs/transducers-js) - Transducers implementation in JS

### Lua

  - [Fennel](https://github.com/bakpakin/Fennel) - A Lisp compiler targeting the Lua runtime

### C++

  - [Factor](https://github.com/factor/factor) - The VM for Factor is pretty amazing

### Java

  - [Guava](https://github.com/google/guava) - Google's Java libraries
    + Supplemental reading: [The Guava Philosophy](https://github.com/google/guava/wiki/PhilosophyExplained)

### BASIC

  - [10 PRINT](https://10print.org) - A book about a tiny fragment of Commodore 64 BASIC

### Rust

  - [intermezzOS](https://github.com/phil-opp/blog_os) - A small and simple OS kernel.
    + Supplemental reading: [The book of the same codebase](https://github.com/intermezzos/kernel)

### Emacs Lisp

  - [Advice](https://github.com/emacs-mirror/emacs/blob/d0e2a341dd9a9a365fd311748df024ecb25b70ec/lisp/emacs-lisp/advice.el) - Function advice implementation

There are countless other interesting codebases to explore and over time I'll point out some more. These should keep us busy for a while. 

:F