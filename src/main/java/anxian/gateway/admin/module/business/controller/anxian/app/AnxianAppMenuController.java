package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.utils.JsonMsg;
import client.api.anxian.app.AnXianAppMenuFeign;
import client.api.app.floor.model.EntryIconModel;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@Controller
@RequestMapping("anxian/appMenu")
public class AnxianAppMenuController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AnXianAppMenuFeign anXianAppMenuFeign;

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<EntryIconModel> list(int page, int limit) {
        List<EntryIconModel> list = anXianAppMenuFeign.list(null);
        return new PageModel<>(list, list.size(), new Pageable(page, limit));
    }

    /**
     * 根据id得到指定的 EntryIconModel
     *
     * @param id 主键id
     * @return EntryIconModel
     */
    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonMsg getEntryIconModel(@PathVariable("id") Long id) {
        JsonMsg jsonMsg = new JsonMsg();
        jsonMsg.setData(anXianAppMenuFeign.getEntryIconModel(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 修改菜单
     *
     * @param entryIconModel 楼层信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(EntryIconModel entryIconModel) {
        anXianAppMenuFeign.updateEntryIcon(entryIconModel);
        return JsonMsg.success("修改成功");
    }

    /**
     * 新增菜单
     * @param entryIconModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonMsg add(EntryIconModel entryIconModel) {
        anXianAppMenuFeign.addEntryIcon(entryIconModel);
        return JsonMsg.success("添加成功");
    }

    /**
     * 删除菜单
     *
     * @param menuId 菜单id
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE)
    public JsonMsg deleteMenu(@RequestParam(value = "menuId") Long menuId) {
        try {
            anXianAppMenuFeign.deleteEntryIcon(menuId);
        } catch (Exception e) {
            return JsonMsg.failure("删除菜单失败");
        }
        return JsonMsg.success("删除菜单成功");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String menu(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "anXian-APP/menu";
    }

    @RequestMapping("/ajaxMenu")
    public String ajaxMenu(Model model, int page, int limit,String shopId, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        List<EntryIconModel> list = anXianAppMenuFeign.list(shopId);
        PageModel<EntryIconModel> menus = new PageModel<>(list, list.size(), new Pageable(page, limit));
        List<EntryIconModel> content = new ArrayList<>();
        int currIdx = page * limit;
        for (int i = 0; i < limit && i < list.size() - currIdx; i++) {
            EntryIconModel menu = list.get(currIdx + i);
            content.add(menu);
        }
        menus.getContent().clear();
        menus.getContent().addAll(content);
        model.addAttribute("page", page + 1);
        model.addAttribute("totalPages",menus.getTotalPages());
        model.addAttribute("menus", menus);
        return "anXian-APP/menu-ajax";
    }
}
