*I've written a book entitled [Functional JavaScript](http://www.functionaljavascript.com) due out in print sometime in June 2013.  In the book I introduce and explore various techniques for writing code in a functional style using the [Underscore](http://www.underscorejs.org) library.  As much as I would have loved to delve into the ecosystem of functional JS libraries, I didn't want to distract from the core functional topics in the book.[^apx]  Therefore, I present a series to explore a few of my favorite [functional JavaScript libraries](http://blog.fogus.me/tag/fun.js)[^incl] focusing primarily on the feature(s) that make them interesting, innovative or unique.[^map]*

The [allong.es](http://allong.es/) library by [Reginald Braithwaite](http://www.twitter.com/raganwald/) provides a bevy of useful function combinators in its arsenal.  However, an additional aspect from my perspective, and something that I didn't cover in depth in [Functional JavaScript](http://www.functionaljavascript.com) is its support for stateful iterators:

<pre class="prettyprint">
var iterators = require('./allong.es').iterators
var take      = iterators.take,
    map       = iterators.map,
    drop      = iterators.drop;

var ints = iterators.numbers(0);
</pre>

Aside from the necessary import séance required to get the correct allong.es iteration functions, I also defined an iterator `ints`, over all numbers.  I can then "perform" an operation over the `ints` iterator:

<pre class="prettyprint">
map(ints, function(n) { return n * n });

//=> function () {
//      var element;
//      element = iter();
//      if (element != null) {
//        return unaryFn.call(element, element);
//      } else {
//        return void 0;
//      }
</pre>

I think [Xergiok said it best](http://www.youtube.com/watch?v=DuNHOHJ7SIw&t=0m12s).

<iframe width="430" height="242" src="http://www.youtube.com/embed/DuNHOHJ7SIw#t=12" frameborder="0" allowfullscreen></iframe>

You see the thing about iterators like those found in allong.es is that they are built using chains of functional combinators.  Instead of performing calculations right away, the iterator calculations are deferred by essentially wrapping them inside of functions and returning those instead.  We can chain these allong.es iterator library calls to an arbitrary depth, as shown below:

<pre class="prettyprint">
var squares = take(drop(map(ints, function(n) {
  return n * n;
}), 100000), 100);
</pre>

Just for fun I squared all of the integers, dropped the first 100,000 results and then grabbed the next 100.  The magic of the allong.es iterator is that I've not actually performed any calculation yet; so let me perform one:

<pre class="prettyprint">
squares();
//=> 10000000000
</pre>

As shown, every chained calculation is wrapped in a function and deferred until I explicitly ask for it.  That is, only when I query the iterator using an external iterator (in the case above, just calling a function) will any of the calculations occur.  I can also use an external iterator like `for` to gather up some calculations:

<pre class="prettyprint">
var coll = [];

for (var i = 0; i < 100; i++ ) {
  coll.push(squares())
}

coll;
//=> [10000200001,
//    10000400004,
//    10000600009,
//    10000800016,
//    10001000025,
//    10001200036,
//    ...
//    10020010000,
//    undefined]
</pre>

I can check the math by manually squaring the number 100,001 (because I already calculated the 100,000 * 100,000 case):

<pre class="prettyprint">
100001 * 100001;
//=> 10000200001
</pre>

And as shown, the manual calculation matches with the first element in the `coll` array.  As you might have guessed, the `undefined` on the end is due to the fact that the iterator's first calculation was already consumed.  That's one downside of stateful iterators; once you call it the deferred calculation is run and can never be run again.  I'd love to see a stateless version of the allong.es iterators.

There is too much in allong.es and about iterators in general to do justice here.  I highly recommend you [explore allong.es yourself](http://allong.es/try/).  In addition, there is a book that motivates, describes most of the allong.es offerings and its underpinnings entitled *[JavaScript Allongé](https://leanpub.com/javascript-allonge)* also by the inimitable Braithwaite.

## A quick review of JavaScript Allongé by Reginald Braithwaite

While not exclusively a book about functional programming, JavaScript Allongé will provide a solid foundation for functional techniques.  However, you'll not be beaten about the head and neck with dogma.  Instead, every section is motivated by relevant dialog and fortified with compelling source examples.  If you look in the front of the [free sample of the book](http://samples.leanpub.com/javascript-allonge-sample.pdf) (PDF link) you'll see a list of interesting highlights in one of the forewords by some dude no one's ever heard of:

* It makes a great companion to the book I have in my brain
* It’s very well written
* It’s got good thinking
* It thanks Friedman and Felleisen
* It’s not too hung up on the “idea” of classes

Taking each of these points in turn, I think I can elaborate a bit.

### A great companion

I would say that JavaScript Allongé makes a great companion to [my book](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20).  In fact, I humbly offer the following reading order for those of you interested in exploring a functional JavaScript style more deeply:

 1. [JavaScript: The Good Parts](http://www.amazon.com/JavaScript-Good-Parts-Douglas-Crockford/dp/0596517742/?tag=fogus-20)
 2. [Functional JavaScript](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20)
 3. [Effective JavaScript](http://www.amazon.com/Effective-JavaScript-Specific-Software-Development/dp/0321812182/?tag=fogus-20)
 4. JavaScript Allongé

These are *the* books to learn functional JavaScripting.
 
### Well written

Come on!  It's Raganwald.  If you've not read his other books nor his prolific blogging output, then by all means [start with Homoiconic](https://github.com/raganwald/homoiconic) and track down the rest of his words right now.

### Good thinking

This is harder to pinpoint, but the gist is that the approach in the book is highly functional in style:

 * Reducing mutation
 * Favoring purity
 * Fostering and using function combination

The book is quite opinionated, but it never comes off as dogmatic.  I think readers will appreciate the tone.

### A little Scheming

Reginald thanks Daniel Friedman and Matthias Felleisen in his book.  These are two authors and thinkers who have also deeply influenced my own thinking, writing and programming styles.  One excellent book worth reading is *[The Little Schemer](http://www.amazon.com/The-Little-Schemer-4th-Edition/dp/0262560992?tag=fogus-20)*.[^littlejs]

### Almost without class

Finally, JavaScript Allongé covers to some degree class-based programming techniques, but it's certainly not the focus of his book.  I personally prefer a functional style, but I can appreciate the merits of class-based, object-oriented programming where it fits.  There are enough books about this style of programming, so I can appreciate that Reginald took a functional bent for his book because I think that there is a lot of teaching to do regarding functional programming in JavaScript.

I liked JavaScript Allongé very much and I think that from the beginner to the experienced JavaScripter to the expert, we can all learn from its lessons.

:F

[^littlejs]: Crockford created [The Little JavaScripter](http://javascript.crockford.com/little.html) which is inspired by this great book.

[^apx]: I cover a few in an appendix, but did not go as deeply as I would have liked given unlimited time and page count.

[^incl]: Including, but not limited to: [Functional JavaScript by Oliver Steele](http://osteele.com/sources/javascript/functional/), [Lemonad](http://functionaljs.org/), [RxJS](http://reactive-extensions.github.com/RxJS/), [Bilby