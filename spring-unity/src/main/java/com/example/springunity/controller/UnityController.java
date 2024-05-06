package com.example.springunity.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.domain.QueryCondition;
import com.example.domain.ResponseVO;
import com.example.enums.AppCode;
import com.example.enums.CommonConst;
import com.example.exception.APIException;
import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.SpringUnityApplication;
import com.example.springunity.controller.biz.UserInfoBiz;
import com.example.springunity.controller.decoratorTest.Circle;
import com.example.springunity.controller.decoratorTest.Shape;
import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import com.example.util.FileUtil;
import com.example.util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    @GetMapping("/success")
    public String success()
    {
        return "success";
    }

    @GetMapping("/successString")
    public ResponseVO successString()
    {
        return new ResponseVO("successString");
    }

    @RequestMapping("testHttp")
    public void testHttp()
    {
        String s = HttpUtil.doGet("https://www.baidu.com");
        log.info(s);
    }

    @RequestMapping("testRedis")
    public void testRedis()
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

    private BlockingQueue<List<UserInfoVO>> getData()
    {
        AtomicInteger queryNum = new AtomicInteger(1);
        // 分页大小可以适当调整
        int pageSize = 100000;
        // 开启多个线程分页查数据
        int cpuNum = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10 * cpuNum, 30 * cpuNum, 40, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100), new ThreadPoolExecutor.DiscardPolicy());
        int count = userInfoBiz.queryUserInfoCount(new UserInfoCondition());
        // int count = 100000;
        log.info("总共" + count + "条数据，要查询" + (count / pageSize + 1) + "次。");
        BlockingQueue<List<UserInfoVO>> queue = new ArrayBlockingQueue<>(count / pageSize + 1);

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                List<UserInfoVO> data = userInfoBiz.queryUserInfoPage(new UserInfoCondition(), new PageInfo(queryNum.getAndIncrement(), pageSize)).getListData();
                if (!CollectionUtils.isEmpty(data)) {
                    try {
                        queue.put(data);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        Thread monitorThread = new Thread(() -> {
            do {
                System.out.println("线程池状态：" + (threadPoolExecutor.isShutdown() ? "关闭" : "开启") + " " + (threadPoolExecutor.isTerminated() ? "关闭" : "开启"));
                if (queue.size() >= ((count % pageSize) == 0 ? (count / pageSize) : (count / pageSize + 1))) {
                    System.out.println("关闭线程池");
                    threadPoolExecutor.shutdown();
                }
                try {
                    Thread.sleep(1000 * 3);
                } catch (InterruptedException e) {
                    // throw new RuntimeException(e);
                }
            }
            while (!threadPoolExecutor.isShutdown() || !threadPoolExecutor.isTerminated());
        });
        monitorThread.start();
        while (true) {
            if (threadPoolExecutor.isTerminated()) {
                System.out.println("关闭监控线程");
                monitorThread.interrupt();
                break;
            }
        }
        while (true) {
            if (!monitorThread.isAlive() && threadPoolExecutor.isTerminated()) {
                System.out.println("监控线程已关闭 线程池已关闭");
                break;
            }
        }
        return queue;
    }

    /**
     * 下载Excel
     * <p>其实是导出，再下载导出的这个文件，再删除导出的这个文件。不然我不知道复杂Excel下载怎么做。</p>
     * <p>耗时长的导出可以放到夜间执行，然后将下载提取出来单独做成一个接口。</p>
     */
    @GetMapping("downloadExcel")
    public void downloadExcel(HttpServletResponse response)
    {
        String fileName = "用户信息" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fullFileName = CommonConst.EXCEL_EXPORT_PATH + fileName + ".xlsx";
        long a = System.currentTimeMillis();
        BlockingQueue<List<UserInfoVO>> data = getData();
        System.out.println("查询数据耗时：" + (System.currentTimeMillis() - a) + "毫秒");
        int size = data.size();
        long b = System.currentTimeMillis();
        try (ExcelWriter excelWriter = EasyExcel.write(fullFileName, UserInfoVO.class).build()) {
            // 这里注意 如果同一个sheet只要创建一次
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来
            for (int i = 0; i < size; i++) {
                // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
                System.out.println("第" + i + "次写入");
                List<UserInfoVO> data1 = data.take();
                excelWriter.write(data1, writeSheet);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("导出Excel耗时：" + (System.currentTimeMillis() - b) + "毫秒");
        FileUtil.downloadExcelFromServer(response, fileName);
        System.out.println("下载文件耗时：" + (System.currentTimeMillis() - b) + "毫秒");
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

    String str1 = "abc";

    /**
     * 关于堆和常量池的字符串常量测试
     */
    @GetMapping("stack")
    public void test3()
    {
        String str2 = new String("ab") + new String("c");
        str2.intern();
        String str3 = "abc";

        System.out.println(str1 == str2);
        System.out.println(str2 == str3);
        System.out.println(str1 == str3);

        System.out.println(str1.equals(str2));
        System.out.println(str2.equals(str3));
        System.out.println(str1.equals(str3));
    }

    /**
     * 动态代理
     */
    @GetMapping("proxyTest")
    public void proxyTest()
    {
        Shape shape = (Shape) getProxy(new Circle());
        shape.draw();
    }

    /**
     * 过去动态代理对象
     */
    private Object getProxy(final Object target)
    {
        /*代理对象的方法最终都会被JVM导向它的invoke方法*/
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),/*类加载器*/
                target.getClass().getInterfaces(),/*让代理对象和目标对象实现相同接口*/
                (proxy, method, args) -> {
                    System.out.println(method.getName() + "方法开始执行...");
                    Object result = method.invoke(target, args);
                    System.out.println(result);
                    System.out.println(method.getName() + "方法执行结束...");
                    return result;
                });
    }
}
