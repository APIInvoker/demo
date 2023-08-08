package com.example.springunity.service.impl;

import com.example.springunity.controller.UnityController;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.mapper.entity.wrapper.UserInfoWrapper;
import com.example.springunity.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

import static com.example.springunity.mapper.condition.UserInfoSelectCondition.buildUserInfoCondition;

/**
 * @author zx
 * @since 2022-08-16 23:03:28
 */
@Service("userInfoService")
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfoWrapper> pageQuery(UnityController.UserInfoCondition condition) {
        UserInfoSelectCondition selectCondition = buildUserInfoCondition(condition);
        PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<UserInfoWrapper> userInfoWrapperList = userInfoMapper.selectAll(selectCondition);
        return new PageInfo<>(userInfoWrapperList);
    }
}
