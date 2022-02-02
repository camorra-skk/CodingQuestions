package com.codingproblem.codingproblem.Leetcode;

/* Problem Statement :
        https://leetcode.com/problems/valid-anagram/
 */

import java.util.HashMap;
import java.util.Map;

public class Leetcode_242 {
    public static void main(String[] args) {

        System.out.println(isAnagram("anagram","nagaram"));

    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (Character ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (Character ch : t.toCharArray()) {
            if (!map.containsKey(ch) || map.get(ch) == 0) {
                return false;
            }
            map.put(ch, map.get(ch) - 1);
        }

        return true;
    }
}
