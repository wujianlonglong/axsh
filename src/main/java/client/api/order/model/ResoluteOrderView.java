package client.api.order.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kimiyu on 16/1/15.
 */
@Data
public class ResoluteOrderView implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 拆单人ID
     */
    private Long userId;

    /**
     * 拆单人姓名
     */
    private String username;

    /**
     * 拆分商品列表
     */
    private List<OrderItemView> orderItemViews;
}
