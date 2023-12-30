package com.example.springunity.service.impl;

import com.example.page.Page;
import com.example.page.PageInfo;
import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.service.UserInfoService;
import com.example.springunity.service.wrapper.UserInfoWrapper;
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
    public Page<UserInfoWrapper> pageQuery(UserInfoSelectCondition selectCondition, PageInfo pageInfo)
    {
        int count = userInfoMapper.countSelectPage(selectCondition);
        if (count == 0) {
            return pageInfo.buildPage();
        }
        List<UserInfoWrapper> userInfoWrapperList = userInfoMapper.selectPage(selectCondition, pageInfo.getOffset(), pageInfo.getPageSize());
        return pageInfo.buildPage(userInfoWrapperList, count);
    }
}
