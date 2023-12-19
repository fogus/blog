<p>Turning our attention to the idea within Broccoli of transient scopes, we can imagine a snippet of source as below:</p>
<pre>
define scope addone
    x += 1
leave

var x as integer is 0
var foo as addone
enter foo
print x
</pre>

<p>What would the result therefore be?  As defined above, Broccoli would print 1 because by default all variables are promiscuous and therefore subject to corruption by haphazard scoping.  However, we should be able to turn off promiscuity through a simple keyword or flag in variable x's declaration, thus causing Broccoli to instead print 0.</p>
<pre>
define scope addone
    x += 1
leave

define func bar ()
    var x as integer is 0
    print x
return x

# Uncorrupted call
print bar()
# Corrupted call
print bar() with addone as postfix
</pre>

<p>Broccoli allows functions to be modified on the fly via scope application to one of three points: prefix, body, and postfix (similar in intent to Lisp advice).  Prefix refers to the application of the scope immediately after the variable declarations (as all are required to be at the beginning of the function).  Body refers to the entire body of the function being replaced.  Finally, postfix refers to the scope application occurring just prior to function return.  For the snippet above the following would be printed:</p>
<p>#Uncorrupted<br>
0<br>
0</p>
<p>#Corrupted<br>
0<br>
1</p>

<p>-m</p>