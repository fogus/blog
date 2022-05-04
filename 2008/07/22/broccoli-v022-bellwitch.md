Finally got around to wrapping up the second iteration of <a href="/projects/broccoli">Broccoli</a>.  Most of the work was put towards changing the syntax.  That is, I originally went with a Lispy syntax filled with parentheses to one where different symbols are used in explicit contexts.  The reason for this was that I originally intended Broccoli to be syntax-less similar to Lisp.  However, the more that I got into the development the more I realized that there was an inherent syntax in the code, and it was good.  I do not treat <a href="http://gigamonkeys.com/book/functions.html">data and functions as equivalent as Lisp does</a>, therefore it is not necessary that everything be a <a href="/projects/broccoli/thoughts/lists.html">list</a>.  Therefore, I decided to work out a syntactic structure that made sense:

Block-level statements
--------------------------
There are two types of block-level statements: function definitions and flow control structures (e.g. if-then-else, while, for, and case-when).  Therefore, when defining a block-level structure, I went with the C-like bracketing system.  Block-level statements always describe a lexical contour, of which the highest level is at the function.

Function calls
----------------
Function calls would be familiar to anyone who has used Lisp.  That is, function calls are surrounded by parentheses and hold to a prefix notation scheme.  This was done to allow for function composition in the future, but I may or may not continue down that path. 

Sigils
------
The first versions of Broccoli used sigils to denote variables much like Perl and Ruby.  The level of granularity for denoting the variable type was far more coarse in that there was only a separation between scalar and collection types.  I decided to keep this distinction, but only for purposes of readability.  That is, scalars are denoted with a single underscore and collections with a double underscore.  You can assign lists to variables with single underscores and vise-verse, the differentiation is for those (such as myself) who enjoy the information provided by sigils.

Function Args
---------------
Function arguments are wrapped in vertical bars... like in Ruby blocks but without commas (note, that is the only similarity with Ruby blocks).  

The <a href="/projects/broccoli/bugs.html#ticket-bell-witch">other changes</a> compared to the older versions are that I have added some trigonometric functions, a couple flow control structures (while and case), and started overloading some core functions.

Distributions of v0.2.2 are available on request.

-m
