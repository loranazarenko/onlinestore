package com.epam.onlinestore.web.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {

    private String encoding;

    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("requestEncoding");

        if(encoding==null) encoding="cp1251";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next)
            throws IOException {
        try {
            request.setCharacterEncoding(encoding);
            next.doFilter(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    public void destroy(){}
}