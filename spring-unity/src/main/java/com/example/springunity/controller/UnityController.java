package com.example.springunity.controller;

import com.example.springunity.annotation.NotControllerResponseAdvice;
import com.example.springunity.pojo.dto.UserInfoDTO;
import com.example.springunity.pojo.vo.ResponseVO;
import com.example.springunity.pojo.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import com.example.springunity.util.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhengxin
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

    @RequestMapping("queryUserByCondition")
    public List<UserInfoVO> queryUserByCondition(UserInfoDTO userInfoDTO) throws JsonProcessingException {
        List<UserInfoDTO> userInfoDTOList = userInfoService.queryUserByCondition(userInfoDTO);
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        userInfoDTOList.forEach(o -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserId(o.getUserId());
            userInfoVO.setNickName(o.getNickName());
            userInfoVO.setSex(o.getSex());
            userInfoVO.setAge(o.getAge());
            userInfoVO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(o.getBirthday()));
            userInfoVO.setIncome(o.getIncome());
            userInfoVOList.add(userInfoVO);
        });
        return userInfoVOList;
    }

    @RequestMapping("redis")
    public void redis() {
        System.out.println(redisTemplate.opsForValue().get("name"));
        redisTemplate.opsForValue().set("name", "zhengxin", 60L, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("name"));
        System.out.println("name expire " + redisTemplate.getExpire("name"));
    }
}
