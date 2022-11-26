package com.example.springunity.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zx
 * @since 2022/11/16 016
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class FilterA implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
        log.info("FilterA init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.info("FilterA before");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("FilterA post");
    }

    @Override
    public void destroy() {
        log.info("FilterA destroy");
    }
}
