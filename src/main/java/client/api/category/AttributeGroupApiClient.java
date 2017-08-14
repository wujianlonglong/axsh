package client.api.category;

import client.api.category.model.AttributeGroupModel;
import client.api.constants.Constants;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Jianghe on 15/12/16.
 */
@FeignClient(Constants.SJES_API_CATEGORY)
@RequestMapping(value = "/attributeGroups")
public interface AttributeGroupApiClient {

    /**
     * 根据分类Id得到AttributeGroupModel列表
     *
     * @param categoryId 分类Id
     * @return AttributeGroupModel列表
     */
    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
    List<AttributeGroupModel> getAttributeModels(@PathVariable("categoryId") Long categoryId);
}
