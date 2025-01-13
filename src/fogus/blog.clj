(ns fogus.blog
  (:require [cybermonday.core :as md]
            [clojure.string :as string]
            [clojure.java.io :as io]))

(set! *warn-on-reflection* true)

(defn traverse-path [path]
  (let [directory (clojure.java.io/file path)
        dir? #(.isDirectory ^java.io.File %)]
    (map #(.getPath ^java.io.File %)
         (filter (comp not dir?)
                 (tree-seq dir? #(.listFiles ^java.io.File %) directory)))))

(defn- path-parts [d]
  (vec (butlast d)))

(defn- filename-part [d]
  (last d))

(defn explode-path [path]
  (let [d (-> path
              (string/split #"/")
              rest
              vec)]
    {:path (path-parts d)
     :filename (filename-part d)}))

(def raw-paths (explode-path "posts"))

(defn- process-paths [paths]
  (map explode-path paths))

(def paths (process-paths raw-paths))

(defn unique-paths [paths]
  (->> paths
       (map :path)
       set
       sort))

(defn- create-dir [parts & {:keys [in-path] :or {in-path "_output"}}]
  (when (seq parts)
    (->> parts
         (cons in-path)
         (string/join "/")
         io/file
         .mkdirs)))

(def all-paths
  (-> paths
      unique-paths))

(defn- prep-output-dir [paths]
  (let [res (doall (map create-dir all-paths))]
    {:dirs-created (count (filter true? res))
     :dirs-skipped (count (filter nil? res))
     :dirs-wanted  (count paths)}))

(comment
  
  
  (dirtree "posts")

  (count paths)

  (create-dir ["_output"])
  (create-dir (second all-paths))

  (prep-output-dir all-paths)
  
  (md/parse-md "foo\n====")
  

)
