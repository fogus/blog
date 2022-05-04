## What lein-generative is

A [Leiningen](https://github.com/technomancy/leiningen) plugin used to run generative tests defined using the [test.generative](https://github.com/clojure/test.generative) Clojure contrib library.

## Getting

*This is currently only proven to work in Leiningen versions earlier than 2.0. It might work with v2.0, but I've yet to try.*

Add the following to your Leiningen `project.clj` file in the `:dev-dependencies` section:

    [lein-generative "0.1.4.0"]

The version number of lein-generative will track the latest released version of test.generative. The end of the version number will be the specific sub-version of this plugin.

## Using

To run tests defined with test.generative you can simply run:

    $ lein generative

This will, by default, run all tests in the project's `test` directory. If you prefer to run tests in a different directory then you can add something like the following to your `project.clj` properties:

    :generative-path "/path/to/your/tests"

Source code for this version will be made available very soon.

## Resources

* [Source code](http://github.com/fogus/lein-generative)
* [The Generative Generation](https://github.com/abedra/the-generative-generation) by Aaron Bedra
* [Programming Clojure](http://www.amazon.com/Programming-Clojure-Stuart-Halloway/dp/1934356867?tag=fogus-20) (2nd edition) by Stuart Halloway and Aaron Bedra
* [Haskell Quickcheck](http://www.haskell.org/haskellwiki/Introduction_to_QuickCheck) (a library similar in intent to test.generative)

## Thanks

A HUGE thanks to [Roman Gonzalez](http://github.com/roman) for putting this project in motion and allowing me to contribute.
