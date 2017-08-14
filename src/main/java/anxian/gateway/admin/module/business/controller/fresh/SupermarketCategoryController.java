package anxian.gateway.admin.module.business.controller.fresh;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.fresh.feign.SupermarketCategoryFeign;
import client.api.fresh.model.SupermarketCategory;
import client.api.fresh.model.SupermarketCategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinhailong on 16-1-4.
 */
@RestController
@RequestMapping("supermarketCategory")
public class SupermarketCategoryController {

    @Autowired
    private SupermarketCategoryFeign supermarketCategoryFeign;

    /**
     * 根据主键id得到SupermarketCategoryModel对象
     *
     * @param id 主键id
     * @return SupermarketCategoryModel
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg get(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(supermarketCategoryFeign.get(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 新增超市页分类
     *
     * @param supermarketCategoryModel 超市页分类
     * @return 超市页分类
     */
    @RequestMapping(method = RequestMethod.POST)
    public JsonMsg save(SupermarketCategoryModel supermarketCategoryModel) {
        supermarketCategoryFeign.save(supermarketCategoryModel);
        return JsonMsg.success("保存成功");
    }


    /**
     * 更新超市页分类
     *
     * @param supermarketCategory 超市页分类
     * @return 修改数目
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(SupermarketCategory supermarketCategory) {
        supermarketCategoryFeign.update(supermarketCategory);
        return JsonMsg.success("更新成功");
    }

    /**
     * 根据指定主键id删除
     *
     * @param id 主键id
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public JsonMsg delete(Long id) {
        supermarketCategoryFeign.delete(id);
        return JsonMsg.success("删除成功");
    }
}
