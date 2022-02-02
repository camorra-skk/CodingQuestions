package com.codingproblem.codingproblem.miscpackage;

public class SumNaturalNumbers {

    public static void main(String[] args) {

        System.out.println(sumNaturalNumber(10));
    }

    private static int sumNaturalNumber(int i) {
        if(i<=1) {
            return i;
        }
        return sumNaturalNumber(i-1)+i;
    }

}
