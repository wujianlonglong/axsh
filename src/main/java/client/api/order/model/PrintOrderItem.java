package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kimiyu on 16/1/15.
 */
@Data
public class PrintOrderItem implements Serializable {
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品数量
     */
    private Integer number;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 商品总金额
     */
    private BigDecimal productAmount;

    /**
     * 换购商品总和
     */
    private Long scoreAmount;

    private String barCode;

    private String sku;
}
