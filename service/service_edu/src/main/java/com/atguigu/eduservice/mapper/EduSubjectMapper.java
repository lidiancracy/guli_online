package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 83799
* @description 针对表【edu_subject(课程科目)】的数据库操作Mapper
* @createDate 2022-10-24 20:41:28
* @Entity com.atguigu.eduservice.entity.EduSubject
*/
@Mapper
public interface EduSubjectMapper extends BaseMapper<EduSubject> {

}




