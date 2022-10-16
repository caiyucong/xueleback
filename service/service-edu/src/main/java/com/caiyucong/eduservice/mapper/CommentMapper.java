package com.caiyucong.eduservice.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.caiyucong.eduservice.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.caiyucong.eduservice.domain.vo.CommentInfoVo;
import com.caiyucong.eduservice.domain.vo.CommentQuery;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * @Author ZhangHao
     * @Description TODO 查询评论
     * @Date 9:27 2022/10/14
     * @Param [page, commentQuery]
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.atguigu.serviceedu.entity.vo.CommentInfoVo>
     **/
    IPage<CommentInfoVo> getCommentPageList(IPage<CommentInfoVo> page, CommentQuery commentQuery);

}
