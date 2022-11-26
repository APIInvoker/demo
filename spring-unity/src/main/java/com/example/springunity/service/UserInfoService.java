package com.example.springunity.service;

import com.example.springunity.entity.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * @Author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @Date  2022-08-16 23:03:28
 */
public interface UserInfoService {
    PageInfo<UserInfo> pageQuery();
}
