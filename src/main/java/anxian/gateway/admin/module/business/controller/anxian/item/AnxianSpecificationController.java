package anxian.gateway.admin.module.business.controller.anxian.item;

import anxian.gateway.admin.module.business.model.item.ProductSpecificationValueModel;
import anxian.gateway.admin.module.business.model.www.MultipleSpecificationsOfGoods;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.item.ProductSpecificationValueFeign;
import client.api.item.SpecificationFeign;
import client.api.item.domain.Goods;
import client.api.item.domain.ProductSpecificationValue;
import client.api.item.model.*;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 15-12-24.
 */
@RestController
@RequestMapping("anxian/specification")
public class AnxianSpecificationController {

    @Autowired
    private SpecificationFeign specificationFeign;

    @Autowired
    private ProductSpecificationValueFeign productSpecificationValueFeign;


    /**
     * 分页查询GoodsModel列表
     *
     * @param goods 分页查询条件
     * @return 分页列表
     */
    @RequestMapping("search")
    public PageModel<GoodsModel> search(Goods goods, int page, int limit) {
        SearchCoditionModel<Goods> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(goods);
        return specificationFeign.search(searchCoditionModel);
    }

    /**
     * 根据主商品Id查询SpecificationModel列表
     *
     * @param goodsId 主商品Id
     * @return SpecificationModel 列表
     */
    @RequestMapping(value = "{goodsId}", method = RequestMethod.GET)
    public JsonMsg list(@PathVariable("goodsId") Long goodsId) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setData(specificationFeign.list(goodsId));
        return jsonMsg;
    }


    /**
     * 更新或保存规格信息
     *
     * @param multipleSpecificationsOfGoods 规格维度
     * @return SpecificationModel
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public JsonMsg saveOrUpdateProductItemModel(MultipleSpecificationsOfGoods multipleSpecificationsOfGoods) {
        specificationFeign.saveOrUpdateSpecification(multipleSpecificationsOfGoods.getSpecificationModels());
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setMsg("修改成功");
        return jsonMsg;
    }


    /**
     * 根据主商品Id查询子商品列表
     *
     * @param goodsId 主商品Id
     * @return 子商品列表
     */
    @RequestMapping(value = "listProductItemModel", method = RequestMethod.GET)
    public List<ProductItemModel> listProductItemModel(Long goodsId) {
        List<ProductItemModel> listProductItemModel = Lists.newArrayList();
        List<ProductItemModel> productItemModels = specificationFeign.listProductItemModel(goodsId);
        for (ProductItemModel productItemModel : productItemModels) {
            List<SpecificationModel> specificationModels = specificationFeign.list(productItemModel.getGoodsId());
            List<ProductSpecificationValue> productSpecificationValues = productSpecificationValueFeign.listBySn(productItemModel.getSn());
            if (!CollectionUtils.isEmpty(specificationModels)) {
                for (int i = 0; i < specificationModels.size(); i++) {
                    SpecificationModel specificationModel = specificationModels.get(i);
                    if (!CollectionUtils.isEmpty(productSpecificationValues)) {
                        for (ProductSpecificationValue specificationValue : productSpecificationValues) {
                            if (specificationValue.getSpecificationId() == specificationModel.getId()) {
                                if (i == 0) {
                                    productItemModel.setSpecifications1(specificationModel.getName());
                                } else {
                                    productItemModel.setSpecifications2(specificationModel.getName());
                                }
                            }
                        }
                    }


                }
            }
            listProductItemModel.add(productItemModel);
        }

        return listProductItemModel;
    }

    /**
     * 根据产品erpGoodsId查询产品规格值列表
     *
     * @param sn 商品管理码
     * @return 产品规格值列表
     */
    @RequestMapping(value = "listBySn", method = RequestMethod.GET)
    List<ProductSpecificationValue> listBySn(String sn) {
        return productSpecificationValueFeign.listBySn(sn);
    }

    /**
     * 子商品信息修改
     *
     * @param productSpecificationValueModel
     */
    @RequestMapping(value = "updateSpecificationModel", method = RequestMethod.POST)
    public JsonMsg updateSpecificationModel(ProductSpecificationValueModel productSpecificationValueModel) {
        productSpecificationValueFeign.updateProductSpecificationModel(productSpecificationValueModel);
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setSuccess(true);
        jsonMsg.setMsg("修改成功");
        return jsonMsg;
    }

}
