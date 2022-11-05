package com.atguigu.serv_stat.service.impl;

import com.atguigu.serv_stat.client.Ucenterclient;
import com.atguigu.serv_stat.domain.StatisticsDaily;
import com.atguigu.serv_stat.mapper.StatisticsDailyMapper;
import com.atguigu.serv_stat.service.StatisticsDailyService;
import com.atguigu.utils.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
* @author 83799
* @description 针对表【statistics_daily(网站统计日数据)】的数据库操作Service实现
* @createDate 2022-11-04 21:27:32
*/
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily>
    implements StatisticsDailyService {
    @Autowired
    Ucenterclient ucenterclient;
    @Override
    public void registerCount(String day) {
        Integer countRegister = ucenterclient.countRegister(day);
        Integer countlogin = ucenterclient.countlogin(day);
        StatisticsDaily sday = new StatisticsDaily();
        sday.setRegisterNum(countRegister);
        sday.setLoginNum(countlogin);
        Random random = new Random();
        sday.setVideoViewNum(random.nextInt(200));
        sday.setCourseNum(random.nextInt(200));
        sday.setDateCalculated(DateUtil.formatDate(new Date()));
        baseMapper.insert(sday);
    }
}




