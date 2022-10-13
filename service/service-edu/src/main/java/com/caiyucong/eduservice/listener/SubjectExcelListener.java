package com.caiyucong.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Subject;
import com.caiyucong.eduservice.domain.excel.SubjectData;
import com.caiyucong.eduservice.service.SubjectService;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import lombok.var;

import java.util.ArrayList;
import java.util.List;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    private final SubjectService subjectService;

    private final List<Subject> subjects = new ArrayList<>();

    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null)
             throw new MyCustomException(2001,"空的Excel表格");
        var oneSubject = exitsSubject(subjectData.getOneSubject(), "0");
        if (oneSubject == null) {
            oneSubject = new Subject();
            oneSubject.setTitle(subjectData.getOneSubject());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }
        var towSubject = exitsSubject(subjectData.getTowSubject(), oneSubject.getId());
        if (towSubject == null) {
            towSubject = new Subject();
            towSubject.setParentId(oneSubject.getId());
            towSubject.setTitle(subjectData.getTowSubject());
            subjects.add(towSubject);
        }
    }

    private Subject exitsSubject(String title, String pid) {
        var queryWrapper = new QueryWrapper<Subject>();
        queryWrapper.eq(Subject.TITLE, title);
        queryWrapper.eq(Subject.PARENT_ID, pid);
        return subjectService.getOne(queryWrapper);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        subjectService.saveBatch(subjects);
    }
}
