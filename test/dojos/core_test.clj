(ns dojos.core-test
  (:require [clojure.test :refer :all]
            [dojos.core :refer :all]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m]))


(defn anagrams [word]
  (let [reversed (apply str (reverse word))]
    [reversed word]))

(defn is-anagram? [first second]
  (= (frequencies first)
     (frequencies second)))

(deftest is-anagram?-test
  (testing "return true when is an anagram"
    (is (true? (is-anagram? "limbo" "mobil")))
    (is (true? (is-anagram? "limbo" "bolim")))
    (is (false? (is-anagram? "limbo" "limbu")))
    (is (false? (is-anagram? "limbo" "limbou")))))

(deftest anagram-test
  (testing "inverting anagram"
    (is (match? (m/embeds ["obmil"])
                (anagrams "limbo"))))

  (testing "includes itself"
    (is (match? (m/embeds ["limbo"])
                (anagrams "limbo"))))

  (testing "rotate letters"
    (is (match? (m/embeds ["olimb" "bolim" "mboli" "imbol"])
                (anagrams "limbo")))))
