package com.annis.leetcode.simple;

import com.annis.utils.ArrayUtil;

public class ArrayTest {
    public static void main(String[] args) {
        //顺序数组去重
//        removeDuplicatesTest();
        //买卖股票的最佳时机
        maxProfitTest();
    }

    private static void maxProfitTest() {
//        int[] preces = new int[]{7, 1, 5, 3, 6, 4};
        int[] preces = new int[]{1,2,3,4,5};
        int count = maxProfit(preces);
        System.out.println("共获利:" + count);
    }

    /**
     * 买卖股票的最佳时机 II
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     */
    public static int maxProfit(int[] prices) {
        int count = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int d = prices[i + 1] - prices[i];
            if (d > 0) {
                count += d;
            }
        }
        return count;
    }

    public static void removeDuplicatesTest() {
        Integer[] array = new Integer[]{1, 1, 2, 2, 3, 3};
//        Integer[] array = new Integer[]{1, 2, 3, 4 ,5, 6};
        int i = removeDuplicates(array);

        System.out.println("不重复长度:" + i + " 数组:" + ArrayUtil.toString(array));
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     */

    public static int removeDuplicates(Integer[] nums) {
        int length = nums.length;
        if (length <= 1) return length;
//        int l = 0, r = 1;
//        while (r < length) {
//            if (nums[l] != nums[r]) {
//                nums[++l] = nums[r];
//            }
//            r++;
//        }
        int l = 0;
        for (int i = 1; i < length; i++) {
            if (nums[l] != nums[i]) {
                nums[++l] = nums[i];
            }
        }
        for (int i = l + 1; i < length; i++) {
            nums[i] = 0;
        }
        return l + 1;
    }
}
