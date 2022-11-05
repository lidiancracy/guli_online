package com.atguigu.eduservice.controller;

import com.atguigu.Exception.lidianException;
import com.atguigu.R.R;
import com.atguigu.eduservice.client.videoclient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName videoController
 * @Description TODO
 * @Date 2022/10/26 20:44
 */
@Api(description = "每章节小结管理")
@RestController
@RequestMapping("/eduservice/video")

public class videoController {

    @Autowired
    private EduVideoService videoService;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    // TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除

    /**
     * @author: 83799
     * @date: 2022/10/28 14:56
     * @description: 我们在videoservice实现的是点击X，删除视频
     * 这里我们点击小节的X删除视频，根据小节获取视频sourseid，调用服务即可删除
     */
    @Autowired
    videoclient videoclient;
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        EduVideo byId = videoService.getById(id);
        if(byId==null){
            throw  new lidianException(20001,"小节不存在");
        }
        if(!byId.getVideoSourceId().isEmpty()){
            videoclient.removeAlyVideo(byId.getVideoSourceId());
        }
        videoService.removeById(id);
        return R.ok();
    }



}
