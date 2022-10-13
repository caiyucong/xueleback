package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Chapter;
import com.caiyucong.eduservice.domain.Video;
import com.caiyucong.eduservice.service.ChapterService;
import com.caiyucong.eduservice.service.VideoService;
import com.caiyucong.servicebase.exceptionhandler.MyCustomException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/chapter")
public class ChapterController {

    @Resource
    private ChapterService chapterService;

    @Resource
    private VideoService videoService;

    @PostMapping
    public R addChapter(@RequestBody Chapter chapter) {
        return chapterService.save(chapter) ? R.ok() : R.error();
    }

    @GetMapping("{id}")
    public R getChapterByCourseId(@PathVariable String id) {
        return R.ok().putData("list", chapterService.getChapterAndVideo(id));
    }

    @GetMapping("/chapter/{id}")
    public R getChapter(@PathVariable String id) {
        return R.ok().putData("chapter", chapterService.getById(id));
    }

    @PutMapping
    public R updateChapter(@RequestBody Chapter chapter) {
        return chapterService.updateById(chapter) ? R.ok() : R.error();
    }

    @DeleteMapping("{id}")
    public R deleteChapter(@PathVariable String id) {
        if (videoService.count(new QueryWrapper<Video>().eq(Video.CHAPTER_ID, id)) > 0) {
            throw new MyCustomException(20001, "请删除所有小节后重试");
        }
        return chapterService.removeById(id) ? R.ok() : R.error();
    }
}

