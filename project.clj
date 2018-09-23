(defproject status-monitor "0.1.0-SNAPSHOT"
  :description "A server side website dashboard to collate monitoring information"
  :url "https://github.com/jr0cket/status-monitor"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [compojure "1.6.1"]
                 [ring/ring-defaults "0.3.2"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.12.4"]
            [lein-eftest "0.5.3"]]
  :ring {:handler status-monitor.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}})
