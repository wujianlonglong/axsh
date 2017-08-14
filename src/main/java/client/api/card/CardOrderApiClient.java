package client.api.card;

import client.api.card.model.CardOrderAdmin;
import client.api.card.model.CardOrderAdminDTO;
import client.api.order.model.OrderApiResponse;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by kimiyu on 16/12/20.
 */
@FeignClient(value = "sjes-api-order")
@RequestMapping(value = "/cardOrders/admin", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface CardOrderApiClient {

    /**
     * 会员订单查询
     *
     * @param cardOrderAdminDTO
     * @return
     */
    @RequestMapping(value = "/searchList", method = RequestMethod.POST)
    OrderApiResponse<SjesPage<CardOrderAdmin>> cardOrderAdmins(CardOrderAdminDTO cardOrderAdminDTO);

    /**
     * 会员订单导出
     *
     * @param cardOrderAdminDTO
     * @return
     */
    @RequestMapping(value = "/exportList", method = RequestMethod.POST)
    OrderApiResponse<List<CardOrderAdmin>> exportList(CardOrderAdminDTO cardOrderAdminDTO);
}
