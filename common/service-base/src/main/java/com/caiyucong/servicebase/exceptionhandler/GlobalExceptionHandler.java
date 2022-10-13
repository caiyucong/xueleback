package com.caiyucong.servicebase.exceptionhandler;


import com.caiyucong.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().setMessage("执行了全局错误...");
    }

    //特定异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e) {
        e.printStackTrace();
        return R.error().setMessage("执行了ArithmeticException...");
    }

    //自定义异常
    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public R error(MyCustomException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().setCode(e.getCode()).setMessage(e.getMsg());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public R error(DuplicateKeyException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        if (Objects.requireNonNull(e.getMessage()).lastIndexOf("uk_name") > 0) {
            return R.error().setMessage("教师名称已经存在！");
        }
        return R.error();
    }
}
