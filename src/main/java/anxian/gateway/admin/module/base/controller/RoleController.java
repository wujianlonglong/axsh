package anxian.gateway.admin.module.base.controller;

import anxian.gateway.admin.module.base.domain.Role;
import anxian.gateway.admin.module.base.service.AuthorityService;
import anxian.gateway.admin.module.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-13.
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(Role role) {
        return roleService.save(role);
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public Object remove(String ids) {
        Map map = new HashMap<>();
        for (String id : ids.split(",")) {
            try {
                roleService.del(Long.valueOf(id));
                map.put("state", "success");
            } catch (Exception e) {
                map.put("state", "error");
            }
        }
        return map;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object update(Role role) {
        Map map = new HashMap<>();
        try {
            roleService.update(role.getId(), role.getName(), role.getDisplayref(), role.getDescription());
            map.put("state", "success");
        } catch (Exception e) {
            map.put("state", "error");
        }
        return map;
    }

    /**
     * 分页列表
     */
    @RequestMapping("/list")
    public Object findAll(int page, int limit, String name) {
        Map<Object, Object> filter = new HashMap<>();
        filter.put("name", name);
        Page<Role> rolePage = roleService.selectPageList(page, limit, filter);
        Map map = new HashMap<>();
        map.put("success", true);
        map.put("total", rolePage.getTotalElements());
        map.put("items", rolePage.getContent());
        return map;
    }

    /**
     * 角色下的权限树
     */
    @RequestMapping("/authTreeForRole")
    public Object authTreeForRole(Long role) {
        return authorityService.authTreeForRole(role);
    }


}
