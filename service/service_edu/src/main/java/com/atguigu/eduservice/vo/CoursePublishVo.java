package com.atguigu.eduservice.vo;

import lombok.Data;

/**
 * 用于课程创建第三步，课程数据显示
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
