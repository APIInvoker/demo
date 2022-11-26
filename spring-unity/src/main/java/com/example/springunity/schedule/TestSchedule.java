package com.example.springunity.schedule;

import com.example.springunity.annotation.TraceIdLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author zx
 * @Date 2022/7/20 9:48
 */
@Component
@Slf4j
public class TestSchedule {
    @TraceIdLog
    @Scheduled(cron = "0/3 * * * * ?")
    public void task1() {
        log.info("task1 infotask1");
        log.error("task1 errortask1");
        log.debug("task1 debugtask1");
        log.warn("task1 warntask1");
    }
}
