(ns u-s.game-test
  (:require [clojure.test :refer :all]
            [u-s.game :refer :all]))

(def row [{:l 0} {:l 0} {:l 1} {:l 2} {:l 0} {:l 5} {:l 0} {:l 10} {:l 2}])

(deftest game
  (testing "score row"
    (is (= (score-row row) 15))))

