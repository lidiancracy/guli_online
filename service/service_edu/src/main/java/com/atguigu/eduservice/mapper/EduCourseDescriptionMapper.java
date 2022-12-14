package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 83799
* @description 针对表【edu_course_description(课程简介)】的数据库操作Mapper
* @createDate 2022-10-26 15:49:39
* @Entity com.atguigu.eduservice.entity.EduCourseDescription
*/
@Mapper
public interface EduCourseDescriptionMapper extends BaseMapper<EduCourseDescription> {

}




