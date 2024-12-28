package com.minhvu.authservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        log.info("Request Headers for {}: ", httpRequest.getRequestURI());
        Collections.list(httpRequest.getHeaderNames())
                .forEach(headerName -> 
                    log.info("{}: {}", headerName, httpRequest.getHeader(headerName)));

        chain.doFilter(request, response);
    }
}