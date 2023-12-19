When deciding to work on a side-project three factors are needed to transition from fancy to application: goal, motivation, and time.

Time is usually the biggest sticking point for me personally but with COVID most of what I may have spent my time on this year was cancelled. However, motivation was still a huge sticking point until I came across a couple of projects that helped propel me forward. First, I spent some time earlier this year combing over [Mary Rose Cook](https://twitter.com/maryrosecook)'s lovely [Little Lisp](https://github.com/maryrosecook/littlelisp) interpreter code. Given what I knew about Mary's previous projects it was no surprise that the Little Lisp implementation was simple and elegant. However, what I wasn't prepared for was that hacking on the interpreter turned out to be straight-forward and addictive. However, it wasn't until I re-discovered [William Taysom](https://twitter.com/wtaysom)'s old Scheme-like language [Misp](https://web.archive.org/web/20090625142941/http://moonbase.rydia.net/mental/blog/programming/misp-is-a-lisp) that I had a form for the interpreter in mind. At the time of William's original blog posts about Misp I was drawn to his passion and enjoyed the implementations of the language that he posted.[^dustbin] Around the same time I found Paul Graham's original Arc tutorial [tut.txt](http://www.arclanguage.org/tut.txt) and used it extensively to guide me in what to implement next.[^readme] All discussion about Arc aside, I definitely appreciate the clarity and layout of the Arc tutorial and found it a great outline for a little language. 

Finally, as some of you might know I [dabbled in functional programming in JavaScript](https://www.amazon.com/gp/product/B00D624AQO?tag=fogus-20) and even went so far as to create a couple of libraries pushing the fringe of fp in js; namely [Lemonad](https://github.com/fogus/lemonad) and [underscore-contrib](https://github.com/documentcloud/underscore-contrib). Some of the ideas in these libraries found their way into my own interpreter which ultimately pushed my code away from Misp, Little Lisp, and Arc into ...something else -- that something else I'm calling **Tori Lisp -- an ersatz LISP for little birds**.

<center>ToriLisp = Litle Lisp + Misp + tut.txt + Lemonad + underscore-contrib + a pinch of CLJ</center>

Here's a very small sample of the language:

    鳥>  (let (x 3 y 4)
           (+ (* x 2) (* y 2)))
    ;;=> 14

    (def map
      (λ (fn list)
        (if (no list)
          list
          (cons (fn (first list))
                (map fn (rest list))))))

    鳥>  (map (+ 10) '(1 2 3))
    ;;=> [ 11, 12, 13 ]

    鳥>  ({x y | (/ (+ x y) 2)} 2 4)
    ;;=> 3

    鳥>  (len "abc")
    ;;=> 3
    鳥>  (len {a | a})
    ;;=> 1
    鳥>  (len +)
    ;;=> 2
    
    鳥>  (read "(push [1] 'Z)")
    ;;=> [ "'push", [ "'list", 1 ], [ "'quote", "'Z" ] ]
    鳥>  (eval (read "(push [1] 'Z)"))
    ;;=> [ "'Z", 1 ]  

If I wanted to present a list of features then the following list would	work:[^mac]

 - Functional
 - Immutable access to JavaScript arrays and hash-maps.
 - Function auto-currying
 - Function introspection
 - Bottoms-out at JavaScript structures and functions
 - ML-like Refs
 - Lightweight syntax for common language forms

If you're interested in checking out the language then the [ToriLisp Github repository](https://github.com/fogus/tori-lisp) has a quick start, test suite, and its own tut.txt. 

[^dustbin]: Though the implementations did not make the Internet Archive it seems. I reached out to Mr. Taysom years ago and he was kind enough to send me the code but I hesitate to share it publicly without
his approval.

[^readme]: Consider this a form of [README-driven language development](https://tom.preston-werner.com/2010/08/23/readme-driven-development.html).

[^mac]: Currently ToriLisp doesn't have macros though not because it would have been terribly difficult to add. Instead, I wanted to start with functional literals and auto-currying and see how far it could take me. I may add them at a later date but only time will tell.