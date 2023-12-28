package com.example.util;

import com.example.enums.CommonConst;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zx
 * @since 2023-12-28
 */
@Slf4j
public class FileUtil {
    /**
     * 从服务器下载Excel文件
     *
     * @param fileName 文件名（非绝对路径）
     */
    public static void downloadExcelFromServer(HttpServletResponse response, String fileName) {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        // 设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8) + ".xlsx");

        String file = Paths.get(CommonConst.EXCEL_EXPORT_PATH) + "\\" + fileName + ".xlsx";
        InputStream in;
        OutputStream out = null;
        try {
            // 获取要下载的文件输入流
            in = Files.newInputStream(Path.of(file));
            int len;
            // 创建数据缓冲区
            byte[] buffer = new byte[1024];
            // 通过response对象获取outputStream流
            out = response.getOutputStream();
            // 将FileInputStream流写入到buffer缓冲区
            while ((len = in.read(buffer)) > 0) {
                // 使用OutputStream将缓冲区的数据输出到浏览器
                out.write(buffer, 0, len);
            }
            out.flush();
            in.close();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("下载流关闭失败");
                }
            }
            File exportFile = new File(file);
            if (exportFile.exists()) {
                boolean deleteResult = exportFile.delete();
            }
        }
    }
}
