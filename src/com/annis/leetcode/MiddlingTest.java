package com.annis.leetcode;

public class MiddlingTest {
    static MiddlingTest middlingTest = new MiddlingTest();

    public static void main(String[] args) {
        System.out.println("最少次数:" + middlingTest.minSteps(238));
    }

    /**
     * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
     * <p>
     * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
     * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
     * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
     *
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int ans = 0, d = 2;
        while (n > 1) {
            while (n % d == 0) {
                ans += d;
                n /= d;
            }
            d++;
        }
        return ans;
    }
}
