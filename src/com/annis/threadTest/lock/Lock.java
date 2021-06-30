package com.annis.threadTest.lock;

/**
 * 简单的 Lock 实现
 */
public class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
         while (isLocked) {
             wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}