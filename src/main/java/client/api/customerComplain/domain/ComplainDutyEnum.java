package client.api.customerComplain.domain;

/**
 * 定责枚举类
 */
public enum ComplainDutyEnum {

    /**
     * 门店
     */
    SHOP("1"),

    /**
     * 采购
     */
    BUYER("2"),

    /**
     * 三江配送
     */
    SANJIANG_LOGISTICS("3"),

    /**
     * 全渠道
     */
    DEPT_QUQUDAO ("4"),

    /**
     * 淘宝便利店
     */
    TAOBAO ("5"),

    /**
     * 京东到家
     */
    JD ("6"),

    /**
     * 百度外卖
     */
    BAIDU ("7"),

    /**
     * 第三方配送
     */
    THIRD_LOGISTICS ("8"),

    /**
     * 信息部
     */
    DEPT_INFO ("9"),

    /**
     * 加工中心
     */
    DEPT_HANDING ("10"),

    /**
     * 供应商
     */
    SUPPLIER ("11"),

    /**
     * 其他
     */
    OTHER ("12"),

    /**
     * 无责任
     */
    NO_RESPONSE ("13");

    /**
     * 请求方法名
     */
    private String complainDutyEnum;

    ComplainDutyEnum(String complainDutyEnum) {
        this.complainDutyEnum = complainDutyEnum;
    }

    public String getComplainDutyEnum() {
        return complainDutyEnum;
    }

    @Override
    public String toString() {
        return this.name() + "[" + complainDutyEnum + "]";
    }
}
