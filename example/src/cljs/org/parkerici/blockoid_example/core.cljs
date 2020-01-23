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

(def toolbox
  `[:toolbox
    [:category "Arithmetic" {}
     ~@(mapv (fn [block] [:block (:type block)]) blockdefs)]])

(defn initialize []
  (bo/define-blocks blockdefs)
  (bo/define-workspace
    "blocklyDiv"
    toolbox
    {}
    (fn [_]
      (prn :change)
      )))


;;; TODO code generation, re/frame, etc.
;;; TODO Getting warnings for message string, maybe en not included properly
