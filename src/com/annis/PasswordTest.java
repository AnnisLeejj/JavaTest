package com.annis;

public class PasswordTest {

    public static void main(String[] args) {
        String password = "666665";
        System.out.println("isSimple:" + isSimple(password));
    }

    private static boolean isSimple(String password) {
        if (password == null || password.length() == 0) return true;
        if (password.length() > 9) return false;
        //包含了数组和字母后， 再判断是否包含这些组合，包含并且长度小于等于9，认为是简单密码
        String[] simpleArray = new String[]{"111111", "222222", "333333", "444444",
                "555555", "666666", "777777", "888888", "999999", "000000", "012345", "123456", "234567",
                "345678", "456789", "567890", "098765", "987654", "876543", "765432", "654321", "543210",
                "012012", "123123", "234234", "345345", "456456", "567567", "678678", "789789", "987987",
                "876876", "765765", "654654", "543543", "432432", "321321", "210210",
                "qwerty", "asdfgh", "zxcvbn", "abcdef", "001122", "112233", "223344", "334455", "445566",
                "556677", "667788", "778899", "889900", "990011", "aabbcc" };

        for (String regular : simpleArray) {
            if (password.contains(regular)) {
                return true;
            }
        }

        return false;
    }
}
