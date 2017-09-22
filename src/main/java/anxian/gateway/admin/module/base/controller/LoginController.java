package anxian.gateway.admin.module.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * 登录页面
 */
@Controller
public class LoginController {


    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String index(Principal principal, HttpSession httpSession) {
        // 登录用户跳到首页
        if (principal != null) {
            // TODO 首页
            return "redirect:/";
        }
        return "login/index";
    }


}
