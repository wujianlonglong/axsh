package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.module.business.controller.order.model.ResoluteOrderViewModel;
import anxian.gateway.admin.module.security.UserContext;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.order.OrderAdminApiClient;
import client.api.order.OrderApiClient;
import client.api.order.PrintApiClient;
import client.api.order.ResoluteOrderApiClient;
import client.api.order.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Jianghe on 16/2/5.
 */
@RestController
@RequestMapping("/resoluteOrder")
public class ResoluteOrderController extends BaseController {

    @Autowired
    private OrderAdminApiClient orderAdminApiClient;

    @Autowired
    private OrderApiClient orderApiClient;

    @Autowired
    private ResoluteOrderApiClient resoluteOrderApiClient;

    @Autowired
    private PrintApiClient printApiClient;

    @Value("${picture.upload.path}")
    private String pictureUploadPath;

    /**
     * 待拣货订单列表
     */
    @RequestMapping(value = "/resoluteOrderList", method = RequestMethod.GET)
    public SjesPage<Order> resoluteOrderList(PickingCondition pickingCondition, int page, int limit) {
        UserContext userContext = UserContextHelper.getUserContext();
        if (!userContext.isManager() && !userContext.isSupperManager()) {//不是超级管理员和系统管理员
            pickingCondition.setShopId(userContext.getUser().getOrg().getOrgNum());
        }
        if (StringUtils.isEmpty(pickingCondition.getOrderId())) {
            pickingCondition.setOrderId(null);
        }
        if (pickingCondition.getConsigneeType() == null || pickingCondition.getConsigneeType() == 0) {
            pickingCondition.setConsigneeType(null);
        }
        pickingCondition.setPage(page - 1);
        pickingCondition.setSize(limit);
        SjesPage<Order> orderlistForSearch = printApiClient.treatsOrders(pickingCondition);
        return orderlistForSearch;
    }


    /**
     * 打开订单拆分页面并且加载数据
     */
    @RequestMapping(value = "/resoluteOrderView/{id}", method = RequestMethod.GET)
    public JsonMsg getResoluteOrderView(@PathVariable("id") Long id) {
        ResoluteOrderViewModel orderViewModel = new ResoluteOrderViewModel();
        orderViewModel.setOrder(orderApiClient.findOrder(id));
        OrderApiResponse<List<OrderItemView>> resoluteOrderItems = resoluteOrderApiClient.getResoluteOrderItems(id);
        if (resoluteOrderItems.getReturn_code() == OrderConstant.successCode) {
            orderViewModel.setOrderItemViews(resoluteOrderItems.getData());
        }

        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(orderViewModel);
        return jsonMsg;
    }

    /**
     * 拆分订单
     *
     * @param resoluteOrderView 拆分订单对象
     */
    @RequestMapping(value = "/doResoluteOrder", method = RequestMethod.POST)
    public JsonMsg resoluteOrder(ResoluteOrderView resoluteOrderView) {
        AclUser user = UserContextHelper.getUserContext().getUser();
        resoluteOrderView.setUserId(user.getId());
        resoluteOrderView.setUsername(user.getFullName());

        OrderApiResponse<Order> orderOrderApiResponse = resoluteOrderApiClient.resoluteOrder(resoluteOrderView);
        if (orderOrderApiResponse.getReturn_code() == OrderConstant.successCode) {
            return JsonMsg.success("拆分成功");
        }
        return JsonMsg.failure("拆分失败");
    }

    /**
     * 已拣货未完结订单列表
     */
    @RequestMapping(value = "/hasNoFinPickGoodsOrderList", method = RequestMethod.GET)
    public SjesPage<Order> hasNoFinPickGoodsOrderList(PickingCondition pickingCondition, Integer congineeType, int page, int limit) {
        UserContext userContext = UserContextHelper.getUserContext();
        if (!userContext.isManager() && !userContext.isSupperManager()) {//不是超级管理员和系统管理员
            pickingCondition.setShopId(userContext.getUser().getOrg().getOrgNum());
        }
        if (congineeType == null || congineeType == 0) {
            congineeType = null;
        }
        if (StringUtils.isEmpty(pickingCondition.getOrderId())) {
            pickingCondition.setOrderId(null);
        }
        pickingCondition.setConsigneeType(congineeType);
        pickingCondition.setSearchType(SearchType.completeGoods);
        pickingCondition.setPage(page - 1);
        pickingCondition.setSize(limit);
        SjesPage<Order> orderlistForSearch = printApiClient.treatsOrders(pickingCondition);
        return orderlistForSearch;
    }

    /**
     * 订单明细
     */
    @RequestMapping(value = "/orderDetails/{id}", method = RequestMethod.GET)
    public JsonMsg picking_orderDetails(@PathVariable("id") Long id) {
        ResoluteOrderViewModel orderViewModel = new ResoluteOrderViewModel();
        orderViewModel.setOrder(orderApiClient.findOrder(id));
        orderViewModel.setOrderItems(orderAdminApiClient.getItemsByOrderId(id));
        orderViewModel.setOrderScoreItems(orderAdminApiClient.getScoreItemsByOrderId(id));
        orderViewModel.setOrderTracks(orderAdminApiClient.getOrderTrackList(id).getData());

        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(orderViewModel);
        return jsonMsg;
    }

    /**
     * 确认收货.
     */
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
