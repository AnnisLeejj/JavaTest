package com.annis.array;

import java.util.Random;

public class ArrayGenerate {
    public static Integer[] random(int size, int max) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max);
        }
        return array;
    }
}
