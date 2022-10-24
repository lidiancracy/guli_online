package com.atguigu.eduservice.controller;

import com.atguigu.R.R;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName EduLoginController
 * @Description 登录
 * @Date 2022/10/22 20:12
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    /**
     * 登录
     */
    @PostMapping("/login")
    public R lgin(){
        return R.ok().data("token","lidian_token");
    }
    /**
     * 用户信息
     */
    @GetMapping("/info")
    public R info(){
        HashMap<String, Object> mp = new HashMap<>();
        ArrayList<Integer> roles = new ArrayList<>();
        roles.add(1);
        mp.put("roles",roles);
        mp.put("name","fuck");
        mp.put("avatar","http://edu-longyang.oss-cn-beijing.aliyuncs.com/2020/08/05/25f411209c8b44b9b003482b6265c3c9file.png");

        return R.ok().data(mp);
    }
}
