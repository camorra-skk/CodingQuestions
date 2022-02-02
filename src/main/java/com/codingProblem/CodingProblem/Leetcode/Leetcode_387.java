package com.codingProblem.CodingProblem.Leetcode;

/* Problem Statement :
        https://leetcode.com/problems/first-unique-character-in-a-string/
 */


import java.util.HashMap;
import java.util.Map;

public class Leetcode_387 {

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar(s));
    }

    public static int firstUniqChar(String s) {
        Map<Character,Integer> hmMap = new HashMap<>();

        for(int i=0;i<s.length();i++) {

            Character ch = s.charAt(i);

            hmMap.put(ch,hmMap.getOrDefault(ch, 0)+1);
        }


        for(int i=0;i<s.length();i++) {

            Character ch = s.charAt(i);

            if(hmMap.get(ch) == 1) {
                return i;
            }

        }
        return -1;
    }
}


