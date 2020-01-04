(ns webapp.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@material-ui/core" :as mui]
            ["material-ui-chip-input" :as muci]
            ))

(def ChipInput muci/default)
(def msg "Hello")

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {:cnt 1}))

(rf/reg-event-db
 :plusone
 (fn [db _]
   (update db :cnt inc)))

(rf/reg-sub
 :cnt
 (fn [db _]
   (:cnt db)))

(defn ui
  []
  [:div
   [:h1 "ChipInput"]
   [:> ChipInput]
   [:> mui/Button {} "KAKA"]
   ])

(prn "@@@")

(defn render
  []
  (r/render [ui]
            (js/document.querySelector "div#app")))


(r/create-element "div")

(defn ^:dev/after-load reload []
  (rf/clear-subscription-cache!)
  (render))

(defn init []
  (rf/dispatch-sync [:initialize])
  (render))
