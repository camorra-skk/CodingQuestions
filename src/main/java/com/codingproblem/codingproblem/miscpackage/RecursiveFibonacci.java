package com.codingproblem.codingproblem.miscpackage;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(5));
    }

    private static int fibonacci(int i) {
        if(i == 0 || i == 1) return i;

        return fibonacci(i-1) + fibonacci(i-2);
    }
}
