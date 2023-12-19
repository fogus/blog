With the growing focus on functional programming the importance of data design has been thrust to the forefront.  That said, you wouldn't know that the importance was widely agreed upon based on an observation of existing data-driven APIs.  Certainly I've committed my share of sins in the name of "API data packets," but with the release of *Spec* I've come to approach the matter of data design in a more systematic way.  In this quick post I'll talk briefly about the first step that I like to call "sketching a struct" that I suspect many programmers do to some extent, but one that I believe is enhanced in the presence of [clojure.spec](https://clojure.org/about/spec).

## Sketching a Struct

The act of sketching a struct is not magical.  Whether you're designing an object graph, JSON packet, or Clojure map the point is generally the same: just type out[^json] what the data structure should look like.  Imagine that you're interested in designing the representation for the runtime *state* of a game that you're creating.  The game consists of pieces of varying *sizes* and *capability*, each of which can reside at a certain location called a *system* either as a *ship* or a *star*.  Each player controls a single system containing two stars called a *[homeworld](http://www.looneylabs.com/rules/homeworlds)*.  This bare skeleton is probably enough to sketch out a structure to represent the state for this fictional game.  

When sketching a struct I tend to work top-down until I have something that works for me and then switch to a bottom-up approach when implementing specs and helper functions.  Therefore, sketching top-down means starting with the high-level game state, structured as a Clojure map:

<pre class="prettyprint lang-clj">
{:game/players    [{:player/name "Mike" 
                    :player/seat :seat/north}, 
                   {:player/name "Russ" 
                    :player/seat :seat/south}]
 :game/turn       0
 :game/systems    {:??? :???}
 :game/homeworlds {:seat/north :??? :seat/south :???}}
</pre>

When sketching a struct I like to fill out the data with something representational, but there are times when I don't quite know what I'm after, so instead of wasting time trying to come up with the perfect representation I just move on and put a placeholder keyword `:???` or perhaps empty structure literals wherever I need them.  So you'll notice that I've decided to prefix my keywords in the sketch above.  In my early days of writing Clojure I probably would have used flat keys like `:players` or `:systems` but over time I've grown to appreciate the organizational utility of namespaced keys.  With the introduction of *Spec* I've taken to always using namespaced keys for domain-level concerns[^growth] and flat keys for local (e.g. function kwargs or Record keys) concerns.  Regarding the use of the vector for the `:game/players` value my thinking was that its nature as a sequential structure could be leveraged[^leveraged] for the encoding of the `:game/turn` value.  In general, when designing a domain structure in Clojure I find it useful to leverage[^sets] the fundamental characteristics of the data types used when they're appropriate for encoding essential domain information.  Note that the use of `:seat/north` and `:seat/south` can be thought of as representing enumerations in the system.  Finally, by embedding the player representation in the state structure I've got a good idea of its essential nature, but as sketches go it may need adjustment later on.

[^sets]: Leveraging the characteristics of a data format is only as powerful as the limitations imposed by its impoverishment however.

[^growth]: The main benefits of prefixed keys are that they allow for contextual name semantics and provide an environment for domain data growth.

Moving down to the next relevant sub-structure in the state, I might represent the system structure as:

<pre class="prettyprint lang-clj">
{:system/name  :name
 :system/stars [:???]
 :system/ships {:ships/north [:???], 
                :ships/south [:???]}}
</pre>

The layout seems reasonable, but I'm not quite sure how to represent the stars and ships residing in the system.  The game is such that the same pieces are used to represent both and there is a good chance that any given system will have pieces of the same size and capability.  This seems like a nice segue into the piece structure:

<pre class="prettyprint lang-clj">
{:piece/action :action/build
 :piece/size   1}
</pre>

There are two important characteristics os a piece: its capability (i.e. action) and its size.  The size could have been an enumeration such as `:size/small`, but a number works just as well.  When dealing with homogenous data elements like the piece structure I prefer to organize the data such that they're categorized by their residence in the host structure rather than using something like an embedded tag.  Anyway, now that I have a useable piece structure I can start filling in a little more detail:

<pre class="prettyprint lang-clj">
{:game/players    [{:player/name "Mike" 
                    :player/seat :seat/north}, 
                   {:player/name "Russ" 
                    :player/seat :seat/south}]
 :game/turn       0
 :game/systems    
  {:name {:system/name  :name
          :system/stars [{:piece/action :action/move 
                          :piece/size 1}]
          :system/ships {:ships/north 
                          [{:piece/action :action/build 
                            :piece/size 1}], 
          :ships/south []}}}
 :game/homeworlds 
  {:seat/north 
    {:system/name  :north
     :system/stars [{:piece/action :action/trade 
                     :piece/size 2}
                    {:piece/action :action/move 
                     :piece/size 3}]
     :system/ships {:ships/north 
                     [{:piece/action :action/build 
                       :piece/size 3}], 
                    :ships/south []}} 
   :seat/south 
    {:system/name  :south
     :system/stars [{:piece/action :action/attack 
                     :piece/size 2}
                    {:piece/action :action/trade 
                     :piece/size 1}]
     :system/ships {:ships/north [], 
                    :ships/south 
                      [{:piece/action :action/move 
                        :piece/size 3}]}}}}
</pre>

Is this the best possible structure?  Maybe or maybe not.  The point at this stage is not to devise the perfect structure but instead to use the data representation as a tool for thinking about the larger problem at hand.  A nice side effect is that at this point I have a useable structure to begin writing a little code.  However, in the past the code that I'd write would be of the nature of utility functions and validators.  However, with the advent of *Spec* my entire thought process changed -- and I'll talk about how in the next post.

:F

The series so far...

* [Introduction](http://blog.fogus.me/2017/01/10/clojure-spec-introduction/)

[^json]: While Lisp programmers have long know the utility of sketching their structs, I'm convinced that one of the primary reasons that JSON has taken over the world is that it provides JS-direct syntactic literals that can be easy typed up and manipulated with little fuss.  JSON is a perfectly adequate tool for sketching for many programmers.

[^leveraged]: I could have also flattened the structure to have independent keys `:player/north` and `:player/south` instead, but the use of namespace keys fosters the idea of keeping similar concerns at the same logical level within a structure.  However, there is nothing preventing a flatter state structure in Clojure and my structure above is a matter of personal aesthetics. 