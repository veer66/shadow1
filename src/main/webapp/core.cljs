(ns webapp.core)

(defn init []
  (let [p-main (js/document.querySelector "p#main")
        txt (js/document.createTextNode "FROM shadow1.webapp.core/init")]
    (.appendChild p-main txt)))
