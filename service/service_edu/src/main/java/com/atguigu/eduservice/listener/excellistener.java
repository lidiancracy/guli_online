package com.atguigu.eduservice.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.Exception.lidianException;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.eduservice.vo.category_lesson;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName excellistener
 * @Description TODO
 * @Date 2022/10/24 20:25
 */
@Component
public class excellistener extends AnalysisEventListener {
    @Autowired
    EduSubjectService eduSubjectService;
    // 一行一行去读取excel中的内容(表头不会去读取)
    @Override
    public void invoke(Object data, AnalysisContext context) {
        /**
         * 预处理
         */
        if(data == null) {
            throw new lidianException(20001,"文件数据为空");
        }
        category_lesson cl=null;
        if(data instanceof category_lesson){
             cl= (category_lesson) data;
        }else {
            throw new lidianException(20001,"导入文件column不符合格式要求");
        }
        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        String cate_1st = cl.getCate_1st();
        String cate_2cd = cl.getCate_2cd();
        LambdaQueryWrapper<EduSubject> qw = new LambdaQueryWrapper<EduSubject>();
        qw.eq(EduSubject::getTitle,cate_1st);
        List<EduSubject> list = eduSubjectService.list(qw);
        if(list==null||list.isEmpty()){
            /**
             * 如果没有这个一级目录，那我就插入一级和二级目录
             */
            EduSubject eduSubject_1 = new EduSubject();
            eduSubject_1.setTitle(cate_1st);
            eduSubject_1.setParentId("0");
            eduSubjectService.save(eduSubject_1);
            EduSubject eduSubject_2 = new EduSubject();
            eduSubject_2.setTitle(cate_2cd);
            eduSubject_2.setParentId(eduSubject_1.getId());
            eduSubjectService.save(eduSubject_2);
        }else {
            /**
             * 如果有这个一级目录
             * 判断有没有这个二级目录，有的话不添加，没有添加
             */
            EduSubject eduSubject = list.get(0);
            String parentid = eduSubject.getId();
            LambdaQueryWrapper<EduSubject> qw2 = new LambdaQueryWrapper<>();
            qw2.eq(EduSubject::getParentId,parentid)
                    .eq(EduSubject::getTitle,cate_2cd);
            List<EduSubject> listcd = eduSubjectService.list(qw2);
            if(listcd==null||listcd.isEmpty()){
                /**
                 * 如果没有这个二级目录 添加
                 */
                EduSubject eduSubject_2 = new EduSubject();
                eduSubject_2.setTitle(cate_2cd);
                eduSubject_2.setParentId(parentid);
                eduSubjectService.save(eduSubject_2);
            }

        }


    }


    // 读取完成之后做的内容
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}

