---
title: "Clojure builds as an amalgamation of orthogonal parts"
author: "Fogus"
date: "2021.07.20"
---

# tools.build

The Clojure Core team recently released a new Clojure library, [tools.build](https://github.com/clojure/tools.build), that is a culmination of thought around batteries-included build support for Clojure projects. I won't go into detail around the history and contents of the library in this post because much of that is found elsewhere, including the [announcement post](https://clojure.org/news/2021/07/09/source-libs-builds), the [tools.build guide](https://clojure.org/guides/tools_build), and the [tools.build API docs](https://clojure.github.io/tools.build). Instead, I'll walk through adding tools.build support to a simple project that currently uses [Leiningen](https://leiningen.org) for building and talk a little about how tools.build goes about the same tasks in a different way.

The project that I'll work with is a small personal project called [reinen-vernunft](https://github.com/fogus/reinen-vernunft) and it's build needs are appropriate for the gentle introduction herein.

## Building is a matter of orthogonal parts

The batteries-included build story for Clojure is composed of an amalgam of complementary pieces, including: [tools.deps](https://github.com/clojure/tools.deps.alpha) with deps.edn, [Clojure CLI](https://clojure.org/reference/deps_and_cli), and [tools.build](https://github.com/clojure/tools.build). Therefore, enabling building in [reinen-vernunft](https://github.com/fogus/reinen-vernunft) will require thinking about how these parts work in conjunction. However, to start let me show the existing project.clj file:

```clojure
    ;; project.clj
    (defproject fogus/reinen-vernunft "0.1.1-SNAPSHOT"
      :description "Code conversations in Clojure regarding the application 
                    of pure search, reasoning, and query algorithms."
      :url "https://github.com/fogus/reinen-vernunft"
      :license {:name "Eclipse Public License"
                :url "http://www.eclipse.org/legal/epl-v10.html"}
      :dependencies [[org.clojure/clojure "1.11.0-alpha1"]
                     [org.clojure/core.unify "0.5.7"]
                     [evalive "1.1.0"]]
      :profiles {:dev {:dependencies [[datascript "1.2.2"]]}})
```

This is as bare-bones for a project file as you can create but there are some interesting things to understand if you want to explore how to perform the same tasks as Leiningen with tools.build.

### Dependencies declaration

First, and likely most importantly is the `:dependencies` section of the project file. Clojure provides a way to similarly describe the same set of dependencies using the deps.edn format and indeed, the same set as follows:

```clojure
    ;; deps.edn
    :deps  {org.clojure/core.unify {:mvn/version "0.5.7"}
            evalive/evalive        {:mvn/version "1.1.0"}}
```

This is a subsection of the total deps.edn file posted out of context so to see how it fits into the structure you can look at the [reinen-vernunft deps.edn file](https://github.com/fogus/reinen-vernunft/blob/main/deps.edn). However, you can see that the declaration of dependencies for Leiningen and Clojure is pretty close. The map-based version in deps.edn allows for different types of specifications be they artifact based, Git based, or local libraries but I won't go into those details in this post [but that information is available on the clojure.org site](https://clojure.org/guides/deps_and_cli). One point of interest is that the dependency coordinate for the [Evalive library](https://github.com/fogus/evalive) was prefixed in the deps.edn case and not in the project.clj case. While both will allow unqualified library declarations for now, the tools.deps library will issue a warning should your own projects declare them as dependencies -- rest assured, the author of Evalive has been sacked.

To find those listed dependencies, Leiningen looks in a few select locations by default: the local Maven repository, Maven Central, and Clojars to name the most popular options. The tools.deps library also looks in these places and will download the artifacts into the local Maven repository as expected. Finally, local source is a dependency also and Leiningen looks in the `src` directory by default and so does tools.deps, but my personal preference is to declare the source path explicitly -- YMMV:

```clojure
    ;; deps.edn
    :paths ["src"]
```

### Building

Now that I have dependencies in place I'd like to build an artifact of my own for reinen-vernunft, specifically a jar file containing the Clojure source files for the project. First, I'd like to specify a `build` alias in the deps.edn file that pulls in the tools.build library as a dependency in the `:aliases` map as such:

```clojure
    ;; deps.edn
    :build {:deps {io.github.clojure/tools.build 
                   {:git/tag "v0.1.3" :git/sha "688245e"}}
            :ns-default build}
```

This is a Git based dependency scoped under the `:build` alias that points to a specific Git repository tag and short SHA. However, now that I've pulled in that dependency how do I do anything? The tools.build library provides a set of functions and utilities that allow builds to be described as code. Indeed, a file named build.clj serves as this program and starts by pulling in the tools.build api:

```clojure
    ;; build.clj
    (ns build
      (:require [clojure.tools.build.api :as b]))
```

Where Leiningen's project.clj declares its configuration parameters as syntax in the `defproject` form, tools.build parameters are just vars in code:

```clojure
    ;; build.clj
    (def lib 'fogus/reinen-vernunft)
    (def version (format "0.1.%s" (b/git-count-revs nil)))
    (def target-dir "target")
    (def class-dir (str target-dir "/" "classes"))
    (def jar-file (format "%s/%s-%s.jar" target-dir (name lib) version))
    (def src ["src/clj"])
    (def basis (b/create-basis {:project "deps.edn"}))
```

These vars describe various things, including version numbers built from calculated Git revisions, class target paths, Jar file names, and useful build configuration. To create a build target function in the build.clj file is as simple as writing a function, in this case `clean` that takes a map argument (although ignored in this case), that calls out to the tools.build API task functions:

```clojure
    ;; build.clj
    (defn clean
      "Delete the build target directory"
      [_]
      (println (str "Cleaning " target-dir))
      (b/delete {:path target-dir}))
```

This `clean` target is ready to run using the Clojure CLI by issuing the following command at your command prompt:

```clojure
    $ clj -T:build clean
    Cleaning target
```

While not earth-shattering, the `clean` target is useful when you're working on a project and need to clean existing artifacts before building them anew. Indeed, one such artifact is a Jar file that for reinen-vernunft means an archive of the name specified by the `jar-file` var and containing the source specified with the `src` var. A `jar` target would need to do the following tasks:

 - Create a pom file
 - Copy source to an intermediate location
 - Archive the contents of the intermediate location

The implementation is as follows:

```clojure
    ;; build.clj
    (defn jar
      "Create the jar from a source pom and source files"
      [_]
      (b/write-pom {:class-dir class-dir
                    :lib lib
                    :version version
                    :src-pom "pom.xml"
                    :basis basis
                    :src-dirs src})
      (b/copy-dir {:src-dirs src
                   :target-dir class-dir})
      (b/jar {:class-dir class-dir
              :jar-file jar-file}))
```

This `jar` target function is fairly straight-forward in that it: 1) writes a pom to target dir, 2) copies src files target dir, and 3) archives these files into a JAR file. One interesting aspect of the `jar` target is that it uses a source pom as the base for the new pom. This is the prefered way to seed a pom with metadata about a project that in Leiningen often stands as `defproject` parameters. Specifically, the `:description` and `:license` fields in the project.clj file shown in the beginning of this post become XML elements in the source pom.xml fed into the `b/write-pom` task:

```xml
    ;; pom.xml
    <description>Code conversations in Clojure regarding the application 
     of pure search, reasoning, and query algorithms!</description>
    <licenses>
      <license>
        <name>Eclipse Public License</name>
        <url>http://www.eclipse.org/legal/epl-v10.html</url>
      </license>
    </licenses>
```

There are hundreds of items that could go into a pom and so rather than offer a subset (or worse, all) as parameters on `b/write-pom` the tools.build library uses the source pom seed to create a new pom in the `:class-dir` directory.

 Running the follow will create the jar file in the target directory:

```sh
     $ clj -T:build jar
```

### Testing

While testing is technically outside of the purview of tools.build, the fact is that it's an important part of most programmers' dev cycle. From the beginning, Leiningen supported automated testing via a close integration with clojure.test. On the other hand, the Clojure CLI is agnostic to testing tools but instead allows a generic way to execute Clojure functions via the `-X` flag. To enable testing one should wire a test runner into their deps.edn file and create an alias for execution via the CLI. The reinen-vernunft library's deps.edn has the following `:test` alias defined:

```clojure
    ;; deps.edn
    :test {:extra-paths ["test"]
           :extra-deps {io.github.cognitect-labs/test-runner 
                         {:git/tag "v0.4.0" :git/sha "334f2e2"}}
           :main-opts ["-m" "cognitect.test-runner"]
           :exec-fn cognitect.test-runner.api/test}
```

This sets up the `:test` alias to call out to the [test-runner](https://github.com/cognitect-labs/test-runner) which looks for tests in a certain place of a certain filename and executes them with `clojure.test`. This is done with the following command:

```sh
    $ clj -X:test
    Could not locate datascript/core__init.class, datascript/core.clj...
```

Whoops! As it turns out the library has a test dependency on the [Datascript library](https://github.com/tonsky/datascript) that in a project.clj file is declared in a `:dev` alias in the `:profiles` map. That same dependency can reside under the aforementioned `:test` alias in deps.edn but for my purposes I decided to create another alias named `:dev` that declares that dependency:

```clojure
    ;; deps.edn
    :dev {:extra-deps {datascript/datascript {:mvn/version "1.2.2"}}}
```

And the test execution is initiated with the following command:

```sh
    $ clj -X:dev:test
    Running tests in #{"test"}
    ...
    0 failures, 0 errors.
```

And that's it. Once again, you can view the whole [reinen-vernunft deps.edn file](https://github.com/fogus/reinen-vernunft/blob/main/deps.edn) and the [build.clj file](https://github.com/fogus/reinen-vernunft/blob/main/build.clj) to view everything in context.

### Summary

Clojure's latest features in [tools.build](https://github.com/clojure/tools.build), [tools.deps](https://github.com/clojure/tools.deps.alpha), and the [Clojure CLI](https://github.com/clojure/https://clojure.org/reference/deps_and_cli)) work to define a orthogonal set of tools that work together to form the basis for a batteries-included story for Clojure builds. Each part is a powerful tool in its own right but the amalgamation forms a powerful way to express the build needs of your own projects. In the future I hope to expand on this post with some other interesting features available to Clojure programmers but in the meantime consider exploring this toolset yourself.
