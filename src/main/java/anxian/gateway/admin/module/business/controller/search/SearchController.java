package anxian.gateway.admin.module.business.controller.search;

import client.api.item.AnXianProductFeign;
import client.api.item.domain.Product;
import client.api.item.domain.ProductsPlanShow;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import client.api.search.AnxianSearchApiClient;
import client.api.search.SearchApiClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 17-1-12.
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchApiClient searchApiClient;

    @Autowired
    private AnXianProductFeign productFeign;

    @Autowired
    private AnxianSearchApiClient anxianSearchApiClient;

    /**
     * 分页查询单品列表
     *
     * @return 分页列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<Product> searchSQL(String name, String productId, int page, int limit) {
        SearchCoditionModel<Product> searchCoditionModel = new SearchCoditionModel<>();
        Product condition = new Product();
        if (StringUtils.isNotBlank(productId)) {
            condition.setSn(productId);
        } else if (StringUtils.isNotBlank(name)) {
            condition.setName(name);
        }
        searchCoditionModel.setSearchCodition(condition);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        return productFeign.search(searchCoditionModel);
    }

    @RequestMapping(value="/searchnew",method = RequestMethod.GET)
    public PageModel<ProductsPlanShow> searchnew(String name, String productId, String shopId , int page, int limit) {
        SearchCoditionModel<ProductsPlanShow> searchCoditionModel = new SearchCoditionModel<>();
        ProductsPlanShow condition = new ProductsPlanShow();
        if(StringUtils.isNotBlank(shopId)){
            condition.setShopId(shopId);
        }
        if (StringUtils.isNotBlank(productId)) {
            condition.setSn(productId);
        } else if (StringUtils.isNotBlank(name)) {
            condition.setName(name);
        }
        searchCoditionModel.setSearchCodition(condition);
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        return productFeign.searchnew(searchCoditionModel);
    }




    /**
     * 搜索
     *
     * @param name
     * @param productId
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public PageModel<Product> search(String name, Long productId, int page, int limit) {
        PageModel<Product> productPageModels = searchApiClient.listProducts(productId, name, page - 1, limit);
        if (productPageModels.getTotal() == 0) {
            productPageModels = anxianSearchApiClient.listProducts(productId, name, page - 1, limit);
        }
        return productPageModels;
    }

}
