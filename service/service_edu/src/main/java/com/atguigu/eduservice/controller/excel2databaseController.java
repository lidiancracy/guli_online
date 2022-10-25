package com.atguigu.eduservice.controller;

import com.atguigu.R.R;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName excel2databaseController
 * @Description TODO
 * @Date 2022/10/24 20:36
 */
@Api(description = "excel管理")
@RestController
@RequestMapping("/eduservice/excel")
@CrossOrigin
public class excel2databaseController {
    /**
     * 上传一个excel文档,将数据传入数据库中
     */
    @Autowired
    EduSubjectService eduSubjectService;

    @PostMapping("/upexcelfile")
    public R upexcel(MultipartFile file) {
        eduSubjectService.excel2database(file);
        return R.ok();
    }
}
