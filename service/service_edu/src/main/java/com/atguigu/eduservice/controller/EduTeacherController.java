package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ld
 * @since 2022-10-21
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @GetMapping("/findAll")
    public List<EduTeacher> list(){
        return eduTeacherService.list(null);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Boolean deleteTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean removeById = eduTeacherService.removeById(id);
        return removeById;
    }
}

