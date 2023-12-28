package com.example.springunity.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.annotation.UnifiedResponse;
import com.example.springunity.SpringUnityApplication;
import com.example.springunity.controller.biz.UserInfoBiz;
import com.example.domain.QueryCondition;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.domain.ResponseVO;
import com.example.enums.AppCode;
import com.example.exception.APIException;
import com.example.springunity.service.UserInfoService;
import com.example.util.HttpUtil;
import com.example.page.Page;
import com.example.page.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @GetMapping(value = "queryUserInfoPage", name = "分页查询")
    public Page<UserInfoVO> queryUserInfoPage(UserInfoCondition condition) {
        return userInfoBiz.queryUserInfoPage(condition, new PageInfo(condition.getPageNum(), condition.getPageSize()));
    }

    @Getter
    @Setter
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
    }

    @GetMapping("/export")
    public void export() {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        ClassLoader classLoader = SpringUnityApplication.class.getClassLoader();
        String excelTemplatePath = "templates/excel/";
        String exportOutPath = "C:/Users/zx/Downloads/";
        String templatePath = Objects.requireNonNull(classLoader.getResource(excelTemplatePath + "UserInfoTemp.xlsx")).getPath();
        System.out.println("Path of template: " + templatePath);

        try (ExcelWriter excelWriter = EasyExcel.write(exportOutPath + UUID.randomUUID() + ".xlsx").withTemplate(templatePath).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 写入列表之前的数据
            Map<String, Object> map = new HashMap<>();
            map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss").format(new Date()));
            excelWriter.fill(map, writeSheet);

            // 写入列表数据
            excelWriter.fill(userInfoBiz.queryUserInfoPage(new UserInfoCondition(), PageInfo.buildExportPageInfo1()).getListData(), writeSheet);
            // 设置列表行之后的统计行
            List<List<String>> totalListList = new ArrayList<>();
            List<String> line1 = new ArrayList<>();
            line1.add(null);
            line1.add(null);
            line1.add("1000");
            totalListList.add(line1);
            excelWriter.write(totalListList, writeSheet);
        }
    }
}
