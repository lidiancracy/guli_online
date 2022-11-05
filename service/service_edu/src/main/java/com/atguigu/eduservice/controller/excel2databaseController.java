package com.atguigu.eduservice.controller;

import com.atguigu.Exception.lidianException;
import com.atguigu.R.R;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName excel2databaseController
 * @Description TODO
 * @Date 2022/10/24 20:36
 */
@Api(description = "excel管理")
@RestController
@RequestMapping({"/eduservice/excel","/eduservice/subject"})

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

    /**
     * 获取课程列表
     */
    @GetMapping("/getAllSubject")
    public R getall() {
        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        /**
         * 封装所有一级目录
         */
        LambdaQueryWrapper<EduSubject> wq = new LambdaQueryWrapper<>();
        wq.eq(EduSubject::getParentId,"0");
        List<EduSubject> list = eduSubjectService.list(wq);
        if(list!=null&&!list.isEmpty()){
            for (EduSubject eduSubject : list) {
                String id = eduSubject.getId();
                String title = eduSubject.getTitle();
                HashMap<String, Object> tempmap = new HashMap<>();
                tempmap.put("id",id);
                tempmap.put("title",title);
                hashMaps.add(tempmap);
            }
        }else {
            throw  new lidianException(20001,"没找到一级目录");
        }

        /**
         * 封装所有一级中的二级目录
         */
        for (HashMap<String, Object> hashMap : hashMaps) {
            LambdaQueryWrapper<EduSubject> wq2 = new LambdaQueryWrapper<>();
            String parentid = (String) hashMap.get("id");
            wq2.eq(EduSubject::getParentId,parentid);
            List<EduSubject> listson = eduSubjectService.list(wq2);

            ArrayList<HashMap<String, Object>> sons = new ArrayList<>();
            if(listson!=null&& !listson.isEmpty()){
                for (EduSubject son : listson) {
                    String id = son.getId();
                    String title = son.getTitle();
                    HashMap<String, Object> tempson = new HashMap<>();
                    tempson.put("id",id);
                    tempson.put("title",title);
                    sons.add(tempson);
                }
            }
            hashMap.put("children",sons);
        }
        return R.ok().data("list",hashMaps);
    }
}
