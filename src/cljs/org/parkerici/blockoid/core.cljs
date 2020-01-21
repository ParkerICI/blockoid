(ns org.parkerici.blockoid.core
  (:require
   [cljsjs.blockly :as blockly]         ;TODO namespace abbrev not used so not needed
   [cljsjs.blockly.blocks] 
   [clojure.data.xml :as xml]))

;;; This file contains a thin, application-independent Clojurescript API for Blockly.
;;; It can eventually be spun out into its own open-source project.

(def workspace (atom nil))

(defn define-blocks
  [blockdefs]
  (.defineBlocksWithJsonArray js/Blockly (clj->js blockdefs)))

(defn define-workspace
  [div toolbox-def options change-handler]
  (swap! workspace 
         (fn [_]
           ;; see options: https://developers.google.com/blockly/guides/get-started/web#configuration
           (.inject js/Blockly div (clj->js (merge {:toolbox (xml/emit-str toolbox-def)} options)))))
  (.addChangeListener @workspace change-handler))

(defn get-workspace-xml-string
  []
  (let [raw (.workspaceToDom js/Blockly.Xml @workspace)
        string (.domToText js/Blockly.Xml raw)]
    string))
        
(defn get-workspace-xml
  []
  (xml/parse-str (get-workspace-xml-string)))

(defn get-block-xml-string
  [block]
  (let [raw (.blockToDom js/Blockly.Xml block)
        string (.domToText js/Blockly.Xml raw)]
    string))

(defn get-block-xml
  [block]
  (xml/parse-str (get-block-xml-string block)))

(defn clear-workspace
  []
  (.clear @workspace))

(defn encode-xml
  [xml]
  (let [xml-string (xml/emit-str xml)]
    (.textToDom js/Blockly.Xml xml-string)))
  
(defn set-workspace-xml
  [xml]
  (let [dom (encode-xml xml)]
    (clear-workspace)
    (.domToWorkspace js/Blockly.Xml dom @workspace)
    ))

(defn add-workspace-xml
  [xml]
  (let [dom (encode-xml xml)]
    (.appendDomToWorkspace js/Blockly.Xml dom @workspace)))

;;; ⊓⊔⊓⊔ Selection ⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔

(defn selected-block
  []
  (.-selected js/Blockly))

(defn root-block
  [block]
  (if-let [parent (.getParent block)]
    (root-block parent)
    block))

(defn relevant-xml
  "Returns the XML of the selected block group, or the upper left one if none is selected"
  []
  (if-let [selected (selected-block)]
    (get-block-xml (root-block selected))
    (first (:content (get-workspace-xml)))))

;;; ⊓⊔⊓⊔ Compaction ⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔⊓⊔

(declare compact)

(defn compact-list
  [block-xml]
  (if-let [next (some #(and (= (name (:tag %)) "next") %)
                      (:content block-xml))]
    (cons (compact block-xml)
          (compact-list (first (:content next))))
    (list (compact block-xml))))

(defn compact
  "Turns raw Block XML into something more semantic and easier to work with."
  [block-xml]
  (if (map? block-xml)
    (case (name (:tag block-xml))
      "xml"
      (map compact (:content block-xml))
      ("field" "value")
      {(get-in block-xml [:attrs :name]) ;always V? maybe not. Also assuming only one value, may not always be true
       (compact (first (:content block-xml)))}
      "statement"
      {(get-in block-xml [:attrs :name])
       (compact-list (first (:content block-xml)))
       }
      "next" nil
      "block"
      {:type (get-in block-xml [:attrs :type])
       :name (get-in block-xml [:attrs :name])
       :btype (keyword (name (:tag block-xml)))
       :children (if (= (:content block-xml) ["unknown"])
                   nil
                   (apply merge (map compact (:content block-xml))))}
      (throw (ex-info "Couldn't interpret Blockly XML"
                      {:xml block-xml}))
      )
    block-xml))

;;; TODO compact → Blockly


