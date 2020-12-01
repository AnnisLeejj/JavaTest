package com.annis.performance;

import java.util.Timer;
import java.util.TimerTask;

public class TimeTest {
    static int times = 0;

    public static void main(String[] args) {

        int a = 1, b = 1;
        Runnable runnable = () -> {
            for(;;){
                int c = a + b;
                times++;
            }
        };
        Thread thread = new Thread(runnable);
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                thread.stop();
                System.out.println("次数:"+times);
            }
        };
        Timer timer = new Timer();
        thread.start();

        timer.schedule(timerTask, 1000);
    }
}
