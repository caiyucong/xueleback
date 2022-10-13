package com.caiyucong.eduservice.service.impl;

import com.caiyucong.eduservice.domain.Chapter;
import com.caiyucong.eduservice.domain.vo.ChapterVo;
import com.caiyucong.eduservice.mapper.ChapterMapper;
import com.caiyucong.eduservice.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Override
    public List<ChapterVo> getChapterAndVideo(String id) {
        return baseMapper.selectChapterAndVideo(id);
    }
}
