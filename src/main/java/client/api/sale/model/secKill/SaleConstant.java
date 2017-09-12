package client.api.sale.model.secKill;

/**
 * 常量 Created by kimiyu on 15/9/10.
 */
public class SaleConstant {

    // 以下为业务返回通用提示
    public static final int successCode = 1;
    public static final String SUCCESS = "SUCCESS";
    public static final int failCode = 0;
    public static final String FAIL = "FAIL";

    // 以下为促销类型
    /**
     * 满赠类型
     */
    public static final int fullGift = 5;
    /**
     * 满赠描述
     */
    public static final String fullGiftName = "满赠";
    /**
     * 秒杀
     */
    public static final int secondKill = 10;
    /**
     * 秒杀
     */
    public static final String secondKillName = "秒杀";
    /**
     * 现金卷
     */
    public static final int cash = 15;
    /**
     * 满减卷
     */
    public static final int fullReduce = 20;
    /**
     * 积分换卷
     */
    public static final int integal = 25;
    /**
     * 大转盘
     */
    public static final int turnTable = 30;

    /**
     * 积分现金券
     */
    public static final int cashScore = 35;

    // 以下为促销状态
    /**
     * 未开始
     */
    public static final int notBegin = 5;
    /**
     * 进行中
     */
    public static final int doing = 10;
    /**
     * 强停
     */
    public static final int manualStop = 15;
    /**
     * 已结束
     */
    public static final int stop = 20;

    // 以下为活动参与类型
    /**
     * 全部参与
     */
    public static final int all = 1;
    /**
     * 部分参与
     */
    public static final int part = 2;
    /**
     * 限分类
     */
    public static final int limitClassification = 3;
    /**
     * 限品牌
     */
    public static final int limitBrand = 4;

    // 以下为使用环境类型
    /**
     * 网站
     */
    public static final int website = 1;
    /**
     * APP
     */
    public static final int app = 2;
    /**
     * 微商城
     */
    public static final int webChat = 3;
    /**
     * 平板
     */
    public static final int pad = 4;
    /**
     * 线下
     */
    public static final int offline = 5;
    /**
     * 安鲜生活
     */
    public static final int anxian = 8;

    // 以下为会员等级
    /**
     * 三江会员
     */
    public static final int member = 1;
    /**
     * 三江惠用户
     */
    public static final int benefitMember = 2;

    // 以下为领卷方式
    /**
     * 免费赠送
     */
    public static final int freePresent = 1;
    /**
     * 积分兑换
     */
    public static final int integralExchange = 2;
    /**
     * 微信互动
     */
    public static final int webChatInteraction = 3;
    /**
     * 单品送卷
     */
    public static final int singleProductVolume = 4;
    /**
     * 新人注册
     */
    public static final int newUser = 5;

    // 以下为商品等级分类
    /**
     * 一级分类
     */
    public static final int GRADE_ONE = 1;
    /**
     * 二级分类
     */
    public static final int GRADE_TWO = 2;

    /**
     * 三级分类
     */
    public static final int GRADE_THREE = 3;

    // 以下为优惠劵使用状态
    /**
     * 所有
     */
    public static final int allStatus = 0;
    /**
     * 未使用
     */
    public static final int unused = 5;

    /**
     * 已锁定
     */
    public static final int locked = 10;

    /**
     * 已使用
     */
    public static final int used = 15;
    /**
     * 已过期
     */
    public static final Integer Expired = 20;


    /**
     * 状态为正常
     */
    public static final Boolean NORMAL = Boolean.TRUE;
    /**
     * 状态为不正常
     */
    public static final Boolean UNNORMAL = Boolean.FALSE;

    /**
     * 已支付
     */
    public static final int PAYED = 1;

    /**
     * 未支付
     */
    public static final int NOPAYED = 0;

    /**
     * 订单提交
     */
    public static final int ORDERSUBMIT = 5;
    /**
     * 订单取消【所有针对订单的取消】
     */
    public static final int ORDERCANCEL = 10;

    /**
     * 以下为消息处理结果
     */
    public static final String SEND_FAILED = "SEND_FAILED";
    public static final String SEND_SUCCESS = "SEND_SUCCESS";
    public static final String MESSAGE_COMPELETE = "MESSAGE_COMPELETE";
    public static final String MESSAGE_FAILED = "MESSAGE_FAILED";


    // 以下为赠品库队列
    /**
     * 赠品库队列
     */
    public final static String TopicForGiftStock = "GIFT_STOCK_TOPIC";
    /**
     * 赠品消息
     */
    public static final String GIFT_QUEUE = "GIFT_QUEUE";
    /**
     * 赠品取消队列
     */
    public static final String CANCEL_GIFT_QUEUE = "CANCEL_GIFT_QUEUE";

    /**
     * 优惠劵TOPIC
     */
    public static final String TOPIC_FOR_VOLUME = "VOLUMETOPIC";
    /**
     * 促销缓存TOPIC
     */
    public static final String TOPIC_FOR_SALE_CACHE = "TOPIC_FOR_SALE_CACHE";
    /**
     * 赠品缓存TOPIC
     */
    public static final String TOPIC_FOR_GIFT_CACHE = "TOPIC_FOR_GIFT_CACHE";
    /**
     * 促销缓存更新队列
     */
    public static final String SALE_CACHE_UPDATE_QUEUE = "SALE_CACHE_UPDATE_QUEUE";
    /**
     * 赠品缓存更新队列
     */
    public static final String GIFT_UPDATE_CACHE_QUEUE = "GIFT_UPDATE_CACHE_QUEUE";

    /**
     * 积分生成现金券消息队列
     */
    public static final String SCORE_CASH_QUEUE = "SCORE_CASH_QUEUE";
    public static final String CANCEL_SCORE_CASH_QUEUE = "CANCEL_SCORE_CASH_QUEUE";

    // 以下为订单确认页的优惠券是否选中
    /**
     * 选中
     */
    public static final int isSelected = 1;
    /**
     * 未选中
     */
    public static final int noSelected = 0;

    // 以下为订单确认页优惠券促销
    /**
     * 优惠券缓存
     */
    public static final String volumeNumberCache = ".volumeCache";
    /**
     * 满赠缓存
     */
    public static final String fullGiftCache = "fullGiftCache";

    /**
     * 分隔符
     */
    public static final String splitForMessage = "-";
    /**
     * 秒杀缓存层级标记
     */
    public static final int secondKillCache = 0;

    /**
     * 以下为特例商品列表缓存
     */
    public static final String specProductCache = "SPEC_PRODUCT_CACHE";

    // 以下为商品类型
    /**
     * 促销商品
     */
    public static final int saleProduct = 1;
    /**
     * 正常商品
     */
    public static final int normalProduct = 2;

    /**
     * 可以使用
     */
    public static final int canuse = 25;
    /**
     * 不可使用
     */
    public static final int notuse = 30;
}
