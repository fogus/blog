I've been programming for quite some time and have explored a variety of programming paradigms along the way.  From time to time people will ask[^me] which is better: functional programming or object-oriented programming.  Having done both extensively in my time I can exclaim a resounding: *it depends*.

Lame.

While I'll never be a major player in the grand Internet flame war, I will say that I've had great success following a simple two-point guideline:[^gl]

 * Whenever I write some code to deal with data *about* people then functional programming seems to work best.

 * Whenever I write some code to *simulate* people then object-oriented programming seems[^actors] to work best.

YMMV.[^both]

:F

[^me]: Not necessarily me, but they ask nonetheless -- I just eavesdrop.

[^gl]: And even these guideline are not written in stone.

[^actors]: Although some recent work has given me some insight into [Actor-based programming](http://akka.io/) and I must say that it too seems particularly suited to the task of "people modeling."

[^both]: Further, I've found that when building a low-level library providing data and algorithmic abstractions then a mix of the two (OO at the bottom with an FP API) seems to work best for me.  However, this scenario happens far less often than the other two listed above.
