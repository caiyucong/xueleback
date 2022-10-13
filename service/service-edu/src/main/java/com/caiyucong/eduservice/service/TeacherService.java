package com.caiyucong.eduservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface TeacherService extends IService<Teacher> {

    List<Teacher> getList(QueryWrapper<Teacher> teacherQuery);
}
