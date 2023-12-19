<pre>
As with any Lisp ever created
,  Clojure has recently been infected with talk of 
   alternative layout styles of closing 

          (and in some cases
,           open
           ) 

   parentheses
.  While I am in danger of succumbing to the 
   Clojure extension to Wadler's Law (1)
,  I take such a chance in order to prove a point
.  You see
,  there are numerous reasons to practice
   alternative placements of parentheses
,  but they basically all boil down to
: 
           1) familiarity
           2) personal aesthetics

.  Likewise
,  the primary reason to adhere to a community 
   standard of paren placement is inherent in 
   the word "community" 
-- it's good sense to present a styling that %99% 
   of the community will instantly feel 
   comfortable viewing and contributing to
.  The problem with the personal aesthetic 
   *and* the community standard is that neither 
   one defines a style that is universally 
   intuitive (2)
.  I think people arguing both sides miss this 
   point and tend to insist that the style they 
   follow is "the best" and "most logical"
,  when they instead should realize that each of 
   these statements contain an implicitly qualifying 
,  "FOR ME"
.  However
,  while your personal style is tailored to 
   your own foibles
,  the community standard aims 
   at a mythical every-man (and woman)
.  Both styles become invisible with repeated use
,  but the advantage of a community standard is that 
   it is invisible for the majority
,  while your personal style puts you on an island 
   where anyone else is instantly repelled
.  The result of this repulsion is two-fold
: 
            1) You lose credibility (whether it's 
               unfounded or not is irrelevant)
            2) People refuse to spend much time 
               looking at your code

.  The end result in either case is the same
,  like the proverbial desert island
,  your project will suffer from a lack of 
   contributors
.  People only have so many hours (3) in a day
,  and are less likely to spend their time helping 
   to fix your code if they also have to attempt to 
   decipher your personal aesthetic
.  Of course
,  if you never plan to subject the world to your 
   code (4)
,  then by all means put the parens wherever you
   like
.  It's purely a pragmatic assessment
.  Perhaps your style is vastly superior
,  but the general fact is that people will not 
   adopt it unless you crank out phenomenal code 
   and become a geek icon 

            (a-la 
              Torvalds
             )

.  This will be my only attempt to make sense of 
   the "placement-wars"
,  so I hope my message is loud and clear 
-- thankfully I used THE optimal formatting scheme
!
</pre>

(1) <http://blog.fogus.me/2010/07/12/wadlers-law-extended-to-clojure/>

(2) If such a thing could ever exist in relation to humanity.

(3) It took Chris Houser to really make this point clear to me.  Thank you.

(4) Companies like Google go out of their way to enforce a uniform community standard throughtout their entire codebase.  The reason for this is that they established an environment where the cost of developer transition can occur unfettered by the nuances of code structuring.  It's difficult enough to dive into a new codebase fresh, and exponentially so when everything looks so weird (relatively).
