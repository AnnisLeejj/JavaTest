package com.annis.threadTest;

import java.util.function.Supplier;

public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
        ThreadTest1 test = new ThreadTest1();
//        test1();
//        test2();
//        test3();
//        test.test4();
//        test.test5();
//        test.test6();
//        test.test7();
        test.InheritableThreadLocalTest();
    }

    private void InheritableThreadLocalTest() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

        Thread thread1 = new Thread(() -> {
            System.out.println("===== Thread 1 =====");
            threadLocal.set("Thread 1 - ThreadLocal");
            inheritableThreadLocal.set("Thread 1 - InheritableThreadLocal");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("===== ChildThread =====");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());

                Thread childThread2 = new Thread(() -> {
                    System.out.println("===== ChildThread's ChildThread =====");
                    System.out.println(threadLocal.get());
                    System.out.println(inheritableThreadLocal.get());
                });
                childThread2.start();
            });
            childThread.start();
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("===== Thread2 =====");
            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());
        });
        thread1.start();
        thread2.start();
    }

    private void test7() throws InterruptedException {
        MyRunnable2 sharedRunnableInstance = new MyRunnable2();

        Thread thread1 = new Thread(sharedRunnableInstance);
        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();
        thread2.start();

        System.out.println(System.currentTimeMillis());
        thread1.join(); //wait for thread 1 to terminate
        thread2.join(); //wait for thread 2 to terminate
        System.out.println(System.currentTimeMillis());
    }

    public class MyRunnable2 implements Runnable {

        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

        @Override
        public void run() {

            int integer = (int) (Math.random() * 100D);
            System.out.println(Thread.currentThread().getName() + "  生成:" + integer);
            threadLocal.set(integer);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread().getName() + "  获取:" + threadLocal.get());
        }
    }

    private void ThreadLocalTest() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = ThreadLocal.withInitial(() -> "fork value");

        ThreadLocal<String> threadLocal1 = ThreadLocal.withInitial(() -> "fork value");

        threadLocal.set("");
        threadLocal.get();
        threadLocal.remove();


        Thread thread = new Thread("run a runnable");
    }

    private void test6() {
        MultiSynchronized multiSynchronized = new MultiSynchronized();

        multiSynchronized.add(11);
    }

    class MultiSynchronized {
        volatile int total = 0;

        private void add(int item) {
            synchronized (this) {
                total += getCount2(item);
                System.out.println(item + "   结果:" + total);
            }
        }

        private int getCount2(int item) {
            int result = 0;
            synchronized (this) {
                result = item % 10;
            }
            return result;
        }

        private synchronized int getCount(int item) {
            return item % 10;
        }
    }

    private void test5() {
        total = 0;
        for (int i = 0; i < 101; i++) {
            new Thread(new NumberRunnable(i)).start();
        }
    }

    int total = 0;
    Object lock = new Object();

    private void add(int item) {
        synchronized (lock) {
            total += item;
            System.out.println(item + "   结果:" + total);
        }
//        total += item;
//        System.out.println(item + "   结果:" + total);
    }

    public class NumberRunnable implements Runnable {
        int number;

        NumberRunnable(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            add(number);
        }
    }

    public class MyRunnable implements Runnable {

        private boolean doStop = false;

        public synchronized void doStop() {
            this.doStop = true;
        }

        private synchronized boolean keepRunning() {
            System.out.println("keepRunning():" + Thread.currentThread().getName());
            return !this.doStop;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                // keep doing what this thread should do.
                System.out.println("Running:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3L * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void test4() {
//        MyRunnable runnable = new MyRunnable();
//        Thread thread = new Thread(runnable,"run thread");
//        thread.start();

        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(myRunnable, "run thread");

        thread.start();

        try {
            Thread.sleep(10L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRunnable.doStop();
    }

    private static void test3() {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread:" + getName() + " running");
                }
            }.start();
        }
    }

    private static void test2() {
        Runnable runnable = () -> System.out.println("runnable run:" + Thread.currentThread().getName());
        Thread thread = new Thread(runnable, "run a runnable");
        thread.run();//runnable run:main
        thread.start();//runnable run:run a runnable
    }

    private static void test1() {
        // Create two Thread objects
        Thread t1 = new Thread(ThreadTest1::print);
        Thread t2 = new Thread(ThreadTest1::print);

        // Start both threads
        t1.start();
        t2.start();
    }

    public static void print() {
        for (int i = 1; i <= 500; i++) {
            System.out.println(i);
        }
    }
}
