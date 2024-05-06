package com.example.springunity.controller.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.springunity.mapper.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 * 用户信息
 */
public class UserInfoVO implements Serializable
{
    private static final long serialVersionUID = 8190511907753965299L;
    /**
     * 主键id
     */
    private Long userInfoId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    @ExcelProperty(value = "创建日期")
    private String gmtCreate;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    private String gmtModified;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别(0:女,1:男,2:未知)
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String birthday;
    /**
     * 收入
     */
    private Integer income;

    public static UserInfoVO build(UserInfo userInfo)
    {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setUserInfoId(userInfo.getUserInfoId());
        userInfoVO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(userInfo.getGmtCreate()));
        userInfoVO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(userInfo.getGmtModified()));
        userInfoVO.setNickName(userInfo.getNickName());
        userInfoVO.setSex(userInfo.getSex());
        userInfoVO.setBornYear(userInfo.getBornYear());
        userInfoVO.setAge(userInfo.getAge());
        userInfoVO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(userInfo.getBirthday()));
        userInfoVO.setIncome(userInfo.getIncome());
        return userInfoVO;
    }

    public Long getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Long userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getGmtCreate()
    {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified()
    {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Short getBornYear()
    {
        return bornYear;
    }

    public void setBornYear(Short bornYear)
    {
        this.bornYear = bornYear;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public Integer getIncome()
    {
        return income;
    }

    public void setIncome(Integer income)
    {
        this.income = income;
    }
}
