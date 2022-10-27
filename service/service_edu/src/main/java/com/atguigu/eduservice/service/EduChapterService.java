package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author 83799
* @description 针对表【edu_chapter(课程)】的数据库操作Service
* @createDate 2022-10-26 15:49:39
*/
public interface EduChapterService extends IService<EduChapter> {

    ArrayList<HashMap<String, Object>> getallvideos(String courseId);
}
