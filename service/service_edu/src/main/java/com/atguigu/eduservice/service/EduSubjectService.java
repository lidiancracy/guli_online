package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 83799
* @description 针对表【edu_subject(课程科目)】的数据库操作Service
* @createDate 2022-10-24 20:41:28
*/

public interface EduSubjectService extends IService<EduSubject> {

    void excel2database(MultipartFile file);

    void selectall();

}
