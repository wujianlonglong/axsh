package client.api.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * 优惠劵基础视图模型
 * Created by kimiyu on 15/9/10.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class VolumeViewModel extends BaseViewModel implements Serializable {
    /**
     * 优惠券层级
     */
    private Integer benefitLevel;
    /**
     * 促销类型
     */
    private Integer saleType;

    /**
     * 优惠券阶梯
     */
    List<JoinedVolume> joinedVolumes;
    /**
     * 参与或排除的商品列表
     */
    List<JoinedItem> joinedItems;
    /**
     * 限分类
     */
    List<JoinedCategory> joinedCategories;
    /**
     * 限品牌
     */
    List<JoinedBrand> joinedBrands;
    /**
     * 商品参与方式
     */
    private Integer participationMode;
    /**
     * 使用说明
     */
    private String usedExplanation;
    /**
     * 卷总量
     */
    private Integer totalVolumeNumber;
    /**
     * 每人每天限领张数
     */
    private Integer limitDayTotalNumber;
    /**
     * 每人每天限兑换张数
     */
    private Integer perDayTotalExchangeAmount;
    /**
     * 领卷类型
     */
    private Integer getVolumeType;
    /**
     * 卷开始时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime getVolumeStartDate;
    /**
     * 卷结束时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime getVolumeEndDate;
    /**
     * 用户总共限领张数
     */
    private Integer limitGetTotalVolume;
    /**
     * 每天阶梯限领张数
     */
    private Integer dayGetTotalVolume;
    /**
     * 每天限领开始时间
     */
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime dayGetStartDate;
    /**
     * 优惠码兑换
     */
    private String couponCode;
}
