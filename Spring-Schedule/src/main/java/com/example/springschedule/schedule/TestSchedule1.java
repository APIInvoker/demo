package com.example.springschedule.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zx
 * @createTime 2022/6/22 21:21
 */
@Component
@Slf4j
public class TestSchedule1 {
    @Scheduled(cron = "${spring.schedule.test}")
    public void test1() {
        log.info("定时任务");
    }
}
