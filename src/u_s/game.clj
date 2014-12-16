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

(defn all-same [coll]
  (= (count (distinct coll)) 1))

(assert (all-same [1 1 1]))
(assert (not (all-same [1 2 3])))

(defn not-zero? [num]
  (not (zero? num)))

(defn score-cell [tiles previous-score total-score]
  (let [[cur & rest] tiles]
    (if (nil? cur)
      total-score
      (let [score (:l cur)]
        (if (and (not-zero? previous-score) (not-zero? score))
          (let [new-score (+ previous-score score)]
            (recur rest new-score (+ total-score new-score)))
          (if (nil? score)
            (recur rest 0 total-score)
            (recur rest score total-score)))))))

(defn score-row [row]
  (score-cell row 0 0))

