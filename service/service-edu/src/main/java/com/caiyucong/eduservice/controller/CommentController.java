package com.caiyucong.eduservice.controller;

import com.caiyucong.commonutils.R;
import com.caiyucong.eduservice.domain.vo.CommentQuery;
import com.caiyucong.eduservice.service.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private CommentService eduCommentService;

    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("/getCommentPageList/{current}/{limit}")
    public R commentPageList(@ApiParam(name = "current",value = "当前页",required = true)
                             @PathVariable Long current,
                             @ApiParam(name = "limit", value = "每页记录数", required = true)
                             @PathVariable Long limit,
                             @ApiParam(name = "teacherQuery",value = "查询对象")
                             @RequestBody(required = false) CommentQuery commentQuery)
    {
        return eduCommentService.pageQuery(current,limit,commentQuery);
    }


    @ApiOperation(value = "删除评论信息")
    @DeleteMapping("/deleteCommentById/{commentId}")
    public R deleteCommentById(
            @ApiParam(name = "commentId", value = "评论id", required = true)
            @PathVariable String commentId){
        boolean result = eduCommentService.removeById(commentId);
        if(result){
            return R.ok().setMessage("删除评论信息成功！");
        }else{
            return R.error().setMessage("删除评论信息失败！");
        }

    }

}

