package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.NewAuthority;
import anxian.gateway.admin.module.base.domain.NewMenu;
import anxian.gateway.admin.module.base.domain.User;
import anxian.gateway.admin.module.base.service.NewAuthorityService;
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
@RequestMapping(value = "/authority")
public class AuthorityController extends BaseController {

    @Autowired
    private NewAuthorityService newAuthorityService;

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

        return "react/authorityList";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public ResponseMessage list(@RequestBody AuthorityParamDTO authorityParamDTO) {

        Pageable pageable = new PageRequest(authorityParamDTO.getPage() - 1, authorityParamDTO.getSize(), Sort.Direction.ASC, "sort");

        return newAuthorityService.list(authorityParamDTO.getAuthorityName(), pageable);
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

        return newAuthorityService.getById(id);
    }

    /**
     * 获取有效的权限列表
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/listByRole", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage listByRole() {
        return newAuthorityService.listByRole();
    }

    /**
     * 保存菜单信息
     *
     * @param newAuthority
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseMessage save(@RequestBody NewAuthority newAuthority, Principal principal) {
        if (null == newAuthority) {
            return ResponseMessage.error("菜单对象为空!");
        }

        String username = "system";
        if (principal != null) {
            username = principal.getName();
        }

        return newAuthorityService.save(newAuthority, username);
    }


}
