package com.codingproblem.codingproblem.miscpackage;

public class DecimalToBinary {

    public static void main(String[] args) {
        String str = "";
        System.out.println(convertBinarytoDecimal(8,str));
//        System.out.println(str);
    }

    private static String convertBinarytoDecimal(int i, String str) {

        if(i == 1) {
            str = 1 + str;

            return str;
        }

        int rem = i%2;
        str = rem + str;
//        System.out.println("Tst" + str);
        return convertBinarytoDecimal(i/2,str);

    }
}
