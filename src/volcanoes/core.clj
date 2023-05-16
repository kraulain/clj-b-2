(ns volcanoes.core
  (:require [clojure.data.csv :as csv]
           [clojure.java.io :as io]))


;; Read csv file and save in memory
(def csv-lines
  (with-open [csv (io/reader "/Users/kraulain/projects/personal/clojure/b2/volcanoes/resources/GVP_Volcano_List_Holocene.csv")]
    (doall
     (csv/read-csv csv))))


(defn transform-header
  "Transform a header string into a clojure keyword"
  [header]
  (if (= header "Elevation (m)")
    :elevation-meters
    (-> header
        clojure.string/lower-case
        (clojure.string/replace #" " "-")
        keyword)))


(defn transform-header-row
  "Transforms the strings in a header row into valid keywords"
  [header-row]
  (map transform-header header-row))


;;Extract volcano records into a clojure map
(def volcano-records
  (let [csv-lines (rest csv-lines)
        header-line (transform-header-row (first csv-lines))
        volcano-lines (rest csv-lines)]
    (map (fn [volcano-line]
           (zipmap header-line volcano-line))
         volcano-lines)))


;;Extract all volcano types
(def types (set (map :primary-volcano-type volcano-records)))
