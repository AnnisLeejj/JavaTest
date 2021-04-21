package com.annis.utils;

public class ArrayUtil {
    public static String toString(int[] array) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(i).append(":").append(array[i]).append("  - ");
        }
        return buffer.toString();
    }

    public static String toString(Object[] array) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            buffer.append(i).append(":").append(array[i].toString()).append("  - ");

        }
        return buffer.toString();
    }

    public static <E> void swap(E[] arr, int i, int j) {
        E t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static <E extends Comparable<E>> boolean isSort(E[] arr) {
        if (arr == null)
            return false;
        if (arr.length < 3) {
            return true;
        }
        final boolean order = arr[0].compareTo(arr[1]) > 0;
        for (int i = 1; i < arr.length - 2; i++) {
            int compare = arr[i].compareTo(arr[i + 1]);
            if (order && compare < 0 || !order && compare > 0) {
                return false;
            }
        }
        return true;
    }
}
