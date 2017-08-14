package client.api.order;

import client.api.order.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单后台接口
 * Created by kimiyu on 16/1/11.
 */
@FeignClient("sjes-api-order")
@RequestMapping(value = "/adminOrders", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface OrderAdminApiClient {

    /**
     * 后台订单模块的订单查询功能点
     *
     * @param searchCondition
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/searchOrder")
    SjesPage<Order> getOrderlistForSearch(SearchCondition searchCondition);

    /**
     * 根据订单号查询商品明细
     *
     * @param orderId 订单编号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/saleclick/{orderId}")
    List<OrderItem> getItemsByOrderId(@PathVariable("orderId") Long orderId);

    /**
     * 根据订单号查询优惠明细
     *
     * @param orderId 订单编号
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/benefitclick/{orderId}")
    List<OrderItem> getBenefitItemsByOrderId(@PathVariable("orderId") Long orderId);

    /**
     * 待审核订单
     *
     * @param verficatingCondition 审核条件
     * @return 返回待审核订单列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/verficatingOrder")
    SjesPage<Order> verficatingOrder(VerficatingCondition verficatingCondition);

    /**
     * 永耀店支付待审核
     *
     * @param verficatingCondition 审核条件
     * @return 返回待审核订单列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/verficatingOrderForYongYao")
    SjesPage<Order> yongYaoVerficatingOrder(@RequestBody VerficatingCondition verficatingCondition);

    /**
     * 已审核订单
     *
     * @param verficationCondition 审核条件
     * @return 返回已审核订单列表
     */
    @RequestMapping(method = RequestMethod.POST, value = "/verficatedOrder")
    SjesPage<Order> verficatedOrder(VerficationCondition verficationCondition);

    /**
     * 根据条件获取要导出的列表对象
     *
     * @param searchCondition 查询模型
     * @return
     */
    @PreAuthorize("hasAuthority('ORDERSEARCH_EXPORT')")
    @RequestMapping(method = RequestMethod.POST, value = "/searchOrder/export")
    List<ExportViewModel> exportOrders(SearchCondition searchCondition);

    /**
     * 根据条件显示异常订单的列表(接口超时处理失败或接口报错等的订单)
     *
     * @param exceptionCondtion 查询条件对象
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/exceptionOrder")
    SjesPage<Order> exceptionOrders(ExceptionCondtion exceptionCondtion);

    /**
     * 保存自动审核信息
     *
     * @param autoVerification
     * @return
     */
    @PreAuthorize("hasAuthority('AUTOAUDITMECHANISM_SAVE')")
    @RequestMapping(method = RequestMethod.POST, value = "/saveAutoVerification")
    AutoVerification saveOrUpdate(AutoVerification autoVerification);


    /**
     * 根据ID获取自动审核信息
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerification/{id}")
    AutoVerification findById(@PathVariable("id") String id);

    /**
     * 获取自动审核记录
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerfication/new")
    AutoVerification findOne();

    /**
     * 根据规则类型获取信息
     *
     * @param rule 审核规则
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerification")
    AutoVerification findByRule(@RequestParam("rule") Integer rule);

    /**
     * 根据订单编号查询物流信息
     *
     * @param orderId 订单ID
     */
    @RequestMapping(value = "/gettrack/{orderId}", method = RequestMethod.GET)
    OrderApiResponse<List<TrackModel>> getOrderTrackList(@PathVariable("orderId") Long orderId);

    /**
     * 根据订单编号查询支付明细
     *
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/payDetail/{orderId}", method = RequestMethod.GET)
    OrderApiResponse<PayDetail> getPayDetail(@PathVariable("orderId") Long orderId);


    /**
     * 待取消订单列表[客服取消].
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cancelOrder")
    SjesPage<Order> cancelOrder(CancelCondition cancelCondition);

    /**
     * 根据订单号查询积分商品明细
     *
     * @param orderId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/scoreItem/{orderId}")
    List<OrderScoreItem> getScoreItemsByOrderId(@PathVariable("orderId") Long orderId);
}
