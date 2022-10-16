package com.caiyucong.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.caiyucong.commonutils.JwtUtils;
import com.caiyucong.commonutils.R;
import com.caiyucong.commonutils.vo.MemberVo;
import com.caiyucong.eduservice.client.MemberClient;
import com.caiyucong.eduservice.domain.Comment;
import com.caiyucong.eduservice.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author caiyucong
 * @since 2022-09-16
 */
@RestController
@RequestMapping("/eduservice/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private MemberClient memberClient;

    @ApiOperation(value = "评论分页列表")
    @PostMapping("/getCommentPage/{page}/{limit}")
    public R indexPageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseId", value = "查询对象")
                    String courseId) {
        Page<Comment> pageParam = new Page<>(page, limit);
        Map<String, Object> map = commentService.getCommentPageByCourseId(pageParam, courseId);
        return R.ok().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/insertComment")
    public R saveComment(
            @ApiParam(name = "Comment", value = "评论对象")
            @RequestBody Comment comment, HttpServletRequest request) {
        //getMemberIdByJwtToken方法返回的就是会员id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return R.error().setCode(28004).setMessage("请登录");
        }
        comment.setMemberId(memberId);

        //远程调用根据id查询会员接口
        MemberVo memberVo = memberClient.getMemberByMemberId(memberId);
        comment.setNickname(memberVo.getNickname());
        comment.setAvatar(memberVo.getAvatar());

        commentService.save(comment);
        return R.ok();
    }

}

