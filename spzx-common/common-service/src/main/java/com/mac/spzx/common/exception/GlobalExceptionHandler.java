package com.mac.spzx.common.exception;

import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月27日, 15:24:11
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error() {
        return Result.build(null, ResultCodeEnum.SYSTEM_ERROR);
    }

    // 自定义异常处理
    @ExceptionHandler(KoreyoshiException.class)
    @ResponseBody
    public Result error(KoreyoshiException e) {
        return Result.build(null, e.getResultCodeEnum());
    }
}