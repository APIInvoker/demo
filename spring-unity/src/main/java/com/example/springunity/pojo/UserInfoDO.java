package com.example.springunity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * @TableName dev_user_info
 */
@Data
public class UserInfoDO implements Serializable {
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
    private Date birthday;

    /**
     * 性别：1-男，2-女，3-未知
     */
    private Integer sex;

    /**
     * 用户年龄
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
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    private static final long serialVersionUID = 3418003266406894786L;
}