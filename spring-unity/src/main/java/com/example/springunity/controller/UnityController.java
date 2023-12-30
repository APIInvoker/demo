package com.example.springunity.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.annotation.UnifiedResponse;
import com.example.domain.QueryCondition;
import com.example.domain.ResponseVO;
import com.example.enums.AppCode;
import com.example.enums.CommonConst;
import com.example.exception.APIException;
import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.SpringUnityApplication;
import com.example.springunity.controller.biz.UserInfoBiz;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import com.example.util.FileUtil;
import com.example.util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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
public class UnityController
{
    ObjectMapper om = new ObjectMapper();
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoBiz userInfoBiz;

    @GetMapping("/heart")
    @UnifiedResponse
    public String health()
    {
        log.info("heart");
        log.error("heart");
        return "success";
    }

    @GetMapping("/testResponse")
    public ResponseVO testResponse()
    {
        log.info("testResponseVO");
        return new ResponseVO("success");
    }

    @RequestMapping("http")
    public String testHttp()
    {
        return HttpUtil.doGet("www.baidu.com");
    }

    @RequestMapping("redis")
    public void redis()
    {
        log.info(String.valueOf(redisTemplate.opsForValue().get("name")));
        redisTemplate.opsForValue().set("name", "zx", 60L, TimeUnit.SECONDS);
        log.info(String.valueOf(redisTemplate.opsForValue().get("name")));
        log.info("name expire " + redisTemplate.getExpire("name"));
    }

    @PostMapping(value = "upload", name = "文件上传")
    public ResponseVO upload(@RequestParam("file") MultipartFile file) throws IOException
    {
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

    @GetMapping(value = "queryUserInfoPage", name = "分页查询")
    public Page<UserInfoVO> queryUserInfoPage(UserInfoCondition condition)
    {
        return userInfoBiz.queryUserInfoPage(condition, new PageInfo(condition.getPageNum(), condition.getPageSize()));
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response)
    {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        ClassLoader classLoader = SpringUnityApplication.class.getClassLoader();
        String excelTemplatePath = "templates/excel/";
        String exportOutPath = "C:/Users/zx/Downloads/";
        String templatePath = Objects.requireNonNull(classLoader.getResource(excelTemplatePath + "UserInfoTemp.xlsx")).getPath();
        System.out.println("Path of template: " + templatePath);

        String file = exportOutPath + UUID.randomUUID() + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(file).withTemplate(templatePath).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 写入列表之前的数据
            Map<String, Object> map = new HashMap<>();
            map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss").format(new Date()));
            excelWriter.fill(map, writeSheet);

            // 写入列表数据
            List<UserInfoVO> data = userInfoBiz.queryUserInfoPage(new UserInfoCondition(), PageInfo.buildExportPageInfo1()).getListData();
            excelWriter.fill(data, writeSheet);
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

    /**
     * 只能下载简单数据的Excel
     */
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws IOException
    {
        // 这里注意 有同学反应使用swagger会导致各种问题，请直接用浏览器或者用postman
        List<UserInfoVO> data = userInfoBiz.queryUserInfoPage(new UserInfoCondition(), PageInfo.buildExportPageInfo1()).getListData();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserInfoVO.class).sheet("模板").doWrite(data);
    }

    /**
     * 下载Excel
     * <p>其实是导出，再下载导出的这个文件，再删除导出的这个文件。不然我不知道复杂Excel下载怎么做。</p>
     */
    @RequestMapping("/downloadExcel")
    public void downloadFile(HttpServletResponse response)
    {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        ClassLoader classLoader = SpringUnityApplication.class.getClassLoader();
        String templatePath = Objects.requireNonNull(classLoader.getResource(CommonConst.EXCEL_TEMPLATE_PATH + "UserInfoTemp.xlsx")).getPath();

        File exportFolder = new File(CommonConst.EXCEL_EXPORT_PATH);
        if (!exportFolder.exists()) {
            boolean mkdir = exportFolder.mkdir();
        }

        String fileName = "用户信息" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String file = CommonConst.EXCEL_EXPORT_PATH + fileName + ".xlsx";
        try (ExcelWriter excelWriter = EasyExcel.write(file).withTemplate(templatePath).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet().build();
            // 写入列表之前的数据
            Map<String, Object> map = new HashMap<>();
            map.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,sss").format(new Date()));
            excelWriter.fill(map, writeSheet);

            // 写入列表数据
            List<UserInfoVO> data = userInfoBiz.queryUserInfoPage(new UserInfoCondition(), PageInfo.buildExportPageInfo1()).getListData();
            excelWriter.fill(data, writeSheet);
            // 设置列表行之后的统计行
            List<List<String>> totalListList = new ArrayList<>();
            List<String> line1 = new ArrayList<>();
            line1.add(null);
            line1.add(null);
            line1.add("1000");
            totalListList.add(line1);
            excelWriter.write(totalListList, writeSheet);
        }

        FileUtil.downloadExcelFromServer(response, fileName);
    }

    @Getter
    @Setter
    public static class UserInfoCondition extends QueryCondition
    {
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
}
