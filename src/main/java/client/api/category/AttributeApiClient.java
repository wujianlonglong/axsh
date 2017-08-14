package client.api.category;

import client.api.category.domain.Attribute;
import client.api.category.model.AttributeModel;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Jianghe on 15/12/15.
 */
@FeignClient(Constants.SJES_API_CATEGORY)
@RequestMapping(value = "/attributes")
public interface AttributeApiClient {

    /**
     * 分页查询属性列表
     *
     * @param searchCoditionModel 分页查询条件
     * @return 分页列表
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Attribute> search(SearchCoditionModel<Attribute> searchCoditionModel);

    /**
     * 保存商品属性
     *
     * @param attributeModel 商品属性
     * @return 商品属性
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void saveAttributeModel(AttributeModel attributeModel);

    /**
     * 根据主键更新商品属性
     *
     * @param attributeModel 商品属性
     * @return 更新条数
     */
    @RequestMapping(value = "/updateAttributeModel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateAttributeModel(AttributeModel attributeModel);

    /**
     * 根据主键查询指定的属性信息
     *
     * @param id 主键id
     * @return 属性信息
     */
    @RequestMapping(value = "{id}/attributeOptions", method = RequestMethod.GET)
    AttributeModel findAttributeModelById(@PathVariable("id") Long id);

    /**
     * 根据id删除指定的属性
     *
     * @param id 主键id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

}
