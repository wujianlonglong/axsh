package anxian.gateway.admin.module.business.controller.product;

import anxian.gateway.admin.module.business.model.item.ExtCategoryModel;
import anxian.gateway.admin.module.business.service.SjesCategoryService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.category.domain.Category;
import client.api.constants.Constants;
import client.api.item.ProductFeign;
import client.api.item.domain.Product;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类控制器
 * Created by jiangzhe on 15-12-4.
 */
@RestController
@RequestMapping("/sjes_category")
public class SjesCategoryController {

    @Autowired
    private SjesCategoryService sjesCategoryService;

    @Autowired
    private ProductFeign productFeign;

    /**
     * 返回所有分类
     *
     * @return
     */
    @RequestMapping("/extCategory")
    public List<ExtCategoryModel> extCategory(Integer type) {
        return sjesCategoryService.roots(type);
    }


    /**
     * 分类列表
     *
     * @return 分类列表
     */
    @RequestMapping("/layerCategoryList")
    public JsonMsg layerCategoryList(Long parentId, Long currentId) {
        List<ExtCategoryModel> exceptOwnList = Lists.newArrayList();

        List<ExtCategoryModel> extCategoryModels = sjesCategoryService.childrenCategoryList(parentId);

        for (ExtCategoryModel extCategoryModel : extCategoryModels) {
            if (currentId != null) {
                if (!extCategoryModel.getId().equals(currentId.toString())) {
                    exceptOwnList.add(extCategoryModel);
                }
            } else {
                exceptOwnList.add(extCategoryModel);
            }
        }

        return new JsonMsg(true, false, exceptOwnList, null, null);
    }


    /**
     * 根据分类级别查询分类列表信息
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    @RequestMapping(value = "/grade/{grade}/{type}", method = RequestMethod.GET)
    public JsonMsg findByGrade(@PathVariable("grade") Integer grade, @PathVariable("type") Integer type) {
        List<ExtCategoryModel> byGrade = sjesCategoryService.findByGrade(grade);
        List<ExtCategoryModel> result = Lists.newArrayList();
        if (type == 0) {//在分类维护添加表单中不显示自定义分类标签
            for (ExtCategoryModel extCategoryModel : byGrade) {
                if (extCategoryModel.getType() == 0) {
                    result.add(extCategoryModel);
                }
            }
        } else {
            result.addAll(byGrade);
        }

        return new JsonMsg(true, false, result, null, null);
    }


    /**
     * 根据分类id查询分类簇对象
     *
     * @param categoryId 　分类id
     * @return　分类簇对象
     */
    @RequestMapping(value = "/categorys", method = RequestMethod.GET)
    public List<Category> findClusters(Long categoryId) {
        return sjesCategoryService.findClusters(categoryId);
    }


    /**
     * @return 分页分类列表
     */
    @RequestMapping("/search")
    public PageModel<Category> search(int page, int limit, Long parentId) {
        Category searchCategory = new Category();
        searchCategory.setParentId(parentId);

        SearchCoditionModel<Category> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(searchCategory);

        return sjesCategoryService.getNewPageMode(sjesCategoryService.search(searchCoditionModel), false);

    }

    /**
     * 保存商品分类
     *
     * @param category
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonMsg saveCategory(Category category) {

        JsonMsg jsonMsg = new JsonMsg();
        if (sjesCategoryService.save(category) != null) {
            jsonMsg.setSuccess(true);
            jsonMsg.setMsg("保存成功");
        } else {
            jsonMsg.setFailure(true);
            jsonMsg.setMsg("保存失败");
        }

        return jsonMsg;
    }

    /**
     * 修改商品分类
     *
     * @param category
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonMsg updateCategory(Category category) {

        /*List<ExtCategoryModel> extCategoryModels = sjesCategoryService.childrenCategoryList(category.getId());
        if(!CollectionUtils.isEmpty(extCategoryModels)){
            return JsonMsg.success("此分类有子分类不能修改");
        }else{*/
        Long parentId = category.getParentId();
        if (null != parentId && category.getId().longValue() == parentId.longValue()) {
            return JsonMsg.failure("不能挂载在自己的分类下");
        }
        if (null == parentId && category.getGrade().intValue() != Constants.CategoryGradeConstants.GRADE_ONE) {

        }
        if (sjesCategoryService.updateCategory(category) > 0) {
            return JsonMsg.success("修改成功");
        } else {
            return JsonMsg.failure("修改失败");
        }
        // }

    }


    /**
     * 修改分类标签
     *
     * @param category
     * @return
     */
    @RequestMapping(value = "/updateCategoryTag", method = RequestMethod.POST)
    public JsonMsg updateCategoryTag(Category category) {
        JsonMsg jsonMsg = new JsonMsg();

        if (sjesCategoryService.updateCategoryTag(category) > 0) {
            jsonMsg.setSuccess(true);
            jsonMsg.setMsg("修改成功");
        } else {
            jsonMsg.setFailure(true);
            jsonMsg.setMsg("修改失败");
        }

        return jsonMsg;
    }


    /**
     * 查询指定实体
     *
     * @param id 主键
     * @return 实体
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg findCategoryById(@PathVariable("id") Long id) {
        return new JsonMsg(true, false, sjesCategoryService.findOne(id), null, null);
    }


    /**
     * 根据主键id删除指定的分类
     *
     * @param id 主键id
     * @return 显示数量
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public JsonMsg delete(@PathVariable("id") Long id, Integer type) {
        try {
            List<ExtCategoryModel> extCategoryModels = sjesCategoryService.childrenCategoryList(id);
            if (CollectionUtils.isEmpty(extCategoryModels)) {
                if (Constants.CategoryType.CATEGORY_TYPE == type) {
                    List<Product> products = productFeign.findByCategoryId(id);
                    if (CollectionUtils.isEmpty(products)) {
                        sjesCategoryService.delete(id);
                    } else {
                        return JsonMsg.failure("此分类有商品绑定，不能删除!");
                    }
                } else {
                    sjesCategoryService.delete(id);
                }
            } else {
                return JsonMsg.failure("此分类有子分类不能删除!");
            }
            return JsonMsg.success("删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("删除失败!");
        }
    }

    /**
     * 商品属性模板管理列表页
     *
     * @param page
     * @param limit
     * @param parentId
     * @param grade
     * @return
     */
    @RequestMapping("/attributeManagementList")
    public PageModel<Category> attributeManagementList(int page, int limit, Long parentId, Integer grade) {
        Category searchCategory = new Category();
        searchCategory.setParentId(parentId);
        searchCategory.setGrade(grade == null ? 3 : grade);
        SearchCoditionModel<Category> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(searchCategory);

        return sjesCategoryService.getNewPageMode(sjesCategoryService.search(searchCoditionModel), true);

    }


}
