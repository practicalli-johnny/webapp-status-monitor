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

;; CIDER logo
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; TODO: Complete converting CIDER logo to Clojure SVG syntax

<svg version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px"
   viewBox="0 0 243.735 253.24" style="enable-background:new 0 0 243.735 253.24;" xml:space="preserve">
<path style="&st0;" d="M205.455,225.406c-1.54,0.66-2.641,1.101-4.841,1.101c-5.939,0-10.779-4.84-10.779-10.78
  c0-3.52,1.979-7.92,6.38-9.9c22.44-10.12,23.32-36.74,23.32-76.56c0-39.82-0.88-66.44-23.32-76.561c-4.4-1.98-6.38-6.381-6.38-9.9
  c0-5.94,4.84-10.78,10.779-10.78c2.2,0,3.301,0.44,4.841,1.101c28.6,11.88,38.28,38.72,38.28,96.141
  C243.735,186.686,234.054,213.526,205.455,225.406z"/>
<rect x="42.418" y="37.942" style="&st1;" width="93" height="178"/>
<g>
  <path style="&st0;" d="M32.78,253.24h-20.68C5.06,253.24,0,248.401,0,241.8V11.44C0,5.06,5.06,0,12.101,0h20.68
    c5.72,0,10.34,4.62,10.34,10.34c0,5.72-4.62,10.34-10.34,10.34H23.76v211.88h9.021c5.72,0,10.34,4.621,10.34,10.341
    S38.5,253.24,32.78,253.24z"/>
  <path style="&st0;" d="M146.058,232.56h9.02V20.68h-9.02c-5.721,0-10.341-4.62-10.341-10.34c0-5.72,4.62-10.34,10.341-10.34h20.68
    c7.04,0,12.1,5.06,12.1,11.44V241.8c0,6.601-5.06,11.44-12.1,11.44h-20.68c-5.721,0-10.341-4.62-10.341-10.34
    S140.337,232.56,146.058,232.56z"/>
</g>
</svg>

(def cider-logo
  [:svg {:style {:border "1px solid"
                 :background "white"
                 :width "250px"
                 :height "260px"}
         :viewbox "0 0 243.735 253.24"}

   [:g
    [:path {:stroke-width 12
            :stroke "Black"
            :fill "none"
            :d "M205.455,225.406c-1.54,0.66-2.641,1.101-4.841,1.101c-5.939,0-10.779-4.84-10.779-10.78
  c0-3.52,1.979-7.92,6.38-9.9c22.44-10.12,23.32-36.74,23.32-76.56c0-39.82-0.88-66.44-23.32-76.561c-4.4-1.98-6.38-6.381-6.38-9.9
  c0-5.94,4.84-10.78,10.779-10.78c2.2,0,3.301,0.44,4.841,1.101c28.6,11.88,38.28,38.72,38.28,96.141
  C243.735,186.686,234.054,213.526,205.455,225.406z"}]

    [:rect {:x 42
            :y 38
            :width 90
            :height 180
            :fill "yellow"
            :stroke "grey"
            :rx 5
            :ry 5
            }]

    [:path {:stroke-width 12
            :stroke "Orange"
            :fill "Orange"
            :d "M146.058,232.56h9.02V20.68h-9.02c-5.721,0-10.341-4.62-10.341-10.34c0-5.72,4.62-10.34,10.341-10.34h20.68
    c7.04,0,12.1,5.06,12.1,11.44V241.8c0,6.601-5.06,11.44-12.1,11.44h-20.68c-5.721,0-10.341-4.62-10.341-10.34
    S140.337,232.56,146.058,232.56z"}]]] )
