package com.example.springunity.controller;

import com.alibaba.fastjson2.JSON;
import com.example.springunity.annotation.NotControllerResponseAdvice;
import com.example.springunity.pojo.ProductInfoDO;
import com.example.springunity.pojo.dto.ProductInfoDTO;
import com.example.springunity.pojo.vo.ProductInfoVO;
import com.example.springunity.pojo.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import com.example.springunity.service.impl.ProductInfoServiceImpl;
import com.example.springunity.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:24
 */
@RestController
@RequestMapping("/")
@Slf4j
public class UnityController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/health")
    @NotControllerResponseAdvice
    public String health() {
        return "success";
    }

    @RequestMapping("http")
    public String testHttp() {
        return HttpUtil.doGet("www.baidu.com");
    }

    @RequestMapping("userInfo")
    public String queryUserInfo() {
        List<UserInfoVO> userInfoList = userInfoService.queryAllUserInfo();
        for (UserInfoVO userInfo : userInfoList) {
            log.info(JSON.toJSONString(userInfo));
        }
        return userInfoList.toString();
    }

    @RequestMapping("redis")
    public void redis() {
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue().set("name", "zhengxin", 60L, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println("name expire " + redisTemplate.getExpire("name"));
    }

    @Resource
    private ProductInfoServiceImpl productInfoService;

    @PostMapping("/findByVo")
    public ProductInfoVO findByVo(@Validated @RequestBody ProductInfoDTO param) {
        ProductInfoDO productInfoDO = new ProductInfoDO();
        BeanUtils.copyProperties(param, productInfoDO);
        return productInfoService.getByProductInfo(productInfoDO);
    }
}
