package client.api.www.constants;

/**
 * Created by qinhailong on 15/9/17.
 */
public enum AdvertisementStatusEnum {

    /**
     * 正常
     */
    normal(0, "正常"),

    /**
     * 不展示
     */
    unShow(1, "不展示"),

    /**
     * 已过期
     */
    expired(2, "已过期");

    private Integer value;

    private String name;

    AdvertisementStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
}
