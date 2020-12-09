(ns aoc2020.day1
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo])
  (:gen-class))

(def input
  (-> "src/aoc2020/input1.txt"
      slurp
      (str/split #"\n")
      (as-> x (map #(Integer/parseInt %) x))
      set))

(defn part1 [] 
  (as-> input x
    (filter #(contains? x (- 2020 %)) x)
    (first x)
    (* x (- 2020 x))
    (println "Part 1: " x)))

(defn part2 []
  (let [sums-to-pairs (as-> input x
                        (combo/combinations x 2)
                        (zipmap (map #(reduce + %) x) x))]
    (as-> sums-to-pairs x
      (filter #(contains? x (- 2020 %)) input)
      (first x)
      (conj (sums-to-pairs (- 2020 x)) x)
      (reduce * x)
      (println "Part 2: " x))))

(defn -main [& args]
  (do
    (part1)
    (part2)))
