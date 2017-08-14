package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单积分商品
 * Created by kimiyu on 15/7/20.
 */
@Data
public class OrderScoreItem implements Serializable {

    private Long id;

    private Long orderId; // 订单ID

    private Long itemId; // 商品ID
    /**
     * 商品sn编码
     */
    private String sn;

    private String itemName; // 商品名称

    private String imageUrl;    // 商品图片

    private BigDecimal sellPrice; // 零售价

    private BigDecimal memberPrice; // 会员价

    private Long score;   // 积分

    private BigDecimal scorePrice; // 积分单价

    private BigDecimal payPrice;    // 实际支付单价

    private Long scoreAmount; // 积分总和

    private BigDecimal retailAmount;    // 单类商品的总价

    private Integer saleType;   // 促销类型

    private String saleId; // 活动ID

    private String activityName; // 活动名称

    private int purchasNum = 0; // 购买数量

    private int returnedGoodsNum = 0; // 已退货的数量

    private BigDecimal itemWeight; // 商品单个重量

    private BigDecimal weight;  // 商品重量

    private Integer itemStatus = 0; // 商品状态
}
