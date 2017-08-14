package anxian.gateway.admin.module.business.controller.item;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductStatusPlanFeign;
import client.api.item.domain.ProductStatusPlan;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 17-1-11.
 */
@RestController
@RequestMapping("productStatusPlan")
public class ProductStatusPlanController {

    @Autowired
    private ProductStatusPlanFeign productStatusPlanFeign;

    /**
     * 分页查询批量更新商品状态计划列表
     *
     * @param productStatusPlan 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search")
    public PageModel<ProductStatusPlan> search(ProductStatusPlan productStatusPlan, int page, int limit) {
        SearchCoditionModel<ProductStatusPlan> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(productStatusPlan);
        return productStatusPlanFeign.search(searchCoditionModel);
    }

    /**
     * 保存商品状态计划
     *
     * @param productStatusPlan 商品状态计划
     * @return
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    public JsonMsg productStatusPlan(ProductStatusPlan productStatusPlan) {
        Long id = productStatusPlan.getId();
        ResponseMessage responseMessage = null;
        if (null != id) {
            responseMessage = productStatusPlanFeign.update(productStatusPlan);
        } else {
            responseMessage = productStatusPlanFeign.save(productStatusPlan);
        }
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.failure(responseMessage.getContent());
    }

    /**
     * 删除指定的商品状态更改计划
     *
     * @param productStatusPlanId
     */
    @RequestMapping(value = "{productStatusPlanId}", method = RequestMethod.DELETE)
    public JsonMsg delete(@PathVariable("productStatusPlanId") Long productStatusPlanId) {
        ResponseMessage responseMessage = productStatusPlanFeign.delete(productStatusPlanId);
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.failure(responseMessage.getContent());
    }

}
