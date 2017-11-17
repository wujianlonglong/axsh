package client.api.item.domain;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Entity - 单品SKU
 * Created by qinhailong on 15-8-21.
 */
@Data
public class ProductOfShelves implements Serializable {

    private Long id; // 主键

    private Long priceId; // 价格主键

    private String shopId; // 门店Id

//    private Long goodsId; // 主商品ID

    private Long erpGoodsId; // 对应ERP GoodsID

//    private String goodsCode; // 商品内码(顺序号)

    private String sn; // 商品编码

//    private String barCode; // 商品条码

    private String name; // 商品名称

    //     @Transient
    private BigDecimal salePrice; // 销售价

    private BigDecimal originalSalePrice; // 原销售价

    //    @Transient
    private BigDecimal memberPrice; // 会员价

    private BigDecimal originalMemberPrice; // 原会员价

//    private String weight; // 商品重量

//    private String place; // 商品产地

//    private String source; // 商品来源, 枚举(自营/联营)

//    private Long sales; //　销量

//    private Long hits; // 点击数

//    private Integer transportType; // 商品运输类型 常温:0 冷藏/冷冻:1 保热:2

    private Long categoryId; // 商品分类Id

//    private Long brandId; // 品牌ID

//    @Transient
//    private String brandName; // 品牌名称

    private Integer status; // 0/1/2; 正常销售/下架停售/未审核
    private String statusMean; // 0/1/2; 正常销售/下架停售/未审核

    private Integer erpStatus; // ERP商品状态

//    private Long displaySales; // 展示销量
//
//    private Long erpProductId; // ERP产品ID
//
//    private String adSlogan; // 广告语
//
//    private Boolean isPromotionParticular; // 是否是促销例
//
//    private Boolean isBargains; // 是否是惠商品
//
//    private Boolean isSeckill; // 是否是秒杀
//
//    private Boolean isFullGift; // 是否满赠
//
//    private String introduction; // 介绍
//
//    private Integer promotionType; // 促销类型
//
//    private String remarker; // 备注
//
//    @CreatedDate
//    @JsonDeserialize(using = CustomDateDeSerializer.class)
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private LocalDateTime createDate; // 创建时间
//
//    @LastModifiedDate
//    @JsonDeserialize(using = CustomDateDeSerializer.class)
//    @JsonSerialize(using = CustomDateSerializer.class)
//    private LocalDateTime updateDate; // 更新时间

    public String getStatusMean() {
        if (categoryId == null) {
            return "分类未绑定";
        }
        if (status == 0) {
            return "正常销售";
        }
        if (status == 1) {
            return "下架停售";
        }
        if (status == 2) {
            return "未审核";
        }
        return "未知状态";
    }

}
