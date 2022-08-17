package com.example.springunity.service.impl;

import com.example.springunity.entity.SettCheckWrongPool;
import com.example.springunity.entity.UserInfo;
import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx
 * @description 针对表【dev_user_info(用户信息表)】的数据库操作Service实现
 * @createDate 2022-08-16 23:03:28
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> queryAllUserInfo() {
        return userInfoMapper.selectAllUserInfo();
    }
}




