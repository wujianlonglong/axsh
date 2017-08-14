package client.api.sale.util;

/**
 * Created by gaoqichao on 15-12-25.
 * 活动页选中商品样式
 */
public enum ActProductClassEnum {
    /**
     * 晃动
     */
    ROCK(1, "晃动"),
    /**
     * 透明度
     */
    TRANSPARENCY(2, "透明度"),
    /**
     * 阴影
     */
    SHADOW(3, "阴影");

    private int value;

    private String description;

    ActProductClassEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int value() {
        return this.value;
    }

    public String description() {
        return this.description;
    }
}
