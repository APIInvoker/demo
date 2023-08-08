package com.example.springunity.controller;

import com.example.annotation.UnifiedResponse;
import com.example.springunity.controller.biz.UserInfoBiz;
import com.example.springunity.controller.condition.QueryCondition;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.controller.ResponseVO;
import com.example.enums.AppCode;
import com.example.exception.APIException;
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
import java.util.Date;
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

    @Resource
    private UserInfoBiz userInfoBiz;

    @GetMapping(value = "pageUserInfo", name = "分页查询")
    public PageInfo<UserInfoVO> queryUserInfo(UserInfoCondition condition, int pageNum, int pageSize) {
        return userInfoBiz.queryPageInfo(condition);
    }

    public static class UserInfoCondition extends QueryCondition {
        /**
         * 主键id
         */
        private Long id;

        /**
         * 创建时间
         */
        private Date gmtCreate;

        /**
         * 修改时间
         */
        private Date gmtModified;

        /**
         * 是否删除
         */
        private Integer isDelete;

        /**
         * 用户id
         */
        private Long userId;

        /**
         * 昵称
         */
        private String nickName;

        /**
         * 性别(0:女,1:男,2:未知)
         */
        private Integer sex;

        /**
         * 出生年份
         */
        private Object bornYear;

        /**
         * 年龄
         */
        private Integer age;

        /**
         * 生日
         */
        private Date birthday;

        /**
         * 收入
         */
        private Integer income;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        public Date getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Date gmtModified) {
            this.gmtModified = gmtModified;
        }

        public Integer getIsDelete() {
            return isDelete;
        }

        public void setIsDelete(Integer isDelete) {
            this.isDelete = isDelete;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public Integer getSex() {
            return sex;
        }

        public void setSex(Integer sex) {
            this.sex = sex;
        }

        public Object getBornYear() {
            return bornYear;
        }

        public void setBornYear(Object bornYear) {
            this.bornYear = bornYear;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public Integer getIncome() {
            return income;
        }

        public void setIncome(Integer income) {
            this.income = income;
        }
    }
}
