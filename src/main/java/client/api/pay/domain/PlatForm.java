package client.api.pay.domain;

/**
 * Created by kimiyu on 15/11/10.
 */
public interface PlatForm {

    /**
     * 京东众包
     */
    int JDPackage = 100;

    /**
     * 微信扫码支付
     */
    int webChatForSweepCode = 105;

    /**
     * 支付宝[移动端]
     */
    int AliPay = 110;

    /**
     * 电商银
     */
    int nbBank = 115;

    /**
     * 现金卷
     */
    int cashVolume = 120;

    /**
     * 货到付款
     */
    int deliveryPayment = 125;

    /**
     * 微信移动端支付
     */
    int webchatForApp = 130;

    /**
     * 支付宝即时到账支付
     */
    int AliPayDirect = 135;

    /**
     * h5支付
     */
    int webchatForH5 = 140;
}
