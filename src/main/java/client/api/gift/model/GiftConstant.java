package client.api.gift.model;

/**
 * 赠品服务常用变量
 * Created by kimiyu on 15/11/25.
 */
public class GiftConstant {

    // 通用常量
    public static final Integer successCode = 1;
    public static final String successMessage = "success";
    public static final Integer failCode = 0;
    public static final String failMessage = "fail";

    // 赠品库存状态
    /**
     * 正常
     */
    public static final Integer Normal = 5;
    /**
     * 过期
     */
    public static final Integer Outdate = 10;
    /**
     * 无货
     */
    public static final Integer NoStore = 15;

    // 赠品来源
    /**
     * 大库
     */
    public static final Integer BigStore = 5;
    /**
     * 采购供应商
     */
    public static final Integer Purchas = 10;
    /**
     * 外采
     */
    public static final Integer OutPurchas = 15;
    /**
     * 内采
     */
    public static final Integer InnerPurchas = 20;

    // 以下为赠品消息对列
    public static final String TopicForGift = "GIFTTOPIC";
    /**
     * 赠品添加消息队列
     */
    public static final String giftQueue = "giftQueue";
    /**
     * 赠品取消消息队列
     */
    public static final String cancelGiftQueue = "cancelGiftQueue";

    // 以下为消息处理结果
    /**
     * 处理成功
     */
    public final static String Message_Compelete = "COMPELETE";
    /**
     * 处理失败
     */
    public final static String Message_Failed = "FAILED";
}
