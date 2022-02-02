package com.codingproblem.codingproblem.miscpackage;

public class RecursiveBinarySearch {

    public static void main(String[] args) {
        int[] a = {-1,0,1,2,3,4,7,9,10,20};
        System.out.println(binarySeach(a,0,a.length-1,20));
    }

    private static int binarySeach(int[] arr, int left, int right, int target) {
        if(left > right) return -1;

        int mid = left + (right-left)/2;

        if(arr[mid] == target) return mid;
        else if(arr[mid] > target) return binarySeach(arr,left,mid-1,target);
        else return binarySeach(arr,mid+1,right,target);
    }
}
