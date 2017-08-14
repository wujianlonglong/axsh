package anxian.gateway.admin.module.business.controller.shopInfo;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.customerComplain.domain.ShopInfo;
import client.api.customerComplain.domain.ShopSearch;
import client.api.sale.model.ResponseMessage;
import client.api.shopInfo.ShopInfoApiClient;
import client.api.user.utils.page.PageForAdmin;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by zhangyunan on 17/4/5.
 */
@RestController
@RequestMapping(value = "/admin/shopInfo")
public class ShopInfoController extends BaseController {

    @Override
    protected void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private ShopInfoApiClient shopInfoApiClient;


    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    public PageForAdmin shopInfoList(@RequestParam(name = "shopId", required = false) String shopId,
                                     @RequestParam(name = "shopName", required = false) String shopName,
                                     @RequestParam(name = "area", required = false) String area,
                                     @RequestParam(name = "address", required = false) String address,
                                     @RequestParam(name = "shopOwner", required = false) String shopOwner,
                                     @RequestParam(name = "contactInfo", required = false) String contactInfo,
                                     @RequestParam(name = "state", required = false) Integer state,
                                     @RequestParam(name = "areaOwner", required = false) String areaOwner,
                                     @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                     @RequestParam(name = "limit", required = false, defaultValue = "10")
                                             int size,
                                     Principal principal) {
        PageForAdmin pageForAdmin = shopInfoApiClient.pageGetShopInfoList(shopId, shopName, area, address, shopOwner, contactInfo, state, areaOwner, page - 1, size);
        return pageForAdmin;
    }

    /**
     * 门店查询列表
     *
     * @param limit
     * @param page
     * @return
     */
    @RequestMapping(value = "/listForShop", method = RequestMethod.GET)
    public SjesPage<ShopInfo> shopListForComplain(String shopId, String shopName, String areaOwner, int limit, int page) {
        ShopSearch gateShopSearch = new ShopSearch();
        gateShopSearch.setShopId(shopId);
        gateShopSearch.setShopName(shopName);
        gateShopSearch.setAreaOwner(areaOwner);
        if (gateShopSearch != null && limit != 0 && page != 0) {
            Pageable pageable = new PageRequest(page, limit);
            gateShopSearch.setPage(page - 1);
            gateShopSearch.setSize(limit);

            return shopInfoApiClient.getShopList(gateShopSearch);
        }
        return null;
    }

    /**
     * 更新门店信息
     *
     * @param shopInfo
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/alter")
    public JsonMsg save(ShopInfo shopInfo, Principal principal) {

        try {
            ResponseMessage responseMessage = shopInfoApiClient.updateShopInfo(shopInfo);
            if (responseMessage.getCode() == 1) {

                return JsonMsg.success("修改成功");
            } else {
                return JsonMsg.failure("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("兄弟，网络有问题");
        }

    }

    /**
     * 新增门店
     *
     * @param shopInfo
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public JsonMsg add(ShopInfo shopInfo, Principal principal) {

        try {
            ResponseMessage responseMessage = shopInfoApiClient.addShop(shopInfo);
            if (responseMessage.getCode() == 1) {

                return JsonMsg.success("添加门店成功");
            } else {
                return JsonMsg.failure("添加门店失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("兄弟，网络有问题");
        }

    }


}
