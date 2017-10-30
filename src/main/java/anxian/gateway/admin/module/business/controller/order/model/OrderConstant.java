package anxian.gateway.admin.module.business.controller.order.model;

import java.math.BigDecimal;

/**
 * Created by Jianghe on 16/1/20.
 */
public class OrderConstant {

    // 设置缓存文件夹
    public static final String ORDER_PATH = "/ordertmp/";

    public static final String PAY_PATH = "/paytmp/";

    /**
     * 成功编码
     */
    public static final int successCode = 1;
    /**
     * 成功
     */
    public static final String SUCCESS = "SUCCESS";
    /**
     * 失败编码
     */
    public static final int failCode = 0;
    /**
     * 失败
     */
    public static final String FAIL = "FAIL";

    /**
     * 用于生成订单编号
     */
    public static final String ORDERIDSTR = "95";

    // 以下为标记商品是否有效
    /**
     * 有效商品
     */
    public static final String VALIDPRODUCT = "VALID";
    /**
     * 无效商品
     */
    public static final String UNVALIDPRODUCT = "UNVALID";

    // 以下为消息状态
    /**
     * 发送成功
     */
    public static final String SEND_SUCCESS = "SEND_SUCCESS";
    /**
     * 发送失败
     */
    public static final String SEND_FAILED = "SEND_FAILED";

    // 运费金额
    /**
     * 一级订单金额设定值:50
     */
    public static final BigDecimal firstOrderMoney = new BigDecimal(50);
    /**
     * 二级订单金额设定值:88
     */
    public static final BigDecimal secondOrderMoney = new BigDecimal(88);
    /**
     * 订单基础重量
     */
    public static final BigDecimal baseWeight = new BigDecimal(5);
    /**
     * 一级订单免费金额
     */
    public static final BigDecimal firstFreeMoney = new BigDecimal(88);
    /**
     * 二级订单免费金额
     */
    public static final BigDecimal secondFreeMoney = new BigDecimal(176);
    /**
     * 一级订单免费重量
     */
    public static final BigDecimal firstFreeWeight = new BigDecimal(10);
    /**
     * 二级订单免费重量
     */
    public static final BigDecimal secondFreeWeight = new BigDecimal(20);
    /**
     * 每KG金额
     */
    public static final BigDecimal perTransMoney = new BigDecimal(1.5);
    /**
     * 每XKG
     */
    public static final BigDecimal perWeight = new BigDecimal(1);
    /**
     * 一级运费
     */
    public static final BigDecimal firstTransMoney = new BigDecimal(10);
    /**
     * 二级运费
     */
    public static final BigDecimal secondTransMoney = new BigDecimal(5);

    // 以下为要用到的消息队列
    /**
     * 库存Topic
     */
    public static final String TopicForStock = "STOCKTOPIC";
    /**
     * 秒杀或满赠Topic
     */
    public static final String TopicForPromotion = "PROMOTIONTOPIC";
    /**
     * 优惠劵TOPIC
     */
    public static final String TopicForVolume = "VOLUMETOPIC";
    /**
     * 赠品TOPIC
     */
    public static final String TopicForGift = "GIFTTOPIC";
    /**
     * 支付队列
     */
    public static final String TopicForPay = "PAYTOPIC";
    /**
     * 物流队列
     */
    public static final String TopicForTrack = "TRACKTOPIC";
    /**
     * 积分队列
     */
    public static final String TopicForIntegral = "INTEGRALTOPIC";


    /**
     * 库存消息队列
     */
    public static final String stockQueue = "stockQueue";
    public static final String cancelStockQueue = "cancelStockQueue";
    /**
     * 秒杀/满赠消息队列
     */
    public static final String promotionQueue = "promotionQueue";
    public static final String cancelPromotionQueue = "cancelPromotionQueue";
    /**
     * 优惠劵消息队列
     */
    public static final String volumeQueue = "volumeQueue";
    public static final String cancelVolumeQueue = "cancelVolumeQueue";

    // 赠品消息队列
    /**
     * 订单到赠品消息队列
     */
    public static final String OrderToGiftQueue = "OrderToGiftQueue";
    public static final String cancelOrderToGiftQueue = "cancelOrderToGiftQueue";
    /**
     * 赠品到消息通知队列
     */
    public static final String GiftToOrderQueue = "GiftToOrderQueue";
    public static final String cancelGiftToOrderQueue = "cancelGiftToOrderQueue";
    /**
     * 支付消息队列
     */
    public static final String cancelPayQueue = "cancelPayQueue";
    /**
     * 物流消息队列
     */
    public static final String trackQueue = "trackQueue";
    public static final String cancelTrackQueue = "cancelTrackQueue";
    /**
     * 积分消息队列
     */
    public static final String integralQueue = "integralQueue";

    // 以下为订单商品退货状态
    /**
     * 未退货
     */
    public static final int notReturnGoods = 0;
    /**
     * 部分退货
     */
    public static final int partReturnedGoods = 1;
    /**
     * 已退货
     */
    public static final int returnedGoods = 2;

    // 以下为配送方式
    /**
     * 三江配送
     */
    public static final int sendForSJ = 1;
    public static final String sendForSJName = "三江配送";
    /**
     * 门店自提
     */
    public static final int shopPicking = 2;
    public static final String shopPickingName = "门店自提";
    /**
     * 大客户自提
     */
    public static final int SEND_FOR_VIPCUST = 3;
    public static final String sendVipCust = "大客户配送";

    public static final int QUICK_MEAL = 4;
    public static final String quickMealName = "快餐自提";

    /**
     * 安鲜配送
     */
    public static final int sendForAnXian = 5;
    public static final String sendForAnXianName = "安鲜配送";

    // 以下为赠品服务处理结果
    /**
     * 成功
     */
    public static final String successForGiftOrder = "T";
    /**
     * 失败
     */
    public static final String failForGiftOrder = "F";

    // 以下为消息处理结果
    /**
     * 处理成功
     */
    public final static String Message_Compelete = "COMPELETE";
    /**
     * 处理失败
     */
    public final static String Message_Failed = "FAILED";

    // 以下为服务台退货类型
    /**
     * 整单退
     */
    public final static int returnGoodsForAll = 5;
    /**
     * 部分退
     */
    public final static int returnGoodsForPart = 10;

    /**
     * 消息分隔符
     */
    public final static String splitForMessage = "-";
}
