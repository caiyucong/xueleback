package com.caiyucong.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.eduservice.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface CommentService extends IService<Comment> {

    Map<String, Object> getCommentPageByCourseId(Page<Comment> pageParam, String courseId);
}
