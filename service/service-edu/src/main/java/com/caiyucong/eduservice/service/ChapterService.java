package com.caiyucong.eduservice.service;

import com.caiyucong.eduservice.domain.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caiyucong.eduservice.domain.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVo> getChapterAndVideo(String id);
}
