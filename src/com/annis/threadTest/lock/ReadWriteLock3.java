package com.annis.threadTest.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * 支持 读写相互可以 重入的锁设计
 * <p>
 * 规范使用 读/写 同理:
 * try{
 *      {@link ReadWriteLock3#lockRead()}
 *      ...your codes
 * }finally{
 *      //在finally中保证一定能释放锁
 *      {@link ReadWriteLock3#unlockRead()}
 * }
 */
public class ReadWriteLock3 {
    /**
     * 读线程的记录,以及每个读线程的重入次数
     * 这里的读线程可能存在一个(且最多一个)写线程
     */
    private Map<Thread, Integer> readingThreads = new HashMap<>();
    /**
     * 写线程的重入次数
     * 这个次数,在某段时间内由一个线程产生
     */
    private int writeAccesses = 0;
    /**
     * 这个请求写的线程数
     */
    private int writeRequests = 0;
    /**
     * 唯一在写的线程
     */
    private Thread writingThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        readingThreads.put(callingThread, (getReadAccessCount(callingThread) + 1));
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if (!isReader(callingThread)) {
            throw new IllegalMonitorStateException("Calling Thread does not" +
                    " hold a read lock on this ReadWriteLock");
        }
        int accessCount = getReadAccessCount(callingThread);
        if (accessCount == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.put(callingThread, (accessCount - 1));
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
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
        if (!isWriter(Thread.currentThread())) {
            throw new IllegalMonitorStateException("Calling Thread does not" +
                    " hold the write lock on this ReadWriteLock");
        }
        writeAccesses--;
        if (writeAccesses == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    /**
     * 判断当前线程是不是可以读
     * 在读里面   优先写
     */
    private boolean canGrantReadAccess(Thread callingThread) {
        //判断这个线程是写线程  -> 重入
        if (isWriter(callingThread)) return true;
        //自己不是写线程,且有写线程 -> 不可入
        if (hasWriter()) return false;
        //自己已经是读线程 -> 重入
        if (isReader(callingThread)) return true;
        //自己不还不是读线程,并且有写请求   -> 不可入
        if (hasWriteRequests()) return false;
        //1.自己不是写线程  2.没有写线程  3.自己不是读线程  4.没有写请求   -> 进入
        return true;
    }

    /**
     * 判断当前线程是不是可以写
     * 在写里面   读优先
     */
    private boolean canGrantWriteAccess(Thread callingThread) {
        //自己是唯一的读者  ->重入
        if (isOnlyReader(callingThread)) return true;
        //有其他读者     -> 不可入
        if (hasReaders()) return false;
        //没有写 者      ->可入
        if (!hasWriter()) return true;
        //有写 者 ,但不是自己   ->不可入
        if (!isWriter(callingThread)) return false;
        //有写 者 ,是自己   -> 重入
        return true;
    }

    private int getReadAccessCount(Thread callingThread) {
        Integer accessCount = readingThreads.get(callingThread);
        if (accessCount == null) return 0;
        return accessCount;
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.get(callingThread) != null;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean isOnlyReader(Thread callingThread) {
        return readingThreads.size() == 1 && readingThreads.get(callingThread) != null;
    }

    private boolean hasWriter() {
        return writingThread != null;
    }

    private boolean hasWriteRequests() {
        return this.writeRequests > 0;
    }
}