package com.atguigu.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.listener.excellistener;
import com.atguigu.eduservice.vo.category_lesson;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEasyExcel {
    @Test
    public void excel() {
//        实现excel写的操作
//        1 设置写入文件夹地址和excel文件名称
//        String filename = "F:\\write.xlsx";
//        2 调用easyexcel里面的方法实现写操作
//        write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
//        EasyExcel.write(filename,DemoData.class).sheet("学生列表").doWrite(getData());


        //实现excel读操作
        String filename = "F:\\wt2.xlsx";
        EasyExcel.read(filename, category_lesson.class,new excellistener()).sheet().doRead();
    }

    //创建方法返回list集合

}
