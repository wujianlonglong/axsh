package client.api.order;

import client.api.order.model.Order;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jianghe on 16/2/5.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/orders")
public interface OrderApiClient {

    /**
     * 根据订单编号查询订单信息
     *
     * @param id 订单ID
     * @return 返回订单信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Order findOrder(@PathVariable("id") Long id);
}
