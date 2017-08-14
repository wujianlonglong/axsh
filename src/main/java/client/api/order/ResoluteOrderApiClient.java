package client.api.order;

import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import client.api.order.model.OrderItemView;
import client.api.order.model.ResoluteOrderView;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 订单拆分
 * Created by Jianghe on 16/2/5.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/adminOrders")
public interface ResoluteOrderApiClient {

    /**
     * 根据订单ID获取待拆分的订单详情列表
     *
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/items/{orderId}", method = RequestMethod.GET)
    OrderApiResponse<List<OrderItemView>> getResoluteOrderItems(@PathVariable("orderId") Long orderId);

    /**
     * 拆分订单
     *
     * @param resoluteOrderView 拆分订单对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/resoluteOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderApiResponse<Order> resoluteOrder(ResoluteOrderView resoluteOrderView);


}
