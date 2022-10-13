package com.caiyucong.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.var;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Getter
public class R {
    @ApiModelProperty(value = "是否成功")
    private boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    public static R ok() {
        var r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        var r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public R setCode(Integer code) {
        this.code = code;
        return this;

    }

    public R setMessage(String message) {
        this.message = message;
        return this;
    }

    public R putData(String name, Object data) {
        this.data.put(name, data);
        return this;
    }

    public R data(Map<String, Object> data) {
        this.data = data;
        return this;
    }
}
