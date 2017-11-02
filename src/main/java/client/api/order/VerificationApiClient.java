package client.api.order;

import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import client.api.order.model.VerficationView;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 审核控制接口
 * Created by kimiyu on 15/9/14.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/orders")
public interface VerificationApiClient {

    /**
     * 单个订单审核
     *
     * @param orderId 订单ID
     */
    @RequestMapping(method = RequestMethod.GET, value = "/verificate/single/{orderId}")
    Integer verificateSingleOrder(@PathVariable("orderId") Long orderId);

    /**
     * 订单审核
     *
     * @param verficationView
     * @return
     */
    @RequestMapping(value = "/verficateOrders", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    OrderApiResponse<List<Order>> verficateOrder(VerficationView verficationView);
}
