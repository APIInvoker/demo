package com.example.springunity.controller.vo;

import com.example.springunity.service.wrapper.UserInfoWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 */
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 8190511907753965299L;

    public static UserInfoVO build(UserInfoWrapper wrapper) {
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setId(wrapper.getId());
        userInfoVO.setGmtCreate(wrapper.getGmtCreate());
        userInfoVO.setGmtModified(wrapper.getGmtModified());
        userInfoVO.setUserId(wrapper.getUserId());
        userInfoVO.setNickName(wrapper.getNickName());
        userInfoVO.setSex(wrapper.getSex());
        userInfoVO.setBornYear(wrapper.getBornYear());
        userInfoVO.setAge(wrapper.getAge());
        userInfoVO.setBirthday(wrapper.getBirthday());
        userInfoVO.setIncome(wrapper.getIncome());
        return userInfoVO;
    }

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS")
    private Date gmtModified;

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
    private Date birthday;

    /**
     * 收入
     */
    private Integer income;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Short getBornYear() {
        return bornYear;
    }

    public void setBornYear(Short bornYear) {
        this.bornYear = bornYear;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }
}
