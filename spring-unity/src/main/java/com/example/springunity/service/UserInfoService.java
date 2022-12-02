package com.example.springunity.service;

import com.example.springunity.controller.vo.UserInfoVO;
import com.github.pagehelper.PageInfo;

/**
 * @author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @since  2022-08-16 23:03:28
 */
public interface UserInfoService {
    PageInfo<UserInfoVO> pageQuery(UserInfoVO userInfoVO);

    void saveUserInfo(UserInfoVO userInfoVO);
}
