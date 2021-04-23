package com.annis.leetcode.simple;

import java.util.Arrays;
import java.util.HashMap;

public class StringTest {
    public static void main(String[] args) {
        //有效的字母异位词
        // isAnagramTest();
        //验证回文串
        //isPalindromeTest();
        //字符串转换整数
        myAtoiTest();
    }

    /**
     * 字符串转换整数 (atoi)
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     */
    public static void myAtoiTest() {
//        System.out.println(myAtoi("   -43"));
//        System.out.println(myAtoi("words and 987"));
//        System.out.println(myAtoi("1.2"));
//        System.out.println(myAtoi("+-3.2"));
//        System.out.println(myAtoi("42"));
//        System.out.println(myAtoi("  -42"));
//        System.out.println(myAtoi("9223372036854775808"));
        System.out.println(myAtoi("-91283472332"));
    }

    public static int myAtoi(String s) {
        boolean haveNum = false;
        int sign = 0;
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                if (sign != 0) {
                    num *= sign;
                    sign =0;
                }
                haveNum = true;

                if (num > 2147483647) return 2147483647;
                if (num < -2147483648) return -2147483648;
            } else {
                if (haveNum) {
                    break;
                }
                if (' ' == c) continue;
                if ('-' == c) {
                    sign = -1;
                    haveNum = true;
                } else if ('+' == c) {
                    sign = 1;
                    haveNum = true;
                } else {
                    break;
                }
            }
        }
        if (!haveNum) return 0;
        return (int) num;
    }

    public static int myAtoi2(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int index = 0;
        // 先去除空格
        while (index < length && chars[index] == ' ') {
            index++;
        }
        // 极端情况 "  " 和""
        if (index >= length) {
            return 0;
        }
        // 再判断符号
        int sign = 1;
        if (chars[index] == '-' || chars[index] == '+') {
            if (chars[index] == '-') {
                sign = -1;
            }
            index++;
        }
        int result = 0;
        int temp = 0;
        while (index < length) {
            int num = chars[index] - '0';
            if (num > 9 || num < 0) {
                break;
            }
            temp = result;
            result = result * 10 + num;
            // 越界后，数值和期望数值发生变化，取余再除10获取原始值，比对判断
            if (result / 10 != temp) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            index++;
        }
        return result * sign;
    }

    public static void isPalindromeTest() {
//        System.out.println(isPalindrome("race a car"));
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isPalindrome("ab2a"));
        System.out.println(isPalindrome3("race a car"));
        System.out.println(isPalindrome3("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome3("ab2a"));
        System.out.println(isPalindrome3("0P"));
    }

    /**
     * 验证回文串
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     * <p>
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     */
    /*
    执行用时：3 ms, 在所有 Java 提交中击败了93.52%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了23.60%的用户
     */
    public static boolean isPalindrome4(String s) {
        char[] chars = s.toCharArray();
        int ss = 0, st = s.length() - 1;

        int d = 'a' - 'A';
        while (ss < st) {
            while (ss < st && !Character.isLetterOrDigit(chars[ss])) {
                ss++;
            }
            while (ss < st && !Character.isLetterOrDigit(chars[st])) {
                st--;
            }
            if (chars[ss] != chars[st]) {
                if (Math.abs(chars[ss] - chars[st]) != d || Character.isDigit(chars[ss]) || Character.isDigit(chars[st])) {
                    return false;
                }
            }
            ss++;
            st--;
        }
        return true;
    }

    /*执行用时：4 ms, 在所有 Java 提交中击败了64.65%的用户
    内存消耗：38.3 MB, 在所有 Java 提交中击败了88.05%的用户*/
    public static boolean isPalindrome3(String s) {
        s = s.toUpperCase();
        char[] chars = s.toCharArray();
        int ss = 0, st = s.length() - 1;
        while (ss < st) {
            char c = chars[ss];
            if (Character.isLetterOrDigit(c)) {
                char c2 = chars[st];
                if (Character.isLetterOrDigit(c2)) {
                    if (c != c2) return false;
                    else {
                        ss++;
                        st--;
                    }
                } else {
                    st--;
                }
            } else {
                ss++;
            }
        }
        return true;
    }

    /*执行用时：3 ms, 在所有 Java 提交中击败了93.52%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了42.50%的用户
     */
    public static boolean isPalindrome2(String s) {
        s = s.toUpperCase();
        char[] chars = s.toCharArray();
        int ss = 0, st = s.length() - 1;
        while (ss < st) {
            char c = chars[ss];
            if (c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
                char c2 = chars[st];
                if (c2 >= 'A' && c2 <= 'Z' || c2 >= '0' && c2 <= '9') {
                    if (c != c2) return false;
                    else {
                        ss++;
                        st--;
                    }
                } else {
                    st--;
                }
            } else {
                ss++;
            }
        }
        return true;
    }

    /*
    执行用时：3 ms, 在所有 Java 提交中击败了93.52%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了57.86%的用户
     */
    public static boolean isPalindrome(String s) {
        s = s.toUpperCase();
        int ss = 0, st = s.length() - 1;
        while (ss < st) {
            char c = s.charAt(ss);
            if (c >= 'A' && c <= 'Z' || c >= '0' && c <= '9') {
                char c2 = s.charAt(st);
                if (c2 >= 'A' && c2 <= 'Z' || c2 >= '0' && c2 <= '9') {
                    if (c != c2) return false;
                    else {
                        ss++;
                        st--;
                    }
                } else {
                    st--;
                }
            } else {
                ss++;
            }
        }
        return true;
    }

    public static boolean isAvailability(char c) {
        return (c >= 'A' && c <= 'Z' || c >= '0' && c <= '9');

    }

    /**
     * 有效的字母异位词
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * <p>
     * 示例 2:
     * 输入: s = "rat", t = "car"
     * 输出: false
     */
    public static void isAnagramTest() {
        //System.out.println(isAnagram("rat", "car"));
        System.out.println(isAnagram("anagram", "nagaram"));
    }

    /*执行用时：3 ms, 在所有 Java 提交中击败了87.69%的用户
    内存消耗：38.4 MB, 在所有 Java 提交中击败了94.58%的用户
     */
    public static boolean isAnagram5(String s, String t) {
        if (s.length() != t.length())
            return false;
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int[] map = new int[26];
        int count = 0;
        for (int i = 0; i < cs.length; i++) {
            //出现了一个新的字符
            if (++map[cs[i] - 'a'] == 1) {
                count++;
            }
            //消失了一个新的字符
            if (--map[ct[i] - 'a'] == 0) {
                count--;
            }
        }
        return count == 0;
    } /*
    执行用时：4 ms, 在所有 Java 提交中击败了63.70%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了65.55%的用户
     */

    public static boolean isAnagram4(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            counts[index] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            if (counts[index] == 0) {
                return false;
            }
            counts[index] -= 1;
        }
        return true;
    }

    /*
    执行用时：17 ms, 在所有 Java 提交中击败了23.53%的用户
    内存消耗：39.2 MB, 在所有 Java 提交中击败了16.93%的用户
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer integer = hashMap.get(c);
            if (integer == null) {
                hashMap.put(c, 1);
            } else {
                hashMap.put(c, integer + 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = t.charAt(i);
            Integer integer = hashMap.get(c);
            if (integer == null) {
                return false;
            } else {
                if (integer == 0) return false;
                hashMap.put(c, integer - 1);
            }
        }
        return true;
    }

    /*
    执行用时：4 ms, 在所有 Java 提交中击败了63.70%的用户
    内存消耗：38.7 MB, 在所有 Java 提交中击败了67.56%的用户
     */
    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;
        char[] sc = s.toCharArray();
        char[] tc = t.toCharArray();
        Arrays.sort(sc);
        Arrays.sort(tc);
        for (int i = 0; i < s.length(); i++) {
            if (sc[i] != tc[i]) {
                return false;
            }
        }
        return true;
    }

    /*
    执行用时：3 ms, 在所有 Java 提交中击败了87.69%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了89.83%的用户
     */
    public boolean isAnagram3(String s, String t) {
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        //对两个字符串中的字符进行排序
        Arrays.sort(sChar);
        Arrays.sort(tChar);
        return Arrays.equals(sChar, tChar);
    }


    //理解失败
//    public static boolean isAnagram(String s, String t) {
//        if (s.length() != t.length()) return false;
//        int stagger = 0;
//        while (stagger < t.length()) {
//            int is = 0, it = stagger;
//            while (s.charAt(is) == t.charAt(it % s.length())) {
//                is++;
//                it++;
//            }
//            if ((it - s.length()) == stagger) {
//                return true;
//            }
//            stagger++;
//        }
//        return false;
//    }
}
