package com.annis.interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.BiConsumer;

public class MyMain {
    public static void main(String[] args) throws Exception {
//        test22();
//        c();
//        find7();
//        completely();
//        add();
//
//        try {
//            findK();
////            findK2();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        findTheFirst();
//        findTwoPrime();
//        statistics();
//        statisticsOne();
//        random();
//        sudoku();//todo 没有做出来
//        niceName();
//        count1();
//        move();
//        statisticsIP();
//        System.out.println(toBinaryString(255));

//        recordError();
//        checkPassword();
//        System.out.println('a' + "   " + (char) ('a' - 32));
//        simplePwd();
//        countBottle();
//        deleteC();
//        team();
//        sortString();
//        findBrothers();
//        huiwen();
//        ipCNumber();
//        int a = 255 << 24;
//        System.out.println(a);
        snakeNumber();
    }
    //HJ43 迷宫问题

    /**
     * 5 5
     * 0 1 0 0 0
     * 0 1 1 1 0
     * 0 0 0 0 0
     * 0 1 1 1 0
     * 0 0 0 1 0
     *
     * @throws IOException
     */
    public static void labyrinth() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[] n_m = line.split(" ");
            int n = Integer.parseInt(n_m[0]);
            int m = Integer.parseInt(n_m[1]);
            int[][] lab = new int[n][m];
            for (int i = 0; i < n; i++) {
                String[] li = br.readLine().split(" ");
                for (int j = 0; j < m; j++) {
                    lab[i][j] = Integer.parseInt(li[j]);
                }
            }
            List<String> ways = new ArrayList<>();
            next(lab, n, m, 0, 0, ways);
        }
    }

    public static int next(int[][] lab, int n, int m, int cN, int cM, List<String> ways) {
        next(lab, n, m, cN - 1, cM, ways);
        next(lab, n, m, cN, cM - 1, ways);

        if (cN >= n || cM >= m) {
            return 0;
        }
        if (lab[cN][cM] == 1) {
            return 0;
        } else {
            if (cN == (n - 1) && cM == (m - 1)) {
                ways.add(String.format("(%d,%d)", cN, cM));
                return 2;
            }
            return 1;
        }
    }

    //HJ35 蛇形矩阵(中等
    public static void snakeNumber() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int n = Integer.parseInt(line);
            int start = 1;
            int bStep = 1;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                start += i;
                bStep++;

                int currentN = start, mStep = bStep;
                for (int j = 0; j < (n - i - 1); j++) {
                    sb.append(currentN).append(" ");
                    currentN += mStep;
                    mStep++;
                }
                sb.append(currentN).append("\n");
            }
            System.out.println(sb);
        }
    }

    //HJ33 整数与IP地址间的转换
    public static void ipCNumber() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.matches("[0-9]*")) {
                long number = Long.parseLong(line);
                StringBuilder sb = new StringBuilder();
                while (number > 0) {
                    long c = number & 0b11111111;
                    number = number >> 8;
                    sb.insert(0, c);
                    if (number > 0) {
                        sb.insert(0, ".");
                    }
                }
                System.out.println(sb);
            } else {
                String[] ips = line.split("\\.");
                long number = 0;
                for (int i = 0; i < ips.length; i++) {
                    number = number << 8;
                    number += Integer.parseInt(ips[i]);
                }
                System.out.print(number);
            }

        }
        //31 6575 7026
        //输入：
        //10.0.3.193
        //167969729
        //输出：
        //167773121
        //10.3.3.193
    }

    //HJ32 密码截取
    public static void huiwen() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int max = 1;
            for (int i = 1; i < line.length() - 1; i++) {
                int mM = 0;
                if (line.charAt(i) == line.charAt(i + 1)) {
                    mM = maxLength(line, i, i + 1);
                }
                if (line.charAt(i - 1) == line.charAt(i + 1)) {
                    int m2 = maxLength(line, i - 1, i + 1);
                    if (mM < m2) {
                        mM = m2;
                    }
                }
                if (mM > max) {
                    max = mM;
                }
            }
            System.out.println(max);
        }
    }

    public static int maxLength(String content, int l, int r) {
        while (l >= 0 && r < content.length() && content.charAt(l) == content.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    //HJ27 查找兄弟单词(困难)
    public static void findBrothers() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[] contents = line.split(" ");
            int count = Integer.parseInt(contents[0]);
            int index = Integer.parseInt(contents[contents.length - 1]);
            String word = contents[contents.length - 2];
            double hexWord = hexWord(word);

            LinkedList<String> brothers = new LinkedList<>();
            for (int i = 1; i < contents.length - 2; i++) {
                if (hexWord == hexWord(contents[i])) {
                    if (word.equals(contents[i])) {
                        continue;
                    }
                    brothers.add(contents[i]);
                }
            }
            System.out.println(brothers.size());
            if (brothers.size() > index) {
                Collections.sort(brothers);
                System.out.println(brothers.get(index - 1));
            }
        }
    }

    public static double hexWord(String word) {
        double hex = 0;
        for (int i = 0; i < word.length(); i++) {
            hex += Math.pow(word.charAt(i), 3);
            hex %= (1e9 + 1);
        }
        return hex;
    }


    //HJ26 字符串排序

    /**
     * 描述
     * 编写一个程序，将输入字符串中的字符按如下规则排序。
     * <p>
     * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
     * <p>
     * 如，输入： Type 输出： epTy
     * <p>
     * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
     * <p>
     * 如，输入： BabA 输出： aABb
     * <p>
     * 规则 3 ：非英文字母的其它字符保持原来的位置。
     * <p>
     * <p>
     * 如，
     * 输入： By?e 输出： Be?y
     * <p>
     * 输入：
     * A Famous Saying: Much Ado About Nothing (2012/8).
     * 输出：
     * A aaAAbc dFgghh: iimM nNn oooos Sttuuuy (2012/8).
     */
    public static void sortString() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (int i = 1; i < chars.length; i++) {
                if (!isWord(chars[i])) {
                    continue;
                }
                int cIndex = i;
                for (int j = i - 1; j >= 0; j--) {
                    if (!isWord(chars[j])) {
                        continue;
                    }
                    if (hexChar(chars[cIndex]) < hexChar(chars[j])) {
                        char cJ = chars[j];
                        chars[j] = chars[cIndex];
                        chars[cIndex] = cJ;
                        cIndex = j;
                    } else break;
                }
            }
            System.out.println(chars);
        }
    }

    public static boolean isWord(char c) {
        if (('a' <= c && 'z' >= c) || ('A' <= c && 'Z' >= c)) {
            return true;
        }
        return false;
    }

    public static int hexChar(char c) {
        int hexC = c - 'A';
        if (hexC >= 32) {
            return hexC - 32;
        }
        return hexC;
    }

    //HJ24 合唱队(较难)
    public static void team() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String count;
        while ((count = br.readLine()) != null) {
            int total = Integer.parseInt(count);
            int[] list = new int[total];
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < s.length; i++) {
                list[i] = Integer.parseInt(s[i]);
            }

            //小 --> 大
            int[] dpL = new int[total];
            for (int i = 0; i < list.length; i++) {
                dpL[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (list[i] > list[j]) {
                        dpL[i] = Math.max(dpL[j] + 1, dpL[i]);
                    }
                }
            }

            //大 --> 小
            int[] dpR = new int[total];
            for (int i = list.length - 1; i >= 0; i--) {
                dpR[i] = 1;
                for (int j = list.length - 1; j > i; j--) {
                    if (list[i] > list[j]) {
                        dpR[i] = Math.max(dpR[j] + 1, dpR[i]);
                    }
                }
            }
            int maxSize = 1;
            for (int i = 0; i < total; i++) {
                maxSize = Math.max(maxSize, dpL[i] + dpR[i] - 1);
            }
            System.out.println(total - maxSize);
        }
    }

    public static void team2() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String count;
        while ((count = br.readLine()) != null) {
            int total = Integer.parseInt(count);
            int[] list = new int[total];
            String[] s = br.readLine().split(" ");
            for (int i = 0; i < s.length; i++) {
                list[i] = Integer.parseInt(s[i]);
            }

            //小 --> 大
            int[] dpL = new int[total];
            for (int i = 0; i < list.length; i++) {
                dpL[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (list[i] > list[j]) {
                        dpL[i] = Math.max(dpL[j] + 1, dpL[i]);
                    }
                }
            }

            //大 --> 小
            int[] dpR = new int[total];
            for (int i = list.length - 1; i >= 0; i--) {
                dpR[i] = 1;
                for (int j = list.length - 1; j > i; j--) {
                    if (list[i] > list[j]) {
                        dpR[i] = Math.max(dpR[j] + 1, dpR[i]);
                    }
                }
            }
            int maxSize = 1;
            for (int i = 0; i < total; i++) {
                maxSize = Math.max(maxSize, dpL[i] + dpR[i] - 1);
            }
            System.out.println(total - maxSize);
        }
    }
    //16
    //1 3 5 7 9 100 2 5 6 1 7 22 10 9 5 3

    //HJ23 删除字符串中出现次数最少的字符(较难)
    public static void deleteC() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            byte[] count = new byte[26];
            char[] chars = line.toCharArray();
            for (char c : chars) {
                count[c - 'a']++;
            }

            byte minCount = -1;
            for (char c : chars) {
                if (minCount == -1) {
                    minCount = count[c - 'a'];
                    continue;
                }
                minCount = count[c - 'a'] < minCount ? count[c - 'a'] : minCount;
            }
            for (char c : chars) {
                if (minCount == count[c - 'a']) {
                    continue;
                }
                System.out.print(c);
            }
            System.out.println();
        }
    }

    //HJ22 汽水瓶(简单)
    public static void countBottle() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            if ("0".equals(line)) {
                break;
            }
            int total2Drink = 0;
            int count = Integer.parseInt(line);
            while (count >= 3) {
                int cSize = count / 3;
                count = cSize + count % 3;
                total2Drink += cSize;
            }
            if (count == 2) {
                total2Drink++;
            }
            System.out.println(total2Drink);
        }
    }

    //HJ21 简单密码(中等)
    public static void simplePwd() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if ('0' <= c && '9' >= c) {
                    continue;
                }
                if (c >= 'A' && c <= 'Z') {
                    if (c == 'Z') {
                        c = 'A';
                    } else {
                        c += 1;
                    }
                    c = (char) (c + 32);
                    chars[i] = c;
                    continue;
                }
                if (c >= 'a' && c <= 'z') {
//                    abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9,
                    if (c <= 'c') {
                        c = '2';
                    } else if (c <= 'f') {
                        c = '3';
                    } else if (c <= 'i') {
                        c = '4';
                    } else if (c <= 'l') {
                        c = '5';
                    } else if (c <= 'o') {
                        c = '6';
                    } else if (c <= 's') {
                        c = '7';
                    } else if (c <= 'v') {
                        c = '8';
                    } else {
                        c = '9';
                    }
                    chars[i] = c;
                }
            }
            //zvbo9441987
            System.out.println(String.copyValueOf(chars));
        }
    }

    //HJ20 密码验证合格程序(较难)

    /**
     * 密码要求:
     * <p>
     * 1.长度超过8位
     * <p>
     * 2.包括大 小写字母.数字.其它符号,以上四种至少三种
     * <p>
     * 3.不能有长度大于2的不含公共元素的子串重复 （注：其他符号不含空格或换行）
     * <p>
     * 输入描述：
     * 一组或多组字符串。每组占一行
     * <p>
     * 输出描述：
     * 如果符合要求输出：OK，否则输出NG
     */
    public static void checkPassword() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            if (chars.length > 8 && hase4Type(chars) && !isRepeat(chars)) {
                System.out.println("OK");
                continue;
            }
            System.out.println("NG");
        }
    }

    public static boolean hase4Type(char[] password) {
        int charCheck = 0;
        for (int i = 0; i < password.length; i++) {
            char c = password[i];
            if ('0' <= c && '9' >= c) {
                charCheck = charCheck | 1 << 2;
            } else if ('A' <= c && 'Z' >= c) {
                charCheck = charCheck | 1;
            } else if ('a' <= c && 'z' >= c) {
                charCheck = charCheck | 1 << 1;
            } else {
                charCheck = charCheck | 1 << 3;
            }
        }
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((charCheck & 1 << i) > 0) {
                count++;
            }
        }
        if (count < 3) {
            return false;
        }
        return true;
    }

    public static boolean isRepeat(char[] password) {
        for (int i = 0; i < (password.length - 3); i++) {
            char[] sub = Arrays.copyOfRange(password, i, i + 3);
            for (int j = 0; j < sub.length; j++) {
                if (password[i + j] != sub[j]) {
                    break;
                }
                if (j + 1 == sub.length) {
                    return true;
                }
            }
        }
        return false;
    }

    //判断是否有相同长度大于2的子串重复,所以取长度为3的子串做比对
    public static boolean isRepeat2(String s) {
        for (int i = 0; i < s.length() - 2; i++) {
            String sonString = s.substring(i, i + 3);
            int count = 0;//子串的个数，如果>1,则不满足要求
            for (int j = 0; j < s.length() - 2; j++) {
                if (s.charAt(j) == sonString.charAt(0) && s.charAt(j + 1) == sonString.charAt(1) && s.charAt(j + 2) == sonString.charAt(2)) {
                    count++;
                }
            }
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    //HJ19 简单错误记录(困难)
    public static void recordError() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedHashMap<String, Integer> errors = new LinkedHashMap<>();
        String line;
        while ((line = br.readLine()) != null && !"".equals(line)) {
            String file[] = line.split(" ");
            String name = file[0].substring(file[0].lastIndexOf("\\") + 1);
            if (name.length() > 16) {
                name = name.substring(name.length() - 16);
            }
            if (errors.containsKey(name + " " + file[1])) {
                Integer integer = errors.get(name + " " + file[1]);
                errors.put(name + " " + file[1], integer + 1);
            } else {
                errors.put(name + " " + file[1], 1);
            }
        }
        int begin = errors.size() - 8;

        errors.forEach(new BiConsumer<String, Integer>() {
            int index = 0;

            @Override
            public void accept(String s, Integer integer) {
                if (index++ >= begin) {
                    System.out.println(s + " " + integer);
                }

            }
        });
    }

    // 替代Integer.toBinaryString()
    public static String toBinaryString(int num) {
        if (num == 0) return "" + 0;
        String result = "";
        // 左面0的个数
        int n = Integer.numberOfLeadingZeros(num);
        num <<= n;
        for (int i = 0; i < 32 - n; ++i) {
            int x = (Integer.numberOfLeadingZeros(num) == 0) ? 1 : 0;
            result += x;
            num <<= 1;
        }
        return result;
    }

    //    HJ18 识别有效的IP地址和掩码并进行分类统计(困难)
    public static void statisticsIP() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int[] count = new int[7];
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            String[] ip_mask = line.split("~");
            if (ip_mask.length != 2) {
                count[5]++;
                continue;
            }
            int valid = 1, type = 0;
            boolean isPrivate = false;
            String[] ips = ip_mask[0].split("\\.");
            for (int i = 0; i < ips.length; i++) {
                String ip = ips[i];
                if ("".equals(ip)) {
                    valid = -1;
                    break;
                }
                int ipInt = Integer.parseInt(ip);
                if (ipInt < 0 || ipInt > 255) {
                    valid = -1;
                    break;
                }
                if (i == 0) {
                    if ((ipInt == 0) || ipInt == 127) {
                        valid = 0;
                        break;
                    } else if (ipInt <= 126) {
                        type = 0;
                        if (ipInt == 10) {
                            isPrivate = true;
                        }
                    } else if (ipInt <= 191) {
                        type = 1;
                        if (ipInt == 172) {
                            isPrivate = true;
                        }
                    } else if (ipInt <= 223) {
                        type = 2;
                        if (ipInt == 192) {
                            isPrivate = true;
                        }
                    } else if (ipInt <= 239) {
                        type = 3;
                    } else if (ipInt <= 255) {
                        type = 4;
                    }
                }
            }
            String[] mask = ip_mask[1].split("\\.");
            for (String s : mask) {
                if (!isValidMask(s)) {
                    valid = -1;
                    break;
                }
            }
            if (valid == 0) {
                continue;
            } else if (valid == -1) {
                count[5]++;
                continue;
            }
            if (isPrivate) {
                count[6]++;
            }
            count[type]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : count) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    public static boolean isValidMask(String mask) {
        String[] maskTable = mask.split("\\.");
        //mask转为32位2进制字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maskTable.length; i++) {
            maskTable[i] = Integer.toBinaryString(Integer.parseInt(maskTable[i]));
            if (maskTable[i].length() < 8) {//不足8位补齐0
                for (int j = 0; j < 8 - maskTable[i].length(); j++) {
                    sb.append("0");//补完零
                }
                sb.append(maskTable[i]);//再添加转换的2进制串
            } else {
                sb.append(maskTable[i]);//刚好8位直接添加，因为之前已经判断过ip的有效性，所以不可能超过8位
            }
        }
        //最后一个1在第一个0之前，有效，否则无效
        return sb.toString().lastIndexOf("1") < sb.toString().indexOf("0");
    }

    //HJ17 坐标移动(较难)
    public static void move() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int x = 0, y = 0;
            String[] steps = line.split(";");
            for (int i = 0; i < steps.length; i++) {
                String step = steps[i];
                if (step.length() <= 1) {
                    continue;
                }
                char d = step.charAt(0);
                if ('A' == d || 'D' == d || 'W' == d || 'S' == d) {
                    try {
                        int stepSize = Integer.parseInt(step.substring(1));
                        if ('A' == d) {
                            x -= stepSize;
                        } else if ('D' == d) {
                            x += stepSize;
                        } else if ('W' == d) {
                            y += stepSize;
                        } else if ('S' == d) {
                            y -= stepSize;
                        }
                    } catch (RuntimeException ignore) {
                        continue;
                    }
                }
            }
            System.out.println(x + "," + y);
        }
    }

    //HJ15 求int型正整数在内存中存储时1的个数(入门)
    public static void count1() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int number = Integer.parseInt(line);
            int count = 0;
            while (number > 0) {
                count += (number % 2 == 0 ? 0 : 1);
                number = number >> 1;
            }
            System.out.println(count);
        }
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextInt()) {
//            int number = scanner.nextInt();
//            int count = 0;
//            while (number > 0) {
//                count += (number % 2 == 0 ? 0 : 1);
//                number = number >> 1;
//            }
//            System.out.println(count);
//        }
    }

    //HJ45 名字的漂亮度
    public static void niceName() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int count = scanner.nextInt();
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                int b = 0;
                for (int j = 0; j < name.length(); j++) {
                    char a = name.charAt(j);
                    if ('a' <= a && 'z' >= a) {
                        b += (a - 'a' + 1);
                    } else {
                        b += (a - 'A' + 1);
                    }
                }
                System.out.println(b);
                count--;
                if (count == 0) {
                    break;
                }
            }
        }
    }
    //HJ44 Sudoku

    /*
    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/sudoku-solver/solution/jie-shu-du-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }

    public static void sudoku() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[][] array = new String[9][9];
            array[0] = line.split(" ");
            for (int i = 1; i < 9; i++) {
                array[i] = br.readLine().split(" ");
            }
            fillTheNumber(array);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (String s : array[i]) {
                    sb.append(s).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }

    private static void fillTheNumber(String[][] array) {
        System.out.println("fillTheNumber 调用~~~~~");
        int emptySize = 0;
        for (int i = 0; i < 9; i++) {//行
            for (int j = 0; j < 9; j++) {//列
                handNext:
                if ("0".equals(array[i][j]) || array[i][j].length() > 1) {//为初始状态或者长度大于1
                    if ("0".equals(array[i][j])) array[i][j] = "123456789";
                    emptySize++;
                    //当前行中 遍历列
                    for (int row = 0; row < 9; row++) {
//                        if (j == row) {
//                            continue;
//                        }
//                        if ("0".equals(array[i][row])) {
//                            continue;
//                        }
                        if (array[i][row].length() > 1) {
                            continue;
                        }
                        array[i][j] = array[i][j].replace(array[i][row], "");
                        if (array[i][j].length() == 1) {
                            emptySize--;
                            break handNext;
                        }
                    }
                    //当前列中 遍历行
                    for (int line = 0; line < 9; line++) {
//                        if (i == line) {
//                            continue;
//                        }
//                        if ("0".equals(array[line][j])) {
//                            continue;
//                        }
                        if (array[line][j].length() > 1) {
                            continue;
                        }
                        array[i][j] = array[i][j].replace(array[line][j], "");
                        if (array[i][j].length() == 1) {
                            emptySize--;
                            break handNext;
                        }
                    }
                    //遍历小方块
                    int sX = i / 3, sY = j / 3;
                    for (int r = sX * 3; r < sX * 3 + 3; r++) {
                        for (int l = sY * 3; l < sY * 3 + 3; l++) {
//                            if (i == r && j == l) {
//                                continue;
//                            }
//                            if ("0".equals(array[r][l])) {
//                                continue;
//                            }
                            if (array[r][l].length() > 1) {
                                continue;
                            }
                            array[i][j] = array[i][j].replace(array[r][l], "");
                            if (array[i][j].length() == 1) {
                                emptySize--;
                                break handNext;
                            }
                        }
                    }
                }
            }
        }
        if (emptySize > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (String s : array[i]) {
                    sb.append(s).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            fillTheNumber(array);
        }
    }

    //HJ3 明明的随机数
    public static void random() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int count = sc.nextInt();
            int[] array = new int[count];
            int times = 0;
            while (sc.hasNextInt() && times < count) {
                times++;
                int num = sc.nextInt();
                insert(num, array);
                if (count == times) {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int temp : array) {
                if (temp > 0) {
                    sb.append(temp).append("\n");
                } else break;
            }
            System.out.print(sb);
        }
    }

    private static void insert(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (num == array[i] || array[i] == 0) {
                array[i] = num;
                return;
            } else if (num < array[i]) {
                int temp = array[i];
                array[i] = num;
                num = temp;
                if (num == 0) {
                    return;
                }
            }
        }
    }

    //HJ2 计算某字符出现次数
    private static void statisticsOne() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            char[] chars = line.toCharArray();
            short[] counts = new short[128];
            for (char c : chars) {
                counts[c]++;
            }
            char findC = br.readLine().charAt(0);
            if ('a' <= findC && 'z' >= findC) {
                System.out.println(counts[findC] + counts[findC + ('A' - 'a')]);
            } else if ('A' <= findC && 'Z' >= findC) {
                System.out.println(counts[findC] + counts[findC + ('a' - 'A')]);
            } else {
                System.out.println(counts[findC]);
            }
        }
    }

    //HJ102 字符统计
    private static void statistics() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            short[] charS = new short[128];
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                charS[c]++;
            }

            List<Character> c_c = new ArrayList<>();
            for (int i = '0'; i <= '9'; i++) {
                if (charS[i] > 0) c_c.add((char) i);
            }
            for (int i = 'a'; i <= 'z'; i++) {
                if (charS[i] > 0) c_c.add((char) i);
            }
            for (int i = 'A'; i <= 'Z'; i++) {
                if (charS[i] > 0) c_c.add((char) i);
            }

            c_c.sort(new Comparator<Character>() {
                @Override
                public int compare(Character o1, Character o2) {
                    int c = charS[o2] - charS[o1];
                    if (c == 0) {
                        return o1 - o2;
                    }
                    return c;
                }
            });
            for (Character c : c_c) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    //HJ61 放苹果
    private static void putApple() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            String[] params = line.split(" ");
            int size = Integer.parseInt(params[0]), group = Integer.parseInt(params[1]);
            for (int i = size; i > size / 2; i--) {

            }
        }
    }

    //HJ60 查找组成一个偶数最接近的两个素数
    public static void findTwoPrime() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            int number = Integer.parseInt(line);
            for (int i = number / 2; i > 0; i--) {
                if (isPrime(i) && isPrime(number - i)) {
                    System.out.println(i);
                    System.out.println(number - i);
                    break;
                }
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num < 3) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i < num; i = i + 2) {
            if (num % i == 0) return false;
        }
        return true;
    }

    //HJ59 找出字符串中第一个只出现一次的字符
    private static void findTheFirst() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = br.readLine()) != null) {
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (line.indexOf(c) == line.lastIndexOf(c)) {
                    System.out.println(c);
                    break;
                } else if (i == line.length() - 1) {
                    System.out.println(-1);
                }
            }
        }
    }

    public static void mainK() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        while ((str = br.readLine()) != null) {
            if (str.equals("")) continue;
            String[] params = str.split(" ");
            int n = Integer.parseInt(params[0]), k = Integer.parseInt(params[1]);
            int[] res = new int[n];
            int start = 0, index = 0;
            if (params.length > 2) start = 2;
            else params = br.readLine().split(" ");
            for (int i = start; i < params.length; i++) {
                res[index++] = Integer.parseInt(params[i]);
            }
            Arrays.sort(res);
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < k; i++) ans.append(res[i]).append(" ");
            System.out.println(ans.toString().trim());
        }
    }

    //HJ58 输入n个整数，输出其中最小的k个
    private static void findK2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = br.readLine()) != null) {
            if (str.equals("")) continue;
            String[] params = str.split(" ");
            int n = Integer.parseInt(params[0]), k = Integer.parseInt(params[1]);
            int[] res = new int[n];
            params = br.readLine().split(" ");
            for (int i = 0; i < params.length; i++) {
                res[i] = Integer.parseInt(params[i]);
            }
            Arrays.sort(res);
            StringBuilder ans = new StringBuilder();
            for (int i = 0; i < k; i++) ans.append(res[i]).append(" ");
            System.out.println(ans.toString().trim());
        }
    }

    private static void findK() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            String[] params = readLine.split(" ");
            int size = Integer.parseInt(params[0]), k = Integer.parseInt(params[1]);
            params = bufferedReader.readLine().split(" ");
            int[] numbers = new int[size];
            for (int i = 0; i < size; i++) {
                numbers[i] = Integer.parseInt(params[i]);
            }
            Arrays.sort(numbers);
            for (int i = 0; i < k; i++) {
                System.out.print(numbers[i] + ((i == k - 1) ? "" : " "));
            }
            System.out.println();
        }

    }

    //    HJ57 高精度整数加法
    /*
09876543210
1234567890
     */
    private static void add() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            char[] num1 = sc.next().toCharArray();
            char[] num2 = sc.next().toCharArray();
            int[] result = new int[Math.max(num1.length, num2.length) + 1];
            int index = 0;
            int temp = 0;
            while (index < num1.length || index < num2.length) {
                char a = '0', b = '0';
                if (index < num1.length) {
                    a = num1[num1.length - index - 1];
                }
                if (index < num2.length) {
                    b = num2[num2.length - index - 1];
                }
                temp = temp + a + b - '0' - '0';
                result[index] = temp % 10;
                temp = temp / 10;
                index++;
            }
            if (temp != 0) {
                System.out.print(temp);
            }
            for (int i = result.length - 2; i >= 0; i--) {
                System.out.print(result[i]);
            }
            System.out.println();
        }
    }

    //    HJ56 完全数计算    6 28 496 8128
    private static void completely() {
        Scanner sc = new Scanner(System.in);
        List<Integer> compList = new ArrayList<>();
        while (sc.hasNextInt()) {
            int number = sc.nextInt();
            Integer maxNumber = 0;
            if (compList.size() > 0) {
                maxNumber = compList.get(compList.size() - 1);
            }
            if (number > maxNumber) {
                for (int i = maxNumber + 1; i <= number; i++) {
                    int total = 1;
                    for (int i1 = 2; i1 < i; i1++) {
                        if (i % i1 == 0) {
                            total += i1;
                        }
                    }
                    if (total == i) {
                        compList.add(i);
                    }
                }
                System.out.println(compList.size());
            } else {
                int lastIndex = 0;
                for (int i = 0; i < compList.size(); i++) {
                    if (compList.get(i) < number) {
                        lastIndex = i;
                    }
                }
                System.out.println(lastIndex + 1);
            }
        }
    }

    //HJ55 挑7
    private static void find7() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int number = sc.nextInt();

            int count = 0;
            for (int i = 1; i <= number; i++) {
                if (i % 7 == 0) {
                    count++;
                    continue;
                }
                int temp = i;
                while (temp > 0) {
                    if (temp % 10 == 7) {
                        count++;
                        temp = 0;
                    }
                    temp /= 10;
                }
            }
            System.out.println(count);
        }
    }

    //HJ54 表达式求值
    private static void c() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            List<Object> numbers = getFormula(next);
            Long result = calculate(numbers);
            System.out.println(result);
        }
    }

    private static Long calculate(List<Object> numbers) {
        Stack<Long> numberStack = new Stack<>();
        for (int i = 0; i < numbers.size(); i++) {
            Object o = numbers.get(i);
            if (o instanceof Long) {
                numberStack.push((Long) o);
            } else {
                Long b = numberStack.pop();
                Long a = numberStack.pop();

                numberStack.push(calculate(a, b, (Character) o));
            }
        }
        return numberStack.pop();
    }

    private static Long calculate(Long a, Long b, Character sign) {
        if ('-' == sign) {
            return a - b;
        }
        if ('+' == sign) {
            return a + b;
        }
        if ('/' == sign) {
            return a / b;
        }
        if ('*' == sign) {
            return a * b;
        }
        return 0L;
    }

    private static List<Object> getFormula(String arithmetic) {
        HashMap<Character, Integer> priority = new HashMap<>();
        priority.put('-', 1);
        priority.put('+', 1);
        priority.put('*', 3);
        priority.put('/', 3);
        priority.put('(', 5);
        priority.put(')', 5);
        List<Object> numbers = new ArrayList<>();
        Stack<Character> signs = new Stack<>();
        int sign = 0;
        long lastNumber = 0;
        for (int i = 0; i < arithmetic.length(); i++) {
            char cI = arithmetic.charAt(i);
            if ('-' == cI || '+' == cI || '*' == cI || '/' == cI || '(' == cI || ')' == cI) {
                if ('-' == cI && (i == 0 || '(' == arithmetic.charAt(i - 1))) {
                    sign = -1;
                } else {
                    if (sign != 0) {
                        numbers.add((sign < 0 ? -1 : 1) * lastNumber);
                        lastNumber = 0;
                        sign = 0;
                    }
                    if ('(' == cI) {
                        signs.push(cI);
                    } else if (')' == cI) {
                        while ('(' != signs.peek()) {
                            numbers.add(signs.pop());
                        }
                        signs.pop();
                    } else {
                        if (signs.empty()) {
                            signs.push(cI);
                        } else {
                            while (!signs.empty() && signs.peek() != '(' && priority.get(signs.peek()) >= priority.get(cI)) {
                                numbers.add(signs.pop());
                            }
                            signs.push(cI);
                        }
                    }
                }
            } else {
                if (sign == 0) {
                    sign = 1;
                }
                lastNumber = lastNumber * 10 + (cI - '0');
            }
        }
        if (sign != 0) {
            numbers.add((sign < 0 ? -1 : 1) * lastNumber);
        }
        while (!signs.empty()) {
            numbers.add(signs.pop());
        }
        return numbers;
    }

    //提交结果：答案正确 运行时间：72ms 占用内存：12612KB 使用语言：Java
    public static void test22() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            test2(inputString);
        }
    }

    //运行时间：72ms 超过10.96% 用Java提交的代码 占用内存：12240KB 超过9.91%用Java提交的代码

    //运行时间：49ms 超过35.18% 用Java提交的代码 占用内存：10876KB 超过32.61%用Java提交的代码

    //运行时间：38ms 超过56.14% 用Java提交的代码 占用内存：11000KB 超过30.43%用Java提交的代码
    public static void test2(String inputString) {

        if (inputString == null) {
            return;
        }
        if (inputString.length() == 1) {
            System.out.println(inputString);
        }
        char[] chars = inputString.toCharArray();
        chars.toString();
        Arrays.sort(chars);

//        for (int i = 0; i < chars.length; i++) {
//            System.out.print(chars[i]);
//        }
        System.out.println(chars);
        //Ihave1nose2hands10fingers
        //0112Iaadeeefghhinnnorsssv
    }

    public static void test1() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        StringBuffer rs = new StringBuffer(str);
        rs.reverse();
        System.out.println(rs);
    }
}

