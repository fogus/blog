I have been playing around with my personal programming language [Ix](http://fogus.me/projects/ix) and have settled on, what I consider, I clean syntax.  I tried to do fancy parsing, but soon realized that since everything is a function call of some sort, then the syntax falls out naturally:

<pre lang="scheme">
--- This is a comment
--- 

fn( foo
    "This is a docstring"
    [?arg1 ?arg2]

    action1()
    action2(?arg1)
    action3(?arg2)

    '(?arg1 ?arg2)) --- return a list

fn( bar
    "This is also a docstring"
    [?x]

    if(not(?x)
        false
    else
        true))

fn( baz 
    "This shows the case/when"
    [?arg]
    
    case( ?arg
        when(true
             out("got true" crlf)
             1)
        when(false
             out("got false" crlf)
             2)
        when(chimp
             out("What the?!?" crlf)
             3)
        default
             4))

fn( fortest
    "Showing the for"
    [?min ?max]

    for([?i in range(?min ?max)]
        if(even?(?i)
            out(even crlf)
        else
            out(odd crlf))))

fn( nstest
    "Using the namespace operator and let"
    []
    
    let ?sine <- math/sine(math/?PI)
    out("sine of pi is " ?sine crlf)
    ?sine)
</pre>

-m
