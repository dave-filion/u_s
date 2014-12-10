(ns u-s.core
  (:require [taoensso.carmine :as car :refer (wcar)])
  (:gen-class))

(def server1-conn {:pool {} :spec {:host "127.0.0.1" :port 6379}})
(defmacro wcar* [& body] `(car/wcar server1-conn ~@body))

(def raw-bag
  {"A" 3
   "B" 4
   "C" 3
   "D" 5
   "E" 6})

(def raw-map
  [[1 0 0  0 1]
   [0 1 0  1 0]
   [0 0 9  0 0]
   [0 1 0  1 0]
   [1 0 0  0 1]])

(def points
  {:A 1})

(defn print-row [row]
  (reduce #(str %1 " " %2) row))

(defn print-map [raw-map]
  (reduce #(str %1 (print-row %2) "\n") "" raw-map))

(defn expand-bag [bag]
  (flatten (reduce-kv #(conj %1 ( take %3 (repeat %2))) [] bag)))

(defn pick-tile [bag]
  "Draw random tile from expanded bag, first in vector is picked tile, second is new bag"
  (let [bag (shuffle bag)]
    [(first bag) (rest bag)]))

(defn -main
  [& args])

