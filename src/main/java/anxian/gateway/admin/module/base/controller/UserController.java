package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping(value = "/users")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/listPage")
    public String list(Model model, Principal principal) {

        // 没有登录或登录超时
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "system/user";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public Page<User> list(@RequestBody UserParamDTO userParamDTO) {

        Pageable pageable = new PageRequest(userParamDTO.getPage() - 1, userParamDTO.getSize());

        return userService.list(userParamDTO.getWorkerId(), pageable);
    }


}
