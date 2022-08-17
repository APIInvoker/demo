package com.example.springunity.service.impl;

import com.example.springunity.entity.SettCheckWrongPool;
import com.example.springunity.service.SettCheckWrongPoolService;
import com.example.springunity.mapper.SettCheckWrongPoolMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author zx
* @description 针对表【tcpay_sett_check_wrong_pool(清结算-对账-单边账池)】的数据库操作Service实现
* @createDate 2022-08-17 19:37:08
*/
@Service
public class SettCheckWrongPoolServiceImpl implements SettCheckWrongPoolService{
    @Resource
    private SettCheckWrongPoolMapper settCheckWrongPoolMapper;

    /**
     * @param settCheckWrongPoolList
     * @return
     */
    @Override
    public int batchInsert(List<SettCheckWrongPool> settCheckWrongPoolList) {
        return settCheckWrongPoolMapper.batchInsert(settCheckWrongPoolList);
    }

    /**
     * @param settCheckWrongPoolList
     */
    @Override
    public void updateByPrimaryKey(SettCheckWrongPool settCheckWrongPoolList) {
        settCheckWrongPoolMapper.updateByPrimaryKey(settCheckWrongPoolList);
    }

    /**
     * @param settCheckWrongPoolList
     * @return
     */
    @Override
    public int batchUpdate(List<SettCheckWrongPool> settCheckWrongPoolList) {
        return settCheckWrongPoolMapper.batchUpdate(settCheckWrongPoolList);
    }
}




