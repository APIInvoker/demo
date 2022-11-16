package com.example.springunity.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author Zheng Xin
 * @since 2022/11/16 016
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class FilterB implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("FilterB init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("FilterB before");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("FilterB post");
    }

    @Override
    public void destroy() {
        log.info("FilterB destroy");
    }
}
