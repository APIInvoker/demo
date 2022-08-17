package com.example.springunity.service;

import com.example.springunity.entity.SettCheckWrongPool;

import java.util.List;

/**
* @author zx
* @description 针对表【tcpay_sett_check_wrong_pool(清结算-对账-单边账池)】的数据库操作Service
* @createDate 2022-08-17 19:37:08
*/
public interface SettCheckWrongPoolService {
    int batchInsert(List<SettCheckWrongPool> settCheckWrongPoolList);

    void updateByPrimaryKey(SettCheckWrongPool settCheckWrongPoolList);

    int batchUpdate(List<SettCheckWrongPool> settCheckWrongPoolList);
}
