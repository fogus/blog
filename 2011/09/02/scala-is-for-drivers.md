*this post inspired by Volkswagen's excellent marketing campaign 'Drivers Wanted'*

An unfortunate meme[^fud] dogging Scala is the notion that it is [a notoriously complicated language](#link-removed).  For better or worse, I do believe that the lion-share of this general perception is fueled by both fear and lack of familiarity.  That's not to say that Scala doesn't have its share of complexities, instead the fearful perception doesn't map to the realities of the language experienced after consistent usage.  Having said all of that, I think it's important to note that Scala is designed to solve very hard problems and because of this there is a definite set of highly abstract ideas that, if one is unfamiliar with it or languages like it, possess a very sharp learning curve.  However, an important point to ponder regarding learning curves is this:

> The complexity of a language cannot be defined solely 
> in terms of the pain felt in the initial stages of
> learning.
>
> -- me

Let's try to attach a picture to this idea.  

# Scala's cliff of insanity

If someone (me again) familiar with both Scala and Java were to describe the complexity curve of the *former* it might look something like this:

![scala-cliff](http://blog.fogus.me/wp-content/uploads/2011/09/scala-complexity.png "The Cliff of insanity")

A description of this image could be described as such:

1. The initial smoothness is indicative of a simple, low-ceremony `"Hello World"` example and the fact that it is super-easy to write Java-flavored Scala
2. The massive spike is indicative of the reality that Scala is a very powerful language chock full of powerful abstractions, syntactic flexibilities, and paradigm-ness[^paradigm]
3. Eventually one comes to learn how the powerful features can be properly used and how robust and maintainable Scala code is constructed
4. However, another spike hits when one learns that the powerful abstractions are [building blocks for even more powerful abstractions](https://github.com/scalaz/scalaz)
5. Eventually, the complexities of the language itself become second nature, make sense in the context of the whole, and stay out of the way,[^typeclasses] leaving one to concentrate on the problems at hand

This complexity curve is indicative of the most powerful programming languages.[^langs]


# Java's cliff of inanity

If someone familiar with both Scala and Java were to describe the complexity curve of the *latter* it might look something like this:

![java-cliff](http://blog.fogus.me/wp-content/uploads/2011/09/java-complexity.png "The Cliff of inanity")

A description of this image could be described as such:

1. The initial smoothness is indicative of a simple, but ceremonious `"Hello World"` example and the fact that Java is designed for ease of introduction [^pithy]
2. The moderate spike is indicative of the reality that good object-oriented programming is hard
3. However, while the complexity curve's slope decreases, it continues to climb.  The reason for this is that Java is chock full of incidental complexities, language foibles, and type system inconsistencies
4. Alas with the help of powerful IDEs, one can eventually come to gripes with the foibles.  However, the language never gets out of your way.  There is a definite, [tangible limit to the abstractions provided by Java](http://sites.google.com/site/steveyegge2/language-trickery-and-ejb) and these limitations constantly impose additional complexities on the already complex problems one is trying to solve

This complexity curve is indicative of [partially powered programming languages](http://www.yesodweb.com/blog/2011/08/perils-partially-powered-languages).


# Cars

I can't stand cars.  Nothing is more boring to me than car talk.  I like a car that will get me from here to there with reasonable fuel efficiency and safety.  That's it.  I can appreciate the beauty and power of an Alfa Romeo 8C Competizione, but it doesn't serve my current needs.  

<img src="http://blog.fogus.me/wp-content/uploads/2011/09/car-300x220.jpg" alt="The height of enterprise offerings" title="car" width="300" height="220" class="aligncenter size-medium wp-image-3545" />

However, if I ever have the need for a fast get-away[^zombies] then my car will fight me at higher-speeds and then eventually simply break-down.  Clearly my car is not designed to meet the needs of a fast-paced environment and because I've never had such a need neither am I.

Scala is an Alfa Romeo.

Drivers wanted.[^drivers]

:F

[^typeclasses]: That's not to say that there is no room for improvement in Scala's handling of powerful abstractions.  For example, the hoops required to facilitate the use of [type classes](http://debasishg.blogspot.com/2010/06/scala-implicits-type-classes-here-i.html) in Scala are extremely cumbersome.  Scala would be better served by a first-class syntactic treatment of type classes, but I alas I have not thought this through to its logical end and am ready to be convinced otherwise.

[^paradigm]: *My language has more paradigms than yours* coming soon to a t-shirt near you.

[^zombies]: No one expects the zombie apocalypse.

[^drivers]: However, there is a lot of effort spent on the matter of Scala's perception of complexity.  I'm of the opinion that those involved in its development and marketing should just step back and say "Yeah.  So what?  So is Spring.  Programming is hard and only set to get harder and Scala can help you meet these growing complexities."  Any time spent on the development of Scala that does not directly benefit "drivers" is time wasted IMO.

[^langs]: Some of these powerful programming languages are found in my blog post about [Perlis Languages](http://blog.fogus.me/2011/08/14/perlis-languages/).

[^pithy]: A alternative, but wholly inflammatory way to describe this post can also be stated as: Scala helps programmers solve hard programming problems - Java helps human resources solve staffing problems.  But I thought this might be a little over the top.  Funny yes, but definitely over the top.

[^fud]: FUD?
