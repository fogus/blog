# What is it?

A [Ring](https://github.com/mmcgrana/ring) middleware that augments :params according to a parsed [Clojure](http://clojure.org) data literal request body.

## Where is it?

<https://github.com/fogus/ring-clj-params>

## Usage

### Leiningen

In your `:dependencies` section add the following:

    [ring-clj-params "0.1.0"]

### Ring

To use this middleware using Ring and [Compojure](https://github.com/weavejester/compojure), create a new Leiningen project with a `project.clj` file of the form:

	 (defproject awesomeness "0.0.1"
	   :description ""true power awesomeness
	   :dependencies [[org.clojure/clojure "1.3.0"]
	                  [ring "1.0.2"]
	                  [compojure "1.0.1"]
	                  [ring-clj-params "0.1.0"]]
	   :main awesome-app)

Next, create a file in `src` called `my_awesome_service.clj` with the following:

	(ns my-awesome-service
	  (:use compojure.core)
	  (:use ring.middleware.clj-params))

	(defn generate-response [data & [status]]
	  {:status (or status 200)
	   :headers {"Content-Type" "application/clojure"}
	   :body (print-str data)})
	  
	(defroutes handler
	  (GET "/" []
	       (generate-response {:hello :cleveland}))

	  (PUT "/" [name]
	       (generate-response {:hello name})))

	(def app
	  (-> handler
	      wrap-clj-params))

And finally, create another file in `src` named `awesome_app.clj` with the following:

	(ns awesome-app
	  (:use ring.adapter.jetty)
	  (:require [my-awesome-service :as awe]))

	(defn -main
	  [& args]
	  (run-jetty #'awe/app {:port 8080}))

### Testing

Run this app in your console with `lein run` and test with `curl` using the following:

    $ curl -X GET http://localhost:8080/
    
    #=> {:hello :cleveland}                               
    
    $ curl -X PUT -H "Content-Type: application/clojure" \ 
      -d '{:name :barnabas}' http://localhost:8080/
    
    #=> {:hello :barnabas}%  

The Clojure `*eval-read*` functionality is turned off and trying to use it will result in a server-side exception thus resulting in an empty response for the eval form; such as:

    $ curl -X PUT -H "Content-Type: application/clojure" \ 
      -d '{:name #=(+ 1 2)}' http://localhost:8080/ 
    
    #=> ...

I'm currently only supporting data literals for Clojure versions 1.3 and below.  Support for later versions (including [tagged literal support](http://dev.clojure.org/display/design/Tagged+Literals)) is planned.

## Acknowledgments

Thanks to [Mark McGranaghan](http://markmcgranaghan.com/) for his work on Ring and [ring-json-params](https://github.com/mmcgrana/ring-json-params) on which this project was based.  Also thanks to [Craig Andera](https://twitter.com/#!/craigandera) for reminding me about `*read-eval*`.  Finally, thanks to [Relevance](http://thinkrelevance.com) for allowing me the time to work on such projects during work hours.

:F
