package com.atguigu.eduservice.controller;

import com.atguigu.R.R;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @ClassName charpterController
 * @Description TODO
 * @Date 2022/10/26 19:15
 */
@Api(description = "章节管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class charpterController {
    /**
     * 根据courseid获取所有章节视频信息
     */
    @Autowired
    EduChapterService eduChapterService;

    @GetMapping("/getChapterVideo/{courseId}")
    public R getallchaptermsg(@PathVariable String courseId) {
        ArrayList<HashMap<String, Object>> info =eduChapterService.getallvideos(courseId);
        return R.ok().data("allChapterVideo",info);
    }


    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    //根据章节id查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.removeById(chapterId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }

}
