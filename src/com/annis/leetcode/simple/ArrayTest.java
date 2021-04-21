package com.annis.leetcode.simple;

import com.annis.utils.ArrayUtil;

import java.util.*;

public class ArrayTest {
    public static void main(String[] args) {
        //顺序数组去重
//        removeDuplicatesTest();
        //买卖股票的最佳时机
//        maxProfitTest();
        //旋转数组(平移)
//        rotateTest();

        //存在重复元素
//        int[] nums = new int[]{7, 7, 1, 5, 3, 6, 4};
//        System.out.println("存在重复元素:" + containsDuplicate(nums));
//        System.out.println("存在重复元素:" + containsDuplicate2(nums));

//        两个数组的交集 II
//        int[] nums = new int[]{7, 7, 1, 5, 3, 6, 4};
//        int[] nums2 = new int[]{7, 7, 5, 3, 4};
//        System.out.println("存在重复元素:" + ArrayUtil.toString(intersect3(nums, nums2)));

        //有效的数独
        //isValidSudokuTest();

    }

    /**
     * 字符串中的第一个唯一字符
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     */
/*
执行用时：4 ms, 在所有 Java 提交中击败了98.69%的用户
内存消耗：39.1 MB, 在所有 Java 提交中击败了31.11%的用户
 */
    public int firstUniqChar2(String s) {
        int[] words = new int[26];
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length ; i++) {
            char c = chars[i];
            words[c - 'a'] += 1;
        }
        for (int i = 0; i < chars.length; i++) {
            if (words[chars[i] - 'a'] == 1)
                return i;
        }
        return -1;
    }
    /*
    执行用时：8 ms, 在所有 Java 提交中击败了75.84%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了96.33%的用户
     */
    public int firstUniqChar(String s) {
        int[] words = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            words[c - 'a'] += 1;
        }
        for (int i = 0; i < s.length(); i++) {
            if (words[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }

    /**
     * 有效的数独
     * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     * <p>
     * 数字 1-9 在每一行只能出现一次。
     * 数字 1-9 在每一列只能出现一次。
     * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     * 数独部分空格内已填入了数字，空白格用 '.' 表示。
     * <p>
     * 注意：
     * 一个有效的数独（部分已被填充）不一定是可解的。
     * 只需要根据以上规则，验证已经填入的数字是否有效即可。
     */

    public static void isValidSudokuTest() {
//        int flag = 0;
//        int num = 1 << 2;
//        System.out.println("num:" + num);
//        System.out.println("& 结果:" + (flag & num));


        int i = 8, j = 8;
        int cellIndex = i / 3 * 3 + j / 3;

        System.out.println("cellIndex:" + cellIndex);
    }

    public static boolean isValidSudoku(char[][] board) {
        int[] lines = new int[9];
        int[] rows = new int[9];
        int[] cells = new int[9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    continue;
                //num是当前格子的数字
                int num = board[i][j] - '0';
                int flag = 1 << num;

                int cellIndex = i / 3 * 3 + j / 3;

                if ((lines[i] & flag) > 0 || (rows[j] & flag) > 0 || (cells[cellIndex] & flag) > 0) {
                    return false;
                }
                lines[i] |= flag;
                rows[j] |= flag;
                cells[cellIndex] |= flag;
            }
        }
        return true;
    }

    /**
     * 两个数组的交集 II
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 进阶：
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？ √
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？√
     * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？√
     */

    /*
     *执行用时：3 ms, 在所有 Java 提交中击败了68.04%的用户
     *内存消耗：38.7 MB, 在所有 Java 提交中击败了47.72%的用户
     */
    public static int[] intersect3(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] big, min;
        if (nums1.length > nums2.length) {
            big = nums1;
            min = nums2;
        } else {
            big = nums2;
            min = nums1;
        }
        for (int i : min) {
            Integer integer = map.get(i);
            map.put(i, (integer == null ? 0 : integer) + 1);
        }
        List<Integer> array = new ArrayList<>();

        for (int i : big) {
            Integer integer = map.get(i);
            if (integer == null) continue;
            array.add(i);
            if (integer == 1) {
                map.remove(i);
            } else {
                map.put(i, integer - 1);
            }
        }
        int count = array.size();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    /*
   使用空间换得了时间(相比上面一种方法优化了时间)
   执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
   内存消耗：38.8 MB, 在所有 Java 提交中击败了24.17%的用户
    */
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i1 = 0;
        int i2 = 0;

        int count = 0;
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                result[count++] = nums1[i1];
                i1++;
                i2++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }

        int[] real = new int[count];
        System.arraycopy(result, 0, real, 0, count);
        return real;
    }

    /*
    执行用时：2 ms, 在所有 Java 提交中击败了87.32%的用户
    内存消耗：38.9 MB, 在所有 Java 提交中击败了8.96%的用户
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> array = new ArrayList<>();
        int i1 = 0;
        int i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                array.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1] > nums2[i2]) {
                i2++;
            } else {
                i1++;
            }
        }
        int count = array.size();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    /**
     * 存在重复元素
     * 给定一个整数数组，判断是否存在重复元素。
     * <p>
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     */
    /*
    执行用时：3 ms, 在所有 Java 提交中击败了99.72%的用户
    内存消耗：41.6 MB, 在所有 Java 提交中击败了86.79%的用户
     */
    public static boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    /*
    执行用时：6 ms, 在所有 Java 提交中击败了57.84%的用户
    内存消耗：42.6 MB, 在所有 Java 提交中击败了51.33%的用户
     */
    public static boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int num : nums) {
            if (!hashSet.add(num)) {
                return true;
            }
        }
        return false;
    }


    public static void rotateTest() {
        int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        rotate(nums, 2);
        System.out.println(ArrayUtil.toString(nums));

        int[] nums2 = new int[]{7, 1, 5, 3, 6, 4};
        rotate2(nums2, 2);
        System.out.println(ArrayUtil.toString(nums2));
    }

    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        int[] temp = new int[k];
        System.arraycopy(nums, nums.length - k, temp, 0, k);
        System.arraycopy(nums, 0, nums, k, nums.length - k);
        System.arraycopy(temp, 0, nums, 0, k);
    }

    /**
     * 旋转数组(平移)
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     * 进阶：
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 你可以使用(空间复杂)度为 O(1) 的 原地 算法解决这个问题吗?
     */
    public static void rotate(int[] nums, int k) {
        if (nums.length < 2) return;
        k %= nums.length;
        rotate(nums, 0, nums.length - 1);
        rotate(nums, 0, k - 1);
        rotate(nums, k, nums.length - 1);
    }

    /**
     * 执行用时：  2 ms, 在所有 Java 提交中击败了 23.86% 的用户
     * 内存消耗：  55.2 MB , 在所有 Java 提交中击败了 5.01%  的用户
     */
    public static void rotate(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    private static void maxProfitTest() {
//        int[] preces = new int[]{7, 1, 5, 3, 6, 4};
        int[] preces = new int[]{1, 2, 3, 4, 5};
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
