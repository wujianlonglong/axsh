package client.api.item;

import anxian.gateway.admin.module.business.model.item.ProductSpecificationValueModel;
import client.api.constants.Constants;
import client.api.item.domain.ProductSpecificationValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by qinhailong on 15-11-17.
 */
@FeignClient(Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping("productSpecificationValues/anxian")
public interface AnXianProductSpecificationValueFeign {

    /**
     * 规格值修改
     *
     * @param productSpecificationValueModel
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateProductSpecificationModel(ProductSpecificationValueModel productSpecificationValueModel);

    /**
     * 根据产品erpGoodsId查询产品规格值列表
     *
     * @param sn 商品编码
     * @return 产品规格值列表
     */
    @RequestMapping(value = "listBySn", method = RequestMethod.GET)
    List<ProductSpecificationValue> listBySn(@RequestParam("sn") String sn);

}
