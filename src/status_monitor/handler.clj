(ns status-monitor.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Main page for application

(defn monitor-dashboard
  "Returns a mock dashboard page using Hiccup and Bootstrap"
  [request]
  (html5 {:ng-app "Mock Status Monitor Web App" :lang "en"}
         [:head
          [:title "Area51 Mock Status"]

          ;; Bootstrap CSS and JavaScript libraries from the Content Delivery Network
          (include-css "//stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css")
          (include-js  "//stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js")
          (include-js  "//stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.bundle.min.js")

          [:body
           ;; An invisible container to create a default margin at each side of the web page
           [:div {:class "container"}

            ;; Page Header using a large central banner, called a Jumbotron
            [:div {:class "jumbotron"}
             [:h1 "Mock Status Monitor Dashboard"]]

            ;; Key systems to monitor displayed in a single row grid
            [:div {:class "row"}
             [:div {:class "col-md-4"}
              [:h2 "Application monitor"]]

             [:div {:class "col-md-4"}
              [:h2 "Database monitor"]]

             [:div {:class "col-md-4"}
              [:h2 "Messaging monitor"]]]]]]))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Application routing

(defroutes app-routes
  (GET "/" [] monitor-dashboard)
  (route/not-found "Not Found"))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Application entry point
;; app is injected into an embedded Jetty process by lein ring server
;; or by an automatically generated -main function by lein uberjar
;;
;; To understand wrap-defaults and site-defaults, review
;; https://github.com/ring-clojure/ring-defaults

(def app
  (wrap-defaults app-routes site-defaults))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; REPL experiments


;; A quick test of some SVG code to check it renders okay in the static page
;; This is a bit of code taken from ClojureBridge London workshop,
;; used with ClojureScript and Reagent.  It just works in Clojure too :)

#_(defn display-cpu-load-average
    "Graphical representation of CPU average load"
    [cpu-load-average]
    [:svg {:style {:border "1px solid"
                   :background "white"
                   :width "150px"
                   :height "150px"}}
     [:circle {:r 50, :cx 75, :cy 75, :fill "green"}]
     [:circle {:r 25, :cx 75, :cy 75, :fill "blue"}]
     [:path {:stroke-width 12
             :stroke "white"
             :fill "none"
             :d "M 30,40 C 100,40 50,110 120,110"}]])
