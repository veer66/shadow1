(ns webapp.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]))

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
   [:h1 "DEMO"]
   [:p msg]
   [:p @(rf/subscribe [:cnt])]
   [:input {:type "button"
            :value "+1"
            :on-click #(rf/dispatch [:plusone])}]])

(defn render
  []
  (r/render [ui]
            (js/document.querySelector "div#app")))

(defn ^:dev/after-load reload []
  (rf/clear-subscription-cache!)
  (render))

(defn init []
  (rf/dispatch-sync [:initialize])
  (render))
