package org.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这是操作的servlet
 * 初始化成功之后，所有的操作都在这里面完成
 */
@WebServlet("/operation")
public class OperationServlet extends BaseServlet {
    protected void operation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
