package com.caiyucong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Course;
import com.caiyucong.eduservice.domain.CourseDescription;
import com.caiyucong.eduservice.domain.vo.CourseVo;
import com.caiyucong.eduservice.domain.vo.CourseWebVo;
import com.caiyucong.eduservice.domain.vo.PublishVo;
import com.caiyucong.eduservice.mapper.CourseMapper;
import com.caiyucong.eduservice.service.CourseDescriptionService;
import com.caiyucong.eduservice.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Override
    public PublishVo getPublish(String id) {
        return baseMapper.selectPublishContent(id);
    }

    @Override
    @Transactional
    public String add(CourseVo courseVo) {
        var course = new Course();
        BeanUtils.copyProperties(courseVo, course);
        if (!save(course)) {
            throw new MyCustomException(20001, "添加课时失败！");
        }
        var courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseVo.getDescription());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }

    @Override
    @Cacheable(value = "course", key = "'getList'")
    public List<Course> getList(QueryWrapper<Course> courseQuery) {
        return list(courseQuery);
    }

    @Override
    public CourseWebVo getCourse(String id) {
        return baseMapper.selectCourseContent(id);
    }

    @Override
    @Transactional
    public String update(CourseVo courseVo) {
        var course = new Course();
        BeanUtils.copyProperties(courseVo, course);
        if (!updateById(course)) {
            throw new MyCustomException(20001, "修改课时失败！");
        }
        var courseDescription = new CourseDescription();
        courseDescription.setDescription(courseVo.getDescription());
        courseDescription.setId(course.getId());
        courseDescriptionService.updateById(courseDescription);
        return course.getId();
    }

    @Override
    public boolean deleteById(String id) {
        return baseMapper.removeById(id);
    }
}

