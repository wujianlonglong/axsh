package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by kimiyu on 16/1/15.
 */
@Data
public class OrderItemView implements Serializable {

    /**
     * id
     */
    private Long id;
    /**
     * 管理编码
     */
    private String sn;
    /**
     * 商品编码
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 销售单价
     */
    private BigDecimal sellPrice;
    /**
     * 商品换购积分
     */
    private Long score;
    /**
     * 购买数量
     */
    private Integer number;
    /**
     * 新订单商品数量
     */
    private Integer newNumber;
}
