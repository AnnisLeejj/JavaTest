package com.annis.test;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class StringTest {
    public static void main(String[] args) {
//        test1();
        test2();
//        hashTest();
    }

    //本来是(A*B+c)%MOD  -----怎么等价的?----->(A*(B%MOD)+c)%MOD
    //如 (17*31 +7)%11 :::   (17*(31%11)+7)%11  -> (17*22 + 17*9 + 7)%11 -> ((17*22)%11+ (17*9)%11 + 7)%11
    //             ---->(17*9 +7)%11

    private static void hashTest() {
        Random random = new Random();
        int times = 100_0000;
        long mod = (long) (1e9 + 7);
        for (int i = 0; i < times; i++) {
            int A = random.nextInt(1000);
            int B = random.nextInt(1000);
            int c = random.nextInt(500);

            long valueA = (A * B + c) % mod;
            long valueB = (A * (B % mod) + c) % mod;
//            System.out.printf("---第%d次计算---",i);
            System.out.println();
            if (valueA != valueB) {
                System.out.printf("第%d次计算  valueA:%d   valueB:%d      结果%s", i, valueA, valueB, "不相等");
            }else{
                System.out.printf("第%d次计算  valueA:%d   valueB:%d      结果%s", i, valueA, valueB, "相等");
            }
        }
    }

    private static void test2() {
        segmentSlit();
    }

    //最大回文段
    private static void segmentSlit() {
        String content = "eregavzdx";
//        String content = "abcefghuhyhuefghabc";
        int count = solve(content, 0, content.length() - 1);

        System.out.printf("%s 可以被分成%d段%n", content, count);
    }

    //    private static final long MOD = 4;
    private static final long MOD = (long) (1e9 + 7);

    private static int solve(String src, int left, int right) {
        if (left > right) {
            return 0;
        }
        long hexLeft = 0, hexRight = 0;
        for (int l = left, r = right; l < r; l++, r--) {
            hexLeft = (hexLeft * 26 + (src.charAt(l) - 'a')) % MOD;
            //todo Math.pow(26, right - r) % MOD   这里的MOD才是整个算法的经典~~~~~~~~~~~~~~
            //本来是(A*B+c)%MOD  -----怎么等价的?----->(A*(B%MOD)+c)%MOD      核心:  A* (B%MOD)
            long valueRight = (long) (((src.charAt(r) - 'a') * (Math.pow(26, right - r) % MOD)) + hexRight);
            hexRight = valueRight % MOD;
            //用例足够大(或者MOD值更小)的时候哈希冲突的可能就更大,所以需要进一步确定
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

    private static void test1() {
        String data = "T2020-11-1000:00:00";
        System.out.println("jieguo :" + data.substring(0, data.indexOf("T")));

        List<String> list;
    }
}
