package cn.how2j.diytomcat.util;

import cn.how2j.diytomcat.catalina.Context;
import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 读取多应用xml配置文件的工具类
 *
 * @author Zheng Xin
 * @see <a href="DIYTomcat/conf/server.xml">server.xml</a>
 * @since 2022-10-19 22:20
 */
public class ServerXMLUtil {
    public static List<Context> getContexts() {
        List<Context> result = new ArrayList<>();
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document d = Jsoup.parse(xml);
        Elements es = d.select("Context");
        for (Element e : es) {
            String path = e.attr("path");
            String docBase = e.attr("docBase");
            Context context = new Context(path, docBase);
            result.add(context);
        }
        return result;
    }

    public static String getHostName() {
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document d = Jsoup.parse(xml);
        Element host = d.select("Host").first();
        return host.attr("name");
    }
}