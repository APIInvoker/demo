package com.example.springunity.config;

import com.example.filter.AFilter;
import com.example.filter.BFilter;
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
public class FilterConfig
{
    @Bean
    public FilterRegistrationBean<HttpTraceIdFilter> regFilter0()
    {
        FilterRegistrationBean<HttpTraceIdFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        HttpTraceIdFilter httpTraceIdFilter = new HttpTraceIdFilter();
        filterRegistrationBean.setFilter(httpTraceIdFilter);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("httpTraceIdFilter");// 设置过滤器名称
        filterRegistrationBean.setOrder(0);// 设置过滤器顺序，越小越优先
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AFilter> regFilter1()
    {
        FilterRegistrationBean<AFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        AFilter filterA = new AFilter();
        filterRegistrationBean.setFilter(filterA);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("filterA");// 设置过滤器名称
        filterRegistrationBean.setOrder(1);// 设置过滤器顺序，越小越优先
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<BFilter> regFilter2()
    {
        FilterRegistrationBean<BFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        BFilter filterB = new BFilter();
        filterRegistrationBean.setFilter(filterB);
        filterRegistrationBean.addUrlPatterns("*");// 配置过滤规则
        filterRegistrationBean.addInitParameter("name", "hahaha");// 设置init参数
        filterRegistrationBean.setName("filterB");// 设置过滤器名称
        filterRegistrationBean.setOrder(2);// 设置过滤器顺序，越小越优先
        return filterRegistrationBean;
    }
}
