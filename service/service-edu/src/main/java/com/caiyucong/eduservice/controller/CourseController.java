package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Course;
import com.caiyucong.eduservice.domain.CourseDescription;
import com.caiyucong.eduservice.domain.vo.CourseQuery;
import com.caiyucong.eduservice.domain.vo.CourseVo;
import com.caiyucong.eduservice.service.CourseDescriptionService;
import com.caiyucong.eduservice.service.CourseService;
import com.caiyucong.eduservice.service.VideoService;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import lombok.var;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/course")
public class CourseController {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private VideoService videoService;

    @PostMapping
    public R addCourse(@RequestBody CourseVo courseVo) {
        return R.ok().putData("courseId", courseService.add(courseVo));
    }

    @GetMapping("{id}")
    public R getCourse(@PathVariable String id) {
        var course = courseService.getById(id);
        if (course == null) {
            throw new MyCustomException(20001, "没有查询到该课程");
        }
        var courseVo = new CourseVo();
        BeanUtils.copyProperties(course, courseVo);
        var description = courseDescriptionService
                .getOne(new QueryWrapper<CourseDescription>().eq(CourseDescription.ID, course.getId()));
        courseVo.setDescription(description.getDescription());
        return R.ok().putData("course", courseVo);
    }

    @PutMapping
    public R updateCourse(@RequestBody CourseVo courseVo) {
        return R.ok().putData("courseId", courseService.update(courseVo));
    }

    @GetMapping("publish/{id}")
    public R getPublish(@PathVariable String id) {
        val publish = courseService.getPublish(id);
        if (publish == null) {
            throw new MyCustomException(20001, "没有查询到该课程");
        }
        return R.ok().putData("data", publish);
    }

    @PostMapping("publish")
    public R publish(@RequestBody Course course) {
        course.setStatus(Course.NORMAL);
        return courseService.updateById(course) ? R.ok() : R.error();
    }

    @PostMapping("page/{current}/{pageSize}")
    public R page(@RequestBody CourseQuery courseQuery, @PathVariable Integer current, @PathVariable Integer pageSize) {
        var query = new QueryWrapper<Course>();
        if (!StringUtils.isEmpty(courseQuery.getTitle()))
            query.like(Course.TITLE, courseQuery.title);
        if (!StringUtils.isEmpty(courseQuery.getStatus()))
            query.eq(Course.STATUS, courseQuery.getStatus());
        var coursePage = new Page<Course>(current, pageSize);
        courseService.page(coursePage, query);
        return R.ok().putData("records", coursePage.getRecords())
                .putData("total", coursePage.getTotal())
                .putData("size", coursePage.getSize())
                .putData("current", coursePage.getCurrent());

    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id) {
        return videoService.delete(id) ? R.ok() : R.error();

    }

    @DeleteMapping("logic/{id}")
    public R logicDelete(@PathVariable String id) {
        return courseService.removeById(id) ? R.ok() : R.error();
    }

    @ApiOperation(value = "所有课程列表")
    @GetMapping("/findAll")
    public R list(){
        List<Course> list = courseService.list(null);
        return R.ok().putData("items",list);
    }
}

