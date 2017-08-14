package anxian.gateway.admin.module.business.controller.track;

import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import client.api.order.OrderApiClient;
import client.api.order.model.Order;
import client.api.order.model.PayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 门店控制类
 * Created by kimiyu on 16/1/11.
 */
@RestController
//@RequestMapping(value = "/gateshops")
public class DadaTrackController {

    @Autowired
    private OrderApiClient orderApiClient;

    @Autowired
    private DadaTrackBusiness dadaTrackBusiness;

    /**
     * @param orderId 订单Id
     * @param flag    "firstSend","retrySend"
     * @return
     */
    @RequestMapping(value = "/sendDadaDeliveryRequest", method = RequestMethod.POST)
    @ResponseBody
    public String sendDadaDeliveryRequest(String orderId, String flag) {
        Order order = orderApiClient.findOrder(Long.valueOf(orderId));
        String vipCustomer = order.getVipCustomer();
        String msg = null;
        if (order.getConsigneeType() != OrderConstant.shopPicking
                && order.getConsigneeType() != OrderConstant.QUICK_MEAL
                && (StringUtils.isEmpty(vipCustomer) && order.getPayType() == PayType.onlinePayment)) {
            msg = dadaTrackBusiness.sendToDada(Long.valueOf(orderId), flag);
        } else {
            msg = "不符合发送请求条件";
        }
        return msg;
    }

}
