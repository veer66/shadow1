(ns webapp.core
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            ["@material-ui/core" :as mui]
            ["@material-ui/lab" :as mui-lab]))

(defn ui
  []
  [:div
   [:h1 "Auto-complete"]
   [:> mui-lab/Autocomplete
    {:render-input #(r/create-element mui/TextField
                                      (js/Object.assign #js{:fullWidth true
                                                            :variant "outlined"}
                                                        %))
     :options (clj->js [{:name "toto"}  {:name "tata"}])
     :get-option-label #(.-name %)}]])


(defn render
  []
  (r/render [ui]
            (js/document.querySelector "div#app")))

(rf/reg-event-db
 :initialize
 (fn [_ _]
   {}))

(defn ^:dev/after-load reload []
  (rf/clear-subscription-cache!)
  (render))

(defn init []
  (rf/dispatch-sync [:initialize])
  (render))
