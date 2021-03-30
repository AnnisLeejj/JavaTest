package com.annis.array;

import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
//        Integer[] random = ArrayGenerate.random(100, 20000);
//        System.out.println("是否已排序：" + ArrayUtil.isSort(random));
//        System.out.println("是否已排序：" + ArrayUtil.isSort(random));
        Integer[] random;

        random = ArrayGenerate.random(10_0000, 20000);
        quickSort(random);

        Integer[] random2 = Arrays.copyOf(random,random.length);
        quickSort(random2);

        for (int i = 0; i < 100; i++) {
            random = ArrayGenerate.random(10_0000, 20000);
            quickSort(random);
        }
    }

    public static void quickSort(Integer[] random) {
        long start = System.currentTimeMillis();
        QuickSort.sort(random);
        long end = System.currentTimeMillis();
        System.out.println("三路快排：" + random.length + "   耗时：" + (end - start) / 1000.0f + "s");
    }
}
