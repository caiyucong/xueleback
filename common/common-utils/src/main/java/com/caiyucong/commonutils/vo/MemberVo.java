package com.caiyucong.commonutils.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName MemberVo
 * @Description TODO 会员信息
 * @Author ZhangHao
 * @Date 2022/9/26 15:56
 * @Version: 1.0
 */
@Data
public class MemberVo {
    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员头像")
    private String avatar;
}
