package com.example.springunity.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5Util {
    private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 私有构造方法,将该工具类设为单例模式.
     */
    private MD5Util() {
    }

    private static final String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String encode(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] byteArray = md5.digest(password.getBytes(StandardCharsets.UTF_8));
            return byteArrayToHexString(byteArray);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return password;
    }

    private static String byteArrayToHexString(byte[] byteArray) {
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
            sb.append(byteToHexChar(b));
        }
        return sb.toString();
    }

    private static Object byteToHexChar(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hex[d1] + hex[d2];
    }
}
