package anxian.gateway.admin.module.business.controller.pay;

import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.pay.PayApiClient;
import client.api.pay.domain.*;
import client.api.user.utils.page.SjesPage;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimiyu on 16/4/18.
 */
@RestController
@RequestMapping(value = "/anxian/pays")
public class PayController {

    @Autowired
    private PayApiClient payApiClient;

    /**
     * 在线支付结果查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/onlinePayList")
    public PageModel<PayModel> getOrderlistForSearch(String platformId,Long orderId, String startDateTime, String endDateTime, String payMethod, Integer platForm, int page, int limit) {
        PayCondition payCondition = new PayCondition();
        payCondition.setPage(page);
        payCondition.setSize(limit);
        payCondition.setOrderId(orderId);
        if(!StringUtils.isEmpty(platformId)){
            payCondition.setPlatformId(platformId);
        }
        if (!StringUtils.isEmpty(startDateTime) && !StringUtils.isEmpty(endDateTime)) {
            payCondition.setStartDateTime(Date.valueOf(startDateTime));
            payCondition.setEndDateTime(Date.valueOf(endDateTime));
        } else {
            payCondition.setStartDateTime(null);
            payCondition.setEndDateTime(null);
        }
        if (!StringUtils.isEmpty(payMethod)) {
            if (payMethod.equals("1")) {
                payMethod = "callback";
            } else {
                payMethod = "refund";
            }
        }
        payCondition.setPayMethod(payMethod);
        payCondition.setPlatForm(platForm);
        SjesPage<PayModel> payModels = payApiClient.payModelList(payCondition);
        if (payModels != null) {
            return new PageModel<>(payModels.getContent(), payModels.getTotalElements(), new Pageable(page, limit));
        } else {
            return null;
        }
    }

    /**
     * 导出支付列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> payListExport(HttpServletRequest httpServletRequest, PayCondition payCondition) {
        List<PayModel> exportViewModels = payApiClient.exportPays(payCondition);

        ResponseEntity<byte[]> responseEntity = null;
        if (!CollectionUtils.isEmpty(exportViewModels)) {
            List<PayModelView> payModelViews = new ArrayList<>();
            exportViewModels.forEach(payModel -> {
                PayModelView payModelView = new PayModelView();
                payModelView.setBankName(payModel.getBankName());
                payModelView.setPlatformId(payModel.getPlatformId());
                payModelView.setShopId(payModel.getShopId());
                payModelView.setActualAmount(payModel.getActualAmount());
                payModelView.setOrderId(payModel.getOrderId());
                payModelView.setPayDate(payModel.getPayDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                String platForm = "";
                switch (payModel.getPlatForm()) {
                    case 105:
                        platForm = "WECHAT网站";
                        break;
                    case 110:
                        platForm = "ALIPAY移动端";
                        break;
                    case 130:
                        platForm = "WECHAT移动端";
                        break;
                    case 135:
                        platForm = "ALIPAY网站";
                        break;
                    case 115:
                        platForm = "DSY";
                        break;
                    default:
                        break;
                }
                payModelView.setPlatForm(platForm);
                String payMethod = "";
                switch (payModel.getPayMethod()) {
                    case "callback":
                    case "syncCallBack":
                        payMethod = "支付";
                        break;
                    case "refundCallback":
                    case "refund":
                        payMethod = "退款";
                        break;
                }
                payModelView.setPayMethod(payMethod);
                payModelViews.add(payModelView);
            });
            Map<String, List<PayModelView>> beanParams = new HashMap<>();
            beanParams.put("pays", payModelViews);

            try {

                String fileName = "在线支付列表.xls";

                String destFilePath = OrderConstant.PAY_PATH + fileName;

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
                InputStream inputStream = this.getClass().getResourceAsStream("/xls_template/payList_export.xlsx");
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
     * 退款结果查询.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/refundList")
    public PageModel<RefundModel> refundModelList(Long orderId, String startDateTime, String endDateTime, int page, int limit) {
        RefundCondition refundCondition = new RefundCondition();
        refundCondition.setPage(page);
        refundCondition.setSize(limit);
        refundCondition.setOrderId(orderId);
        if (!StringUtils.isEmpty(startDateTime) && !StringUtils.isEmpty(endDateTime)) {
            refundCondition.setStartDateTime(Date.valueOf(startDateTime));
            refundCondition.setEndDateTime(Date.valueOf(endDateTime));
        } else {
            refundCondition.setStartDateTime(null);
            refundCondition.setEndDateTime(null);
        }
        SjesPage<RefundModel> refundModels = payApiClient.refundModelList(refundCondition);
        if (refundModels != null) {
            return new PageModel<>(refundModels.getContent(), refundModels.getTotalElements(), new Pageable(page, limit));
        } else {
            return null;
        }
    }

    /**
     * 关闭已退款的订单.
     */
    @RequestMapping(method = RequestMethod.POST, value = "/alipay/refund")
    public JsonMsg findByRule(Long id) {
        boolean refundResult = payApiClient.closeRefundOrder(id);
        if (refundResult) {
            return JsonMsg.success("订单处理成功!");
        } else {
            return JsonMsg.failure("订单处理失败!");
        }
    }
}
