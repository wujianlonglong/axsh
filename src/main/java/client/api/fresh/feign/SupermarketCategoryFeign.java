package client.api.fresh.feign;

import client.api.constants.Constants;
import client.api.fresh.model.SupermarketCategory;
import client.api.fresh.model.SupermarketCategoryModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mac on 15/9/24.
 */
@FeignClient(Constants.SJES_API_FRESH)
@RequestMapping("supermarketCategorys")
public interface SupermarketCategoryFeign {

    /**
     * 根据主键id得到SupermarketCategoryModel对象
     *
     * @param id 主键id
     * @return SupermarketCategoryModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    SupermarketCategoryModel get(@PathVariable("id") Long id);

    /**
     * 新增超市页分类
     *
     * @param supermarketCategoryModel 超市页分类
     * @return 超市页分类
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SupermarketCategory save(SupermarketCategoryModel supermarketCategoryModel);


    /**
     * 更新超市页分类
     *
     * @param supermarketCategory 超市页分类
     * @return 修改数目
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int update(SupermarketCategory supermarketCategory);

    /**
     * 根据指定主键id删除
     *
     * @param id 主键id
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

}
