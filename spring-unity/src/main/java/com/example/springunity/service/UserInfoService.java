package com.example.springunity.service;

import com.example.springunity.pojo.vo.UserInfoVO;

import java.util.List;

/**
 * @author zx
 * @description 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @createDate 2022-08-16 23:03:28
 */
public interface UserInfoService {
    List<UserInfoVO> queryAllUserInfo();
}
