package client.api.item;

import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.ProductCategoryConditionModel;
import client.api.item.model.ProductCategoryModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinhailong on 15-12-25.
 */
@FeignClient(Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping("productCategorys/anxian")
public interface AnXianProductCategoryFeign {

    /**
     * 分页查询商品多分类列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<ProductCategoryModel> searchProductCategoryModel(SearchCoditionModel<ProductCategoryConditionModel> searchCoditionModel);

    /**
     * 新增多分类关系列表
     *
     * @param productCategoryModel 多分类关系实体列表
     * @return 实体列表
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveOrUpdate(ProductCategoryModel productCategoryModel);


    /**
     * 根据商品Id得到分类列表
     *
     * @param productId 商品Id
     * @return 分类列表
     */
    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    ProductCategoryModel findByProductId(@PathVariable("productId") Long productId);

}
