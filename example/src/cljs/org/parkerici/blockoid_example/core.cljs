(ns org.parkerici.blockoid-example.core
  (:require [org.parkerici.blockoid.core :as bo])
  )

(defn fn-block [f]
  {:type (str f "-fn")
   :message0 (str f "%1 %2")
   :args0 [{:type "field_input"
            :name "a"}
           {:type "field_input"
            :name "b"}]
   })

(def blockdefs
  (map fn-block '(+ - * /)))

;;; → blockoid
;;; TODO color, contained blocks, etc
(defn block-xml
  [blockdef]
  (let [type (:type blockdef)]
    {:tag :block
     :attrs {:type type}
     }))

;;; → blockoid
(defn toolbox-category [cname blocks]
  {:tag :category
   :attrs {:name cname
           :expanded true}
   :content (mapv block-xml blocks)
   })

(defn toolbox []
  {:tag "xml"
   :content [(toolbox-category "Arithmetic" blockdefs)]})

(defn initialize []
  (prn :toolbox (toolbox))
  (bo/define-blocks blockdefs)
  (bo/define-workspace
    "blocklyDiv"
    (toolbox)
    {}
    (fn [_]
      (prn :change)
      )))


