package anxian.gateway.admin.module.business.controller.anxian.coupon;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.business.service.PreSellService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import client.api.sale.model.SaleManageCondition;
import client.api.sale.model.preSell.PreSell;
import client.api.search.SearchApiClient;
import client.api.store.StoreApiClient;
import client.api.store.model.GateShop;
import client.api.store.model.GateShopSearch;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Created by kimiyu on 2017/2/22.
 */
@Controller
@RequestMapping("/anxian/preSell")
public class PreSellController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private PreSellService preSellService;

    @Autowired
    private SearchApiClient searchApiClient;

    @Autowired
    private StoreApiClient storeApiClient;

    @RequestMapping("/list")
    public String preSells(Principal principal, Model model){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        return "anXian-promotion/presell";
    }

    @RequestMapping(value = "/ajax", method = RequestMethod.POST)
    public String ajaxPreSells(Principal principal, Model model, SaleManageCondition saleManageCondition){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        SjesPage<PreSell> resonse = preSellService.listBySearch(saleManageCondition);
        model.addAttribute("totalPages", resonse.getTotalPages());
        model.addAttribute("page", resonse.getNumber() + 1);
        model.addAttribute("size", resonse.getSize());
        model.addAttribute("preList", resonse.getContent());
        return "anXian-promotion/presell-ajax";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMsg save(Principal principal, @RequestBody PreSell preSell) {
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return JsonMsg.failure("请先登入");
        }
        if (null == preSell) {
            return JsonMsg.failure("传入对象为空！");
        }
        return preSellService.save(preSell);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getById(Principal principal, Model model, String id) {
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        model.addAttribute("id", id);
        return "anXian-promotion/edit-preSell";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonMsg getById(@PathVariable("id") String id) {
        return preSellService.getOne(id);
    }


    /**
     * 删除商品预售
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonMsg deleteById(@PathVariable("id") String id) {
        if (StringUtils.isEmpty(id)) {
            return JsonMsg.failure("参数为空");
        }
        return preSellService.deleteById(id);
    }

    /**
     * 活动强停
     * @param id
     * @param reason
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public JsonMsg manualStop(String id, String reason) {
        return preSellService.manualStop(id, reason);
    }

    @ResponseBody
    @RequestMapping(value = "/productSearch", method = RequestMethod.GET)
    public PageModel<Product> productSearch(String name, Long productId, int page, int limit) {
        PageModel<Product> productPageModels = searchApiClient.listProducts(productId, name, page - 1, limit);
        return productPageModels;
    }

    @ResponseBody
    @RequestMapping(value = "/shopSearch", method = RequestMethod.GET)
    public SjesPage<GateShop> shopListForSale(GateShopSearch gateShopSearch) {
        if (gateShopSearch.getAreaId() != null && gateShopSearch.getAreaId().compareTo("0") == 0){
            gateShopSearch.setAreaId(null);
        }
        gateShopSearch.setPage(gateShopSearch.getPage() - 1);
        return storeApiClient.getGateShopList(gateShopSearch);
    }
}
