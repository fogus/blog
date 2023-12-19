A completely tongue in cheek comparison between Scala and Clojure, implementing the famous closure test.

Scala Version[^longer]
=============

    type Supercalifragilisticexpialidocious = java.lang.Integer
      
    implicit def Supercalifragilisticexpialidocious2Int(tehThingToConvert:Supercalifragilisticexpialidocious) = {
      tehThingToConvert.asInstanceOf[Int]
    }
     
    trait ClosureTestMixin {
      def makeAdder(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int]
    }
     
    class AdderMaker extends ClosureTestMixin {
      def apply(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int] = {
        new Function1[Int,Int] {
          def apply(tehOtherAdditionValue:Int):Int = {
            return tehAdditionValue.$plus(tehOtherAdditionValue.asInstanceOf[Supercalifragilisticexpialidocious])
          }
        }
      }
      
      def makeAdder(tehAdditionValue:Supercalifragilisticexpialidocious):Function1[Int,Int] = {
        return this.apply(tehAdditionValue)
      }
    }
    
    val tehAdderMakerInstance = new AdderMaker()
    
    val tehAdderPlus10 = tehAdderMakerInstance.makeAdder(10)
    
    tehAdderPlus10.apply(9)
    //=> 19

Clojure Version
===============

    (defn mk-adder [x] #(+ % x))
    (def add10 (mk-adder 10))
    (add10 9)
    ;=> 19

To perform this simple exercise the Scala version *requires*[^req] 29 lines and ~960 characters!  The Clojure version on the other hand requires only 3 lines and 64 characters.  

Need I say more?[^scala]

-m


[^scala]: Just in case there was *any* ambiguity -- the Scala version was intentionally obfuscated for chuckles.  For a much more eloquent [rebutal of flawed LOC comparisions](http://codemonkeyism.com/scala-vs-clojure-flawed-loc-comparison/) read Stephan Schmidt's latest post on the subject.

[^longer]: Any suggestions for making this longer?

[^req]: Nope, not at all.

