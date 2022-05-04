Although I hoped to wait another day or two to talk about this on my blog, [circumstances](https://twitter.com/jashkenas/status/327382752393568256) have force my hand.  Therefore, I would like to take a few of your spare moments to talk about a new project that I'm working on called [underscore-contrib](https://github.com/documentcloud/underscore-contrib) -- an extensions library / proving ground for [Underscore.js](https://www.github.com/documentcloud/underscore).[^lodash]

*TLDR; The official Github repository is at <https://github.com/documentcloud/underscore-contrib> -- go there and read code.*

[^lodash]: And by extension, [Lo-Dash](http://lodash.com/).

## Why underscore-contrib?

Underscore is a great library supporting a functional style of programming in JavaScript.  While [Underscore provides a bevy of useful tools](http://underscorejs.org/) to this end, there are many more functions that can be derived from it that are of less-broad applicability.  The reasons that any given function is not available directly in Underscore are too numerous to list here, but two in particular help to define the "why" of underscore-contrib:

 1. Some functions are limited in scope.
 2. Some functions are lesser known, but are highly useful.

In the case of #1, underscore-contrib can serve as a home for functions that solve certain pointed problems; stopping somewhere below domain-specific.  In the case of #2 underscore-contrib will work as a proving ground for features that should be in Underscore proper, but need some advocacy and/or evolution (or devolution) to get them there.

## What's in underscore-contrib?

At the moment, the following contrib libraries are in place:

 * underscore.array.builders.js        - building arrays
 * underscore.array.selectors.js       - getting stuff from arrays
 * underscore.function.arity.js        - partial application/currying
 * underscore.function.combinators.js  - function combinators
 * underscore.function.predicates.js   - isX functions return true|false
 * underscore.object.builders.js       - building objects
 * underscore.object.selectors.js      - getting stuff from objects
 * underscore.util.existential.js      - truthiness
 * underscore.util.trampolines.js      - function trampolining

These libraries are not complete and do not represent the entire universe of future libraries.  Instead, most of the functions used to build the initial set were taken from my upcoming book "[Functional JavaScript](http://www.functionaljavascript.com)" and my [Lemonad JavaScript library](http://www.functionaljs.org).[^l]

[^l]: What this means for Lemonad is that it got a whole lot smaller. :-)

## How to use underscore.contrib

First, you'll need Underscore.  Next you can grab the relevant underscore-contrib libraries and simply add something like the following to your pages:

    <script type="text/javascript" src="underscore.js"></script>
    <script type="text/javascript" src="underscore.object.builders.js"></script>

At the moment there are no cross-contrib dependencies (i.e. each library can stand by itself), but that may change in the future.

## How you can help

There is still a lot of work to do around perf, documentation, examples, testing and distribution so any help in those areas is welcomed.  Pull requests are accepted, but please search the [underscore-contrib issues](https://github.com/documentcloud/underscore-contrib/issues?page=1&state=open) before proposing a new sub-contrib or addition.  Additionally, all patches and proposals should have strong documentation, motivating cases and tests.  It would be nice if we could not only provide useful tools built on Underscore, but also provide an educational experience for why and how one might use them

Other (potentially) useful sub-contribs include the following:

 * String utilities
 * Date/time utilities
 * Validators
 * Iterators
 * Generators
 * Promises
 * Monads
 * Currying
 * Laziness
 * Multimethods

What do these mean?  Well, that's up for discussion. :-)

Enjoy.

:F