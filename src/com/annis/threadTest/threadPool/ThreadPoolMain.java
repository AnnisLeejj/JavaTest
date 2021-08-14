package com.annis.threadTest.threadPool;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadPoolMain {
    public static void main(String[] args) throws Exception {
        ThreadPool threadPool = new ThreadPool(4, 10);
        for (int i = 0; i < 100; i++) {
            int taskNo = i;
            threadPool.execute(() -> {
                String message = Thread.currentThread().getName() + ": Task " + taskNo;
                System.out.println(message);
            });
        }
        threadPool.waitUntilAllTasksFinished();
        threadPool.stop();


    }
}
