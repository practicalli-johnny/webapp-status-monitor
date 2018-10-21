(ns status-monitor.svg-components)

;; Converting HTML SVG components into equivalent Clojure code
;; Ideally create a library of components that can be easily used
;;
;; HTML SVG specification from Mozilla
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Element

;; An alternative approach would be to write a parser that
;; generated Clojure Hiccup style code from HTML SVG code.


;; HTML Elements in Hiccup format
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; HTML can be generated using Clojure Hiccup style data structures,

#_(defn web-link
  "Generate a hypertext link in HTML using the website address specified"
  [website-URL]
  [:a {:href website-URL
       :target "_blank"}
   (str website-URL)])


;; Make the weblink function polymorphic, so it returns different results
;; based on the number of arguments passed
;;
;; Starting to think passing a map of arguments could be very useful and
;; make arguments to calling these functions easy to explain.

(defn web-link
  "Generate a hypertext link in HTML using the website address specified.
  Unless text for the link is provided, the text will be the web address itself"
  ([website-URL]
   [:a {:href website-URL
        :target "_blank"}
    (str website-URL)])
  ([website-URL link-text]
   [:a {:href website-URL
        :target "_blank"}
    (str link-text)]))

(defn image
  [image-URL]
  [:img {:src image-URL
         :alt "Image description"}])

(defn image-linked
  [image-URL website-URL]
  [:a {:href website-URL}
   [:img {:src image-URL
          :target "_blank"}]])




;; Structural elements
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; <defs>, <g>, <svg>, <symbol>, <use>

;; SVG graphic

;; An SVG image starts with the <svg> tag in HTML, which in Clojure we can write as:

[:svg ]

;; The vector represents the scope of the tag and so no closing tag is required
;; The contents of the image is contained within the vector
;; :svg is a keyword representing the html tag of the same name

;; Adding styles is via a map of key values, where those keys are
;; keyword representations of CSS style names (typically using kebab case and converted by hiccup)
;; The values in the map are values passed to the HTML as strings.
;; Any values that are not numbers should be written inside Clojure strings




;; Group elements into one SVG graphic
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Basic shapes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;
;; <circle>, <ellipse>, <line>, <polygon>, <polyline>, <rect>


;; circle

;; <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
;; <circle cx="50" cy="50" r="50"/>
;; </svg>


;; ellipse
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Element/ellipse

;; Original HTML syntax
;; <svg viewBox="0 0 200 100" xmlns="http://www.w3.org/2000/svg">
;; <ellipse cx="100" cy="50" rx="100" ry="50" />
;; </svg>

;; Clojure syntax

(def eclipse
  [:eclipse {:cx 100
             :cy 50
             :rx 100
             :ry 50
             :fill "green"}])

(def eclipse-viewbox
  [:svg {:viewbox "0 0 200 100"
         :xmlns "http://www.w3.org/2000/svg"}
   [:eclipse {:cx 100
              :cy 50
              :rx 100
              :ry 50
              :fill "green"}]])


;; line

;; The <line> element is an SVG basic shape used to create a line connecting two points.

;; <svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
;; <line x1="0" y1="80" x2="100" y2="20" stroke="black" />

;; <!-- If you do not specify the stroke
;; color the line will not be visible -->
;; </svg>


;; {:line { x1="0" y1="80" x2="100" y2="20" stroke="black"}


;; polygon

;; The <polygon> element defines a closed shape consisting of a set of connected straight line segments. The last point is connected to the first point. For open shapes see the <polyline> element.

;; <svg viewBox="0 0 200 100" xmlns="http://www.w3.org/2000/svg">
;; <!-- Example of a polygon with the default fill -->
;; <polygon points="0,100 50,25 50,75 100,0" />

;; <!-- Example of the same polygon shape with stroke and no fill -->
;; <polygon points="100,100 150,25 150,75 200,0"
;; fill="none" stroke="black" />
;; </svg>







;; polyline
;; The <polyline> SVG element is an SVG basic shape that creates straight lines connecting several points. Typically a polyline is used to create open shapes as the last point doesn't have to be connected to the first point. For closed shapes see the <polygon> element.
;; https://developer.mozilla.org/en-US/docs/Web/SVG/Element/polyline

;; <svg viewBox="0 0 200 100" xmlns="http://www.w3.org/2000/svg">
;; <!-- Example of a polyline with the default fill -->
;; <polyline points="0,100 50,25 50,75 100,0" />

;; <!-- Example of the same polyline shape with stroke and no fill -->
;; <polyline points="100,100 150,25 150,75 200,0"
;; fill="none" stroke="black" />
;; </svg>



;; Commonly created shapes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;; Rectangle


;; <svg viewBox="0 0 220 100" xmlns="http://www.w3.org/2000/svg">
;; <!-- Simple rect element -->
;; <rect x="0" y="0" width="100" height="100" />

;; <!-- Rounded corner rect element -->
;; <rect x="120" y="0" width="100" height="100" rx="15" ry="15" />
;; </svg>


(def rectangle-fixed
  [:rect {:x 0
          :y 0
          :width 100
          :height 100
          :fill "blue"
          :stroke "grey"
          :rx 15
          :ry 15
          }])

(defn rectangle-rounded
  "A rectangle shape with rounded corners
  Refactor: use a map for arguments and provide default values

  https://developer.mozilla.org/en-US/docs/Web/SVG/Element/rect"

  [x y width height fill stroke rounding]
  [:rect {:x x
          :y y
          :width width
          :height height
          :fill fill
          :stroke stroke
          :rx rounding
          :ry rounding
          }])




;; Progress bar (static)







;; SVG Element Demos
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;



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



;; Clojure logo

(def curved-lambda-logo
  [:svg {:style {:border "1px solid"
                 :background "white"
                 :width "150px"
                 :height "150px"}}
   [:circle {:r 50, :cx 75, :cy 75, :fill "green"}]
   [:circle {:r 25, :cx 75, :cy 75, :fill "blue"}]
   [:path {:stroke-width 12
           :stroke "white"
           :fill "none"
           :d "M 30,40 C 100,40 50,110 120,110"}]
   [:path {:stroke-width 12
           :stroke "white"
           :fill "none"
           :d "M 75,75 C 50,90 50,110 35,110"}]])
