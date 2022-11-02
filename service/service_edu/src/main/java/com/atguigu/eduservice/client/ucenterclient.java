package com.atguigu.eduservice.client;

import com.atguigu.R.R;
import com.atguigu.eduservice.client.impl.videoimpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName ucenterclient
 * @Description TODO
 * @Date 2022/11/2 17:42
 */
@Component
@FeignClient(value = "service-ucenter")
public interface ucenterclient {
    @GetMapping("/educenter/member/getMemberInfo")
    public R getMemberInfo(HttpServletRequest request);
}


