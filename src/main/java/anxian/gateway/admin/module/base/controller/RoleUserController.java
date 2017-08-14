package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangzhe on 15-11-20.
 */
@RestController
@RequestMapping("/roleUser")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    /**
     * 用户与角色关联操作
     */
    @RequestMapping(value = "/relation", method = RequestMethod.POST)
    public Object relation(boolean isAdd, Long roleId, Long aclUserId) {
        return roleUserService.relation(isAdd, roleId, aclUserId);
    }
}
