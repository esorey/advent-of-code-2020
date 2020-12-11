(ns aoc2020.day4
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]
            [clojure.walk :as walk])
  (:gen-class))

(def input
  (-> "src/aoc2020/input4.txt"
      slurp
      (str/split #"\n\n")))

(defn parse-passport [data]
  (let [line (str/split data #"\n| ")]
    (->>  line
          (map #(str/split % #":"))
          (into {})
          walk/keywordize-keys)))

(defn parse-int [v] 
  (try 
    (Integer/parseInt v) 
    (catch Exception e -1)))

(s/def ::passport (s/keys :req-un [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid]))

(s/def ::byr (s/and #(re-matches #"[0-9]{4}" %)
                    #(>= (parse-int %) 1920)
                    #(<= (parse-int %) 2002)))

(s/def ::iyr (s/and #(re-matches #"[0-9]{4}" %)
                    #(>= (parse-int %) 2010)
                    #(<= (parse-int %) 2020)))

(s/def ::eyr (s/and #(re-matches #"[0-9]{4}" %)
                    #(>= (parse-int %) 2020)
                    #(<= (parse-int %) 2030)))

(s/def ::hgt-cm (s/and #(re-matches #"[0-9]+cm" %)
                       #(>= (parse-int (subs % 0 (- (count %) 2))) 150)
                       #(<= (parse-int (subs % 0 (- (count %) 2))) 193)))

(s/def ::hgt-in (s/and #(re-matches #"[0-9]+in" %)
                       #(>= (parse-int (subs % 0 (- (count %) 2))) 59)
                       #(<= (parse-int (subs % 0 (- (count %) 2))) 76)))

(s/def ::hgt (s/or :cm ::hgt-cm :in ::hgt-in))

(s/def ::hcl #(re-matches #"#[0-9a-f]{6}" %))

(s/def ::ecl #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"})

(s/def ::pid #(re-matches #"[0-9]{9}" %))

(defn has-required-keys? [passport]
  (let [required-keys [:byr :iyr :eyr :hgt :hcl :ecl :pid]
        passport-keys (set (keys passport))]
    (every? #(contains? passport-keys %) required-keys)))

(defn part1 []
  (->> input
       (map parse-passport)
       (filter has-required-keys?)
       count))

(defn part2 []
  (->> input
       (map parse-passport)
       (filter #(s/valid? ::passport %))
       count))

(defn -main [& args]
  (do (println "Part 1: " (part1))
      (println "Part 2: " (part2))))
