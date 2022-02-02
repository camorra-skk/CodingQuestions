package com.codingproblem.codingproblem.Leetcode;

/* Problem Statement :
        https://leetcode.com/problems/ransom-note/
 */

import java.util.HashMap;
import java.util.Map;

public class Leetcode_383 {
    public static void main(String[] args) {

        System.out.println(canConstruct("aa","aab"));

    }

    public static boolean canConstruct(String ransomNote, String magazine) {

        Map<Character,Integer> hm = new HashMap<>();

        for(int i=0;i<magazine.length();i++) {
            Character ch = magazine.charAt(i);
            hm.put(ch,hm.getOrDefault(ch,0)+1);
        }

        for(int i=0;i<ransomNote.length();i++) {
            Character ch = ransomNote.charAt(i);
            if(!hm.containsKey(ch)) return false;
            else {
                int res = hm.get(ch);
                if(res == 0) return false;
                hm.put(ch,--res);
            }
        }
        return true;
    }
}
