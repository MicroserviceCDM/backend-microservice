package io.github.cdm.authservice.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
@Slf4j
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // Log Request Headers
        log.info("Request Headers for {}: ", httpRequest.getRequestURI());
        Collections.list(httpRequest.getHeaderNames())
                .forEach(headerName -> 
                    log.info("Request {}: {}", headerName, httpRequest.getHeader(headerName)));

        chain.doFilter(request, response);

        // Log Response Headers
        log.info("Response Headers for {}: ", httpRequest.getRequestURI());
        httpResponse.getHeaderNames()
                .forEach(headerName ->
                    log.info("Response {}: {}", headerName, httpResponse.getHeader(headerName)));
    }
}