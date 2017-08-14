package anxian.gateway.admin.module.base.service.impl;

import anxian.gateway.admin.module.base.domain.*;
import anxian.gateway.admin.module.base.repository.*;
import anxian.gateway.admin.module.base.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleAuthRepository roleAuthRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AclUserRepository aclUserRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    /**
     * 操作用户与角色的关联
     */
    @Override
    public Map relation(boolean isAdd, Long roleId, Long aclUserId) {
        Map map = new HashMap<>();

        try {
            Role role = roleRepository.findOne(roleId);
            AclUser aclUser = aclUserRepository.findOne(aclUserId);
            List<RoleAuthority> byRole = roleAuthRepository.findByRole(roleId);

            if (isAdd) { //关联操作
                UserRole userRole = new UserRole();
                userRole.setSecurityuser(aclUser);
                userRole.setRole(role);
                roleUserRepository.save(userRole);

                for (RoleAuthority roleAuthority : byRole) {
                    UserAuthority userAuthority = new UserAuthority();
                    userAuthority.setSecurityuser(aclUser);
                    userAuthority.setRoleAuthority(roleAuthority);
                    userAuthority.setAuthority(roleAuthority.getAuthority());
                    userAuthorityRepository.save(userAuthority);
                }
            } else { //取消关联操作
                for (RoleAuthority roleAuthority : byRole) {
                    userAuthorityRepository.removeByRAU(roleAuthority, roleAuthority.getAuthority(), aclUser);
                }

                roleUserRepository.removeByRoleAndUser(role, aclUser);

            }

            map.put("state", "success");
        } catch (Exception e) {
            e.getStackTrace();
            map.put("state", "error");
        }

        return map;
    }
}
