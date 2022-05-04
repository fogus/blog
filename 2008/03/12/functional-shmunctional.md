I have mentioned that Broccoli is not meant to be a purely functional language, but that is not to say that it does not have some functional aspects.  Take for example the <em>call</em> function.  It works similarly to the Lisp <em><a href="http://www.lisp.org/HyperSpec/Body/fun_funcall.html">funcall</a></em>, minus all of the pound-quote mumbo-jumbo.  Instead, it always takes a symbol or a string which it coerces into a function (if exists of course) and calls it with the remaining arguments.  The result of this is that functions themselves cannot really be passed around in Broccoli, but their names can, and therefore can be accessed indirectly.  This may or may not be as powerful as the purely functional approach, but I can say it is a lot easier for me to wrap my head around.  

An example of the way that Broccoli can use names to provide a classic functional programming function is as follows (from your Week 2 C.S. 101 assignment):
<pre>
(fn double ($num)
	(* $num 2)
)

(fn map ($func @lst)
	(if (== @lst (list))
		(list)
	 else
	 	(list (apply $func (first @lst)) (map $func (rest @lst)))
	)
)
</pre>

Therefore, (map double (list 2 3 4)) => (4 6 8).<br/>
<br/>
There is something else worth noting, that is, the if-condition is checking against and empty list rather than the <em>nil</em> symbol.  The reason for this is due to the fact that in Broccoli nil is not equal to an empty list, nil instead is the symbol for false.  <br/>
-m

<em>Note: This post has been graduated to an <a href="/projects/broccoli/thoughts/functional-shmunctional.html">ongoing essay</a>.</em>
