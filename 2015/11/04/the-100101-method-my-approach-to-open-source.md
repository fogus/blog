</a>For many years I was whole hog into the open-source movement and at the time I rarely wrote a stitch of code in my free time that didn't find itself in a public Sourceforge/GCode/Github repo.[^more]  However, over time I came to learn that the burden that came with publicly offering code to the universe, to put it mildly, sucked.  Aside from the fact that much of the code that was "released" was sub-par, the very act of putting code out into the world implied (whether [intentionally](https://en.wikipedia.org/wiki/Authorial_intent), or [not](https://en.wikipedia.org/wiki/Reader-response_criticism)) a willingness to participate in a social contract[^moral] with those who chose to use it for their own purposes.  Granted I'm not necessarily against that social contract per se, instead my eventual change of heart around releasing code became such that I was more reserved in my approach.  The approach that I now use for releasing code into the wild is governed by an approach called the "100:10:1 method," a term coined by Nick Bentley.[^bent]

## What even is the 100:10:1 method?

I won't belabor the details of the method, you can read more about it [from the perspective of a talented game designer](https://nickbentleygames.wordpress.com/2014/05/12/the-100-10-1-method-for-game-design/), but I'll try to frame it in the context of my approach to open source software development.

The 100:10:1 method has three parts, each described below.

### Write down 100 wacky ideas

The first step was to find a notebook and a pen and just write down 100 ideas for interesting open source projects.  These project ideas ranged across all manner of topics, depth, and quality.  I thought of wild language ideas, new features in existing projects, system designs, protocols, missing documentation, interesting forks, golfing code, games, prototypes, implementations of paper ideas, second-systems, whatever.

![notes](http://blog.fogus.me/wp-content/uploads/2015/11/notes-300x119.jpg)

Prior to incorporating the 100:10:1 method, I had always had a notebook filled with wacky ideas, but they tended to be hither and thither and never in one place.  Laying 100 ideas out on a few pages let me really focus my attention on them all at once, allowing me to weigh them against each other for the purposes of the second step.

### Make a MVP for 10 of those ideas

Having 100 ideas laid out in front of me let me really get a grasp on the ideas that I not only felt interested in pursuing at that time, but also those that I felt could benefit not only myself, but others as well.[^oss]  Of the original 100 I picked 10 projects that I was certain I wanted to dive into and explore.  For lack of a better term I picked the 10 projects that I wanted to write a minimum viable "product" for all -- *concurrently*.

![proto](http://blog.fogus.me/wp-content/uploads/2015/11/vs-300x169.png)

Now you might be thinking that 10 concurrent projects is in itself a burden.  This would not be an incorrect assessment.  However, I realized that minimum viable implementations for most of the projects that I chose would probably never see the light of day and if they did it would be months to years before that time.  You see, at one time I had viewed releasing open source software as a method for putting a potentially useful tool into the hands of others.  Sadly, over time that view had degenerated into the idea that releasing code into the wild was in itself good -- the method had supplanted the original goal.  My view now is that it's highly likely that the code that I'm working on in my spare time is only an exploration that may or may not bear fruit -- and that's OK.

A nice benefit of working on 10 concurrent projects is that when I feel that one has stagnated I can simply move on to another that motivates me more.  As a person with a rich hobby, community, and family life outside of mere coding, it benefits me immensely to use the power of a bevy of choice to battle fleeting interest.  Having 10 concurrent projects means that there's always one that can grab my immediate interest.  I've learned (the hard way) that when my full interest is directed on a task I'm much more efficient in my production.  Eventually however, one of these projects will bear fruit[^rotten] and when that happens I'm ready for the final stage.

### Fully develop one of those ideas into a legitimate software release

At some point during the 10-stage, one of the projects will inevitably turn into something valuable.[^viable]  When that happens I then take that one project and really work to turn it into a quality software artifact.[^keepgo]  This means to go all the way and ensure a robust system with solid examples, tests, documentation, stable API, blog posts and/or talks, and even a web-presence perhaps.[^logo]

![z](http://blog.fogus.me/wp-content/uploads/2015/11/proj.png)

Up until this point the code for said project was for my own purposes and would likely need a lot of work to make it into something that I'd be proud to share with others.  It depends on the complexity of the software of course, but it's likely that the final stage takes the longest amount of time to realize.  Likewise, it's at this final stage that I'm most likely to scrap a project entirely.  I've found (again, the hard way) that if I can't bring myself to turn a codebase into something that I'm willing to write documentation, examples, and tests for then there's zero chance that I'll be willing to maintain it for the next X years.

## Conclusion

I've used this method for some time now, and prior to that I followed something similar and have found good results so far.  That is, both [Zeder and Patagonia](https://www.youtube.com/watch?v=1E2CoObAaPQ) were developed using the 100:10:1 method, or some facsimile of it and I have a Clojure library named "Tathata" that's in the final stages right now.  Perhaps one of my 10 current projects[^gd] will bear similar fruit, but if not that's OK, I'll just pick 10 more.

![tree](http://blog.fogus.me/wp-content/uploads/2015/11/100-10-1-300x171.png)

I've got plenty of time -- a lifetime indeed.

:F

*Thanks to [Carin Meier](https://twitter.com/gigasquid), [Rich Hickey](https://twitter.com/richhickey), [Justin Gehtland](https://twitter.com/jgehtland), and [Paul deGrandis](https://github.com/ohpauleez) for reading and providing feedback to a draft of this post.*

[^moral]: And unfortunately there seems to be a tendency to view open sourcing as a moral statement as well.

[^viable]: Or not... what me worry?

[^logo]: And of course a finalized name and logo. :p

[^rotten]: And sometimes a project will rot on the vine.  In this case I have no qualms about trashing it and promoting another from the list of 100.  Indeed, the list of 100 is a living list and things drop off and come on all the time.

[^keepgo]: When that one project surfaces, I still work on the other 9 concurrently, just at the more superficial, MVP-esque level.  If the fancy strikes me I might even pick another from the list of 100 to elevate... there are no hard and fast rules about this method of course.

[^gd]: As you can see, I apply the 100:10:1 method to other creative hobbies as well.  From coding to music to writing to game design, it works for any number of creative activities.
[^oss]: Because if we're not creating open sourced code to benefit others, what's the point.  

[^more]: As it turns out, my other hobbies (game design, Sabremetrics, writing, etc.) followed a similar trend in the past, but like open source coding they too have fallen into the 100:10:1 ideal.

[^bent]: The name "100:10:1 method" comes from [a post by Nick Bentley describing his game design approach](https://nickbentleygames.wordpress.com/2014/05/12/the-100-10-1-method-for-game-design/).  While my own views on open source coding had already become somewhat similar to Nick's, it was his post that gave me a name and base structure to my ad hoc approach.  Thank you Mr. Bentley, wherever you are!