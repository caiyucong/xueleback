package com.caiyucong.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Course;
import com.caiyucong.eduservice.domain.Teacher;
import com.caiyucong.eduservice.service.CourseService;
import com.caiyucong.eduservice.service.TeacherService;
import lombok.var;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eduservice/indexfront")
public class IndexFrontController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    @GetMapping
    public R index() {
        var courseQuery = new QueryWrapper<Course>();
        courseQuery.orderByDesc(Course.ID);
        courseQuery.last("limit 8");
        var courseList = courseService.getList(courseQuery);
        var teacherQuery = new QueryWrapper<Teacher>();
        teacherQuery.orderByDesc(Teacher.ID);
        teacherQuery.last("limit 4");
        var teacherList = teacherService.getList(teacherQuery);
        return R.ok().putData("courseList", courseList)
                .putData("teacherList", teacherList);
    }

}
