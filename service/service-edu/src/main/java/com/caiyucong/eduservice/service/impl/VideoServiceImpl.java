package com.caiyucong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.client.VideoClient;
import com.caiyucong.eduservice.domain.Chapter;
import com.caiyucong.eduservice.domain.CourseDescription;
import com.caiyucong.eduservice.domain.Video;
import com.caiyucong.eduservice.mapper.VideoMapper;
import com.caiyucong.eduservice.service.ChapterService;
import com.caiyucong.eduservice.service.CourseDescriptionService;
import com.caiyucong.eduservice.service.CourseService;
import com.caiyucong.eduservice.service.VideoService;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private CourseService courseService;

    @Resource
    private CourseDescriptionService courseDescriptionService;

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoClient videoClient;

    @Override
    @Transactional
    public boolean delete(String id) {
        List<Video> list = list(new QueryWrapper<Video>().eq(Video.COURSE_ID, id));
        StringBuilder ids = new StringBuilder();
        int i = 0;
        for (var o : list) {
            i++;
            ids.append(o.getVideoSourceId());
            if (i < list.size()) {
                ids.append(",");
            }
        }
        return courseService.deleteById(id)
                && courseDescriptionService.remove(new QueryWrapper<CourseDescription>().eq(CourseDescription.ID, id))
                && remove(new QueryWrapper<Video>().eq(Video.COURSE_ID, id))
                && chapterService.remove(new QueryWrapper<Chapter>().eq(Chapter.COURSE_ID, id))
                && Objects.equals(videoClient.deleteVideo(ids.toString()).getCode(), R.ok().getCode());
    }
}

