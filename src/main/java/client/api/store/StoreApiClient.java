package client.api.store;

import client.api.store.model.GateShop;
import client.api.store.model.GateShopSearch;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kimiyu on 16/1/11.
 */
@FeignClient("sjes-api-store")
@RequestMapping(value = "/stores/outservices")
public interface StoreApiClient {

    /**
     * 显示所有门店信息
     *
     * @param gateShopSearch 参与门店查询条件
     * @return 返回查询结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<GateShop> getGateShopList(GateShopSearch gateShopSearch);
}
