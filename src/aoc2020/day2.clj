(ns aoc2020.day2
  (:require [clojure.string :as str])
  (:gen-class))

(def input
  (-> "src/aoc2020/input2.txt"
      slurp
      (str/split #"\n")))

(defn parse-line [line]
  (let [[limits char string] (str/split line #" ")
        [min max]            (map #(Integer/parseInt %) (str/split limits #"-"))]
    {:min    min
     :max    max
     :char   (first char)
     :string string}))

(defn valid-password? [{:keys [min max char string]}]
  (let [char-count (-> string frequencies (get char 0))]
    (and (>= char-count min)
         (<= char-count max))))

(defn valid-password2? [{:keys [min max char string]}]
  (let [chars       [(get string (- min 1)) (get string (- max 1))]
        match-count (count (filter #(= char %) chars))]
    (= 1 match-count)))

(defn part1 [] 
  (->> input
       (map parse-line)
       (filter valid-password?)
       count))

(defn part2 [] 
  (->> input
       (map parse-line)
       (filter valid-password2?)
       count))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
