package org.java.web;

import com.alibaba.fastjson.JSON;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserInfo;
import org.java.entity.UserShoppingCart;
import org.java.service.UserService;
import org.java.util.GetProperties;
import org.java.util.HtmlText;
import org.java.util.JavaMailUtil;
import org.java.util.MD5;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

/***
 * 用户的操作全部都在这里面
 * 包括，显示购物车，结算
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {
    private ApplicationContext cxt=new ClassPathXmlApplicationContext("applicationContent.xml");
    UserService userService= (UserService) cxt.getBean("userService");
    protected void showCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * 找回密码
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
     * 找回密码
     * 用户名存在，发送验证码进行验证
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
            String msg = HtmlText.getBackPwd(code);

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

    /**
     * 找回密码
     * 验证码正确
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkIdentity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        int pnum = Integer.parseInt(request.getParameter("pnum"));
        int code=(Integer) request.getSession().getAttribute("code");
        System.out.println(code==pnum);
        if (code==pnum){
            //验证正确，可以修改密码
            String md5Email = MD5.MD5Email(email);
            request.setAttribute("email",md5Email);
            request.getRequestDispatcher("backPwd/checkIdentity.jsp").forward(request, response);
        }else {
            //验证码错误
            request.setAttribute("error","验证错误!");
            request.getRequestDispatcher("../user/backPwd.jsp").forward(request, response);
        }
    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void setPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到前台传过来的账号密码
        String email = request.getParameter("email");
        if (email==null||email.equals("")){
            response.sendRedirect("index.jsp");
        }
        String pwd = request.getParameter("pwd");
        System.out.println("email:"+email);
        System.out.println("pwd:"+pwd);
        //加密
        String md5Pwd = MD5.MD5Pwd(pwd);
        userService.updatePwd(email,md5Pwd);
        response.sendRedirect("user/login.jsp");

    }

        /**
         * 用户登录
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1);
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        System.out.println(email);
        System.out.println(pwd);
        String md5Email = MD5.MD5Email(email);
        String md5Pwd = MD5.MD5Pwd(pwd);
        int count = userService.getLoginCheck(md5Email, md5Pwd);
        System.out.println(count);
        if (count>0){
            System.out.println("可以登录");
            UserInfo user = userService.getUser(md5Email, md5Pwd);
            System.out.println(user.getUserPwd());
            System.out.println(user.getUserEmail());
            request.getSession().setAttribute("user",user);
            Cookie cookie=new Cookie("user",user.getUserEmail() );
            System.out.println("cookie的email"+user.getUserEmail());
            cookie.setMaxAge(60*30);
            response.addCookie(cookie);
            getShoppingCartCount(request,response,md5Email);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            System.out.println("ssss");
            request.setAttribute("error","用户名或密码错误!");
            request.getRequestDispatcher("user/login.jsp").forward(request, response);
        }

    }

    /**
     * 登录成功之后需要加载的数据
     * @param request
     * @param response
     * @param md5Email
     * @throws ServletException
     * @throws IOException
     */
    protected void getShoppingCartCount(HttpServletRequest request,HttpServletResponse response,String md5Email)throws ServletException, IOException{
        //获得前台的ip地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        //先根据ip地址查询购物车中是否存在数据
        int count = userService.getIpShoppingCartCount(remoteAddr);
        if (count>0){
//            System.out.println("panduan shibushi 0");
            UserInfo user= (UserInfo) request.getSession().getAttribute("user");
            System.out.println("session:"+user.getUserEmail());
            //表示有数据,在把IP购物车中的数据取出来，存到用户数据表中
            List<IpShoppingCart> list = userService.getIpShoppingCartByIp(remoteAddr);
            //根据用户邮箱，判断用户购物车是否存在信息
            int userShoppingCartCount = userService.getUserShoppingCartCount(user.getUserEmail());
            if (userShoppingCartCount>0){
                //把用户购物车信息找出来
                List<UserShoppingCart> userList = userService.getUserShoppingCartByUserId(user.getUserEmail());
                System.out.println("userList"+userList);
                    System.out.println("userList!=null");
                    //如果不为空，循环遍历ip购物车的信息
                    for (IpShoppingCart ipShoppingCart : list) {
                        System.out.println(ipShoppingCart.getGoodsId());
                        System.out.println("IpShoppingCart ipShoppingCart : list");
                        //外层循环一次，内层循环多次，去判断用户购物车中是否有跟ip购物车相同的商品id
                        for (UserShoppingCart userShoppingCart : userList) {
                            if (ipShoppingCart.getGoodsId().equals(userShoppingCart.getGoodsId())){
                                System.out.println("ipShoppingCart.getGoodsId().equals(userShoppingCart.getGoodsId())");
                                //判断，存在相同的商品信息id，在对应的商品信息id的基础上，把数量加1
                                userService.updateUserShoppingCartGoodsNumByUserEmail(user.getUserEmail(),ipShoppingCart.getGoodsNum());
                            }else {
                                //如果不存在，那么就根据用户id添加对应的商品信息
                                userService.addUserShoppingCart(user.getUserEmail(),ipShoppingCart.getGoodsId(),1);
                            }
                        }
                    }
            }else {
                System.out.println("jinlaile");
                for (IpShoppingCart ipShoppingCart : list) {
                    System.out.println("jinlaileaaaaaaaaaaaaaaaaaa");
                    userService.addUserShoppingCart(user.getUserEmail(),ipShoppingCart.getGoodsId(),1);
                }
            }
            //上面的都执行完成，删除ip购物车中的数据
            userService.delIpShoppingCartByIp(remoteAddr);

        }
        //登录成功之后去加载购物车内的总数
        int cou = userService.getUserShoppingCartCount(md5Email);
        System.out.println(cou);
//        request.getSession().setAttribute("cou",cou);
        Cookie cookie=new Cookie("cou",String.valueOf(cou) );
        cookie.setMaxAge(60*3600);
        response.addCookie(cookie);
    }

    /**
     * 用户退出登录
     * @param request
     * @param response
     *
     * @throws ServletException
     * @throws IOException
     */
    protected void logOut(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        response.sendRedirect("index.jsp");
    }

    /**
     * 查看用户购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void userShoppingCart(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        System.out.println("come,userShoppingCart");
        String userEmail = request.getParameter("userEmail");
        System.out.println(userEmail);
        List<GoodsInfo> goodsInfoList=null;
        int count = userService.getUserShoppingCartCount(userEmail);
        if (count>0){
            List<UserShoppingCart> list = userService.getUserShoppingCartByUserId(userEmail);
            for (UserShoppingCart userShoppingCart : list) {
                String goodsId = userShoppingCart.getGoodsId();
                goodsInfoList = userService.getAllUserShoppingCartContent(goodsId);

                request.setAttribute("goodsInfoList",goodsInfoList);
            }
        }
        request.getRequestDispatcher("shopcart.jsp").forward(request,response);
//        System.out.println(goodsInfoList.toString());
//                String json = JSON.toJSONString(goodsInfoList);
//                System.out.println(json);
//                response.setCharacterEncoding("utf-8");
//                response.setContentType("text/html;charset=utf-8");
//                PrintWriter out = response.getWriter();
//                out.write(json);
//                out.flush();
//                out.close();
    }
}
