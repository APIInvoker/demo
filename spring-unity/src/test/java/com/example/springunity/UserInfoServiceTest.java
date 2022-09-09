package com.example.springunity;

import com.alibaba.fastjson2.JSON;
import com.example.springunity.pojo.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author zx
 * @createTime 2022/8/17 19:25
 */
@SpringBootTest
@Slf4j
public class UserInfoServiceTest {
    @Resource
    private UserInfoService userInfoService;

    @Test
    public void test1() {
    }
}
