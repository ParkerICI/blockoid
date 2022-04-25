(defproject blockoid-example "0.0.1-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.520"]
                 [org.parkerici/blockoid "0.3.5"]]

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
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "org.parkerici.blockoid-example.core/initialize"}
     :compiler     {:main                 org.parkerici.blockoid-example.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true

                    :optimizations        :none
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "prod"
     :source-paths ["src/cljs"]
     :compiler     {:main            org.parkerici.blockoid.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :output-dir      "resources/public/js/compiled/outprod"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})
