package client.api.www.constants;

/**
 * Created by qinhailong on 15-11-12.
 */
public enum HotGoodsEnum {

    /**
     * 标准图
     */
    stdPic(0, "标准图"),

    /**
     * 大图
     */
    bigPic(1, "大图");

    private Integer value;

    private String name;

    HotGoodsEnum(Integer value, String name) {
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
