package com.example.springunity;

import com.alibaba.fastjson2.JSON;
import com.example.springunity.entity.SettCheckWrongPool;
import com.example.springunity.service.SettCheckWrongPoolService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zx
 * @createTime 2022/8/17 19:25
 */
@SpringBootTest
@Slf4j
public class SettCheckWrongPoolServiceTest {
    @Resource
    private SettCheckWrongPoolService settCheckWrongPoolService;

    @Test
    public void testBatchInsert() {
        List<SettCheckWrongPool> settCheckWrongPoolList = new ArrayList<>();

        SettCheckWrongPool settCheckWrongPool1 = new SettCheckWrongPool();
        settCheckWrongPool1.setRequestNo("00001");
        settCheckWrongPool1.setCheckStatus("INIT");
        settCheckWrongPool1.setEditTime(new Date());
        settCheckWrongPool1.setId(1000007L);
        settCheckWrongPool1.setMerchentOrderNo("101");
        settCheckWrongPool1.setAmount(new BigDecimal(1));
        settCheckWrongPool1.setFundDirection("ADD");
        settCheckWrongPool1.setTrxType("EXPENSE");
        settCheckWrongPool1.setPayWayCode("WXMINI");
        settCheckWrongPool1.setStatus("SUCCESS");
        settCheckWrongPool1.setCreateTime(new Date());
        settCheckWrongPool1.setVersion(1L);
        settCheckWrongPool1.setOrderFrom("eetopin");
        settCheckWrongPool1.setCheckDate(new Date());
        settCheckWrongPoolList.add(settCheckWrongPool1);

        SettCheckWrongPool settCheckWrongPool2 = new SettCheckWrongPool();
        settCheckWrongPool2.setRequestNo("00002");
        settCheckWrongPool2.setCheckStatus("SUCCESS");
        settCheckWrongPool2.setEditTime(new Date());
        settCheckWrongPool2.setId(1000008L);
        settCheckWrongPool2.setMerchentOrderNo("102");
        settCheckWrongPool2.setAmount(new BigDecimal(2));
        settCheckWrongPool2.setFundDirection("ADD");
        settCheckWrongPool2.setTrxType("EXPENSE");
        settCheckWrongPool2.setPayWayCode("WXMINI");
        settCheckWrongPool2.setStatus("SUCCESS");
        settCheckWrongPool2.setCreateTime(new Date());
        settCheckWrongPool2.setVersion(1L);
        settCheckWrongPool2.setOrderFrom("eetopin");
        settCheckWrongPool2.setCheckDate(new Date());

        settCheckWrongPoolList.add(settCheckWrongPool2);

        SettCheckWrongPool settCheckWrongPool3 = new SettCheckWrongPool();
        settCheckWrongPool3.setRequestNo("00003");
        settCheckWrongPool3.setCheckStatus("FALSE");
        settCheckWrongPool3.setEditTime(new Date());
        settCheckWrongPool3.setId(1000009L);
        settCheckWrongPool3.setMerchentOrderNo("103");
        settCheckWrongPool3.setAmount(new BigDecimal(3));
        settCheckWrongPool3.setFundDirection("ADD");
        settCheckWrongPool3.setTrxType("EXPENSE");
        settCheckWrongPool3.setPayWayCode("WXMINI");
        settCheckWrongPool3.setStatus("SUCCESS");
        settCheckWrongPool3.setCreateTime(new Date());
        settCheckWrongPool3.setVersion(1L);
        settCheckWrongPool3.setOrderFrom("eetopin");
        settCheckWrongPool3.setCheckDate(new Date());

        settCheckWrongPoolList.add(settCheckWrongPool3);

        int count = settCheckWrongPoolService.batchInsert(settCheckWrongPoolList);
        log.info("插入了" + count + "条");
    }

    @Test
    public void testUpdateByPrimary() {
        SettCheckWrongPool settCheckWrongPool1 = new SettCheckWrongPool();
        settCheckWrongPool1.setCheckStatus("SUCCESS");
        settCheckWrongPool1.setEditTime(new Date());
        settCheckWrongPool1.setId(1000001L);
        settCheckWrongPoolService.updateByPrimaryKey(settCheckWrongPool1);
    }

    @Test
    public void testBatchUpdate() {
        List<SettCheckWrongPool> settCheckWrongPoolList = new ArrayList<>();

        SettCheckWrongPool settCheckWrongPool1 = new SettCheckWrongPool();
        settCheckWrongPool1.setCheckStatus("INIT");
        settCheckWrongPool1.setEditTime(new Date());
        settCheckWrongPool1.setId(1000001L);
        settCheckWrongPoolList.add(settCheckWrongPool1);
        SettCheckWrongPool settCheckWrongPool2 = new SettCheckWrongPool();
        settCheckWrongPool2.setCheckStatus("SUCCESS");
        settCheckWrongPool2.setEditTime(new Date());
        settCheckWrongPool2.setId(1000002L);
        settCheckWrongPoolList.add(settCheckWrongPool2);
        SettCheckWrongPool settCheckWrongPool3 = new SettCheckWrongPool();
        settCheckWrongPool3.setCheckStatus("FALSE");
        settCheckWrongPool3.setEditTime(new Date());
        settCheckWrongPool3.setId(1000003L);
        settCheckWrongPoolList.add(settCheckWrongPool3);

        for (SettCheckWrongPool settCheckWrongPool : settCheckWrongPoolList) {
            log.info(JSON.toJSONString(settCheckWrongPool));
        }

        int count = settCheckWrongPoolService.batchUpdate(settCheckWrongPoolList);
        log.info("更新了" + count + "条");
    }
}
