package com.example.springunity.enums;

import com.example.springunity.controller.vo.common.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppCode implements StatusCode {
    APP_ERROR(2000, "业务异常"),
    PRICE_ERROR(2001, "价格异常");

    private final int code;
    private final String msg;
}
