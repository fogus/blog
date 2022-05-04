*I've written a book entitled [Functional JavaScript](http://www.functionaljavascript.com) due out in print sometime in June 2013.  In the book I introduce and explore various techniques for writing code in a functional style using the [Underscore](http://www.underscorejs.org) library.  As much as I would have loved to delve into the ecosystem of functional JS libraries, I didn't want to distract from the core functional topics in the book.[^apx]  Therefore, I present a series to explore a few of my favorite [functional JavaScript libraries](http://blog.fogus.me/tag/fun.js)[^incl] focusing primarily on the feature(s) that make them interesting, innovative or unique.[^map]*

## Bilby

A self-contained functional library, Brian McKenna's [Bilby](https://github.com/puffnfresh/bilby.js) stretches the possibilities of functional style in JavaScript.  It's worth exploring Bilby to learn its entire feature set, but one that is particularly nice is its implementation of multimethods.[^funjs-mm]  Brian touts his use of [category theory](http://www.amazon.com/Category-Computer-Scientists-Foundations-Computing/dp/0262660717/?tag=fogus-20) in Bilby, but that's a red herring that detracts from its powerful, yet simple, capabilities and ideas.

For example, with Bilby is you can define functions that dispatch on any number of interesting conditions.  Bilby provides a module system called environments that aggregate related methods and properties:

<pre class="prettyprint">
    var noAnimals = bilby.environment();
</pre>

Before adding a multimethod I can define a few helper functions:

<pre class="prettyprint">
    function voice(type, sound) {
      return ["The", type, "says", sound].join(' ');
    }
    
    function isA(thing) {
      return function(obj) {
        return obj.type == thing;
      }
    }
    
    function say(sound) {
      return function(obj) {
        console.log(voice(obj.type, sound));
      }
    }
</pre>

Using these helpers I can tell Bilby:

  1. The name of the method
  2. A predicate that checks the arguments[^curry]
  3. An action function that performs the method behaviors

The `Environment#method` takes the three arguments listed above and returns a *new* environment:

<pre class="prettyprint">
    var cats = noAnimals.method('speak', 
                                isA('cat'), 
                                say("mew"));
</pre>

As shown, adorning an environment with a new multimethod returns a new environment.  I can now call `speak`:

<pre class="prettyprint">
    cats.speak({type: 'cat'});
    // (console) The cat says mew
</pre>

Adding a new polymorphic behavior is simple:

<pre class="prettyprint">
    var catsNDogs = cats.method('speak', 
                                isA('dog'), 
                                say("woof"));
</pre>

And calling `speak` with a dog object works as expected:

<pre class="prettyprint">
    catsNDogs.speak({type: 'dog'});
    // (console) The dog says woof
</pre>

And cats still sound like cats:

<pre class="prettyprint">
    catsNDogs.speak({type: 'cat'});
    // (console) The cat says mew
</pre>

Of course, I can match on an arbitrary condition within the dispatch predicate:

<pre class="prettyprint">
    var animals = catsNDogs.method('speak',
      function(obj) {
        return isA('frog')(obj) && 
               (obj.status == 'dead');
      },
      say('Hello ma, baby!'));
</pre>

So passing in a dead frog works all the same:

<pre class="prettyprint">
    animals.speak({type: 'frog', status: 'dead'});
    // (console) The frog says Hello ma, baby!
</pre>

I love the idea of the immutable environments and look forward to the chance to take advantage of them for a project.  Of course, Bilby provides more than multimethods, including a [trampoline](http://raganwald.com/2013/03/28/trampolines-in-javascript.html) that allows you to return functions, monadic structures,[^fantasy-land], a [crazy-insane operator overloading form](https://github.com/puffnfresh/bilby.js/blob/master/src/do.js), validation helpers and much more.

*If you want to talk functional programming, my book, the library in question or anything else then feel free to comment below or email me at the address at the top of this blog post.*

:F

[^fantasy-land]: Bilby is not currently [Fantasy Land compatible](https://github.com/puffnfresh/fantasy-land), but should be soon(ish).  I hope to write about FL in the near(ish) future.

[^curry]: The `isA` function is an example of a curried function, up to 2 parameters.  A curried function is one that returns a closure for each of its logical arguments minus one.  When it gets the last argument the function is executed with all of the historical arguments given to it.  All talk more about curried functions in the future, but the key is that this behavior is very different than partial application such as provided by `_.partial` or `Function#bind`.

[^funjs-mm]: Bilby's multimethods are similar to the `dispatch` function defined in chapter 5 of *[Functional JavaScript](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20)* but more robust and flexible.

[^apx]: I cover a few in an appendix, but did not go as deeply as I would have liked given unlimited time and page count.

[^incl]: Including, but not limited to: [Functional JavaScript by Oliver Steele](http://osteele.com/sources/javascript/functional/), [Lemonad](http://functionaljs.org/), [RxJS](http://reactive-extensions.github.com/RxJS/), [Bilby](http://bilby.brianmckenna.org/), [Allong.es](http://allong.es/), [D3](http://d3js.org/), [Reducers](https://github.com/Gozala/reducers), [Underscore-contrib](https://github.com/documentcloud/underscore-contrib), [Mori](https://github.com/swannodette/mori) and [Bacon.js](https://github.com/raimohanska/bacon.js).

[^map]: Seriously, who needs yet another blog post on closures, `map`, `reduce`, `filter`, `__proto__` and the like?  I'll hit on those topics from time to time, but they will not be the focus of these posts.