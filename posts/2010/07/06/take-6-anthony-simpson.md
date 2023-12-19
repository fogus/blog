[Raynes](http://github.com/Raynes) (*aka* Anthony Grimes) is a Clojure prodigy.  His projects have gained some notoriety in the community, but chief among them is [Try Clojure](http://try-clojure.org).  Following in the footsteps of other **Try XX** sites, Anthony is gradually pulling together what will, in my opinion, prove to be an invaluable asset for Clojure neophytes.

*[(take...](http://blog.fogus.me/tag/take) is an on-going series of micro-interviews focused on interesting Clojurians.*

### What led you to Clojure?

My first *real* language was actually Haskell. When I started programming, I found little more than frustration in object oriented languages like C#.  Everything felt so unnecessarily complex to me.   Classes for every little thing, OOP here, there, and everywhere.

A friend introduced me to Haskell, and that's when I really started to learn to program rather than swim around in bulky OOP concepts that made little sense to me.

Since then, I've played with all sorts of languages.  Ruby, Scala, Ioke, Factor, etc. On the JVM, Scala lead me to Clojure.  Finding Clojure was like seeing God.  Clojure is everything I've ever wanted.  All the good of OOP without any of the bad, bulky, and complex.  I've been using Clojure for probably around a year, but I've been playing with it for probably two.  All together, I've been programming a little over 3 years, and at the time of this writing, I'm 16 years old. 


### You've been finding some nice niches with your Clojure projects.  Are you specifically targetting specific niches, or has it simply fallen that way?

My projects are random at best. sexpbot started as a little script to connect to IRC with [Pircbot](http://www.jibble.org/pircbot.php).  It turned into my largest project, and managed to spin off another project, Irclj, an IRC library I wrote to replace Pircbot in sexpbot.

As for try-clojure, I don't remember exactly how that was started, but I made a joke about the other TryLanguage sites to my buddy [Heinz](http://blog.licenser.net) (Licenser) in #clojure-casual on freenode, and how we should write one for Clojure. I never intended to actually do it, but by the next day, Heinz had already bought the domain and decided it would be.  I'm happy he did.  It's my first real well-known (kind of) project, and despite my horrible design skills, I'm quite proud of it. :) 

### Can you share your favorite code snippet?

Probably going to have to be this one:

    require'socket' 
    a,p,c=$*
    $>=TCPSocket.new a,p
    puts"USER "*5,"NICK w"
    $>.each{|l|puts case l
    when/(P.+)-/
    $1+$'
    when/PI/
    "PO#$'JOIN "+c
    end}

from [here](http://scott-olson.org/2010/05/16/the-ruby-irc-bot-that-fits-in-a-tweet.html)

Scott is a friend, and that snippet is pretty impressive.  It's all that comes to mind right now.

### [Zombies][z] or vampires?

Vampires, of course.  Zombies just walk[^runners] around stupidly.  Vampires are lucid and strong. 

### What's your next project?

I don't have any real plans right now, but my next new project will probably be a graphical RSS and Atom reader.  Something I've been wanting to do for a while. It will be two projects when it happens.  A library for reading the feeds, and the actual application.  I want the RSS reading stuff to be a library itself so that I can use it in sexpbot.

Mostly, I just need to do some serious work on existing stuff like sexpbot and tryclojure.  sexpbot is one of those never-going-to-be-finished projects. 

### What do you listen to when you code?

Pretty much everything I have.  Most recently, The Script, AC/DC, and The All-American Rejects.  Other ones include Adam Lambert, Shinedown, Train, Disturbed, and lots of Coldplay.

[^runners]: Except for the "fast zombies".

[z]: http://www.quora.com/Why-do-zombies-terrify-us-so
