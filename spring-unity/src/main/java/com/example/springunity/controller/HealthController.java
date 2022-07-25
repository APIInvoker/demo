package com.example.springunity.controller;

import com.example.springunity.annotation.NotControllerResponseAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengxin
 * @createTime 2022/7/18 12:24
 */
@RestController
public class HealthController {
    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }
}