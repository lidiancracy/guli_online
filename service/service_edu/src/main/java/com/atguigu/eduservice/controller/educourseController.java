package com.atguigu.eduservice.controller;

import com.atguigu.Exception.lidianException;
import com.atguigu.R.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.courseVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 添加课程 具体指添加课程基本信息
     */
    @Autowired
    EduCourseService eduCourseService;
    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;

    @PostMapping("/addCourseInfo")
    public R addcourse(@RequestBody courseVo courseInfo) {
        String courseid = eduCourseService.addcourse(courseInfo);
        return R.ok().data("courseId", courseid);
    }

    /**
     * 根据课程id查询course基本信息
     */
    @GetMapping("/getCourseInfo/{id}")
    public R getcourinfo(@PathVariable String id) {
        courseVo courseVo = new courseVo();
        EduCourse byId = eduCourseService.getById(id);
        if (byId == null) {
            throw new lidianException(20001, "课程id不存在");
        }
        BeanUtils.copyProperties(byId, courseVo);
        EduCourseDescription desp = eduCourseDescriptionService.getById(id);
        if (desp == null) {
            throw new lidianException(20001, "课程描述不存在");
        }
        courseVo.setDescription(desp.getDescription());
        return R.ok().data("courseInfoVo", courseVo);
    }

    /**
     * 修改课程信息
     */
    @PostMapping("/updateCourseInfo")
    public R updatecourse(@RequestBody courseVo courseInfoVo) {
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据id查询课程全部详细信息
     * 发布课程第三步的数据显示
     */
    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return R.ok().data("publishCourse", coursePublishVo);
    }

    /**
     * 第三步 按钮发布
     *
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    /**
     * 课程list展示
     */
    @GetMapping
    public R getCourseList() {
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("list", list);
    }
    /**
     * 课程删除，同时删除所有下面的章节小结
     */
    //删除课程
    @ApiOperation("删除课程")
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }
}

