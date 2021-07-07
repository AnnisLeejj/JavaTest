package com.annis.threadTest;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {
    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
//        test.test1();
        test.test2();

    }


    public void test2() {
        AtomicInteger integer = new AtomicInteger();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                //这段代码try代码放开会导致结果出错
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                boolean flag = false;
                while (!flag) {
                    int i1 = integer.get();
                    flag = integer.compareAndSet(i1, i1 + 1);
                }
                System.out.println("当前值:" + integer.get());
            }).start();
        }
    }

    volatile int count = 0;
    public void test1() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                //这段代码try代码放开会导致结果出错
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                }
                int temp = count;
                temp++;
                count = temp;
                System.out.println("当前值:" + count);
            }).start();
        }
    }
}
