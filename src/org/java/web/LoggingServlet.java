package org.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logging")
public class LoggingServlet extends BaseServlet {
    /**
     * 用户登录的方法，判断用户名密码是否正确
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void checkId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
