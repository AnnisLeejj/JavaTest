package com.annis.threadTest;

public class ThreadTest2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest2 test = new ThreadTest2();
        test.test1();

    }

    public void test1() {
        Counter counter = new Counter();

        Thread thread1 = new Thread(() -> {
            synchronized (counter) {
                counter.setCount(1);
                try {
                    counter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结果:" + counter.getCount());
            }
        }, "thread1");
        thread1.start();
        Thread thread3 = new Thread(() -> {
            synchronized (counter) {
                counter.setCount(3);
                try {
                    counter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "结果:" + counter.getCount());
            }
        }, "thread3");
        thread3.start();

        Thread thread2 = new Thread(() -> {
            synchronized (counter) {
                counter.setCount(counter.getCount() + 1);
                counter.notify();
            }
        });
        thread2.start();
    }

    class Counter {
        int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
