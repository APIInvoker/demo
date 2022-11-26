package com.example.springunity.mapper;

import com.example.springunity.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author zx
* @description 针对表【dev_user_info(用户信息)】的数据库操作Mapper
* @createDate 2022-11-23 19:21:00
* @Entity com.example.springunity.entity.UserInfo
*/
@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectAll();

}
