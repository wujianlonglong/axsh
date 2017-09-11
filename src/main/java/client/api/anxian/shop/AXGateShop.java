package client.api.anxian.shop;

import lombok.Data;

@Data
public class AXGateShop {

    private String id;

    /***
     * 门店状态
     */
    private Integer state;

    /***
     * 经度
     */
    private Double longitude;

    /***
     * 纬度
     */
    private Double latitude;

    /***
     * 区域
     */
    private String areaName;

    /***
     * 门店编号
     */
    private String shopId;

    /***
     * 门店名称
     */
    private String shopName;

    /***
     * 配送方
     */
    private Integer distributWay;

    /***
     * 运费
     */
    private String distributCost;

    /***
     * 门店地址
     */
    private String address;

    /***
     * 配送距离
     */
    private String distance;

    /***
     * 开店时间
     */
    private String openTime;

    /***
     * 闭店时间
     */
    private String closeTime;

    /***
     * 配送时间
     */
    private Integer distributNeedTime;

    /***
     * 间隔时间
     */
    private Integer intervalTime;

    /***
     * 拣货打包准备时间
     */
    private Integer prepareTime;
    /***
     * 购买平台  10004:安鲜生活；10005:三江网购
     */
    private int platform;

    /***
     * 店长姓名
     */
    private String shopOwner;

    /***
     * 店长手机号
     */
    private String mobile;

    /***
     * 营业面积
     */
    private String floorArea;

    /***
     * 门店类型
     */
    private Integer type;
}
