package com.example.springunity.aop.interceptor;

import com.example.domain.ResponseVO;
import com.example.enums.ResponseCode;
import com.example.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler(BindException.class)
    public ResponseVO MethodArgumentNotValidExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResponseVO(ResponseCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResponseVO APIExceptionHandler(APIException e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(e.getCode(), e.getMsg(), e.getMessage());
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseVO APIExceptionHandler(JsonProcessingException e) {
        log.error(e.getMessage(), e);
        return new ResponseVO(ResponseCode.RESPONSE_PACK_ERROR, e.getMessage());
    }
}
