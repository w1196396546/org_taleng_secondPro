package org.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 用户的操作全部都在这里面
 * 包括，显示购物车，结算
 */
@WebServlet("/user")
public class UserServlet extends BaseServlet {

    protected void showCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
