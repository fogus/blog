Let's start with a premise, indulge me if you please:

* You're creating a system for some client
* You're using a dynamic programming language like Clojure, Ruby, Io, etc...

How do you know your system works properly?  One answer is that you write unit tests.  However, as Evan Farrer wrote in his excellent blog post, "[Unit testing isn't enough. You need static typing too."](http://evanfarrer.blogspot.ca/2012/06/unit-testing-isnt-enough-you-need.html?m=1). I think this he's absolutely correct; unit testing is not enough. But...

## Static typing is not enough. You need contracts too.

However, while static typing will catch a very large class of errors that you may never discover with unit testing, they will not catch everything.  There are a certain class of errors that are caused by subtle, broken relationships between inputs to any given function or method and their results.  This is where [contracts can really save you](http://www.github.com/fogus/trammel).[^agda]

[^agda]: The programming language Agda is exploring some extremely interesting techniques that may even allow one, with the proper encoding, to move many of these contractual consistency checks into the realm of static analysis.

## Contracts are not enough. You need generative testing too.

But how do you know that your contracts are valid?  That is, how do you know that you've identified them all?  Likewise, how do you know that the contracts that you've specified are consistent?  One way of tackling this problem is a class of verification where tests are either inferred directly from the contract specifications, or you may need to use a limits [specification language](http://www.haskell.org/haskellwiki/Introduction_to_QuickCheck) to effectively restate the contracts.  In either case the intent is to target the assumptions of functions and methods with a mass of data inputs.

## Generative testing is not enough. You need simulation testing too.

So you've written specifications (or had them inferred) that validate that your local assumptions hold true.  What about the behavior of the system as a whole?  It's unlikely that generative testing will provide help there.  Instead, another approach may be to create a testing harness to simulate parts of your system talking to other parts in different aggregations up to the level of the user interacting with the system qua system.  The power in this approach comes ex post-facto.  That is, I assume you would collect system activity into some external store that is then independently verified for accuracy.

## Simulation testing is not enough.  You need fuzzing too.

I hope that you remembered to cover more than the happy path in your simulation tests.  If not, then you probably missed the opportunity to check if your system works in the presence of massive chaos and confusion (i.e. the real world). [Fuzzing](http://en.wikipedia.org/wiki/Fuzzing) should help you here.

## Fuzzing is not enough. You need luck too.

> In my experience, there is no such thing as luck.
> -- Ben Kenobi

The odd thing about fuzzing is that it may not hit on the magical incantation that breaks your system.  [That's the problem with randomness; you can never be sure](http://dilbert.com/strips/comic/2001-10-25/).  

## Luck is not enough.  You need user testing too.

What if you created the wrong system?  You may have observed every step in this post, but what you'll have created is merely a verifiable piece of garbage.

I hope the intent of my post is clear.

**In software development, no one thing will save your ass.**[^2]

This is daunting to say the least. In the face of all of this complexity[^1] and uncertainty why on earth would anyone willingly become a programmer?

The answer is simple.

All you need is love.

Love Driven Development.

:F

[^1]: And this is far from a complete list.

[^2]: And sadly, doing all of these things may not save your ass either.