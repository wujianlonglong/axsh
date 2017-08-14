package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import client.api.card.CardOrderApiClient;
import client.api.card.model.CardOrderAdmin;
import client.api.card.model.CardOrderAdminDTO;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.order.model.OrderApiResponse;
import client.api.user.utils.page.SjesPage;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimiyu on 16/12/20.
 */
@RestController
@RequestMapping(value = "/cardOrders")
public class CardOrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardOrderController.class);

    @Autowired
    private CardOrderApiClient cardOrderApiClient;

    /**
     * 会员订单查询列表
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/searchList", method = RequestMethod.GET)
    public PageModel<CardOrderAdmin> searchList(Long orderId, String startDateTime, String endDateTime, String cardNumber, String type, int page, int limit) {
        CardOrderAdminDTO cardOrderAdminDTO = new CardOrderAdminDTO();
        cardOrderAdminDTO.setPage(page);
        cardOrderAdminDTO.setSize(limit);
        cardOrderAdminDTO.setOrderId(orderId);
        cardOrderAdminDTO.setCardNumber(cardNumber);
        cardOrderAdminDTO.setType(type);
        if (!StringUtils.isEmpty(startDateTime) && !StringUtils.isEmpty(endDateTime)) {
            cardOrderAdminDTO.setStartDateTime(Date.valueOf(startDateTime));
            cardOrderAdminDTO.setEndDateTime(Date.valueOf(endDateTime));
        } else {
            cardOrderAdminDTO.setStartDateTime(null);
            cardOrderAdminDTO.setEndDateTime(null);
        }
        OrderApiResponse<SjesPage<CardOrderAdmin>> orderApiResponse = cardOrderApiClient.cardOrderAdmins(cardOrderAdminDTO);
        if (orderApiResponse.getReturn_code() == OrderConstant.failCode) {
            return new PageModel<>(null, 0, new Pageable(page, limit));
        }

        SjesPage<CardOrderAdmin> cardOrderAdminSjesPage = orderApiResponse.getData();

        if (null == cardOrderAdminSjesPage) {
            return new PageModel<>(new ArrayList<>(), 0, new Pageable(page, limit));
        }

        LOGGER.info("cardPages:{}", cardOrderAdminSjesPage.toString());

        return new PageModel<>(cardOrderAdminSjesPage.getContent(), cardOrderAdminSjesPage.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 导出会员订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> payListExport(HttpServletRequest httpServletRequest, CardOrderAdminDTO cardOrderAdminDTO) {
        LOGGER.info("传入的参数：{}", cardOrderAdminDTO.toString());
        OrderApiResponse<List<CardOrderAdmin>> orderApiResponse = cardOrderApiClient.exportList(cardOrderAdminDTO);

        ResponseEntity<byte[]> responseEntity = null;

        if (orderApiResponse.getReturn_code() == OrderConstant.successCode) {
            List<CardOrderAdmin> cardOrderAdmins = orderApiResponse.getData();
            if (!CollectionUtils.isEmpty(cardOrderAdmins)) {

                Map<String, List<CardOrderAdmin>> beanParams = new HashMap<>();
                beanParams.put("cardOrder", cardOrderAdmins);

                try {

                    String fileName = "会员卡订单列表.xls";

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
                    InputStream inputStream = this.getClass().getResourceAsStream("/xls_template/cardOrderList_export.xlsx");
                    Workbook sheets = former.transformXLS(inputStream, beanParams);
                    OutputStream outputStream = new FileOutputStream(exportFile);
                    sheets.write(outputStream);

                    responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders, HttpStatus.OK);

                    outputStream.close();
                    inputStream.close();
                } catch (IOException | InvalidFormatException e) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("会员卡订单导出异常：{}", ExceptionUtils.getMessage(e));
                    }
                    LOGGER.info("会员卡订单导出异常：{}", ExceptionUtils.getMessage(e));
                }
            }
        }
        return responseEntity;


    }


}
