package com.example.springunity.controller;

import com.example.annotation.UnifiedResponse;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.controller.ResponseVO;
import com.example.enums.AppCode;
import com.example.springunity.exception.APIException;
import com.example.springunity.service.UserInfoService;
import com.example.util.HttpUtil;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zx
 * @since 2022/7/18 11:24
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
    @UnifiedResponse
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

    @GetMapping(value = "pageUserInfo", name = "分页查询")
    public PageInfo<UserInfoVO> queryUserInfo(UserInfoVO userInfoVO) {
        log.info("分页查询开始");
        return userInfoService.pageQuery(userInfoVO);
    }

    @PostMapping(value = "saveUserInfo", name = "保存用户信息")
    public ResponseVO insertUserInfo(@RequestBody UserInfoVO userInfoVO) {
        log.info("保存用户信息开始");
        userInfoService.saveUserInfo(userInfoVO);
        return new ResponseVO("保存成功");
    }

    @PostMapping(value = "upload", name = "文件上传")
    public ResponseVO upload(@RequestParam("file") MultipartFile file) throws IOException {
        File dir = new File("spring-unity/src/main/resources/upload");
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new APIException(AppCode.APP_ERROR, "创建文件夹失败");
            }
        }
        String absolutePath = dir.getAbsolutePath();
        String separator = File.separator;
        file.transferTo(new File(absolutePath + separator + file.getName() + ".txt"));
        return new ResponseVO("上传完成！文件名：" + file.getName() + ".txt");
    }
}
