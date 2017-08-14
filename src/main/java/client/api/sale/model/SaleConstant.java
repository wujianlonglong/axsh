package client.api.sale.model;

/**
 * 常量
 * Created by kimiyu on 15/9/10.
 */
public class SaleConstant {

    // 以下为业务返回通用提示
    public static final Integer successCode = 1;
    public static final String SUCCESS = "SUCCESS";
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


    public static final String CACHE_CARTITEM = ".cartitem.";

    public static final String CACHE_TURNTABLE = "turnTable";

    public static final String CACHE_JOINEDITEMS = ".joinedItems.";

    public static final String SESSION_USEDITEMIDS = "usedItems";

    // 设置缓存文件夹
    public static final String VOLUMETMP_PATH = "/volumetmp/";


}
