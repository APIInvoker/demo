package com.example.springunity.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * 行政区域工具类
 *
 * @author zx
 * @since 2022-10-15
 */
public class LocationUtil {
    /**
     * 所有国家名称List
     */
    private static final List<String> COUNTRY_REGION = new ArrayList<>();
    private static LocationUtil locationUtil;
    private Document document;
    /**
     * 根元素
     */
    private final Element rootElement;

    // 初始化
    private LocationUtil() {
        // 各地区xml文件路径
        String LOCAL_LIST_PATH = 
                Objects.requireNonNull(LocationUtil.class.getClassLoader().getResource("config/LocationList.xml"))
                        .getPath();
        // 1.读取
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(LOCAL_LIST_PATH);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 2.获得根元素
        rootElement = document.getRootElement();
        // 3.初始化所有国家名称列表
        Iterator<Element> it = rootElement.elementIterator();
        Element ele;
        while (it.hasNext()) {
            ele = it.next();
            COUNTRY_REGION.add(ele.attributeValue("Name"));
        }
    }
    
    /**
     * 获取国家名称集合
     *
     * @return 国家名称字符串数组
     */
    public List<String> getCountry() {
        return COUNTRY_REGION;
    }
    
    /**
     * 获取省份集合
     *
     * @param countryName 国家名称
     * @return 省份集合
     */
    private List<Element> provinces(String countryName) {
        Iterator<Element> it = rootElement.elementIterator();
        List<Element> provinces = new ArrayList<>();
        Element ele;
        while (it.hasNext()) {
            ele = it.next();
            COUNTRY_REGION.add(ele.attributeValue("Name"));
            if (ele.attributeValue("Name").equals(countryName)) {
                provinces = ele.elements();
                break;
            }
        }
        return provinces;
    }

    /**
     * 根据国家名称获取该国所有省份
     * 
     * @param countryName 国家名称
     * @return 省份名称集合
     */
    public List<String> getProvinces(String countryName) {
        List<Element> tmp = this.provinces(countryName);
        List<String> list = new ArrayList<>();
        for (Element element : tmp) {
            list.add(element.attributeValue("Name"));
        }
        return list;
    }

    /**
     * 根据国家名称和省份名称，获取该省份城市名列表
     *
     * @param countryName 国家名称
     * @param provinceName 省份名称
     * @return 城市集合
     */
    private List<Element> cities(String countryName, String provinceName) {
        return getSubElements(provinceName, provinces(countryName));
    }

    /**
     * 根据国家名称和省份名称获取城市名称集合
     * 
     * @param countryName 国家名称
     * @param provinceName 省份名称
     * @return 城市名称集合
     */
    public List<String> getCities(String countryName, String provinceName) {
        List<Element> tmp = this.cities(countryName, provinceName);
        List<String> cities = new ArrayList<>();
        for (Element element : tmp) {
            cities.add(element.attributeValue("Name"));
        }
        return cities;
    }

    /**
     * 根据国家、省份和城市名称，获取该城市县级集合
     * 
     * @param countryName 国家名称
     * @param provinceName 省份名称
     * @param cityName 城市名称
     */
    private List<Element> county(String countryName, String provinceName, String cityName) {
        return getSubElements(cityName, cities(countryName, provinceName));
    }

    /**
     * 根据国家、省份和城市名称，获取县级名称集合
     *
     * @param countryName 国家名称
     * @param provinceName 省份名称
     * @return 县级名称集合
     */
    public List<String> getCounty(String countryName, String provinceName, String city) {
        List<Element> countyList = this.county(countryName, provinceName, city);
        List<String> cities = new ArrayList<>();
        for (Element county : countyList) {
            cities.add(county.attributeValue("Name"));
        }
        return cities;
    }

    /**
     * 获取下级行政区域集合
     *
     * @param areaName 行政区域名称
     * @param areas 行政区域集合
     * @return 下级行政区域集合
     */
    private static List<Element> getSubElements(String areaName, List<Element> areas) {
        List<Element> subArea = new ArrayList<>();
        for (Element area : areas) {
            if (area.attributeValue("Name").equals(areaName)) {
                subArea = area.elements();
                break;
            }
        }
        return subArea;
    }

    /**
     * 获取工具类实例
     *
     * @return 行政区域工具类
     */
    public static LocationUtil getInstance() {
        if (locationUtil == null) {
            locationUtil = new LocationUtil();
        }
        return locationUtil;
    }
}
