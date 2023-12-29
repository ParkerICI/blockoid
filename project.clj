(defproject org.candelbio/blockoid "1.0.1"
  :description "Clojurescript shim for Blockly"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :url "http://github.com/candelbio/blockoid"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/data.xml "0.2.0-alpha6"]
                 #_ [org.clojure/clojurescript "1.11.121"]
                 [re-frame "1.4.2"]
                 [thheller/shadow-cljs "2.20.10"] ;NOTE: going up to current version 2.22.10 causes a compile error "2.20.10"
                 ]
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
