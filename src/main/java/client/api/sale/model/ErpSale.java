package client.api.sale.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by kimiyu on 16/12/13.
 */
@Data
public class ErpSale implements Serializable {

    private String id;

    private String promotionId;

    private String promotionName;

    /**
     * 促销类型：
     * A-金额满减
     * D-第N件N折
     * G-数量满减
     * K-捆绑
     * QC-全场打折
     */
    private String promotionType;

    /**
     * 会员金额
     */
    private BigDecimal memberSaleAmount;

    /**
     * 会员折扣
     */
    private BigDecimal memberDiscount;

    /**
     * 金额
     */
    private BigDecimal saleAmount;

    /**
     * 折扣
     */
    private BigDecimal discount;

    /**
     * 最大优惠金额
     */
    private BigDecimal maxDiscount;

    /**
     * 会员数量
     */
    private BigDecimal memberSaleQuantity;

    /**
     * 数量
     */
    private BigDecimal saleQuantity;

    /**
     * 打折梯度
     */
    private BigDecimal discountStep;

    /**
     * 会员折扣等级
     */
    private BigDecimal memberDiscountLevel;

    /**
     * 折扣等级
     */
    private BigDecimal discountLevel;

    /**
     * 最大优惠数量
     */
    private BigDecimal maxQuantity;

    private LocalDate startDateTime;

    private LocalDate endDateTime;
}
