package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.client.VideoClient;
import com.caiyucong.eduservice.domain.Video;
import com.caiyucong.eduservice.service.VideoService;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private VideoClient videoClient;

    @PostMapping
    public R addVideo(@RequestBody Video video) {
        return videoService.save(video) ? R.ok() : R.error();
    }

    @PutMapping
    public R updateVideo(@RequestBody Video video) {
        return videoService.updateById(video) ? R.ok() : R.error();
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id) {
        return R.ok().putData("video", videoService.getById(id));
    }

    @DeleteMapping("{id}")
    public R deleteById(@PathVariable String id) {
        Video video = videoService.getById(id);
        if (videoService.removeById(id)) {
            return videoClient.deleteVideo(video.getVideoSourceId());
        }
        return R.error();
    }

    @GetMapping("source/{id}")
    public String getBySourceId(@PathVariable String id) {
        val queryWrapper = new QueryWrapper<Video>();
        queryWrapper.eq(Video.VIDEO_SOURCE_ID, id);
        val video = videoService.getOne(queryWrapper);
        return video != null ? video.getCourseId() : null;
    }

}

