package com.annis.threadTest.lock;

/**
 * 可以重入的锁
 */
public class Lock2 {
    boolean isLocked = false;
    Thread lockedBy = null;
    /**
     * 对lock计数
     */
    int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = callingThread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}