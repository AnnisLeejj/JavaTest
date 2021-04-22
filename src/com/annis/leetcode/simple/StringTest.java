package com.annis.leetcode.simple;

import java.util.Arrays;
import java.util.HashMap;

public class StringTest {
    public static void main(String[] args) {
        //有效的字母异位词
        // isAnagramTest();
        //验证回文串
        isPalindromeTest();
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
    public static boolean isPalindrome3(String s) {
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
