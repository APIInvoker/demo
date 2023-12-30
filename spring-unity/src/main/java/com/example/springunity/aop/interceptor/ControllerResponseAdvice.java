package com.example.springunity.aop.interceptor;

import com.example.annotation.UnifiedResponse;
import com.example.domain.ResponseVO;
import com.example.enums.ResponseCode;
import com.example.exception.APIException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Nonnull;

@RestControllerAdvice(basePackages = "com.example.springunity")
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object>
{
    @Override
    public boolean supports(MethodParameter methodParameter, @Nonnull Class<? extends HttpMessageConverter<?>> aClass)
    {
        // response是responseVO类型，或者注释了@NotControllerResponseAdvice都不进行包装
        return !(methodParameter.getParameterType().isAssignableFrom(ResponseVO.class) || methodParameter.hasMethodAnnotation(UnifiedResponse.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, @Nonnull MediaType selectedContentType, @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull ServerHttpResponse response)
    {
        // String类型不能直接包装
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseVO里后转换为json串进行返回
                return objectMapper.writeValueAsString(new ResponseVO(body));
            } catch (JsonProcessingException e) {
                throw new APIException(ResponseCode.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }
        // 否则直接包装成ResponseVO返回
        return new ResponseVO(body);
    }
}
