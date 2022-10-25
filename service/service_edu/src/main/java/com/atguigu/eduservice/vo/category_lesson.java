package com.atguigu.eduservice.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * @ClassName category
 * @Description TODO
 * @Date 2022/10/24 20:27
 */
@Data
public class category_lesson {
    @ExcelProperty(value = "一级分类",index = 0)
    private String cate_1st;
    @ExcelProperty(value = "二级分类",index = 1)
    private String cate_2cd;
}


