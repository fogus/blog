Like most players of games, I have had ideas for my own designs over the years. My medium of choice for realizing design ideas are typically existing game systems. By "system" I mean a set of pieces that do not, in themselves, constitute a game but instead provide a flexible framework for creating new games.[^langdev] One of my favorite game systems are the lovely [Looney Pyramids](https://www.looneylabs.com/pyramids-home) from [Looney Labs](https://www.looneylabs.com/). The following games were designed over the course of years and most are still WiP. After the list I'll share some meta ideas about game design for the pyramids themselves and in general. (sorted in order of my favorite)

[^langdev]: Game design is very much like programming language design. Indeed, the word "game" in this post could be replaced with "language" without missing a beat.

 0. [Martian Whist](http://www.fogus.me/fun/spiel/martian-whist/index.html) -- *"Solving" trick-taking mechanics for Looney Pyramids has been an obsession for years and is still an ongoing process. I've landed on a serviceable mechanism but I still think that it could  be better. I think that this one could be great eventually.*

 1. [Logistics][l1] -- *I actually think that this is a legitimately good game[^c] and as a fan of [combinatorial abstracts](https://en.wikipedia.org/wiki/Abstract_strategy_game) easily my favorite instance in this group. The capture powers could be better.*

 2. [Cydonia][l2] -- *I also think that this is a good game too, but the number of pyramids required might be prohibitive large. This was my first attempt at trick-taking for pyramids and I think the game could be simpler.*
	
 3. [Toripoka][l3] -- *A neat micro card game that could use more play-testing.*

 4. [Gorgias][l4] -- *My entry into the Yahtzee family with (I believe) a novel "battle" resolution scheme for pyramids.*
	
 5. [Quux][l5] -- *A little connection game designed purely to be a "breakfast game." Needs more play-testing to see if there are any killer strategies -- I suspect that there are.*
 
 6. [Malice][l6] -- *A version of [Alice Chess](https://en.wikipedia.org/wiki/Alice_chess), informed by [Martian Chess](https://www.looneylabs.com/rules/martian-chess).  I enjoy it, but there is a decided lack of [clarity][lc] which may be unavoidable.*

 7. [Pew Pew, Die][l7] -- *My version of Roshambo using no pyramids at all, but instead a [pyramid die](https://www.looneylabs.com/pyramids-equipment).*
	
 8. [CarboniteDice][l8] -- *A variant of [IceDice](https://www.looneylabs.com/games/icedice) meant for solitaire play. Interestingly, this is probably my most-played pyramid game by *other players*.
 
 9. [Initiative][l9] -- *A game of perfect information that sadly can be very cold. Much more thinking needed here.*
 
 10. [Pungo][l10] -- *Basically a game designed just to explore the "controlled roll" mechanism.*
	
 11. [Coin Hijinks][l11] -- *An adaptation of [Pink Hijinks](https://www.looneylabs.com/games/pink-hijinks) for pocket change.*

 12. [Twitwheel](http://www.fogus.me/fun/spiel/twitwheel/index.html) -- *A solitaire mancala-like game that needs more work.*

 13. Pink Poppycock -- *An adaptation of [Pink Hijinks](https://www.looneylabs.com/games/pink-hijinks) to use a "controlled roll" meta-mechanism that I've played around with for years.*

Nothing earth-shattering in these games for sure, but all in all not too bad IMO.  There are a few games on this list that I think I can see myself (and others hopefully) actually enjoying.  This to me is a success.  Additionally, I learned some valuable lessons over the past year that I'd like to share.

[l1]: http://fogus.me/fun/spiel/logistics/
[l2]: http://fogus.me/fun/spiel/cydonia
[l3]: http://fogus.me/fun/spiel/toripoka/
[l4]: http://fogus.me/fun/spiel/gorgias
[l5]: http://fogus.me/fun/spiel/quux/
[l6]: https://github.com/fogus/spiel/blob/master/pyramidenspiel/malice/README.markdown
[l7]: http://fogus.me/fun/spiel/pewpewdie/
[l8]: http://icehousegames.org/wiki/index.php?title=CarboniteDice
[l9]: http://icehousegames.org/wiki/index.php?title=Initiative
[l10]: http://icehousegames.org/wiki/index.php?title=Pungo
[l11]: https://github.com/fogus/spiel/blob/master/taschenspiele/coin-hijinks/README.markdown
[l12]: http://looneylabs.ning.com/forum/topics/pink-poppycock
[lc]: http://www.cameronius.com/games/shibumi/browne-elegance-5.pdf

## Game ideas are worthless

Over the years I dreamt up dozens of game ideas that didn't turn into games.  That is, there's a huge distinction between coming up with an idea for a game and actually creating a game that anyone would want to play.  Additionally, once you've come up with an idea that may be playable, there's no guarantee that anyone will want to play it.  Even further, even if you come up with an idea that someone might want to play, then play-testing might highlight some fatal flaws or, at best, sticking points.  By this I mean that I've come up with plenty of other pyramid games...

 * Karankaron: Zendo, but for sound
 * Pedestal: a blind bidding game
 * Pyrametto: Coloretto, but for pyramids
 * Hollywood Babylon: Babylon, but for pyramids
 * Helmet: a 2p abstract checkers-like
 * Panopticon: a 2p abstract racing game
 * A Kamisado-like using the "controlled roll"
 * Minimax: a 2p engine-building game
 * A tile-laying game using pyramids and Iota cards
 * and many others not worth mentioning

...but in every case writing out the rules and/or play-testing exposed some deep flaw --"boring" being the most prevalent.  Therefore, it struck me that ideas alone are worthless and when they turn out to have been bad from the start they should be thrown away.  Eventually, more will pop up to replace them.

Additionally, you'll notice that about half of the games listed are derivative.  One thing that I've found is that it's super-easy to take a game that already exists and "adapt" it to pyramids.  However, often I've found that the vehicle of the pyramids doesn't always fit the original game.  Instead, either the derivation doesn't work at all or the pyramids themselves inform a mutation of the original design to the extent that it's practically a new game (e.g. Malice above).  I think that this is likely the most important lesson that I've learned in the past year.  That is, if you're designing an pyramid game then listen to the pyramids... they'll help guide you, and wow are they lovely guides indeed.

## Play-testing is a pain for all involved

I won't harp on this point too much, but I will say a few words.  First, I have a problem with play-testing.  That is, I always feel very timid asking someone to play my own designs.  I always feel like I'm burdening them in some way.  Thankfully, I have two awesome kids who're always willing to play my games, no matter how bad they are.  Even more lucky for me is that they are not shy about shooting down my designs when they are truly boring.  That said, I still have a hard time asking people outside of my sphere of influence to help me to play-test.

## Writing rules is hard

I've written a few books and so when it was time to start writing rules for these games I was confident, but was quickly humbled.  Of the entire game design process I still think that writing lucid rules for even the most simple of games is the hardest.  Writing programming books does not translate to game rules sadly. I'm still not sure that I've even risen to the level of mediocre in this regard.  However, I've found time and time again that writing down rules, even very early on, is a great way to find problems in the design itself.  A few times (and more as I got more experience) the act of simplifying the words used to express the rules led to the simplification of the designs themselves.  In fact, I plan to go back to the earliest designs and simplify the rules in hopes that I can find opportunities to simplify the games themselves.

## When in doubt... Volcano scoring

Almost as hard as writing rules is coming up with fair scoring mechanisms.  Indeed, devising solid scoring is directly antithetical to having a problem with asking people to play-test your games.  That is, very often (or so I've read) scoring mechanisms are finely tuned via a piece-wise change process occurring over the course of many many many play-tests.  None of my designs have gone through a process like this.  Thankfully for me [Volcano](https://www.looneylabs.com/content/volcano) scoring already exists, and it usually works right out of the box, it's simple, and has been in rotation in pyramid games for 20+ years.  Perhaps when/if these games get more plays I'll move away from Volcano scoring in those that use it, but for now I consider it a nice starting point.

## The goal of game design

Finally, game design for me is less a goal and more a vehicle for sharpening my mind in various ways. Certainly, it would be wonderful to create a game that gained massive popularity, but if that happens at all it'll be an accident. 

I hope that you've enjoyed this post. Please feel free to share your own designs and thought-processes in the comments.

:F

[^c]: Thanks to its heavy borrowing from [Cups](http://boardgamegeek.com/boardgame/19916/cups).
