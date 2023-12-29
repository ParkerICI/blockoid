(defproject blockoid-example "1.0.0"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 #_ [org.clojure/clojurescript "1.10.520"]
                 #_ [org.parkerici/blockoid "1.0.0"]
                 [thheller/shadow-cljs "2.20.10"]]

  :min-lein-version "2.5.3"

  :source-paths [ "src/cljs"]
  :test-paths [ "test/cljs"]

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [mock-clj "0.2.1"]
                   ]
    :plugins      []}
   }
)
