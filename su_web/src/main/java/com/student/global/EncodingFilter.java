package com.student.global;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    private String encoding="utf-8";
    public void init(FilterConfig config) throws ServletException {
        if(config.getInitParameter("encoding")!=null){
            encoding=config.getInitParameter("encoding");
        }
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        if (!request1.getServletPath().contains(".css") && !request1.getServletPath().contains(".js")) {
            request1.setCharacterEncoding(encoding);
            response1.setContentType("text/html;charset=" + encoding);
        }
        String method = request1.getMethod();
        //options请求直接放行
        if(method.equals("OPTIONS")){
            chain.doFilter(request,response);
        }
        chain.doFilter(request,response);
    }
}
