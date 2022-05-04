Concatenative styles are making the rounds lately including some interesting examples in [Clojure](https://github.com/brandonbloom/factjor) and [Haskell](https://gist.github.com/4467979).  Now I'm perpetually drawn to concatenative languages and their ideas, so I wanted to know what it would take to provide a minimally representative concatenative experience in Clojure.[^l]  As it turns out... not much:


    (defn postfix [& e]
      (reduce #(if (fn? %2)
                 (let [[l r & m] %]
                   (cons (%2 r l) m))
                 (cons %2 %)) [] e))

This is how it's used:

    (postfix 5 1 2 + 4 * + 3 -)
    ;;=> [14]

The way that it works is that it treats every non-function as a value and every function as a binary function taking two elements off of a "stack".  It then wraps the answers back up onto the "top" of the stack.  The final answer is the stack itself.

This is in no way indicative of a fully blown representation of concatenative programming languages,[^c] but it was fun and it highlights the stark simplicity of the model.  This is my year to finally [take concatenative languages seriously](http://factorcode.org/).  [I plan to study hard](http://fogus.me/important/von-thun/).  I hope you'll join me.

:F

*if you want to talk concatenative languages or pesto5, then feel free to comment below or email me at the address at the top of my blog*

[^c]: It's debatable if this is concatenative at all, but alas the name pesto5 was too alluring to pass on.

[^l]: pesto5 is too small to be a "library", but [a gist will do](https://gist.github.com/4468183).