package com.atguigu.educenter.controller;


import com.atguigu.R.JwtUtils;
import com.atguigu.R.R;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-03-09
 */
@RestController
@RequestMapping("/educenter/member")

public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token", token);
    }

    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户id
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法，根据request对象获取头信息
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo", member);
    }

    @GetMapping("getUcenter/{memberId}")
    public UcenterMember getUcenterById(@PathVariable("memberId") String memberId) {
        UcenterMember member = memberService.getById(memberId);
        return member;
    }

    /**
     * 根据日期查看注册人数
     */
    @GetMapping("countRegister/{day}")
    public Integer countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return count;
    }

    /**
     * 根据日期查看登陆人数
     */
    @GetMapping("countlogin/{day}")
    public Integer countlogin(@PathVariable String day) {
        return memberService.countlogin(day);
    }
}

