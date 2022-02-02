package com.codingProblem.CodingProblem.miscPackage;


//Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
//The digits are stored such that the most significant digit is at the head of the list,
//and each element in the array contain a single digit.
//You may assume the integer does not contain any leading zero, except the number 0 itself.


import java.util.Arrays;

public class PlusOne {

    public static void main(String[] args) {

        int arr[] = {8, 9, 9, 9};
        int num = 0;
        int flag = 0;

        for (int i = arr.length-1; i >=0 ; i--) {
            if(arr[i] ==9) {
                arr[i] = 0;
                num = 1;
                if(i==0) {
                    flag = 1;
                }
            }
            else {
                if(i == arr.length-1) {
                    arr[i] +=1;
                }
                arr[i]+=num;
                break;
            }

        }

        if(flag ==1) {
            System.out.println(1);
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
