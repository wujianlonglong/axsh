package client.api.customerComplain.domain;

/**
 * 客诉平台枚举类
 */
public enum ComplainTypeEnum {

    /**
     * 商品质量
     */
    QUALITY_GOOD("101"),

    /**
     * 商品价格
     */
    PRICE_GOOD("102"),

    /**
     * 商品缺货
     */
    LESS_GOOD("103"),

    /**
     * 服务态度
     */
    ATTITUDE_SERVICE ("104"),

    /**
     * 物流配送
     */
    LOGISTICS ("105"),

    /**
     * 购物环境
     */
    SHOP_ENVIRONMENT ("106"),

    /**
     * 其他类型
     */
    OTHER ("107");

    /**
     * 请求方法名
     */
    private String complainTypeEnum;

    ComplainTypeEnum(String complainTypeEnum) {
        this.complainTypeEnum = complainTypeEnum;
    }

    public String getComplainTypeEnum() {
        return complainTypeEnum;
    }

    @Override
    public String toString() {
        return this.name() + "[" + complainTypeEnum + "]";
    }
}
