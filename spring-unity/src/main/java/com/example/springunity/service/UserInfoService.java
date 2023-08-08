package com.example.springunity.service;

import com.example.springunity.controller.UnityController;
import com.example.springunity.mapper.entity.wrapper.UserInfoWrapper;
import com.github.pagehelper.PageInfo;

/**
 * @author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @since 2022-08-16 23:03:28
 */
public interface UserInfoService {
    PageInfo<UserInfoWrapper> pageQuery(UnityController.UserInfoCondition UserInfoCondition);
}
