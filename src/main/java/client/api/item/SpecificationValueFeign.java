package client.api.item;

import client.api.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by qinhailong on 16-1-5.
 */
@FeignClient(Constants.ANXIAN_SJES_API_ITEM)
@RequestMapping(value = "specificationValues/anxian")
public interface SpecificationValueFeign {

    /**
     * 删除指定规格值 (同时删除子商品对应的规格值)
     *
     * @param id 规格id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void deleteSpecificationValue(@RequestParam("id") Long id);

}
