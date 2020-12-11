(ns aoc2020.day6
  (:require [clojure.string :as str]
            [clojure.set :as set])
  (:gen-class))

(def input
  (-> "src/aoc2020/input6.txt"
      slurp
      (str/split #"\n\n")))

(defn part1 []
  (->> input
       (map (comp count distinct #(str/replace % "\n" "")))
       (apply +)))

(defn part2 []
  (->> input
       (map (comp #(map set %) #(map seq %) str/split-lines))
       (map #(apply set/intersection %))
       (map count)
       (apply +)))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
