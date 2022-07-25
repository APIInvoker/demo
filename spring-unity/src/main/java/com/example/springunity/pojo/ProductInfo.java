package com.example.springunity.pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:27
 */
@Data
public class ProductInfo {
    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 上架状态
     */
    private Integer productStatus;
}
