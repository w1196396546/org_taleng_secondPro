package org.java.web;

import com.alibaba.fastjson.JSON;
import org.java.dao.OperationMapper;
import org.java.dao.impl.OperationMapperImpl;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
import org.java.entity.UserCart;
import org.java.service.OperationService;
import org.java.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是操作的servlet
 * 初始化成功之后，所有的操作都在这里面完成
 */
@WebServlet("/operation")
public class OperationServlet extends BaseServlet {
    private ApplicationContext cxt=new ClassPathXmlApplicationContext("applicationContent.xml");
    OperationService  operationService= (OperationService) cxt.getBean("operationService");
    protected void commodity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OperationMapper opm=new OperationMapperImpl();
        List<GoodsInfo> list=opm.getGoodsInfo();
        request.setAttribute("listgoods",list);
        System.out.println(234);
        request.getRequestDispatcher("commodity.jsp").forward(request,response);
    }

    protected void details(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String goodId=request.getParameter("goodsId");
        request.setAttribute("goodId",goodId);
        request.getRequestDispatcher("details.jsp").forward(request,response);
    }

    /**
     * 这是没有登录时的ip购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showIpShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("goodsInfoList");
        //获得访问页面用户的ip地址
                String remoteAddr = request.getRemoteAddr();
                System.out.println(remoteAddr);
        int count = operationService.getIpShoppingCartCount(remoteAddr);
        System.out.println(count);
        List<UserCart> goodsInfoList =new ArrayList<>();;
        if (count>0){
            //代表IP购物车存在数据
            List<IpShoppingCart> list = operationService.getIpShoppingCartByIp(remoteAddr);
            for (IpShoppingCart ipShoppingCart : list) {
                System.out.println(ipShoppingCart.getGoodsId());
                UserCart userCart = operationService.getAllIpShoppingCartContent(ipShoppingCart.getGoodsId(), remoteAddr);
                goodsInfoList.add(userCart);
            }
        }
        request.getSession().setAttribute("goodsInfoList",goodsInfoList);
        response.sendRedirect("shopcart.jsp");
//        request.getRequestDispatcher("shopcart.jsp").forward(request,response);
//            String json = JSON.toJSONString(goodsInfoList);
//            System.out.println(json);
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("text/html;charset=utf-8");
//            PrintWriter out = response.getWriter();
//            out.write(json);
//            out.flush();
//            out.close();

    }
    protected void shoppingIpCart(HttpServletRequest request, HttpServletResponse response,String remoteAddr) throws ServletException, IOException {
        request.getSession().removeAttribute("goodsInfoList");
        List<UserCart> goodsInfoList =new ArrayList<>();
            //代表IP购物车存在数据
            List<IpShoppingCart> list = operationService.getIpShoppingCartByIp(remoteAddr);
            for (IpShoppingCart ipShoppingCart : list) {
                System.out.println(1);
                System.out.println(ipShoppingCart.getGoodsId());
                UserCart userCart = operationService.getAllIpShoppingCartContent(ipShoppingCart.getGoodsId(), remoteAddr);
                goodsInfoList.add(userCart);
        }
        request.getSession().setAttribute("goodsInfoList",goodsInfoList);
        goodsInfoList.forEach(k-> System.out.println(k.getGoods_intro()));

    }
        protected void operationIpGoodsNum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        String goodsId = request.getParameter("goodsId");
        System.out.println(ip+"  "+goodsId);
        String type = request.getParameter("type");
        System.out.println(type);
        if (type.equals("l")){
            //减少的操作
            operationService.updateLessIpShoppingCartGoodsNum(goodsId,ip);
        }else {
            System.out.println("2342342");
            //增加的操作
            operationService.updateAddIpShoppingCartGoodsNum(goodsId,ip);
        }
            shoppingIpCart(request,response,ip);
            response.sendRedirect("shopcart.jsp");
    }

    }
