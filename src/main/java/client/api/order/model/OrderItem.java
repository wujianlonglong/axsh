package client.api.order.model;

import client.api.sale.model.SaleConstant;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by kimiyu on 15/7/20.
 */
@Data
public class OrderItem {

    private Long id;

    private Long orderId; // 订单ID

    private Long transferMarketId; // 要转换的商场ID

    private Long itemId; // 商品ID
    /**
     * 商品sn编码
     */
    private String sn;

    private String itemName; // 商品名称

    private String imageUrl;    // 商品图片

    private BigDecimal sellPrice; // 销售价

    private BigDecimal memberPrice; // 会员价

    private BigDecimal salePrice;   // 促销价

    private BigDecimal payPrice;    // 实际支付单价

    private BigDecimal sharePrice;  // 分摊单价金额

    private BigDecimal benefitTotalAmount; // 优惠总价

    private Integer saleType;   // 促销类型

    private String saleId; // 活动ID

    private String activityName; // 活动名称

    private String volumeNumber; // 满减券卷号

    private int purchasNum = 0; // 购买数量

    private int returnGoodsNum = 0; // 要退货数量

    private int returnedGoodsNum = 0; // 已退货的数量

    private BigDecimal itemWeight; // 商品单个重量

    private BigDecimal weight;  // 商品重量

    private Boolean isShow; // 是否显示:TRUE 是;FALSE 否;

    private Boolean transferFreezed; // 冻结是否成功: TRUE 是;FALSE 否;

    private String freezedFailMessage; // 冻结失败原因

    private Integer transportType; //商品运输类型

    private Integer itemStatus = 0; // 商品状态


    public String getSaleTypeName() {
        if (saleType == null) {
            return "";
        }
        if (saleType == SaleConstant.fullGift) {
            return "满赠";
        } else if (saleType == SaleConstant.secondKill) {
            return "秒杀";
        } else if (saleType == SaleConstant.cash) {
            return "现金券";
        } else if (saleType == SaleConstant.fullReduce) {
            return "满减券";
        } else if (saleType == SaleConstant.integal) {
            return "积分换券";
        } else if (saleType == SaleConstant.turnTable) {
            return "大转盘";
        } else if (saleType == SaleConstant.amountReduced) {
            return "金额满减";
        }else if (saleType == SaleConstant.partFold) {
            return "第n件n折";
        }else if (saleType == SaleConstant.fullCourt) {
            return "打折";
        }
        return "";
    }
}
