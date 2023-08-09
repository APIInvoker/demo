package com.example.domain;

import com.example.enums.ResponseCode;

import java.util.Objects;

/**
 * @author zx
 * @since 2022/7/18 11:13
 */
public class ResponseVO {
    /**
     * 状态码
     */
    private int code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private Object data;

    /**
     * 手动设置返回vo
     */
    public ResponseVO(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 默认返回成功状态码，数据对象
     *
     * @param data 要返回的数据
     */
    public ResponseVO(Object data) {
        code = ResponseCode.SUCCESS.getCode();
        msg = ResponseCode.SUCCESS.getMsg();
        this.data = data;
    }

    /**
     * 返回指定状态码，数据对象
     *
     * @param statusCode 状态码
     * @param data       要返回的数据
     */
    public ResponseVO(StatusCode statusCode, Object data) {
        code = statusCode.getCode();
        msg = statusCode.getMsg();
        this.data = data;
    }

    /**
     * 只返回状态码
     *
     * @param statusCode 状态码
     */
    public ResponseVO(StatusCode statusCode) {
        code = statusCode.getCode();
        msg = statusCode.getMsg();
        data = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseVO responseVO = (ResponseVO) o;
        return code == responseVO.code && Objects.equals(msg, responseVO.msg) && Objects.equals(data, responseVO.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, msg, data);
    }

    @Override
    public String toString() {
        return "ResponseVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
