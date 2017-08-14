package client.api.order.model;

/**
 * 订单状态 Created by kimiyu on 15/10/12.
 */
public interface OrderStatus {

    /**
     * 已取消[网站前台,web]
     */
    int allocateFail = -30;
    /**
     * 已取消[客服取消,admin]
     */
    int customHasCancel = -25;

    /**
     * 提交订单取消成功
     */
    int submitCancel = -24;
    /**
     * 已付款取消成功
     */
    int paidCancel = -23;
    /**
     * 审核取消
     */
    int verificateCancel = -22;
    /**
     * 拣货完成取消
     */
    int completeGoodsCancel = -21;

    /**
     * 已取消[系统自动取消,admin]
     */
    int systemHasCancel = -20;

    /**
     * 拆分订单[取消原订单]
     */
    int cancelByResolute = -19;

    /**
     * 已退款[admin]
     */
    int refundMoney = -15;
    /**
     * 退货中
     */
    int inReturn = -14;
    /**
     * 已退货[全部退货]
     */
    int returnGoods = -10;

    /**
     * 配送失败[admin]
     */
    int hasCancel = -5;

    /**
     * 待支付
     */
    int treatPayment = 5;
    /**
     * 待拣货[已支付]
     */
    int hasPayed = 10;
    /**
     * 待审核
     */
    int treatVerificate = 15;
    /**
     * 待拣货[admin]
     */
    int treatSortGoods = 16;
    /**
     * 拣货中[admin]
     */
    int sortGoods = 20;
    /**
     * 拣货完成,等待配送[admin]
     */
    int completeGoods = 25;
    /**
     * 待自提
     */
    int treatPick = 30;
    /**
     * 待发货
     */
    int undelivered = 35;

    /**
     * 配送中[admin]
     */
    int inAllocation = 40;
    /**
     * 配送成功[admin]
     */
    int allocateSuccess = 45;
    /**
     * 待收货
     */
    int hasDeliverGoods = 50;
    /**
     * 已签收[作废]
     */
    //int hasReceived = 55;
    /**
     * 已完成
     */
    int hasComplete = 60;
    /**
     * 部分退货完成
     */
    int returnGoodsForPart = 65;
}
