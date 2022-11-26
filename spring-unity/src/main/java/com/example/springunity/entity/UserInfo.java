package com.example.springunity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息
 *
 * @TableName dev_user_info
 */
@Data
public class UserInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date birthday;

    /**
     * 性别，0-女，1-男，3-未知
     */
    private Integer sex;

    /**
     * 出生年份
     */
    @JsonFormat(pattern = "yyyy", timezone = "GMT+8")
    private Date bornYear;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 收入
     */
    private BigDecimal income;

    /**
     * 是否删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date gmtModified;

    private static final long serialVersionUID = 8916921333462460596L;
}