Lambda C++
==========
<pre>
\#define Lambda(args, ret, body) \
  class MakeName(__Lambda__) { \
    public: ret operator() args { body; }}

Lambda((int a, int b), int, return a+b) foo;
cout << "foo(1,2) is " << foo(1, 2) << endl;

/** Recursive lambda **/
Lambda((int n), int, 
       if( n <= 0) 
         return 1; 
       else
         return n * (*this)(n - 1)) fact;

/** Mega-example **/
template<class T> struct pair { T fst, snd; };

typedef 
Lambda((int x),
    struct xv: public pair<int>
    { xv(void) { fst=0; snd=0; }
      xv(const int a, const int b) { fst = a; snd = b; }};
      pair<xv> cache;
      int do_cache(const xv prev, const xv curr)
      {
         if( curr.fst > cache.snd.fst )
         { cache.fst = prev; cache.snd = curr; }
         return curr.snd;
      }
      int,
      if( x == 1 ) return 1;
      if( x == 2 ) return 1;
      if( x == cache.fst.fst ) return cache.fst.snd;
      if( x == cache.snd.fst ) return cache.snd.snd;
      if( x == (cache.snd.fst + 1) )
         return do_cache(cache.snd, xv(x,cache.fst.snd + cache.snd.snd));
      const int vp = (*this)(x-1);
      return do_cache(xv(x-1,vp), xv(x,vp+(*this)(x-2)))) fib;
    template<class T> void print_every_other(const int n)
    {
        T closure;
        cout << closure(1);
        for(register int i=3; i<=n; i+=2)
            cout << " " << closure(i);
        cout << endl;
    }
    main() {
        print_every_other<fib>(11);
    }
</pre>

Some Tab
========
<pre>
INTRO
e-|------------------------------------------|
B-|------------------------------------------|
G-|--7--7--7--7-7-7-7-7--7--7--7--7-7-7-7-7--|
D-|--7--7--7--7-7-7-7-7--7--7--7--7-7-7-7-7--|
A-|--5--5--5--5-5-5-5-5--5--5--5--5-5-5-5-5--|
E-|------------------------------------------|

VERSE (PART1)              (PART2)
e-|------------------|------------------------------------------|
B-|------------------|------------------------------------------|
G-|--------5---------|-7--7--7--7-7-7-7-7--7--7--7--7-7-7-7-7---|
D-|--7--7--5---5-5-5-|-7--7--7--7-7-7-7-7--7--7--7--7-7-7-7-7---|
A-|--7--7--3---5-5-5-|-5--5--5--5-5-5-5-5--5--5--5--5-5-5-5-5---|
E-|--5--5------3-3-3-|------------------------------------------|


CHORUS
e-|-----------------------------------------------|
B-|-----------------------------------------------|
G-|--9--9-9-----------6-6/7-7-7-7--6-6/7-7-7-7----|
D-|--9--9-95-5-5-5-5--6-6/7-7-7-7--6-6/7-7-7-7----|
A-|--7--7-75-5-5-5-5--4-4/5-5-5-5--4-4/5-5-5-5----|
E-|--------3-3-3-3-3------------------------------|

AFTER CHORUS
e-|-----------------------------------------------|
B-|-----------------------------------------------|
G-|--9--9-9---------------------------------------|
D-|--9--9-95--------------------------------------|
A-|--7--7-75--------------------------------------|
E-|--------3--------------------------------------|
           ^let ring and pick slide until verse begins again

Song Structure
~~~~~~~~~~~~~~
INTRO
Spirits fly high, but they die so easily
^VERSE1 ^VERSE2
 I cry when they do
^VERSE1
  Young energy, that flame inside seems so Free, Yeah,
^VERSE2
 can i rely on you?
^VERSE1
 I feel the years pass through me,
^CHORUS
 Keep my soul awake,
^CHORUS
 Don't wanna lose that feeling,
^CHORUS
 Keep my soul awake.
^CHORUS
 Keep my soul awake.
^CHORUS
AFTER CHORUS
Don't let it slide, we all grow up so quickly.
^VERSE1  ^VERSE2
 Hang on to what's inside
^VERSE1
 So young at heart, just take your time,
^VERSE2
 There's no hurry,
 don't want to lose my mind.
^VERSE1
 I feel the years pass through me,
^CHORUS
 Keep my soul awake,
^CHORUS
 Don't wanna lose that feeling,
^CHORUS
 Keep my soul awake.
^CHORUS
 Keep my soul awake.
^CHORUS
</pre>

Read File in one Java line
===========================
String file = new Scanner( new File( "test.txt" ) ).useDelimiter( "\\Z" ).next();

Kick-ass js Toolkit
===================
1.  [jquery](http://jquery.com)
2.  [Taffy DB](http://taffydb.com)
3.  [Greybox-redux](http://jquery.com/demo/grey)
4.  [jquery UI](http://ui.jquery.com)
5.  [js2-mode.el](http://code.google.com/p/js2-mode)
6.  [Chilli source highlighter](http://noteslog.com/chili)
7.  [Simile Timeline](http://code.google.com/p/simile-widgets)
8.  [dragtable](http://code.google.com/p/dragtable)
9.  [Erlang-in-js](http://www.beatniksoftware.com/erjs/index.html)
10. [Thread.js](http://www.neilmix.com/2007/02/07/threading-in-javascript-17)
11. [Processing.js](http://ejohn.org/blog/processingjs)

## Y-Combinator in js
<pre>
// The Y combinator, applied to the factorial function

function factorial(proc) {
    return function (n) {
        return (n <= 1) ? 1 : n * proc(n-1);
    }
}

function Y(outer) {
    function inner(proc) {
        function apply(arg) {
            return proc(proc)(arg);
        }
        return outer(apply);
    }
    return inner(inner);
}

print("5! is " + Y(factorial)(5));
</pre>

Thoughts on gor
===============
[object-relational impedance mismatch][ormimp]


Transgressional Fiction
========================
- William S. Burroughs 	Naked Lunch, Junkie
- Hubert Selby Jr. 		Last Exit to Brooklyn, Requiem for a Dream
- Emile Zola			
- Fyodor Dostoyevsky
- Knut Hamsun		Hunger
- Gordon Lish
- Henry Miller			Tropic of Cancer, Tropic of Capricorn
- Alan Ginsberg
- J.G. Ballard			Crash
- Kathy Acker
- Charles Bukowski
- Douglas Coupland	Generation X
- Bret Easton Ellis		American Psycho, Lunar Park
- Irvine Welsh			Trainspotting
- Chuck Palahniuk		Choke, Fight Club
- Craig Clevenger		The Contortionist's Handbook
- Luke Rhinehart		Dice Man
- Georges Bataille

Splatterpunk
- Clive Barker			Books of Blood
- Craig Spector		The Light at the End
- David Schow			The Kill Riff
- Joe R. Lansdale		The Nightrunners
- Rex Miller			Slob
- Kathe Koja			The Cipher
- Jack Ketchum			Off Season

## Writing tips from Chuck Palahniuk ##
Number One: Two years ago, when I wrote the first of these essays it was about my "egg timer method" of writing. You never saw that essay, but here's the method: When you don't want to write, set an egg timer for one hour (or half hour) and sit down to write until the timer rings. If you still hate writing, you're free in an hour. But usually, by the time that alarm rings, you'll be so involved in your work, enjoying it so much, you'll keep going. Instead of an egg timer, you can put a load of clothes in the washer or dryer and use them to time your work. Alternating the thoughtful task of writing with the mindless work of laundry or dish washing will give you the breaks you need for new ideas and insights to occur. If you don't know what comes next in the story… clean your toilet. Change the bed sheets. For Christ sakes, dust the computer. A better idea will come.

Number Two: Your audience is smarter than you imagine. Don't be afraid to experiment with story forms and time shifts. My personal theory is that younger readers distain most books - not because those readers are dumber than past readers, but because today's reader is smarter. Movies have made us very sophisticated about storytelling. And your audience is much harder to shock than you can ever imagine.

Number Three: Before you sit down to write a scene, mull it over in your mind and know the purpose of that scene. What earlier set-ups will this scene pay off? What will it set up for later scenes? How will this scene further your plot? As you work, drive, exercise, hold only this question in your mind. Take a few notes as you have ideas. And only when you've decided on the bones of the scene - then, sit and write it. Don't go to that boring, dusty computer without something in mind. And don't make your reader slog through a scene in which little or nothing happens.

Number Four: Surprise yourself. If you can bring the story - or let it bring you - to a place that amazes you, then you can surprise your reader. The moment you can see any well-planned surprise, chances are, so will your sophisticated reader.

