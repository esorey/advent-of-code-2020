(defproject aoc2020 "0.1.0-SNAPSHOT"
  :description "Advent of Code 2020 in Clojure"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/math.combinatorics "0.1.6"]
                 ]
  :main nil
  :target-path "target/%s"
  :profiles {:core {:main aoc2020.core}
             :day1 {:main aoc2020.day1}
             :day2 {:main aoc2020.day2}
             :day3 {:main aoc2020.day3}
             :day4 {:main aoc2020.day4}
             :uberjar {:aot :all}})
