(defproject clj-nativedep "0.2.1"
  :description "Clojure native dependency loader"
  :url "http://www.vnetpublishing.com"
  :license {:name "VNETLPL - Limited Public License"
            :url "http://www.vnetpublishing.com/Legal/Licenses/2010/10/vnetlpl.txt"}
  :plugins [[lein-codox "0.9.1"]
            [lein-marginalia "0.8.0"]
            [lein-localrepo "0.5.3"]]
  :codox {:output-path "doc/codox"
          :source-uri "https://github.com/rritoch/clj-nativedep/tree/v0.2.1/{filepath}#L{line}" 
          :src-linenum-anchor-prefix "L"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :aot :all
  :jar-name "clj-nativedep-lib-0.2.1.jar"
  :uberjar-name "clj-nativedep-0.2.1.jar")

