package client.api.shopInfo;

import client.api.customerComplain.domain.ShopInfo;
import client.api.customerComplain.domain.ShopSearch;
import client.api.sale.model.ResponseMessage;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhangyunan on 17/4/5.
 */
@FeignClient("sjes-api-user")
@RequestMapping("/shopInfo")
public interface ShopInfoApiClient {

    /**
     * 获取门店信息列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/page")
    PageForAdmin pageGetShopInfoList(@RequestParam(name = "shopId", required = false) String shopId,
                                     @RequestParam(name = "shopName", required = false) String shopName,
                                     @RequestParam(name = "area", required = false) String area,
                                     @RequestParam(name = "address", required = false) String address,
                                     @RequestParam(name = "shopOwner", required = false) String shopOwner,
                                     @RequestParam(name = "contactInfo", required = false) String contactInfo,
                                     @RequestParam(name = "state", required = false) Integer state,
                                     @RequestParam(name = "areaOwner", required = false) String areaOwner,
                                     @RequestParam("page") int page,
                                     @RequestParam("size") int size);

    /**
     * 显示所有门店信息
     *
     * @param shopSearch 参与门店查询条件
     * @return 返回查询结果
     */
    @RequestMapping(value = "/searchlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SjesPage<ShopInfo> getShopList(ShopSearch shopSearch);

    /**
     * 更新门店信息
     *
     * @param shopInfo 　门店对象
     */

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    ResponseMessage updateShopInfo(@RequestBody ShopInfo shopInfo);

    /**
     * 新增门店
     *
     * @param shopInfo 　门店对象
     */

    @RequestMapping(method = RequestMethod.POST, value = "/addShop")
    ResponseMessage addShop(@RequestBody ShopInfo shopInfo);

}
