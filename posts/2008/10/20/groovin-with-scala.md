I have been attempting to introduce some low-ceremony programming tools and languages to my programming team at [my job](https://www.tena-sda.org) and thanks to a series of brown-bags and internal discussions have sold [Scala](http://www.scala-lang.org) for use in an upcoming project.  It wasn't [functional programming](http://en.wikipedia.org/wiki/Functional_programming), [type-inference](http://www.scala-lang.org/node/127), [case classes](http://www.scala-lang.org/node/107), or even the Actor Model that was the biggest win but instead [embeddable XML](http://www.scala-lang.org/node/131) and the fact that [Twitter uses it](http://www.gracelessfailures.com/2008_06_01_archive.html)... hey, whatever works right?  

I have looked into Scala for a few months and on occasion attempted to put together a nice example of how it compares to Java.  However, it turns out that [Jim Weirich from Compuware presented a nice example](http://onestepback.org/articles/groovy/index.html) for [Groovy](http://groovy.codehaus.org/) that I decided to steal and mangle to work for Scala (standing on the shoulders of giants and all that).  The results are below, but bear in mind that it works much better as a presentation.  Nonetheless...

> Caveat emptor: I am still learning Scala, so I have yet to absorb its idioms, terminology, and nuances

Original Java Class
----------------------
<pre lang="java">

import java.util.*;

class Clutter {
    public static void main( String[] args) {
        List names = new ArrayList();
        names.add( "Mike");
        names.add( "Marvin");
        names.add( "Bhagat");
        names.add( "Horacio");

        System.out.println( names);

        Clutter e = new Clutter();
        List short_names = e.filterLongerThan( names, 4);

        System.out.println( short_names.size());

        for( String s : short_names) {
            System.out.println( s);
        }
    }
    
    public List filterLongerThan( List strings, int length) {
        List result = new ArrayList();

        for( String s : strings ) {
            if( s.length() < length+1) {
                result.add( s);
            }
        }

        return result;
    }
}
</pre>


There are no semi-colons and the package wildcard is different in Scala
--------------------
<pre lang="java">


import java.util._

class Clutter {
    public static void main( String[] args) {
        List names = new ArrayList()
        names.add( "Mike")
        names.add( "Marvin")
        names.add( "Bhagat")
        names.add( "Horacio")

        System.out.println( names);

        Clutter e = new Clutter()
        List short_names = e.filterLongerThan( names, 4)

        System.out.println( short_names.size())

        for( String s : short_names) {
            System.out.println( s)
        }
    }
    
    public List filterLongerThan( List strings, int length) {
        List result = new ArrayList()

        for( String s : strings ) {
            if( s.length() < length+1) {
                result.add( s)
            }
        }

        return result
    }
}
</pre>

Iterating over a list is elegant
--------------------
<pre lang="java">
import java.util._

class Clutter {
    public static void main( String[] args) {
        List names = new ArrayList()
        names.add( "Mike")
        names.add( "Marvin")
        names.add( "Bhagat")
        names.add( "Horacio")

        System.out.println( names);

        Clutter e = new Clutter()
        List short_names = e.filterLongerThan( names, 4)

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )
    }
    
    public List filterLongerThan( List strings, int length) {
        List result = new ArrayList()

        strings.foreach { s =>
            if( s.length() < length+1) {
                result.add( s)
            }
        }

        return result
    }
}
</pre>

Let type-inference take care of most of those type annotations, plus Scala doesn't need the `new` keyword
--------------------
<pre lang="java"> 
import java.util._

class Clutter {
    public static void main( args:Array[String]) {
        val names = List()  
        names.add( "Mike")
        names.add( "Marvin")
        names.add( "Bhagat")
        names.add( "Horacio")

        System.out.println( names);

        val e = Clutter() 
        var List short_names = e.filterLongerThan( names, 4)

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) ) 
    }
    
    public List filterLongerThan( List strings, int length) {
        List result = ArrayList()

        strings.foreach { s =>       
            if( s.length() < length+1) {
                result.add( s)
            }
        }

        return result
    }
}
</pre>

It should not be this difficult to create a list of strings
--------------------
<pre lang="java"> 
import java.util._

class Clutter {
    public static void main( args:Array[String]) {
        val names = List("Mike", "Marvin", "Bhagat", "Horacio")

        System.out.println( names);

        val e = Clutter()                                      
        var List short_names = e.filterLongerThan( names, 4)   

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )      
    }
    
    public List filterLongerThan( List strings, int length) {
        List result = new ArrayList()

        strings.foreach { s =>                                 
            if( s.length() < length+1) {
                result.add( s)
            }
        }

        return result
    }
}
</pre>

Filtering can be handled using a for-comprehension
--------------------
<pre lang="java"> 
import java.util._

class Clutter {
    public static void main( args:Array[String]) {
        val names = List("Mike", "Marvin", "Bhagat", "Horacio") 

        System.out.println( names);

        val e = Clutter()                                       
        var List short_names = e.filterLongerThan( names, 4)    

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )       
    }
    
    public List filterLongerThan( List strings, int length) {
        for( n <- strings if( n.length <= length)) yield n     
    }
}
</pre>

The filtering can occur locally and does not need to be part of a class
--------------------
<pre lang="java"> 
import java.util._

class Clutter {
    public static void main( args:Array[String]) {
        val names = List("Mike", "Marvin", "Bhagat", "Horacio")

        System.out.println( names);

        var List short_names = for( n <- names if( n.length <= 4)) yield n

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )      
    }
}
</pre>

Why not make our class a singleton?
--------------------
<pre lang="java">
object Clutter {                                             
    public static void main( args:Array[String]) {
        val names = List("Mike", "Marvin", "Bhagat", "Horacio")

        System.out.println( names);

        List short_names = for( n <- names if( n.length <= 4)) yield n

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )      
    }
}
</pre>

There are no statics in Scala
--------------------
<pre lang="java"> 
object Clutter {                                               
    def main( args:Array[String]) {                            
        val names = List("Mike", "Marvin", "Bhagat", "Horacio")

        System.out.println( names);

        List short_names = for( n <- names if( n.length <= 4)) yield n

        System.out.println( short_names.size())

        short_name.foreach( s => System.out.println( s) )      
    }
}
</pre>

We really do not need a main at all, instead make our filter a library function
--------------------
<pre lang="java"> 
object Cleaner {                                      
    def filterLongerThan( names:List[String], len:int) = {  
        for( n <- names if( n.length <= len)) yield n 
    }
}

val names = List("Mike", "Marvin", "Bhagat", "Horacio")
