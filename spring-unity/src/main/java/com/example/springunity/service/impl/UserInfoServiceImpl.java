package com.example.springunity.service.impl;

import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.pojo.UserInfoDO;
import com.example.springunity.pojo.vo.UserInfoVO;
import com.example.springunity.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    public List<UserInfoVO> queryAllUserInfo() {
        List<UserInfoDO> userInfoDOList = userInfoMapper.selectAllUserInfo();
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        userInfoDOList.forEach(o -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(o, userInfoVO);
            userInfoVOList.add(userInfoVO);
        });
        return userInfoVOList;
    }
}
