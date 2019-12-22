(ns webapp.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@material-ui/core" :as mui]
            ))

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
   [:> mui/Button
      {:variant "contained"
       :color "primary"
       :on-click #(rf/dispatch [:plusone])
       }
      "+1"
      ]])

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
