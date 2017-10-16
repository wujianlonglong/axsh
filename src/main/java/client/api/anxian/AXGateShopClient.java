package client.api.anxian;

import client.api.anxian.shop.AXGateShop;
import client.api.anxian.shop.AXGateShopSearch;
import client.api.sale.model.ResponseMessage;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("sjes-hub-api")
@RequestMapping(value = "/gateShop")
public interface AXGateShopClient {
    @RequestMapping(value = "/getShopList", method = RequestMethod.POST)
    ResponseMessage<SjesPage<AXGateShop>> getShopList(AXGateShopSearch gateShopSearch);

    @RequestMapping(value = "/getShopDetail", method = RequestMethod.POST)
    AXGateShop getShopDetail(@RequestParam("shopId") String shopId);

    @RequestMapping(value = "/updateShop", method = RequestMethod.POST)
    ResponseMessage updateShop(AXGateShop gateShop);

    @RequestMapping(value = "/getShops", method = RequestMethod.POST)
    List<AXGateShop> getGateShopByShopId(@RequestBody List<String> shopIds);
}
