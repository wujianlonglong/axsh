package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.*;
import anxian.gateway.admin.module.base.repository.RoleAuthRepository;
import anxian.gateway.admin.module.base.repository.RoleUserRepository;
import anxian.gateway.admin.module.base.repository.UserAuthorityRepository;
import anxian.gateway.admin.module.base.service.AuthorityService;
import anxian.gateway.admin.module.base.service.RoleAuthorityService;
import anxian.gateway.admin.module.base.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    /**
     * 关联角色与权限
     */
    @Override
    public Map relation(boolean checked, Long roleId, Long authorityId) {
        Map map = new HashMap<>();

        Role role = roleService.findById(roleId); //获取角色

        Authority authority = authorityService.findById(authorityId);//获取权限

        List<UserRole> userRoles = roleUserRepository.findByRole(role);//获取用户与角色关联记录

        try {
            if (checked) {//关联的操作
                //保存角色与权限的关联
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRole(role);
                roleAuthority.setAuthority(authority);
                roleAuthRepository.save(roleAuthority);

                //保存用户与权限的关联
                for (UserRole userRole : userRoles) {
                    UserAuthority userAuthority = new UserAuthority();
                    userAuthority.setAuthority(authority);
                    userAuthority.setRoleAuthority(roleAuthority);
                    userAuthority.setSecurityuser(userRole.getSecurityuser());
                    userAuthorityRepository.save(userAuthority);
                }

            } else {//取消关联的操作

                //取消用户与角色的关联
                for (UserRole userRole : userRoles) {
                    userAuthorityRepository.removeByRoleAndAuthAndUser(authority, userRole.getSecurityuser());
                }

                //取消角色与权限的关联
                roleAuthRepository.removeByRoleAndAuth(role, authority);

            }

            map.put("state", "success");
        } catch (Exception e) {
            e.getStackTrace();
            map.put("state", "error");
        }

        return map;
    }
}
