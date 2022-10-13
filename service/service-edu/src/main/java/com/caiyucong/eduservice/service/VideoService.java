package com.caiyucong.eduservice.service;

import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Video;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface VideoService extends IService<Video> {

    boolean delete(String id);
}
