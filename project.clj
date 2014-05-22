(defproject clj-nativedep "0.1.0"
  :description "Clojure native dependency loader"
  :url "http://www.vnetpublishing.com"
  :license {
    :name "VNETLPL - Limited Public License"
    :url "http://www.vnetpublishing.com/Legal/Licenses/2010/10/vnetlpl.txt"
  }
  :plugins [
    [codox "0.8.6"]
    [lein-marginalia "0.7.1"]
    [lein-localrepo "0.5.3"]
  ]
  :codox { 
    :output-dir "doc/codox"
    :src-dir-uri "https://github.com/rritoch/clj-nativedep/tree/master/" 
    :src-linenum-anchor-prefix "L"
  }

  :dependencies [[org.clojure/clojure "1.5.1"]]
  :aot :all
  :jar-name "clj-nativedep-lib-0.1.0.jar"
  :uberjar-name "clj-nativedep-0.1.0.jar"
)

