package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.NewMenuService;
import anxian.gateway.admin.module.base.service.UserService;
import anxian.gateway.admin.module.common.domain.ResponseMessage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    @Autowired
    private NewMenuService newMenuService;

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

        return "react/menuList";
    }

    /**
     * 查询父菜单
     *
     * @return
     */
    @RequestMapping(value = "/parentMenus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<List<NewMenu>> parentMenus() {

        return newMenuService.getParentMenus();
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseMessage list(@RequestBody MenuParamDTO menuParamDTO) {

        Pageable pageable = new PageRequest(menuParamDTO.getPage() - 1, menuParamDTO.getSize(), Sort.Direction.ASC, "sort");

        return newMenuService.list(menuParamDTO.getMenuName(), pageable);
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

        return newMenuService.getById(id);
    }

    /**
     * 保存菜单信息
     *
     * @param newMenu
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(@RequestBody NewMenu newMenu) {
        if (null == newMenu) {
            return ResponseMessage.error("菜单对象为空!");
        }
        return newMenuService.save(newMenu);
    }
}
