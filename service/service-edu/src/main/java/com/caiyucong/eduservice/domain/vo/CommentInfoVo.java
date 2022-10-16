package com.caiyucong.eduservice.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName CourseInfoVo
 * @Description TODO 评论查询信息
 * @Author ZhangHao
 * @Date 2022/9/16 14:59
 * @Version: 1.0
 */
@ApiModel(value = "评论基本信息", description = "评论基本信息的表单对象")
@Data
public class CommentInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "评论ID")
    private String id;
    @ApiModelProperty(value = "评论人昵称")
    private String nickName;
    @ApiModelProperty(value = "评论课程")
    private String courseTitle;
    @ApiModelProperty(value = "课程讲师")
    private String teacherName;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "评论时间")
    private Date gmtCreate;
}
