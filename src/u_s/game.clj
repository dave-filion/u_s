(ns u-s.game (:gen-class))

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

(defn make-tile [letter player]
  { :letter letter
    :player player})

(defn make-space [tile]
  {:tile nil})

(defn empty-board [max-x max-y]
  (vec (repeat max-y
               (vec (repeat max-x (make-space nil))))))

(defn place-tile [letter player x y board]
  (let [row (nth board y)
        space (nth row x)]
    (assoc board y 
           (assoc row x
                  (assoc space :tile
                         (make-tile letter player))))))

;; Play word

(def letters [{:x 1 :y 1 :letter "A"} {:x 2 :y 1 :letter "T"}])

(defn all-same [coll]
  (= (count (distinct coll)) 1))

