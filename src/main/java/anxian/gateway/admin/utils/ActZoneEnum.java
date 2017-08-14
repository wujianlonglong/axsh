package anxian.gateway.admin.utils;

/**
 * Created by gaoqichao on 16-3-11.
 */
public enum ActZoneEnum {
    /**
     * 三江优选
     */
    SANGJIANG_CHOSED("100008", "三江优选"),

    /**
     * 惠商品
     */
    HUI_GOODS("100009", "惠商品"),

    /**
     * 周三疯
     */
    CRAZY_WEND("100010", "周三疯");

    ActZoneEnum(String zoneId, String zoneName) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
    }

    private String zoneId;

    private String zoneName;

    public String zoneId() {
        return this.zoneId;
    }

    public String zoneName() {
        return this.zoneName;
    }
}
