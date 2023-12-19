In the past couple of days I've had some time to hack up the CLIPS scanner and parser to get a rudimentary binding syntax.  Well, I threw that out last night and spent a couple hours re-writing the CLIPS parser.  Out of this I was able to do some kinda cool things:

~~~
# Bind a variable
var _foo <- bar

# Re-bind the variable
_foo <- baz

# Call a function
out( "Hello World" crlf)

# Bind a variable to the symbol out
var _fun <- out

# Indirectly call the function pointed to by _fun
call( _fun "Hello World" crlf)

# Or just call it directly
_fun( "Hello World" crlf)
~~~

This should prove to be an interesting feature.
-m
