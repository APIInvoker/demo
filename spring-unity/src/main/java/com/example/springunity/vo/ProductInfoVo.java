package com.example.springunity.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProductInfoVo {
    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不允许为空")
    private String productName;

    /**
     * 商品价格
     */
    @Min(value = 0, message = "商品价格不允许为负数")
    private BigDecimal productPrice;

    /**
     * 上架状态
     */
    private Integer productStatus;
}