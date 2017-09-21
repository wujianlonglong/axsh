package client.api.item;

import client.api.constants.Constants;
import client.api.item.domain.ProductAttributeValue;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Jianghe on 15/12/18.
 */
@FeignClient(Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping("/productAttributeValues/anxian")
public interface ProductAttributeApiClient {

    /**
     * 根据productId得到单品属性值列表
     *
     * @param productId 单品Id
     * @return 单品属性值列表
     */
    @RequestMapping(value = "{productId}", method = RequestMethod.GET)
    List<ProductAttributeValue> listByProductId(@PathVariable("productId") Long productId);
}
