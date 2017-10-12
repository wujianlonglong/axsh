package anxian.gateway.admin.module.business.service.impl;

import anxian.gateway.admin.module.business.model.item.ExtCategoryModel;
import anxian.gateway.admin.module.business.service.SjesCategoryService;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import client.api.category.CategoryApiClient;
import client.api.category.domain.Category;
import client.api.category.model.CategoryModel;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangzhe on 15-12-4.
 */
@Service
public class SjesCategoryServiceImpl implements SjesCategoryService {

    @Autowired
    private CategoryApiClient categoryApiClient;

    /**
     * 返回所有商品分类,处理过后的数据,有父子关系
     *
     * @return
     */
    @Override
    public List<ExtCategoryModel> roots(Integer type) {
        List<ExtCategoryModel> list = new ArrayList<>();

        List<CategoryModel> roots = categoryApiClient.findRoots();

        for (CategoryModel layerClassification : roots) {

            CategoryModel resultLayerClassification = null;
            if (type == 0) {//系统分类标签
                if (layerClassification.getType() == 0) {
                    resultLayerClassification = layerClassification;
                }
            } else {
                resultLayerClassification = layerClassification;
            }

            if (resultLayerClassification != null) {
                ExtCategoryModel layerCategory = new ExtCategoryModel();//一层
                layerCategory.setId(resultLayerClassification.getId().toString());
                layerCategory.setText(resultLayerClassification.getName());
                layerCategory.setGrade(resultLayerClassification.getGrade());
                List<CategoryModel> secondCategoryModels = resultLayerClassification.getCategoryModels();
                for (CategoryModel twoLayerClassification : secondCategoryModels) {
                    ExtCategoryModel twoLayCategory = new ExtCategoryModel();//二层
                    twoLayCategory.setId(twoLayerClassification.getId().toString());
                    twoLayCategory.setText(twoLayerClassification.getName());
                    twoLayCategory.setGrade(twoLayerClassification.getGrade());
                    List<CategoryModel> thirdCategoryModels = twoLayerClassification.getCategoryModels();
                    for (CategoryModel threeLayerClassification : thirdCategoryModels) {
                        ExtCategoryModel threeLayCategory = new ExtCategoryModel();//三层
                        threeLayCategory.setId(threeLayerClassification.getId().toString());
                        threeLayCategory.setText(threeLayerClassification.getName());
                        threeLayCategory.setGrade(threeLayerClassification.getGrade());
                        twoLayCategory.getItems().add(threeLayCategory);
                    }
                    layerCategory.getItems().add(twoLayCategory);
                }

                list.add(layerCategory);
            }

        }

        return list;
    }

    /**
     * 根据父分类得到子分类
     *
     * @param parentId
     * @return
     */
    @Override
    public List<ExtCategoryModel> childrenCategoryList(Long parentId) {
        List<Category> children = categoryApiClient.findChildren(parentId);
        List<ExtCategoryModel> list = new ArrayList<>();
        for (Category child : children) {
            ExtCategoryModel extCategoryModel = new ExtCategoryModel();
            extCategoryModel.setId(child.getId().toString());
            extCategoryModel.setText(child.getName());
            extCategoryModel.setType(child.getType());
            list.add(extCategoryModel);
        }

        return list;
    }

    /**
     * 根据类别ID,返回它自己,父类,爷爷类
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<Category> findClusters(Long categoryId) {
        return categoryApiClient.findClusters(categoryId);
    }

    /**
     * 分页查询分类列表
     *
     * @param searchCoditionModel 查询条件
     * @return 分页分类列表
     */
    @Override
    public PageModel<Category> search(SearchCoditionModel<Category> searchCoditionModel) {
        return categoryApiClient.search(searchCoditionModel);
    }

    /**
     * 保存商品分类
     *
     * @param category
     */
    @Override
    public Category save(Category category) {
        return categoryApiClient.save(category);
    }

    /**
     * 修改分类名
     *
     * @param category
     * @return
     */
    @Override
    public int updateCategory(Category category) {
        return categoryApiClient.updateCategory(category);
    }

    /**
     * 修改分类标签
     *
     * @param category
     * @return
     */
    @Override
    public int updateCategoryTag(Category category) {
        return categoryApiClient.updateCategoryTag(category);
    }

    /**
     * 查询指定实体
     *
     * @param id 主键
     * @return 实体
     */
    @Override
    public Category findOne(Long id) {
        return categoryApiClient.findOne(id);
    }

    /**
     * 所在分类赋值
     *
     * @param old
     * @return
     */
    @Override
    public PageModel<Category> getNewPageMode(PageModel<Category> old, boolean onlyThird) {
        List<Category> hasClassificationList = Lists.newArrayList();
        for (Category category : old.getContent()) {
            if (category.getGrade() > 1) {
                List<Category> categoryList = categoryApiClient.findClusters(category.getId());
                String classification = "";//所在分类
                for (Category category1 : categoryList) {
                    if (onlyThird && category.getGrade() == 3) {
                        if (classification.equals("")) {
                            classification += category1.getName();
                        } else if (category1 != null) {
                            classification += "-->" + category1.getName();
                        }
                    } else if (category1.getGrade() != category.getGrade()) {
                        if (classification.equals("")) {
                            classification += category1.getName();
                        } else {
                            classification += "-->" + category1.getName();
                        }
                    }

                }

                category.setClassification(classification);
            }
            hasClassificationList.add(category);

        }

        PageModel<Category> newPageModel = new PageModel<>(hasClassificationList, old.getTotal(), old.getPageable());

        return newPageModel;
    }

    /**
     * 根据分类级别查询分类列表信息
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    public List<Category> listByGrade(Integer grade) {
        return categoryApiClient.findByGrade(grade);
    }


    /**
     * 根据分类级别查询分类列表信息,在分类新增中使用
     *
     * @param grade 分类级别
     * @return 分类列表信息
     */
    public List<ExtCategoryModel> findByGrade(Integer grade) {
        List<Category> children = categoryApiClient.findByGrade(grade);
        List<ExtCategoryModel> list = new ArrayList<>();
        for (Category child : children) {
            ExtCategoryModel extCategoryModel = new ExtCategoryModel();
            extCategoryModel.setId(child.getId().toString());
            extCategoryModel.setText(child.getName());
            extCategoryModel.setType(child.getType());
            list.add(extCategoryModel);
        }

        return list;
    }

    /**
     * 根据主键id删除指定的分类
     *
     * @param id 主键id
     * @return 显示数量
     */
    public void delete(Long id) {
        categoryApiClient.delete(id);
    }

    @Override
    public ResponseMessage saveAnxian(Category category) {
        return categoryApiClient.saveAnxian(category);
    }

    @Override
    public ResponseMessage updateCategoryAnxian(Category category) {
        return categoryApiClient.updateCategoryAnxian(category);
    }
}
