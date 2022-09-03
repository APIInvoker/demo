package com.example.springunity.service;

import com.example.springunity.pojo.ProductInfoDO;
import com.example.springunity.pojo.vo.ProductInfoVO;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:27
 */
public interface ProductInfoService {
    ProductInfoVO getByProductInfo(ProductInfoDO productInfoDO);
}
