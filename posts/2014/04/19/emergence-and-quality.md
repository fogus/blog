Although the blogging bug has not hit me lately, I have been thinking about bloggable topics.  One interesting topic that I'm mulling around in my mind at the moment is the idea of emergence in programming language design.  The catalyst for this focus was my recent research into the processes that board game designers use to create games.

## Emergent strategies and idioms

First, one of my favorite designers [Kory Heath](http://www.koryheath.com/), has extensive design notes for two of my favorite games [Zendo](http://www.koryheath.com/zendo/design-history/) [^0] and [Uptown](http://www.koryheath.com/uptown/design-history/). [^1]  In both design histories you'll see themes that are very reminiscent of the same themes that pop up when talking to language designers about their creations.[^2]  It's eerie the parallels between the two worlds.  I suppose this parallel explains my [recent interest in the process of board/card/dice game](http://blog.fogus.me/2013/10/31/open-source-boardcarddice-game-design/) design.[^3]

A point that I particularly like espoused by Mr. Heath is that idea of [emergent strategies](http://web.archive.org/web/20061119095146/http:/wunderland.com/WTS/Kory/JoEGD/Discussion/heath14.html).  The idea of emergent strategy reminds me of programming language idioms.  Idioms in languages come about for various reasons, but the best idioms emerge from the language itself.  That is, the strongest idioms emerge during the use of the language and play on and highlight the language's strengths[^str].  It's almost as if the language itself begs for a given idiom to arise.  It's a topic I want to ponder much more -- in both game design[^nb] and language design.

[^nb]: Another game designer that I admire is [Nick Bentley](http://nickbentleygames.wordpress.com/category/game-designs/), the creator of the amazing [Catchup](http://nickbentleygames.wordpress.com/2012/04/29/my-best-game-i-suspect-ketchup/).

## Quality

The second topic that I've been thinking about concerns quality.  There is a very interesting discussion by Daniel Solis and (again) Kory Heath about differing approaches to achieving quality in their designs.  There are many nuances in play during the discussion, but I hope that a representational summary is as follows:

 * Heath: Hold back a design until it feels perfect
 * Solis: Quality will emerge (there's that word again) from quantity

You can watch the video below to regain some of the nuance, but I believe that summarizes the discussion fairly well.

<iframe width="560" height="315" src="//www.youtube.com/embed/aUiSDjSJMyg?list=UUVMiK5E4VEq7FPCNBsw1hJQ" frameborder="0" allowfullscreen></iframe>

This dichotomy really hits home for me, especially in the way that I view, and have viewed, open source software.  That is, until about 13-months ago I believed that more was better in open-source and by releasing package after package I could contribute a body of work that was not only useful in isolation, but could point to an underlying philosophy of design.  However, [after spending nearly a year on a single system](https://www.youtube.com/watch?v=1E2CoObAaPQ) my viewpoint on the matter has changed completely.  That is, my view now is that I want to hold off on code and designs until I feel like they tell a story by themselves.[^z]  Practically speaking this means that I'll be releasing far less software than I have in the past.  I still want to contribute to open source of course, and am still active in [Clojure](http://www.clojure.org), [Datomic](http://datomic.com), and [_.contrib](https://github.com/documentcloud/underscore-contrib); however for my own designs I want to strive for something more.

:F

[^z]: As it turns out, I have decided *not* to release [Zeder](https://gist.github.com/fogus/7118257) as open source.  After spending a year working on it and its encompassing system, I've come to the realization that I can do better.  That's not to say that I'm disappointed in its design, indeed it could be useful to many people.  Instead, to release Zeder would put me in a position where I would feel compelled to maintain it for the next X number of years, and that is something that I cannot bring myself to do with a system that I do not believe is perfect.

[^str]: Rather than patch a language flaw.

[^0]: My son calls Zendo, the ultimate brain-game.

[^1]: The ultimate "Chillaxing" game.

[^2]: Specifically I'm reminded of a conversation that I had with José Valim about [Elixir](http://elixir-lang.org/) two StrangeLoops ago.

[^3]: Another thing that I'm sure drives my along this trajectory is that I struggle tremendously on game design, so it's really started firing neurons that I didn't know that I had.