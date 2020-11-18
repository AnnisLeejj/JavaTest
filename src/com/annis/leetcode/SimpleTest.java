package com.annis.leetcode;

public class SimpleTest {
    static SimpleTest simpleTest = new SimpleTest();

    public static void main(String[] args) {
//        String binary = simpleTest.addBinary2("11", "1");
//        System.out.println(binary);
        //System.out.println(simpleTest.maxProfit1(new int[]{7, 1, 5, 3, 6, 4}));
        String s = Integer.toBinaryString(1);
        String s1 = Integer.toBinaryString(-1);
        System.out.println(s);
        System.out.println(s1);

        System.out.println(simpleTest.add(1, 0));
    }

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
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
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
     * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
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