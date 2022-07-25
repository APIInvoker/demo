package com.example.springunity.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zhengxin
 * @createTime 2022/7/20 9:48
 */
@Component
@Slf4j
public class TestSchedule {
    @Scheduled(cron = "0/10 * * * * ?")
    public void task1() {
        log.info("task1 info");
        log.error("task1 error");
        log.debug("task1 debug");
        log.warn("task1 warn");
        log.trace("task1 trace");
    }
}
