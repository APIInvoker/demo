package com.example.springunity.annotation;

import java.lang.annotation.*;

/**
 * @author Zheng Xin
 * @since 2022/11/16 016
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceIdLog {
}
