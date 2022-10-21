package com.atguigu.eduservice.controller;


import com.atguigu.R.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    @ApiOperation("查询所有教师")
    @GetMapping("/findAll")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("teacherlist",list);
    }

    /**
     * 删除某老师
     * @param id
     * @return
     */
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R deleteTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        boolean removeById = eduTeacherService.removeById(id);
        return R.ok().data("delete",removeById);
    }
    /**
     * 分页查询老师数据
     */
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("/{current}/{limit}")
    public R pageselectTeacher(@ApiParam(value = "当前页") @PathVariable Long current,
                               @ApiParam(value = "每页条数") @PathVariable Long limit) {
        Page<EduTeacher> eduTeacherPage = new Page<EduTeacher>(current, limit);
        eduTeacherService.page(eduTeacherPage,null);
        // 获取当页记录
        List<EduTeacher> records = eduTeacherPage.getRecords();
        //获取总条数
        return R.ok().data("total",eduTeacherPage.getTotal()).data("data",records);
    }
}

