(defproject org.parkerici/blockoid "0.3.6"
  :description "Clojurescript shim for Blockly"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :url "http://github.com/ParkerICI/blockoid"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.xml "0.2.0-alpha6"]
                 [org.clojure/clojurescript "1.10.520"]
                 [re-frame "0.10.9"]
                 [cljsjs/blockly "3.20200123.1-0"]]

  :plugins [[lein-cljsbuild "1.1.7"]]
  :deploy-repositories [["clojars" {:sign-releases false}]]
  :source-paths [ "src/cljs"]
  :test-paths [ "test/cljs"]
  :codox
  {:project {:name "Example Project", :version "1.0.0"}
   :namespaces :all
   :doc-files ["doc/blockoid.md"]}  
  ;; Only for testing, there's no reason to build this independently.
  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "src/cljc"]
     :compiler     {:main                 blockoid.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :optimizations        :none
                    :infer-externs        true
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}
    ]}
  )
