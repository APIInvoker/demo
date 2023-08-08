package com.example.springunity.mapper;

import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.mapper.entity.wrapper.UserInfoWrapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zx
 * @description 针对表【dev_user_info(用户信息)】的数据库操作Mapper
 * @createDate 2022-11-30 11:40:15
 * @Entity com.example.springunity.entity.UserInfo
 */
@Mapper
public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(@Param("condition") UserInfoSelectCondition condition);

    int insertSelective(@Param("condition") UserInfoSelectCondition condition);

    UserInfoWrapper selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(@Param("condition") UserInfoSelectCondition condition);

    int updateByPrimaryKey(@Param("condition") UserInfoSelectCondition condition);

    List<UserInfoWrapper> selectAll(@Param("condition") UserInfoSelectCondition condition);
}
