package com.example.springunity.service.impl;

import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.mapper.entity.UserInfo;
import com.example.springunity.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zx
 * @since 2022-08-16 23:03:28
 */
@Service("userInfoService")
@Slf4j
public class UserInfoServiceImpl implements UserInfoService
{
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public Page<UserInfo> pageQuery(UserInfoSelectCondition selectCondition, PageInfo pageInfo)
    {
        Integer count = userInfoMapper.countSelectPage(selectCondition, pageInfo.getOffset(), pageInfo.getPageSize());
        if (count == null || 0 == count) {
            return pageInfo.buildPage();
        }
        List<UserInfo> userInfoList = userInfoMapper.selectUserInfoPage(selectCondition, pageInfo.getOffset(), pageInfo.getPageSize());
        return pageInfo.buildPage(userInfoList, count);
    }

    @Override
    public int count()
    {
        return userInfoMapper.count();
    }
}
