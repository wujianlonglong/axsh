package anxian.gateway.admin.module.business.controller.sale;


import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;


import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.model.promotionSycn.PromotionSycnView;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.sale.PromotionSycnApiClient;
import client.api.sale.model.*;
import client.api.sale.util.ErpSaleDTO;
import client.api.user.utils.page.SjesPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by byinbo on 2016/12/21.
 */
@Controller
@RequestMapping(value = "/admin/promotionSycn")
public class PromotionSycnController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(PromotionSycnController.class);

    @Override
    protected void initBinder(WebDataBinder binder) {
        super.initBinder(binder);
    }

    @Autowired
    private PromotionSycnApiClient promotionSycnApiClient;

    @Autowired
    private UserService userService;

    /**
     * 查询促销同步列表
     *
     * @param promotionName 促销名称
     * @param promotionType 促销类型
     * @param syncStatus    促销状态
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/pageList")
    @ResponseBody
    public SjesPage<PromotionSycnView> pageList(@RequestParam(name = "promotionName", required = false) String promotionName,
                                                @RequestParam(name = "promotionType", required = false) String promotionType,
                                                @RequestParam(name = "status", required = false) Integer status,
                                                @RequestParam(name = "syncStatus", required = false) Integer syncStatus,
                                                @RequestParam(name = "startDate", required = false) String startDate,
                                                @RequestParam(name = "endDate", required = false) String endDate,
                                                @RequestParam(name = "page", required = false, defaultValue = "1")
                                                        int page,
                                                @RequestParam(name = "limit", required = false, defaultValue = "10")
                                                        int size) {
        ErpSaleDTO erpSaleDTO = new ErpSaleDTO();
        erpSaleDTO.setSaleName(promotionName);
        erpSaleDTO.setSaleType(promotionType);
        erpSaleDTO.setStatus(status);
        erpSaleDTO.setSyncStatus(syncStatus);
        if (StringUtils.isNotEmpty(startDate)) {
            erpSaleDTO.setStartDate(LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (StringUtils.isNotEmpty(endDate)) {
            erpSaleDTO.setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        erpSaleDTO.setPage(page - 1);
        erpSaleDTO.setSize(size);
        SjesPage<PromotionSycnView> sjesPage = promotionSycnApiClient.getPages(erpSaleDTO);
        return sjesPage;
    }

    /**
     * 根据id获取ERP促销信息
     *
     * @param id 主键
     * @return 返回优ERP促销信息
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    @ResponseBody
    public JsonMsg getById(@PathVariable("id") String id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        ResponseMessage<PromotionSycnViewModel> promotionSycnViewModel = promotionSycnApiClient.getById(id);
        jsonMsg.setData(promotionSycnViewModel.getData());
        return jsonMsg;
    }

    @RequestMapping(method = RequestMethod.GET, value = "judge/{id}")
    @ResponseBody
    public ResponseMessage<PromotionSycnViewModel> judge(@PathVariable("id") String id) {
         ResponseMessage<PromotionSycnViewModel> promotionSycnViewModel = promotionSycnApiClient.getById(id);
        return promotionSycnViewModel;
    }

    /**
     * 同步促销
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg updateBenefitVolume(@RequestBody PromotionSyncModel promotionSyncModel) {
        ResponseMessage<PromotionSync> promotionSycnResponseMessage = promotionSycnApiClient.savePromotionSycn(promotionSyncModel);
        if (promotionSycnResponseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("同步成功");
        } else {
            return JsonMsg.failure(promotionSycnResponseMessage.getCodeMessage());
        }

    }

    /**
     * 删除促销
     */
    @RequestMapping(value = "/delete/{sheetId}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg delBySheetId(@PathVariable("sheetId") String sheetId) {
        ResponseMessage responseMessage = promotionSycnApiClient.delBySheetId(sheetId);
        if (responseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("删除成功");
        } else {
            return JsonMsg.failure("删除失败");
        }

    }

    @RequestMapping(value="/turnToSyncEdit/{id}")
    public String turnToSyncEdit(@PathVariable("id") String id, Model model, Principal principal){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);

        model.addAttribute("id",id);
        return "anXian-promotion/edit-sync";
    }
}
