(ns fogus.feed
  (:require [clj-rss.core :as rss]
            [clojure.edn :as edn]))

(set! *warn-on-reflection* true)

(defn fetch-index [path]
  (edn/read-string
   (slurp path)))

(defn massage-item [item]
  (-> item
      (update :description #(str "<![CDATA[" % "]]>"))
      (update :pubDate #(java.time.Instant/parse %))))

(comment
  (def index (fetch-index "posts/index.edn"))

  (let [{:keys [title description link copyright]} index
        {:keys [max-entries] :or {max-entries 10}} (meta index)]
    (spit "feed.xml"
          (rss/channel-xml {:title title
                            :link link
                            :description description
                            :language "en-US"
                            :lastBuildDate (java.time.Instant/now)
                            :copyright copyright}
                           (vec (map massage-item (take max-entries (:entries index)))))))

)
