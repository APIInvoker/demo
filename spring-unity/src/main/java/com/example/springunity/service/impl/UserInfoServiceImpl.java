package com.example.springunity.service.impl;

import com.example.springunity.mapper.UserInfoMapper;
import com.example.springunity.pojo.UserInfoDO;
import com.example.springunity.pojo.dto.UserInfoDTO;
import com.example.springunity.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<UserInfoDTO> queryUserByCondition(UserInfoDTO userInfoDTO) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setNickName(userInfoDTO.getNickName());
        userInfoDO.setSex(userInfoDTO.getSex());
        userInfoDO.setAge(userInfoDTO.getAge());
        userInfoDO.setBirthday(userInfoDTO.getBirthday());
        Long income = userInfoDTO.getIncome();
        if (income != null) {
            userInfoDO.setIncome(new BigDecimal(userInfoDTO.getIncome()));
        }
        List<UserInfoDO> userInfoDOList = userInfoMapper.selectByCondition(userInfoDO);
        List<UserInfoDTO> userInfoDTOList = new ArrayList<>();
        UserInfoDTO dto = new UserInfoDTO();
        userInfoDOList.forEach(o -> {
            dto.setUserId(o.getUserId());
            dto.setNickName(o.getNickName());
            dto.setSex(o.getSex());
            dto.setAge(o.getAge());
            dto.setBirthday(o.getBirthday());
            dto.setIncome(o.getIncome().longValue());
            userInfoDTOList.add(dto);
        });
        return userInfoDTOList;
    }
}
