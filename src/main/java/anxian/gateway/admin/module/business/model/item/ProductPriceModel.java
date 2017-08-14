package anxian.gateway.admin.module.business.model.item;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by qinhailong on 16-3-23.
 */
@Data
public class ProductPriceModel implements Serializable {

    private Long productId; // 商品Id

    private Long erpGoodsId; // 商品管理码

    private String sn; // 商品编码

    private String productName; // 商品名称

    private Integer status; // 0/1/2; 正常销售/下架停售/未审核

    private BigDecimal salePrice; // 销售价

    private BigDecimal memberPrice; // 会员价

    private BigDecimal saleHistoryPrice; // 销售历史价格

    private BigDecimal memberHistoryPrice; // 会员历史价格

    private String salePriceModifyUserName; // 销售价修改名

    private String memberPriceModifyUserName; // 会员价修改名

    private String salePriceModifyDate; // 会员价修改时间

    private String memberPriceModifyDate; // 会员价修改时间

}
