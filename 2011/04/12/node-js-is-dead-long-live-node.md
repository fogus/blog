Funode
=====

*the development of this project has been superseded by my involvement with [ClojureScript](http://github.com/clojure/clojurescript). novus ova seclorum!*

I give [node.js](http://nodejs.org/) a ribbing from time to time, mostly in response to the propensity of its greatest adherents to view it in an unrealistic light.  My real bias is of course against Javascript.   My problem with Javascript has always been that expertise is defined more in terms of understanding its faults rather than its features.  Node.js needs to be liberated from the shackles of the unwieldy mess that is Javascript.  Node.js should become its own language forged in the embers lit by Crockford.  

## Node.js is dead.  Long live Node.

[Funode](http://github.com/fogus/funode) is my vision for a runtime that realizes the potential in Node.  [Funode](http://github.com/fogus/funode) is a functional library for Node.js -- a state of mind yielding Node.  

The greatest things to happen to Javascript are: 

* being tied to the browser
* being divorced from the browser

Node is the way forward.  [Funode](http://github.com/fogus/funode) is the map.

## The Funode Way&copy;

Why would you write this:

    function do_request(req) {
      return long_io_operation(
        req, 
        callback=function(results) {
          return create_something_from(results);
        });
    }

When you can instead write this:

    function do_request(req) {
      var result = long_io_operation(req);
      return create_something_from(result);
    }

This is but a taste! 

The Funode Way&copy; will include:

+ persistent, immutable data structures
+ coroutines
+ pure prototypal inheritance
+ functional composition
+  observable collections
+ functors
+ advice 
+ currying
+ partial application
+ combinators
+ unification
+ promises
+ pre and postconditions
+ macros
+ FSMs
+ and so much more...

The next step of course is to print some t-shirts and speak at some conferences, but then the very next step will be to change the world.

## T-Shirt Options

#### Funode: exploiting the inherent inefficiencies in nothingness

#### Use Funode! Because I/O is always slower.

#### Use <span style="text-decoration: line-through;">Erlang!</span> <span style="text-decoration: line-through;">message queues!</span> Funode!

#### Node.js is dead.  Long live Node.

Available as polo, for casual Friday.

## Changing the world

TBD

:f
