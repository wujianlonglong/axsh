package client.api.category;

import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.category.domain.Category;
import client.api.category.model.CategoryModel;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jiangzhe on 15-12-4.
 */
@FeignClient(Constants.SJES_API_CATEGORY)
@RequestMapping(value = "/categorys")
public interface CategoryApiClient {

    /**
     * 查询分类列表
     *
     * @param category 查询条件
     * @return 分类列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    List<Category> list(Category category);

    /**
     * 查找顶级商品分类
     *
     * @return 顶级商品分类
     */
    @RequestMapping(value = "/roots", method = RequestMethod.GET)
    List<CategoryModel> findRoots();

    /**
     * 查找下级商品分类
     *
     * @param parentId 父类Id
     * @return 下级商品分类
     */
    @RequestMapping(value = "/children", method = RequestMethod.GET)
    List<Category> findChildren(@RequestParam("parentId") Long parentId);

    /**
     * 查找顶级商品分类
     *
     * @param id 商品分类id
     * @return 顶级商品分类
     */
    @RequestMapping(value = "/parents", method = RequestMethod.GET)
    CategoryModel findParents(Long id);

    /**
     * 根据分类id查询分类簇对象
     *
     * @param categoryId 　分类id
     * @return　分类簇对象
     */
    @RequestMapping(value = "/clusters", method = RequestMethod.GET)
    List<Category> findClusters(@RequestParam("categoryId") Long categoryId);


    /**
     * 分页查询分类列表
     *
     * @param searchCoditionModel 查询条件
     * @return 分页分类列表
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageModel<Category> search(SearchCoditionModel<Category> searchCoditionModel);


    /**
     * 保存商品分类
     *
     * @param category
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Category save(Category category);

    /**
     * 修改分类名
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/updateCategory", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int updateCategory(Category category);

    /**
     * 保存商品分类
     * @param category
     */
    @RequestMapping(value = "/save/anxian", method = RequestMethod.POST)
    ResponseMessage saveAnxian(@RequestBody Category category);

    /**
     * 修改分类名
     * @param category
     * @return
     */
    @RequestMapping(value = "/updateCategory/anxian", method = RequestMethod.PUT)
    ResponseMessage updateCategoryAnxian(@RequestBody Category category);

    /**
     * 修改分类标签
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/updateCategoryTag", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    int updateCategoryTag(Category category);

    /**
     * 查询指定实体
     *
     * @param id 主键
     * @return 实体
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    Category findOne(@PathVariable("id") Long id);

    /**
     * 根据分类级别查询分类列表信息
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    @RequestMapping(value = "grade", method = RequestMethod.GET)
    List<Category> findByGrade(@RequestParam("grade") Integer grade);

    /**
     * 根据主键id删除指定的分类
     *
     * @param id 主键id
     * @return 显示数量
     */
    @RequestMapping(method = RequestMethod.DELETE)
    void delete(@RequestParam("id") Long id);


    /**
     * 根据ids查询分类列表
     *
     * @param categoryIds 分类ids
     * @return 分类列表
     */
    @RequestMapping(value = "listByIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Category> listByIds(@RequestBody List<Long> categoryIds);
}
