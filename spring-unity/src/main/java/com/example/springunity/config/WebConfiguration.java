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
        以解决在统一封装返回对象操作中，在对String做特殊处理时，手动生成的JSON字符串中存在反斜杠的问题。
         */
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}