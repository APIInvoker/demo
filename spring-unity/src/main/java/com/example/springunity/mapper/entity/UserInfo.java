package com.example.springunity.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 *
 * @TableName dev_user_info
 */
@Data
public class UserInfo implements Serializable
{
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private Long userInfoId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 是否删除
     */
    private Integer deleted;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别: 1-男,0-女
     */
    private Integer sex;
    /**
     * 出生年份
     */
    private Short bornYear;
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