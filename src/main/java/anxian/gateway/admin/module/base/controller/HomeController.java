package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.service.NewMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * 首页
 */
@Controller
public class HomeController {

    @Autowired
    private NewMenuService newMenuService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Principal principal, HttpSession httpSession, Model model) {

        // 没有登录或登录超时
        if (null == principal || null == httpSession) {
            return "redirect: login/index";
        }

        model.addAttribute("menus", newMenuService.showMenu());
        model.addAttribute("user", principal.getName());

        return "anXian-goods/info-maintain";
    }
}
