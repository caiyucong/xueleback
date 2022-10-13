package com.caiyucong.eduservice.service.impl;

import com.caiyucong.eduservice.domain.Comment;
import com.caiyucong.eduservice.mapper.CommentMapper;
import com.caiyucong.eduservice.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
