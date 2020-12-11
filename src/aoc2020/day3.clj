(ns aoc2020.day3
  (:require [clojure.string :as str])
  (:gen-class))

(def input
  (-> "src/aoc2020/input3.txt"
      slurp
      (str/split #"\n")))

(defn lookup [[x y]]
  (let [w      (count (first input))
        y-wrap (mod y w)]
    (-> input (nth x) (nth y-wrap))))

(defn next-coord-fn [rise run]
  (fn [[x y]] [(+ x rise) (+ y run)]))

(defn count-trees [step-fn]
  (let [h       (count input)
        coords  (take-while #(< (first %) h) (iterate step-fn [0 0]))
        squares (map lookup coords)]
    (count (filter #(= \# %) squares))))

(defn part1 [] (count-trees (next-coord-fn 1 3)))

(defn part2 []
  (apply * [(count-trees (next-coord-fn 1 1))
            (count-trees (next-coord-fn 1 3))
            (count-trees (next-coord-fn 1 5))
            (count-trees (next-coord-fn 1 7))
            (count-trees (next-coord-fn 2 1))
            ]))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
