package client.api.www.constants;

/**
 * Created by qinhailong on 15/9/17.
 */
public enum DeviceEnum {

    /**
     * 网站
     */
    site(0, "网站"),

    /**
     * APP
     */
    app(1, "APP"),

    /**
     * 微信
     */
    weiChat(2, "微信"),

    /**
     * 平板
     */
    pad(3, "平板");

    private Integer value;

    private String name;

    DeviceEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


}
