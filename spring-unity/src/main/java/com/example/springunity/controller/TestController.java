package com.example.springunity.controller;

import com.example.springunity.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zx
 * @createTime 2022/8/9 20:55
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @RequestMapping("http")
    public String testHttp() {
        String result = HttpUtil.doGet("www.baidu.com");
        return result;
    }
}
