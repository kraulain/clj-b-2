(ns volcanoes.core
  (:require [clojure.data.csv :as csv]
           [clojure.java.io :as io]))

(def csv-lines
  (with-open [csv (io/reader "/Users/kraulain/projects/personal/clojure/b2/volcanoes/resources/GVP_Volcano_List_Holocene.csv")]
    (doall
     (csv/read-csv csv))))


