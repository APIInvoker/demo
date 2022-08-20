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
    @Scheduled(cron = "* * 1 * * ?")
    public void task1() {
        log.info("task1 infotask1");
        log.error("task1 errortask1");
        log.debug("task1 debugtask1");
        log.warn("task1 warntask1");
    }
}
