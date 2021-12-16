package com.annis.test;

public class SystemTest {
    public static void main(String[] args) {


        long s = System.currentTimeMillis();
        long count = 1_0000_0000l;
        long times = 0;
        for (long i = 0; i < count; i++) {
            times++;
        }
        long e = System.currentTimeMillis();
        System.out.println(count+"次运算,需要毫秒:" + (e - s));
    }
}
