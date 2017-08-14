package anxian.gateway.admin.module.business.controller.user;


import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.user.FeedbackApiClient;
import client.api.user.domain.Feedback;
import client.api.user.domain.FeedbackModel;
import client.api.user.utils.page.PageForAdmin;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaoqichao on 16-3-30.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private static final Logger log = LoggerFactory.getLogger(FeedbackController.class);

    private static final String EXCEL_PATH = "feedback_temp/";


    @Autowired
    private FeedbackApiClient feedbackApiClient;

    /**
     * 取得用户反馈信息列表
     *
     * @param mobile 手机号
     * @param status 处理状态
     * @param page   页码
     * @param size   每页展示的最大条目数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public PageForAdmin<Feedback> list(@RequestParam(name = "userId", required = false) Long userId,
                                       @RequestParam(name = "mobile", required = false) String mobile,
                                       @RequestParam(name = "status", required = false) String status,
                                       @RequestParam(name = "startDate", required = false) String startDate,
                                       @RequestParam(name = "endDate", required = false) String endDate,
                                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(name = "size", required = false, defaultValue = "20") int size) {
        if (StringUtils.isNotEmpty(startDate)) {
            startDate = startDate + " 00:00:00";
        }

        if (StringUtils.isNotEmpty(endDate)) {
            endDate = endDate + " 23:59:59";
        }

        PageForAdmin<Feedback> feedbackPage
                = feedbackApiClient.findByCondition(userId, mobile, status, startDate, endDate, page - 1, size);

        return feedbackPage;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/deal")
    public JsonMsg deal(Feedback feedback) {
        Feedback feedbackDB = feedbackApiClient.findById(feedback.getId());
        if (null == feedback) {
            return JsonMsg.failure("个人反馈ID不存在");
        }

        AclUser user = UserContextHelper.getUser();
        feedbackDB.setDealSuggestion(feedback.getDealSuggestion());
        feedbackDB.setDealDate(LocalDateTime.now());
        feedbackDB.setOperatorId(user.getId());
        feedbackDB.setOperatorName(user.getUsername());
        feedbackDB.setSolved(true);

        try {
            feedback = feedbackApiClient.save(feedbackDB);
        } catch (Exception e) {
            log.error("处理个人反馈意见发生异常!", e);

            return JsonMsg.failure("处理个人反馈意见发生异常");
        }
        return JsonMsg.success("处理成功!");
    }


    /**
     * 导出已审核订单列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> export(HttpServletRequest httpServletRequest,
                                         @RequestParam(name = "userId", required = false) Long userId,
                                         @RequestParam(name = "mobile", required = false) String mobile,
                                         @RequestParam(name = "status", required = false) String status,
                                         @RequestParam(name = "startDate", required = false) String startDate,
                                         @RequestParam(name = "endDate", required = false) String endDate,
                                         @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(name = "size", required = false, defaultValue = "10000")
                                                 int size) {
        if (StringUtils.isNotEmpty(startDate)) {
            startDate = startDate + " 00:00:00";
        }

        if (StringUtils.isNotEmpty(endDate)) {
            endDate = endDate + " 23:59:59";
        }


        PageForAdmin<Feedback> feedbackPage
                = feedbackApiClient.findByCondition(userId, mobile, status, startDate, endDate, page - 1, size);
        List<Feedback> content = feedbackPage.getList();
        ResponseEntity<byte[]> responseEntity = null;
        if (!CollectionUtils.isEmpty(content)) {
            Map<String, List<FeedbackModel>> beanParams = new HashMap<>();
            beanParams.put("list", this.covertFeedbackToModel(content));

            try {

                String fileName = "个人反馈.xls";

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
                httpHeaders.setContentDispositionFormData("attachment",
                        URLEncoder.encode(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + "_" +
                                fileName, "UTF-8"));

                XLSTransformer former = new XLSTransformer();
                InputStream inputStream = this.getClass().getResourceAsStream
                        ("/xls_template/feedbackList_export.xlsx");
                Workbook sheets = former.transformXLS(inputStream, beanParams);
                OutputStream outputStream = new FileOutputStream(exportFile);
                sheets.write(outputStream);

                responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(exportFile), httpHeaders,
                        HttpStatus.OK);

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
     * @param feedbackList
     * @return
     */
    private List<FeedbackModel> covertFeedbackToModel(List<Feedback> feedbackList) {
        List<FeedbackModel> feedbackModelList = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(feedbackList)) {
            feedbackList.stream()
                    .forEach(feedback -> {
                        FeedbackModel model = new FeedbackModel();
                        model.setUserId(feedback.getUserId());
                        model.setMobile(feedback.getContactInfo());
                        model.setOrderId(feedback.getOrderId());
                        model.setDescription(feedback.getDescription());
                        model.setCreatedDate(feedback.getCreatedDate().format(DateTimeFormatter.ofPattern
                                ("yyyy-MM-dd HH:mm:ss")));
                        model.setDealDate(feedback.getDealDate() == null
                                ? null
                                : feedback.getDealDate()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        model.setOperatorName(feedback.getOperatorName());
                        model.setIsSolved(feedback.isSolved() ? "已解决" : "未解决");
                        model.setDealSuggestion(feedback.getDealSuggestion());

                        feedbackModelList.add(model);
                    });
        }

        return feedbackModelList;
    }
}
