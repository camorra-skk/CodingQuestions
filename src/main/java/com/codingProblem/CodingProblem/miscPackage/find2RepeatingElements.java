package com.codingProblem.CodingProblem.miscPackage;



//You are given an array of n+2 elements. All elements of the array are in range 1 to n.
//And all elements occur once except two numbers which occur twice. Find the two repeating numbers.
//For example, array = {4, 2, 4, 5, 2, 3, 1} and n = 5
//
//The above array has n + 2 = 7 elements with all elements occurring once except 2 and 4 which occur twice.
//So the output should be 4 2.

import java.util.HashSet;

public class find2RepeatingElements {
    public static void main(String[] args) {

        int a[] = {1, 2, 3, 1, 3, 6, 6};

        HashSet<Integer> hs = new HashSet<>();

        for (int i = 0; i < a.length; i++) {
            boolean k = hs.add(a[i]);
            if(!k) {
                System.out.println(a[i]);
            }
        }
    }
}
