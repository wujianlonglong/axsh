package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.service.NewMenuService;
import anxian.gateway.admin.module.base.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

/**
 * 首页
 */
@Controller
public class HomeController {

    @Autowired
    private NewMenuService newMenuService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Principal principal, HttpSession httpSession, Model model) {

        // 没有登录或登录超时
        if (null == principal || null == httpSession) {
            return "redirect:/login";
        }

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        NewRole newRole = user.getNewRole();
        List<MenuModel> menuModelList = newMenuService.showMenu(newRole.getNewMenus());
        if (CollectionUtils.isEmpty(menuModelList)) {
//            return "redirect:/login";
        }
        model.addAttribute("menus", menuModelList);
        model.addAttribute("user", principal.getName());

        return "anXian-goods/info-maintain";
    }
}
