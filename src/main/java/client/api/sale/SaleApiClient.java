package client.api.sale;

import client.api.sale.model.*;
import client.api.user.utils.page.SjesPage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by kimiyu on 16/1/11.
 */
@FeignClient("sjes-api-sale")
@RequestMapping(value = "/sales", consumes = MediaType.APPLICATION_JSON_VALUE)
public interface SaleApiClient {

    /**
     * 保存促销秒杀信息
     *
     * @param promotionBenefitViewModel 秒杀对象
     * @return 保存成功
     */
    @PreAuthorize("hasAuthority('PROMOTION_SAVE')")
    @RequestMapping(method = RequestMethod.POST, value = "/promotion/saveSecondKill")
    ResponseMessage saveSecondKill(PromotionBenefitViewModel promotionBenefitViewModel);

    /**
     * 保存满赠促销信息
     *
     * @param promotionGiftViewModel 满赠促销
     */
    @PreAuthorize("hasAuthority('PROMOTION_SAVE')")
    @RequestMapping(value = "/promotion/saveGift", method = RequestMethod.POST)
    ResponseMessage saveGift(PromotionGiftViewModel promotionGiftViewModel);

    /**
     * 删除促销信息
     *
     * @param id
     */
    @PreAuthorize("hasAuthority('PROMOTION_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/promotion/{id}")
    void deleteSale(@PathVariable("id") String id);

    /**
     * 根据ID获取促销信息
     *
     * @param id 促销ID
     * @return 促销对象
     */
    @RequestMapping(method = RequestMethod.GET, value = "/promotion/{id}")
    Promotion getSale(@PathVariable("id") String id);

    /**
     * 促销管理
     *
     * @return
     */
    @RequestMapping(value = "/promotion/lists", method = RequestMethod.POST)
    SjesPage<Promotion> saleManage(SaleManageCondition saleManageCondition);

    /**
     * 检查商品是否可以参加秒杀活动
     *
     * @param saleManageCondition 查询条件
     * @return
     */
    @RequestMapping(value = "/promotion/checkProductForSecondKill", method = RequestMethod.POST)
    Long checkProductForSecondKill(SaleManageCondition saleManageCondition);

    /**
     * 根据用户ID获取填写的促销限制数量
     *
     * @param userId 用户Id
     * @return
     */
    @RequestMapping(value = "/promotion/getLimitNumber", method = RequestMethod.GET)
    ResponseMessage<Integer> getLimitNumber(@RequestParam("userId") Long userId);

    /**
     * 促销强停
     * 根据id来设置状态和强停理由
     *
     * @param promotion 强停信息存储
     * @return Promotion
     */
    @RequestMapping(value = "/promotion/stop", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Promotion stopPromotion(Promotion promotion);


    /**
     * 更新限买次数
     *
     * @param limitNumber 限买次数
     * @param userId      用户ID
     * @return 返回结果
     */
    @RequestMapping(value = "/promotion/updateLimitNumber", method = RequestMethod.POST)
    ResponseMessage<List<Promotion>> updateLimitNumber(@RequestParam("limitNumber") Integer limitNumber,
                                                       @RequestParam("userId") Long userId);
}
