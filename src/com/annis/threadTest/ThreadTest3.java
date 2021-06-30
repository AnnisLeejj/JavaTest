package com.annis.threadTest;

import com.annis.threadTest.lock.Lock;

public class ThreadTest3 {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest3 test = new ThreadTest3();
        test.test1();
//        test.test2();

    }

    public void test2() {
        boolean mustWait = true;
        while (mustWait) {
            System.out.println(System.nanoTime());
        }
    }

    public void test1() {
        Counter3 counter = new Counter3();
        for (int i = 0; i < 100; i++) {
            new Thread(counter::addCount).start();
        }
    }
}
