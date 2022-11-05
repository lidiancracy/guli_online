package com.atguigu.msmservice.controller;


import com.atguigu.R.R;
import com.atguigu.msmservice.service.MsmService;
import com.atguigu.msmservice.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping("/edumsm/msm")

public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //生成随机值，传递阿里云进行发送
        String code = RandomUtil.getFourBitRandom();
        log.info(code);
        redisTemplate.opsForValue().set("registerphone:" + phone, code, 2, TimeUnit.MINUTES);
        return R.ok();
    }
}
