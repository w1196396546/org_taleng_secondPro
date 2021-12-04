package org.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *这是初始化加载index页面的
 */
@WebServlet("/init")
public class InitServlet extends BaseServlet {
    /**
     * 初始化加载的方法
     * 进入页面从数据库取数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void init(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
