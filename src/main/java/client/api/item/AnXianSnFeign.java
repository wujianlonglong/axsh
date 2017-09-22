package client.api.item;

import client.api.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qinhailong on 16-4-21.
 */
@FeignClient(Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping(value = "/sns/anxian")
public interface AnXianSnFeign {

    /**
     * 产生商品编码
     *
     * @param productId 商品Id
     * @return 商品编号
     */
    @RequestMapping(value = "generateProductSn/{productId}", method = RequestMethod.GET)
    String generateProductSn(@PathVariable("productId") Long productId);
}
