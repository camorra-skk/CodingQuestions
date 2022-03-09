package com.codingproblem.codingproblem.miscpackage;

import lombok.AllArgsConstructor;

public class EvenOddThreadMain {

    public static void main(String[] args) {
        Printer p = new Printer();
        Thread t1 = new Thread(new EvenOddThread(10,p,false),"Odd");
        Thread t2 = new Thread(new EvenOddThread(10,p,true),"Even");
        t1.start();
        t2.start();
    }
}


@AllArgsConstructor
class EvenOddThread implements Runnable {

    private int max;
    private Printer print;
    private boolean isEvenNumber;

    @Override
    public void run() {
        int number = isEvenNumber? 2 : 1;
        while (number < max) {
            if(isEvenNumber) {
                print.printEven(number);
            } else {
                print.printOdd(number);
            }
            number+=2;
        }

    }
}

class Printer {
    private volatile boolean isOdd;

    synchronized void printEven(int number) {
        while (!isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = false;
        notify();
    }


    synchronized void printOdd(int number) {
        while (isOdd) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(Thread.currentThread().getName() + ":" + number);
        isOdd = true;
        notify();
    }
}
