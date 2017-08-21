package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewRole;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.MenuModel;
import anxian.gateway.admin.module.base.service.NewMenuService;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return "common/home";
    }


    /**
     * 修改密码
     *
     * @param principal
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMessage updatePassword(Principal principal, String oldPwd, String newPwd) {

        if (null == principal) {
            return ResponseMessage.error("请重新刷新页面，进行登录");
        }

        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd)) {
            return ResponseMessage.error("参数校验不能为空！");
        }

        String username = principal.getName();
        return userService.updatePassword(newPwd, oldPwd, username);
    }
}
