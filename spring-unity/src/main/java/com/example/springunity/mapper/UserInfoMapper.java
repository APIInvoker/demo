package com.example.springunity.mapper;

import com.example.springunity.pojo.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zx
 * @description 针对表【dev_user_info(用户信息表)】的数据库操作Mapper
 * @createDate 2022-08-16 23:03:28
 * @Entity com.example.springunity.entity.UserInfo
 */
@Mapper
public interface UserInfoMapper {
    List<UserInfoDO> selectAllUserInfo();
}




