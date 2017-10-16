package anxian.gateway.admin.module.business.controller.anxian.order;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.order.PrintApiClient;
import client.api.order.model.Order;
import client.api.order.model.OrderApiResponse;
import client.api.order.model.PickingCondition;
import client.api.order.model.SearchType;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by wangdinglan on 2017/09/13
 */
@Controller
@RequestMapping("/anxian/resoluteOrder")
public class AnxianResoluteOrderController extends BaseController {
    @Autowired
    private PrintApiClient printApiClient;

    @Autowired
    private UserService userService;

    @RequestMapping("/main")
    public String coupons(Principal principal, Model model){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        return "order/nocomplete-order";
    }

    /**
     * 已拣货未完结订单列表
     */
    @RequestMapping(value = "/ajaxList", method = RequestMethod.POST)
    public String ajaxCoupons(Principal principal, Model model, PickingCondition pickingCondition){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        pickingCondition.setShopId(user.getShopId());
        pickingCondition.setSearchType(SearchType.completeGoods);
        SjesPage<Order> orderlistForSearch = printApiClient.treatsOrders(pickingCondition);
        model.addAttribute("totalPages", orderlistForSearch.getTotalPages());
        model.addAttribute("page", pickingCondition.getPage() + 1);
        model.addAttribute("orders", orderlistForSearch);
        return "order/nocomplete-order-ajax";
    }

    /**
     * 确认收货.
     */
    @ResponseBody
    @RequestMapping(value = "/receiveGoods/{id}", method = RequestMethod.POST)
    public JsonMsg receiveGoods(@PathVariable("id") Long id) {
        if (id != null) {
            OrderApiResponse orderApiResponse = printApiClient.confirmOrder(id);
            if (orderApiResponse != null && orderApiResponse.getReturn_code() == OrderConstant.successCode) {
                return JsonMsg.success(orderApiResponse.getReturn_msg());
            } else {
                if (orderApiResponse == null) {
                    return JsonMsg.failure("返回结果为空!");
                } else {
                    return JsonMsg.failure(orderApiResponse.getReturn_msg());
                }
            }
        } else {
            return JsonMsg.failure("订单ID为空!");
        }
    }

}
