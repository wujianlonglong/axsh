package anxian.gateway.admin.module.business.controller.anxian.coupon;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import client.api.anxian.AXGateShopClient;
import client.api.anxian.shop.AXGateShop;
import client.api.item.AnXianProductFeign;
import client.api.item.domain.Product;
import client.api.sale.CouponApiClient;
import client.api.sale.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by wangdinglan on 2017/09/04
 */
@Controller
@RequestMapping("/anxian")
public class AnxianCouponController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private CouponApiClient couponApiClient;

    @Autowired
    private AnXianProductFeign anXianProductFeign;

    @Autowired
    private AXGateShopClient axGateShopClient;


    @RequestMapping("/coupons")
    public String coupons(Principal principal, Model model){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        return "anXian-promotion/coupon";
    }

    @RequestMapping(value = "/ajaxCoupons", method = RequestMethod.POST)
    public String ajaxCoupons(Principal principal, Model model, VolumeCondition volumeCondition){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        if (!StringUtils.isEmpty(volumeCondition.getStartDateStr())) {
            volumeCondition.setStartDate(LocalDateTime.parse(volumeCondition.getStartDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        if (!StringUtils.isEmpty(volumeCondition.getEndDateStr())) {
            volumeCondition.setEndDate(LocalDateTime.parse(volumeCondition.getEndDateStr(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
        ResponseMessage<SjesPage<CouponMongo>> couponResonse = couponApiClient.list(volumeCondition);
        model.addAttribute("totalPages", couponResonse.getData().getTotalPages());
        model.addAttribute("page", volumeCondition.getPage() + 1);
        model.addAttribute("coupons", couponResonse.getData());
        return "anXian-promotion/coupon-ajax";
    }

    @RequestMapping(value = "/editCoupon", method = RequestMethod.POST)
    public String editCoupons(Principal principal, Model model, String id){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }
        getMenus(user, model);
        ResponseMessage<CouponMongo> couponResonse = couponApiClient.getById(id);
        CouponMongo couponMongo = couponResonse.getData();
        if (couponMongo.getParticipationMode().compareTo(SaleConstant.part) == 0 &&
                !CollectionUtils.isEmpty(couponMongo.getJoinedItems())){
            List<Product> joinProducts = anXianProductFeign.findByErpGoodsIdIn(couponMongo.getJoinedItems());
            couponMongo.setJoinedProducts(joinProducts);
        }
        if (couponMongo.getJoinGateShopType().compareTo(SaleConstant.part) == 0 &&
                !CollectionUtils.isEmpty(couponMongo.getJoinedShops())){
            List<AXGateShop> anxianShops = axGateShopClient.getGateShopByShopId(couponMongo.getJoinedShops());
            couponMongo.setJoinedShopsDetail(anxianShops);
        }
        model.addAttribute("coupon", couponMongo);
        return "anXian-promotion/edit-coupon";
    }
    @ResponseBody
    @RequestMapping(value = "/saveCoupon", method = RequestMethod.POST)
    public ResponseMessage<CouponMongo> save(Principal principal, Model model, CouponParamDTO couponParamDTO){
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
           ResponseMessage responseMessage = new ResponseMessage<>();
           responseMessage.setCode(0);
           responseMessage.setCodeMessage("请先登入");
           return responseMessage;
        }
        couponParamDTO.setOperator(user.getUsername());
        return couponApiClient.save(couponParamDTO);
    }

}
