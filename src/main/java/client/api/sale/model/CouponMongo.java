package client.api.sale.model;

import client.api.anxian.shop.AXGateShop;
import client.api.item.domain.Product;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class CouponMongo implements Serializable {

    @Id
    private String id;
    /**
     * 促销名称
     */
    private String name = "";

    /**
     * 促销类型
     */
    private Integer saleType = 20;

    /**
     * 开始时间
     */
    private String startDateTime;

    /**
     * 开始日期
     */
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    private String endDateTime;

    /**
     * 结束日期
     */
    private LocalDateTime endDate;

    /**
     * 使用场景
     */
    private List<Integer> channels = new ArrayList<>();

    /**
     * 会员等级
     */
    private List<Integer> memberLevel = new ArrayList<>();

    /**
     * 发券总量
     */
    private Integer totalNumber = 0;

    /**
     * 参与门店状态: 1 所有门店;2 部分门店
     */
    private Integer joinGateShopType = 0;

    /**
     * 参与门店列表
     */
    private List<String> joinedShops = new ArrayList<>();;

    /**
     * 参与门店列表--详细
     */
    private List<AXGateShop> joinedShopsDetail = new ArrayList<>();;

    /**
     * 换购优惠劵数量
     */
    private Integer limitVolumeNum = 0;

    /**
     * 促销状态
     */
    private Integer status = SaleConstant.notBegin;


    /**
     * 优惠阶梯层级
     */
    private Integer benefitLevel = 0;
    /**
     * 参与的优惠券
     */
    private List<EpbVolume> joinedVolumes = new ArrayList<>();;


    private Integer participationMode; // 参与方式

    private List<Long> joinedItems = new ArrayList<>();;   // 参与活动商品

    private List<Product> joinedProducts = new ArrayList<>();; // 参与活动商品的详细信息

    private String usedExplanation;     // 使用说明

    /**
     * 领卷开始日期
     */
    private String receiveStartDate;

    private LocalDateTime getVolumeStartDate;    // 领卷开始日期

    /**
     * 领券结束日期
     */
    private String receiveEndDate;


    private LocalDateTime getVolumeEndDate;      // 领卷结束日期

    private Integer limitGetTotalVolume;// 用户总共限领数量
    private Integer limitDayTotalNumber;    // 用户每天限领总数
    private Integer perDayTotalExchangeAmount;  // 用户每人每天限兑换张数

    private Integer dayGetTotalVolume;  // 每天限领

    /**
     * 是否同步
     */
    private Boolean isSync = false;

    /**
     * 每天开始时间
     */
    private String receiveDayTime;


    private LocalTime dayGetStartDate;       // 每天开始时间


    private String operator;


    private LocalDateTime operateTime;


    /**
     * 备注
     */
    private String memo = "";
}
