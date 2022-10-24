package com.atguigu.ossservice.controller;

import com.atguigu.R.R;
import com.atguigu.ossservice.service.ossservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName osscontroller
 * @Description 上传文件
 * @Date 2022/10/24 17:47
 */
@RestController
@RequestMapping("/ossservice/fileoss")
@CrossOrigin
public class osscontroller {
    /**
     * 上传头像
     * @return
     */
    @Autowired
    private ossservice ossservice;
    @RequestMapping(value = "/upload",method = {RequestMethod.GET,RequestMethod.POST})
    public R upload(MultipartFile file){
        if(file.isEmpty()){
            return null;
        }
        String filename = file.getOriginalFilename();
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
        filename =  date+System.currentTimeMillis()+filename.substring(filename.lastIndexOf("."));
        String name = null;
        try {
            name = ossservice.upload(file.getInputStream(), filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().data("url",name);

    }
}
