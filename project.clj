(defproject org.parkerici/blockoid "0.0.2"
  :description "Clojurescript shim for Blockly"
  :license {:name "Eclipse Public License" ;TODO 
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "http://github.com/ParkerICI/blockoid"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.xml "0.2.0-alpha6"]
                 [org.clojure/clojurescript "1.10.520"]
                 [cljsjs/blockly "3.20200123.1-0"]]

  :repositories [["github" {:url "https://maven.pkg.github.com/ParkerICI/blockoid"
                            :username :env/gh_user
                            :password :env/gh_token
                            :sign-releases false ;TODO 
                            }]]
  :source-paths [ "src/cljs"]
  :test-paths [ "test/cljs"]
  )
