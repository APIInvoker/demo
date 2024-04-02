package com.example.springunity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        /*
        将MappingJackson2HttpMessageConverter排在StringHttpMessageConverter前面。
        以解决接口返回类型为String时，解析器对Content-Type为text/plain的返回进行解析时失败的问题
         */
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}