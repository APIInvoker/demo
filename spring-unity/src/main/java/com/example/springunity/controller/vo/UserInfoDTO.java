package com.example.springunity.controller.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 *
 * @TableName dev_user_info
 */
@Data
public class UserInfoDTO implements Serializable {
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 收入
     */
    private Long income;

    private static final long serialVersionUID = -613462762639108431L;
}
