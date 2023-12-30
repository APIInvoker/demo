package com.example.springunity.mapper.condition;

import com.example.springunity.controller.UnityController;

import java.util.Date;

public class UserInfoSelectCondition
{

    /**
     * 主键id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 是否删除
     */
    private Integer isDelete;
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
    private Object bornYear;
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

    public static UserInfoSelectCondition buildUserInfoCondition(UnityController.UserInfoCondition condition)
    {
        UserInfoSelectCondition userInfoSelectCondition = new UserInfoSelectCondition();
        userInfoSelectCondition.setId(condition.getId());
        userInfoSelectCondition.setGmtCreate(condition.getGmtCreate());
        userInfoSelectCondition.setGmtModified(condition.getGmtModified());
        userInfoSelectCondition.setIsDelete(condition.getIsDelete());
        userInfoSelectCondition.setUserId(condition.getUserId());
        userInfoSelectCondition.setNickName(condition.getNickName());
        userInfoSelectCondition.setSex(condition.getSex());
        userInfoSelectCondition.setBornYear(condition.getBornYear());
        userInfoSelectCondition.setAge(condition.getAge());
        userInfoSelectCondition.setBirthday(condition.getBirthday());
        userInfoSelectCondition.setIncome(condition.getIncome());
        return userInfoSelectCondition;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Date getGmtCreate()
    {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate)
    {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified()
    {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified)
    {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDelete()
    {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
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

    public Object getBornYear()
    {
        return bornYear;
    }

    public void setBornYear(Object bornYear)
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

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
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
