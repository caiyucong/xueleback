package com.caiyucong.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.eduservice.domain.Subject;
import com.caiyucong.eduservice.domain.excel.SubjectData;
import com.caiyucong.eduservice.domain.tree.SubjectTree;
import com.caiyucong.eduservice.listener.SubjectExcelListener;
import com.caiyucong.eduservice.mapper.SubjectMapper;
import com.caiyucong.eduservice.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectService subjectService;

    @Override
    public void addExcel(InputStream inputStream) {
        EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
    }

    @Override
    public List<SubjectTree> getOneSubject() {
        var oneSubject = subjectService.list(new QueryWrapper<Subject>().eq(Subject.PARENT_ID, "0"));
        var towSubject = subjectService.list(new QueryWrapper<Subject>().ne(Subject.PARENT_ID, "0"));
        var oneSubjectList = new ArrayList<SubjectTree>();
        for (var o : oneSubject) {
            var oneSubjectTree = new SubjectTree();
            BeanUtils.copyProperties(o, oneSubjectTree);
            var children = new ArrayList<SubjectTree>();
            for (var o1 : towSubject) {
                var towSubjectTree = new SubjectTree();
                if (o1.getParentId().equals(o.getId())) {
                    BeanUtils.copyProperties(o1, towSubjectTree);
                    children.add(towSubjectTree);
                }
            }
            oneSubjectTree.setChildren(children);
            oneSubjectList.add(oneSubjectTree);
        }
        return oneSubjectList;
    }
}
