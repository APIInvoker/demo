package com.example.springunity.mapper;

import com.example.springunity.entity.SettCheckWrongPool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author zx
* @description 针对表【tcpay_sett_check_wrong_pool(清结算-对账-单边账池)】的数据库操作Mapper
* @createDate 2022-08-17 19:37:08
* @Entity com.example.springunity.entity.SettCheckWrongPool
*/
@Mapper
public interface SettCheckWrongPoolMapper  {
    int batchInsert(List<SettCheckWrongPool> settCheckWrongPoolList);

    void updateByPrimaryKey(SettCheckWrongPool settCheckWrongPool);

    int batchUpdate(List<SettCheckWrongPool> settCheckWrongPoolList);
}




