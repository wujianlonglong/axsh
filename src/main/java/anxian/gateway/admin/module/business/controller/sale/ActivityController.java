package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.controller.sale.model.ActFloorProduct;
import anxian.gateway.admin.module.business.model.activity.ActivityViewModel;
import anxian.gateway.admin.utils.ActZoneEnum;
import anxian.gateway.admin.utils.AdminPage;
import anxian.gateway.admin.utils.ExcelUtil;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductFeign;
import client.api.item.domain.Product;
import client.api.sale.ActivityApiClient;
import client.api.sale.model.act.Activity;
import client.api.sale.util.ActivityParam;
import client.api.user.utils.page.SjesPage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoqichao on 16-1-15.
 */
@RestController
@RequestMapping(value = "/admin/act")
public class ActivityController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ActivityController.class);

    @Override
    protected void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private ActivityApiClient activityApiClient;

    @Autowired
    private ProductFeign productFeign;

    /**
     * 插入新增活动
     *
     * @param activity 活动对象
     * @return 插入数据库后的对象实例
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg insert(Activity activity, Principal principal) {
        try {
            String zoneId = activity.getZoneId();
            if (StringUtils.isNotEmpty(zoneId) &&
                    (zoneId.equals(ActZoneEnum.HUI_GOODS.zoneId())
                            || zoneId.equals(ActZoneEnum.SANGJIANG_CHOSED.zoneId())
                            || zoneId.equals(ActZoneEnum.CRAZY_WEND.zoneId()))) {
                Activity existActivity = activityApiClient.findByZoneId(zoneId);
                if (null != existActivity) {
                    zoneId = existActivity.getZoneId();
                    if (zoneId.equals(ActZoneEnum.HUI_GOODS.zoneId())) {
                        return JsonMsg.failure("惠商品活动已存在，不能再次添加！");
                    } else if (zoneId.equals(ActZoneEnum.SANGJIANG_CHOSED.zoneId())) {
                        return JsonMsg.failure("三江优选活动已存在，不能再次添加！");
                    } else {
                        return JsonMsg.failure("周三疯活动已存在，不能再次添加！");
                    }
                }
            }
            if (StringUtils.isBlank(activity.getId())) {
                activity.setId(null);
            }
            Activity insert = activityApiClient.insert(activity);
            if (insert != null) {
                log.info("用户{}创建了活动：{}", principal.getName(), activity.getActivityName());
                return JsonMsg.success("保存成功");
            }

        } catch (Exception e) {
            log.error("新增活动发生异常", e);
        }

        return JsonMsg.failure("保存失败");
    }


    /**
     * 更新活动对象
     *
     * @param activity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public JsonMsg save(Activity activity, Principal principal) {

        try {
            Activity saveActivity = activityApiClient.save(activity);
            if (saveActivity != null) {
                log.info("用户{}修改了活动：{}", principal.getName(), activity.getActivityName());
                return JsonMsg.success("修改成功");
            } else {
                return JsonMsg.failure("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("修改失败");
        }

    }

    /**
     * 根据主键取得活动
     *
     * @param id 活动对象
     * @return 插入数据库后的对象实例
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public JsonMsg findActivity(@PathVariable("id") String id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(activityApiClient.findActivity(id));
        return jsonMsg;
    }

    /**
     * 根据id删除对应活动
     *
     * @param id 活动id
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonMsg delete(@PathVariable("id") String id, Principal principal) {
        try {
            Activity activity = activityApiClient.findActivity(id);
            activityApiClient.delete(id);
            log.info("用户{}删除了活动：{}", principal.getName(), activity.getActivityName());

            return JsonMsg.success("删除成功！");
        } catch (Exception e) {
            log.error("删除活动失败", e);
        }

        return JsonMsg.failure("删除活动失败");
    }

    /**
     * 查询活动列表
     *
     * @param name      活动名称
     * @param status    状态
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param page      页码
     * @param size      每页展示的最大条目数
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pageList")
    public AdminPage pageList(@RequestParam(name = "activityName", required = false) String name,
                              @RequestParam(name = "status", required = false, defaultValue = "-1")
                                      int status,
                              @RequestParam(name = "startDate", required = false)
                                      String startDate,
                              @RequestParam(name = "endDate", required = false)
                                      String endDate,
                              @RequestParam(name = "page", required = false, defaultValue = "1")
                                      int page,
                              @RequestParam(name = "limit", required = false, defaultValue = "10")
                                      int size) {
        ActivityParam activityParam = new ActivityParam();
        activityParam.setActivityName(name);
        activityParam.setFlag(status);
        if (StringUtils.isNotEmpty(startDate)) {
            activityParam.setBeginTime(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (StringUtils.isNotEmpty(endDate)) {
            activityParam.setEndTime(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }

        SjesPage<ActivityViewModel> sjesPage = activityApiClient.pageGetActivityList(activityParam, page - 1, size);

        AdminPage adminPage = new AdminPage();
        if (null != sjesPage) {
            adminPage.setItems(sjesPage.getContent());
            adminPage.setTotal(sjesPage.getTotalElements());
        }

        return adminPage;
    }

    /**
     * 导入楼层商品
     *
     * @param response
     * @param multipartFile
     */
    @RequestMapping(method = RequestMethod.POST, value = "/floor/importProduct")
    public JsonMsg importFloorProdct(HttpServletResponse response,
                                     @RequestParam("snStrs") String snStrs,
                                     @RequestParam("file") MultipartFile multipartFile) {
        response.setHeader("X-Frame-Options", "SAMEORIGIN");//添加了文件上传后跨域问题解决办法
        XSSFWorkbook wb;
        try {
            wb = new XSSFWorkbook(multipartFile.getInputStream());
        } catch (IOException e) {
            wb = null;
            return JsonMsg.failure("导入失败!");
        }
        List<ActFloorProduct> list = new ArrayList<>();

        if (null != wb) {
            XSSFSheet sheet = wb.getSheetAt(0);
            if (null != sheet) {
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();

                List<String> snList = new ArrayList<>();
                if (StringUtils.isNotEmpty(snStrs) && snStrs.length() != 1) {
                    for (String sn : snStrs.substring(1).split(",")) {
                        if (!snList.contains(sn)) {
                            snList.add(sn);
                        }
                    }
                }
                String sn;
                for (int i = firstRowNum + 1; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (null == row) {
                        continue;
                    }
                    sn = ExcelUtil.getCellValue(row.getCell(0)).trim();
                    if (StringUtils.isNotEmpty(sn) && !snList.contains(sn)) {
                        snList.add(sn);
                    }
                }
                if (CollectionUtils.isNotEmpty(snList)) {
                    List<Product> productList = productFeign.findBySnIn(snList);
                    if (CollectionUtils.isNotEmpty(productList)) {
                        productList.stream()
                                .forEach(product -> {
                                    int status = product.getStatus() == null ? -1 : product.getStatus();
                                    if (status == 0) {
                                        ActFloorProduct actFloorProduct = new ActFloorProduct();
                                        actFloorProduct.setSn(product.getSn());
                                        actFloorProduct.setName(product.getName());
                                        actFloorProduct.setStatus(status);

                                        list.add(actFloorProduct);
                                    }
                                });
                    }
                }
            }
        }
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(list);
        jsonMsg.setSuccess(true);
        jsonMsg.setMsg("success");
        return jsonMsg;
    }
}
