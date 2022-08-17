package com.example.springunity.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 清结算-对账-单边账池
 * @TableName tcpay_sett_check_wrong_pool
 */
@Data
public class SettCheckWrongPool implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 请求号(平台订单号)
     */
    private String requestNo;

    /**
     * 商户订单号
     */
    private String merchentOrderNo;

    /**
     * 商户编号
     */
    private String merchantNo;

    /**
     * 金额(元)
     */
    private BigDecimal amount;

    /**
     * 资金变动方向：
ADD 流入（充值、退款）
SUB 流出（消费、提现）
     */
    private String fundDirection;

    /**
     * 身份号码
     */
    private String idCard;

    /**
     * 银行流水号
     */
    private String bankTrxNo;

    /**
     * 交易业务类型：
充值 RECHARGE
消费 EXPENSE
提现 WITHDRAW
退款 REFUND 
     */
    private String trxType;

    /**
     * 支付方式类型编号
     */
    private String payWayCode;

    /**
     * 支付成功时间(记录银行成功时间)
     */
    private Date paySuccessTime;

    /**
     * 对账结果:
INIT 初始、
SUCCESS 对账成功、
WAIT_REFUND 未退款、
REFUND_SUCCESS 人工退款成功 DELETED_SUCCESS删除成功
     */
    private String checkStatus;

    /**
     * 退款成功时间
     */
    private Date refundSuccessTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 ：
SUCCESS  成功数据
FAIL 失败数据

     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date editTime;

    /**
     * 版本号
     */
    private Long version;

    /**
     * 对账日期
     */
    private Date checkDate;

    /**
     * 对账订单号(对应his的charge_code)
     */
    private String orderNo;

    /**
     * 数据来源：his_window
     */
    private String orderFrom;

    private static final long serialVersionUID = 1L;
}