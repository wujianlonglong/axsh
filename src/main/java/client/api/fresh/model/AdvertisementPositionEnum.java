package client.api.fresh.model;

/**
 * Created by mac on 15/9/17.
 */
public enum AdvertisementPositionEnum {

    /**
     * 首焦
     */
    header(2, "首焦"),

    /**
     * 右焦
     */
    right(4, "右焦"),

    /**
     * 特价商品列表
     */
    speci(6, "特价"),

    /**
     * 每日特价
     */
    speci_left(7, "每日特价");

    private Integer value;

    private String name;

    AdvertisementPositionEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
