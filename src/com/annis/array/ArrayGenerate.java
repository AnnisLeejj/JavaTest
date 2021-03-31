package com.annis.array;

import java.util.Random;

public class ArrayGenerate {
    /**
     * 随机生成一个数组
     *
     * @param size 数组大小
     * @param max  最大数
     * @return
     */
    public static Integer[] random(int size, int max) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max);
        }
        return array;
    }

    /**
     * 生成有序数组
     *
     * @param size 数组大小
     * @return
     */
    public static Integer[] order(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
        return array;
    }

    /**
     * 生成相同数组
     *
     * @param size
     * @return
     */
    public static Integer[] equal(int size) {
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1;
        }
        return array;
    }
}
