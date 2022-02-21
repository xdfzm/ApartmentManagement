package com.rt.Filter;

import com.rt.util.constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JspFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servletRequest1 = (HttpServletRequest) servletRequest;
        HttpServletResponse servletResponse1 = (HttpServletResponse) servletResponse;
        if(servletRequest1.getSession().getAttribute(constant.USER_SESSION)==null){
            servletRequest1.getSession().setAttribute("msg","请先登入");
            servletResponse1.sendRedirect("/index.jsp");

        }
        filterChain.doFilter(servletRequest1, servletResponse1);
    }

    @Override
    public void destroy() {

    }
}
