package anxian.gateway.admin.module.business.controller.anxian.app;

import anxian.gateway.admin.utils.JsonMsg;
import client.api.app.floor.feign.AppMenuFeign;
import client.api.app.floor.model.AdItemTempleteModel;
import client.api.app.floor.model.EntryIconModel;
import client.api.app.version.domain.Version;
import client.api.item.model.PageModel;
import client.api.item.model.Pageable;
import client.api.user.utils.page.SjesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by byinbo on 2016/12/6.
 */
@Controller
@RequestMapping("anxian/appMenu")
public class AnxianAppMenuController {

    @Autowired
    AppMenuFeign appMenuFeign;

    /**
     * 楼层列表
     *
     * @return 楼层列表
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public PageModel<EntryIconModel> list(int page, int limit) {
        List<EntryIconModel> list = appMenuFeign.list();
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
        jsonMsg.setData(appMenuFeign.getEntryIconModel(id));
        jsonMsg.setSuccess(true);
        return jsonMsg;
    }

    /**
     * 修改楼层
     *
     * @param entryIconModel 楼层信息
     * @return 修改数目
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonMsg update(EntryIconModel entryIconModel) {
        appMenuFeign.updateEntryIcon(entryIconModel);
        return JsonMsg.success("修改成功");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String menu(Model model) {
        return "anXian-APP/menu";
    }

    @RequestMapping("/ajaxMenu")
    public String ajaxMenu(Model model, int page, int limit) {
        List<EntryIconModel> list = appMenuFeign.list();
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
        model.addAttribute("menus", menus);
        return "anXian-APP/menu-ajax";
    }
}
