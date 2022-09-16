package com.example.springunity.interceptor;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Author zx
 * @Date 2022/9/9 22:05
 */
public class TraceIdInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
        MDC.put("traceId", getTraceId());
        MDC.put("split", " | ");
        return true;
    }

    private String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }
}
