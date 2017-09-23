package anxian.gateway.admin.module.business.controller.anxian.item;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.AnXianProductFeign;
import client.api.item.AnXianProductsPlanFeign;
import client.api.item.ProductFeign;
import client.api.item.ProductsPlanFeign;
import client.api.item.domain.Product;
import client.api.item.domain.ProductsPlan;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 17-1-11.
 */
@RestController
@RequestMapping("anxian/productsPlan")
public class AnxianProductsPlanController {

    @Autowired
    private AnXianProductsPlanFeign productsPlanFeign;

    @Autowired
    private AnXianProductFeign productFeign;

    /**
     * 分页查询指定计划的商品列表
     *
     * @param condition 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search")
    public PageModel<Product> search(ProductsPlan condition, int page, int limit) {
        SearchCoditionModel<ProductsPlan> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(condition);
        PageModel<ProductsPlan> pageModel = productsPlanFeign.search(searchCoditionModel);
        List<ProductsPlan> content = pageModel.getContent();
        if (CollectionUtils.isNotEmpty(content)) {
            List<Long> erpGoodsIds = Lists.newArrayList();
            content.forEach(productsPlan -> {
                erpGoodsIds.add(productsPlan.getErpGoodsId());
            });
            List<Product> products = productFeign.findByErpGoodsIdIn(Lists.newArrayList(erpGoodsIds));
            return new PageModel(products, pageModel.getTotal(), pageModel.getPageable());
        }
        return new PageModel(Lists.newArrayList(), pageModel.getTotal(), pageModel.getPageable());
    }


    /**
     * 保存
     *
     * @param productsPlan
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(ProductsPlan productsPlan) {
        ResponseMessage responseMessage = productsPlanFeign.save(productsPlan);
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.success(responseMessage.getContent());
    }

    /**
     * 批量保存
     *
     * @param productsPlans
     * @return
     */
    @RequestMapping(value = "batSave", method = RequestMethod.POST)
    public JsonMsg batSave(List<ProductsPlan> productsPlans) {
        ResponseMessage responseMessage = productsPlanFeign.batSave(productsPlans);
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.success(responseMessage.getContent());
    }

    /**
     * 根据计划Id和商品管理码删除
     *
     * @param planId     计划Id
     * @param erpGoodsId 商品管理码
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long planId, Long erpGoodsId) {
        ResponseMessage responseMessage = productsPlanFeign.delete(planId, erpGoodsId);
        if (ResponseMessage.isSuccess(responseMessage)) {
            return JsonMsg.success(responseMessage.getContent());
        }
        return JsonMsg.success(responseMessage.getContent());
    }


}
