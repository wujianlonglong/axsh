package anxian.gateway.admin.module.business.controller.anxian.product;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.business.service.SjesAttributeService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.category.domain.Attribute;
import client.api.category.model.AttributeGroupEnum;
import client.api.category.model.AttributeGroupModel;
import client.api.category.model.AttributeModel;
import client.api.item.model.PageModel;
import client.api.item.model.SearchCoditionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by Jianghe on 15/12/15.
 */
//@RestController
@Controller
@RequestMapping("/anxian/sjes_attribute")
public class AnxianAttributeController extends BaseController {

    @Autowired
    private SjesAttributeService sjesAttributeService;

    @Autowired
    private UserService userService;

    /**
     * 分页查询属性列表
     *
     * @param page       当前页码
     * @param limit      每页数量
     * @param categoryId 分类ID
     * @return
     */
    @RequestMapping("/attributeList")
    public PageModel<Attribute> list(int page, int limit, Long categoryId) {
        Attribute searchAttribute = new Attribute();
        SearchCoditionModel<Attribute> searchCoditionModel = new SearchCoditionModel<>();
        searchCoditionModel.setPage(page - 1);
        searchCoditionModel.setSize(limit);
        searchCoditionModel.setSearchCodition(searchAttribute);
        return sjesAttributeService.search(searchCoditionModel);
    }


    /**
     * 保存商品属性
     *
     * @param attributeModel 商品属性
     * @return 商品属性
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg saveAttributeModel(HttpServletRequest request, AttributeModel attributeModel) {
        String[] htmlAttributeOptions = request.getParameterValues("htmlAttributeOptions");
        try {
            sjesAttributeService.saveAttributeModel(attributeModel, htmlAttributeOptions);
            return JsonMsg.success("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("保存失败");
        }
    }

//    /**
//     * 根据分类Id得到AttributeGroupModel列表
//     *
//     * @param categoryId 分类Id
//     * @return AttributeGroupModel列表
//     */
//    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
//    public Map getAttributeModels(@PathVariable("categoryId") Long categoryId) {
//        return sjesAttributeService.getAttributeModels(categoryId);
//    }

    /**
     * 根据分类Id得到AttributeGroupModel列表
     *
     * @param categoryId 分类Id
     * @return AttributeGroupModel列表
     */
    @RequestMapping(value = "{categoryId}", method = RequestMethod.GET)
    public String getAttributeModels(@PathVariable("categoryId") Long categoryId, Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);


        model.addAttribute("categoryList", sjesAttributeService.getAttributeModels(categoryId));
        return "anXian-goods/attribute-maintain";
    }

    /**
     * 根据分类Id得到AttributeGroupModel列表
     *
     * @param categoryId 分类Id
     * @return AttributeGroupModel列表
     */
    @RequestMapping(value = "test/{categoryId}", method = RequestMethod.GET)
    @ResponseBody
    public Map getAttributeModelsTest(@PathVariable("categoryId") Long categoryId, Model model) {
        return sjesAttributeService.getAttributeModels(categoryId);
    }

    /**
     * 根据分类Id得到AttributeGroupModel列表,在商品修改页面使用
     *
     * @param categoryId 分类Id
     * @return
     */
    @RequestMapping(value = "/getAttributeModelsForProduct", method = RequestMethod.GET)
    @ResponseBody
    public List<AttributeGroupModel> getAttributeModelsForProduct(Long categoryId) {
        return sjesAttributeService.getAttributeModelsForProduct(categoryId);
    }

    /**
     * 取得所有属性组
     *
     * @return
     */
    @RequestMapping("/categoryAttributeGroups")
    @ResponseBody
    public List<Map> categoryAttributeGroups() {
        return AttributeGroupEnum.getAttributeGroupList();
    }

    /**
     * 修改的表单数据加载
     *
     * @param id
     * @param categoryId
     * @return
     */
    @RequestMapping("/toUpdateAttributeModel")
    @ResponseBody
    public JsonMsg toUpdateAttributeModel(Long id, Long categoryId, Long attributeGroupId) {
        JsonMsg jsonMsg = new JsonMsg(true, false, sjesAttributeService.getAttributeModel(id, categoryId, attributeGroupId), "", "");
        return jsonMsg;
    }


    /**
     * 根据主键更新商品属性
     *
     * @param attributeModel 商品属性
     * @return 更新条数
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonMsg updateAttributeModel(AttributeModel attributeModel) {
        try {
            sjesAttributeService.updateAttributeModel(attributeModel);
            return JsonMsg.success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("修改失败");
        }
    }

    /**
     * 根据主键id删除指定的分类
     *
     * @param id 主键id
     * @return 显示数量
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public JsonMsg delete(@PathVariable("id") Long id) {
        try {
            sjesAttributeService.delete(id);
            return JsonMsg.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonMsg.failure("删除失败");
        }
    }

}
