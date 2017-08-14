package client.api.item.model;

import java.math.BigDecimal;

/**
 * Created by qinhailong on 16-5-31.
 */
public class ProductPriceModel {

    private Long erpGoodsId; // 对应ERP GoodsID

    private BigDecimal salePrice; // 销售价

    private BigDecimal memberPrice; // 会员价
}
