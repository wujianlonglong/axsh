package anxian.gateway.admin.module.business.controller.anxian.order;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.order.OrderAdminApiClient;
import client.api.order.VerificationApiClient;
import client.api.order.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wangdinglan on 2017/10/30
 */
@Controller
@RequestMapping("/anxian/verifyOrder")
public class AnxianOrderVerifyController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderAdminApiClient orderAdminApiClient;
    @Autowired
    private VerificationApiClient verificationApiClient;

    @RequestMapping("/main")
    public String coupons(Principal principal, Model model){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        return "order/verify-order";
    }

    @RequestMapping(value = "/ajaxList", method = RequestMethod.POST)
    public String ajaxCoupons(Principal principal, Model model, VerficatingCondition verficatingCondition){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        if (verficatingCondition.getPayType() != null && verficatingCondition.getPayType() == 0) {
            verficatingCondition.setPayType(null);
        }
        if (verficatingCondition.getVerficationType() != null && verficatingCondition.getVerficationType() == 0) {
            verficatingCondition.setVerficationType(null);
        }
        if (!StringUtils.isEmpty(verficatingCondition.getPayStartStr())) {
          verficatingCondition.setPayStartDate(Date.valueOf(verficatingCondition.getPayStartStr()));
        }
        if (!StringUtils.isEmpty(verficatingCondition.getPayEndStr())) {
            verficatingCondition.setPayEndDate(Date.valueOf(verficatingCondition.getPayEndStr()));
        }
        SjesPage<Order> orders = orderAdminApiClient.verficatingOrder(verficatingCondition);
        model.addAttribute("totalPages", orders.getTotalPages());
        model.addAttribute("page", verficatingCondition.getPage() + 1);
        model.addAttribute("orders", orders);
        return "order/verify-order-ajax";
    }

    /**
     * 订单审核
     */
    @ResponseBody
    @RequestMapping(value = "/verficateOrders", method = RequestMethod.POST)
    public JsonMsg verficateOrder(Principal principal, @RequestBody VerficationView verficationView) {
        verficationView.setUserId(100001L);
        List<Long> orderIds = verficationView.getOrderIdsStr().stream().map(s->Long.valueOf(s)).collect(Collectors.toList());
        verficationView.setOrderIds(orderIds);
        OrderApiResponse<List<Order>> listOrderApiResponse = verificationApiClient.verficateOrder(verficationView);
        if (listOrderApiResponse.getReturn_code() == OrderConstant.successCode) {
            return JsonMsg.success("审核成功");
        }
        return JsonMsg.failure("审核失败");
    }
}
