package client.api.www.feign;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.constants.Constants;
import client.api.www.model.CategoryProduct;
import client.api.www.model.FloorCategory;
import client.api.www.model.FloorCategoryModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by mac on 15/9/23.
 */
@FeignClient(Constants.SJES_API_WWW)
@RequestMapping("floorCategorys")
public interface FloorCategoryFeign {

    /**
     * 根据id得到楼层分类信息
     *
     * @param id 主键id
     * @return
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    FloorCategoryModel get(@PathVariable("id") Long id);

    /**
     * 根据id得到楼层分类商品列表
     *
     * @param id 主键id
     * @return 分类商品列表
     */
    @RequestMapping(value = "{id}/products", method = RequestMethod.GET)
    List<CategoryProduct> getCategoryproductsById(@PathVariable("id") Long id);

    /**
     * 新增楼层分类信息
     *
     * @param floorCategoryModel 楼层分类信息
     * @return 楼层分类信息
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    FloorCategory save(FloorCategoryModel floorCategoryModel);

    /**
     * 更新楼层分类信息
     *
     * @param floorCategoryModel 楼层分类信息
     * @return 修改数目
     */
//    @PreAuthorize("hasAuthority('FLOOR_SAVE')")
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(FloorCategoryModel floorCategoryModel);

    /**
     * 根据主键删除
     *
     * @param id 主键
     */
//    @PreAuthorize("hasAuthority('FLOOR_DELETE')")
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);

    /**
     * 导入楼层分类商品
     *
     * @param categoryId 楼层分类categoryId
     * @param sns        商品编码
     * @return 是否成功
     */
    @RequestMapping(value = "importProducts/{categoryId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseMessage importProducts(@PathVariable("categoryId") Long categoryId, List<String> sns);
}
