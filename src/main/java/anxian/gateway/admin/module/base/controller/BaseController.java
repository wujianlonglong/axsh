package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.service.NewMenuService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.List;

public class BaseController {

    @Autowired
    private NewMenuService newMenuService;

    public void getMenus(User user, Model model) {

        NewRole newRole = user.getNewRole();
        List<MenuModel> menuModelList = newMenuService.showMenu(newRole.getNewMenus());
        if (CollectionUtils.isEmpty(menuModelList)) {
//            return "redirect:/login";
        }
        model.addAttribute("menus", menuModelList);
        model.addAttribute("user", user.getUsername());
    }
}
