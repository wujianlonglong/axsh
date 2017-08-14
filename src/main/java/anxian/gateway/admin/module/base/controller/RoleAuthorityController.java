package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.service.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by jiangzhe on 15-11-19.
 */
@RestController
@RequestMapping("/roleauthority")
public class RoleAuthorityController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    /**
     * 关联操作
     */
    @RequestMapping(value = "/relation", method = RequestMethod.POST)
    public Object relation(boolean checked, Long roleId, Long authorityId) {
        return roleAuthorityService.relation(checked, roleId, authorityId);
    }

}
