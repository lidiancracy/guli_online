package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.vo.courseVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 83799
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-10-26 15:49:39
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService{
    /**
     * 添加课程
     * @param courceInfo
     * @return
     */
    @Autowired
    EduCourseDescriptionService despservice;
    @Override
    public String addcourse(courseVo courceInfo) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setIsDeleted(0);
        BeanUtils.copyProperties(courceInfo,eduCourse);
        save(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courceInfo.getDescription());
        despservice.save(eduCourseDescription);
        return eduCourse.getId();
    }
}




