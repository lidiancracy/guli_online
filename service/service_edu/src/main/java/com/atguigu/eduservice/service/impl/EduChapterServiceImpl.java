package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author 83799
* @description 针对表【edu_chapter(课程)】的数据库操作Service实现
* @createDate 2022-10-26 15:49:39
*/
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter>
    implements EduChapterService{
    /**
     * 根据courseid获取所有video信息
     * @param courseId
     * @return
     */
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService chapterService;
    @Cacheable(key = "#courseId",cacheNames = "chaptercont")
    @Override
    public ArrayList<HashMap<String, Object>> getallvideos(String courseId) {
        ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<>();
        /**
         * 根据courseid查出所有章节
         */
        LambdaQueryWrapper<EduChapter> qw = new LambdaQueryWrapper<>();
        qw.eq(EduChapter::getCourseId,courseId);
        List<EduChapter> charpterlist = chapterService.list(qw);
        if(charpterlist!=null&& !charpterlist.isEmpty()){
            for (EduChapter eduChapter : charpterlist) {
                HashMap<String, Object> tempmap = new HashMap<>();
                tempmap.put("id",eduChapter.getId());
                tempmap.put("title",eduChapter.getTitle());
                String csid = eduChapter.getCourseId();
                String chapid = eduChapter.getId();
                LambdaQueryWrapper<EduVideo> wq2 = new LambdaQueryWrapper<>();
                wq2.eq(EduVideo::getCourseId,csid).eq(EduVideo::getChapterId,chapid);
                List<EduVideo> videolist = eduVideoService.list(wq2);
                if(videolist!=null&& !videolist.isEmpty()){
                    ArrayList<HashMap<String, Object>> hashMaps1 = new ArrayList<>();
                    for (EduVideo eduVideo : videolist) {
                        HashMap<String, Object> tempvideomap = new HashMap<>();
                        tempvideomap.put("id",eduVideo.getId());
                        tempvideomap.put("title",eduVideo.getTitle());
                        hashMaps1.add(tempvideomap);
                    }
                    tempmap.put("children",hashMaps1);
                }
                hashMaps.add(tempmap);
            }
        }
        return hashMaps;
    }
}




