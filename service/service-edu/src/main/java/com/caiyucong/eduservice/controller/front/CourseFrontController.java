package com.caiyucong.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.R;
import com.caiyucong.commonutils.vo.CourseVoOrder;
import com.caiyucong.eduservice.client.OrderClient;
import com.caiyucong.eduservice.domain.Course;
import com.caiyucong.eduservice.domain.vo.CourseQueryVo;
import com.caiyucong.eduservice.service.CourseService;
import com.caiyucong.eduservice.service.TeacherService;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Resource
    private CourseService courseService;

    @Resource
    private TeacherService teacherService;

    @Resource
    private OrderClient orderClient;

    @PostMapping("/{current}/{pageSize}")
    public R getCourseList(@PathVariable Long current, @PathVariable Long pageSize,
                           @RequestBody(required = false) CourseQueryVo courseQueryVo) {
        val queryWrapper = new QueryWrapper<Course>();
        if (!StringUtils.isEmpty(courseQueryVo.getSort()))
            queryWrapper.orderByDesc(courseQueryVo.getSort());
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectParentId()))
            queryWrapper.eq(Course.SUBJECT_PARENT_ID, courseQueryVo.getSubjectParentId());
        if (!StringUtils.isEmpty(courseQueryVo.getSubjectId()))
            queryWrapper.eq(Course.SUBJECT_ID, courseQueryVo.getSubjectId());
        val page = new Page<Course>(current, pageSize);
        courseService.page(page, queryWrapper);
        return R.ok().putData("records", page.getRecords())
                .putData("total", page.getTotal())
                .putData("current", page.getCurrent())
                .putData("size", page.getSize())
                .putData("hasNext", page.hasNext())
                .putData("hasPrevious", page.hasPrevious())
                .putData("pages", page.getPages());
    }

    @GetMapping("{id}")
    public R getCourse(@PathVariable String id, HttpServletRequest request) {
        boolean isBuy = false;
        val memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if (!memberIdByJwtToken.equals("")) {
            isBuy = orderClient.isBuy(id, memberIdByJwtToken);
        }
        return R.ok().putData("course", courseService.getCourse(id))
                .putData("isBuy", isBuy);
    }

    @GetMapping("info/{id}")
    public CourseVoOrder getCourseInfo(@PathVariable String id) {
        val course = courseService.getById(id);
        if (course == null) {
            return null;
        }
        val teacher = teacherService.getById(course.getTeacherId());
        val courseVoOrder = new CourseVoOrder();
        BeanUtils.copyProperties(course, courseVoOrder);
        courseVoOrder.setTeacherName(teacher.getName());
        return courseVoOrder;
    }
}
