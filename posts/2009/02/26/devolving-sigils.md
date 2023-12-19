Sigils are little symbols attached to a variable name that provide some information regarding its type, scope, or simply marking it as different from non-variables.  There is very little middle-ground on the opinion toward variable [sigils][sigil] among programmers; you either love them, or you hate them.  The quintessential language containing sigils is probably Perl, followed by BASIC, and more recently Ruby.  I pick these three because they use sigils for different purposes:

1.  BASIC [^basic] sigils denote datatypes 
  + `foo$` denotes a variable holding a string
  + `foo%` denotes an integer
2.  Perl sigils denote a datatype category [^twigil]
  + `$foo` denotes a scalar type
  + `@foo` denotes an array
  + `%foo` denotes a hash
  + `&foo` denotes a subroutine
3.  Ruby sigils denote a variable's scope [^symbol]
  + `$foo` denotes a global variable
  + `@foo` denotes an instance variable
  + `@@foo` denotes a class variable

I personally like sigils -- very much so.  However, I tend to prefer the types of sigils used by Ruby rather than the finer-grained meaning attached to Perl and BASIC sigils (which is also the reason that I dislike [Hungarian notation][hn]).  I like being able to read my source and, at a glance, soak in the maximum amount of information.  Sigils, when used sparingly, can provide a tremendous service.  However, there is a fine line between sigils providing useful information and those akin to [line-noise][noise].  My head tends to swim when looking at some [Perl code][pl] due to the presence of sigils, but maybe that's just me ([probably not][yegge]).  Therefore, when I set out to design [my own experimental language][doris], effective sigils were high on my wish list.

First Cut
---------
Since my sandbox programming language [Ix][ix] is based on the [CLIPS][clips] source base, I wholly adopted the CLIPS convention.  That is, CLIPS denotes variables using by pre-pending `?` or `$?` to the front of a symbol.  By convention, the former was meant to denote a scalar while the latter was meant for multifield [^multifield] variables, but they could both be used interchangeably [^seqexp].  Therefore, a simple reduce function initially looked like this:

<pre lang="ix">
fn( reduce
  [?f $?lst]
  if(empty?($?lst)
    call(?f)
  else
    call(?f first($?lst)
            reduce(?f rest($?lst)))))

reduce(_(1 2 3 4 5) +)
</pre>

Not too bad, but the sigils clutter up what is effectively a succinct function.  As an added disadvantage, I decided a long time ago that predicate functions in Ix should have a question mark at the end of them; therefore, in this small function the question mark has two different meanings depending on the context.  But even still, I stuck with this syntax for months.  

Second Cut
----------
After writing a pile of code in the first version of Ix, I decided to add some syntactic sugar for the `(call)` function (see its usage above).  As a result, the code above became:

<pre lang="ix">
fn( reduce
  [?f $?lst]
  if(empty?($?lst)
    ?f()
  else
    ?f(first($?lst)
       reduce(?f rest($?lst)))))

reduce(_(1 2 3 4 5) +)
</pre>

This looked a little better than the original, but there were a couple of issues that stuck with me:

1.  The `$?` sigil was still too noisey
2.  The `?f()` form is hideous, and `$?f()` even moreso
3.  The issue of differing meanings for `?` still remained

Third Cut
---------
I initially decided to live with issues #2 and #3 and instead remove the $? form altogether.  

<pre lang="ix">
fn( reduce
  [?f ?lst]
  if(empty?(?lst)
    ?f()
  else
    ?f(first(?lst)
       reduce(?f rest(?lst)))))

reduce(_(1 2 3 4 5) +)
</pre>

Better?  It took me a while to learn to hate this new syntax, but eventually I did.  While reducing the `$?` noise, it introduced a whole new problem.  That is, when calling predicate functions, the pattern `?(?` tended to cause mass confusion (at least for me).  My mind would often fill in the second question mark even in its absence thus turning something like `symbol?(x)` into `symbol?(?x)`.  Why is this a problem instead of and outright syntax error?  The answer is that symbols in Ix are defined as any sequence of characters not starting with a number, and not containing a small set of delimiters. [^sym]  Therefore, in the first call `x` is a symbol and thus the call to `symbol?()` always evaluates to true.  It took me only a few frustrating debugging sessions to see the err of my ways.

Today
-----
After much despairing over the seeming disparity between wishing to keep sigils and requiring the presence of symbols as defined above, I hit on a very nice compromise.  That is, who's to say that a sigil **must** be a non-alphabetical character (or sequence thereof)?  

<pre lang="ix">
fn( reduce
  [F Lst]
  if(empty?(Lst)
    F()
  else
    F(first(Lst)
      reduce(F rest(Lst)))))

reduce(_(1 2 3 4 5) +)
</pre>

So what happened?  Simple.  Variables are now identified as starting with a capital letter.  Assuredly, this is nothing new in the history of programming language design, but it did solve nicely the issues above:

1.  Less visible noise
2.  Variables and symbols are clearly delineated
3.  F() looks much nicer
4.  There is now only one meaning of `?`

Of course, the `symbol(x)` issue now evolved into `symbol(X)` issue, I found that the occurrences of this mistake disappeared once the confusing `?(?` pattern disappeared likewise.  I think I've hit on the right formula for sigils in Ix.  That is, I've reduced the granularity of their meaning to be agnostic of type and scope, while at the same time clearly separating symbols from variables.   

Sigils are nice; [as long as they are not abused][wank]. 

-m

[doris]: /2008/08/19/project-doris/
[pl]: http://snippets.dzone.com/tag/perl
[noise]: http://onestepback.org/index.cgi/Tech/Ruby/LineNoise.rdoc
[hn]: http://en.wikipedia.org/wiki/Hungarian_notation
[sigil]: http://en.wikipedia.org/wiki/Sigil_(computer_programming)
[clips]: http://clipsrules.sourceforge.net/
[ix]: http://github.com/fogus/ix/tree/master
[wank]: http://books.google.com/books?id=W6IycFk3VZ0C&pg=PA19&lpg=PA19&dq=%22wank+technique%22&source=bl&ots=QjIgLlLwoE&sig=hWaLEGOnYXq_yUucVmYm6E6Mg2w#PPA20,M1
[yegge]: http://steve.yegge.googlepages.com/ancient-languages-perl

[^multifield]: A multifield is essentially a list restricted to depth 1.
[^seqexp]: That is, until the introduction of sequence expansion in CLIPS v6.
[^basic]: Not all dialects use sigils in the same way.
[^twigil]: Perl 6 introduces something called a twigil, that is a secondary sigil denoting scope
[^symbol]: Ruby symbols are prefixed by the `:` character, but that is the syntax for a symbol literal (thanks [Ola](http://olabini.com/blog))
[^sym]: Example of valid symbols: foo, b12, +, and jfkdashnsadjhio1231231123123@#@#!$#%#!#@@$#___+_+_+_____+_++++
