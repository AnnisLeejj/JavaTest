package com.annis.threadTest;

import com.annis.threadTest.lock.Lock;

public class Counter3 {
    Lock lock = new Lock();
    int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
        System.out.println(Thread.currentThread().getName() + "  unlock and count:" + count);
        lock.unlock();
    }
}
