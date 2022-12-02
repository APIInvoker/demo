package com.example.springunity.interceptor;

import com.example.springunity.util.TraceIdUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zx
 * @since 2022/9/9 22:05
 */
public class TraceIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        MDC.put("traceId", TraceIdUtil.getTraceId() + " | ");
        return true;
    }
}
