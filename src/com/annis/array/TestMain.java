package com.annis.array;

import java.util.Random;

public class TestMain {

    public static void main(String[] args) {
        int times = 100;
        int size = 10_0000;
        int max = 10_10000;
        testRandom(times,size,max);
        testOrder(times,size);
        testEqual(times, size);
    }

    private static void testRandom(int times, int size, int max) {
        Random random = new Random();
        Integer[] array;
        float total_time = 0.0f;
        for (int i = 0; i < times; i++) {
            array = ArrayGenerate.random(size, max);
            total_time += quickSort(array, random);
        }
        System.out.println("三路快排(随机)：" + size + " - " + times + "次  平均耗时：" + (total_time / times) + "毫秒");
    }

    private static void testOrder(int times, int size) {
        Random random = new Random();
        Integer[] array;
        float total_time = 0.0f;
        for (int i = 0; i < times; i++) {
            array = ArrayGenerate.order(size);
            total_time += quickSort(array, random);
        }
        System.out.println("三路快排(顺序)：" + size + " - " + times + "次  平均耗时：" + (total_time / times) + "毫秒");
    }

    private static void testEqual(int times, int size) {
        Random random = new Random();
        Integer[] array;
        float total_time = 0.0f;
        for (int i = 0; i < times; i++) {
            array = ArrayGenerate.equal(size);
            total_time += quickSort(array, random);
        }
        System.out.println("三路快排(相同)：" + size + " - " + times + "次  平均耗时：" + (total_time / times) + "毫秒");
    }

    /**
     * 单次排序
     *
     * @param array  待排序数组
     * @param random
     * @return
     */
    public static long quickSort(Integer[] array, Random random) {
        long start = System.currentTimeMillis();
        QuickSort.sort(array, random);
        long end = System.currentTimeMillis();
        long time = (end - start);
//        System.out.println("三路快排：" + array.length + "   耗时：" + time + "s");
        return time;
    }
}
