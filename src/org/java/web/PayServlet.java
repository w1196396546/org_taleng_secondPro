package org.java.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Servlet", value = "/pay")
public class PayServlet extends BaseServlet {

    protected void aliPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
