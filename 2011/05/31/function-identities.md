One of the first features one will encounter when exploring Clojure (and most Lisps that I am familiar with) is that its arithmetical operations can take any number of arguments.

    (+ 1 2 3 4 5)
    ;=> 15

This feature is very convenient, but if you are a tinkerer then you may quickly encounter something about Clojure that seems odd, or at least surprising.  What happens if you pass zero arguments to the `+` function?

    (+)
    ;=> 0

What about `*`?

    (*)
    ;=> 1

For addition and multiplication, the values `0` and `1` are known as the [identities](http://en.wikipedia.org/wiki/Identity_element) for each function respectively.  Some of you may already know of this and others may intuitively get why they are this way.

## Iterative and Applicative Examples

In words, the results thus observed can be summarized as:

- The sum of no numbers is 0
- The product of no numbers is 1

Ignoring the mathematical aspects for now, how would similar behavior manifest itself in "real word" code?  An insight can be found in the way one might implement a sum function in an classically imperative way (using Ruby to illustrate):

    def summer(*args)
      result = 0
      args.each {|x| result += x}
      result
    end

An applicative solution proves more succinct, but the principle is the same:

    def summer(*args)
      args.inject(0) {|x,y| x + y}
    end

Both yielding:

    summer(1,2,3,4,5)
    #=> 15
    
    summer()
    #=> 0

As you can see, both implementations of `summer` use an accumulator (explicit in the first case, and implicit in the second) initialized to `0`.  When the function is passed no args then the looping is never performed and the initializer `0` is returned.  Pretty clear right?

## Σ and ∏

Why not simply restrict `+` to two arguments and make it signal an error when less or more are given?  Surely this would be more intuitive no?  Well, it depends on whose intuition you're talking about.  A mathematician would have no problem with the behavior outlined above. Because to a mathematician Clojure's addition function does not implement `+`, it implements something closer to <span style="font-family: times, serif; font-size:150%">Σ</span> (and in the case of `*`, <span style="font-family: times, serif; font-size:150%">∏</span>)[^summ].  For the infix plus it makes no sense to allow zero arguments, but for <span style="font-family: times, serif; font-size:150%">Σ</span> it does.  But these are simply restrictions of notation and not emblematic of any deeper truths.

## But Why?

As you've noticed, I haven't really explained why function identities are important.  Indeed, there is more that can be said about function identities, but this post is already longer than I intended, so I'll hold off on that discussion for another day.  However, for now think about this question:

> Assume that the identities for addition and multiplication were replaced by arity exceptions.  What abilities would you lose?

I'll answer this question next time.[^ans]

:F

[^summ]: Clojure's `+` and `*` is not exactly <span style="font-family: times, serif; font-size:150%">Σ</span> and <span style="font-family: times, serif; font-size:150%">∏</span>, but they're close enough for the sake of this discussion.

[^ans]: Unless someone beats me to it.
