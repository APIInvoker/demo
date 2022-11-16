package com.example.springunity.advice;

import com.example.springunity.util.TraceIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @author Zheng Xin
 * @since 2022/11/16 016
 */
@Component
@Aspect
public class TraceIdAdvice {
    @Pointcut("@annotation(com.example.springunity.annotation.TraceIdLog)")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MDC.put("traceId", TraceIdUtil.getTraceId() + " | ");
        Object result = proceedingJoinPoint.proceed();
        MDC.remove("traceId");
        return result;
    }
}
