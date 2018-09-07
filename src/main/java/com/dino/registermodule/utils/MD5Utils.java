package com.dino.registermodule.utils;


import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具类
 *
 * @author xiewanqiang
 */
public class MD5Utils {

    public static void main(String[] args) {
        System.out.println(MD5Utils.hash("123456"));
        // System.out.println(MD5Utils.getInstance().getFileHash("E:\\software\\2003_Visio_Pro.iso"));
    }

    public static String hash(String source) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return toHexString(md5.digest(source.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String md5Base64(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buf = md5.digest(str.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(buf));
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("argument [" + str + "] can't be md5 and base64");
        }
    }

    private static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    private static char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
}