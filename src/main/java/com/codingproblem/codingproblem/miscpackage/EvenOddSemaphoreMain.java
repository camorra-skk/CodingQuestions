package com.codingproblem.codingproblem.miscpackage;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

public class EvenOddSemaphoreMain {

    public static void main(String[] args) {
        SharedPrinter sp = new SharedPrinter();
        Thread t1 = new Thread(new Odd(sp,10),"Odd");
        Thread t2 = new Thread(new Even(sp,10),"Even");
        t1.start();
        t2.start();
    }
}

class SharedPrinter {
    private Semaphore evenSemaphore = new Semaphore(0);
    private Semaphore oddSemaphore = new Semaphore(1);


    void printEven(int num) {
        try {
            evenSemaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Thread.currentThread().getName() + num);
        oddSemaphore.release();
    }

    void printOdd(int num) {
        try {
            oddSemaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + num);
        evenSemaphore.release();
    }
}

@AllArgsConstructor
class Even implements Runnable {

    private SharedPrinter sp;
    private int max;

    @Override
    public void run() {
        for(int i=2;i<max;i+=2) {
            sp.printEven(i);
        }
    }
}


@AllArgsConstructor
class Odd implements Runnable {
    private SharedPrinter sp;
    private int max;

    @Override
    public void run() {
        for (int i=1;i<max;i+=2) {
            System.out.println();
            sp.printOdd(i);
        }
    }
}
