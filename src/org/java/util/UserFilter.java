package org.java.util;

import org.java.entity.UserInfo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 过滤器，用于拦截用户请求，判断用户是否登录
 */
@WebFilter(urlPatterns = "*")
public class UserFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpSession session = request.getSession();
        UserInfo user= (UserInfo) session.getAttribute("user");
        //获得请求
        String uri = request.getRequestURI();
        int index = uri.indexOf("/user/login.jsp");
        if (index>-1){
            if (user!=null){
                HttpServletResponse response= (HttpServletResponse) resp;
                response.sendRedirect("../index.jsp");
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
