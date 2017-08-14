package anxian.gateway.admin.module.business.controller.sale.enums;

/**
 * Created by gaoqichao on 15-12-25.
 * 活动类型模板
 */
public enum ActTemplateTypeEnum {
    /**
     * 基础版
     */
    BASE(0, "(第一套)基础版"),

    /**
     * 促销版
     */
    SALE(1, "(第二套)促销版"),

    /**
     * 优惠券版
     */
    COUPON(2, "(第三套)优惠券版"),

    /**
     * 纯图片版
     */
    IMAGE(3, "(第四套)纯图片版"),

    /**
     * 未知
     */
    UNKNOWN(-1, "unknown");

    private int value;

    private String templateName;

    ActTemplateTypeEnum(int value, String templateName) {
        this.value = value;
        this.templateName = templateName;
    }

    public int value() {
        return this.value;
    }

    public String templateName() {
        return this.templateName;
    }

    public static ActTemplateTypeEnum of(int value) {
        ActTemplateTypeEnum templateTypeEnum = UNKNOWN;
        switch (value) {
            case 0:
                templateTypeEnum = BASE;
                break;
            case 1:
                templateTypeEnum = SALE;
                break;
            case 2:
                templateTypeEnum = COUPON;
                break;
            case 3:
                templateTypeEnum = IMAGE;
                break;
            case -1:
            default:
                break;
        }

        return templateTypeEnum;
    }
}
