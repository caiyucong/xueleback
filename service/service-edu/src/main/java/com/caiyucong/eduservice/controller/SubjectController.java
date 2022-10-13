package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Subject;
import com.caiyucong.eduservice.domain.tree.SubjectTree;
import com.caiyucong.eduservice.service.SubjectService;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @PostMapping
    public R uploadExcelFile(MultipartFile multipartFile) {
        try {
            subjectService.addExcel(multipartFile.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return R.error();
        }
        return R.ok();
    }

    @GetMapping
    public R getSubjectTree() {
        return R.ok().putData("list", subjectService.getOneSubject());
    }
}

