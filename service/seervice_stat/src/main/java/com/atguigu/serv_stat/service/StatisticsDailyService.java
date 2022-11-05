package com.atguigu.serv_stat.service;


import com.atguigu.serv_stat.domain.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 83799
* @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service
* @createDate 2022-11-04 21:27:32
*/
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void registerCount(String day);
}
