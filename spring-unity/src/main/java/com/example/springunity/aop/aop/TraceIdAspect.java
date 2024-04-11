package com.example.springunity.aop.aop;

import com.example.util.TraceIdUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * 用于给HTTP请求之外的方法日志添加TraceId（通过注解形式@TraceIdLog）
 * <p>因为过滤器截取不到，比如定时任务<p/>
 *
 * @author zx
 * @since 2022/11/16 016
 */
@Component
@Aspect
public class TraceIdAspect
{
    @Pointcut("@annotation(com.example.annotation.TraceIdLog)")
    public void pointCut()
    {
    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MDC.put("traceId", TraceIdUtil.getTraceId());
        Object result = proceedingJoinPoint.proceed();
        MDC.remove("traceId");
        return result;
    }
}
