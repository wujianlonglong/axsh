package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.controller.order.model.LogisticsTracking;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.module.business.controller.order.model.PayAmount;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.order.CancelOrderApiClient;
import client.api.order.OrderAdminApiClient;
import client.api.order.VerificationApiClient;
import client.api.order.model.*;
import client.api.user.utils.page.SjesPage;
import com.google.common.collect.Lists;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jianghe on 16/1/19.
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderAdminApiClient orderAdminApiClient;

    @Autowired
    private VerificationApiClient verificationApiClient;

    @Autowired
    private CancelOrderApiClient cancelOrderApiClient;

    /**
     * 保存自动审核信息
     */
    @RequestMapping(method = RequestMethod.POST, value = "/saveAutoVerification")
    public JsonMsg saveOrUpdate(AutoVerification autoVerification) {
        String id = autoVerification.getId();
        AclUser user = UserContextHelper.getUser();
        if (user != null) {
            if (StringUtils.isEmpty(id)) {
                autoVerification.setId(null);
                autoVerification.setCreatedBy(user.getId());
                autoVerification.setCreatedDate(LocalDateTime.now());
            } else {
                autoVerification.setUpdatedBy(user.getId());
                autoVerification.setUpdatedDate(LocalDateTime.now());
            }
        }

        AutoVerification autoVerification1 = orderAdminApiClient.saveOrUpdate(autoVerification);
        if (autoVerification1 != null) {
            return JsonMsg.success("保存成功");
        }
        return JsonMsg.failure("保存失败");
    }

    /**
     * 获取自动审核信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerification/{id}")
    public JsonMsg findById(@PathVariable("id") String id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(orderAdminApiClient.findById(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 获取自动审核记录
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerfication/new")
    public JsonMsg findOne() {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(orderAdminApiClient.findOne());
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 根据规则类型获取信息
     *
     * @param rule 审核规则
     */
    @RequestMapping(method = RequestMethod.GET, value = "/autoVerification")
    public JsonMsg findByRule(Integer rule) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(orderAdminApiClient.findByRule(rule));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }


    /**
     * 后台订单模块的订单查询功能点
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchOrder")
    public PageModel<Order> getOrderlistForSearch(SearchCondition searchCondition, int page, int limit) {
        searchCondition.setPage(page - 1);
        searchCondition.setSize(limit);
        SjesPage<Order> orderlistForSearch = orderAdminApiClient.getOrderlistForSearch(searchCondition);
        return new PageModel<>(orderlistForSearch.getContent(), orderlistForSearch.getTotalElements(), new Pageable(page, limit));
    }


    /**
     * 根据订单号查询商品明细
     *
     * @param orderId 订单编号
     */
    @RequestMapping(method = RequestMethod.GET, value = "/saleclick/{orderId}")
    public Map getItemsByOrderId(@PathVariable("orderId") Long orderId) {
        List<OrderItem> itemsByOrder = orderAdminApiClient.getItemsByOrderId(orderId);
        Map itemsResult = new HashMap<>();
        itemsResult.put("items", itemsByOrder);
        itemsResult.put("total", itemsByOrder.size());
        return itemsResult;
    }

    /**
     * 根据订单号查询优惠明细
     *
     * @param orderId 订单编号
     */
    @RequestMapping(method = RequestMethod.GET, value = "/benefitclick/{orderId}")
    public Map getBenefitItemsByOrderId(@PathVariable("orderId") Long orderId) {
        List<OrderItem> benefitItemsByOrder = orderAdminApiClient.getBenefitItemsByOrderId(orderId);
        Map benefitItemsResult = new HashMap<>();
        benefitItemsResult.put("items", benefitItemsByOrder);
        benefitItemsResult.put("total", benefitItemsByOrder.size());
        return benefitItemsResult;
    }

    /**
     * 导出订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> orderListExport(HttpServletRequest httpServletRequest, SearchCondition searchCondition) {
        List<ExportViewModel> exportViewModels = orderAdminApiClient.exportOrders(searchCondition);

        ResponseEntity<byte[]> responseEntity = null;
        if (!CollectionUtils.isEmpty(exportViewModels)) {

            Map<String, List<ExportViewModel>> beanParams = new HashMap<>();
            beanParams.put("orders", exportViewModels);

            try {

                String fileName = "订单列表.csv";

                String destFilePath = OrderConstant.ORDER_PATH + fileName;

                ServletContext servletContext = httpServletRequest.getSession(Boolean.TRUE).getServletContext();
                File exportFile = new File(servletContext.getRealPath(destFilePath));
                if (!exportFile.getParentFile().exists()) {
                    exportFile.getParentFile().mkdirs();
                }

                // 判断文件是否存在
                if (exportFile.exists()) {
                    exportFile.delete();
                }
                exportFile.createNewFile();

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode(System.currentTimeMillis() + fileName, "UTF-8"));

                XLSTransformer former = new XLSTransformer();
                InputStream inputStream = this.getClass().getResourceAsStream("/xls_template/orderList_export.xlsx");
                Workbook sheets = former.transformXLS(inputStream, beanParams);
                OutputStream outputStream = new FileOutputStream(exportFile);
                sheets.write(outputStream);

                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders, HttpStatus.OK);

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

    /**
     * 导出已审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/alreadyExamineExport")
    public ResponseEntity<byte[]> alreadyExamineExport(HttpServletRequest httpServletRequest, VerficationCondition verficationCondition) {
        verficationCondition.setPage(0);
        verficationCondition.setSize(1000);
        List<Order> content = orderAdminApiClient.verficatedOrder(verficationCondition).getContent();
        ResponseEntity<byte[]> responseEntity = null;
        if (!CollectionUtils.isEmpty(content)) {
            Map<String, List<Order>> beanParams = new HashMap<>();
            beanParams.put("orders", content);

            try {

                String fileName = "已审核订单.csv";

                String destFilePath = OrderConstant.ORDER_PATH + fileName;

                ServletContext servletContext = httpServletRequest.getSession(Boolean.TRUE).getServletContext();
                File exportFile = new File(servletContext.getRealPath(destFilePath));
                if (!exportFile.getParentFile().exists()) {
                    exportFile.getParentFile().mkdirs();
                }

                // 判断文件是否存在
                if (exportFile.exists()) {
                    exportFile.delete();
                }
                exportFile.createNewFile();

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentDispositionFormData("attachment", URLEncoder.encode(System.currentTimeMillis() + fileName, "UTF-8"));

                XLSTransformer former = new XLSTransformer();
                InputStream inputStream = this.getClass().getResourceAsStream("/xls_template/order_alreadyExamine_export.xlsx");
                Workbook sheets = former.transformXLS(inputStream, beanParams);
                OutputStream outputStream = new FileOutputStream(exportFile);
                sheets.write(outputStream);

                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders, HttpStatus.OK);

                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

        }
        return responseEntity;
    }

    /**
     * 待审核订单
     *
     * @param verficatingCondition 审核条件
     * @return 返回待审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/verficatingOrder")
    public PageModel<Order> verficatingOrder(VerficatingCondition verficatingCondition, int page, int limit) {
        verficatingCondition.setPage(page - 1);
        verficatingCondition.setSize(limit);
        if (verficatingCondition.getPayType() != null && verficatingCondition.getPayType() == 0) {
            verficatingCondition.setPayType(null);
        }
        if (verficatingCondition.getVerficationType() != null && verficatingCondition.getVerficationType() == 0) {
            verficatingCondition.setVerficationType(null);
        }
        SjesPage<Order> orders = orderAdminApiClient.verficatingOrder(verficatingCondition);
        return new PageModel<>(orders.getContent(), orders.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 待审核订单
     *
     * @param verficatingCondition 审核条件
     * @return 返回待审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/verficatingYongYaoOrder")
    public PageModel<Order> verficatingYongYaoOrder(VerficatingCondition verficatingCondition, int page, int limit) {
        verficatingCondition.setPage(page - 1);
        verficatingCondition.setSize(limit);
        if (verficatingCondition.getVerficationType() != null && verficatingCondition.getVerficationType() == 0) {
            verficatingCondition.setVerficationType(null);
        }
        SjesPage<Order> orders = orderAdminApiClient.yongYaoVerficatingOrder(verficatingCondition);
        return new PageModel<>(orders.getContent(), orders.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 已审核订单
     *
     * @param verficationCondition 审核条件
     * @return 返回已审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/verficatedOrder")
    public PageModel<Order> verficatedOrder(VerficationCondition verficationCondition, int page, int limit) {
        verficationCondition.setPage(page - 1);
        verficationCondition.setSize(limit);
        SjesPage<Order> orders = orderAdminApiClient.verficatedOrder(verficationCondition);
        return new PageModel<>(orders.getContent(), orders.getTotalElements(), new Pageable(page, limit));
    }


    /**
     * 已审核订单
     *
     * @param verficationCondition 审核条件
     * @return 返回已审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/verficatedYongYaoOrder")
    public PageModel<Order> verficatedYongYaoOrder(VerficationCondition verficationCondition, int page, int limit) {
        verficationCondition.setPayType(3);
        verficationCondition.setPage(page - 1);
        verficationCondition.setSize(limit);
        SjesPage<Order> orders = orderAdminApiClient.verficatedOrder(verficationCondition);
        return new PageModel<>(orders.getContent(), orders.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 订单审核
     */
    @RequestMapping(value = "/verficateOrders", method = RequestMethod.POST)
    public JsonMsg verficateOrder(VerficationView verficationView) {
        AclUser user = UserContextHelper.getUser();
        verficationView.setUserId(user.getId());
        OrderApiResponse<List<Order>> listOrderApiResponse = verificationApiClient.verficateOrder(verficationView);
        if (listOrderApiResponse.getReturn_code() == OrderConstant.successCode) {
            return JsonMsg.success("审核成功");
        }
        return JsonMsg.failure("审核失败");
    }

    /**
     * 订单取消列表.
     */
    @RequestMapping(value = "/cancelingOrder", method = RequestMethod.GET)
    public PageModel<Order> cancelOrderList(CancelCondition cancelCondition, int page, int limit) {
        cancelCondition.setPage(page - 1);
        cancelCondition.setSize(limit);
        SjesPage<Order> orders = orderAdminApiClient.cancelOrder(cancelCondition);
        return new PageModel<>(orders.getContent(), orders.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 订单取消.
     */
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public JsonMsg cancelOrder(CancelOrderView cancelOrderView) {
        AclUser user = UserContextHelper.getUser();
        cancelOrderView.setUserId(user.getId());
        cancelOrderView.setCustomer(user.getFullName());
        OrderApiResponse<List<Order>> listOrderApiResponse = cancelOrderApiClient.cancelOrder(cancelOrderView);
        if (listOrderApiResponse.getReturn_code().equals(OrderConstant.successCode)) {
            return JsonMsg.success("订单取消成功!");
        } else {
            return JsonMsg.failure(listOrderApiResponse.getReturn_msg());
        }
    }


    /**
     * 根据订单编号查询支付明细
     */
    @RequestMapping(value = "/payDetail/{orderId}", method = RequestMethod.GET)
    public Map getPayDetail(@PathVariable("orderId") Long orderId) {
        List<PayAmount> payAmountList = Lists.newArrayList();

        OrderApiResponse<PayDetail> payDetail = orderAdminApiClient.getPayDetail(orderId);
        if (payDetail.getReturn_code() == OrderConstant.successCode) {
            PayDetail data = payDetail.getData();

            PayAmount payType = new PayAmount();
            payType.setKey("支付类型");
            payType.setValue(data.getPayType());
            payAmountList.add(payType);

            PayAmount paySource = new PayAmount();
            paySource.setKey("支付来源");
            paySource.setValue(data.getPaySource());
            payAmountList.add(paySource);

            PayAmount platForm = new PayAmount();
            platForm.setKey("支付平台");
            platForm.setValue(data.getPlatForm());
            payAmountList.add(platForm);

            PayAmount actualAmount = new PayAmount();
            actualAmount.setKey("支付金额");
            actualAmount.setValue(String.valueOf(data.getActualAmount()));
            payAmountList.add(actualAmount);

            if (!CollectionUtils.isEmpty(data.getVolumeViews())) {
                for (VolumeView volumeView : data.getVolumeViews()) {
                    PayAmount volumeType = new PayAmount();
                    volumeType.setKey("优惠券类型");
                    volumeType.setValue(volumeView.getVolumeType());
                    payAmountList.add(volumeType);

                    PayAmount volumeMondy = new PayAmount();
                    volumeMondy.setKey("优惠劵金额");
                    volumeMondy.setValue(String.valueOf(volumeView.getVolumeMondy()));
                    payAmountList.add(volumeMondy);

                    PayAmount volumeNumber = new PayAmount();
                    volumeNumber.setKey("优惠券编号");
                    volumeNumber.setValue(volumeView.getVolumeNumber());
                    payAmountList.add(volumeNumber);
                }
            }

        }


        Map map = new HashMap<>();
        map.put("kids", payAmountList);

        return map;
    }


    /**
     * 根据订单编号查询物流信息
     *
     * @param orderId 订单ID
     */
    @RequestMapping(value = "/gettrack/{orderId}", method = RequestMethod.GET)
    public JsonMsg getOrderTrackList(@PathVariable("orderId") Long orderId) {
        OrderApiResponse<List<TrackModel>> orderTrackList = orderAdminApiClient.getOrderTrackList(orderId);
        JsonMsg jsonMsg = new JsonMsg();

        if (orderTrackList.getReturn_code() == OrderConstant.successCode) {
            LogisticsTracking logisticsTracking = new LogisticsTracking();//物流跟踪信息
            logisticsTracking.setOrderTracks(orderTrackList.getData());
            jsonMsg.setSuccess(true);
            jsonMsg.setData(logisticsTracking);
        }

        return jsonMsg;
    }
}

