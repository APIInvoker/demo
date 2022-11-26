package com.example.springunity.service.impl;

import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.entity.UserInfo;
import com.example.springunity.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service实现
 * @Date 2022-08-16 23:03:28
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfo> pageQuery() {
        PageHelper.startPage(0, 10);
        List<UserInfo> userInfoList = userInfoMapper.selectAll();
        return new PageInfo<>(userInfoList);
    }
}
