package com.annis.usable;

public class CarNumberSelector {
    public static void main(String[] args) {
        int[] numbers = new int[]{5241, 9675, 123, 743, 785};


        select(numbers);
    }

    final static int[] goodNumber = new int[]{1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25,
            29, 31, 32, 33, 35, 37, 39, 41, 45, 47, 48, 52, 63, 65, 67, 73, 81};
    final static int[] goodWithBad = new int[]{27, 30, 40, 42, 43, 50, 51, 55, 61, 71, 75, 77, 80};
    final static int[] badWithGood = new int[]{38, 57, 58,};

    private static void select(int[] numbers) {
        for (int number : numbers) {
            String judge = judge(number);


            System.out.println(String.format("%5d : %s %s", number, dayOfWeekBelLimit(number), judge));
        }
    }

    /**
     * 一周限号时间
     *
     * @param number
     */
    private static String dayOfWeekBelLimit(int number) {
        int value = number % 10 % 5;
        value = value == 0 ? 5 : value;
        return "周" + value + "限号";
    }

    /**
     * 判断凶吉
     *
     * @param number
     * @return
     */
    private static String judge(int number) {
        int value = (int) ((number / 80.0f % 1) * 80);
        for (int item : goodNumber) {
            if (item == value) {
                return "吉";
            }
        }

        for (int item : goodWithBad) {
            if (item == value) {
                return "吉带凶";
            }
        }

        for (int item : badWithGood) {
            if (item == value) {
                return "凶带吉";
            }
        }
        return "凶";
    }
}
