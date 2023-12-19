Compiling Clojure to Javascript pt. 1 of n
-------------------------------------------

*this is the first entry in an n-part [series explaining the techniques and design principles of ClojureScript](http://blog.fogus.me/tag/clj-compilation). translations: [[日本語](http://ykomatsu.akaumigame.org/compiling-clojure-to-javascript-pt1-ja.html)]*

<a href="http://blog.fogus.me/wp-content/uploads/2011/07/random-image-from-library-of-congress.jpeg"><img src="http://blog.fogus.me/wp-content/uploads/2011/07/random-image-from-library-of-congress-300x262.jpg" alt="" title="clojurescript is better than an epic beard" width="300" height="262" class="aligncenter size-medium wp-image-3363" /></a>

Over the past couple of months I've been working with the [Clojure/core](http://clojure.com) and [friends](https://github.com/clojure/clojurescript/contributors) to develop [ClojureScript](https://github.com/clojure/clojurescript) -- a new Clojure compiler targeting JavaScript.  This post is about a couple of the approaches that we've taken and the practical use of the [Google Clo**S**ure compiler](http://code.google.com/closure/compiler/).

Given the arity overloaded function below:

    (fn
     ([t] t)
     ([x y] y)
     ([a b & zs] b))
    
The Clo**j**ureScript compiler currently compiles it into something[^0] like this:

    (function() {
     var foo = null;
     var foo__5675 = function(t) {
       return t
     };
     var foo__5676 = function(x, y) {
       return y
     };
     var foo__5677 = function(a, b, zs) {
       zs = Array.prototype.slice.call(arguments, 2);
       return b
     };
     foo = function(a, b, zs) {
       switch(arguments.length) {
         case 1:
           return foo__5675.call(this, a);
         case 2:
           return foo__5676.call(this, a, b);
         default:
           return foo__5677.apply(this, arguments)
       }
       throw"Invalid arity: " + arguments.length;
     };
     return foo
    })();

This is perfectly legal Javascript code, and as a nice bonus is fairly performant.  However, the Clo**j**ureScript compiler `cljsc` provides an [optional pass](https://github.com/clojure/clojurescript/wiki/Quick-Start) that compiles the generated JavaScript to JavaScript using the [Google Clo**s**ure compiler](http://code.google.com/closure/compiler/).[^1]  The code that comes out of this second pass is really pretty cool:

    (function() {
     var d = null, c = function(b, a) {
       Array.prototype.slice.call(arguments, 2);
       return a
     };
     return function(b, a) {
       switch(arguments.length) {
         case 1:
           return b;
         case 2:
           return a;
         default:
           return c.apply(this, arguments)
       }
       throw "Invalid arity: " + arguments.length;
     }
    })();

Notice that the two helper functions `foo__5676` and `foo__5677` are now inlined.  Google's Clo**s**ure compiler is a true optimizing compiler providing powerful dead-code elimination.  In fact, on its highest optimization setting, the Clo**s**ure compiler actually generates the following for the definition above:

    
    // intentionally blank
    
Why?  Because the original function is never actually called.  In the words of Rich Hickey:

> Party!

## Let's not get crazy though

The Clo**s**ure compiler is very powerful, but some choices in the way that Clo**j**ure compiles were chosen so as not to rely too heavily on its optimizations.  For example, we could use a common trick for compiling `let` blocks (`let` is a structure that provides lexical bindings), namely, to convert:

    (let [a 1 b 2 a b] a)

Into:

    (function() {
      return (function foo__2313 (a) {
        return (function foo__2314 (b) {
          return (function foo__2315 (a) {
            return a;
          }(b));
        }(2));
      }(1));
    })();
    
and let Google's Clo**s**ure compiler transform that into something like the following:

    (function() {
      var a = 1;
      var b = 2;
      return b;
    })();

But instead, Clo**j**ure's compiler uses renaming to simulate lexical bindings and generates something like the following:

    (function (){
      var a__847 = 1;
      var b__848 = 2;
      var a__849 = b__848;
    
      return a__849;
    })();

Where Clo**j**ure's compiler can optimize without performing whole-program analysis and dead-code elimination it will.

## Wholly Pragmatic

Clojure is doggedly pragmatic in the way that it defers to the JIT for  runtime performance and also in its superior Java interop.  ClojureScript is likewise pragmatic in that it [leverages](https://github.com/clojure/clojurescript/wiki/Google-Closure) the [Google Closure tools](http://code.google.com/closure/) for its implementation and minification strategy.  For any platform that Clojure targets now and in the future, the question will always be asked: *where are the libraries*?  There were many potential choices for ClojureScript, but Closure, while not perfect, was the best match given the [motivating forces behind ClojureScript](https://github.com/clojure/clojurescript/wiki/Rationale).

## Conclusion

So what is the advanced compilation output[^2] from Google's Clo**s**ure compiler for the last snippet?

    2

Party indeed.

:F

[^0]: All caveats apply.  The generated code samples are subject to change over time, but I hope the point is clear nonetheless.

[^1]: Closure?  Is there no better name for Google to have chosen?

[^2]: Actually, in a statement context, of which I will talk about in a later post, the code `(let [a 1 b 2 a b] a)` again compiles to `//nothingness`, but I hope the point is clear nonetheless.
