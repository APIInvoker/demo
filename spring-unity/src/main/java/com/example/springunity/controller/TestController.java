package com.example.springunity.controller;

import com.alibaba.fastjson2.JSON;
import com.example.springunity.entity.UserInfo;
import com.example.springunity.service.UserInfoService;
import com.example.springunity.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx
 * @createTime 2022/8/9 20:55
 */
@RestController
@RequestMapping("test")
@Slf4j
public class TestController {
    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("http")
    public String testHttp() {
        return HttpUtil.doGet("www.baidu.com");
    }

    @RequestMapping("userInfo")
    public String queryUserInfo() {
        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        for (UserInfo userInfo : userInfoList) {
            log.info(JSON.toJSONString(userInfo));
        }
        return userInfoList.toString();
    }
}
