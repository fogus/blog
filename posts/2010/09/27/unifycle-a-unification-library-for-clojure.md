As a first step toward a much grander Clojure project I needed a unification library to supplement a simple logic engine (the second step in the larger plan).  There are a few options floating around, but nothing that was readily available nor complete.  Therefore, I decided to package a simple unification library in hopes that others might find it useful and hopefully eliminate the need to search around fruitlessly like I did.  A basic use case for Unifycle is as follows:

[sourcecode lang="clojure" gist="599021"](use '[me.fogus.unifycle :only [unifier]])

;; unify two expressions with an occurs check

(unifier '((?a * ?x ** 2) + (?b * ?x) + ?c) 
         '(?z + (4 * 5) + 3))

;=> ((?a * 5 ** 2) + (4 * 5) + 3)[/sourcecode]

Unifycle exposes a number of canned functions, starting with `unifier`, `try-subst`, and `garner-unifiers`.  These functions use an *occurs check* internally, so use them with that fact in mind.  I have also exposed versions of these functions without internal *occurs check* named `unifier-`, `try-subst-`, and `garner-unifiers-`.  If you know what unification is then you know what an *occurs check* is -- if not, then this whole post is probably moot.

I have also exposed factory functions named `make-occurs-unify-fn`, `make-occurs-subst-fn`, `make-occurs-unifier-fn`, `make-unify-fn`, `make-subst-fn`, and `make-unifier-fn`.  The first three create versions using and *occurs check* and the last three create versions without.  Each of these factory functions take a single predicate function that is used to determine if a symbol in a (potentially) unified expression refers to a variable.  From the example above, you'll notice that the default variable function is preceded with a question mark (e.g. `?snigglet`).

I have created a [documentation page for Unifycle](http://fogus.me/fun/unifycle/) that I plan to expand.  Any and all help is appreciated.

:f
