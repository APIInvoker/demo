package com.example.springunity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springunity.mapper.condition.UserInfoSelectCondition;
import com.example.springunity.mapper.entity.UserInfo;
import com.example.springunity.service.wrapper.UserInfoWrapper;
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
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    int deleteByPrimaryKey(Long id);

    // int insert(@Param("condition") UserInfoSelectCondition condition);

    int insertSelective(@Param("condition") UserInfoSelectCondition condition);

    UserInfoWrapper selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(@Param("condition") UserInfoSelectCondition condition);

    int updateByPrimaryKey(@Param("condition") UserInfoSelectCondition condition);

    int countSelectPage(@Param("condition") UserInfoSelectCondition selectCondition);

    List<UserInfoWrapper> selectPage(@Param("condition") UserInfoSelectCondition condition, @Param("offset") int offset, @Param("pageSize") int pageSize);
}
