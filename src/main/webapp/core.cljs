(ns webapp.core
  (:require [reagent.core :as r]))

(def msg "Hello")

(defn ui
  []
  [:div
   [:h1 "DEMO"]
   [:p msg]])

(defn render
  []
  (r/render [ui]
            (js/document.querySelector "div#app")))

(defn ^:dev/after-load reload []
  (render))

(defn init []
  (render))


