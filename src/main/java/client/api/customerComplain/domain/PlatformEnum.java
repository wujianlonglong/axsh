package client.api.customerComplain.domain;

/**
 * 客诉平台枚举类
 */
public enum PlatformEnum {

    /**
     * 三江购物各门店
     */
    PLATFORM_SJMD("10001"),

    /**
     * 三江购物网
     */
    PLATFORM_SJW("10004"),

    /**
     * 淘宝便利店(宁波)
     */
    PLATFORM_TB("10002"),

    /**
     * 淘鲜达(宁波)
     */
    PLATFORM_TXD("10003"),

    /**
     * 京东到家(三江门店)
     */
    PLATFORM_JD("10005"),

    /**
     * 百度外卖(三江门店)
     */
    PLATFORM_BD("10006"),

    /**
     * 其他
     */
    PLATFORM_OTHER("10007");

    /**
     * 请求方法名
     */
    private String platformEnum;

    PlatformEnum(String platformEnum) {
        this.platformEnum = platformEnum;
    }

    public String getPlatformEnum() {
        return platformEnum;
    }

    @Override
    public String toString() {
        return this.name() + "[" + platformEnum + "]";
    }
}
