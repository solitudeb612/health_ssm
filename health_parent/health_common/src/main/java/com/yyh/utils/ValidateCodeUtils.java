package com.yyh.utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ValidateCodeUtils {
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    /**
     * 随机生成验证码
     *
     * @param length 长度为4位或者6位
     * @return
     */
    public static Integer generateValidateCode(int length) {
        Integer code = null;
        if (length == 4) {
            code = new Random().nextInt(9999);
            if (code < 1000) {
                code += 1000;
            }
        } else if (length == 6) {
            code = new Random().nextInt(999999);
            if (code < 100000) {
                code = code + 100000;
            }
        } else {
            throw new RuntimeException("只能生成4位或6位数字验证码");
        }
        return code;
    }

    /**
     * 随机生成指定长度字符串验证码
     * @param length 长度
     * @return
     */

    //这个方法虽然快
    //这个方法有很大的弊端的潜在的bug，1.将整型的随机数转16进制来生成的带字母和数字的字符串，字母的返回只能在a-f,
    //                           2.随机数产生为10,但是想生成4位的随机码会报错。
    @Deprecated
    public static String generateValidateCodeString(int length) {
        Random random = new Random();
        String hashcode = Integer.toHexString(random.nextInt()); //b7a6ed96
        String capstr = hashcode.substring(0, length); //b7a6 (length为4）
        return capstr;
    }

    /**
     * 生成随机字符
     * @param length
     * @return
     */
    public static String generateRandomString(int length){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    /**
     * 生成随机字符，更优雅的写法
     * @param length
     * @return
     */
    public static String generateStringCode(int length){
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++){
            int randomValue = secureRandom.nextInt(62); // 生成 0 到 61 之间的随机数
            char randomChar;
            if (randomValue < 10) {
                randomChar = (char) (randomValue + '0'); // 数字 0-9
            } else if (randomValue < 36) {
                randomChar = (char) (randomValue - 10 + 'A'); // 大写字母 A-Z
            }else {
                randomChar = (char) (randomValue - 36 + 'a'); // 小写字母 a-z
            }
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
