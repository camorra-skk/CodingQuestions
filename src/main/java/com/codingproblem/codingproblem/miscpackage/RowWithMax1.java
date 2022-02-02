package com.codingproblem.codingproblem.miscpackage;

public class RowWithMax1 {

    public static void main(String[] args) {

        int mat[][] = { {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1}};

        System.out.print("Index of row with maximum 1s is " + rowWithMax1s(mat));

    }

    private static int rowWithMax1s(int[][] mat) {

        for (int i = 0; i < mat.length; i++) {
            if(mat[i][0] == 1) return i;
        }

        int maxRowCount = 0;
        for (int[] ints : mat) {
            if (ints[ints.length - 1] != 0) {
                maxRowCount = Math.max(ints.length - binarySearch(ints),maxRowCount);
                System.out.println("maxRowCount : "+maxRowCount);
            }
        }
        return maxRowCount;
    }

    private static int binarySearch(int[] arr) {
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int mid = low + (high-low)/2;

            if(arr[mid] == 0) low = mid +1;
            else if(mid > 0 && arr[mid] == 1 && arr[mid-1] == 0)  {
                System.out.println(mid);
                return mid;
            }
            else high = mid -1;
        }

        return -1;
    }
}
