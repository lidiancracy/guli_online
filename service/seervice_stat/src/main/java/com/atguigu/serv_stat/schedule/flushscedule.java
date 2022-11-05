package com.atguigu.serv_stat.schedule;

import com.atguigu.serv_stat.service.StatisticsDailyService;
import com.atguigu.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName flushscedule
 * @Description TODO
 * @Date 2022/11/5 15:37
 */
@Component
public class flushscedule {
    @Autowired
    StatisticsDailyService service;
    @Scheduled(cron = "0 0 1/24 * * ? ")
    public void task2() {
        service.registerCount(DateUtil.formatDate(new Date()));
    }
}
