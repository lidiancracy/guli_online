package com.atguigu.eduservice.service.impl;

import com.atguigu.Exception.lidianException;
import com.atguigu.eduservice.client.videoclient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.eduservice.vo.CourseFrontVo;
import com.atguigu.eduservice.vo.CoursePublishVo;
import com.atguigu.eduservice.vo.CourseWebVo;
import com.atguigu.eduservice.vo.courseVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
* @author 83799
* @description 针对表【edu_course(课程)】的数据库操作Service实现
* @createDate 2022-10-26 15:49:39
*/
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse>
    implements EduCourseService{
    /**
     * 添加课程
     * @param courceInfo
     * @return
     */
    @Autowired
    EduCourseDescriptionService despservice;
    @Override
    public String addcourse(courseVo courceInfo) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setIsDeleted(0);
        BeanUtils.copyProperties(courceInfo,eduCourse);
        save(eduCourse);
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescription.setDescription(courceInfo.getDescription());
        despservice.save(eduCourseDescription);
        return eduCourse.getId();
    }

    /**
     * 修改课程
     * @param courseInfoForm
     */
    @Autowired
    EduCourseService eduCourseService;
    @Override
    public void updateCourseInfo(courseVo courseInfoForm) {
        //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        boolean update = eduCourseService.updateById(eduCourse);
        if (!update){
            throw new lidianException(20001,"修改课程信息失败");
        }

        //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        despservice.updateById(eduCourseDescription);

    }

    /**
     * 显示第三步课程信息
     * @param id course id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        //调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * 删除课程
     * todo 删除课程同时删除该课程所有小结视频
     * @param courseId
     */
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    videoclient videoclient;

    @Override
    public void removeCourse(String courseId) {
        //===========阿里云删除操作
        LambdaQueryWrapper<EduVideo> vwq = new LambdaQueryWrapper<>();
        vwq.eq(EduVideo::getCourseId,courseId);
        List<EduVideo> list = eduVideoService.list(vwq);
        if(list!=null&& !list.isEmpty()){
            ArrayList<String> strings = new ArrayList<>();
            for (EduVideo eduVideo : list) {
                String videoSourceId = eduVideo.getVideoSourceId();
                if(!videoSourceId.isEmpty()){
                    strings.add(videoSourceId);
                }
            }
            videoclient.deleteBatch(strings);
        }

        //===========数据库删除操作
        LambdaQueryWrapper<EduVideo> wqvideo = new LambdaQueryWrapper<>();
        wqvideo.eq(EduVideo::getCourseId,courseId);
        //1 根据课程id删除小节
        eduVideoService.remove(wqvideo);
        LambdaQueryWrapper<EduChapter> wqchapter = new LambdaQueryWrapper<>();
        wqchapter.eq(EduChapter::getCourseId,courseId);
        //2 根据课程id删除章节
        eduChapterService.remove(wqchapter);

        //3 根据课程id删除描述
        despservice.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0) { //失败返回
            throw new lidianException(20001,"删除失败");
        }
    }

    /**
     * 条件查询课程
     * @param
     * @param courseFrontVo
     * @return
     */
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        LambdaQueryWrapper<EduCourse> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq(EduCourse::getSubjectParentId,courseFrontVo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getSubjectId())) { //二级分类
            wrapper.eq(EduCourse::getSubjectId,courseFrontVo.getSubjectId());
        }
        if(!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) { //关注度
            wrapper.orderByDesc(EduCourse::getBuyCount);
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) { //最新
            wrapper.orderByDesc(EduCourse::getGmtCreate);
        }

        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {//价格
            wrapper.orderByDesc(EduCourse::getPrice);
        }

        page(pageParam,wrapper);
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();//下一页
        boolean hasPrevious = pageParam.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;


    }

    /**
     * 查询课程基本信息
     * @param courseId
     * @return
     */
    @Autowired
    EduCourseMapper eduCourseMapper;
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        CourseWebVo courseWebVo=eduCourseMapper.getinfobycid(courseId);
        return courseWebVo;
    }
}




