package com.example.springunity.schedule;

import com.example.annotation.TraceIdLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zx
 * @since 2022/7/20 9:48
 */
@Component
@Slf4j
public class TestSchedule
{
    @TraceIdLog
    @Scheduled(cron = "3 * * * * ?")
    public void task1()
    {
        log.info("task1 info");
        log.error("task1 error");
        log.debug("task1 debug");
        log.warn("task1 warn");
    }
}
