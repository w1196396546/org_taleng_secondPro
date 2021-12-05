package org.java.util;

import java.util.Properties;

public class GetProperties {


    public static Properties getProp(){
        //创建属性类
        Properties prop=new Properties();
        //开启debug调试
        prop.setProperty("mail.debug","true");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 设置右键服务器的主机名
        prop.setProperty("mail.host", JavaMailUtil.emailSMTPHost);
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");

        return prop;
    }
}
