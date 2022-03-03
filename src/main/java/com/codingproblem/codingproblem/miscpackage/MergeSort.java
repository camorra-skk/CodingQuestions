package com.codingproblem.codingproblem.miscpackage;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {4,1,3,2,0,-1,7,10,9,20};

        mergeSort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));

    }

    private static void mergeSort(int[] arr,int start, int end) {
        if(start < end) {
            int mid = start + (end -start)/2;
            mergeSort(arr,start,mid);
            mergeSort(arr,mid+1,end);
            merge(arr,start,mid,end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end-start + 1];

        int i = start,j=mid+1,k=0;

        while (i <= mid && j<=end) {
            if(arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }else  {
                temp[k++] = arr[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = arr[i++];
        }
        while(j<=end) {
            temp[k++] = arr[j++];
        }

        for(int p = 0;p<k;p++){
            arr[p+start] = temp[p];
        }
    }
}
