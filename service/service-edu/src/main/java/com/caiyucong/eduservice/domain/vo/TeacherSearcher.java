package com.caiyucong.eduservice.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="TeacherSearcher对象", description="讲师搜索")
public class TeacherSearcher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "创建时间开始")
    private Date gmtCreateBegin;

    @ApiModelProperty(value = "创建时间结束")
    private Date gmtCreateEnd;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

}
