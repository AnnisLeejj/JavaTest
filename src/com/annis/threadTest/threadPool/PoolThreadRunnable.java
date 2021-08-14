package com.annis.threadTest.threadPool;

import java.util.concurrent.BlockingQueue;

public class PoolThreadRunnable implements Runnable {
    private BlockingQueue taskQueue = null;
    private boolean isStopped = false;

    public PoolThreadRunnable(BlockingQueue queue) {
        taskQueue = queue;
    }

    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch (Exception e) {
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop() {
        isStopped = true;
        //break pool thread out of dequeue() call.
        Thread.currentThread().interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }
}