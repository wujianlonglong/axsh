package client.api.sale.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by byinbo on 2016/12/23.
 */
@Data
public class PromotionSycnViewModel implements Serializable {

    /**
     * ERP促销名称
     */
    private String promotionName;

    /**
     * 促销类型
     */
    private String promotionType;

    /**
     * 会员满
     */
    private String memberBuyLimit;

    /**
     * 惠用户满
     */
    private String buyLimit;

    /**
     * 会员数量满
     */
    private String memberBuyLimitNum;

    /**
     * 数量满
     */
    private String buyLimitNum;

    /**
     * 单笔最大优惠金额
     */
    private BigDecimal maxDiscount;

    /**
     * 促销商品
     */
    private List<PromotionGood> promotionGoods;

    /**
     * 促销门店
     */
    private List<PromotionShop> promotionShops;

    /**
     * 开始日期
     */
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    /**
     * 结束日期
     */
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;

    public String getExtViewStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getStartDate() != null ? formatter.format(getStartDate()) : "";
    }

    public String getExtViewEndDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return getEndDate() != null ? formatter.format(getEndDate()) : "";
    }

    /**
     * 促销名称
     */
    private String promotionSycnName;

    /**
     * 使用场景
     */
    private int[] scenes;

    /**
     * 会员级别
     */
    private int[] memberLevels;

    /**
     * 广告语
     */
    private String slogan;

    /**
     * 商品促销信息
     */
    private String promotionInfo;

    /**
     * 优惠金额上限
     */
    private double discountLimit;

    /**
     * 手机号
     */
    private String mobiles;

    /**
     * 提示内容
     */
    private String content;

    /**
     * 图片
     */
    private String imageUrl;
}
