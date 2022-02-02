package com.codingproblem.codingproblem.miscpackage;

public class StringPalindrome {

    public static void main(String[] args) {

        System.out.println(isStringPalindrome("Sumit"));
    }

    static boolean isStringPalindrome(String input) {
       if(input.length() == 0 || input.length() == 1) return true;

       if(input.charAt(0) == input.charAt(input.length()-1)) {
           return isStringPalindrome(input.substring(1,input.length()-1));
       }
       return false;
    }
}
