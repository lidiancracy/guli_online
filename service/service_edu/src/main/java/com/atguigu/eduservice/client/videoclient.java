package com.atguigu.eduservice.client;

import com.atguigu.R.R;
import com.atguigu.eduservice.client.impl.videoimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ClassName videoclient
 * @Description TODO
 * @Date 2022/10/28 15:00
 */
@Component
@FeignClient(value = "service-video",fallback = videoimpl.class)
public interface videoclient {
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) ;
}
