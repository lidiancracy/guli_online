package com.atguigu.eduservice.client.impl;

import com.atguigu.R.R;
import com.atguigu.eduservice.client.videoclient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName videoimpl
 * @Description TODO
 * @Date 2022/10/28 17:04
 */

@Component
public class videoimpl implements videoclient {
    //出错之后会执行
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
