package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Teacher;
import com.caiyucong.eduservice.domain.vo.TeacherSearcher;
import com.caiyucong.eduservice.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.var;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(tags = "教师的控制器")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @PostMapping("{current}/{pageSize}")
    @ApiOperation(value = "分页查询")
    public R getAllTeacher(@ApiParam(value = "当前页", required = true) @PathVariable int current,
                           @ApiParam(value = "页大小", required = true) @PathVariable int pageSize,
                           @ApiParam(value = "查询条件") @RequestBody TeacherSearcher teacherSearcher) {
        var query = new QueryWrapper<Teacher>();
        if (!StringUtils.isEmpty(teacherSearcher.getName()))
            query.like(Teacher.NAME, teacherSearcher.getName());
        if (!StringUtils.isEmpty(teacherSearcher.getGmtCreateBegin()))
            query.ge(Teacher.GMT_CREATE, teacherSearcher.getGmtCreateBegin());
        if (!StringUtils.isEmpty(teacherSearcher.getGmtCreateEnd()))
            query.le(Teacher.GMT_CREATE, teacherSearcher.getGmtCreateEnd());
        if (!StringUtils.isEmpty(teacherSearcher.getLevel()))
            query.eq(Teacher.LEVEL, teacherSearcher.getLevel());
        var page = new Page<Teacher>(current, pageSize);
        teacherService.page(page, query);
        return R.ok().putData("records", page.getRecords())
                .putData("total", page.getTotal())
                .putData("current", page.getCurrent())
                .putData("size", page.getSize());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "通过id获取教师的信息")
    public R getById(@ApiParam(value = "教师的id", required = true)
                     @PathVariable String id) {
        var teacher = teacherService.getById(id);
        if (teacher != null)
            return R.ok().putData("teacher", teacher);
        return R.error().setMessage("未找到这个id的教师");
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "通过id删除教师")
    public R deleteById(@ApiParam(value = "教师的id", required = true) @PathVariable String id) {
        if (teacherService.removeById(id))
            return R.ok();
        return R.error();
    }

    @PostMapping
    @ApiOperation(value = "添加教师")
    public R addTeacher(@RequestBody Teacher teacher) {
        if (teacherService.save(teacher))
            return R.ok();
        return R.error();
    }

    @PutMapping
    @ApiOperation(value = "修改教师")
    public R updateTeacher(@RequestBody Teacher teacher) {
        if (teacherService.updateById(teacher))
            return R.ok();
        return R.error();
    }

    @GetMapping
    public R finAll() {
        return R.ok().putData("list", teacherService.list(null));
    }

}

