package com.atguigu.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "科目",index = 0)
    private String sno;
    @ExcelProperty(value = "实例",index = 1)
    private String sname;
}
