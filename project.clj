(defproject org.parkerici/blockoid "0.3.0"
  :description "Clojurescript shim for Blockly"
  :license {:name "Eclipse Public License" ;TODO 
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "http://github.com/ParkerICI/blockoid"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.xml "0.2.0-alpha6"]
                 [org.clojure/clojurescript "1.10.520"]
                 [cljsjs/blockly "3.20200123.1-0"]]

  :plugins [[lein-cljsbuild "1.1.7"]]
  :repositories [["github" {:url "https://maven.pkg.github.com/ParkerICI/mvn-packages"
                            :sign-releases false
                            :username :env/gith_user
                            :password :env/gh_token
                            :credentials :gpg
                            }]]
  :source-paths [ "src/cljs"]
  :test-paths [ "test/cljs"]
  :codox
  {:project {:name "Example Project", :version "1.0.0"}
;   :metadata {:doc "FIXME: write docs"}
   :namespaces :all
;   :doc-files ["doc/intro.md"               "doc/formatting.md"]
;   :source-uri   "https://github.com/weavejester/codox/blob/{version}/codox.example/{filepath}#L{basename}-{line}"
   }  

  ;; Only for testig, there's no reason to build this independently.
  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs" "src/cljc"]
     :compiler     {:main                 mrfrieze.core
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
