package com.example.springunity.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class PayOrderVo implements Serializable {
    private static final long serialVersionUID = 4657916227076727153L;

    @NotNull(message = "payKey不能为空")
    @Size(min = 1, max = 32, message = "payKey长度必须在1-32之间")//在1~32个字符之间
    private String payKey;//商户支付KEY

    @NotNull(message = "productName不能为空")
    @Size(min = 1, max = 42, message = "productName长度必须在1-42之间")
    private String productName;//商品名称

    @NotNull(message = "orderNo不能为空")
    @Size(min = 1, max = 40, message = "orderNo长度必须在1-40之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "orderNo格式不正确(只能是数字或字母)")
    private String orderNo;//商户订单号
    
    @NotNull(message = "orderTime不能为空")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private String orderTime;//下单时间

    @NotNull(message = "orderPrice不能为空")
    @Digits(fraction = 2, integer = 8, message = "orderPrice不合法")//小数位数2 整数位数6
    @DecimalMin(value = "0.01", message = "orderPrice不得小于0.01元")
    @DecimalMax(value = "10000000", message = "orderPrice不得大于10000000.00元")
    private BigDecimal orderPrice;//订单金额(元)
    
    private BigDecimal savaidCardPrice;//混合支付中存济卡订单金额(元)
    private BigDecimal giftCardPrice;//混合支付中礼品卡订单金额(元)

    @Size(max = 50, message = "payWayCode长度必须在0-50之间")
    private String payWayCode;

    @Size(max = 50, message = "payTypeCode长度必须在0-50之间")
    private String payTypeCode;

    @Size(max = 50, message = "payAppType长度必须在0-50之间")
    private String payAppType;

    @NotNull(message = "orderIp不能为空")
    @Size(min = 1, max = 50, message = "orderIp长度必须在1-50之间")
    private String orderIp;//下单IP

    @NotNull(message = "orderPeriod不能为空")
    @Digits(fraction = 0, integer = 4)//小数位数 0 整数位数4
    private Integer orderPeriod;//订单有效期(分钟)

    @NotNull(message = "orderFrom不能为空")
    @Size(min = 1, max = 40, message = "orderFrom长度必须在1-40之间")
    private String orderFrom; 

    @Size(min = 1, max = 40, message = "chargeCode长度必须在1-40之间")
    private String chargeCode; 
    
    @Size(max = 200, message = "returnUrl长度必须在0-200之间")
    private String returnUrl;//支付结果页面通知地址

    @Size(max = 200, message = "notifyUrl长度必须在0-200之间")
    private String notifyUrl;//支付结果后台通知地址

    @Size(max = 50, message = "nonceStr长度必须在0-50之间")
    private String nonceStr;//随机字符串

    @Size(max = 100, message = "remark长度必须在0-100之间")
    private String remark;//支付备注
    
    @Size(max = 16, message = "savaidCardNo长度必须在0-16之间")
    private String savaidCardNo;//存济卡号
    
    @Size(max = 50, message = "savaidCardPassword长度必须在0-50之间")
    private String savaidCardPassword;//存济卡支付密码
    
    @Size(max = 20, message = "certNo长度必须在0-20之间")
    private String certNo;//用户身份证号码

    @Size(max = 50, message = "wxOpenId长度必须在0-50之间")
    private String wxOpenId;//微信用户openId
    
    @Size(max = 300, message = "authCode长度必须在0-300之间")
    private String authCode;//付款码authCode
    
    @Size(max = 4000, message = "receiveAccountNo长度必须在0-4000之间")
    private String receiveAccountNo;//收款账户号
    
    @Size(max = 200, message = "activityInfo长度必须在0-200之间")
    private String activityInfo;//营销活动详情JSON
    
    @Size(max = 500, message = "giftCardPasswordList长度必须在0-500之间")
    private String giftCardPasswordList;//礼品卡卡密集合
    
    @Size(max = 20, message = "receiver长度必须在0-20之间")
    private String receiver;//收款方(收银台支付时必填)
    
    @Size(max = 500, message = "payTypeList长度必须在0-100之间")
    private String payTypeList;//付款方式列表(收银台支付时必填)
    
    @Size(max = 100, message = "field1长度必须在0-100之间")
    private String field1;//扩展字段1
    @Size(max = 100, message = "field2长度必须在0-100之间")
    private String field2;//扩展字段2
    @Size(max = 100, message = "field3长度必须在0-100之间")
    private String field3;//扩展字段3
    @Size(max = 100, message = "field4长度必须在0-100之间")
    private String field4;//扩展字段4
    @Size(max = 100, message = "field5长度必须在0-100之间")
    private String field5;//扩展字段5

    @NotNull(message = "sign不能为空")
    @Size(min = 1, max = 32, message = "sign长度必须在1-32之间")
    private String sign;//签名
    
    @NotNull(message = "signType不能为空")
    @Size(min = 1, max = 10, message = "signType长度必须在1-10之间")
    private String signType;//签名类型
    
    private String paySecret;//商户密钥(不作为参数传递)

    private String idCard;//用户余额

    private String payerId;//通联付款方会员号

    private String tollCollector; //收费员

    private String buyerLogonId;//支付宝买房Id

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }

    public String getTollCollector() {
        return tollCollector;
    }

    public void setTollCollector(String tollCollector) {
        this.tollCollector = tollCollector;
    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

//    public String getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(String orderDate) {
//        this.orderDate = orderDate;
//    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getPayWayCode() {
        return payWayCode;
    }

    public void setPayWayCode(String payWayCode) {
        this.payWayCode = payWayCode;
    }

    public String getOrderIp() {
        return orderIp;
    }

    public void setOrderIp(String orderIp) {
        this.orderIp = orderIp;
    }

    public Integer getOrderPeriod() {
        return orderPeriod;
    }

    public void setOrderPeriod(Integer orderPeriod) {
        this.orderPeriod = orderPeriod;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getPayTypeCode() {
        return payTypeCode;
    }

    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode;
    }

    public String getPayAppType() {
        return payAppType;
    }

    public void setPayAppType(String payAppType) {
        this.payAppType = payAppType;
    }

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	/**
	 * @return the savaidCardNo
	 */
	public String getSavaidCardNo() {
		return savaidCardNo;
	}

	/**
	 * @param savaidCardNo the savaidCardNo to set
	 */
	public void setSavaidCardNo(String savaidCardNo) {
		this.savaidCardNo = savaidCardNo;
	}

	/**
	 * @return the savaidCardPassword
	 */
	public String getSavaidCardPassword() {
		return savaidCardPassword;
	}

	/**
	 * @param savaidCardPassword the savaidCardPassword to set
	 */
	public void setSavaidCardPassword(String savaidCardPassword) {
		this.savaidCardPassword = savaidCardPassword;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(String orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getReceiveAccountNo() {
		return receiveAccountNo;
	}

	public void setReceiveAccountNo(String receiveAccountNo) {
		this.receiveAccountNo = receiveAccountNo;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getGiftCardPasswordList() {
		return giftCardPasswordList;
	}

	public void setGiftCardPasswordList(String giftCardPasswordList) {
		this.giftCardPasswordList = giftCardPasswordList;
	}

	public BigDecimal getSavaidCardPrice() {
		return savaidCardPrice;
	}

	public void setSavaidCardPrice(BigDecimal savaidCardPrice) {
		this.savaidCardPrice = savaidCardPrice;
	}

	public BigDecimal getGiftCardPrice() {
		return giftCardPrice;
	}

	public void setGiftCardPrice(BigDecimal giftCardPrice) {
		this.giftCardPrice = giftCardPrice;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(String payTypeList) {
		this.payTypeList = payTypeList;
	}

	public String getPaySecret() {
		return paySecret;
	}

	public void setPaySecret(String paySecret) {
		this.paySecret = paySecret;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }
}
