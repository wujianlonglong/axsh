package anxian.gateway.admin.module.business.controller.store;

import client.api.store.StoreApiClient;
import client.api.store.model.GateShop;
import client.api.store.model.GateShopSearch;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店控制类
 * Created by kimiyu on 16/1/11.
 */
@RestController
@RequestMapping(value = "/gateshops")
public class GateShopController {

    @Autowired
    private StoreApiClient storeApiClient;

    /**
     * 门店列表(促销)
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping(value = "/listForSale", method = RequestMethod.GET)
    public SjesPage<GateShop> shopListForSale(String areaId, String shopId, String shopName, int limit, int page) {
        GateShopSearch gateShopSearch = new GateShopSearch();
        if (!"0".equals(areaId) && !StringUtils.isEmpty(areaId)) {
            gateShopSearch.setAreaId(areaId);
        }
        gateShopSearch.setShopId(shopId);
        gateShopSearch.setShopName(shopName);
        if (gateShopSearch != null && limit != 0 && page != 0) {
            gateShopSearch.setPage(page - 1);
            gateShopSearch.setSize(limit);
            return storeApiClient.getGateShopList(gateShopSearch);
        }
        return null;
    }
}
