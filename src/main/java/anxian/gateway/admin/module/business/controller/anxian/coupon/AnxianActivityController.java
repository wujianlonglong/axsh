package anxian.gateway.admin.module.business.controller.anxian.coupon;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import client.api.anxian.activity.AnxianActivityApiClient;
import client.api.anxian.activity.model.ActivityCondition;
import client.api.anxian.activity.model.AnxianActivity;
import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import client.api.sale.model.*;
import client.api.sale.model.ResponseMessage;
import client.api.search.AnxianSearchApiClient;
import client.api.track.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by wangdinglan on 2017/10/16
 */
@Controller
@RequestMapping("/anxian")
public class AnxianActivityController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private AnxianActivityApiClient anxianActivityApiClient;

    @Autowired
    private AnxianSearchApiClient anxianSearchApiClient;

    @RequestMapping("/activities")
    public String activities(Principal principal, Model model){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        return "anXian-promotion/activity";
    }

    @RequestMapping(value = "/ajaxActivities", method = RequestMethod.POST)
    public String ajaxActivities(Principal principal, Model model, ActivityCondition activityCondition){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        if (!StringUtils.isEmpty(activityCondition.getStartDateStr())) {
            activityCondition.setStartDate(LocalDate.parse(activityCondition.getStartDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        if (!StringUtils.isEmpty(activityCondition.getEndDateStr())) {
            activityCondition.setEndDate(LocalDate.parse(activityCondition.getEndDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        ResponseMessage<SjesPage<AnxianActivity>> couponResonse = anxianActivityApiClient.list(activityCondition);
        model.addAttribute("totalPages", couponResonse.getData().getTotalPages());
        model.addAttribute("page", activityCondition.getPage() + 1);
        model.addAttribute("activities", couponResonse.getData());
        return "anXian-promotion/activity-ajax";
    }

    /**
     * 跳转新增、编辑页面
     * @param principal
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/activitiesEdit", method = RequestMethod.GET)
    public String editCoupons(Principal principal, Model model, String id){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        ResponseMessage couponsRes = anxianActivityApiClient.getCoupons();
        model.addAttribute("coupons", couponsRes.getData());
        if (StringUtils.isEmpty(id)) {
            AnxianActivity anxianActivity = new AnxianActivity();
            model.addAttribute("activity", anxianActivity);
            return "anXian-promotion/edit-activity";
        }
        ResponseMessage<AnxianActivity> activityResponse = anxianActivityApiClient.getById(id);
        if (activityResponse.getCode() == SaleConstant.successCode) {
            AnxianActivity anxianActivity = activityResponse.getData();
            model.addAttribute("activity", anxianActivity);
            model.addAttribute("couponMongos",anxianActivity.getActivityCouponMongos());
            return "anXian-promotion/edit-activity";
        } else {
            return "redirect:/anxian/activities";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/deleteActivity", method = RequestMethod.GET)
    public ResponseMessage delete(Principal principal, Model model, String id){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            ResponseMessage responseMessage = new ResponseMessage<>();
            responseMessage.setCode(0);
            responseMessage.setCodeMessage("请先登入");
            return responseMessage;
        }
        return anxianActivityApiClient.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/stopActivity", method = RequestMethod.GET)
    public ResponseMessage stop(Principal principal, Model model, String id){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            ResponseMessage responseMessage = new ResponseMessage<>();
            responseMessage.setCode(0);
            responseMessage.setCodeMessage("请先登入");
            return responseMessage;
        }
        return anxianActivityApiClient.stop(id);
    }


    /**
     *  新增/更新 活动
     * @param principal
     * @param anxianActivity
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
    public ResponseMessage<AnxianActivity> save(Principal principal, @RequestBody AnxianActivity anxianActivity){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            ResponseMessage responseMessage = new ResponseMessage<>();
            responseMessage.setCode(0);
            responseMessage.setCodeMessage("请先登入");
            return responseMessage;
        }
        anxianActivity.setStartDate(LocalDate.parse(anxianActivity.getStartDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        anxianActivity.setEndDate(LocalDate.parse(anxianActivity.getEndDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return anxianActivityApiClient.save(anxianActivity);
    }



    @ResponseBody
    @RequestMapping(value = "/getCouponById", method = RequestMethod.GET)
    public ResponseMessage getCouponById(Principal principal, String promotionId) {
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            ResponseMessage responseMessage = new ResponseMessage<>();
            responseMessage.setCode(0);
            responseMessage.setCodeMessage("请先登入");
            return responseMessage;
        }
        ResponseMessage d =  anxianActivityApiClient.getCouponById(promotionId);
        return anxianActivityApiClient.getCouponById(promotionId);
    }

    @ResponseBody
    @RequestMapping(value = "/activity/search", method = RequestMethod.GET)
    public PageModel<Product> search(String name, Long productId, int page, int limit) {
        PageModel<Product> productPageModels = anxianSearchApiClient.listProducts(productId, name, page - 1, limit);

        return productPageModels;
    }


}
