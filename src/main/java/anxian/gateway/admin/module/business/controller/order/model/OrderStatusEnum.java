package anxian.gateway.admin.module.business.controller.order.model;

import lombok.Getter;

/**
 * Created by cs on 2016/10/31.
 */
@Getter
public enum OrderStatusEnum {

    UNFILLED_ORDER(1, "未发货"),
    SHIPPED_ORDER(2, "已发货"),
    RECEIVING_ORDER(3, "已收货"),
    RETURN_GOODS_ORDER(4, "已退货"),
//    PARTIAL_RECEIVING_ORDER(5, "部分收货"),
    PARTIAL_SHIPPED_ORDER(6, "部分发货中"),
    NOT_CREATE_SEND_ORDER(8, "还未创建物流订单"),
    DISTRIBUTION_ORDER(9, "配货中"),
    CANCEL_ORDER(-1, "已取消"),
    DELIVERED_ORDER(7, "已妥投"),
    allocateFail(-30, "已取消"),
    customHasCancel(-25, "已取消"),
    submitCancel(-24, "提交订单取消成功"),
    paidCancel(-23, "已付款取消成功"),
    verificateCancel(-22, "审核取消"),
    completeGoodsCancel(-21, "拣货完成取消"),
    systemHasCancel(-20, "已取消"),
    cancelByResolute(-19, "拆分订单[取消原订单]"),
    refundMoney(-15, "已退款"),
    inReturn(-14, "退货中"),
    returnGoods(-10, "已退货"),
    hasCancel(-5, "配送失败"),
    treatPayment(5, "待支付"),
    hasPayed(10, "待拣货[已支付]"),
    treatVerificate(15, "待审核"),
    treatSortGoods(16, "待拣货"),
    sortGoods(20, "拣货中"),
    completeGoods(25, "拣货完成,等待配送"),
    treatPick(30, "待自提"),
    undelivered(35, "待发货"),
    inAllocation(40, "配送中"),
    allocateSuccess(45, "配送成功"),
    hasDeliverGoods(50, "待收货"),
    hasComplete(60, "已完成"),
    returnGoodsForPart(65, "部分退货完成");


    private int code;
    private String statusMsg;

    OrderStatusEnum(int code, String statusMsg) {
        this.code = code;
        this.statusMsg = statusMsg;
    }

    @Override
    public String toString() {
        return "TaoBaoOrderStatusEnum[code=" + code + ", statusMsg= " + statusMsg + "]";
    }

    /**
     * 取得给定配送状态码对应的枚举值
     *
     * @param code 　配送状态码
     * @return　配送状态码对应的枚举
     */
    public static OrderStatusEnum of(int code) {
        for (OrderStatusEnum statusEnum : OrderStatusEnum.values()) {
            if (code == statusEnum.code) {
                return statusEnum;
            }
        }
        return null;
    }
}
