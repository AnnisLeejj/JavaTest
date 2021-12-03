package com.annis.test;

public class ThreadTest {
    static ThreadTest test = new ThreadTest();

    public static void main(String[] args) {
        test.test1();
    }

    public void test1() {
        Counter counter = new Counter();
        Thread threadA = new CounterThread("threadA",counter);
        Thread threadB = new CounterThread("threadB",counter);

        threadA.start();
        threadB.start();
    }

    public class Counter {
        long count = 0;

        public synchronized void add(long value) {
            this.count += value;
            System.out.println(Thread.currentThread().getName() + ":" + value + "    当前和:" + count);
        }
    }

    public class CounterThread extends Thread {

        protected Counter counter = null;

        public CounterThread(Counter counter) {
            this(null, counter);
        }

        public CounterThread(String name, Counter counter) {
            super(name);
            this.counter = counter;
        }


        public void run() {

            for (int i = 0; i < 10; i++) {
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.add(i);
            }
        }
    }
    //https://www.vhwke.com/link/KsUMlaxIeiFKLPeO?clash=1&extend=1
}
