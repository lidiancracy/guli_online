package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.eduservice.vo.ChapterVo;
import com.atguigu.eduservice.vo.VideoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import org.springframework.beans.BeanUtils;
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

    /**
     * 根据课程id查询所有章节
     * @param courseId
     * @return
     */

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id",courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2 根据课程id查询课程里面所有的小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合，用于最终封装数据
        List<ChapterVo> finalList = new ArrayList<>();

        //3 遍历查询章节list集合进行封装
        //遍历查询章节list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //eduChapter对象值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);

            //创建集合，用于封装章节的小节
            List<VideoVo> videoList = new ArrayList<>();

            //4 遍历查询小节list集合，进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断：小节里面chapterid和章节里面id是否一样
                if(eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    //放到小节封装集合
                    videoList.add(videoVo);
                }
            }
            //把封装之后小节list集合，放到章节对象里面
            chapterVo.setChildren(videoList);
        }
        return finalList;
    }
}




