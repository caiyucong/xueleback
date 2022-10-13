package com.caiyucong.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caiyucong.eduservice.domain.vo.CourseVo;
import com.caiyucong.eduservice.domain.vo.CourseWebVo;
import com.caiyucong.eduservice.domain.vo.PublishVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface CourseService extends IService<Course> {

    PublishVo getPublish(String id);

    boolean deleteById(String id);

    String update(CourseVo courseVo);

    String add(CourseVo courseVo);

    List<Course> getList(QueryWrapper<Course> courseQuery);

    CourseWebVo getCourse(String id);
}
