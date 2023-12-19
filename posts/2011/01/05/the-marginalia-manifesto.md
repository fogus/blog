*[source](https://github.com/fogus/marginalia)*, *[official site](http://fogus.me/fun/marginalia/)*, *[example output](http://fogus.me/fun/marginalia/)*, *[guide](https://github.com/fogus/marginalia/wiki)*

The importance of documentation
-------------------------------

In late 2010 I was motivated by posts by [Tom Preston-Werner][rdd], [Reginald Braithwaite][reg], and [Luigi Montanez][ddd] to create a Clojure clone of the excellent [Docco](http://jashkenas.github.com/docco/) documentation generator written in [CoffeeScript](http://jashkenas.github.com/coffee-script/).  The byproduct of this motivation was [Marginalia](https://github.com/fogus/marginalia).  The goal of Marginalia was to create a full-blown [literate programming][literate] system[^lit] with Docco-esque functionality as a way-station along the way.

> No one will give a crap about your crap if your documentation is crap
> 
> -- fogus

I have a very strong opinion regarding the importance of clear and complete documentation.  My reaction to poorly documented code, products, and services is visceral to the point that I often refuse to release even the most humble library without code comments, examples, tests, invariant definitions, a logo, and an "official website".  **However**, time is not always on my side for personal projects, so I am constantly looking for ways to minimize the amount of work required to generate well-documented software without sacrificing quality.  Marginalia is a step in that direction.  However, I piddled around with a comment parser and created the code that builds a tree of code-lines associated with comment lines.  Having scratched that particular itch I then took a detour into researching literate programming proper, leaving behind a half-baked mess.

[ddd]: http://luigimontanez.com/2010/reading-code-is-good-writing-documentation-is-better/

[rdd]: http://tom.preston-werner.com/2010/08/23/readme-driven-development.html

Enter Zachary Kim
-----------------

You may already know [Zachary Kim](http://zacharykim.com/) as the creator of the excellent [ClojureDocs](http://clojuredocs.org/) website; an invaluable resource for finding Clojure API examples.  Zachary resurrected my original source for Marginalia and single-handedly molded it into a tool worth using.  Marginalia would be nothing without his vision and hard-work.  I would also like to thank [Justin Balthrop](http://ninjudd.com/) and [Brenton Ashworth](http://formpluslogic.blogspot.com/) for their support and code contributions.

*If you find Marginalia useful (or even just cool), then please do visit [ClojureDocs](http://clojuredocs.org/) and donate some of your time to enhance its existing examples and/or fill in the holes as you find them.*

Current features
----------------

Before I dive into a diatribe, I'll take a moment to go over some of the details of Marginalia.  You can always see the current state of the [output of Marginalia](http://fogus.me/fun/marginalia/).  A high-level features list currently stands at:

* HTML generation from Clojure source
* Markdown support within comments and docstrings
* Latex formatting support (via [MathJax](http://www.mathjax.org/))
* Leiningen and Cake support

Usage
-----

Currently Marginalia can be used in a number of ways as described below.  In either case, the output document will be generated in `$PWD/doc/uberdoc.html`.

### Command line

You can download the [Marginalia 0.3.2 jar including packaged dependencies from Github](https://github.com/downloads/fogus/marginalia/marginalia-0.3.2-standalone.jar).

Running Marginalia given the jar file linked above is as easy as:

    java -jar marginalia-0.3.2-standalone.jar

This will search the `$PWD` for a `src` directory which it will then traverse looking for Clojure source files to parse and generate documentation for.  Marginalia also takes specific locations and files to generate docs for:

    java -jar marginalia-0.3.2-standalone.jar <files...>

Arguments can be specific files or directories.

### Leiningen

To use Marginalia in your own projects simply add the following to your `project.clj` file in the `:dev-dependencies` section:

    [marginalia "0.3.2"]

After executing `lein deps` you can generate your complete source documentation with the following command:

    lein marg

More information regarding usage scenarios (e.g. Cake support, programmatic use, etc.) will be provided on the [official Marginalia website](http://fogus.me/fun/marginalia).

TODO features
-------------

Marginalia is useable today, but more work is needed.  At the moment the parser is based entirely on regular expressions and as a result might miss some documentation in code with non-standard formatting.  Fixing the parser is the most glaring need, but other notable plans include:

* Paragraph anchors
* Smart code-parser
* Modularized documents
* Maven support
* Alternative output formats
* Special handling of `:pre` and `:post`
* Plugins


[reg]: https://github.com/raganwald/homoiconic/blob/master/2010/11/docco.md

[literate]: http://www.literateprogramming.com/

[^lit]: While a worthy goal initially, it has since been superseded by the [excellent work by Tim Daly](http://groups.google.com/group/clojure/browse_frm/thread/664a1d305f32ab90) of which I hope to contribute ideas, code, and documentation.

Now that you know something about Marginalia, why should you want to use it?

A new way to think about programs
---------------------------------

What if your code and its documentation were one and the same?

Much of the philosophy guiding literate programming is the realization of the answer to this question.  However, if literate programming stands as a comprehensive programming methodology at one of end of the spectrum and no documentation stands as its antithesis, then Marginalia falls somewhere between.  That is, you should always aim for comprehensive documentation, but the shortest path to a useful subset is the commented source code itself.

The art of Marginalia
---------------------

If you're fervently writing code that is heavily documented, then using Marginalia for your Clojure projects is as simple as running it on your codebase.  However, if you're unaccustomed to documenting your source, then the following guidelines[^cong] will help you make the most out of Marginalia for true-power documentation:

- Start by running Marginalia against your code
- Cringe at the sad state of your code commentary
- Add docstrings and code comments[^comments] as appropriate (I find smallish functions with descriptive names and comprehensive docstrings to be ideal -- very often docstrings will be longer than the code they describe)
- Generate the documentation again
- Read the resulting documentation
- Make changes to code and documentation so that the "dialog" flows sensibly
- Repeat the previous 3 steps until complete

Following the steps outlined will work to make your code not only easier to follow -- it will make it better.  The very process of using Marginalia will help[^help] to crystalize your understanding of problem and its solution(s).  The quality of the prose in your documentation will often reflect the quality of the code itself thus highlighting problem areas.  The elimination of problem areas will solidify your code and its accompanying prose.  Marginalia provides a virtuous circle spiraling inward toward maximal code quality.

[^cong]: These guidelines are a mixture of my own process and those outlined by Reginald Braithwaite regarding Docco.

[^comments]: A proliferation of regular comments (i.e. `; this kind`) within the body of Clojure functions can be a "smell".  If you find yourself with a mess of comments, take a step back and see how the surrounding context can be broken out into separate functions, each with its own docstring.

[^help]: Code documentation will help crystalize your understanding, but other factors are equally important in squaring the circle of software complexity.

:F
