package client.api.sale;

import client.api.sale.model.JoinedGateShop;
import client.api.sale.model.ResponseMessage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Jianghe on 16/1/15.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/sales/gateshop")
public interface GateShopApiClient {

    /**
     * 保存参与的门店信息
     *
     * @param joinedGateShops
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage<List<JoinedGateShop>> saveJoinedGateShops(List<JoinedGateShop> joinedGateShops);
}
