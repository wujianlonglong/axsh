package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.UserModel;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "")
    public String list(Model model, Principal principal) {

        // 没有登录或登录超时
        User user = userService.getByUserName(principal.getName());
        if (null == user) {
            return "redirect:/login";
        }

        getMenus(user, model);

        return "react/userList";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseMessage<Page<User>> list(@RequestBody UserParamDTO userParamDTO) {

        Pageable pageable = new PageRequest(userParamDTO.getPage() - 1, userParamDTO.getSize());

        return userService.list(userParamDTO.getWorkerId(), pageable);
    }


    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<User> getById(@RequestParam String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.error("参数为空！");
        }

        return userService.getById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(@RequestBody UserModel userModel, Principal principal) {
        if (null == userModel) {
            return ResponseMessage.error("用户对象为空!");
        }

        log.info(userModel.toString());

        String username = "system";
        if (principal != null) {
            username = principal.getName();
        }

        return userService.save(userModel, username);
    }


}
