package com.caiyucong.eduservice.mapper;

import com.caiyucong.eduservice.domain.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caiyucong.eduservice.domain.vo.CourseWebVo;
import com.caiyucong.eduservice.domain.vo.PublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface CourseMapper extends BaseMapper<Course> {

    PublishVo selectPublishContent(String id);

    boolean removeById(String id);

    CourseWebVo selectCourseContent(String id);
}
