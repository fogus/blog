While throwing out the idea in the title [as a Twitter post](http://twitter.com/fogus/status/2789832163), I was surprised at the seeming interest.  Therefore, in hopes to open up a discussion, here is how I would envision a programming language homoiconic on maps.  *(note: Slava Pestov of [Factor](http://factorcode.org) fame provided a link to a language named [Misc](http://will.thimbleby.net/misc) that implements this idea in some way, but having not looked at it yet any similarities are incidental)*

I have been turning these ideas over in my mind for a while, and actually started toward an implementation named [Jaka](http://github.com/fogus/jaka/tree), but any such implementation has to wait its turn in my ever-deepening queue.  

Things to note:
{}          
:    map literal syntax

()          
:    tuple literal syntax

||          
:    set literal syntax

[]          
:    list literal syntax

foo         
:    a symbol

"foo"       
:    a string

foo => bar  
:    a pair

:.          
:    a block (similar to progn)

=           
:    bind

~           
:    merge

<~          
:    merge bind

?           
:    property query

foo(42)     
:    symbol followed by a tuple is a function call

foo.fun()   
:    call a method 

foo fun()   
:    call a method

$           
:    similar to `this` or `self`

foo {}      
:    function followed by map is same as foo({})

foo:.       
:    function followed by a block is same as foo(:.)

[sourcecode lang="js" gist="153045"]
def foo = {}            // start with an empty map
foo.n = 42              // put a property 
foo.z = 0               
foo?n                   // does foo have property n?
foo.n                   // lookup property n

def bar = {|| => [print("Hello Cleveland")]}
bar()                   // prints Hello Cleveland
bar.||                  // returns `[print("Hello Cleveland")]`
bar?()                  // accepts zero args?
bar.||.first()          // returns the symbol `print`

def baz = {|| => [print($.n)]}
baz()                   // returns notset
baz.n = 138             // sets property `n`
baz()                   // prints 138

def qux = {||=> baz.||} // grab function body 
qux()                   // returns object notset
qux <~ foo              // foo has `n`
qux()                   // prints 42
qux.n = baz.n           // set qux.n to baz.n
qux()                   // prints 138
qux <~ {|x| => [        
         print($.n - x) // merge arity1 fun
       ]}
qux(100)                // prints 38

qux <~ {|x| => [        
         {|y| => [x + y]}
       ]}               // merge arity1 fun

def frob = {} 
frob <~ {
          || => [print("arity0")]
          |x| => [print("arity1")]
          |x y| => [print("arity2")]
        }
frob()                  // prints "arity0"
frob(2)                 // prints "arity1"
frob(3 4)               // prints "arity2"

def add2 = qux(2)       
add2                    // Lexical closure
add2.|_|                // returns list `[$.x + y]`
add2?x                  // false - `x` is closed
add2(10)                // returns 12

/** Multimethods **/
def mm = multimethod    // prototypal defmulti object
mm.on                   // default dispatch function
mm.on                   // {|_| => [_.class]}
mm.on?Object            // has a method for Object? true
mm.on.Object            // {|_| => [_]}
mm.on.Object = method {|_| => [_.str()]}
mm(42)                  // returns "42"
mm.on.Number = method {|_| => [_ + 2)]}
mm(10)                  // returns 12
mm([1 2])               // returns "[1 2]"
mm.on.List   = method {|_| => _.length()}
mm([1 2])               // returns 2
[/sourcecode]

Thoughts?

-m
