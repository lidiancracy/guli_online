package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.listener.excellistener;
import com.atguigu.eduservice.vo.category_lesson;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
* @author 83799
* @description 针对表【edu_subject(课程科目)】的数据库操作Service实现
* @createDate 2022-10-24 20:41:28
*/
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject>
    implements EduSubjectService{
    /**
     * 使用easyexcel读取
     * @param file
     */
    @Autowired
    excellistener excellistener;
    @Override
    public void excel2database(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), category_lesson.class,excellistener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有分类 返回形式
     * {
     *
     *
     * }
     */
    @Override
    public void selectall() {

    }
}




