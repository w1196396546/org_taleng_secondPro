package org.java.web;

import org.java.service.RegisterService;
import org.java.service.UserService;
import org.java.util.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

@WebServlet("/register")
public class RegisterServlet extends BaseServlet {
    private ApplicationContext cxt=new ClassPathXmlApplicationContext("applicationContent.xml");

    /**
     * 验证用户名是否存在，如果存在不允许注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到前台传递的参数
        String email = request.getParameter("email");
        //将邮箱加密
        String md5 = MD5.MD5Email(email);
        UserService userService= (UserService) cxt.getBean("userService");
        int count = userService.getCount(md5);
        System.out.println(count);
        if (count!=0){
            //邮箱已存在
            PrintWriter out = response.getWriter();
            out.write("用户名已存在!");
            out.flush();
            out.close();
        }
    }

    /**
     * 发送邮件验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regUserSendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
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
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println(1);
//        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        System.out.println(email);
        String pwd = request.getParameter("pwd");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
//        System.out.println(pnum);
        int code= (Integer) request.getSession().getAttribute("code");
        System.out.println(code);
        System.out.println(code==pnum);
        if (code==pnum){
            //验证码正确,可以进行注册
            String md5Email = MD5.MD5Email(email);
            String md5Pwd = MD5.MD5Pwd(pwd);
            RegisterService registerService= (RegisterService) cxt.getBean("registerService");
            registerService.addUser(md5Email,md5Pwd);
            System.out.println("注册成功");
            try {
                //将得到的邮箱，指定到要发送的邮箱地址
                JavaMailUtil.receiveMailAccount=email;
                //调用静态方法，得到properties邮箱属性文件
                Properties prop = GetProperties.getProp();
                //根据会话创建session
                Session session=Session.getInstance(prop);
//        设置debug，可以查看详细的发送log
                session.setDebug(true);
                //通过封装好的静态方法，得到需要发送的邮箱内容
                String msg = RegisterSuccess.getRegisterSuccessCode(email, pwd);


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

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            request.setAttribute("error","验证码错误!");
            request.getRequestDispatcher("user/register.jsp").forward(request, response);
        }

    }


    }
