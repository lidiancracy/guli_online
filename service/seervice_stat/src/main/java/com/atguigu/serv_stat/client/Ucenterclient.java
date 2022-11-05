package com.atguigu.serv_stat.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @ClassName Ucenterclient
 * @Description TODO
 * @Date 2022/11/5 14:48
 */
@FeignClient("service-ucenter")
public interface Ucenterclient {
    @GetMapping("/educenter/member/countRegister/{day}")
    public Integer countRegister(@PathVariable("day") String day);

    @GetMapping("/educenter/member/countlogin/{day}")
    public Integer countlogin(@PathVariable("day") String day);
}
