package com.atguigu.eduservice.controller;

import com.atguigu.R.R;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.vo.courseVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName educourseController
 * @Description TODO
 * @Date 2022/10/26 15:51
 */
@Api(description = "课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class educourseController {
    /**
     * 添加课程
     */
    @Autowired
    EduCourseService eduCourseService;
    @PostMapping("/addCourseInfo")
    public R addcourse(@RequestBody courseVo courseInfo){
        String courseid= eduCourseService.addcourse(courseInfo);
        return R.ok().data("courseId",courseid);
    }
}
