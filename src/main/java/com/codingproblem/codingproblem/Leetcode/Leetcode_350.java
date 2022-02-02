package com.codingproblem.codingproblem.Leetcode;

/* Problem Statement :
        https://leetcode.com/problems/intersection-of-two-arrays-ii/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode_350 {
    public static void main(String[] args) {

        System.out.println(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2}));

    }

    public static int[] intersect(int[] nums1, int[] nums2) {


//         Arrays.sort(nums1);
//         Arrays.sort(nums2);
//         int m = nums1.length-1;
//         int n = nums2.length-1;

//         List<Integer> list = new ArrayList<>();


//         while(m>= 0 && n>= 0) {
//             if(nums1[m] > nums2[n]) m--;
//             else if(nums1[m] < nums2[n]) n--;
//             else {
//                 list.add(nums1[m]);
//                 m--;
//                 n--;
//             }
//         }

//         return list.stream().mapToInt(i->i).toArray();


        Map<Integer,Integer> hm = new HashMap<>();

        int m = nums1.length;
        int n = nums2.length;

        List<Integer> list = new ArrayList<>();

        for (int k : nums1) {
            hm.put(k, hm.getOrDefault(k, 0) + 1);
        }

        for (int j : nums2) {

            if (hm.containsKey(j) && hm.get(j) > 0) {
                list.add(j);
                int newCount = hm.get(j) - 1;
                hm.put(j, newCount);
            }
        }

        return list.stream().mapToInt(i->i).toArray();
    }
}
