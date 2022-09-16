package com.example.springunity.service;

import com.example.springunity.pojo.dto.UserInfoDTO;

import java.util.List;

/**
 * @Author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service
 * @Date  2022-08-16 23:03:28
 */
public interface UserInfoService {
    List<UserInfoDTO> queryUserByCondition(UserInfoDTO userInfoDTO);
}
