package client.api.item;

import anxian.gateway.admin.module.business.model.item.ProductPriceModel;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.constants.Constants;
import client.api.item.domain.Product;
import client.api.item.domain.ProductModel;
import client.api.item.model.PageModel;
import client.api.item.model.ProductImageModel;
import client.api.item.model.SearchCoditionModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by mac on 15/8/28.
 */
@FeignClient(Constants.SJES_API_ITEM)
@RequestMapping(value = "/products")
public interface ProductFeign {

    /**
     * 根据商品Id得到商品详细信息
     *
     * @param sn 商品编码
     * @return 详细信息
     */
    @RequestMapping(value = "/getProductDetail", method = RequestMethod.GET)
    ProductModel getProductDetail(@RequestParam("sn") String sn, @RequestParam("status") Integer status);

    /**
     * 分页查询单品列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Product> search(SearchCoditionModel<Product> searchCoditionModel);

    /**
     * 分页查询单品列表[fdw]
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "searchForSecondKill", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Product> searchForSecondKill(SearchCoditionModel<Product> searchCoditionModel);

    /**
     * 分页查询单品列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "searchCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Product> searchProductPackage(SearchCoditionModel<Product> searchCoditionModel);


    /**
     * 根据主键得到商品信息
     *
     * @param id 主键
     * @return 商品信息
     */
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    Product findById(@RequestParam("id") Long id);

    /**
     * 根据主键得到商品信息
     *
     * @param ids 主键列表
     * @return 商品信息
     */
    @RequestMapping(value = "findByIdIn", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> findByIdIn(List<Long> ids);

    /**
     * 根据商品编码得到商品信息
     *
     * @param sn 商品编码
     * @return 商品信息
     */
    @RequestMapping(value = "findBySn", method = RequestMethod.GET)
    Product findBySn(@RequestParam("sn") String sn);

    /**
     * 根据商品编码sn查询商品列表信息
     *
     * @param sns 商品编码sn
     * @return 商品列表信息
     */
    @RequestMapping(value = "findBySnIn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> findBySnIn(List<String> sns);

    /**
     * 根据产品erpGoodsIds 商品管理码列表 查询产品
     *
     * @param erpGoodsIds 商品管理码列表
     * @return 产品
     */
    @RequestMapping(value = "findByErpGoodsIdIn", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Product> findByErpGoodsIdIn(List<Long> erpGoodsIds);

    /**
     * 根据商品分类id查询商品列表
     *
     * @param categoryId 分类id
     * @return 商品列表
     */
    @RequestMapping(value = "findByCategoryId", method = RequestMethod.GET)
    List<Product> findByCategoryId(@RequestParam("categoryId") Long categoryId);

    /**
     * 更新商品
     *
     * @param productModel
     * @return 更新数目
     */
//    @PreAuthorize("hasAuthority('COMMODITYINFORMATION_SAVE')")
    @RequestMapping(value = "updateProductModel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int updateProductModel(ProductModel productModel) throws ServiceException;

    /**
     * 更新单品状态
     *
     * @param id
     * @param status
     * @return 更新的数量
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.PUT)
    int updateStatus(@RequestParam("id") Long id, @RequestParam("status") Integer status, @RequestParam("message") String message);

    /**
     * 分页查询商品价格修改列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "searchProductPrices", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<ProductPriceModel> searchProductPrices(SearchCoditionModel<Product> searchCoditionModel);

    /**
     * 根据商品管理码列表分页查询商品列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页商品列表
     */
    @RequestMapping(value = "listByErpGoodsIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<ProductImageModel> listByErpGoodsIds(SearchCoditionModel<String[]> searchCoditionModel);

    /**
     * 修改商品价格
     *
     * @param modifyUserName 改价用户名
     * @param productId      商品id
     * @param salePrice      销售价
     * @param memberPrice    会员价
     */
    @RequestMapping(value = "updatePrice", method = RequestMethod.GET)
    ResponseMessage updatePrice(@RequestParam("modifyUserName") String modifyUserName, @RequestParam("productId") Long productId, @RequestParam("salePrice") BigDecimal salePrice, @RequestParam("memberPrice") BigDecimal memberPrice);

    /**
     * 批量修改商品价格
     *
     * @param productPrices 商品价格列表
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "batUpdatePrice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage batUpdatePrice(List<ProductPriceModel> productPrices) throws ServiceException;

    /**
     * 批量更新单品状态
     *
     * @param erpGoodsIds
     * @param status
     * @return
     * @throws ServiceException
     */
    @RequestMapping(value = "batUpdateStatus/{status}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage batUpdateStatus(List<Long> erpGoodsIds, @PathVariable("status") Integer status) throws ServiceException;

}
