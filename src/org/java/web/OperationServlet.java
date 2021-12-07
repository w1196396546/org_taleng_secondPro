package org.java.web;

import org.java.dao.OperationMapper;
import org.java.dao.impl.OperationMapperImpl;
import org.java.entity.GoodsInfo;

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


}
