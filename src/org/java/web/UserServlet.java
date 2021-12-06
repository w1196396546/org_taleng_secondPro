package org.java.web;

import org.java.service.UserService;
import org.java.util.GetProperties;
import org.java.util.HtmlText;
import org.java.util.JavaMailUtil;
import org.java.util.MD5;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/***
 * 用户的操作全部都在这里面
 * 包括，显示购物车，结算
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private ApplicationContext cxt=new ClassPathXmlApplicationContext("applicationContent.xml");

    protected void showCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 这是忘记密码检测用户名是否存在
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void backPwdcheckEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1);
        String email = request.getParameter("email");
        System.out.println(email);
        String newemail = MD5.MD5Email(email);
        UserService userService= (UserService) cxt.getBean("userService");
        int count = userService.getCount(newemail);
        System.out.println(count);
        if (count==0){
            PrintWriter out = response.getWriter();
            out.write("用户名不存在!");
            out.flush();
            out.close();
        }
    }

    /**
     * 用户名存在，进行验证码验证
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void backPwdSendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1);
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        try {
            //将得到的邮箱，指定到要发送的邮箱地址
            JavaMailUtil.receiveMailAccount=email;
            //调用静态方法，得到properties邮箱属性文件
            Properties prop = GetProperties.getProp();
            //根据会话创建session
            Session session=Session.getInstance(prop);
//        设置debug，可以查看详细的发送log
            session.setDebug(true);
            //得到随机验证码
            int code=(int)((Math.random()*9+1)*100000);
            //通过封装好的静态方法，得到需要发送的邮箱内容
            String msg = HtmlText.getText(code);

            //得到邮箱对象
            MimeMessage message = JavaMailUtil.creatMimeMessage(session, JavaMailUtil.emailAccount, JavaMailUtil.receiveMailAccount, msg);
//        根据session获取邮件传输对象
            Transport transport=session.getTransport();
            //使用邮箱账号和密码连接邮箱服务器emailAccount必须与message中的发件人邮箱一致，否则报错
            transport.connect(JavaMailUtil.emailAccount,JavaMailUtil.emailPassword);
//            发送邮件,发送所有收件人地址
            transport.sendMessage(message,message.getAllRecipients());
            //关闭连接
            transport.close();
            //将验证码存放到session中
            request.getSession().setAttribute("code",code);

            out.write("true");
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.write("验证码发送失败!");
        }
    }
}
