package com.caiyucong.eduservice.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class PublishVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String teacher;

    private Integer lessonNum;

    private String subjectParent;

    private String subject;

    private BigDecimal price;

    private String cover;


}
