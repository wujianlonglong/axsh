package client.api.item;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.constants.Constants;
import client.api.item.domain.ProductsPlan;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by qinhailong on 17-1-10.
 */
@FeignClient(value=Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping(value = "productsPlans/anxian", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface AnXianProductsPlanFeign {

    /**
     * 保存
     *
     * @param productsPlan
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseMessage save(ProductsPlan productsPlan);

    /**
     * 批量保存
     *
     * @param productsPlans
     * @return
     */
    @RequestMapping(value = "batSave", method = RequestMethod.POST)
    ResponseMessage batSave(List<ProductsPlan> productsPlans);

    /**
     * 分页查询指定计划的商品列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    PageModel<ProductsPlan> search(SearchCoditionModel<ProductsPlan> searchCoditionModel);

    /**
     * 根据计划Id和商品管理码删除
     *
     * @param planId     计划Id
     * @param erpGoodsId 商品管理码
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    ResponseMessage delete(@RequestParam("planId") Long planId, @RequestParam("erpGoodsId") Long erpGoodsId);

    @RequestMapping(value="deletenew",method = RequestMethod.DELETE)
    ResponseMessage deletenew(@RequestParam("planId") Long planId, @RequestParam("erpGoodsId") Long erpGoodsId,@RequestParam("shopId")String shopId);

}
