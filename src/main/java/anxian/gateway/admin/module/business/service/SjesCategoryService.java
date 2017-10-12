package anxian.gateway.admin.module.business.service;

import anxian.gateway.admin.module.business.model.item.ExtCategoryModel;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.category.domain.Category;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;

import java.util.List;

/**
 * 商品分类服务器接口
 * Created by jiangzhe on 15-12-4.
 */
public interface SjesCategoryService {

    /**
     * 返回所有商品分类,处理过后的数据,有父子关系
     *
     * @return
     */
    List<ExtCategoryModel> roots(Integer type);

    /**
     * 根据父分类得到子分类
     *
     * @param parentId
     * @return
     */
    List<ExtCategoryModel> childrenCategoryList(Long parentId);

    /**
     * 根据类别ID,返回它自己,父类,爷爷类
     *
     * @param categoryId
     * @return
     */
    List<Category> findClusters(Long categoryId);

    /**
     * 分页查询分类列表
     *
     * @param searchCoditionModel 查询条件
     * @return 分页分类列表
     */
    PageModel<Category> search(SearchCoditionModel<Category> searchCoditionModel);

    /**
     * 保存商品分类
     *
     * @param category
     */
//    @PreAuthorize("hasAuthority('COMMODITYCATEGORY_SAVE')")
    Category save(Category category);

    /**
     * 所在分类赋值
     *
     * @param old
     * @return
     */
    PageModel<Category> getNewPageMode(PageModel<Category> old, boolean onlyThird);

    /**
     * 修改分类名
     *
     * @param category
     * @return
     */
//    @PreAuthorize("hasAuthority('COMMODITYCATEGORY_SAVE')")
    int updateCategory(Category category);

    /**
     * 修改分类标签
     *
     * @param category
     * @return
     */
//    @PreAuthorize("hasAuthority('DISPLAYLABEL_SAVE')")
    int updateCategoryTag(Category category);

    /**
     * 查询指定实体
     *
     * @param id 主键
     * @return 实体
     */
    Category findOne(Long id);

    /**
     * 根据分类级别查询分类列表信息
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    List<Category> listByGrade(Integer grade);

    /**
     * 根据分类级别查询分类列表信息,在分类新增中使用
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    List<ExtCategoryModel> findByGrade(Integer grade);

    /**
     * 根据主键id删除指定的分类
     *
     * @param id 主键id
     * @return 显示数量
     */
    void delete(Long id);

    /**
     * 保存商品分类
     * @param category
     */
    ResponseMessage saveAnxian(Category category);

    /**
     * 修改分类名
     * @param category
     * @return
     */
    ResponseMessage updateCategoryAnxian(Category category);

}
