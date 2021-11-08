(ns dojos.core-test
  (:require [clojure.test :refer :all]
            [dojos.anagram :as anagram]
            [matcher-combinators.test :refer [match?]]
            [matcher-combinators.matchers :as m]))

;; https://codingdojo.org/kata/Anagram/

(defn anagrams [word]
  (let [reversed (apply str (reverse word))]
    [reversed word]))

(deftest two-words-of-same-size-as-test
  (testing "Return two word tuples from the given dictionary with the combined size as the given word"
    (is (match? #{#{"d" "ccc"}
                  #{"ee" "ff"}}
                (anagram/two-words-of-same-size-as "aaaa"
                                                   #{"bbbbb" "ccc" "d" "ee" "ff"})))))

(deftest two-words-that-together-make-an-anagram
  (testing "Return two word tuples from the given dictionary with the combined are a anagram of the first"
    (is (match? #{#{"aaa" "a"}}
                (anagram/only-anagramic "aaaa"
                                        #{#{"aaa" "a"} #{"b" "acr"}})))))

(deftest is-anagram?-test
  (testing "return true when is an anagram"
    (is (true? (anagram/is-anagram? "limbo" "mobil")))
    (is (true? (anagram/is-anagram? "limbo" "bolim")))
    (is (false? (anagram/is-anagram? "limbo" "limbu")))
    (is (false? (anagram/is-anagram? "limbo" "limbou")))))

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
