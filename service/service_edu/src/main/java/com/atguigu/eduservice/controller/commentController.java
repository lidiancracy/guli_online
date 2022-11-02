package com.atguigu.eduservice.controller;

import com.atguigu.Exception.lidianException;
import com.atguigu.R.JwtUtils;
import com.atguigu.R.R;
import com.atguigu.eduservice.client.ucenterclient;
import com.atguigu.eduservice.entity.EduComment;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCommentService;
import com.atguigu.eduservice.service.EduCourseService;

import com.atguigu.eduservice.vo.commentVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName commentController
 * @Description TODO
 * @Date 2022/11/2 17:11
 */
@Api(description = "课程评论管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/comment")
public class commentController {
    @Autowired
    EduCommentService eduCommentService;

    /**
     * 获取所有评论
     */
    @GetMapping("/{page}/{limit}")
    public R getcm(@PathVariable long page, @PathVariable long limit,HttpServletRequest request) {

        Page<EduComment> pageParam = new Page<>(page, limit);
        eduCommentService.page(pageParam, null);
        List<EduComment> records = pageParam.getRecords();

        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        System.out.println(map);

        return R.ok().data("data", map);
    }

    /**
     * 添加评论
     * 接收到的只有courseid和评论content
     */
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    ucenterclient ucenterclient;

//    @PostMapping("/auth/save")
//    public R addcm(@RequestBody EduComment eduComment, HttpServletRequest request) {
//        EduComment eduCommenttemp = new EduComment();
//        eduCommenttemp.setContent(eduComment.getContent());
//        eduCommenttemp.setCourseId(eduComment.getCourseId());
//        // 根据courseid查出teacherid
//        EduCourse byId = eduCourseService.getById(eduComment.getCourseId());
//        if (byId == null) {
//            throw new lidianException(20001, "课程不存在");
//        }
//        String teacherId = byId.getTeacherId();
//        if (StringUtils.hasText(teacherId)) {
//            eduCommenttemp.setTeacherId(teacherId);
//        }
//        // 当前用户信息也要保存
//        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
//        if (StringUtils.hasText(memberIdByJwtToken)) {
//            throw new lidianException(20001, "用户未登录");
//        }
//        eduCommenttemp.setMemberId(memberIdByJwtToken);
//
//        // 保存会员基本信息 这里要调服务
//        R memberInfo = ucenterclient.getMemberInfo(request);
//        Map<String, Object> data = memberInfo.getData();
//        Object user = data.get("userinfo");
//        commentVo commentVo=(commentVo)user;
//        eduCommenttemp.setNickname(commentVo.getNickname());
//        eduCommenttemp.setAvatar(commentVo.getAvatar());
//        eduCommentService.save(eduCommenttemp);
//        return R.ok();
//    }
}
