package com.annis.array;

import java.util.Random;

public class QuickSort {
    static Random random;

    public static <E extends Comparable<E>> void sort(E[] array) {
        if (array == null) {
            return;
        }
        if (random == null) random = new Random();
        sort3Ways(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void sort3Ways(E[] array, int l, int r) {
        if (l >= r) {
            return;
        }
        //找出参照数
        int anchor = l + random.nextInt(r - l + 1);
        ArrayUtil.swap(array, l, anchor);
        int index = l + 1;
        int lr = l;
        int rl = r + 1;
        while (index < rl) {
            int compare = array[l].compareTo(array[index]);
            if (compare > 0) {
                lr++;
                ArrayUtil.swap(array, lr, index);
                index++;
            } else if (compare < 0) {
                rl--;
                ArrayUtil.swap(array, rl, index);
            } else {
                index++;
            }
        }
        ArrayUtil.swap(array, l, lr);

        sort3Ways(array, l, lr - 1);
        sort3Ways(array, rl, r);
    }
}
