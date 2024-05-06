package com.example.springunity.mapper.entity;

import java.io.Serial;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @TableName area
 */
@Data
public class Area implements Serializable {
    /**
     * 区域ID
     */
    @TableId
    private Integer areaId;

    /**
     * 上级区域ID
     */
    private Integer parentAreaId;

    /**
     * 行政区域登记(1:省;2:市;3:区县;4:街道镇;)
     */
    private Integer level;

    /**
     * 名称
     */
    private String name;

    /**
     * 完整名称
     */
    private String wholeName;

    /**
     * 本区域经度
     */
    private String longitude;

    /**
     * 本区域纬度
     */
    private String latitude;

    /**
     * 电话区号
     */
    private String cityCode;

    /**
     * 邮政编码
     */
    private String zipCode;

    /**
     * 行政区划代码
     */
    private String areaCode;

    /**
     * 名称全拼
     */
    private String pinYin;

    /**
     * 首字母简拼
     */
    private String simplePy;

    /**
     * 区域名称拼音的第一个字母
     */
    private String perPinYin;

    @Serial
    private static final long serialVersionUID = 1L;
}