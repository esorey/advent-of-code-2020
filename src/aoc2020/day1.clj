(ns aoc2020.day1
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo])
  (:gen-class))

(def input
  (-> "src/aoc2020/input1.txt"
      slurp
      (str/split #"\n")
      (as-> lines (map #(Integer/parseInt %) lines))
      set))

(defn complement [n] (- 2020 n))

(defn part1 [] 
  (let [comps (set (map complement input))
        match (some input comps)]
    (* match (complement match))))

(defn part2 []
  (let [comps         (set (map complement input))
        pairs         (combo/combinations input 2)
        pair-sums     (map #(reduce + %) pairs)
        sums-to-pairs (zipmap pair-sums pairs)
        sum-match     (some comps pair-sums)
        pair-match    (sums-to-pairs sum-match)
        triplet       (conj pair-match (complement sum-match))]
    (apply * triplet)))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
