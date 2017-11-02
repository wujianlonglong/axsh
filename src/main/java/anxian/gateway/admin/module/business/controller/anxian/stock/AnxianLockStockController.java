package anxian.gateway.admin.module.business.controller.anxian.stock;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import client.api.item.model.Pageable;
import client.api.order.model.Order;
import client.api.stock.StockApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by wangdinglan on 2017/10/28
 */
@Controller
@RequestMapping("anxian/stock")
public class AnxianLockStockController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private StockApiClient stockApiClient;

    //返回库存锁定或库存管理列表
    @RequestMapping(method = RequestMethod.GET, value = "/stockSyncShow")
    public String stockSyncShow(Model model, Principal principal){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        Map<String, Object> result = stockApiClient.stockSyncShow(user.getShopId());
        model.addAttribute("stockList", result.get("stockList"));
        model.addAttribute("page", Integer.parseInt(result.get("pageNum").toString()) + 1);
        model.addAttribute("pageSize", Integer.parseInt(result.get("pageSize").toString()));
        model.addAttribute("totalPages", Integer.parseInt(result.get("totalPage").toString()));
        return "stock/sync-lockStock-init";

    }

    //查询库存锁定或库存管理
    @RequestMapping(method = RequestMethod.POST, value = "/stockSyncAjax")
    public String syncStock(Model model, String sjShopCode, String sjGoodsCode, String sjGoodsName,
                            int page,int size, String minNum, String maxNum,
                            String lockMinNum, String lockMaxNum, Principal principal){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        Map<String, Object> result = stockApiClient.syncStock(sjShopCode, sjGoodsCode, sjGoodsName, page,
                 size,minNum, maxNum, lockMinNum, lockMaxNum, user.getShopId());
        model.addAttribute("totalPages", Integer.parseInt(result.get("totalPage").toString()));
        model.addAttribute("page", page + 1);
        model.addAttribute("pageSize", Integer.parseInt(result.get("pageSize").toString()));
        model.addAttribute("stockList", result.get("stockList"));
        return "stock/sync-lockStock-ajax";

    }


    //修改库存锁定
    @RequestMapping(method = RequestMethod.POST, value = "/modifyLockStock")
    @ResponseBody
    public String modifyLockStock(Model model, Principal principal, String numVir, String id, String shopCode, String goodsCode){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        String result = stockApiClient.modifyLockStock(user.getUsername(), numVir, id, shopCode, goodsCode);
        return result;
    }



}
