package org.java.web;

import com.alibaba.fastjson.JSON;
import org.java.entity.*;
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
import java.util.*;

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
        PrintWriter out = response.getWriter();
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
            String remeberUser = request.getParameter("remeberUser");
            if (remeberUser!=null){
                Cookie cookie=new Cookie("remeberUser",user.toString());
                cookie.setMaxAge(60*60);
                response.addCookie(cookie);
            }
            System.out.println(user.getUserPwd());
            System.out.println(user.getUserEmail());
            request.getSession().setAttribute("user",user);
            Cookie cookie=new Cookie("user",user.getUserEmail() );
            System.out.println("cookie的email"+user.getUserEmail());
            cookie.setMaxAge(60*30);
            response.addCookie(cookie);
//            getShoppingCartCount(request,response,md5Email);
            getShoppingCartCount(request,response,md5Email);
//            System.out.println("youmeiyou jinru gouwuchedefangfa ");
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
        System.out.println("youmeiyoujinru zhege fangfa");
        //获得前台的ip地址
        String remoteAddr = request.getRemoteAddr();
        System.out.println(remoteAddr);
        //先根据ip地址查询购物车中是否存在数据
        int count = userService.getIpShoppingCartCount(remoteAddr);
        if (count>0){
            //定义两个map集合，用于存放IP购物车与用户购物车中的数据
            Map<String,Integer> ipMap = new HashMap<>();

            System.out.println("panduan shibushi 0");
            UserInfo user= (UserInfo) request.getSession().getAttribute("user");
//            System.out.println("session:"+user.getUserEmail());
            //表示有数据,在把IP购物车中的数据取出来，存到用户数据表中
            List<IpShoppingCart> list = userService.getIpShoppingCartByIp(remoteAddr);
            for (int i = 0; i < list.size(); i++) {
                IpShoppingCart ipShoppingCart = list.get(i);
                int goodsNum=ipShoppingCart.getGoodsNum();
//                System.out.println("ipMap:values:"+goodsNum);
                ipMap.put(ipShoppingCart.getGoodsId(),goodsNum);
            }
            list.forEach(k-> System.out.println(k.getGoodsId()));
            //根据用户邮箱，判断用户购物车是否存在信息
            int userShoppingCartCount = userService.getUserShoppingCartCount(user.getUserEmail());
            if (userShoppingCartCount>0){
                //如果用户信息不为0，就要map集合
                Map<String,UserShoppingCart> userMap=new HashMap<>();
                //把用户购物车信息找出来
                List<UserShoppingCart> userList = userService.getUserShoppingCartByUserId(user.getUserEmail());
                System.out.println("userList"+userList);
                    System.out.println("userList!=null");
                    //如果不为空，循环遍历ip购物车的信息

                for (int i = 0; i < userList.size(); i++) {
                    UserShoppingCart userShoppingCart = userList.get(i);
                    userMap.put(userShoppingCart.getGoodsId(),userShoppingCart);
                }
                Set<String> ipKey = ipMap.keySet();
                for (String s : ipKey) {
                    boolean flag = userMap.containsKey(s);
                    if (flag){
                        Integer integer = ipMap.get(s);
//                        System.out.println("integer:value"+integer);
                        userService.updateUserShoppingCartGoodsNumByUserEmail(user.getUserEmail(),integer);
                        break;

                    }else {
                        Integer cou=ipMap.get(s);
//                        System.out.println("cou:value"+cou);
                        userService.addUserShoppingCart(user.getUserEmail(),s,cou);
                        break;
                    }
                }
            }else {
//                System.out.println("xiaoyu：eaor");
//                System.out.println(user.getUserEmail());
                Set<String> keys = ipMap.keySet();
//                System.out.println(keys.size());
                for (String key : keys) {
                    System.out.println(key);
                    int value=ipMap.get(key);
//                    System.out.println("ipMap value:"+value);
                    userService.addUserShoppingCart(user.getUserEmail(),key,value);
                }
            }
            //上面的都执行完成，删除ip购物车中的数据
            userService.delIpShoppingCartByIp(remoteAddr);

        }
        //登录成功之后去加载购物车内的总数
        int cou = userService.getUserShoppingCartCount(md5Email);

//        System.out.println(cou);
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
        request.getSession().removeAttribute("goodsInfoList");
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
        request.getSession().removeAttribute("goodsInfoList");
        System.out.println("come,userShoppingCart");
        String userEmail = request.getParameter("userEmail");
        System.out.println(userEmail);
        List<UserCart> goodsInfoList=new ArrayList<>();;
        int count = userService.getUserShoppingCartCount(userEmail);
        if (count>0){
            List<UserShoppingCart> list = userService.getUserShoppingCartByUserId(userEmail);
            for (UserShoppingCart userShoppingCart : list) {
                String goodsId = userShoppingCart.getGoodsId();
                UserCart userCart = userService.getAllUserShoppingCartContent(goodsId, userEmail);
                goodsInfoList.add(userCart);
            }
        }
//        goodsInfoList.forEach(k-> System.out.println(k.getGoods_intro()));
        request.getSession().setAttribute("goodsInfoList",goodsInfoList);
        response.sendRedirect("shopcart.jsp");
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

    /**
     * 调用购物车的复用代码
     * @param request
     * @param response
     * @param email
     * @throws ServletException
     * @throws IOException
     */
    protected void showCart(HttpServletRequest request,HttpServletResponse response,String email)throws ServletException, IOException {
            request.getSession().removeAttribute("goodsInfoList");
            List<UserCart> goodsInfoList=new ArrayList<>();
            List<UserShoppingCart> list = userService.getUserShoppingCartByUserId(email);
            for (UserShoppingCart userShoppingCart : list) {
                String goodsId = userShoppingCart.getGoodsId();
                UserCart userCart = userService.getAllUserShoppingCartContent(goodsId, email);
                goodsInfoList.add(userCart);
//                request.setAttribute("goodsNum",userShoppingCart.getGoodsNum());

        }
        request.getSession().setAttribute("goodsInfoList",goodsInfoList);
            goodsInfoList.forEach(k-> System.out.println("k的值 "+k.getGoods_intro()));
            response.sendRedirect("shopcart.jsp");
    }

    /**
     * 操作购物车数量的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void operationGoodsNum(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        System.out.println("abc");
        String goodsId = request.getParameter("goodsId");
        String type = request.getParameter("type");
        System.out.println(type);
        UserInfo user= (UserInfo) request.getSession().getAttribute("user");
        if (type.equals("l")){
            //做减的操作
            userService.updateLessUserGoodsCart(user.getUserEmail(),goodsId);
        }else {
            //做加的操作
            userService.updateAddUserGoodsCart(user.getUserEmail(),goodsId);
        }
        showCart(request,response, user.getUserEmail());

    }

    /**
     * 添加用户收货地址的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addAddress(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

        String name = request.getParameter("name");
        Integer province= Integer.valueOf(request.getParameter("province"));
        Integer city= Integer.valueOf(request.getParameter("city"));
        Integer area= Integer.valueOf(request.getParameter("area"));
        System.out.println(province+"  "+city+"  "+area);
        //获得省份城市信息
        String provinceMsg=request.getParameter("provinceMsg");
        String cityMsg=request.getParameter("cityMsg");
        String areaMsg=request.getParameter("areaMsg");

        System.out.println("provinceMsg+cityMsg+areaMsg.........."+provinceMsg+cityMsg+areaMsg);
        String tel = request.getParameter("tel");
        String addr = request.getParameter("addr");
        String youbian = request.getParameter("youbian");
        //拼接成实际地址
        String msg=provinceMsg+" "+cityMsg+" "+areaMsg+" "+addr;
        //得到用户名
        UserInfo user= (UserInfo) request.getSession().getAttribute("user");
        String userEmail = user.getUserEmail();
        userService.addAddress(name,msg,userEmail,tel,youbian,province,city,area);

    }

    /**
     * 修改用户地址信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateAddress(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        String name = request.getParameter("name");
        Integer province= Integer.valueOf(request.getParameter("province"));
        Integer city= Integer.valueOf(request.getParameter("city"));
        Integer area= Integer.valueOf(request.getParameter("area"));
        System.out.println(province+"  "+city+"  "+area);
        //获得省份城市信息
        String provinceMsg=request.getParameter("provinceMsg");
        String cityMsg=request.getParameter("cityMsg");
        String areaMsg=request.getParameter("areaMsg");
        //这是获取收货地址唯一编号，不能根据用户名去修改，要根据这个唯一标号去修改
        String addrId = request.getParameter("addrId");
        System.out.println("provinceMsg+cityMsg+areaMsg.........."+provinceMsg+cityMsg+areaMsg);
        String tel = request.getParameter("tel");
        String addr = request.getParameter("addr");
        String youbian = request.getParameter("youbian");
        //拼接成实际地址
        String msg=provinceMsg+" "+cityMsg+" "+areaMsg+" "+addr;
        //得到用户名
        UserInfo user= (UserInfo) request.getSession().getAttribute("user");
        String userEmail = user.getUserEmail();
        userService.updateAddress(name,msg,addrId,tel,youbian,province,city,area);

    }

    /**
     * 得到用户收货地址的方法
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getAddress(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        String userEmail = user.getUserEmail();
        List<Address> list = userService.getAddress(userEmail);
        String json = JSON.toJSONString(list);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
