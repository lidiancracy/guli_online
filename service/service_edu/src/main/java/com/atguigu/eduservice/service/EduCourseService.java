package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.courseVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 83799
* @description 针对表【edu_course(课程)】的数据库操作Service
* @createDate 2022-10-26 15:49:39
*/
public interface EduCourseService extends IService<EduCourse> {

    String addcourse(courseVo courseVo);

    void updateCourseInfo(courseVo courseInfoForm);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);
}
