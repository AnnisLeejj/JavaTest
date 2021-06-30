package com.annis.threadTest.lock.lockTest;

public class LockTest {
    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 200; i++) {
            new Thread(counter::inc).start();
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结果:" + counter.getCount());
    }
}