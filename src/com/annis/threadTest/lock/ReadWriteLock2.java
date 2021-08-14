package com.annis.threadTest.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * 读写锁
 * 支持重入
 *
 * ReadWriteLock3 完成版
 */
public class ReadWriteLock2 {
    /**
     * 这个size 也不能是  >1的呀???????
     */
    private Map<Thread, Integer> readingThreads = new HashMap<>();

    private int writeRequests = 0;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    /**
     * 获取读者的 重入次数
     *
     * @param callingThread 如果是 0, 则表示还没有进入过临界区
     * @return
     */
    private int getReadAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) return 0;
        return accessCount;
    }

    private int writeAccesses = 0;
    private Thread writingThread = null;

    public synchronized void lockWrite() throws InterruptedException {
        /**一旦进入就抢占先机
         * {@link #canGrantReadAccess(Thread)} 在没有读线程是优先判断这个条件
         */
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writeAccesses++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() throws InterruptedException {
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    /**
     * 判断是否可以读,遵循 写 优先原则
     */
    private boolean canGrantReadAccess2(Thread callingThread) {
        //  有写, 优先写
        if (hasWriter()) return false;
        //没有写,自己是读者,可以重入
        if (isReader(callingThread)) return true;
        //没有写,自己不是读者,如果有写请求,则优先去写
        if (writeRequests > 0) return false;
        //没有写,自己不是读者, 这进入临界区   ----> 所以是可以有多个(读)线程可以同时进入临界区
        return true;
    }

    /**
     * 判断是否可以读,遵循 写 优先原则
     */
    private boolean canGrantReadAccess(Thread callingThread) {
        //允许写 重入 到 读
        if (isWriter(callingThread)) return true;
        if (hasWriter()) return false;
        if (isReader(callingThread)) return true;
        if (writeRequests > 0) return false;
        return true;
    }

    /**
     * 判断是否可写
     */
    private boolean canGrantWriteAccess2(Thread callingThread) {
        //有读就等待
        if (hasReaders()) return false;
        //没有读,也没有写 ,就进入 临界区
        if (!hasWriter()) return true;
        //没有读,有写,判断 不是自己  ->等待
        if (!isWriter(callingThread)) return false;
        //没有读,有写,判断 是自己  -> 进入
        return true;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        //重入 -> 判断这个 写线程 是不是唯一的 读线程
        if (isOnlyReader(callingThread)) return true;
        if (hasReaders()) return false;
        if (!hasWriter()) return true;
        if (!isWriter(callingThread)) return false;
        return true;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean hasWriter() {
        return writingThread != null;
    }

    /**
     * 判断当前线程是不是 正在读
     */
    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }
}