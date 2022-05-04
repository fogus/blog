<p>Python generators are very interesting beasts.  Essentially, they are functions that can be invoked numerous times while maintaining their state between calls.  This is an incremental process defined by the way that the <strong>yield</strong> keyword is used in the function.  That is, every call to the generator's next() method will return the result of the successive yield within said function.  Since I am currently using Python to write a modern version of the famous <a target="_blank" href="http://en.wikipedia.org/wiki/Dnd_%28computer_game%29">dnd</a> game, I have been striving for a useful way to use generators — and I believe I have found one.   </p>
<p>In the word of RPG weapon proficiencies, how would one define the relationship between increased proficiency and weapon usage?  One possible way could be to envision perfect proficiency as a unit circle whose area is completely filled.  Therefore, gaining proficiency is represented by more and more of the circle being filled as the weapon is used.  This analogy can be easily simulated using Python generators (as described in <a target="_blank" href="http://linuxgazette.net/index.html">Linux Gazette</a> #100).  First, we define a function that returns an increasingly accurate estimation of pi and use that as a comparison against our perfect proficiency circle:</p>
<pre>def proficiency():
    sum = 0
    i = 1.0
    j = 1

    while( True):
        sum = sum + j / i
        yield 4 * sum
        i = i + 2; j = j * -1
</pre>

<p>
Each successive call to the proficiency function will return a closer and closer approximation for pi.  Therefore, in terms of the previous analogy, the usage of a weapon will, over time, cause the area of the unit square to become filled closer to full, but will never fill completely (unless we run out of accuracy).  However, the proficiency function above takes a very long time to converge.  This is fine for things like peripheral weapon proficiencies, but what if one wanted to simulate natural proficiencies (e.g. Elves and bows)?  One way to do this would be to use a sequence accelerator such as defined by <a target="_blank" href="http://en.wikipedia.org/wiki/Euler">Euler</a>, which for brevity's sake can be written in Python as:</p>
<pre>def accelerator( prof):
    s0 = prof.next() # Sn-1
    s1 = prof.next() # Sn
    s2 = prof.next() # Sn+1
    while 1:
        yield s2 - (s2 - s1)**2/(s0 - 2*s1 + s2)
        s0, s1, s2 = s1, s2, prof.next()
</pre>
<p>
The function above takes a proficiency function generator as its parameter.  Using the accelerator above allows the proficiency circle to be filled more accurately, more quickly.  Different accelerators can be used for differing levels of proficiency gains.  Neat and elegant.</p>
<p>Here is a Python class, using a generator object, that rolls n number of s sided dice:</p>
<pre>from random import randint, seed

class Dice:
    def __init__( self):
        seed()

    #
    # Uses a generator object to return the total of the roll and
    # a tuple of the individual results
    #
    def genRoll( self, num, sides):
        if not ((sides &gt; 0) and (num &gt; 0)):
            return

        total = 0;
        results = []

        for i in range( num):
            roll = randint( 1, sides)
            total += roll
            results.append( roll)

        yield total
        yield tuple( results)

    #
    # Returns only the total of a roll request
    #
    def getTotal( self, num, sides):
        return self.genRoll( num, sides).next()

    #
    # Returns only the individual results tuple of a roll request
    #
    def getSet( self, num, sides):
        gen = self.genRoll( num, sides)
        gen.next()
        return gen.next()

</pre>
<p></p>


<p>What have I been doing all of these years... on a whim I picked up OK Computer and Amnesiac and am blown away — great albums — been listening to little else since purchasing them.  I've always used simplified APIs built on SAX, so I felt it was time to learn the mechanics of it to expand the depth of my knowledge on that front. </p>

<p>-m</p>
