package com.annis.interview;

public class StringTest {
    public static void main(String[] args) {
        segmentSlit();
    }

    private static void segmentSlit() {
        String content = "abcefghuhuefghabc";
        int count = solve(content, 0, content.length() - 1);

        System.out.printf("%s 可以被分成%d段%n", content, count);
    }

    private static final long MOD = (long) (1e9 + 7);

    private static int solve(String src, int left, int right) {
        if (left > right) {
            return 0;
        }
        long hexLeft = 0, hexRight = 0;
        for (int l = left, r = right; l < r; l++, r--) {
            hexLeft = (hexLeft * 26 + (src.charAt(l) - 'a')) % MOD;
            hexRight = (((src.charAt(r) - 'a') * (26 ^ (right - r) % MOD)) + hexRight) % MOD;
            if (hexLeft == hexRight && segmentEqual(src, left, l, r, right)) {
                return 2 + solve(src, l + 1, r - 1);
            }
        }
        return 1;
    }

    private static boolean segmentEqual(String src, int left, int l, int r, int right) {
        for (int step = 0; step <= l - left; step++) {
            if (src.charAt(step + left) != src.charAt(r + step)) {
                return false;
            }
        }
        return true;
    }
}














