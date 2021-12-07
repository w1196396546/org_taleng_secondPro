package org.java.web;

import org.java.dao.OperationMapper;
import org.java.dao.impl.OperationMapperImpl;
import org.java.entity.GoodsInfo;
import org.java.entity.IpShoppingCart;
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
                //获得访问页面用户的ip地址
                String remoteAddr = request.getRemoteAddr();
                System.out.println(remoteAddr);
        int count = operationService.getIpShoppingCartCount(remoteAddr);
        System.out.println(count);
        if (count>0){
            //代表IP购物车存在数据
            List<IpShoppingCart> list = operationService.getIpShoppingCartByIp(remoteAddr);
            for (IpShoppingCart ipShoppingCart : list) {
                List<GoodsInfo> goodsInfoList = operationService.getAllIpShoppingCartContent(ipShoppingCart.getGoodsId());
                request.setAttribute("goodsInfoList",goodsInfoList);
            }
        }
        request.getRequestDispatcher("shopcart.jsp").forward(request,response);

    }


    }
