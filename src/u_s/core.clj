(ns u-s.core
  (:use [compojure.route :only [files not-found]]
        [compojure.handler :only [site]]
        [compojure.core :only [defroutes GET POST DELETE ANY context]]
        u-s.game
         org.httpkit.server)
  (:require [taoensso.carmine :as car :refer (wcar)]
            [clojure.data.json :as json])
  (:gen-class))

(def server1-conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(defn response [data]
  (let [data (json/read-str data)]
    (str (data "id"))))

(defn ws [req]
  (with-channel req channel
    (on-close channel (fn [status] (println "channel closed: " status)))
    (on-receive channel (fn [data]
                          (send! channel (response data))))))

(defroutes all-routes
  (GET "/" [] "HI")
  (GET "/ws" [] ws))

(defn -main
  [& args]
  (println "Starting server...")
  (run-server (site #'all-routes) {:port 8080}))

(print-map {})
