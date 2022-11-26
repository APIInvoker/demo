package com.example.springunity.annotation;

import java.lang.annotation.*;

/**
 * @author zx
 * @since 2022/11/16 016
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TraceIdLog {
}
