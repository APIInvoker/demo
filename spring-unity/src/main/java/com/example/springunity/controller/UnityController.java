package com.example.springunity.controller;

import com.alibaba.fastjson2.JSON;
import com.example.springunity.pojo.PayOrderVo;
import com.example.springunity.pojo.ProductInfo;
import com.example.springunity.service.impl.ProductInfoServiceImpl;
import com.example.springunity.util.MD5Util;
import com.example.springunity.vo.ProductInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author zhengxin
 * @createTime 2022/7/18 11:24
 */
@RestController
@RequestMapping("/product/product-info")
@Slf4j
public class UnityController {
    @Resource
    private ProductInfoServiceImpl productInfoService;

    @PostMapping("/findByVo")
    public ProductInfo findByVo(@Validated @RequestBody ProductInfoVo vo) {
        ProductInfo productInfo = new ProductInfo();
        BeanUtils.copyProperties(vo, productInfo);
        return productInfoService.getByProductInfo(productInfo);
    }

    @RequestMapping("getsign")
    public String findByVo(@Valid PayOrderVo reqMap) {
        // 获取商户传入参数
        String payKey = reqMap.getPayKey();        // 企业支付KEY
        String productName = reqMap.getProductName();    // 商品名称
        String orderNo = reqMap.getOrderNo();        // 订单编号
        BigDecimal orderPrice = reqMap.getOrderPrice();   // 订单金额 （元）
        String payWayCode = reqMap.getPayWayCode();    // 支付渠道编码  工行:ICBCPAY  支付宝: ALIPAY  微信:WEIXIN  存济卡:SAVAID_CARD
        String payTypeCode = reqMap.getPayTypeCode();    // 支付方式编码  工行微信收银台:ICBC_WEIXIN_CASHIER_PAY  工行支付宝收银台:ICBC_ALIPAY_CASHIER_PAY  工行微信小程序:ICBC_WEIXIN_MINAPP_PAY  工行微信付款码:ICBC_WEIXIN_SCAN_PAY  工行支付宝付款码:ICBC_ALIPAY_SCAN_PAY
        String payAppType = reqMap.getPayAppType();    // 支付应用类型  工行微信非小程序:ICBC_WEIXIN_NORMAL  工行支付宝非小程序:ICBC_ALIPAY_NORMAL  工行微信小程序:ICBC_WEIXIN_MINAPP  存济卡:DIRECT_NORMAL
        String orderIp = reqMap.getOrderIp();        // 下单IP
        String orderTime = reqMap.getOrderTime();    // 订单时间
        Integer orderPeriod = reqMap.getOrderPeriod();    // 订单超时时间（分钟）
        String orderFrom = reqMap.getOrderFrom();    // 订单来源
        String returnUrl = reqMap.getReturnUrl();    // 页面通知返回url
        String notifyUrl = reqMap.getNotifyUrl();    // 后台消息通知Url
        String nonceStr = reqMap.getNonceStr();        // 随机字符串
        String remark = reqMap.getRemark();        // 支付备注
        String tollCollector = reqMap.getTollCollector(); // 收费员

        // 非存济卡支付 或者 非被扫模式时 必输
        String receiveAccountNo = reqMap.getReceiveAccountNo();        // 收款账户编号

        // 礼品卡支付或者混合支付时 必输
        String giftCardPasswordList = reqMap.getGiftCardPasswordList();    // 礼品卡卡密集合

        // 存济卡支付 必输 6201开头
        String cardNo = reqMap.getSavaidCardNo();            // 存济卡卡号
        String password = reqMap.getSavaidCardPassword();    // 存济卡支付密码
        String certNo = reqMap.getCertNo();                // 证件号码
        // 仅存济卡支付时有营销活动 非必输
        // 折扣活动	{"activityType":"DISCOUNT","activityId":"4333646059691909122"}
        // 保险活动	{"activityType":"INSURANCE","activityId":"4014462832542310402","orderAmountOne":"100.00","orderAmountThree":"300.00","orderAmountTwo":"200.00"}
        // 立减活动	{"activityType":"REDUCTION","activityId":"4014462832542310402"}
        String activityInfo = reqMap.getActivityInfo();            // 营销活动详情JSON

        // 工行微信支付 必输
        String wxOpenId = reqMap.getWxOpenId();        // 微信用户openId

        // 付款码支付 必输
        // 支付宝授权码，25~30开头的长度为16~24位的数字
        // 微信支付授权码（以10/11/12/13/14/15为前缀的18位数字）
        String authCode = reqMap.getAuthCode();        // 支付宝/微信付款码authCode

        // HIS窗口收费 his_window必输
        String chargeCode = reqMap.getChargeCode();    // HIS订单chargeCode

        String payerId = reqMap.getPayerId();    // 通联支付必传

        String field1 = reqMap.getField1();        // 扩展字段1
        String field2 = reqMap.getField2();        // 扩展字段2
        String field3 = reqMap.getField3();        // 扩展字段3
        String field4 = reqMap.getField4();        // 扩展字段4
        String field5 = reqMap.getField5();        // 扩展字段5
        String signType = reqMap.getSignType();    // 签名类型
        String sign = reqMap.getSign();        // 签名
        log.info("入参:" + JSON.toJSONString(reqMap));

        log.info("组建签名参数map");
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("payKey", payKey);
        paramMap.put("productName", productName);
        paramMap.put("orderNo", orderNo);
        paramMap.put("orderPrice", orderPrice);
        paramMap.put("payWayCode", payWayCode);
        paramMap.put("payTypeCode", payTypeCode);
        paramMap.put("payAppType", payAppType);
        paramMap.put("orderIp", orderIp);
        paramMap.put("orderTime", orderTime);
        paramMap.put("orderPeriod", orderPeriod);
        paramMap.put("orderFrom", orderFrom);
        paramMap.put("returnUrl", returnUrl);
        paramMap.put("notifyUrl", notifyUrl);
        paramMap.put("nonceStr", nonceStr);
        paramMap.put("signType", signType);
        paramMap.put("remark", remark);
        paramMap.put("receiveAccountNo", receiveAccountNo);
        paramMap.put("giftCardPasswordList", giftCardPasswordList);
        paramMap.put("activityInfo", activityInfo);
        paramMap.put("savaidCardNo", cardNo);
        paramMap.put("savaidCardPassword", password);
        paramMap.put("certNo", certNo);
        paramMap.put("wxOpenId", wxOpenId);
        paramMap.put("authCode", authCode);
        paramMap.put("chargeCode", chargeCode);
        paramMap.put("payerId", payerId);
        paramMap.put("tollCollector", tollCollector);
        paramMap.put("field1", field1);
        paramMap.put("field2", field2);
        paramMap.put("field3", field3);
        paramMap.put("field4", field4);
        paramMap.put("field5", field5);
        log.info("签名验证开始,paramMap" + JSON.toJSONString(paramMap));

        if (isRightSign(paramMap, "bcf14aa564534c299fb971d6939e38be", sign)) {
            log.info("sign is right");
        } else {
            log.info("sign is false");
        }
        String sign1 = getSign(paramMap, "bcf14aa564534c299fb971d6939e38be");
        log.info("sign" + sign1);

        return sign1;
    }

    /**
     * 验证商户签名
     * @param paramMap  签名参数
     * @param paySecret 签名私钥
     * @param signStr   原始签名密文
     * @return
     */
    public static boolean isRightSign(Map<String , Object> paramMap , String paySecret ,String signStr){
        if (StringUtils.isEmpty(signStr)){
            return false;
        }

        String sign = getSign(paramMap, paySecret);
        return signStr.equals(sign);
    }

    /**
     * 获取参数签名
     *
     * @param paramMap  签名参数
     * @param paySecret 签名密钥
     * @return sign
     */
    public static String getSign(Map<String, Object> paramMap, String paySecret) {
        SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, Object> m : smap.entrySet()) {
            Object value = m.getValue();
            if (value != null && String.valueOf(value) != null && "".equals(String.valueOf(value))) {
                stringBuffer.append(m.getKey()).append("=").append(value).append("&");
            }
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());

        String argPreSign = stringBuffer.append("&paySecret=").append(paySecret).toString();
        log.info("待签名字符串===" + argPreSign);
        String signStr = MD5Util.encode(argPreSign).toUpperCase();
        log.info("平台生成的签名===" + signStr);
        return signStr;
    }
}
