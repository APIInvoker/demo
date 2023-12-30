package com.example.filter;

import com.example.util.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * TraceIdFilter
 * <p>为每一个HTTP请求的日志添加TraceId</p>
 *
 * @author zx
 * @since 2023/1/22 022
 */
@Slf4j
@WebFilter(urlPatterns = "/*")
public class HttpTraceIdFilter implements Filter
{
    private final String TRACE_ID = "traceId";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        log.info("init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String traceId = TraceIdUtil.getTraceId();
        MDC.put(TRACE_ID, traceId);
        log.info("doFilter pre");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("doFilter post");
        MDC.clear();
    }

    @Override
    public void destroy()
    {
        log.info("destroy");
        Filter.super.destroy();
    }
}
