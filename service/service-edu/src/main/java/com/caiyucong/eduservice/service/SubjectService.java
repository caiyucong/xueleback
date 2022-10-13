package com.caiyucong.eduservice.service;

import com.caiyucong.eduservice.domain.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caiyucong.eduservice.domain.tree.SubjectTree;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface SubjectService extends IService<Subject> {

    void addExcel(InputStream inputStream);

    List<SubjectTree> getOneSubject();
}
