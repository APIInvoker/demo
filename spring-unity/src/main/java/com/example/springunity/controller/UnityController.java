package com.example.springunity.controller;

import com.example.springunity.annotation.NotControllerResponseAdvice;
import com.example.springunity.entity.UserInfo;
import com.example.springunity.controller.vo.ResponseVO;
import com.example.springunity.service.UserInfoService;
import com.example.springunity.util.HttpUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author zx
 * @Date 2022/7/18 11:24
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UnityController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/heart")
    @NotControllerResponseAdvice
    public String health() {
        log.info("heart");
        log.error("heart");
        return "success";
    }

    @GetMapping("/testResponse")
    public ResponseVO testResponse() {
        log.info("testResponseVO");
        return new ResponseVO("success");
    }

    @RequestMapping("http")
    public String testHttp() {
        return HttpUtil.doGet("www.baidu.com");
    }

    @RequestMapping("redis")
    public void redis() {
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue().set("name", "zx", 60L, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println("name expire " + redisTemplate.getExpire("name"));
    }

    @GetMapping("pageUserInfo")
    public PageInfo<UserInfo> queryUserInfo() {
        return userInfoService.pageQuery();
    }
}
