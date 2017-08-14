package client.api.pay;

import client.api.pay.domain.PayCondition;
import client.api.pay.domain.PayModel;
import client.api.pay.domain.RefundCondition;
import client.api.pay.domain.RefundModel;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by kimiyu on 16/4/18.
 */
@FeignClient("sjes-api-pay")
@RequestMapping(value = "/pays", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface PayApiClient {

    /**
     * 单笔退款
     *
     * @param orderId      订单ID
     * @param refundAmount 退款金额
     */
    @RequestMapping(value = "/nbcb/refund", method = RequestMethod.POST)
    boolean refund(@RequestParam("orderId") Long orderId,
                   @RequestParam("parentId") Long parentId,
                   @RequestParam("refundAmount") String refundAmount);


    /**
     * 在线支付列表.
     */
    @RequestMapping(value = "/outservice/payList", method = RequestMethod.POST)
    SjesPage<PayModel> payModelList(PayCondition payCondition);

    /**
     * 在线支付导出列表
     *
     * @param payCondition
     * @return
     */
    @RequestMapping(value = "/outservice/exportList", method = RequestMethod.POST)
    List<PayModel> exportPays(PayCondition payCondition);

    @RequestMapping(value = "/outservice/refundList", method = RequestMethod.POST)
    SjesPage<RefundModel> refundModelList(RefundCondition refundCondition);

    @RequestMapping(value = "/outservice/closeRefund/{id}", method = RequestMethod.POST)
    boolean closeRefundOrder(@PathVariable("id") Long id);


}
