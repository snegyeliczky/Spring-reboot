package com.amigoscode;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ":" + request.getHeader(headerName));
        });
        filterChain.doFilter(servletRequest, servletResponse);
    }


}
