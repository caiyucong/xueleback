package com.caiyucong.eduservice.mapper;

import com.caiyucong.eduservice.domain.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface VideoMapper extends BaseMapper<Video> {
    List<Video> selectVideoByChapterId(String id);
}
