package client.api.item;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.constants.Constants;
import client.api.item.domain.ProductStatusPlan;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinhailong on 17-1-11.
 */
@FeignClient(name=Constants.SJES_API_ITEM)
@RequestMapping(value = "productStatusPlans", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface ProductStatusPlanFeign {

    /**
     * 分页查询批量更新商品状态计划列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    PageModel<ProductStatusPlan> search(SearchCoditionModel<ProductStatusPlan> searchCoditionModel);

    /**
     * 保存商品状态计划
     *
     * @param productStatusPlan 商品状态计划
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    ResponseMessage save(ProductStatusPlan productStatusPlan);

    /**
     * 更新指定的商品状态更改计划
     *
     * @param productStatusPlan 商品状态计划
     */
    @RequestMapping(method = RequestMethod.PUT)
    ResponseMessage update(ProductStatusPlan productStatusPlan);

    /**
     * 删除指定的商品状态更改计划
     *
     * @param productStatusPlanId 主键Id
     */
    @RequestMapping(value = "{productStatusPlanId}", method = RequestMethod.DELETE)
    ResponseMessage delete(@PathVariable("productStatusPlanId") Long productStatusPlanId);
}
