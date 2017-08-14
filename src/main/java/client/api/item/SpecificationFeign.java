package client.api.item;

import client.api.constants.Constants;
import client.api.item.domain.Goods;
import client.api.item.model.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by qinhailong on 15-12-23.
 */
@FeignClient(Constants.SJES_API_ITEM)
@RequestMapping(value = "specifications")
public interface SpecificationFeign {

    /**
     * 根据主商品Id查询SpecificationModel列表
     *
     * @param goodsId 主商品Id
     * @return SpecificationModel 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    List<SpecificationModel> list(@RequestParam("goodsId") Long goodsId);

    /**
     * 分页查询GoodsModel列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<GoodsModel> search(SearchCoditionModel<Goods> searchCoditionModel);

    /**
     * 根据主商品Id查询子商品列表
     *
     * @param goodsId 主商品Id
     * @return 子商品列表
     */
    @RequestMapping(value = "listProductItemModel", method = RequestMethod.GET)
    List<ProductItemModel> listProductItemModel(@RequestParam("goodsId") Long goodsId);

    /**
     * 更新规格
     *
     * @param specificationModels 更新规格
     * @return 是否成功
     */
//    @PreAuthorize("hasAuthority('SPECIFICATIONS_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveOrUpdateSpecification(List<SpecificationModel> specificationModels);

}
