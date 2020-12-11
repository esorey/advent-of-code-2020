(ns aoc2020.day5
  (:require [clojure.string :as str]
            [clojure.math.numeric-tower :as math])
  (:gen-class))

(def input
  (-> "src/aoc2020/input5.txt"
      slurp
      str/split-lines
      ))

(defn step [[lo hi] char]
  (let [shift (math/ceil (/ (- hi lo) 2))]
    (case char
      \F [lo (- hi shift)]
      \B [(+ lo shift) hi]
      \L [lo (- hi shift)]
      \R [(+ lo shift) hi]
      :error)))

(defn compute-seat-id [seat-code]
  (let [row  (first (reduce step [0 127] (subs seat-code 0 7)))
        seat (first (reduce step [0 8] (subs seat-code 7 10)))]
    (+ (* row 8) seat)))

(defn part1 [] (int (apply max (map compute-seat-id input))))

(defn part2 []
  (let [seat-ids (sort (map compute-seat-id input))
        diffs    (map - (rest seat-ids) seat-ids)
        idx (.indexOf diffs 2N)]
    (int (+ (nth seat-ids idx) 1))))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
