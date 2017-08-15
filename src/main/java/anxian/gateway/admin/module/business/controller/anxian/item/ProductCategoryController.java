package anxian.gateway.admin.module.business.controller.anxian.item;

import anxian.gateway.admin.module.business.model.item.ProductCategoryDetailModel;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.category.CategoryApiClient;
import client.api.category.domain.Category;
import client.api.item.ProductCategoryFeign;
import client.api.item.model.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 15-12-25.
 */
@RestController
@RequestMapping("anxian/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryFeign productCategoryFeign;

    @Autowired
    private CategoryApiClient categoryApiClient;

    /**
     * 分页查询商品多分类列表
     *
     * @param productCategoryConditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public PageModel<ProductCategoryDetailModel> searchProductCategoryModel(ProductCategoryConditionModel productCategoryConditionModel, int page, int limit) {
        SearchCoditionModel<ProductCategoryConditionModel> searchCoditionModel = new SearchCoditionModel();
        searchCoditionModel.setSearchCodition(productCategoryConditionModel);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        PageModel<ProductCategoryModel> pageModel = productCategoryFeign.searchProductCategoryModel(searchCoditionModel);
        List<ProductCategoryModel> contents = pageModel.getContent();
        List<ProductCategoryDetailModel> productCategoryDetailModels = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(contents)) {
            contents.forEach(content -> {
                productCategoryDetailModels.add(this.populationProductCategoryDetailModel(content));
            });
        }
        return new PageModel(productCategoryDetailModels, pageModel.getTotal(), new Pageable(pageModel.getPage(), pageModel.getSize()));
    }

    /**
     * 新增多分类关系列表
     *
     * @param productCategoryModel 多分类关系实体列表
     * @return 实体列表
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg saveOrUpdate(ProductCategoryModel productCategoryModel) {
        productCategoryFeign.saveOrUpdate(productCategoryModel);
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setMsg("修改成功");
        return jsonMsg;
    }

    /**
     * 根据商品Id得到分类列表
     *
     * @param productId 商品Id
     * @return 分类列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public ProductCategoryDetailModel findByProductId(Long productId) {
        return this.populationProductCategoryDetailModel(productCategoryFeign.findByProductId(productId));
    }


    private ProductCategoryDetailModel populationProductCategoryDetailModel(ProductCategoryModel productCategoryModel) {
        ProductCategoryDetailModel productCategoryDetailModel = new ProductCategoryDetailModel();
        BeanUtils.copyProperties(productCategoryModel, productCategoryDetailModel);
        List<ProductCategory> productCategories = productCategoryDetailModel.getProductCategories();
        List<String> categoryNames = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(productCategories)) {
            productCategories.forEach(productCategory -> {
                List<Category> categories = categoryApiClient.findClusters(productCategory.getCategoryId());
                if (CollectionUtils.isNotEmpty(categories)) {
                    List<String> categoryNameList = Lists.newArrayList();
                    categories.forEach(category -> {
                        categoryNameList.add(category.getName());
                    });
                    categoryNames.add(StringUtils.join(categoryNameList, "-->"));
                }
            });
        }
        productCategoryDetailModel.setCategoryNames(categoryNames);
        return productCategoryDetailModel;
    }
}
