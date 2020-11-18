package com.annis.leetcode;

public class SimpleTest {
    static SimpleTest simpleTest = new SimpleTest();

    public static void main(String[] args) {
        String binary = simpleTest.addBinary("11", "1");
        System.out.println(binary);
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
}