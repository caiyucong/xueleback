package com.caiyucong.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Course;
import com.caiyucong.eduservice.domain.Teacher;
import com.caiyucong.eduservice.service.CourseService;
import com.caiyucong.eduservice.service.TeacherService;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private CourseService courseService;

    @GetMapping("/page/{current}")
    public R getPage(@PathVariable Long current) {
        val page = new Page<Teacher>(current, 8);
        teacherService.page(page, new QueryWrapper<Teacher>()
                .orderByDesc("id"));
        return R.ok().putData("records", page.getRecords())
                .putData("current", page.getCurrent())
                .putData("hasNext", page.hasNext())
                .putData("hasPrevious", page.hasPrevious())
                .putData("total", page.getTotal())
                .putData("pages", page.getPages());
    }

    @GetMapping("{id}")
    public R getOne(@PathVariable String id) {
        val teacher = teacherService.getById(id);
        val courses = courseService.list(new QueryWrapper<Course>().eq(Course.TEACHER_ID, id));
        return R.ok().putData("teacher", teacher)
                .putData("courses", courses);
    }

}
