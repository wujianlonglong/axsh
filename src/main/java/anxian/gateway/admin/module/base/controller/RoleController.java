package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.model.NewRoleModel;
import anxian.gateway.admin.module.base.service.NewRoleService;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    private NewRoleService newRoleService;

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

        return "react/roleList";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseMessage list(@RequestBody RoleParamDTO roleParamDTO) {

        Pageable pageable = new PageRequest(roleParamDTO.getPage() - 1, roleParamDTO.getSize(), Sort.Direction.ASC, "sort");

        return newRoleService.list(roleParamDTO.getRoleName(), pageable);
    }


    /**
     * 根据ID展示菜单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage getById(@RequestParam String id) {
        if (StringUtils.isEmpty(id)) {
            return ResponseMessage.error("参数为空！");
        }

        return newRoleService.getById(id);
    }

    /**
     * 保存角色信息
     *
     * @param newRoleModel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(@RequestBody NewRoleModel newRoleModel, Principal principal) {
        if (null == newRoleModel) {
            return ResponseMessage.error("角色对象为空!");
        }

        log.info("保存角色的对象：{}", newRoleModel.toString());

        String username = "system";
        if (principal != null) {
            username = principal.getName();
        }

        return newRoleService.save(newRoleModel, username);
    }
}
