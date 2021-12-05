package org.java.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    public static String MD5Email(String userEmail){
        byte[] bytes = userEmail.getBytes();
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            BASE64Encoder coder=new BASE64Encoder();
            String email = coder.encode(digest);
            return email;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
    public static String MD5Pwd(String userPwd){
        byte[] bytes = userPwd.getBytes();
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            BASE64Encoder coder=new BASE64Encoder();
            String pwd = coder.encode(digest);
            return pwd;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
