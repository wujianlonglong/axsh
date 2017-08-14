package client.api.order;

import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import client.api.order.model.PickingCondition;
import client.api.order.model.PrintOrderView;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jianghe on 16/2/6.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/adminOrders")
public interface PrintApiClient {

    /**
     * 打印订单查询
     */
    @RequestMapping(value = "/print/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<Order> treatsOrders(PickingCondition pickingCondition);

    /**
     * 打印订单
     *
     * @param orderId 订单ID
     */
    @RequestMapping(value = "/print", method = RequestMethod.POST)
    OrderApiResponse printOrder(@RequestParam("orderId") Long orderId,
                                @RequestParam("userId") Long userId,
                                @RequestParam("userName") String userName,
                                @RequestParam("shopId") String shopId);

    /**
     * 打印订单明细
     */
    @RequestMapping(method = RequestMethod.GET, value = "/print/{orderId}")
    OrderApiResponse<PrintOrderView> printOrderView(@PathVariable("orderId") Long orderId);

    /**
     * 确认收货.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/confirmOrder/{orderId}")
    OrderApiResponse confirmOrder(@PathVariable("orderId") Long orderId);
}
