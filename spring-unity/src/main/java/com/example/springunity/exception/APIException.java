package com.example.springunity.exception;

import com.example.springunity.enums.AppCode;
import com.example.springunity.vo.StatusCode;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private final int code;
    private final String msg;

    // 手动设置异常
    public APIException(StatusCode statusCode, String message) {
        // message用于用户设置抛出错误详情，例如：当前价格-5，小于0
        super(message);
        // 状态码
        code = statusCode.getCode();
        // 状态码配套的msg
        msg = statusCode.getMsg();
    }

    // 默认异常使用APP_ERROR状态码
    public APIException(String message) {
        super(message);
        code = AppCode.APP_ERROR.getCode();
        msg = AppCode.APP_ERROR.getMsg();
    }

}