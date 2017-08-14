package client.api.www.constants;

/**
 * Created by qinhailong on 15/9/17.
 */
public enum AdvertisementPositionEnum {

    /**
     * 顶通
     */
    top(0, "顶通"),

    /**
     * 半通
     */
    half(1, "半通"),

    /**
     * 首焦
     */
    header(2, "首焦"),

    /**
     * 中焦
     */
    middle(3, "中焦"),

    /**
     * 右焦
     */
    right(4, "右焦"),

    /**
     * 2屏推荐
     */
    recommended(5, "2屏推荐"),

    /**
     * APP促销
     */
    app(6, "APP促销");

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
