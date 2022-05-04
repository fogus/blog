Listening to [Cafe Apres-Midi:Olive](https://www.amazon.com/Cafe-Apres-Midi-Olive/dp/B00004W9T5/?tag=fogus-20) I'm reminded that I was once a die-hard adherent to strong statically typed languages. Since that time I've moved into more dynamic languages, but static thinking and programming still holds a spot in my heart. Below I'll just briefly about the aspects of static languages and type systems that made a deep impression on me.

Things I've found to be true
============================

## Design facilitation

There's a lot of incentive for putting a lot of effort into up-front design for a well-known domain using a strong statically typed language. A big benefit comes from modeling your domain such that known illegal states are, well, illegal to even represent with the data model.

## If it compiles it's correct

A lot of thought, preparation, design, and tears go into making this happen, but when modeling a well-known domain using a strong static type system you can use the compiler to prove the implementation itself. It took a lot of understanding and thought to get to this point (for me) but when I did, it was a thing of beauty.

Things that I've found to NOT be true
=====================================

## There is no connection to morality

There's a pernicious thread in the static typing "community" that tries to tie the use of dynamically typed languages to moral irresponsibility. While in a twisted way I can understand the chain of thought, I'm highly skeptical of any actual link. Despite the somewhat libertine sensibilities, dynamic language practitioners are, IME, just as rigorous as anyone when it comes to creating robust systems.

## No noticeable slow-down

A view that's gained some traction is that static languages slow down the development cycle because they're less amenable to exploratory programming. I can't say for sure if that's true, but in my time I found that the allocation of programming time was more backloaded with static languages compared to dynamic, but I can't say that I've ever observed an overall development-cycle slow-down.

## No fewer bugs

I've not noticed an overall decrease in production bugs using strong static languages, but I will say that oft times potential bugs were caught earlier. 

---

On the zeitgeist there's an increasing interest in the States in stories and articles easily summarized as "dying alone in Japan." I wonder what this says about the zeitgeist...

:F