package anxian.gateway.admin.module.business.controller.order.model;

import client.api.order.model.*;
import lombok.Data;

import java.util.List;

/**
 * 待拣货订单拆分和订单详情显示model
 * Created by Jianghe on 16/2/5.
 */
@Data
public class ResoluteOrderViewModel {
    /**
     * 订单
     */
    private Order order;

    /**
     * 拆分商品列表
     */
    private List<OrderItemView> orderItemViews;

    /**
     * 商品明细
     */
    private List<OrderItem> orderItems;

    /**
     * 积分换购商品
     */
    private List<OrderScoreItem> orderScoreItems;

    /**
     * 物流明细
     */
    List<TrackModel> orderTracks;

}
