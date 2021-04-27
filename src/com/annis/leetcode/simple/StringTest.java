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
        //myAtoiTest();
        //实现 strStr()
//        strStrTest();
        //外观数列
        countAndSayTest();
    }

    /**
     * 外观数列
     * 给定一个正整数 n ，输出外观数列的第 n 项。
     * <p>
     * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
     * <p>
     * 你可以将其视作是由递归公式定义的数字字符串序列：
     * <p>
     * countAndSay(1) = "1"
     * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
     * 前五项如下：
     * <p>
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     * 第一项是数字 1
     * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
     * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
     * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
     * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
     */
    public static void countAndSayTest() {
//        System.out.println("外观数列:" + countAndSay(1));
//        System.out.println("外观数列:" + countAndSay(2));
//        System.out.println("外观数列:" + countAndSay(3));
//        System.out.println("外观数列:" + countAndSay(4));
//        System.out.println("外观数列:" + countAndSay(5));
        for (int i = 1; i < 10; i++) {
            System.out.println("外观数列(" + i + "):" + countAndSay5(i));
        }
    }
    /*执行用时：1 ms, 在所有 Java 提交中击败了97.78%的用户
    内存消耗：35.9 MB, 在所有 Java 提交中击败了87.11%的用户*/
    public static String countAndSay5(int n) {
        String res = "1";
        for (int i = 0; i < n; i++) {
            res = say(res);
        }
        return res;
    }
    /*执行用时：1 ms, 在所有 Java 提交中击败了97.78%的用户
       内存消耗：35.9 MB, 在所有 Java 提交中击败了87.11%的用户*/
    private static String say(String s) {
        if (s.equals("1")) {
            return "1";
        }

        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int cnt = 1;
        char cur = s.charAt(0);
        for (int i = 1; i < n; i++) {
            if (cur == s.charAt(i)) {
                cnt++;
            } else {
                sb.append((char)('0' + cnt));
                sb.append(cur);
                cur = s.charAt(i);
                cnt = 1;
            }
        }

        sb.append((char)('0' + cnt));
        sb.append(cur);

        return sb.toString();
    }

    /*执行用时：6 ms, 在所有 Java 提交中击败了44.66%的用户
    内存消耗：35.9 MB, 在所有 Java 提交中击败了87.51%的用户*/
    public static String countAndSay4(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 2;
        StringBuilder buffer = new StringBuilder("1");
        int readStartIndex ;
        int lastLength = 0;

        char c = 0;
        int count = 0;
        while (i <= n) {
            readStartIndex = lastLength;
            lastLength = buffer.length();
            for (int index = readStartIndex; index < lastLength; index++) {
                char ci = buffer.charAt(index);
                if (ci == c) {
                    count += 1;
                } else {
                    if (c != 0) {
                        buffer.append(count).append(c);
                    }
                    c = ci;
                    count = 1;
                }
            }
            buffer.append(count).append(c);

            count = 0;
            c = 0;
            i++;
        }
        return buffer.substring(lastLength);
    }

    /*
    执行用时：7 ms, 在所有 Java 提交中击败了41.73%的用户
    内存消耗：36.1 MB, 在所有 Java 提交中击败了67.29%的用户
     */
    public String countAndSay3(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 2;
        StringBuffer last = new StringBuffer("1");

        char c = 0;
        int count = 0;
        while (i <= n) {
            StringBuffer buffer = new StringBuffer();
            for (int index = 0; index < last.length(); index++) {
                char ci = last.charAt(index);
                if (ci == c) {
                    count += 1;
                } else {
                    if (c != 0) {
                        buffer.append(count).append(c);
                    }
                    c = ci;
                    count = 1;
                }
            }
            buffer.append(count).append(c);
            last = buffer;

            count = 0;
            c = 0;
            i++;
        }
        return last.toString();
    }

    /*执行用时：6 ms, 在所有 Java 提交中击败了44.66%的用户
    内存消耗：36 MB, 在所有 Java 提交中击败了76.81%的用户*/
    public static String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 2;
        String last = "1";

        char c = 0;
        int count = 0;
        while (i <= n) {
            StringBuffer buffer = new StringBuffer();
            for (int index = 0; index < last.length(); index++) {
                char ci = last.charAt(index);
                if (ci == c) {
                    count += 1;
                } else {
                    if (c != 0) {
                        buffer.append(count).append(c);
                    }
                    c = ci;
                    count = 1;
                }
            }
            buffer.append(count).append(c);
            last = buffer.toString();

            count = 0;
            c = 0;
            i++;
        }
        return last;
    }

    /*int 的越界,让这个函数局限性太大了
     */
    public static String countAndSay1(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 2;
        String last = "1";
        while (i <= n) {
            int num = Integer.parseInt(last);
            last = readNum1(num);
            i++;
        }
        return last;
    }

    /*int 的越界,让这个函数局限性太大了
     */
    private static String readNum1(int n) {
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        int num = -1;
        while (n > 0) {
            int c = n % 10;
            if (num == c) {
                count += 1;
            } else {
                if (num != -1) {
                    buffer.append(num).append(count);
                }
                num = c;
                count = 1;
            }
            n = n / 10;
        }
        buffer.append(num).append(count);
        return buffer.reverse().toString();
    }

    public static void strStrTest() {
        System.out.println(strStr4("abcdefghijklmn", ""));
    }

    /**
     * 实现 strStr()
     * 实现 strStr() 函数。
     * <p>
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     * <p>
     *  
     * <p>
     * 说明：
     * <p>
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     * <p>
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
     */
    /*
    执行用时：4 ms, 在所有 Java 提交中击败了30.31%的
    用户存消耗：36.9 MB, 在所有 Java 提交中击败了79.89%的用户
     */
    public static int strStr5(String haystack, String needle) {
        if (haystack == null) return -1;
        if ("".equals(needle)) return 0;
        int index = 0;
        while (index <= haystack.length() - needle.length()) {
            int force = index;
            int cIndex = 0;
            while (force < haystack.length() && cIndex < needle.length() && haystack.charAt(force) == needle.charAt(cIndex)) {
                cIndex++;
                force++;
            }

            if (cIndex == needle.length()) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /*
    执行用时:1 ms, 在所有 Java 提交中击败了73.84%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了11.63%的用户
     */
    public static int strStr4(String haystack, String needle) {
        if (haystack == null) return -1;
        if ("".equals(needle)) return 0;
        char[] chars = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        int index = 0;
        while (index <= chars.length - needles.length) {
            int force = index;
            int cIndex = 0;
            while (force < chars.length && cIndex < needles.length && chars[force] == needles[cIndex]) {
                cIndex++;
                force++;
            }

            if (cIndex == needles.length) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /*
    执行用时：681 ms, 在所有 Java 提交中击败了5.05%的用户
    内存消耗：38.6 MB, 在所有 Java 提交中击败了18.33%的用户
     */
    public static int strStr3(String haystack, String needle) {
        if (haystack == null) return -1;
        if ("".equals(needle)) return 0;
        char[] chars = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        int index = 0;
        while (index < chars.length) {
            int force = index;
            int cIndex = 0;
            while (force < chars.length && cIndex < needles.length && chars[force] == needles[cIndex]) {
                cIndex++;
                force++;
            }

            if (cIndex == needles.length) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /*
    执行用时：1338 ms, 在所有 Java 提交中击败了5.05%的用户
    内存消耗：38.5 MB, 在所有 Java 提交中击败了28.69%的用户
     */
    public static int strStr2(String haystack, String needle) {
        if (haystack == null) return -1;
        if ("".equals(needle)) return 0;
        char[] chars = haystack.toCharArray();
        char[] needles = needle.toCharArray();
        int index = 0;
        while (index < haystack.length()) {
            int force = index;
            int cIndex = 0;
            while (force < haystack.length() && cIndex < needle.length() && chars[force] == needles[cIndex]) {
                cIndex++;
                force++;
            }

            if (cIndex == needle.length()) {
                return index;
            }
            index++;
        }
        return -1;
    }

    //超出时间限制
    public static int strStr1(String haystack, String needle) {
        if (haystack == null) return -1;
        if ("".equals(needle)) return 0;
        int index = 0;
        while (index < haystack.length()) {
            int force = index;
            int cIndex = 0;
            while (force < haystack.length() && cIndex < needle.length() && haystack.charAt(force) == needle.charAt(cIndex)) {
                cIndex++;
                force++;
            }

            if (cIndex == needle.length()) {
                return index;
            }
            index++;
        }
        return -1;
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
        System.out.println(myAtoi("  -42"));
//        System.out.println(myAtoi("9223372036854775808"));
//        System.out.println(myAtoi("-91283472332"));
    }

    public static int myAtoi(String s) {
        boolean haveNum = false;
        int sign = 0;
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                if (sign == 0) {
                    sign = 1;
                }
                num = num * 10 + (c - '0') * sign;
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
