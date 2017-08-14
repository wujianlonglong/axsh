package anxian.gateway.admin.module.business.controller.order.model;

/**
 * Created by Jianghe on 16/1/25.
 */
public class LogisiticsStatus {
    /**
     * 待抢单
     */
    public static final Integer WAIT_FOR_RUSH_ORDER = 10;

    /**
     * 已抢单
     */
    public static final Integer RUSHED_ORDER = 20;

    /**
     * 已收单
     */
    public static final Integer GET_ORDER = 30;

    /**
     * 已妥投
     */
    public static final Integer PUTTED_IN = 40;

    /**
     * 取货失败
     */
    public static final Integer GET_GOODS_FAILURE = 25;

    /**
     * 投递失败
     */
    public static final Integer DELIVERY_FALURE = 35;

    /**
     * 已取消
     */
    public static final Integer CANCELED = 50;

    /**
     * 已完成
     */
    public static final Integer FINISHED = 60;

    /**
     * 异常
     */
    public static final Integer EXCEPTION = 70;
}
