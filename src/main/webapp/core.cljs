(ns webapp.core)

(def msg "Hello")

(defn remove-all-children [elem]
  (loop [elem elem]
    (when-let [child (.-firstChild elem)]
      (do
        (.removeChild elem child)
        (recur elem)))))

(defn render []
  (let [p-main (js/document.querySelector "p#main")
        txt (js/document.createTextNode msg)]
    (js/console.log (remove-all-children p-main))
    (.appendChild p-main txt)))

(defn ^:dev/after-load reload []
  (render))

(defn init []
  (render))


