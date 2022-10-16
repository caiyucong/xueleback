package com.caiyucong.eduservice.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author ZhangHao
 * @Description TODO 评论条件查询
 * @Date 17:10 2022/10/13
 * @Param
 * @return
**/
@Data
@ApiModel(value = "Comment查询对象", description = "评论查询对象封装")
public class CommentQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "会员名称")
    private String nickname;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "查询开始时间")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
    @ApiModelProperty(value = "查询结束时间")
    private String end;
}
