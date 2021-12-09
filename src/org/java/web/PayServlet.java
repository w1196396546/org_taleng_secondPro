package org.java.web;

import org.java.entity.UserCart;
import org.java.entity.UserInfo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "Servlet", value = "/pay")
public class PayServlet extends BaseServlet {

    protected void aliPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
