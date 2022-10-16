package com.caiyucong.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.caiyucong.eduservice.domain.vo.CommentQuery;

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

    /**
     * @Author ZhangHao
     * @Description TODO 根据课程id分页查询评论
     * @Date 15:18 2022/9/26
     * @Param [pageParam, courseId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    Map<String, Object> getCommentPageByCourseId(Page<Comment> pageParam, String courseId);

    /**
     * @Author ZhangHao
     * @Description TODO 分页查询评论
     * @Date 17:17 2022/10/13
     * @Param [pageParam, commentQuery]
     * @return void
     **/
    R pageQuery(Long current, Long limit, CommentQuery commentQuery);

}
