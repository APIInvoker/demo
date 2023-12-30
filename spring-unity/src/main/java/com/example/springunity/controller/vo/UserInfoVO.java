package com.example.springunity.controller.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.example.springunity.service.wrapper.UserInfoWrapper;
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
    private Long id;
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
     * 用户id
     */
    private Long userId;
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

    public static UserInfoVO build(UserInfoWrapper wrapper)
    {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(wrapper.getId());
        userInfoVO.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(wrapper.getGmtCreate()));
        userInfoVO.setGmtModified(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS").format(wrapper.getGmtModified()));
        userInfoVO.setUserId(wrapper.getUserId());
        userInfoVO.setNickName(wrapper.getNickName());
        userInfoVO.setSex(wrapper.getSex());
        userInfoVO.setBornYear(wrapper.getBornYear());
        userInfoVO.setAge(wrapper.getAge());
        userInfoVO.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(wrapper.getBirthday()));
        userInfoVO.setIncome(wrapper.getIncome());
        return userInfoVO;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
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
