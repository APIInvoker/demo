package com.example.springunity.service.impl;

import com.example.springunity.pojo.ProductInfoDO;
import com.example.springunity.pojo.vo.ProductInfoVO;
import com.example.springunity.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:28
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    @Override
    public ProductInfoVO getByProductInfo(ProductInfoDO productInfoDO) {
        ProductInfoVO productInfoVO = new ProductInfoVO();
        BeanUtils.copyProperties(productInfoDO, productInfoVO);
        return productInfoVO;
    }
}
