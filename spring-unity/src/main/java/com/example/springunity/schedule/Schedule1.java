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
public class Schedule1
{
    @TraceIdLog
    @Scheduled(cron = "0 0/10 * * * ? ")
    public void task1()
    {
        log.info("Schedule1 execute");
    }
}
