package com.example.springunity.service.impl;

import com.example.springunity.controller.vo.UserInfoVO;
import com.example.springunity.entity.UserInfo;
import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author zx
 * @Desc 针对表【dev_user_info(用户信息表)】的数据库操作Service实现
 * @Date 2022-08-16 23:03:28
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfoVO> pageQuery(UserInfoVO userInfoVO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(userInfoVO.getNickName());
        PageHelper.startPage(userInfoVO.getPageNum(), userInfoVO.getPageSize());
        List<UserInfo> userInfoList = userInfoMapper.selectAll(userInfo);
        PageInfo<UserInfo> userInfoPageInfo = new PageInfo<>(userInfoList);
        List<UserInfo> pageUserInfoList = userInfoPageInfo.getList();
        ArrayList<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (UserInfo info : pageUserInfoList) {
            UserInfoVO vo = new UserInfoVO();
            vo.setNickName(info.getNickName());
            vo.setAge(info.getAge());
            vo.setSex(info.getSex());
            vo.setBornYear(info.getBornYear());
            vo.setBirthday(info.getBirthday());
            vo.setIncome(info.getIncome());
            vo.setGmtCreate(info.getGmtCreate());
            vo.setGmtModified(info.getGmtModified());
            userInfoVOList.add(vo);
        }
        return new PageInfo<>(userInfoVOList);
    }

    @Override
    public void saveUserInfo(UserInfoVO userInfoVO) {
        UserInfo userInfo = new UserInfo();
        long min = 1;
        long max = 1000000;
        long rangeLong = min + (((long) (new Random().nextDouble() * (max - min))));
        userInfo.setUserId(rangeLong);
        userInfo.setNickName(userInfoVO.getNickName());
        userInfo.setSex(userInfoVO.getSex());
        userInfo.setBornYear(userInfoVO.getBornYear());
        userInfo.setAge(userInfoVO.getAge());
        userInfo.setBirthday(userInfoVO.getBirthday());
        userInfo.setIncome(userInfoVO.getIncome());
        userInfo.setGmtCreate(new Date());
        userInfo.setGmtModified(new Date());
        userInfoMapper.insertSelective(userInfo);
    }
}
