package com.caiyucong.eduservice.mapper;

import com.caiyucong.eduservice.domain.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caiyucong.eduservice.domain.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface ChapterMapper extends BaseMapper<Chapter> {
    List<ChapterVo> selectChapterAndVideo(String courseId);
}
