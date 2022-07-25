package com.example.springunity.service.impl;

import com.example.springunity.pojo.ProductInfo;
import com.example.springunity.service.ProductInfoService;
import org.springframework.stereotype.Service;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:28
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Override
    public ProductInfo getByProductInfo(ProductInfo productInfo) {
        return productInfo;
    }
}
