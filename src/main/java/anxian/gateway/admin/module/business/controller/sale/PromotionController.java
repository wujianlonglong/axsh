package anxian.gateway.admin.module.business.controller.sale;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.gift.GiftApiClient;
import client.api.gift.model.Gift;
import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.sale.SaleApiClient;
import client.api.sale.model.*;
import client.api.search.AnxianSearchApiClient;
import client.api.search.SearchApiClient;
import client.api.user.utils.page.SjesPage;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kimiyu on 16/1/11.
 */
@RestController
@RequestMapping(value = "/promotions")
public class PromotionController extends BaseController {

    @Autowired
    private SaleApiClient saleApiClient;

    @Autowired
    private GiftApiClient giftApiClient;

    @Autowired
    private SearchApiClient searchApiClient;

    @Autowired
    private AnxianSearchApiClient anxianSearchApiClient;


    /**
     * 促销管理列表
     */
    @RequestMapping(value = "/lists", method = RequestMethod.POST)
    public PageModel<Promotion> saleManage(SaleManageCondition saleManageCondition, int page, int limit) {
        saleManageCondition.setPage(page - 1);
        saleManageCondition.setSize(limit);
        SjesPage<Promotion> promotions = saleApiClient.saleManage(saleManageCondition);
        List<Promotion> newPromotions = Lists.newArrayList();
        for (Promotion promotion : promotions.getContent()) {
            promotion.setPromotionalDate(promotion.getStartDate().toString().split("T")[0] + "至" + promotion.getEndDate().toString().split("T")[0]);
            newPromotions.add(promotion);
        }
        return new PageModel<>(newPromotions, promotions.getTotalElements(), new Pageable(page, limit));
    }


    /**
     * 返回秒杀商品列表
     */
    @RequestMapping(value = "/spikeCommodity", method = RequestMethod.GET)
    public SjesPage<Promotion> spikeCommodity(String productId, String itemName, int page, int limit) {
        SaleManageCondition saleManageCondition = new SaleManageCondition();
        saleManageCondition.setPromotionType(SaleConstant.secondKill);//类型为秒杀
        //saleManageCondition.setPromotionStatus(SaleConstant.doing);//进行中
        if (!StringUtils.isEmpty(productId)) {
            saleManageCondition.setItemId(productId);//商品ID
        }

        if (!StringUtils.isEmpty(itemName)) {
            saleManageCondition.setItemName(itemName);//商品名称
        }
        saleManageCondition.setPage(page - 1);
        saleManageCondition.setSize(limit);
        SjesPage<Promotion> promotions = saleApiClient.saleManage(saleManageCondition);

        return promotions;
    }

    /**
     * 返回可以供秒杀添加的商品
     */
    @RequestMapping(value = "/unContainSpikeCommodity", method = RequestMethod.GET)
    public PageModel<Product> unContainSpikeCommodity(String name, String id, int page, int limit) {
        Long productId = null;
        if (!StringUtils.isEmpty(id)) {
            productId = Long.parseLong(id);
        }
        PageModel<Product> productPageModels = searchApiClient.getProductsByIdOrName(productId, name, SaleConstant.secondKill, page - 1, limit);
        if (productPageModels.getTotal() == 0) {
            productPageModels = anxianSearchApiClient.getProductsByIdOrName(productId, name, SaleConstant.secondKill, page - 1, limit);
        }
        return productPageModels;

    }


    /**
     * 根据ID获取促销信息
     *
     * @param id 促销ID
     * @return 促销对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public JsonMsg getSale(@PathVariable("id") String id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(saleApiClient.getSale(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 保存促销秒杀信息
     *
     * @param promotionBenefitViewModel 秒杀对象
     * @return 保存成功
     */
    @RequestMapping(method = RequestMethod.POST, value = "/promotion/saveSecondKill")
    public JsonMsg saveSecondKill(PromotionBenefitViewModel promotionBenefitViewModel) {

        try {
            AclUser user = UserContextHelper.getUser();

            promotionBenefitViewModel.setUserId(user.getId());
            List<JoinedGateShop> gateShops = promotionBenefitViewModel.getGateShops();
            if (CollectionUtils.isEmpty(gateShops)) {
                promotionBenefitViewModel.setJoinGateShopType(1);
            } else {
                promotionBenefitViewModel.setJoinGateShopType(2);
                for (JoinedGateShop gateShop : gateShops) {
                    gateShop.setUserId(user.getId());
                }
            }
            String saleId = promotionBenefitViewModel.getId();
            if (!StringUtils.isEmpty(saleId)) {
                Promotion promotion = saleApiClient.getSale(saleId);
                if (promotion != null) {
                    promotionBenefitViewModel.setEnvType(promotion.getEnvType());
                    promotionBenefitViewModel.setMemberLevel(promotion.getMemberLevel());
                }
            }

            ResponseMessage responseMessage = saleApiClient.saveSecondKill(promotionBenefitViewModel);
            if (responseMessage.getCode() == SaleConstant.successCode) {
                return JsonMsg.success("保存成功");
            } else {
                return JsonMsg.failure(responseMessage.getCodeMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("保存失败");
        }
    }

    /**
     * 满赠的商品列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/promotion/fullGiftProducts")
    public PageModel<Product> getProducts(String name, int page, int limit) {
        PageModel<Product> productPageModels = searchApiClient.getProductsByIdOrName(null, name, null, page - 1, limit);
        if (productPageModels.getTotal() == 0) {
            productPageModels = anxianSearchApiClient.getProductsByIdOrName(null, name, null, page - 1, limit);
        }
        return productPageModels;
    }


    /**
     * 保存满赠促销信息
     *
     * @param promotionGiftViewModel 满赠促销
     */
    @RequestMapping(value = "/promotion/saveGift", method = RequestMethod.POST)
    public JsonMsg saveGift(PromotionGiftViewModel promotionGiftViewModel) {
        try {
            AclUser user = UserContextHelper.getUser();

            promotionGiftViewModel.setUserId(user.getId());

            //阶梯信息处理
            List<JoinedGift> newJoinedGiftList = Lists.newArrayList();
            for (FullGift fullGift : promotionGiftViewModel.getFullGifts()) {
                fullGift.setUserId(user.getId());
                List<JoinedGift> joinedGiftList = fullGift.getJoinedGiftList();
                for (JoinedGift joinedGift : joinedGiftList) {
                    joinedGift.setUserId(user.getId());
                    joinedGift.setGiftLevel(fullGift.getGiftLevel());
                }
            }
            String saleId = promotionGiftViewModel.getId();
            if (!StringUtils.isEmpty(saleId)) {
                Promotion promotion = saleApiClient.getSale(saleId);
                if (promotion != null) {
                    promotionGiftViewModel.setMemberLevel(promotion.getMemberLevel());
                    promotionGiftViewModel.setEnvType(promotion.getEnvType());
                }
            }


            //参与门店处理
            List<JoinedGateShop> gateShops = promotionGiftViewModel.getGateShops();
            if (!CollectionUtils.isEmpty(gateShops)) {
                for (JoinedGateShop gateShop : gateShops) {
                    gateShop.setUserId(user.getId());
                    gateShop.setSaleType(SaleConstant.fullGift);
                    List<GateShopGift> gateShopGifts = gateShop.getGateShopGifts();
                    Map<String, JoinedGift> joinedGifts = new HashMap<>();
                    for (GateShopGift gateShopGift : gateShopGifts) {
                        JoinedGift joinedGift = new JoinedGift();
                        String giftName = gateShopGift.getGiftName();
                        Gift gift = giftApiClient.getGift(giftName);
                        if (gift != null) {
                            joinedGift.setSendNumber(gateShopGift.getSendNumber());
                            joinedGift.setGiftAmount(gateShopGift.getSendNumber());
                            joinedGift.setGiftCode(giftName);
                            joinedGift.setGiftName(gift.getName());
                            joinedGift.setUserId(user.getId());
                            joinedGift.setGiftStatus(gift.getStatus());
                            for (FullGift fullGift : promotionGiftViewModel.getFullGifts()) {
                                List<JoinedGift> joinedGiftList = fullGift.getJoinedGiftList();
                                for (JoinedGift fullGift_joinedGift : joinedGiftList) {
                                    if (fullGift_joinedGift.getGiftCode().equals(joinedGift.getGiftCode())) {
                                        joinedGift.setGiftLevel(fullGift_joinedGift.getGiftLevel());
                                        break;
                                    }

                                }
                            }

                        }
                        joinedGifts.put(gateShopGift.getGiftName(), joinedGift);
                    }
                    gateShop.setJoinedGifts(joinedGifts);
                }
            }
            promotionGiftViewModel.setGateShops(gateShops);

            //参与商品处理
            List<JoinedItem> joinedItems = promotionGiftViewModel.getJoinedItems();
            if (!CollectionUtils.isEmpty(joinedItems)) {
                for (JoinedItem joinedItem : joinedItems) {
                    joinedItem.setParticipationMode(promotionGiftViewModel.getJoinedItemType());
                    joinedItem.setSaleType(promotionGiftViewModel.getSaleType());
                    joinedItem.setUserId(user.getId());
                }
                promotionGiftViewModel.setJoinedItemType(SaleConstant.part);
            } else {
                promotionGiftViewModel.setJoinedItemType(SaleConstant.all);
            }

            ResponseMessage<Promotion> responseMessage = saleApiClient.saveGift(promotionGiftViewModel);
            if (responseMessage.getCode() == SaleConstant.successCode) {
                return JsonMsg.success("保存成功");
            } else {
                return JsonMsg.failure(responseMessage.getCodeMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("保存失败");
        }

    }

    /**
     * 删除促销信息
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public JsonMsg deleteSale(@PathVariable("id") String id) {
        try {
            saleApiClient.deleteSale(id);
            return JsonMsg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("删除失败");
        }
    }

    /**
     * 促销强停 根据id来设置状态和强停理由
     *
     * @param promotion 强停信息存储
     * @return Promotion
     */
    @RequestMapping(value = "/stop", method = RequestMethod.POST)
    public JsonMsg stopPromotion(Promotion promotion) {
        Promotion promotion1 = saleApiClient.stopPromotion(promotion);
        if (promotion1 != null) {
            return JsonMsg.success("强停成功");
        }
        return JsonMsg.failure("强停失败");
    }


    /**
     * 更新限买次数
     *
     * @param limitNumber 限买次数
     * @return 返回结果
     */
    @RequestMapping(value = "/updateLimitNumber", method = RequestMethod.POST)
    public JsonMsg updateLimitNumber(Integer limitNumber) {
        AclUser user = UserContextHelper.getUser();
        ResponseMessage<List<Promotion>> responseMessage = saleApiClient.updateLimitNumber(limitNumber, user.getId());
        if (responseMessage.getCode() == SaleConstant.successCode) {
            return JsonMsg.success("保存成功");
        } else {
            return JsonMsg.failure(responseMessage.getCodeMessage());
        }
    }

    /**
     * 显示限购次数
     */
    @RequestMapping(value = "/getLimitNumber", method = RequestMethod.GET)
    public JsonMsg getLimitNumber() {
        AclUser user = UserContextHelper.getUser();
        ResponseMessage<Integer> responseMessage = saleApiClient.getLimitNumber(user.getId());
        JsonMsg jsonMsg = new JsonMsg();
        if (responseMessage.getCode() == SaleConstant.successCode) {
            jsonMsg.setSuccess(true);
            jsonMsg.setData(responseMessage.getData());
        } else {
            jsonMsg.setFailure(true);
            jsonMsg.setMsg(responseMessage.getCodeMessage());
        }
        return jsonMsg;
    }

}
