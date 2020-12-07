package com.annis.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 获得的总结
 * 1.数组查找是有内存消耗的,尽可能的减少其使用
 * 2.数组转换的操作是费时的
 */
public class SimpleTest {
    static SimpleTest simpleTest = new SimpleTest();

    public static void main(String[] args) {
//        String binary = simpleTest.addBinary2("11", "1");
//        System.out.println(binary);
        //System.out.println(simpleTest.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
//        String s = Integer.toBinaryString(1);
//        String s1 = Integer.toBinaryString(-1);
//        System.out.println(s);
//        System.out.println(s1);

//        System.out.println(simpleTest.add(1, 0));

//        System.out.println(simpleTest.sortString("eabcddcba"));
//        simpleTest.treeNode();

//        System.out.println("各位数相加:" + simpleTest.addDigits(65));

//        System.out.println("定位位置是:" + simpleTest.search(new int[]{62}, 62));
//        System.out.println("定位位置是:" + simpleTest.search(new int[]{1, 12, 23, 31, 41, 52, 62, 75, 84, 91, 100, 110, 124, 132}, 62));

//        System.out.println("替换结果:" + simpleTest.replaceSpace("new int array"));
//        System.out.println("最小集合:" + simpleTest.getLeastNumbers(new int[]{1, 12, 23, 31, 41, 52, 62, 75, 84, 91, 100, 110, 124, 132}, 2));
        System.out.println("最小集合:" + simpleTest.getLeastNumbers(new int[]{1, 2, 3}, 2));
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     * <p>
     * 执行用时： 7 ms , 在所有 Java 提交中击败了 70.69% 的用户
     * 内存消耗： 40.2 MB , 在所有 Java 提交中击败了 32.31% 的用户
     * <p>
     * 7ms 太差了 , 应该 把排序的工作自己做
     */
    public int[] getLeastNumbers2(int[] arr, int k) {
        int[] result = new int[k];
        Arrays.sort(arr);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
             result[i]=-1;
        }
        int max = Integer.MAX_VALUE;
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {
            if (arr[i] < max) {
                max = insertArray(result, arr[i]);
            }
        }
        return result;
    }

    /**
     * 插入数组
     *
     * @param arr
     * @param num
     * @return 数组现在最大的数
     */
    private int insertArray(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        int current = (left + right) / 2;
        //找位置
        while (left < right) {
            if (arr[current] == 0 || num < arr[current]) {
                right = current - 1;
            } else {
                left = current + 1;
            }
        }
        if (arr.length - current == 1) {
            arr[arr.length - 1] = num;
            return num;
        }
        //插入
        int temp = num;
        for (int i = current; i < arr.length - 2; i++) {
            int s = arr[i];
            arr[i] = temp;
            temp = s;
        }
        arr[arr.length - 1] = temp;
        return temp;
    }

    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * <p>
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 36.2 MB , 在所有 Java 提交中击败了 95.03%  的用户
     *
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        if (s == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            buffer.append((c == ' ') ? "%20" : c);
        }
        return buffer.toString();
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 25.83% 的用户
     * 内存消耗： 36.3 MB , 在所有 Java 提交中击败了 79.46% 的用户
     * <p>
     * -> 数组的转换是费时的
     *
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null) {
            return null;
        }
        StringBuffer buffer = new StringBuffer();
        for (char c : s.toCharArray()) {
            buffer.append((c == ' ') ? "%20" : c);
        }

        return buffer.toString();
    }

    /**
     * 执行用时： 3 ms , 在所有 Java 提交中击败了 12.76% 的用户
     * 内存消耗： 36.5 MB , 在所有 Java 提交中击败了 52.17% 的用户
     * <p>
     * -> 数组的转换是费时的
     *
     * @param s
     * @return
     */
    public String replaceSpace3(String s) {
        return s.replace(" ", "%20");
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 66.82%  的用户
     * <p>
     * ##### 数组查找是有内存消耗的,尽可能的减少其使用
     *
     * @param nums
     * @param target
     * @return
     */
    public int search2(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int next = (right + left) / 2;
            int current = nums[next];
            if (current == target) {
                return next;
            } else if (target < current) {
                right = next - 1;
            } else {
                left = next + 1;
            }
        }
        return -1;
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 66.82%  的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int next = (right + left) / 2;
            if (nums[next] == target) {
                return next;
            } else if (target < nums[next]) {
                right = next - 1;
            } else {
                left = next + 1;
            }
        }
        return -1;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了  14.16%  的用户
     * 内存消耗： 35.5 MB , 在所有 Java 提交中击败了 83.76%  的用户
     */
    public int addDigits(int num) {
        int current = 0;
        while (num > 0) {
            current += (num % 10);
            num /= 10;
        }
        if (current > 9) {
            current = addDigits(current);
        }
        return current;
//        return (num - 1) % 9 + 1;
    }

    /**
     * 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * <p>
     * 本题中，一棵高度平衡二叉树定义为：
     * <p>
     * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
     */
    private void treeNode() {
        TreeNode root = new TreeNode(1);
        //----------左
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        root.left.right.left.right = new TreeNode(5);
        root.left.right.left.right.left = new TreeNode(6);
        //----------右
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.left.right = new TreeNode(4);
        root.right.left.right.right = new TreeNode(5);

        System.out.println(isBalanced(root) ? "是 平衡树" : "不是 平衡树");
    }

    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 执行用时：  1 ms  , 在所有 Java 提交中击败了  99.80%  的用户
     * 内存消耗：  38.5 MB  , 在所有 Java 提交中击败了 77.46% 的用户
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
//        return Math.abs(left - right) <= 1 ;//之前这个居然没有想到 轮询就OK了   mmp
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return 1 + Math.max(left, right);
    }

    /**
     * 给你一个字符串s，请你根据下面的算法重新构造字符串：
     * <p>
     * 从 s中选出 最小的字符，将它 接在结果字符串的后面。
     * 从 s剩余字符中选出最小的字符，且该字符比上一个添加的字符大，将它 接在结果字符串后面。
     * 重复步骤 2 ，直到你没法从 s中选择字符。
     * 从 s中选出 最大的字符，将它 接在结果字符串的后面。
     * 从 s剩余字符中选出最大的字符，且该字符比上一个添加的字符小，将它 接在结果字符串后面。
     * 重复步骤 5，直到你没法从 s中选择字符。
     * 重复步骤 1 到 6 ，直到 s中所有字符都已经被选过。
     * 在任何一步中，如果最小或者最大字符不止一个，你可以选择其中任意一个，并将其添加到结果字符串。
     * <p>
     * 请你返回将s中字符重新排序后的 结果字符串 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/increasing-decreasing-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String sortString(String s) {
//        StringBuilder stringBuilder = new StringBuilder();
//        boolean asc = true;
//        char current = 256;
//        while (s.length() == 0) {
//            if (asc) {//升
//                char min = getMin(s, current);
//                if (min == 0) {
//                    asc = false;
//                } else {
//                    current = min;
//                    stringBuilder.append(min);
//                }
//            } else {//降
//
//            }
//        }
//
//
//        return stringBuilder.toString();

        StringBuilder builder = new StringBuilder();
        int[] map = new int[26];
        for (char c : s.toCharArray()) map[c - 'a']++;
        boolean flag;
        do {
            flag = false;
            for (int i = 0; i < 26; i++) {
                if (map[i] > 0) {
                    builder.append((char) (i + 'a'));
                    map[i]--;
                    flag = true;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (map[i] > 0) {
                    builder.append((char) (i + 'a'));
                    map[i]--;
                    flag = true;
                }
            }
        } while (flag);
        return builder.toString();
    }

//    private char getMin(String s, char current) {
//        char result = 0;
//        for (int index = 0; index < s.length(); index++) {
//            char c = s.charAt(index);
//            if (c < current) {
//                result = c;
//            }
//        }
//        if (result != 0) {
//            s.replaceFirst(result,'-');
//        }
//        return result;
//    }
//
//    private char getMax(String s, char current) {
//        char result = 0;
//        for (int index = 0; index < s.length(); index++) {
//            char c = s.charAt(index);
//            if (c > current) {
//                result = c;
//            }
//        }
//        if (result != 0) {
//
//        }
//        return result;
//    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     */
    public int climbStairs(int n) {
//        int max2Tip = n / 2;//最多这个多个 2量

        return 0;
    }

    /**
     * 设计一个函数把两个数字相加。不得使用 + 或者其他算术运算符。
     * <p>
     * 执行用时：0 ms , 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.2 MB, 在所有 Java 提交中击败了81.00%的用户
     *
     * @param a
     * @param b
     * @return
     */
    public int add(int a, int b) {
        while (b != 0) {
            int sum = (a ^ b);
            b = (a & b) << 1;
            a = sum;
        }
        return a;
    }
    /**
     * 121. 买卖股票的最佳时机
     * <p>
     * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
     * <p>
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
     * <p>
     * 注意：你不能在买入股票前卖出股票。
     */
    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了98.64%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了64.44%的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (maxprofit < prices[i] - minprice) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }

    /**
     * 执行用时：540 ms, 在所有 Java 提交中击败了5.07%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了81.44%的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxInCome = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int currentIncome = 0;
            for (int j = i + 1; j < prices.length; j++) {
                currentIncome += prices[j] - prices[j - 1];
                maxInCome = Math.max(maxInCome, currentIncome);
            }
        }
        return maxInCome;
    }

    /**
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10或11)来表示。
     * <p>
     * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
     *
     * @param bits 00000100001110
     * @return
     */
    public boolean isOneBitCharacter(int[] bits) {
        if (bits.length == 0) return false;
        if (bits[bits.length - 1] == 1) return false;
        if (bits.length == 1) return true;
        if (bits[bits.length - 2] == 0) return true;
        if (bits.length == 3) return bits[0] == 1;
        //0      10       11

        //******00**11010
        //00 前面就不用看了

        // 110
        boolean needdjoin = true;//n+1 是否需要被带上

        // 1 可以带    0 不可以带

        //1.需要带而不能带 false   2.不要带
        for (int index = bits.length - 3; index >= 0; index--) {
            int current = bits[index];
            if (needdjoin) {
                if (current == 0) {
                    return false;
                } else {
                    needdjoin = false;
                }
            } else {
                if (current == 0) {
                    return true;
                } else {
                    needdjoin = true;
                }
            }
        }
        return !needdjoin;
    }

    /**
     * https://leetcode-cn.com/problems/add-binary/submissions/
     * <p>
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     * <p>
     * 输入为 非空 字符串且只包含数字 1 和 0。
     * 输入: a = "11", b = "1"
     * 输出: "100"
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了98.88%的用户
     * 内存消耗：37.3 MB, 在所有 Java 提交中击败了77.00%的用户
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();
        char[] result = new char[1 + Math.max(lengthA, lengthB)];

        //这里不赋值 会少 0.1M内存  >.<
        char cA = '0';
        char cB = '0';
        char more = '0';
        for (int index = 1; index <= result.length; index++) {
            int indexA = lengthA - index;
            int indexB = lengthB - index;
            if (indexA < 0) {
                cA = '0';
            } else {
                cA = a.charAt(indexA);
            }
            if (indexB < 0) {
                cB = '0';
            } else {
                cB = b.charAt(indexB);
            }

            if (cA == cB) {
                result[result.length - index] = more;
                more = cB == '1' ? '1' : '0';
            } else {
                result[result.length - index] = (more == '1' ? '0' : '1');
                more = (more == '1' ? '1' : '0');
            }
        }
        String s = new String(result);
        int i = s.indexOf("1");
        if (i >= 0) {
            return s.substring(i);
        } else {
            return "0";
        }
    }

    public String addBinary1(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();
        char[] result = new char[1 + Math.max(lengthA, lengthB)];

        int first1 = -1;
        char cA;
        char cB;
        char more = '0';
        for (int index = 1; index <= result.length; index++) {
            int indexA = lengthA - index;
            int indexB = lengthB - index;
            if (indexA < 0) {
                cA = '0';
            } else {
                cA = a.charAt(indexA);
            }
            if (indexB < 0) {
                cB = '0';
            } else {
                cB = b.charAt(indexB);
            }

            if (cA == cB) {
                result[result.length - index] = more;
                more = cB;
            } else {
                result[result.length - index] = (more == '1' ? '0' : '1');
            }
            if (result[result.length - index] == '1') {
                first1 = result.length - index;
            }
        }
        if (first1 == -1) return "0";
        return new String(result, first1, result.length - first1);
        //较上面多了 0.1M内存
//        String s = new String(result);
//        int i = s.indexOf("1");
//        if (i >= 0) {
//            return s.substring(i);
//        } else {
//            return "0";
//        }
    }

    /**
     * 执行用时：2 ms, 在所有 Java 提交中击败了 98.88%的用户
     * 内存消耗：37.1 MB, 在所有 Java 提交中击败了90.76% 的用户
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary2(String a, String b) {
        int lengthA = a.length();
        int lengthB = b.length();
        StringBuilder result = new StringBuilder();

        char cA = '0', cB = '0', more = '0';
        for (int index = 1; index <= Math.max(lengthA, lengthB); index++) {
            int indexA = lengthA - index;
            int indexB = lengthB - index;
            if (indexA >= 0) {
                cA = a.charAt(indexA);
            }
            if (indexB >= 0) {
                cB = b.charAt(indexB);
            }

            if (cA == cB) {
                result.append(more);
                more = cB;
            } else {
                result.append(more == '1' ? '0' : '1');
            }
            cA = '0';
            cB = '0';
        }
        if (more == '1') {
            result.append('1');
        }
        result.reverse();
        return result.toString();
    }
}