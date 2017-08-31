package anxian.gateway.admin.module.business.controller.webpage;


import anxian.gateway.admin.module.base.controller.BaseController;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping(value = "/anXian")
public class WebPage extends BaseController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/good/goodsUpdown", method = RequestMethod.GET)
    public String goodsUpdown(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "/anXian-goods/up-down-shelves";
    }


    @RequestMapping(value = "/good/goodsTimeUpdown", method = RequestMethod.GET)
    public String goodsTimeUpdown(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "/anXian-goods/timer-up-down-shelves";
    }


    @RequestMapping(value = "/user/userList", method = RequestMethod.GET)
    public String userList(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "/anXian-user/user";
    }

    @RequestMapping(value = "/user/blackList", method = RequestMethod.GET)
    public String blackList(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "/anXian-user/blacklist";
    }

    @RequestMapping(value = "/pay/payList", method = RequestMethod.GET)
    public String payList(Model model, Principal principal) {

        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "/pay/pay";
    }

}
