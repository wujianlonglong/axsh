package client.api.order.model;

/**
 * Created by kimiyu on 15/11/10.
 */
public interface PayType {

    /**
     * 货到付款
     */
    int deliveryPayment = 1;
    String deliveryPaymentStr = "货到付款";

    /**
     * 在线支付
     */
    int onlinePayment = 2;
    String onlinePaymentStr = "在线支付";

    /**
     * 永耀店支付
     */
    int yongyaoPayment = 3;
    String yongyaoPaymentStr = "永耀门店支付";
}
