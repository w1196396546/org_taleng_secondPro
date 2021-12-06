package org.java.util;

public class RegisterSuccess {

    public static String getRegisterSuccessCode(String email,String pwd){
        String msg="尊敬的用户，欢迎您成为我们的会员，您的账号是:"+email+"，您的密码是:"+pwd+" ，" +
                "请您务必保管好您的账号，请勿将账号密码转发给其他人。";
        return msg;
    }
}
