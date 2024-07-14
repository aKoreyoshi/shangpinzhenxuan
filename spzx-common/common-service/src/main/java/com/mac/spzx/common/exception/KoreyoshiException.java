package com.mac.spzx.common.exception;

import com.mac.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月27日, 15:28:20
 */
@Data
public class KoreyoshiException extends RuntimeException {

    private Integer code;
    private String message;
    private ResultCodeEnum resultCodeEnum;

    public KoreyoshiException(ResultCodeEnum resultCodeEnum) {
        this.resultCodeEnum = resultCodeEnum;
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    public KoreyoshiException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}