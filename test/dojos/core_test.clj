(ns dojos.core-test
  (:require [clojure.test :refer :all]
            [dojos.core :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m]))


(defn anagrams [word])

(deftest anagram-test
  (testing "inveting anagram"
    (is (match? (m/embeds ["obmil"])
                (anagrams "limbo")) )))
