package com.example.springunity.config;

import com.example.filter.FilterA;
import com.example.filter.FilterB;
import com.example.filter.HttpTraceIdFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 *
 * @author zx
 * @since 2023/1/22 022
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<HttpTraceIdFilter> regFilter0() {
        FilterRegistrationBean<HttpTraceIdFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        HttpTraceIdFilter httpTraceIdFilter = new HttpTraceIdFilter();
        filterRegistrationBean.setFilter(httpTraceIdFilter);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("httpTraceIdFilter");// 设置过滤器名称
        filterRegistrationBean.setOrder(0);// 执行次序
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<FilterA> regFilter1() {
        FilterRegistrationBean<FilterA> filterRegistrationBean = new FilterRegistrationBean<>();
        FilterA filterA = new FilterA();
        filterRegistrationBean.setFilter(filterA);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("filterA");// 设置过滤器名称
        filterRegistrationBean.setOrder(1);// 执行次序
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<FilterB> regFilter2() {
        FilterRegistrationBean<FilterB> filterRegistrationBean = new FilterRegistrationBean<>();
        FilterB filterB = new FilterB();
        filterRegistrationBean.setFilter(filterB);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("filterB");// 设置过滤器名称
        filterRegistrationBean.setOrder(2);// 执行次序
        return filterRegistrationBean;
    }
}
