package com.example.springunity.advice;

import com.example.springunity.enums.ResultCode;
import com.example.springunity.exception.APIException;
import com.example.springunity.pojo.vo.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler({BindException.class})
    public ResultVO MethodArgumentNotValidExceptionHandler(BindException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        return new ResultVO(ResultCode.VALIDATE_ERROR, objectError.getDefaultMessage());
    }

    @ExceptionHandler(APIException.class)
    public ResultVO APIExceptionHandler(APIException e) {
        // log.error(e.getMessage(), e); 由于还没集成日志框架，暂且放着，写上TODO
        return new ResultVO(e.getCode(), e.getMsg(), e.getMessage());
    }
}
