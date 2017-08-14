package client.api.order;

import client.api.order.model.CancelOrderView;
import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by kimiyu on 16/4/12.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface CancelOrderApiClient {

    /**
     * 订单后台取消
     */
    @RequestMapping(value = "/orderAdminCancel", method = RequestMethod.POST)
    OrderApiResponse<List<Order>> cancelOrder(CancelOrderView cancelOrderView);
}
