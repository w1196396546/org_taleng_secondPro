package org.java.util;

public class HtmlText {

    /**
     * 邮箱注册验证码
     * @param code
     * @return
     */
    public static String getText(Integer code){
        return "邮箱注册验证码:"+code+"，此验证码用于用户注册，请勿转发给他人，否则将导致账号被盗或财产损失风险。【母婴商城】";

    }

    /**
     * 注册成功验证码
     * @param email
     * @param pwd
     * @return
     */
    public static String getRegisterSuccessCode(String email,String pwd){
        String msg="尊敬的用户，欢迎您成为我们的会员，您的账号是:"+email+"，您的密码是:"+pwd+" ，" +
                "请您务必保管好您的账号，请勿将账号密码转发给其他人。";
        return msg;
    }

    public static String getBackPwd(int code) {
        return "找回密码验证码:"+code+"，此验证码用于用户找回密码，请勿转发给他人，否则将导致账号被盗或财产损失风险。【母婴商城】";

    }
}
