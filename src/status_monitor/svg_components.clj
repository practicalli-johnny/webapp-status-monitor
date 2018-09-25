(ns status-monitor.svg-components)

;; Converting HTML SVG components into equivalent Clojure code
;; Ideally create a library of components that can be easily used
;;
;; HTML SVG specification from Mozilla
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Element

;; An alternative approach would be to write a parser that
;; generated Clojure Hiccup style code from HTML SVG code.




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Demos



;; Circle in a box
(def circle-in-a-box
  [:svg {:version "1.1"
         :base-profile "full"
         :width 300
         :height 200
         :xmlns "http://www.w3.org/2000/svg"}

   [:rect {:width "100%"
           :height "100%"
           :fill "blue"}]
   [:circle {:cx 150
             :cy 100
             :r 80
             :fill "green"}]
   [:text {:x 150
           :y 125
           :font-size 60
           :text-anchor "middle"
           :fill "white"}
    "SVG"]])

;; <svg version="1.1"
;;   baseProfile="full"
;;   width="300" height="200"
;;   xmlns="http://www.w3.org/2000/svg">

;;   <rect width="100%" height="100%" fill="red" />
;;   <circle cx="150" cy="100" r="80" fill="green" />
;;   <text x="150" y="125" font-size="60" text-anchor="middle" fill="white">SVG</text>
;; </svg>
