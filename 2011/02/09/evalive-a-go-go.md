I've mentioned my spunky little library evalive numerous 
times, but never got around to announcing it officially.  Well here 
you go. 
evalive: a tiny clojure library providing another eval... and destro. 

[source](http://github.com/fogus/evalive)

[marginalia docs](http://fogus.me/fun/evalive/)

![evalive](http://images.fogus.me/logos/evalive.png "0x14 eyes")

Examples
--------

    (use '[evalive.core :only (evil destro)])

### evil

    (evil '{message "Hello", place "Cleveland"}
          '(println message place))
    
    ; Hello Cleveland

### destro

    (destro [message place] ["Hello" "Cleveland"])
    ;=> {vec__2438 [Hello Cleveland], message Hello, place Cleveland}

### evil destro
  
    (evil (destro [message place] ["Hello" "Cleveland"])
          '(println message place))
    
    ; Hello Cleveland

Getting
-------

### Leiningen

Modify your [Leiningen](http://github.com/technomancy/leiningen) dependencies to include [evalive](http://fogus.me/fun/evalive/):

    :dependencies [[evalive "1.0.0"] ...]    

### Maven

Add the following to your `pom.xml` file:

    <dependency>
      <groupId>evalive</groupId>
      <artifactId>evalive</artifactId>
      <version>1.0.0</version>
    </dependency>

Have fun.

:f
