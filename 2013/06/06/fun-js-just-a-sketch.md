*I've written a book entitled [Functional JavaScript](http://www.functionaljavascript.com) due out in print sometime in June 2013.  In the book I introduce and explore various techniques for writing code in a functional style using the [Underscore](http://www.underscorejs.org) library.  As much as I would have loved to delve into the ecosystem of functional JS libraries, I didn't want to distract from the core functional topics in the book.[^apx]  Therefore, I present a series to explore a few of my favorite [functional JavaScript libraries](http://blog.fogus.me/tag/fun.js)[^incl] focusing primarily on the feature(s) that make them interesting, innovative or unique.[^map]*


In this installment I'm just going to sketch a functional JavaScript library that does not exist, but that I wish did[^maybe]... for fun I'll call it Undermine.

I'll start it like I start all of my JS libraries:

<pre class="prettyprint">
var exists = function(x) { return x != null; };

var truthy = function(x) { 
  return (x !== false) && exists(x); 
};
</pre>

These two functions are just meant to simplify JS truthiness.  The library itself starts with `filter`:

<pre class="prettyprint">
var _filter = function(fun) {
  return function(coll) {
    var results = [];
    if (coll == null) return results;
    coll.forEach(function(value, index, list) {
      if (truthy(fun.call(null, value))) results.push(value);
    });
    return results;
  };
};

function filter(fun) {
  var coll = arguments[1];

  if (exists(coll)) return _filter(fun)(coll);

  return _filter(fun);
}
</pre>

... and then `map`:

<pre class="prettyprint">
var _map = function(fun) {
  return function(coll) {
    var results = [];
    if (coll == null) return results;
    coll.forEach(function(value, index, list) {
      results.push(fun.call(null, value));
    });
    return results;
  };
};

function map(fun) {
  var coll = arguments[1];

  if (exists(coll)) return _map(fun)(coll);

  return _map(fun);
}
</pre>

To see `map` in action just run the following:

<pre class="prettyprint">
function double(n) { return 2*n; }
 
map(double, [1,2,3])
//=> [2, 4, 6]
</pre>

But both `filter` and `map` are implemented in a funny way.  That is, they only return a result if all of the expected arguments are given, otherwise they return a closure that expects a collection.  This is called currying.  Let's see it in action:

<pre class="prettyprint">
var doubleAll = map(double);
 
doubleAll([1,2,3]);
//=> [2, 4, 6]
</pre>

Seems pretty straight-forward no?  Although I curry these 2-arg functions manually, I can write a function to curry 2-arg functions automatically:

<pre class="prettyprint">
function schonfinkel(fun) {
  return function(first) {
    return function(last) {
      return fun(first, last);
    };
  };
};
</pre>

The `schonfinkel` function curries a function of two parameters, from left to right.

Oh look! Here's a function:

<pre class="prettyprint">
function greaterThan(l, r) {
  return l > r;
}

greaterThan(500, 10);
//=> true
</pre>

Here's a curried version:

<pre class="prettyprint">
var maybeGT = schonfinkel(greaterThan);
</pre>

And its usage:

<pre class="prettyprint">
maybeGT(5)
//=> function(last) { ... }
 
maybeGT(5)(10000000);
//=> false
</pre>

Whoops.  The problem is that the curried application ordering is wrong, so let's create a function to `flip` the application order:

<pre class="prettyprint">
function flip(fun) {
  return function() {
    return fun.call(null, arguments[1], arguments[0]);
  };
}
</pre>

And so the new function becomes:

<pre class="prettyprint">
var gt = schonfinkel(flip(greaterThan));
 
gt(5)
//=> function(last) { ... }
 
gt(5)(10000000);
//=> true
 
gt(5)(1);
//=> false
</pre>

You know what?  I forgot `reduce`.  Here it is, following a *different currying pattern*:

<pre class="prettyprint">
var _reduce = function(fun, seed, coll) {
  var acc = seed;
  coll.forEach(function(value) {
    acc = fun.call(null, acc, value);
  });
  return acc;
};
 
function reduce(fun) {
  var count = arguments.length;
  var seed = arguments[1];
  var coll = arguments[2];
  var result;
 
  switch(count) {
  case 1:
    result = schonfinkel(_reduce.bind(null, fun));
    break;
 
  case 2:
    result = _reduce.bind(null, fun, seed);
    break;
 
  case 3:
    result = _reduce(fun, seed, coll);
    break;
  }
 
  return result;
}
</pre>

`reduce` is more complicated and inverted compared to `map` and `filter`, but that's only because I want to show the use of partial application (in the `result = schonfinkel(_reduce.bind(null, fun))` snippet).  Since `reduce` assumes that it gets a `fun` then said `fun` can be partially applied and the resulting function from `Function#bind` Schonfinkeled... I mean curried.  Partial application is different than currying.  A partially applied function is always ready to rock, but a curried function keeps returning closures until the last arg is given, only then is it executable.

Let's see `reduce` in action:

<pre class="prettyprint">
function sum(x,y) { return x + y; }
 
reduce(sum, 0, [1,2,3,4,5]);
//=> 15
</pre>

Seems correct no?  What about a curried version:

<pre class="prettyprint">
var summate = reduce(sum, 0);
 
summate([1,2,3,4,5]);
//=> 15
</pre>

So what's the point of all this?  The goal in a library like Undermine is to strive to facilitate the description of programs as a series of actions that should occur.  A function that can help execute such a series is as follows:

<pre class="prettyprint">
var pipeline = function(/*, funs */){
  var funs = Array.prototype.slice.call(arguments);
 
  return function(seed) {
    return reduce(function(l,r) { return r(l); },
                  seed,
                  funs);
  };
};
</pre>

That's a bit subtle, but let's see it in action:

<pre class="prettyprint">
var gimmeTheThingTHETHING = pipeline();

gimmeTheThingTHETHING(5);
//=> 5
</pre>

So a `pipeline` that's given nothing returns a function that just returns whatever's given it.  What about if `pipeline` is given something?

<pre class="prettyprint">
var doubler = pipeline(double);

doubler(5);
//=> 10
</pre>

So the closure returned by `pipeline` calls the captured function argument in the beginning with whatever's passed into it.  What about if I supply more functions?

<pre class="prettyprint">
var quadrupler = pipeline(double, double);

quadrupler(5);
//=> 20
</pre>

Seems right no?  I can now use `pipeline` to describe a sequence of actions to take to perform some task:

<pre class="prettyprint">
var tasks = pipeline(
  map(double)
  , filter(gt(5))
);
 
tasks([1,2,3,4,5]);
//=> [6,8,10]
</pre>

The `tasks` are described as:

1. First double everything
2. then take everything >5

And that's exactly what the code says!  Pipelines can also be strung together:

<pre class="prettyprint">
var moreTasks = pipeline(
  tasks
  , reduce(function(x,y) { return x*y }, 1)
);

moreTasks([1,2,3,4,5]);                                                                                                
//=> 480  
</pre>

Pretty cool huh?  That's all for [Undermine](https://gist.github.com/fogus/5722992).  There's more stuff like this in my book [Functional JavaScript](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20).

Enjoy.

:F

*Thanks to [Michael-Keith](https://twitter.com/segfaultax) for the idea for a better currying approach.*

[^apx]: I cover a few in an appendix, but did not go as deeply as I would have liked given unlimited time and page count.

[^incl]: Including, but not limited to: [Functional JavaScript by Oliver Steele](http://osteele.com/sources/javascript/functional/), [Lemonad](http://functionaljs.org/), [RxJS](http://reactive-extensions.github.com/RxJS/), [Bilby](http://bilby.brianmckenna.org/), [Allong.es](http://allong.es/), [D3](http://d3js.org/), [Reducers](https://github.com/Gozala/reducers), [Underscore-contrib](https://github.com/documentcloud/underscore-contrib), [Mori](https://github.com/swannodette/mori) and [Bacon.js](https://github.com/raimohanska/bacon.js).

[^map