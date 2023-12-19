Call me crazy, but it seems that there is a large influx of Ruby programmers exploring the Clojure programming language.  Based on the results of [Chas Emerick's great State of Clojure - Summer 2010 survey][stateofclj], 17% of surveyed Clojure users come from a Ruby background.  Based on observed trends, I expect that percentage to grow in the next round.  But why?

An Opening of Minds
-------------------

[Paul Graham's][pg] seminal essays on Lisp were an incredible boon for reawakening the public's interest in the language.  I imagine many programmers, like myself, were awe-struck when reading [Beating the Averages][ba] for the first time.  While I had previous experience with Lisp in a university setting, I was never able to crystalize my feelings on the matter.  Upon reading that essay in early 2001 I was driven to an incredible urge to "get me some of that" -- which I did by enrolling in an Artificial Intelligence graduate school program in the fall of the same year.  I believe, based on personal  conversations that there were many who had a similar urge, but chose a different path -- the path of "halfway to Lisp".[^halfway]  That is, I believe that languages[^pgrb] like Perl, Python, and Ruby managed to capture a significant portion of programmer mindshare because they not only learned their lessons from Lisp, but they put many of its powerful capabilities in the hands of their programmers.

Enter Clojure
-------------

And now years later we see that Clojure, while still humble compared to Java, C++, C, and C#, is gaining increasing popularity and mindshare in the technological zeitgeist.  In increasing cases Clojure seems to be gaining [serious consideration for][tw], if not outright adoption.  But again -- why?  In my humble opinion it can be summarized as the result of a feedback loop starting with the influence of Lisp on languages like Ruby which again feedback into an increased interest and adoption of Clojure.[^rh]

ruby.clj
------

*I would love to hear from Ruby programmers on the following observation[^observe], be it correct or wrong-headed.*

The existence of the Lisp feedback loop has lured Ruby programmers in much the same way that Lisp lured them into Ruby in the first place.  That is, Ruby programmers being the adventurous lot to begin with, are not satisfied with "halfway to Lisp".  Instead, they want it all.  That's not to say that I think (nor wish) Ruby programmers will abandon ship and run arms-wide to Clojure.  Instead, I think that our industry can support both in a complementary relationship modeled (just to name one such scenario) much like the inner workings of [FlightCaster][fc].  I already see a similar path in Clojure as previously observed in Ruby[^rubylibs].  That is, Clojure seems to be used in applications already built on cutting edge technologies (as noted by Chas in his survey results post mentioned in the beginning).  Is it really too much to suppose that this environment might also appeal to the Rubyist?

Finally
------

I've babbled on enough about this, but let me end by saying that I fully expect that the inclusion of Clojure into the Well-Grounded Rubyist's[^wgr] arsenal will work to strengthen said Rubyist's skills and indeed the language itself.  Likewise, the presence of Ruby mindshare into the Clojure community will help to motivate a continuing culture of practicality, while also maintaining cutting edge, yet practical, exploration.  

Welcome Rubyists[^jruby] -- it's a pleasure.

:f

[^pgrb]: I'm not implying that Paul Graham is the reason that these languages succeeded, only that his essays may have helped provoked similar motivations in some.

[^wgr]: To borrow a title of fellow Manning author David Black

[fc]: http://www.infoq.com/articles/flightcaster-clojure-rails

[^rh]: That's not to diminish the herculean effort by Rich Hickey, Clojure's author, in creating a truly beautiful language that stands on its own merits.  But if the industry has taught us anything, it's that the existence of superior tech does not guarantee adoption.

[tw]: http://www.thoughtworks.com/radar/

[ba]: http://paulgraham.com/avg.html

[pg]: http://paulgraham.com

[stateofclj]: http://muckandbrass.com/web/display/~cemerick/2010/06/07/Results+from+the+State+of+Clojure,+Summer+2010+Survey

[^jruby]: And with [JRuby](http://jruby.org) we can share the same runtime and libraries.



[^halfway]: Guy L. Steele in a famous quote mentioned that Java dragged C++ programmers "halfway to Lisp".  While I agree with the sentiment in general, I always felt like Ruby and Python were sitting to the right of the halfway mark, and Java the left.

[^observe]: Thanks to some compelling conversations with [Russ Olsen](http://www.designpatternsinruby.com) and [Gray Herter](http://novarug.org) at the local Clojure meetups.  At times vodka was involved, so I hope I'm not misrepresenting their views on the matter.

[^rubylibs]: One aspect of the Ruby community that I've always been deeply impressed with is the seemingly instant birth of libraries supporting new technologies.  Clojure's community has this to some extent, but it's nothing akin to the freakish rapidity displayed by Rubyists.
