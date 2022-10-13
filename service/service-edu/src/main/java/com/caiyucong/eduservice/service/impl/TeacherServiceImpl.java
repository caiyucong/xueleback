package com.caiyucong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Teacher;
import com.caiyucong.eduservice.mapper.TeacherMapper;
import com.caiyucong.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.val;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    @Cacheable(value = "teacher", key = "'getList'")
    public List<Teacher> getList(QueryWrapper<Teacher> teacherQuery) {
        return list(teacherQuery);
    }
}

