package com.annis.threadTest.lock.lockTest;

import com.annis.threadTest.lock.Lock;

public class Counter {
    Lock lock = new Lock();
    private int count = 0;

    public int getCount() {
        return count;
    }

    public int inc() {

        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int newCount = ++count;
        lock.unlock();
        return newCount;
    }
}