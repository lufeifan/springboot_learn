package com.security.learn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 1分钟打印一次日志
     */
    @Scheduled(cron = "0 0/1 * ? * ?")
    private void cancelTimeOutOrder() {
        LOGGER.info(" 1分钟打印一次日志");
    }
}
