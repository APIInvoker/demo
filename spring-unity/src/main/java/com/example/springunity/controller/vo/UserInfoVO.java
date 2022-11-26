package com.example.springunity.controller.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息表
 *
 * @TableName dev_user_info
 */
@Data
public class UserInfoVO implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别：1-男，2-女，3-未知
     */
    private Integer sex;

    /**
     * 用户年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 收入
     */
    private Long income;

    private static final long serialVersionUID = -2994531335068773954L;
}
