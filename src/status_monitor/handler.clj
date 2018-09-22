(ns status-monitor.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Mock data generators

(defn mock-data
  "Mock data generator"
  [maximum-value]
  (if (float? maximum-value)
    (format "%.2f" (rand (+ maximum-value 1)))
    (rand-int (+ maximum-value 1))))



;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Status components using Hiccup and SVG

;; There is a limited number of examples of writing SVG in Clojure Hiccup style.
;; To find out the syntax, i used the SVG specification and translated the elements
;; into Clojure vectors, maps and keywords.
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Element

;; Defining a view-box gives an overall size to the SVG graphic relative to the page.
;; Without the view-box the graphic is given a default 300x150px size and leaves a lot of
;; space in between the components.


(defn component-status-bar
  "Generate an SVG static progress bar

  The progress bar is made from a rectangle with rounded corners for asthetics.
  The rectangle has a boarder, as defined by the :stroke value.

  The rectangle is wrapped with a :view-box to control the size of the generated SVG image.
  Without this :view-box the graphic would be a default size of 300x150px, leaving a
  large gap between images.

  The width of the status bar is controlled by the percentage argument.
  TODO: design a way to set status thresholds for specific types of monitor."

  [percentage]
  [:svg {:view-box "0 0 100 20"
         :width 202
         :height 22}
   [:rect {:x 1
           :y 1
           :width percentage
           :height 20
           :rx 5 :ry 5
           :stroke "black"
           :fill (cond
                   (< percentage 50) "green"
                   (< percentage 60) "orange"
                   (>= percentage 70) "red"
                   :else "grey")}]])


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

            ;; Key systems to monitor displayed in a single bootstrap row, with 3 columns.
            [:div {:class "row"}

             [:div {:class "col-md-4"}
              [:h2 "Application monitor"]
              [:p "CPU load average:"
               (component-status-bar 9)]
              [:p "Memory used:"
               (component-status-bar 55)]
              [:p "Swap used:"
               (component-status-bar 36)]]

             [:div {:class "col-md-4"}
              [:h2 "Database monitor"]
              [:p "CPU load average:"
               (component-status-bar 10)]
              [:p "Tablespace DATA:"
               (component-status-bar 55)]
              [:p "Tablespace INDEX:"
               (component-status-bar 85)]]

             [:div {:class "col-md-4"}
              [:h2 "Messaging monitor"]
              [:p "Asynchronous storage:"
               (component-status-bar 74)]
              [:p "Server memory usage:"
               (component-status-bar 75)]
              [:p "Durable pending messages"
               (component-status-bar 92)]]]]]]))


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



;; Mock data generators

#_(defn mock-data
  "Mock data generator"
  [maximum-value]
  (rand (+ maximum-value 1)))


#_(mock-data 10)
;; => 1.7532050382877502


#_(defn mock-data
  "Mock data generator"
  [maximum-value]
  (if (float? maximum-value)
    (rand (+ maximum-value 1))
    (rand-int (+ maximum-value 1))))

;; Passing an integer number returns a random integer.
(mock-data 10)
;; => 8

;; Passing a float number returns a float.
(mock-data 10.0)
;; => 7.3488245303455635

;; I want to limit a float to a maximum of 2 decimal places


#_(defn mock-data
  "Mock data generator"
  [maximum-value]
  (if (float? maximum-value)
    (with-precision 2 (rand (+ maximum-value 1)))
    (rand-int (+ maximum-value 1))))


(mock-data 10.0);; => 9.735358290172472
;; with-precision only works with BigDecimal type.

#_(clojure.pprint.cl-format 3 9.735358290172472)
;; symbol not found.


(format "%.2f" 9.735358290172472)
;; => "9.74"


#_(defn mock-data
  "Mock data generator"
  [maximum-value]
  (if (float? maximum-value)
    (format "%.2f" (rand (+ maximum-value 1)))
    (rand-int (+ maximum-value 1))))


(mock-data 10.0)
;; => "6.02"
