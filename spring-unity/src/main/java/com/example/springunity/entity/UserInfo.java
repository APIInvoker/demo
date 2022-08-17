package com.example.springunity.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
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
     * 用户头像
     */
    private String logo;

    /**
     * 用户标签：用多个逗号分割
     */
    private String tags;

    /**
     * 性别：1-男，2-女，3-未知
     */
    private Integer sex;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 学历
     */
    private String edu;

    /**
     * 居住城市
     */
    private String city;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 封面图片
     */
    private String coverPic;

    /**
     * 行业
     */
    private String industry;

    /**
     * 收入
     */
    private String income;

    /**
     * 婚姻状态
     */
    private String marriage;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    private static final long serialVersionUID = -2994531335068773954L;
}