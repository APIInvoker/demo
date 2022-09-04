package com.example.springunity.mapper;

import com.example.springunity.pojo.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author zx
* @description 针对表【dev_user_info(用户信息表)】的数据库操作Mapper
* @createDate 2022-09-04 14:21:31
* @Entity com.example.springunity.pojo.UserInfoDO
*/
@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);

    List<UserInfoDO> selectByCondition(UserInfoDO userInfoDO);
}
