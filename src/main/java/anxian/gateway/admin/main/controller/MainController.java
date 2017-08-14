package anxian.gateway.admin.main.controller;

import anxian.gateway.admin.module.base.domain.AclUser;
import anxian.gateway.admin.module.base.domain.Authority;
import anxian.gateway.admin.module.base.domain.UserAuthority;
import anxian.gateway.admin.module.base.model.LeftTreeParent;
import anxian.gateway.admin.module.base.service.AclUserService;
import anxian.gateway.admin.module.security.UserContext;
import anxian.gateway.admin.module.security.UserContextHelper;
import anxian.gateway.admin.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 关于系统一级的控制器
 * Created by jiangzhe on 15-11-3.
 */
@Controller
public class MainController {

    @Autowired
    private AclUserService aclUserService;

    /**
     * 系统首页
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    /**
     * 系统首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String indexHtml() {
        return "index";
    }

    /**
     * 系统左侧菜单
     *
     * @return
     */
    @RequestMapping("/leftTree")
    @ResponseBody
    public List<LeftTreeParent> leftTreeLoad() {
        UserContext userContext = UserContextHelper.getUserContext();

        return userContext.getLeftTree();
    }

    /**
     * 返回当前登陆用户的所有权限,在列表页的工具条加载用
     *
     * @return
     */
    @RequestMapping("/toolbar")
    @ResponseBody
    public Set<String> topToolbar() {
        Set<String> authorities = new HashSet<>();
        List<UserAuthority> userAuthorityList = UserContextHelper.getUserContext().getUserAuthorities();
        for (UserAuthority userAuthority : userAuthorityList) {
            Authority authority = userAuthority.getAuthority();
            authorities.add(authority.getAuthority());
        }

        return authorities;
    }

    /**
     * 判断用户是否有具体的某个权限
     *
     * @param authority 权限名称
     * @return
     */
    @RequestMapping("/hasAuthority")
    @ResponseBody
    public Boolean hasAuthority(String authority) {
        for (UserAuthority userAuthority : UserContextHelper.getUserContext().getUserAuthorities()) {//循环所有权限
            if (userAuthority.getAuthority().getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到当前登陆用户全名,在首页右上角显示用
     *
     * @return
     */
    @RequestMapping("/currentUser")
    @ResponseBody
    public Map currentUser() {
        Map map = new HashMap<>();
        map.put("fullname", UserContextHelper.getUserContext().getUser().getFullName());
        return map;
    }

    /**
     * 得到当前登陆用户部门编号
     *
     * @return
     */
    @RequestMapping("/currentUserOrg")
    @ResponseBody
    public Map currentUserOrg() {
        Map map = new HashMap<>();
        AclUser aclUser = UserContextHelper.getUserContext().getUser();
        if (aclUser != null) {
            if (aclUser.getOrg() != null) {
                map.put("org", aclUser.getOrg().getOrgNum());
            } else {
                map.put("org", "");
            }
        } else {
            map.put("org", "");
        }
        return map;
    }

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 修改密码,密码加密格式为:MD5加密函数( 明文密码{用户名} )
     *
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @return
     */
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public Map updatePwd(String oldPwd, String newPwd) {
        Map map = new HashMap<>();

        AclUser currentUser = UserContextHelper.getUserContext().getUser();

        String currentPwd = currentUser.getPassword();

        if (!currentPwd.equals(PasswordUtil.generatePassword(oldPwd, currentUser.getUsername()))) {//旧密码与新密码不一致
            map.put("state", "atypism");
        } else {
            try {
                aclUserService.updatePwd(PasswordUtil.generatePassword(newPwd, currentUser.getUsername()), currentUser.getId());
                map.put("state", "success");//修改成功
            } catch (Exception e) {
                e.getStackTrace();
                map.put("state", "error");//修改失败
            }
        }

        return map;
    }
}
