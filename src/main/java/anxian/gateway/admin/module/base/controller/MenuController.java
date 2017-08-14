package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.Menu;
import anxian.gateway.admin.module.base.model.MenuJson;
import anxian.gateway.admin.module.base.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 保存
     *
     * @param menu
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Menu menu) {
        Map map = new HashMap<>();
        try {
            menuService.save(menu);
            map.put("state", "success");
        } catch (Exception e) {
            e.getStackTrace();
            map.put("state", "error");
        }
        return map;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Object remove(String ids) {
        Map map = new HashMap<>();
        for (String id : ids.split(",")) {
            try {
                menuService.del(Long.valueOf(id));
                map.put("state", "success");
            } catch (Exception e) {
                map.put("state", "error");
            }
        }
        return map;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Menu menu) {
        Map map = new HashMap<>();
        try {
            menuService.save(menu);
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
        }
        return map;
    }


    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public Object list(int page, int limit, String text) {
        Map<Object, Object> filter = new HashMap<>();
        filter.put("text", text);
        Page<Menu> menuPage = menuService.selectPageList(page, limit, filter);
        List<MenuJson> menuJsonList = new ArrayList<>();
        for (Menu menu : menuPage.getContent()) {
            MenuJson menuJson = new MenuJson();
            menuJson.setId(menu.getId());
            menuJson.setText(menu.getText());
            menuJson.setParent(menu.isParent());
            menuJson.setExpanded(menu.isExpanded());
            menuJson.setLeaf(menu.isLeaf());
            menuJson.setUrl(menu.getUrl());
            menuJson.setParentMenuId(menu.getParentMenu() != null ? menu.getParentMenu().getId() : 0L);
            menuJson.setParentMenu(menu.getParentMenu() != null ? menu.getParentMenu().getText() : "");
            menuJsonList.add(menuJson);

        }
        Map map = new HashMap<>();
        map.put("success", true);
        map.put("total", menuPage.getTotalElements());
        map.put("items", menuJsonList);
        return map;
    }


    /**
     * 查询
     */
    @RequestMapping(value = "/viewById", method = RequestMethod.POST)
    public Object viewById(Long id) {
        Menu menu = menuService.findById(id);
        Menu menuJson = new Menu();
        menuJson.setText(menu.getText());
        menuJson.setUrl(menu.getUrl());
        return menuJson;
    }

    /**
     * 菜单树
     */
    @RequestMapping("/loadTree")
    public Object loadTree() {
        return menuService.loadTree();
    }
}
