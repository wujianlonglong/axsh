package anxian.gateway.admin.module.business.controller.www;

import anxian.gateway.admin.module.business.controller.BaseController;
import anxian.gateway.admin.module.business.service.SjesCategoryService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.category.domain.Category;
import client.api.constants.Constants;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.www.feign.CategoryAdvertisementFeign;
import client.api.www.model.CategoryAdvertisement;
import client.api.www.model.CategoryAdvertisementCountModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by qinhailong on 15-12-30.
 */
@RestController
@RequestMapping("categoryAdvertisement")
public class CategoryAdvertisementContoller extends BaseController {

    @Autowired
    private CategoryAdvertisementFeign categoryAdvertisementFeign;

    @Autowired
    private SjesCategoryService sjesCategoryService;

    /**
     * 分类广告计数
     *
     * @return 分类广告计数列表
     */
    @RequestMapping(value = "countByCategoryIds", method = RequestMethod.GET)
    public PageModel<CategoryAdvertisementCountModel> list(int page, int limit) {
        List<Category> categories = sjesCategoryService.listByGrade(Constants.CategoryGradeConstants.GRADE_ONE);
        List<Long> categoryIds = Lists.newArrayList();
        categories.forEach(category -> {
            categoryIds.add(category.getId());
        });

        List<CategoryAdvertisementCountModel> categoryAdvertisementCountModels = categoryAdvertisementFeign.countByCategoryIds(categoryIds);
        for (CategoryAdvertisementCountModel categoryAdvertisementCountModel : categoryAdvertisementCountModels) {
            categoryAdvertisementCountModel.setCategoryName(sjesCategoryService.findOne(categoryAdvertisementCountModel.getCategoryId()).getName());
        }

        return new PageModel<>(categoryAdvertisementCountModels, categoryAdvertisementCountModels.size(), new Pageable(page, limit));
    }

    /**
     * 保存或修改分类广告
     *
     * @param categoryAdvertisement 分类广告
     * @return 分类广告
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg saveOrUpdate(CategoryAdvertisement categoryAdvertisement) {
        categoryAdvertisementFeign.save(categoryAdvertisement);
        return JsonMsg.success("保存成功");
    }

    /**
     * 根据分类Id和类型查询列表
     *
     * @param categoryId 分类Id
     * @param type       类型
     * @return 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<CategoryAdvertisement> listByCategoryIdAndType(Long categoryId, Integer type, int page, int limit) {
        List<CategoryAdvertisement> categoryAdvertisements = categoryAdvertisementFeign.listByCategoryIdAndType(categoryId, type);
        return new PageModel<>(categoryAdvertisements, categoryAdvertisements.size(), new Pageable(page, limit));
    }

    /**
     * 根据指定id删除
     *
     * @param id 主键id
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        categoryAdvertisementFeign.delete(id);
        return JsonMsg.success("删除成功");
    }

}
