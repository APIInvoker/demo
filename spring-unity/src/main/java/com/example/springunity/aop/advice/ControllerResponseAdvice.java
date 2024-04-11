package com.example.springunity.aop.advice;

import com.example.annotation.DoNotUnify;
import com.example.domain.ResponseVO;
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
        // response是ResponseVO类型，或者注释了@DoNotUnify的都不进行统一封装
        return !(methodParameter.getParameterType().isAssignableFrom(ResponseVO.class) || methodParameter.hasMethodAnnotation(DoNotUnify.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, @Nonnull MediaType selectedContentType, @Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nonnull ServerHttpRequest request, @Nonnull ServerHttpResponse response)
    {
        /*
        方式一
        String类型不能直接包装，因为MVC框架在侦测到返回为String时会使用StringHttpMessageConverter而不是MappingJackson2HttpMessageConverter。
        StringHttpMessageConverter不支持text/plain。
        所以需要手动生成JSON字符串返回。
        但是手动生成字符串不够优雅，嘿嘿。而且会有反斜杠的问题，所以推荐方式二。
        */
        /*if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                // 将数据包装在ResponseVO里后转换为json串进行返回
                System.out.println("进行resp封装");
                return objectMapper.writeValueAsString(new ResponseVO(body));
            } catch (JsonProcessingException e) {
                throw new APIException(ResponseCode.RESPONSE_PACK_ERROR, e.getMessage());
            }
        }

        // 否则直接包装成ResponseVO返回
        return new ResponseVO(body);*/

        // 方式二(要搭配com.example.springunity.config.WebConfiguration.configureMessageConverters使用)
        if (!(body instanceof ResponseVO)) {
            return new ResponseVO(body);
        }
        return body;
    }
}
