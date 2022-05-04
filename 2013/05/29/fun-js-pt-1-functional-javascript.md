*I've written a book entitled [Functional JavaScript](http://www.functionaljavascript.com) due out in print sometime in June 2013.  In the book I introduce and explore various techniques for writing code in a functional style using the [Underscore](http://www.underscorejs.org) library.  As much as I would have loved to delve into the ecosystem of functional JS libraries, I didn't want to distract from the core functional topics in the book.[^apx]  Therefore, I present a series to explore a few of my favorite [functional JavaScript libraries](http://blog.fogus.me/tag/fun.js)[^incl] focusing primarily on the feature(s) that make them interesting, innovative or unique.[^map]*

## Functional JavaScript

[Oliver Steele's Functional JavaScript library](http://osteele.com/sources/javascript/functional/) is the first functional library that I discovered; about 4-5 years ago.[^beyondjs] It provides all of the normal higher-order functions like `map`, `reduce` and `filter`.  For example, to square the numbers in an array one would normally write the following:

    function square(n) { return n * n }
    
    map(square, [1, 2, 3, 4]);
    //=> [1, 4, 9, 16]

This is known technology by now, but [at the time of its release](http://osteele.com/posts/2007/07/functional-javascript), Functional JavaScript was fostering a style of programming that hadn't gained a foothold in the larger JavaScript community.[^perhaps]

However, something that Functional JavaScript provided, that to this day has not been fully leveraged[^strfn] are its deliciously insane (in a good way) lambda string literals.  Using the string lambda feature the same code as above can be written as:

    map('n*n', [1, 2, 3, 4]); 
    //=> [1, 4, 9, 16]

So as you'll notice, the whole `square` function was replaced by the string `'n*n'`.  The mechanism behind this mojo is pretty interesting to read, so I highly advise studying it deeply to see some true JS-wizardry in action.  

In a nutshell, the string lambda is parsed into a string format representing a function's parameters and body text and passed on to the `Function` constructor to build a real-life function.  The Functional JavaScript core functions will build real functions from string lambdas whenever they're given a string where a function argument is expected.  The one downside of using the string lambda form is that it does not create a closure.  Observe that the following code uses a closure to scale an array:

    function scale(a, m) { 
      return map(function(n) { return n*m }, a);
    }
    
    scale([1,2,3], 10);
    
    //=> [10,20,30]

In short, the `m` argument is captured by the inner function passed to `map` and used as the scale factor.  The follow, using a string lambda does not build a closure:

    function scale(a, m) { 
      return map('n*m', a) ;
    }
    
    scale([1,2,3], 10);
    
    //=> [0,2,6]

That's weird. How did it get those numbers?  

The problem is that since a closure **[cannot][fun]** be created,[^proof] Functional JavaScript just parses and sets `m` as another parameter thus building a 2-arg function that is called on every element in the array.  As you might have guessed, Functional JavaScript does the same thing as `Array#map`. That is, when the 2-arg function is called the second argument `m` is assigned the array index of the current element.  Therefore, the actual calculations occurring are:

    1*0 //=> 0
    2*1 //=> 2
    3*2 //=> 6

I could murder that `Array#map`.

My favorite feature of Functional JavaScript is the interplay between currying and the string lambdas, shown below:

    var lessThan5 = rcurry('<', 5);
    
    lessThan5(4); 
    //=> true
    
    lessThan5(44); 
    //=> false

I'll cover currying in a later post, but it's very interesting how the string lambda and currying interoperate to provide very succinct code.  

Functional JavaScript is a masterful piece of JavaScript meta-programming[^mp] and well worth exploring for its technical insights alone.

Enjoy.

*If you want to talk functional programming, my book, the library in question or anything else then feel free to comment below or email me at the address at the top of this blog post.*

:F

[^beyondjs]: Although I must have come across BeyondJS at some point during my early [LtU](http://lambda-the-ultimate.org/classic/message1677.html) lurking. Memory fails me.

[^strfn]: Reginald Braithwaite has extracted the string lambdas from Functional JavaScript into a pointed JS library at <https://github.com/raganwald/string-lambdas>. Do evil. Enjoy.

[^proof]: You can see this in action by substituting the string lambda `'n -> n*m'` instead -- which will just blow up instead of returning the wrong answer.

[^perhaps]: And perhaps FP hasn't still caught on in the larger JS community, but it's gaining ground and [I hope my book will help in some way](http://www.amazon.com/Functional-JavaScript-Introducing-Programming-Underscore-js/dp/1449360726/?tag=fogus-20).

[^apx]: I cover a few in an appendix, but did not go as deeply as I would have liked given unlimited time and page count.

[^incl]: Including, but not limited to: [Functional JavaScript by Oliver Steele](http://osteele.com/sources/javascript/functional/), [Lemonad](http://functionaljs.org/), [RxJS](http://reactive-extensions.github.com/RxJS/), [Bilby](http://bilby.brianmckenna.org/), [Allong.es](http://allong.es/), [D3](http://d3js.org/), [Reducers](https://github.com/Gozala/reducers), [Underscore-contrib](https://github.com/documentcloud/underscore-contrib), [Mori](https://github.com/swannodette/mori) and [Bacon.js](https://github.com/raimohanska/bacon.js).

[fun]: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function?redirectlocale=en-US&redirectslug=JavaScript%2FReference%2FGlobal_Objects%2FFunction

[^mp]: I've toyed with the idea of writing a JS metaprogramming book, but I think there's no market for it and besides, I'm burned out on writing for now.

[^map]: Seriously, who needs yet another blog post on closures, `map`, `reduce`, `filter`, `__proto__` and the like?  I'll hit on those topics from time to time, but they will not be the focus of these posts.