I'm chugging along on the Broccoli interpreter adding the following:<br/>
<h2><em>fast</em> lists</h2>
Lists are backed by arrays now, so random access is O(1).  I was considering making an extra array type, but the array-backed list will work just as well and keep the type system cleaner.

<h2>for loops</h2>
I have added a for loop that works as both a list iterator and a range iterator.  I was considering using only the former form, but that would have been a bit messy behind the scenes in the way that the recognition of ranges would need to occur.  The BNF of the for loop is as follows:<br/>
<em>List iteration form:</em><br/>
<pre>
&lt;list iteration for&gt;    ::= (for &lt;scalar-variable&gt; in &lt;list&gt; &lt;expression&gt;*)
</pre>
<em>Range iteration form:</em><br/>
<pre>
&lt;range iteration for&gt;   ::= (for &lt;scalar-variable&gt; in &lt;start-index&gt; to &lt;end-index&gt; &lt;expression&gt;*)
&lt;start-index&gt;    ::= &lt;integer&gt; | &lt;scalar-variable&gt;
&lt;end-index&gt;      ::= &lt;integer&gt; | &lt;scalar-variable&gt;
</pre>
<br/>
In the list iteration case, the scalar-variable will be the currently indexed element in the list.  In order to obtain the current index count for this case, the special variable &lt;scalar-variable&gt;-index is provided.  In the range iteration case, the scalar-variable <em>is</em> the loop index.<br/>

<h2>if-then-else</h2>
Basic if-then-else statements are provided of the form:
<pre>
&lt;if-then-else&gt;    ::= (if &lt;boolean-condition&gt; &lt;expression&gt;* else &lt;expression&gt;)
</pre>

<h2>functions</h2>
Functions are now provided.  Recursion is possible, although tail-recursion will trash the stack at around 16K calls deep.<br/>
Functions take the form:<br/>
<pre>
&lt;function&gt;   ::= (fn &lt;name&gt; (&lt;parameter&gt;*) &lt;expression&gt;*)
&lt;name&gt;       ::= &lt;symbol&gt;
&lt;parameter&gt;  ::= &lt;symbol&gt;
</pre>
<br/>

An example of the solution for your Week 1 undergrad C.S. assignment would therefore be:
<pre>
(fn fib ($num)
	(if (< $num 2)
		$num
	else
		(+ (fib (- $num 1)) (fib (- $num 2)))
	)
)

(fn fact ($num)
	(if (== $num 0)
		1
	else
		(* $num (fact (- $num 1)))
	)
)
</pre>
-m
