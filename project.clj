(defproject org.parkerici/blockoid "0.0.1-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.xml "0.2.0-alpha6"]
                 [org.clojure/clojurescript "1.10.520"]
                 [cljsjs/blockly "3.20200123.1-0"]
                 [inflections "0.13.2"] ;TODO probably not?
                 ]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths [ "src/cljs"]
  :test-paths [ "test/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:server-port 3457
             :css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [mock-clj "0.2.1"]
                   [figwheel-sidecar "0.5.19"
                    :exclusions [org.clojure/clojurescript]]]
    :plugins      [[lein-figwheel "0.5.19"]]}
   :prod {}

   }
  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "src/cljc"]
     :figwheel     {:on-jsload "org.parkerici.blockoid.core/initialize"}
     :compiler     {:main                 org.parkerici.blockoid.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true

                    :optimizations        :none
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}
    

    {:id           "prod"
     :source-paths ["src/cljs" "src/cljc"]
     :compiler     {:main            org.parkerici.blockoid.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir      "resources/public/js/compiled/outprod"
                    ;; TODO :advanced is better but requires special handling for Blockly refs
                    :optimizations   :simple
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})
