package com.annis.interview;

import java.util.Scanner;

public class Huawei {
    public static void main(String[] args) {
//        theBackwardsK();
//        System.out.println(ways2zBottomRight(10,5));
        stringD();
    }

    //HJ52 计算字符串的距离
    /*
abcdefg
abcdef
abcde
abcdf
abcde
bcdef
     */
    private static void stringD() {
//        stringDStudy();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = sc.next();
            int[][] dp = new int[s1.length() + 1][s2.length() + 1];
            dp[0][0] = 0;
            for (int i = 0; i <= s1.length(); i++) {
                for (int j = 0; j <= s2.length(); j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = i + j;
                    } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    }
                }
            }
            System.out.println(dp[s1.length()][s2.length()]);
        }
    }

    private static void stringDStudy() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.next();
            String s2 = sc.next();
            int dp[][] = new int[s1.length() + 1][s2.length() + 1];
            dp[0][0] = 0;
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = i + j;
                    } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j] + 1));
                    }
                }
            }
            System.out.println(dp[s1.length()][s2.length()]);
        }
    }

    //动态规划
    public static int ways2zBottomRight(int r, int l) {
        int[][] ways = new int[r][l];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < l; j++) {
                if (i == 0 || j == 0) {
                    ways[i][j] = 1;
                } else {
                    ways[i][j] = ways[i - 1][j] + ways[i][j - 1];
                }
            }
        }
        return ways[r - 1][l - 1];
    }

    //HJ51 输出单向链表中倒数第k个结点
    private static void theBackwardsK() {
        Scanner scanner = new Scanner(System.in);

        Node head = null, lastNode = null;
        while (scanner.hasNext()) {
            int length = scanner.nextInt();
            for (int i = 0; i < length; i++) {
                Node node = new Node(scanner.nextInt());
                if (i == 0) {
                    head = node;
                } else {
                    lastNode.next = node;
                }
                lastNode = node;
            }
            int k = scanner.nextInt();
            findBackwardsK(head, length, k);
        }
    }

    private static void findBackwardsK(Node head, int length, int k) {
        if (k < 1 || k > length) {
            System.out.println(0);
            return;
        }

        int index = length - k;
        int times = 0;
        Node node = head;
        while (times != index) {
            node = node.next;
            times++;
        }

        System.out.println(node.value);
    }

    // 运行时间 55ms 占用内存 12624KB
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
