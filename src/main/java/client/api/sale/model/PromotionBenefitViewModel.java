package client.api.sale.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 秒杀促销 Created by kimiyu on 15/9/7.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PromotionBenefitViewModel extends PromotionViewModel implements Serializable {

    /**
     * 用户每天限买秒杀商品次数
     */
    private Integer limitBuyForSecondKill = 0;
    private String productId;          // 商品ID
    private String sn;                  // 商品sn编码
    private String itemName;        // 商品名称
    private Double memberPrice;     // 促销价
    private Double price;           // 零售价
    private Double discount;        // 折扣
    private Integer lessBuyNum;     // 单次最少购买数量
    private Integer moreBuyNum;     // 单次最多购买数量
    private String itemAd;          // 商品广告语
    private String itemPromotionInfo;   // 商品促销信息
    private String benefitMemo;     // 秒杀说明
}
