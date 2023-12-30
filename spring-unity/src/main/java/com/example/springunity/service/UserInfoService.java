package com.example.springunity.service;

import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.service.wrapper.UserInfoWrapper;

/**
 * @author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @since 2022-08-16 23:03:28
 */
public interface UserInfoService
{
    Page<UserInfoWrapper> pageQuery(UserInfoSelectCondition selectCondition, PageInfo pageInfo);
}
