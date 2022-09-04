package com.example.springunity.advice;

import com.example.springunity.enums.ResultCode;
import com.example.springunity.exception.APIException;
import com.example.springunity.pojo.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice {
    @ExceptionHandler(BindException.class)
    public ResultVO MethodArgumentNotValidExceptionHandler(BindException e) {
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO APIExceptionHandler(APIException e) {
        log.error(e.getMessage(), e);
        return new ResultVO(e.getCode(), e.getMsg(), e.getMessage());
    }
}
