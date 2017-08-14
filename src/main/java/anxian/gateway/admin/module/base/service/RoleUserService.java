package anxian.gateway.admin.module.base.service;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Map;

/**
 * Created by jiangzhe on 15-11-20.
 */
public interface RoleUserService {
    /**
     * 操作用户与角色的关联
     */
    @PreAuthorize("hasAuthority('ROLE_CONFIGURE')")
    Map relation(boolean isAdd, Long roleId, Long aclUserId);
}
