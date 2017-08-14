package anxian.gateway.admin.module.business.controller.order;

import anxian.gateway.admin.module.business.controller.order.model.OrderConstant;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.order.ScoreConvertNoteApiClient;
import client.api.order.model.ScoreConvertNote;
import client.api.order.model.SearchCondition;
import client.api.user.utils.page.SjesPage;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangyunan on 2017/8/2.
 */
@RestController
@RequestMapping("/scoreConvert")
public class ScoreConvertNoteController {

    @Autowired
    private ScoreConvertNoteApiClient scoreConvertNoteApiClient;

    /**
     * 积分兑换记录查询
     */
    @RequestMapping(method = RequestMethod.GET, value = "/searchScoreConvertNote")
    public PageModel<ScoreConvertNote> scoreConvertList(SearchCondition searchCondition, int page, int limit) {
        searchCondition.setPage(page - 1);
        searchCondition.setSize(limit);
        SjesPage<ScoreConvertNote> scoreConvertNotes = scoreConvertNoteApiClient.scoreConvertList(searchCondition);
        return new PageModel<>(scoreConvertNotes.getContent(), scoreConvertNotes.getTotalElements(), new Pageable(page, limit));
    }

    /**
     * 导出积分兑换列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/export")
    public ResponseEntity<byte[]> scoreOrderListExport(HttpServletRequest httpServletRequest, SearchCondition searchCondition) {
        List<ScoreConvertNote> scoreConvertNotes = scoreConvertNoteApiClient.exportscoreConvertList(searchCondition);

        ResponseEntity<byte[]> responseEntity = null;
        if (!CollectionUtils.isEmpty(scoreConvertNotes)) {

            Map<String, List<ScoreConvertNote>> beanParams = new HashMap<>();
            beanParams.put("scoreConvertNotes", scoreConvertNotes);

            try {

                String fileName = "积分兑换记录列表.csv";

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
                InputStream inputStream = this.getClass().getResourceAsStream("/xls_template/scoreConvertNotesList_export.xlsx");
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
}
