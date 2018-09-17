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
